package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IPurchased;

public class Engine extends Entity implements IPurchased {

    public Engine() {
        entityType = Engine.class;
    }
}
