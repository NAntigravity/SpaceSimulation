package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IMovable;

public abstract class Spaceship extends Entity implements IMovable {
    public void makeNoise(){
        System.out.print("Какой-то шум");
    }

    @Override
    public void move() {

    }

}
