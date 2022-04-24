package space.simulation.oop.game.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tile implements Serializable {
    protected Set<Class> tileType;
    public Tile() {
        super();
        tileType = new HashSet<>(List.of(Tile.class));
    }

    public Set<Class> getTileType() {
        return tileType;
    }

    public void setTileType(Class tileType) {
        this.tileType = Collections.singleton(tileType);
    }
}
