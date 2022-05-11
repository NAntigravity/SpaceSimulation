package space.simulation.oop.game.model.celestial.bodies;

import space.simulation.oop.game.services.MovableService;
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
        boolean flag = MovableService.isEntityMotionAvailable(this, direction,true);
        while (!flag) {
            direction = MovableService.getRandomDirection();
            flag = MovableService.isEntityMotionAvailable(this, direction,true);
        }
        MovableService.move(this, direction);
        for (Mine mine :
                getMines()) {
            mine.setCoordinates(MovableService.getCoordinateChanges(mine.getCoordinates(), direction));
        }
    }
}
