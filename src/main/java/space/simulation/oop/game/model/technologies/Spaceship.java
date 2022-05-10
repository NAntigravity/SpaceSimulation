package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.IMovable;

public abstract class Spaceship implements IMovable {
    public void makeNoise(){
        System.out.print("Какой-то шум");
    }
}
