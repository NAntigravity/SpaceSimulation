package space.simulation.oop.game.model.resources;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IPurchased;

public class Iron extends Entity implements IFossil, IPurchased {
    public Iron() {
        entityType = Iron.class;
    }
}
