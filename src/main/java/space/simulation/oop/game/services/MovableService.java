package space.simulation.oop.game.services;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import space.simulation.oop.game.ControlClass;
import space.simulation.oop.game.configs.SpaceSimulationConfiguration;
import space.simulation.oop.game.model.*;
import space.simulation.oop.game.model.map.Map;
import space.simulation.oop.game.model.map.Tile;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.sqrt;

public class MovableService {
    public static boolean isEntityMotionAvailable(Entity entity,
                                                  Direction direction,
                                                  Boolean isCollidable) {
        if (!(entity instanceof IMovable)) {
            return false;
        }

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpaceSimulationConfiguration.class);
        ControlClass game = context.getBean(ControlClass.class);

        Coordinates tempCoordinates = getCoordinateChanges(entity.getCoordinates(), direction);
        int tempX = tempCoordinates.getX();
        int tempY = tempCoordinates.getY();

        if (isObjectInsidePerimeter(tempCoordinates, entity.getWidth(), entity.getHeight(), game.getGameField())) {
            if (!isCollidable) {
                return true;
            }
            //on these coordinates there are no objects that do not implement IMovable
            if (game.getGameField().getTiles().get(tempX).get(tempY).getTileType() == Tile.class) {
                for (Entity e : game.getEntities()) {
                    if (isCollidableWithVolumeObject(e, tempCoordinates, entity.getWidth(), entity.getHeight())) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Contract("_, _ -> new")
    public static @NotNull Coordinates getCoordinateChanges(@NotNull Coordinates oldCoordinates,
                                                            @NotNull Direction direction) {
        int tempX = oldCoordinates.getX();
        int tempY = oldCoordinates.getY();

        switch (direction) {
            case RIGHT:
                tempX += 1;
                break;
            case LEFT:
                tempX -= 1;
                break;
            case DOWN:
                tempY += 1;
                break;
            case UP:
                tempY -= 1;
                break;
        }
        return new Coordinates(tempX, tempY);
    }

    public static void move(@NotNull Entity entity, Direction direction) {
        entity.setCoordinates(getCoordinateChanges(entity.getCoordinates(), direction));
    }

    public static Direction getRandomDirection() {
        int direction = ThreadLocalRandom.current().nextInt(1, 5);

        switch (direction) {
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.LEFT;
            case 3:
                return Direction.DOWN;
            default:
                return Direction.UP;
        }
    }

    public static boolean isCollidableWithVolumeObject(@NotNull Entity e,
                                                       @NotNull Coordinates coordinates,
                                                       int width,
                                                       int height) {
        int intersectLUY = Math.max(coordinates.getY(), e.getCoordinateY());
        int intersectLUX = Math.max(coordinates.getX(), e.getCoordinateX());
        int intersectRDX = Math.min(coordinates.getX() + width, e.getCoordinateX() + e.getWidth());
        int intersectRDY = Math.min(coordinates.getY() + height, e.getCoordinateY() + e.getHeight());
        int intersectWidth = intersectRDX - intersectLUX;
        int intersectHeight = intersectRDY - intersectLUY;
        return intersectWidth > 0 && intersectHeight > 0;
    }

    public static boolean isObjectInsidePerimeter(@NotNull Coordinates coordinates, int width, int height, Map map) {
        if (coordinates.getX() >= 0 && coordinates.getX() < map.getWidth()
                && coordinates.getY() >= 0 && coordinates.getY() < map.getHeight()) {
            if (coordinates.getX() + width >= 0 && coordinates.getX() + width < map.getWidth()
                    && coordinates.getY() + height >= 0 && coordinates.getY() + height < map.getHeight()) {
                return true;
            }
        }
        return false;
    }

    @Contract(value = "_, _, _, _ -> new", pure = true)
    public static @NotNull Double getDistanceToEntity(@NotNull Entity e,
                                                      @NotNull Coordinates coordinates,
                                                      int width,
                                                      int height) {
        double minDistance;

        Coordinates centerEntityOne = new Coordinates(coordinates.getX() + width / 2, coordinates.getY() + height / 2);
        Coordinates centerEntityTwo = new Coordinates(e.getCoordinateX() + e.getWidth() / 2, e.getCoordinateY() + e.getHeight() / 2);

        double distanceBetweenCentersX = Math.abs(centerEntityTwo.getX() - centerEntityOne.getX());
        double distanceBetweenCentersY = Math.abs(centerEntityTwo.getY() - centerEntityOne.getY());

        // The two rectangles do not overlap. There are two overlapping rectangles in the X direction.
        if ((distanceBetweenCentersX < ((width + e.getWidth()) / 2)) && (distanceBetweenCentersY >= ((height + e.getHeight()) / 2))) {
            minDistance = distanceBetweenCentersY - ((height + e.getHeight()) / 2);
        }

        // The two rectangles do not overlap. There are two overlapping rectangles in the Y direction.
        else if ((distanceBetweenCentersX >= ((width + e.getWidth()) / 2)) && (distanceBetweenCentersY < ((height + e.getHeight()) / 2))) {
            minDistance = distanceBetweenCentersX - ((width + e.getWidth()) / 2);
        }

        // Two rectangles do not intersect, two rectangles do not intersect in the X and Y directions
        else if ((distanceBetweenCentersX >= ((width + e.getWidth()) / 2)) && (distanceBetweenCentersY >= ((height + e.getHeight()) / 2))) {
            double deltaX = distanceBetweenCentersX - ((width + e.getWidth()) / 2);
            double deltaY = distanceBetweenCentersY - ((height + e.getHeight()) / 2);
            minDistance = sqrt(deltaX * deltaX + deltaY * deltaY);
        }

        // Intersection of two rectangles
        else {
            minDistance = -1;
        }

        return minDistance;
    }

    public static void moveToTarget(Entity currentEntity, Entity target) {
        if (target == null) {
            return;
        }

        int targetX = target.getCoordinateX();
        int targetY = target.getCoordinateY();

        if (targetX > currentEntity.getCoordinateX()) {
            move(currentEntity, Direction.RIGHT);
            return;
        } else if (targetX < currentEntity.getCoordinateX()) {
            move(currentEntity, Direction.LEFT);
            return;
        }
        if (targetY > currentEntity.getCoordinateY()) {
            MovableService.move(currentEntity, Direction.DOWN);
        } else if (targetY < currentEntity.getCoordinateY()) {
            MovableService.move(currentEntity, Direction.UP);
        }
    }
}
