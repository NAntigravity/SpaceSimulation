package space.simulation.oop.game.model.technologies;

public class Tardis extends Spaceship {
    public Tardis(Integer radius) {
        super(radius);
    }

    @Override
    public void makeNoise(){
        System.out.print("Вррруууум-вррррууууум");
    }
}
