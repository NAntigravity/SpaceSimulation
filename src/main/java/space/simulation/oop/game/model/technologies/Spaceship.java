package space.simulation.oop.game.model.technologies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import space.simulation.oop.game.ControlClass;
import space.simulation.oop.game.configs.SpaceSimulationConfiguration;
import space.simulation.oop.game.model.Direction;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IMovable;
import space.simulation.oop.game.model.celestial.bodies.CelestialBodyWithMine;
import space.simulation.oop.game.services.MovableService;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public abstract class Spaceship extends EntityWithInventory implements IMovable {
    @JsonIgnore
    @Getter
    @Setter
    protected Entity target;

    @Getter
    @Setter
    protected ArrayList<RobotMiner> robots;

    @Getter
    @Setter
    protected boolean landed = false;

    public Spaceship(Integer radius) {
        target = null;
        this.width = radius;
        this.height = radius;
        entityType = Spaceship.class;
    }

    public void makeNoise() {
        System.out.print("Какой-то шум");
    }

    // заменить на liveOneTick
    @Override
    public void move() {
        landing();
        if (target == null) {
            searchAndSetNewTarget();
        }
        if (!landed) {
            moveToTarget();
        }
    }

    protected void moveToTarget() {
        if (target == null) {
            return;
        }

        int targetX = target.getCoordinateX();
        int targetY = target.getCoordinateY();

        if (targetX > coordinateX) {
            MovableService.move(this, Direction.RIGHT);
            return;
        } else if (targetX < coordinateX) {
            MovableService.move(this, Direction.LEFT);
            return;
        }
        if (targetY > coordinateY) {
            MovableService.move(this, Direction.DOWN);
        } else if (targetY < coordinateY) {
            MovableService.move(this, Direction.UP);
        }
    }

    // TODO: rewrite the logic of the function below, as long as right now it is just a stub for test

    protected void searchAndSetNewTarget() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpaceSimulationConfiguration.class);
        ControlClass game = context.getBean(ControlClass.class);

        var allEntities = game.getEntities();
        var filteredEntities = allEntities.stream()
                .filter(entity -> (entity instanceof CelestialBodyWithMine || entity instanceof SpaceStation))
                .collect(Collectors.toCollection(ArrayList::new));
        int randomEntityNumber = ThreadLocalRandom.current().nextInt(0, filteredEntities.size());
        this.setTarget(filteredEntities.get(randomEntityNumber));
    }

    protected void landing() {
        if (target == null) {
            return;
        }
        if (MovableService.getDistanceToEntity(target, this.getCoordinates(), this.getWidth(), this.getHeight()) < 0) {
            landed = true;
        }
    }

    protected void landingOnPlanet() {
        // если у корабля есть роботы, он выпускает роботов на планету и улетает, когда инвентарь с роботами опустеет
        // если у корабля нет роботов и он задетектил своих роботов на планете, он забирает их ресурсы и улетает
        // если у корабля нет роботов и он не задетектил на планете своих роботов, он улетает
    }

    protected void landingOnAsteroid() {

    }

    protected void landingOnSpaceStation() {

    }

    protected boolean robotSearch() {
        return false;
    }
}
