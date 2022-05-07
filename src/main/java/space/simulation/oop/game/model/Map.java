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
        generateEmptyMap();
    }

    public synchronized void updateTileOnCoordinate(Class tileType, int x, int y) {
        Vector<Vector<Tile>> newTiles = new Vector<>();
        for (int i = 0; i < width; i++) {
            Vector<Tile> tileVector = new Vector<>();
            for (int j = 0; j < height; j++) {
                if (i == x && j == y) {
                    tileVector.add(new Tile(tileType));
                } else {
                    tileVector.add(tiles.get(i).get(j));
                }
            }
            newTiles.add(tileVector);
        }
        this.tiles = newTiles;
    }

    private void generateEmptyMap() {
        tiles = new Vector<>();
        for (int i = 0; i < width; i++) {
            Vector<Tile> tileVector = new Vector<>();
            for (int j = 0; j < height; j++) {
                tileVector.add(new Tile());
            }
            tiles.add(tileVector);
        }
    }
}
