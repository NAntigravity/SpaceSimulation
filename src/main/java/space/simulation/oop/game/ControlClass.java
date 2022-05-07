package space.simulation.oop.game;

import lombok.Getter;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IMovable;
import space.simulation.oop.game.model.Map;
import space.simulation.oop.game.model.celestial.bodies.Star;

import java.util.Vector;

import static space.simulation.oop.game.SpaceSimulationConstants.*;

public class ControlClass {
    private final Map gameField;
    private static ControlClass INSTANCE;
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

    public Vector<Entity> getEntities() {
        return entityControlService.getEntities();
    }

    public void start() {
        generateStars();
    }

    public void liveOneTick() {
        synchronized (lock) {
            entityControlService.appendExistingEntityCollection();
        }
    }

    public Map getGameField() {
        Vector<Entity> entities = getEntities();
        for (Entity entity :
                entities) {
            if (!(entity instanceof IMovable)) {
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
        int starsAmount = (int) (Math.random() * MAX_STARS_AMOUNT);
        for (int i = 0; i <= starsAmount; i++) {
            int starWidth = (int) (Math.random() * MAX_STAR_WIDTH + MIN_STAR_WIDTH);
            int starHeight = (int) (Math.random() * MAX_STAR_HEIGHT + MIN_STAR_HEIGHT);
            int starDamageRadius = (int) (Math.random() * MAX_STAR_DAMAGE_RADIUS + MIN_STAR_DAMAGE_RADIUS);
            int starRadiationRadius = (int) (Math.random() * MAX_STAR_RADIATION_RADIUS + MIN_STAR_RADIATION_RADIUS);
            int starRadiationPower = (int) (Math.random() * MAX_STAR_RADIATION_POWER + MIN_STAR_RADIATION_POWER);
            Entity entityToSpawn = new Star(starWidth, starHeight, starRadiationRadius, starRadiationPower, starDamageRadius);
            entityControlService.spawnEntityOnRandomCoordinates(entityToSpawn, gameField);
        }
    }
}
