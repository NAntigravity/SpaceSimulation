package space.simulation.oop.dto;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.map.Map;

import java.util.ArrayList;

public class GameObjectDTO {
    public Map map;
    public ArrayList<Entity> entities;
    public GameObjectDTO(Map map, ArrayList<Entity> entities) {
        this.map = map;
        this.entities = entities;
    }
}
