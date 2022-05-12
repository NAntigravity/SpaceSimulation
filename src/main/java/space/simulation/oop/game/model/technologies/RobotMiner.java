package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IInventoryItem;
import space.simulation.oop.game.model.IMovable;
import space.simulation.oop.game.model.IPurchased;
import space.simulation.oop.game.model.celestial.bodies.Mine;
import space.simulation.oop.game.model.celestial.bodies.Planet;
import space.simulation.oop.game.services.MovableService;

import java.util.Map;

import static space.simulation.oop.game.configs.SpaceSimulationConstants.ROBOTS_INVENTORY_CAPACITY;

public class RobotMiner extends EntityWithInventory implements IMovable, IPurchased {
    private final Mine purposeOfMining;
    private final Planet planet;
    private final Spaceship owner;

    public RobotMiner(Mine purposeOfMining, Planet planet, Spaceship owner) {
        this.purposeOfMining = purposeOfMining;
        this.planet = planet;
        this.owner = owner;
        this.inventoryCapacity = ROBOTS_INVENTORY_CAPACITY;
    }

    @Override
    public void existOneTick() {
        move();
        mineResource();
        giveAwayResource();
    }

    @Override
    public void move() {
        Entity target;
        if (isOwnerLanded() && !isInventoryEmpty()) {
            target = owner;
        } else {
            target = purposeOfMining;
        }
        MovableService.moveToTarget(this, target);
    }

    private boolean isOwnerLanded() {
        return owner.target == planet && owner.landed;
    }

    private void mineResource() {
        if (this.isInventoryNotFilled() && MovableService.isCollidableWithVolumeObject(
                purposeOfMining, this.getCoordinates(), this.getWidth(), this.getHeight())) {
            var mineResult = tryAddToInventory(purposeOfMining.getResourceType(), purposeOfMining.getMinePower());
            if (!mineResult) {
                tryAddToInventory(purposeOfMining.getResourceType(), this.getEmptySpaceAmount());
            }
        }
    }

    private void giveAwayResource() {
        if (isOwnerLanded() && !isInventoryEmpty() && MovableService.isCollidableWithVolumeObject(
                owner, this.getCoordinates(), this.getWidth(), this.getHeight())) {
            for (Map.Entry<Class<IInventoryItem>, Integer> item :
                 inventory.entrySet()) {
                int giveResourceAmount = owner.getEmptySpaceAmount() < item.getValue() ?
                        owner.getEmptySpaceAmount() - item.getValue() :
                        item.getValue();
                owner.tryAddToInventory(item.getKey(), giveResourceAmount);
                this.tryDeleteFromInventory(item.getKey(), giveResourceAmount);
            }
        }
    }
}
