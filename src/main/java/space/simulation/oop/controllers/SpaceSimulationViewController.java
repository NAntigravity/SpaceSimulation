package space.simulation.oop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaceSimulationViewController {
    @GetMapping("/")
    public String getMap() {
        return "map";
    }
}
