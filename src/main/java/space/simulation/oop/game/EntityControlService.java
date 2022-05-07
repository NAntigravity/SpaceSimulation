package space.simulation.oop.game;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.Map;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class EntityControlService {
    private final Vector<Entity> entities = new Vector<>();
    private final Map gameField;
    private Vector<Entity> entitiesToCreate = new Vector<>();

    public EntityControlService(Map map) {
        gameField = map;
    }

    public Vector<Entity> getEntities() {
        return entities;
    }

    public void spawnEntityOnCoordinates(Entity entity, int x, int y) {
        entity.setCoordinateX(x);
        entity.setCoordinateY(y);
        entitiesToCreate.add(entity);
    }

    public void spawnEntityOnRandomCoordinates(Entity entity, Map map) {
        boolean flag = false;
        while (!flag) {
            int x = ThreadLocalRandom.current().nextInt(0, gameField.getWidth());
            int y = ThreadLocalRandom.current().nextInt(0, gameField.getHeight());
            flag = trySetupCoordinates(entity, map, x, y);
        }
    }

    private boolean trySetupCoordinates(Entity entity, Map map, int x, int y) {
        boolean overlap = isOverlapByAnotherEntity(x, y, entity.getWidth(), entity.getHeight());
        if (!overlap) {
            spawnEntityOnCoordinates(entity, x, y);
            return true;
        }
        return false;
    }

    private boolean isOverlapByAnotherEntity(int x, int y, int width, int height) {
        for (Entity e : entities) {
            int intersectLUX = Math.max(x, e.getCoordinateX());
            int intersectLUY = Math.max(y, e.getCoordinateY());
            int intersectRDX = Math.min(x + width, e.getCoordinateX() + e.getWidth());
            int intersectRDY = Math.min(y + height, e.getCoordinateY() + e.getHeight());
            int intersectWidth = intersectRDX - intersectLUX;
            int intersectHeight = intersectRDY - intersectLUY;
            if (intersectWidth <= 0 || intersectHeight <= 0) {
                continue;
            }
            return true;
        }
        return false;
    }
}
