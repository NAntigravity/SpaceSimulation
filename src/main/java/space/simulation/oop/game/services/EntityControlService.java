package space.simulation.oop.game.services;

import org.jetbrains.annotations.NotNull;
import space.simulation.oop.game.model.Coordinates;
import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.celestial.bodies.CelestialBodyWithMine;
import space.simulation.oop.game.model.celestial.bodies.Mine;
import space.simulation.oop.game.model.map.Map;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class EntityControlService {
    private ArrayList<Entity> entities = new ArrayList<>();
    private final Map gameField;
    private ArrayList<Entity> entitiesToCreate = new ArrayList<>();

    public EntityControlService(Map map) {
        gameField = map;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void spawnEntityOnCoordinates(@NotNull Entity entity, int x, int y) {
        entity.setCoordinateX(x);
        entity.setCoordinateY(y);
        entitiesToCreate.add(entity);
    }

    public void spawnEntityOnRandomCoordinates(Entity entity) {
        boolean flag = false;
        while (!flag) {
            int x = ThreadLocalRandom.current().nextInt(0, gameField.getWidth());
            int y = ThreadLocalRandom.current().nextInt(0, gameField.getHeight());
            flag = trySetupCoordinates(entity, x, y);
        }
    }

    public void appendExistingEntityCollection() {
        entities.addAll(entitiesToCreate);
        entitiesToCreate = new ArrayList<>();
    }

    private boolean trySetupCoordinates(@NotNull Entity entity, int x, int y) {
        if (!(MovableService.isObjectInsidePerimeter(new Coordinates(x, y),
                entity.getWidth(),
                entity.getHeight(),
                gameField))) {
            return false;
        }

        boolean overlap = isOverlapByAnotherEntity(x, y, entity.getWidth(), entity.getHeight());
        if (!overlap) {
            spawnEntityOnCoordinates(entity, x, y);
            if (entity instanceof CelestialBodyWithMine) {
                for (Mine mine :
                        ((CelestialBodyWithMine) entity).getMines()) {
                    spawnEntityOnCoordinates(
                            mine,
                            (int) (Math.random() * entity.getWidth() + x),
                            (int) (Math.random() * entity.getHeight() + y));
                }
            }
            return true;
        }
        return false;
    }

    private boolean isOverlapByAnotherEntity(int x, int y, int width, int height) {
        for (Entity e : entities) {
            if (e instanceof Mine) {
                continue;
            }
            if (!(MovableService.isCollidableWithVolumeObject(e, new Coordinates(x, y), width, height))) {
                continue;
            }
            return true;
        }
        return false;
    }

    public void killEntity(@NotNull Entity entity) {
        entity.setDead(true);
    }

    public void clearKilledEntities() {
        entities = entities.stream()
                .filter(entity -> !entity.isDead())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
