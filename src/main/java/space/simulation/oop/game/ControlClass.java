package space.simulation.oop.game;

import space.simulation.oop.game.model.Map;

public class ControlClass {
    private final Map gameField;
    private static ControlClass INSTANCE;

    private ControlClass() {
        gameField = new Map(40, 40);
    }

    public static ControlClass getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ControlClass();
        }
        return INSTANCE;
    }

    public Map getMap() {
        return gameField;
    }
}
