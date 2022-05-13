package space.simulation.oop.game.model.technologies;

import static space.simulation.oop.game.configs.SpaceSimulationConstants.SPACE_UBER_INVENTORY_CAPACITY;
import static space.simulation.oop.game.configs.SpaceSimulationConstants.SPACE_UBER_ROBOTS_AMOUNT_PER_START;

public class SpaceUber extends Spaceship {
    public SpaceUber() {
        super();
        entityType = SpaceUber.class;
        inventoryCapacity = SPACE_UBER_INVENTORY_CAPACITY;
        tryAddToInventory(RobotMiner.class, SPACE_UBER_ROBOTS_AMOUNT_PER_START);
    }

    @Override
    public void makeNoise(){
        System.out.println("Ваш Убер прибыл");
    }
}
