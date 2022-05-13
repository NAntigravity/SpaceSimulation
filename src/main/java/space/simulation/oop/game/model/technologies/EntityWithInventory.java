package space.simulation.oop.game.model.technologies;

import lombok.Getter;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IInventoryItem;
import space.simulation.oop.game.model.resources.IFossil;

import java.util.HashMap;
import java.util.Map;

public abstract class EntityWithInventory extends Entity {
    @Getter
    protected Map<Class<IInventoryItem>, Integer> inventory;

    @Getter
    protected Integer inventoryCapacity;

    public EntityWithInventory() {
        this.inventory = new HashMap<>();
        this.inventoryCapacity = 1;
    }

    protected boolean isInventoryNotFilled() {
        return getEmptySpaceAmount() > 0;
    }

    protected boolean isInventoryEmpty() {
        return getEmptySpaceAmount().equals(inventoryCapacity);
    }

    protected boolean tryDeleteFromInventory(Class itemType, Integer itemAmount) {
        if (inventory.containsKey(itemType)) {
            if (inventory.get(itemType) >= itemAmount) {
                inventory.put(itemType, inventory.get(itemType) - itemAmount);
                if (inventory.get(itemType) == 0) {
                    inventory.remove(itemType);
                }
                return true;
            }
        }
        return false;
    }

    protected boolean tryAddToInventory(Class itemType, Integer itemAmount) {
        int currentItemsAmount = inventory.values().stream().reduce(0, Integer::sum);
        if (currentItemsAmount + itemAmount > inventoryCapacity) {
            return false;
        }
        if (inventory.containsKey(itemType)) {
            inventory.put(itemType, inventory.get(itemType) + itemAmount);
        } else {
            inventory.put(itemType, itemAmount);
        }
        return true;
    }

    public Integer getEmptySpaceAmount() {
        return this.getInventoryCapacity() - inventory.values().stream().reduce(0, Integer::sum);
    }

    public Integer calculateInventoryPrice(SpaceStation spaceStation) {
        int totalPrice = -1;
        for (Map.Entry<Class<IInventoryItem>, Integer> item :
                inventory.entrySet()) {
            if (!IFossil.class.isAssignableFrom(item.getKey())) {
                continue;
            }
            try {
                Class<IFossil> objectKey = (Class<IFossil>) item.getKey().newInstance().getClass();
                Integer oneObjectPrice = spaceStation.getObjectPriceForSell(objectKey);
                if (oneObjectPrice == null) {
                    continue;
                }
                if (totalPrice == -1) {
                    totalPrice = oneObjectPrice + item.getValue();
                } else {
                    totalPrice += oneObjectPrice * item.getValue();
                }
            }
            catch (Exception e) {
                continue;
            }
        }
        if (totalPrice == -1) {
            return null;
        }
        return totalPrice;
    }
}
