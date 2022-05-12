package space.simulation.oop.game.model.technologies;

public class SpaceUber extends Spaceship {
    public SpaceUber() {
        super();
        entityType = SpaceUber.class;
    }

    @Override
    public void makeNoise(){
        System.out.println("Ваш Убер прибыл");
    }
}
