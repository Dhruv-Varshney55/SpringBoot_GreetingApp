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
}