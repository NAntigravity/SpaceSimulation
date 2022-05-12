package space.simulation.oop.game.model.celestial.bodies;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import space.simulation.oop.game.ControlClass;
import space.simulation.oop.game.configs.SpaceSimulationConfiguration;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.technologies.*;
import space.simulation.oop.game.services.MovableService;

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

    public Star(Integer radius, Integer radiationRadius, Integer radiationPower, Integer damageRadius) {
        super();
        entityType = Star.class;
        this.radiationRadius = radiationRadius;
        this.radiationPower = radiationPower;
        this.damageRadius = damageRadius;
        this.width = radius;
        this.height = radius;
    }

    @Override
    public void existOneTick() {
        exterminate();
    }

    private void exterminate() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpaceSimulationConfiguration.class);
        ControlClass game = context.getBean(ControlClass.class);

        var allEntities = game.getEntities();
        for (Entity entity : allEntities) {
            // Because java can't catch parent class
            // And because java is piece of programming language
            // YEEEES!! JAVA IS TRUE OOP!
            // OF COURSE!
            // AAAAAAAAAAAAAAAAAAAAA
            if (!(entity instanceof ScoutShip ||
                    entity instanceof SpaceBarge ||
                    entity instanceof SpaceUber ||
                    entity instanceof Tardis)) {
                continue;
            }
            var distance = MovableService.getDistanceToEntity(entity, this.getCoordinates(), this.getWidth(), this.getHeight());
            if (distance <= damageRadius) {
                // Landed spaceships defended from radiation by CelestialBody atmosphere
                // (yes, asteroids has atmosphere too))0)
                if(((Spaceship) entity).isLanded()) {
                    continue;
                }
                game.getEntityControlService().killEntity(entity);
            }
        }
    }
}
