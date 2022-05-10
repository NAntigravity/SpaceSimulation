package space.simulation.oop.game.model.celestial.bodies;

import space.simulation.oop.game.MovableService;
import space.simulation.oop.game.model.Direction;
import space.simulation.oop.game.model.IAvailableForLanding;
import space.simulation.oop.game.model.IMovable;

public class Asteroid extends CelestialBodyWithMine implements IAvailableForLanding, IMovable {
    public Asteroid(Integer radius) {
        super(radius);
        entityType = Asteroid.class;
    }

    @Override
    public void move() {
        Direction direction = MovableService.getRandomDirection();
        if (MovableService.isEntityMotionAvailable(this, direction,true)){
            MovableService.move(this, direction);
        }
    }
}
