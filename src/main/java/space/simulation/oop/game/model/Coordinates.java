package space.simulation.oop.game.model;

import lombok.Getter;
import lombok.Setter;

public class Coordinates {
    @Getter
    @Setter
    private Integer x;

    @Getter
    @Setter
    private Integer y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
