package space.simulation.oop.game.model.technologies;

import lombok.Getter;
import space.simulation.oop.game.model.IInventoryItem;

import java.util.Map;

public abstract class EntityWithInventory {
    @Getter
    protected Map<Class<IInventoryItem>, Integer> inventory;

    protected boolean deleteFromInventory(Class<IInventoryItem> itemType, Integer itemAmount) {
        if (inventory.containsKey(itemType)) {
            if (inventory.get(itemType) > itemAmount) {
                inventory.put(itemType, inventory.get(itemType) - itemAmount);
                return true;
            }
        }
        return false;
    }

    protected void addToInventory(Class<IInventoryItem> itemType, Integer itemAmount){
        if (inventory.containsKey(itemType)) {
                inventory.put(itemType, inventory.get(itemType) + itemAmount);
        } else {
            inventory.put(itemType, itemAmount);
        }
    }
}
