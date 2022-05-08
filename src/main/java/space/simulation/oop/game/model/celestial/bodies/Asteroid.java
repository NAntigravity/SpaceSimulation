package space.simulation.oop.game.model.celestial.bodies;

import space.simulation.oop.game.model.IAvailableForLanding;
import space.simulation.oop.game.model.IMovable;

public class Asteroid extends CelestialBodyWithMine implements IAvailableForLanding, IMovable {
    public Asteroid(Integer radius) {
        super(radius);
        entityType = Asteroid.class;
    }

    @Override
    public void existOneTick() {

    }

    @Override
    public void move() {

    }
}
