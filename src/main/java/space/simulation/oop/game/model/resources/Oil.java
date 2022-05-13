package space.simulation.oop.game.model.resources;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IPurchased;

public class Oil extends Entity implements IPurchased, IFuel, IFossil {
    public Oil() {
        entityType = Oil.class;
    }
}
