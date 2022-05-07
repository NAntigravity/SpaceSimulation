package space.simulation.oop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.simulation.oop.game.ControlClass;
import space.simulation.oop.game.SpaceSimulationConfiguration;

@RestController
public class SpaceSimulationRestController {
    AnnotationConfigApplicationContext context;
    ControlClass game;

    public SpaceSimulationRestController() {
        context = new AnnotationConfigApplicationContext(SpaceSimulationConfiguration.class);
        game = context.getBean(ControlClass.class);
    }

    private String Info() throws JsonProcessingException {
        ObjectMapper serialization = new ObjectMapper();
        var map = game.getGameField();
        var entities = game.getEntities();
        return serialization.writeValueAsString(new GameObjectDTO(map, entities));
    }

    @GetMapping("/space")
    public ResponseEntity<String> gettingInformationAboutWorld() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(Info());
    }
}
