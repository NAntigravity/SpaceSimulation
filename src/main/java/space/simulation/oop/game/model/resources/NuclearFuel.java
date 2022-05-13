package space.simulation.oop.game.model.resources;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IPurchased;

public class NuclearFuel extends Entity implements IFuel, IPurchased {
    public NuclearFuel() {
        entityType = NuclearFuel.class;
    }
}
