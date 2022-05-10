package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.IMovable;

public class SpaceUber extends Spaceship {
    @Override
    public void makeNoise(){
        System.out.print("Ваш Убер прибыл");
    }
}
