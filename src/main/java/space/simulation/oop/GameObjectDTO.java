package space.simulation.oop;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.Map;

import java.util.Vector;

public class GameObjectDTO {
    public Map map;
    public Vector<Entity> entities;
    public GameObjectDTO(Map map, Vector<Entity> entities) {
        this.map = map;
        this.entities = entities;
    }
}
