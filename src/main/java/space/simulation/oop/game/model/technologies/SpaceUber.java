package space.simulation.oop.game.model.technologies;

public class SpaceUber extends Spaceship {
    public SpaceUber(Integer radius) {
        super(radius);
        entityType = SpaceBarge.class;
    }

    @Override
    public void makeNoise(){
        System.out.print("Ваш Убер прибыл");
    }
}
