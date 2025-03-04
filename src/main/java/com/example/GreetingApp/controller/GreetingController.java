package com.example.GreetingApp.controller;
import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    // UC2
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // UC-3
    @GetMapping
    public Greeting getGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        String message;

        if(firstName != null && lastName != null){
            message = "Hello, " + firstName + " " + lastName + "!";
        }
        else if(firstName != null){
            message = "Hello, " + firstName + "!";
        }
        else if(lastName != null){
            message = "Hello, Mr./Ms. " + lastName + "!";
        }
        else{
            message = "Hello, World!";
        }
        return new Greeting(message);
    }

    // UC-4
    @PostMapping("/save")
    public Greeting saveGreeting(@RequestBody Greeting greeting) {
        return greetingService.saveGreeting(greeting.getMessage());
    }

    //UC-5( Fetches the greeting message by ID)
    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }

    @PostMapping("/create")
    public Greeting createGreeting(@RequestBody Greeting newGreeting) {
        return new Greeting("Greeting Created: " + newGreeting.getMessage());
    }

    @PutMapping
    public Greeting updateGreeting(@RequestBody Greeting updatedGreeting) {
        return new Greeting("Greeting Updated: " + updatedGreeting.getMessage());
    }

    @DeleteMapping
    public Greeting deleteGreeting() {
        return new Greeting("Greeting Deleted");
    }
}