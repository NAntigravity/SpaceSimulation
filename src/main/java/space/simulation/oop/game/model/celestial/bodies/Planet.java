package space.simulation.oop.game.model.celestial.bodies;

import space.simulation.oop.game.model.IAvailableForLanding;

public class Planet extends CelestialBodyWithMine implements IAvailableForLanding {
    public Planet(Integer radius) {
        super(radius);
    }

    @Override
    public void existOneTick() {

    }
}
