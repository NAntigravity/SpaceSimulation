package space.simulation.oop.game.model.technologies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import space.simulation.oop.game.ControlClass;
import space.simulation.oop.game.configs.SpaceSimulationConfiguration;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IMovable;
import space.simulation.oop.game.model.celestial.bodies.Asteroid;
import space.simulation.oop.game.model.celestial.bodies.CelestialBodyWithMine;
import space.simulation.oop.game.model.celestial.bodies.Mine;
import space.simulation.oop.game.model.celestial.bodies.Planet;
import space.simulation.oop.game.services.MovableService;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Spaceship extends EntityWithInventory implements IMovable {
    @JsonIgnore
    @Getter
    @Setter
    protected Entity target;

    @JsonIgnore
    @Getter
    @Setter
    protected ArrayList<RobotMiner> landedRobots = new ArrayList<>();

    @Getter
    @Setter
    protected boolean landed = false;

    protected Predicate<Entity> availableForLandingPredicate;

    public Spaceship() {
        target = null;
        entityType = Spaceship.class;
        this.availableForLandingPredicate = entity -> entity instanceof Planet || entity instanceof SpaceStation;
    }

    public void makeNoise() {
        System.out.println("Какой-то шум");
    }

    @Override
    public void move() {
        if (!landed) {
            MovableService.moveToTarget(this, target);
        }
    }

    @Override
    public void existOneTick() {
        move();
        landing();
        if (target == null) {
            Entity newTarget = trySearchNewTarget();
            if (newTarget != null) {
                this.setTarget(newTarget);
            }
        }
        makeNoise();
    }

    // TODO: rewrite the logic of the function below, as long as right now it is just a stub for test

    protected Entity trySearchNewTarget() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpaceSimulationConfiguration.class);
        ControlClass game = context.getBean(ControlClass.class);

        var filteredEntities = game.getEntities().stream()
                .filter(this.availableForLandingPredicate)
                .collect(Collectors.toCollection(ArrayList::new));
        int randomEntityNumber = ThreadLocalRandom.current().nextInt(0, filteredEntities.size());
        return filteredEntities.get(randomEntityNumber);
    }

    protected void landing() {
        if (target == null) {
            return;
        }
        if (landed) {
            if (target instanceof Asteroid) {
                this.setCoordinates(target.getCoordinates());
            }
        }
        int landingDistance = 0;
        if (target instanceof Asteroid) {
            landingDistance = 1;
        }
        if (landed || MovableService.getDistanceToEntity(target, this.getCoordinates(), this.getWidth(), this.getHeight())
                <= landingDistance) {
            landed = true;
            if (target instanceof Asteroid) {
                landingOnAsteroid();
            } else if (target instanceof Planet) {
                landingOnPlanet();
            } else if (target instanceof SpaceStation) {
                landingOnSpaceStation();
            }
        }
    }

    protected void landingOnPlanet() {
         if (inventory.containsKey(RobotMiner.class)) {
            int robotsAmount = inventory.get(RobotMiner.class);
            AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(SpaceSimulationConfiguration.class);
            ControlClass game = context.getBean(ControlClass.class);
            for (int i = 0; i < robotsAmount; i++) {
                var selectedMine = getRandomMine((CelestialBodyWithMine) target);
                if (selectedMine == null) {
                    landed = false;
                    target = null;
                    return;
                }
                RobotMiner robotMiner = new RobotMiner(selectedMine, (Planet) target, this);
                landedRobots.add(robotMiner);
                game.getEntityControlService().spawnEntityOnCoordinates(robotMiner, target.getCoordinateX(), target.getCoordinateY());
                this.tryDeleteFromInventory(RobotMiner.class, 1);
            }
        } else {
             if (landedRobots.stream().noneMatch(robotMiner -> robotMiner.getPlanet() == target)) {
                 landed = false;
                 target = null;
                 return;
             }
         }
        if (isInventoryNotFilled()) {
            return;
        }
        landed = false;
        target = null;
    }

    protected void landingOnAsteroid() {
        // if spaceship has free space in inventory, spaceship lands on asteroid and starts to get resources
        // after there will be no free space in inventory, spaceship leave
        var selectedMine = getRandomMine((CelestialBodyWithMine) target);
        if (selectedMine == null) {
            landed = false;
            target = null;
            return;
        }
        boolean addItemResult = this.tryAddToInventory(selectedMine.getResourceType(), selectedMine.getMinePower());
        if (!addItemResult) {
            this.tryAddToInventory(selectedMine.getResourceType(), this.getEmptySpaceAmount());
            landed = false;
            target = null;
        }
    }

    private @Nullable Mine getRandomMine(@NotNull CelestialBodyWithMine celestialBodyWithMine) {
        var mines = celestialBodyWithMine.getMines();
        if (mines.size() == 0) {
            return null;
        }
        return celestialBodyWithMine.getMines().get(
                (int) (Math.random() * celestialBodyWithMine.getMines().size()));
    }

    protected void landingOnSpaceStation() {
    }

}
