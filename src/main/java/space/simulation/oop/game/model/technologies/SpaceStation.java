package space.simulation.oop.game.model.technologies;

import space.simulation.oop.game.model.IAvailableForLanding;
import space.simulation.oop.game.model.IPurchased;
import space.simulation.oop.game.model.resources.*;

import java.util.HashMap;
import java.util.Map;

public class SpaceStation extends EntityWithInventory implements IAvailableForLanding {
    public Map<Class<IPurchased>, Integer> salesMarket = new HashMap<>();
    public Map<Class<IFossil>, Integer> buyingMarket = new HashMap<>();

    public SpaceStation() {
        entityType = SpaceStation.class;
        Oil oil = new Oil();
        NuclearFuel nuclearFuel = new NuclearFuel();
        Iron iron = new Iron();
        salesMarket.put(oil.getEntityType(), 10);
        salesMarket.put(iron.getEntityType(), 30);
        salesMarket.put(nuclearFuel.getEntityType(), 50);
    }

    public Integer getObjectPrice(Class<IPurchased> object) {
        return salesMarket.get(object);
    }

    public Integer buyObject(Class<IFossil> resource) {
        return 1;
    }

    public boolean tryToSaleObject(Class<IPurchased> object) {
        return false;
    }

}
