package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.Entity;
import space.simulation.oop.game.model.IAvailableForLanding;
import space.simulation.oop.game.model.IPurchased;
import space.simulation.oop.game.model.resources.*;

import java.util.HashMap;
import java.util.Map;

import static space.simulation.oop.game.configs.SpaceSimulationConstants.*;

public class SpaceStation extends Entity implements IAvailableForLanding {
    public Map<Class<IPurchased>, Integer> salesMarket = new HashMap<>();
    public Map<Class<IFossil>, Integer> buyingMarket = new HashMap<>();

    public SpaceStation() {
        entityType = SpaceStation.class;

        Oil oil = new Oil();
        NuclearFuel nuclearFuel = new NuclearFuel();
        Iron iron = new Iron();
        Kryptonite kryptonite = new Kryptonite();
        Vespen vespen = new Vespen();

        salesMarket.put(oil.getEntityType(), 10);
        salesMarket.put(iron.getEntityType(), 30);
        salesMarket.put(nuclearFuel.getEntityType(), 50);

        buyingMarket.put(oil.getEntityType(), (int) (Math.random() * (MAX_RESOURCE_PRICE - MIN_RESOURCE_PRICE + 1) + MIN_RESOURCE_PRICE));
        buyingMarket.put(iron.getEntityType(), (int) (Math.random() * (MAX_RESOURCE_PRICE - MIN_RESOURCE_PRICE + 1) + MIN_RESOURCE_PRICE));
        buyingMarket.put(kryptonite.getEntityType(), (int) (Math.random() * (MAX_RESOURCE_PRICE - MIN_RESOURCE_PRICE + 1) + MIN_RESOURCE_PRICE));
        buyingMarket.put(vespen.getEntityType(), (int) (Math.random() * (MAX_RESOURCE_PRICE - MIN_RESOURCE_PRICE + 1) + MIN_RESOURCE_PRICE));
    }

    public Integer getObjectPrice(Class<IPurchased> object) {
        return salesMarket.get(object);
    }

    public Integer getObjectPriceForSell(Class<IFossil> object) {
        return buyingMarket.get(object);
    }

}
