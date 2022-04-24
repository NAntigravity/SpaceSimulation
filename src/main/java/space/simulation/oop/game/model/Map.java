package space.simulation.oop.game.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Vector;

public class Map implements Serializable {
    @Getter
    @Setter
    private Integer height;
    @Getter
    @Setter
    private Integer width;
    @Getter
    @Setter
    private Vector<Vector<Tile>> tiles;

    public Map(int x, int y) {
        height = y;
        width = x;
        generateMap();
    }

    private void generateMap() {
        tiles = new Vector<>();
        for (int i = 0; i < width; i++) {
            Vector<Tile> tileVector = new Vector<>();
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile();
                tile.setTileType(Tile.class);
                tileVector.add(tile);
            }
            tiles.add(tileVector);
        }
    }
}
