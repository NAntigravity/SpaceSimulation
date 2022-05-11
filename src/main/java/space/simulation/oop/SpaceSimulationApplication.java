package space.simulation.oop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import space.simulation.oop.game.ControlClass;
import space.simulation.oop.game.configs.SpaceSimulationConfiguration;

@SpringBootApplication
public class SpaceSimulationApplication {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(SpaceSimulationConfiguration.class);
        var game = context.getBean(ControlClass.class);
        SpringApplication.run(SpaceSimulationApplication.class, args);
        while (true) {
            game.liveOneTick();
            try {
                Thread.sleep(980);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
