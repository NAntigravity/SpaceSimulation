package space.simulation.oop.game.model.technologies;

public class Tardis extends Spaceship {
    public Tardis() {
        super();
        this.availableForLandingPredicate = entity -> false;
    }

    @Override
    public void makeNoise(){
        System.out.println("Вррруууум-вррррууууум");
    }
}
