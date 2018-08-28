package cuex.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class GreetingController {

    @GetMapping
    public List<String> greetings() {
        ArrayList<String> greetings = new ArrayList<>();
        greetings.add("Bonjour!");
        greetings.add("Hello!");
        greetings.add("Dzień dobry!");
        return greetings;
    }
}