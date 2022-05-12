package space.simulation.oop.game;

import lombok.Getter;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IMovable;
import space.simulation.oop.game.model.celestial.bodies.Asteroid;
import space.simulation.oop.game.model.celestial.bodies.Mine;
import space.simulation.oop.game.model.celestial.bodies.Planet;
import space.simulation.oop.game.model.celestial.bodies.Star;
import space.simulation.oop.game.model.map.Map;
import space.simulation.oop.game.model.technologies.ScoutShip;
import space.simulation.oop.game.model.technologies.SpaceBarge;
import space.simulation.oop.game.model.technologies.SpaceUber;
import space.simulation.oop.game.services.EntityControlService;

import java.util.ArrayList;

import static space.simulation.oop.game.configs.SpaceSimulationConstants.*;

public class ControlClass {
    private final Map gameField;
    private static ControlClass INSTANCE;
    @Getter
    private final EntityControlService entityControlService;
    private final Object lock = new Object();

    private ControlClass() {
        gameField = new Map(40, 40);
        entityControlService = new EntityControlService(gameField);
        start();
    }

    public static ControlClass getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ControlClass();
        }
        return INSTANCE;
    }

    public ArrayList<Entity> getEntities() {
        return entityControlService.getEntities();
    }

    public void start() {
        generateStars();
        generatePlanets();
        generateAsteroids();
        generateSpaceships();
    }

    public void liveOneTick() {
        synchronized (lock) {
            for (Entity entity : getEntities()) {
                entity.existOneTick();
            }
            entityControlService.clearKilledEntities();
            entityControlService.appendExistingEntityCollection();
        }
    }

    public Map getGameField() {
        ArrayList<Entity> entities = getEntities();
        for (Entity entity :
                entities) {
            if (!(entity instanceof IMovable || entity instanceof Mine)) {
                for (int i = 0; i < entity.getWidth(); i++) {
                    for (int j = 0; j < entity.getHeight(); j++) {
                        gameField.updateTileOnCoordinate(
                                entity.getEntityType(),
                                entity.getCoordinateX() + i,
                                entity.getCoordinateY() + j);
                    }
                }
            }
        }
        return gameField;
    }

    private void generateStars() {
        int starsAmount = (int) (Math.random() * (MAX_STARS_AMOUNT - MIN_STARS_AMOUNT + 1) + MIN_STARS_AMOUNT);
        for (int i = 0; i < starsAmount; i++) {
            int starRadius = (int) (Math.random() * (MAX_STAR_RADIUS - MIN_STAR_RADIUS + 1) + MIN_STAR_RADIUS);
            int starDamageRadius = (int) (Math.random() * (MAX_STAR_DAMAGE_RADIUS - MIN_STAR_DAMAGE_RADIUS + 1) + MIN_STAR_DAMAGE_RADIUS);
            int starRadiationRadius = (int) (Math.random() * (MAX_STAR_RADIATION_RADIUS - MIN_STAR_RADIATION_RADIUS + 1) + MIN_STAR_RADIATION_RADIUS);
            int starRadiationPower = (int) (Math.random() * (MAX_STAR_RADIATION_POWER - MIN_STAR_RADIATION_POWER + 1) + MIN_STAR_RADIATION_POWER);
            Entity entityToSpawn = new Star(starRadius, starRadiationRadius, starRadiationPower, starDamageRadius);
            entityControlService.spawnEntityOnRandomCoordinates(entityToSpawn);
        }
    }

    private void generatePlanets() {
        int planetsAmount = (int) (Math.random() * (MAX_PLANET_AMOUNT - MIN_PLANET_AMOUNT + 1) + MIN_PLANET_AMOUNT);
        for (int i = 0; i < planetsAmount; i++) {
            int planetRadius = (int) (Math.random() * (MAX_PLANET_RADIUS - MIN_PLANET_RADIUS + 1) + MIN_PLANET_RADIUS);
            Entity entityToSpawn = new Planet(planetRadius);
            entityControlService.spawnEntityOnRandomCoordinates(entityToSpawn);
        }
    }

    private void generateAsteroids() {
        int asteroidsAmount = (int) (Math.random() * (MAX_ASTEROID_AMOUNT - MIN_ASTEROID_AMOUNT + 1) + MIN_ASTEROID_AMOUNT);
        for (int i = 0; i < asteroidsAmount; i++) {
            Entity entityToSpawn = new Asteroid(ASTEROID_RADIUS);
            entityControlService.spawnEntityOnRandomCoordinates(entityToSpawn);
        }
    }

    private void generateSpaceships() {
        int spaceshipAmount = (int) (Math.random() * (MAX_SPACESHIP_AMOUNT - MIN_SPACESHIP_AMOUNT + 1) + MIN_SPACESHIP_AMOUNT);
        for (int i = 0; i < spaceshipAmount; i++) {
            int spawnEntityNumber = (int) (Math.random() * 3);
            Entity entityToSpawn;
            switch (spawnEntityNumber) {
                case 1:
                    entityToSpawn = new SpaceBarge();
                    break;
                case 2:
                    entityToSpawn = new SpaceUber();
                    break;
                default:
                    entityToSpawn = new ScoutShip();
                    break;
            }
            entityControlService.spawnEntityOnRandomCoordinates(entityToSpawn);
        }
    }
}
