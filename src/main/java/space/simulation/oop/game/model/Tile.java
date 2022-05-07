package space.simulation.oop.game.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Tile implements Serializable {
    @Getter
    @Setter
    protected Class tileType;

    public Tile() {
        super();
        tileType = Tile.class;
    }

    public Tile(Class tileType) {
        super();
        this.tileType = tileType;
    }
}
