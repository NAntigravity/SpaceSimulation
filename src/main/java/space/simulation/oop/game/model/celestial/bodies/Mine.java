package space.simulation.oop.game.model.celestial.bodies;

import lombok.Getter;
import lombok.Setter;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.resources.IFossil;

public class Mine<T extends IFossil> extends Entity {
    @Getter
    @Setter
    public Integer resourceAmount;

    @Getter
    public Class<T> resourceType;

    @Override
    public void existOneTick() {
    }

    public Integer giveResource(){
        return 0;
    }

}
