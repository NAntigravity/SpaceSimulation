package space.simulation.oop.game.model.celestial.bodies;

import lombok.Getter;
import lombok.Setter;
import space.simulation.oop.game.model.Entity;

public class Star extends Entity {
    @Getter
    @Setter
    public Integer radiationRadius;

    @Getter
    @Setter
    public Integer radiationPower;

    @Getter
    @Setter
    private Integer damageRadius;

    public Star(Integer width, Integer height, Integer radiationRadius, Integer radiationPower, Integer damageRadius) {
        this.radiationRadius = radiationRadius;
        this.radiationPower = radiationPower;
        this.damageRadius = damageRadius;
        this.width = width;
        this.height = height;
    }

    @Override
    public void existOneTick() {
    }

    private void exterminate(){

    }
}
