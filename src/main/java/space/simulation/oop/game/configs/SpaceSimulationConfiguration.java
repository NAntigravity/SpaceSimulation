package space.simulation.oop.game.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import space.simulation.oop.game.ControlClass;

@Configuration
public class SpaceSimulationConfiguration {
    @Bean
    @Scope("singleton")
    public ControlClass getControlClass() {
        return ControlClass.getInstance();
    }
}
