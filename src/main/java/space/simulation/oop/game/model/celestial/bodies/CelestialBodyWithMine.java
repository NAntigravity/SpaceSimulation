package space.simulation.oop.game.model.celestial.bodies;

import lombok.Getter;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.resources.IFossil;

import java.util.List;

public abstract class CelestialBodyWithMine extends Entity {
    @Getter
    public List<Mine<IFossil>> mines;

    public CelestialBodyWithMine() {
        //Рандомное количество месторождений с рандомными ресурсами
    }
}
