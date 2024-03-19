package exercise.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
import exercise.daytime.Daytime;

@RestController
public class WelcomeController {

    private final Daytime daytime;

    public WelcomeController(Daytime daytime) {
        this.daytime = daytime;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return String.format("It is %s now! Welcome to Spring!", daytime.getName());
    }
}
// END
