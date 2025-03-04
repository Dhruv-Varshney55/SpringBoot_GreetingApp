package com.example.GreetingApp.controller;
import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greetings")
public class GreetingController {
    @GetMapping
    public Greeting getGreetings(){
        return new Greeting("Hello , My name is Dhruv Varshney !");
    }
    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting newGreeting){
        return new Greeting("Greeting Created :"+newGreeting.getMessage());
    }
    @PutMapping
    public Greeting updateGreeting(@RequestBody Greeting updatedGreeting){
        return new Greeting("Greetings Updated :"+updatedGreeting.getMessage());
    }
    @DeleteMapping
    public Greeting deleteGreeting(){
        return new Greeting("Greeting Deleted ");
    }

    // UC2
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}