package space.simulation.oop.game.model.technologies;

public class ScoutShip extends Spaceship {
    public ScoutShip() {
        super();
        entityType = ScoutShip.class;
    }

    @Override
    public void makeNoise(){
        System.out.println("Shinzou Wo Sasageyo!");
    }
}
