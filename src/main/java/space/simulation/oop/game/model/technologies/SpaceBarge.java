package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.celestial.bodies.Asteroid;

import static space.simulation.oop.game.configs.SpaceSimulationConstants.SPACE_BARGE_INVENTORY_CAPACITY;

public class SpaceBarge extends Spaceship {
    public SpaceBarge() {
        super();
        entityType = SpaceBarge.class;
        this.availableForLandingPredicate = entity -> entity instanceof Asteroid || entity instanceof SpaceStation;
        inventoryCapacity = SPACE_BARGE_INVENTORY_CAPACITY;
    }
}
