package space.simulation.oop.game.model.celestial.bodies;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.resources.IFossil;

import static space.simulation.oop.game.configs.SpaceSimulationConstants.MAX_MINE_POWER;
import static space.simulation.oop.game.configs.SpaceSimulationConstants.MIN_MINE_POWER;

public class Mine<T extends IFossil> extends Entity {

    @Getter
    @Setter
    public Integer minePower;

    @Getter
    public Class<T> resourceType;

    public Mine(@NotNull T entity) {
        this(entity, (int) (Math.random() * (MAX_MINE_POWER - MIN_MINE_POWER + 1) + MIN_MINE_POWER));
    }

    public Mine(@NotNull T entity, Integer minePower) {
        this.width = 1;
        this.height = 1;
        this.entityType = Mine.class;
        this.resourceType = (Class<T>) entity.getClass();
        this.minePower = minePower;
    }

    @Override
    public void existOneTick() {
    }

    public Integer giveResource() {
        return 0;
    }

}
