package space.simulation.oop.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpaceSimulationConfiguration {
    @Bean
    @Scope("singleton")
    public ControlClass getControlClass() {
        return ControlClass.getInstance();
    }
}
