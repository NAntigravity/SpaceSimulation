package space.simulation.oop.game.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public abstract class Entity {
    @Getter
    @Setter
    protected Integer height;

    @Getter
    @Setter
    protected Integer width;

    @Getter
    @Setter
    protected Integer coordinateX;

    @Getter
    @Setter
    protected Integer coordinateY;

    @Getter
    @Setter
    protected Class entityType;

    @Getter
    @Setter
    protected boolean isDead = false;

    public Coordinates getCoordinates() {
        return new Coordinates(getCoordinateX(), getCoordinateY());
    }
    public void setCoordinates(@NotNull Coordinates newCoordinates) {
        this.setCoordinateX(newCoordinates.getX());
        this.setCoordinateY(newCoordinates.getY());
    }

    public void existOneTick() {
        if (this instanceof IMovable) {
            ((IMovable) this).move();
        }
    }
}
