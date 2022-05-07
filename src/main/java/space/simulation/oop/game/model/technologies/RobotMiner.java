package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.IMovable;
import space.simulation.oop.game.model.IPurchased;
import space.simulation.oop.game.model.celestial.bodies.Mine;
import space.simulation.oop.game.model.celestial.bodies.Planet;
import space.simulation.oop.game.model.resources.IFossil;

public class RobotMiner extends EntityWithInventory implements IMovable, IPurchased {
    private boolean isGrounded;
    private Mine<IFossil> purposeOfMining;
    private Spaceship purposeOfUnloading;
    private Planet planet;

    @Override
    public void move() {

    }

    private void getResource(){

    }

    private void giveResource(){

    }

//    protected boolean deleteFromInventory(Class<IFossil> itemType, Integer itemAmount) {
    // Спросить у знающих людей про ограничения. В отчаянном случае страдать с эксепшенами
//        return super.deleteFromInventory(itemType, itemAmount);
//    }

}
