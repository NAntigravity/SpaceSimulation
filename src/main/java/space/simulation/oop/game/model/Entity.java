package space.simulation.oop.game.model;

import lombok.Getter;
import lombok.Setter;

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

    public Coordinates getCoordinates() {
        return new Coordinates(getCoordinateX(), getCoordinateY());
    }
    public void setCoordinates(Coordinates newCoordinates) {
        this.setCoordinateX(newCoordinates.getX());
        this.setCoordinateY(newCoordinates.getY());
    }

    public abstract void existOneTick();
}
