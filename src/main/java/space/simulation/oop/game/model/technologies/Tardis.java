package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.Direction;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.services.MovableService;

public class Tardis extends Spaceship {
    public Tardis() {
        super();
        this.availableForLandingPredicate = entity -> false;
    }

    @Override
    public void move() {
        Direction direction = MovableService.getRandomDirection();
        boolean flag = MovableService.isEntityMotionAvailable(this, direction, true);
        while (!flag) {
            direction = MovableService.getRandomDirection();
            flag = MovableService.isEntityMotionAvailable(this, direction, true);
        }
        MovableService.move(this, direction);
    }


    @Override
    public void existOneTick() {
        move();
        if (Math.random() < 0.3) {
            makeNoise();
        }
    }

    @Override
    public void makeNoise() {
        System.out.println("Вррруууум-вррррууууум");
    }
}
