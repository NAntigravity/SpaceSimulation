package space.simulation.oop.game;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import space.simulation.oop.game.model.*;

import java.util.concurrent.ThreadLocalRandom;

public class MovableService {
    public static boolean isEntityMotionAvailable(Entity entity, Direction direction, Boolean isCollidable) {
        if (!(entity instanceof IMovable)) {
            return false;
        }

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpaceSimulationConfiguration.class);
        ControlClass game = context.getBean(ControlClass.class);

        Coordinates tempCoordinates = getCoordinateChanges(entity.getCoordinates(), direction);
        int tempX = tempCoordinates.getX();
        int tempY = tempCoordinates.getY();

        if (tempX >= 0 && tempX < game.getGameField().getWidth()
                && tempY >= 0 && tempY < game.getGameField().getHeight()) {
            if (!isCollidable) {
                return true;
            }
            //если на данных координатах нет не IMovable объекта
            if (game.getGameField().getTiles().get(tempX).get(tempY).getTileType() == Tile.class) {
                for (Entity e :
                        game.getEntities()) {
                    if (e.getCoordinateX() == tempX && e.getCoordinateY() == tempY) {
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
}
