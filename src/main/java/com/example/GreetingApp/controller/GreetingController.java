package com.example.GreetingApp.controller;
import com.example.GreetingApp.dto.MessageDTO;
import com.example.GreetingApp.interfaces.GreetingInterface;
import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    GreetingInterface greetingInterface;

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

    // UC4 (Save)
    @PostMapping("/save")
    public MessageDTO saveGreeting(@RequestBody MessageDTO message){
        return greetingInterface.saveGreeting(message);
    }

    // UC5 (Get)
    @GetMapping("/{id}")
    public MessageDTO getGreetingById(@PathVariable Long id){
        return greetingInterface.getGreetingById(id);
    }

    //UC6 (Show)
    @GetMapping("/greetings")
    public List<MessageDTO> getAllGreetings(){
        return greetingInterface.getAllGreetings();
    }

    // UC7 (Update)
    @PutMapping("/{id}")
    public MessageDTO updateGreeting(@RequestBody MessageDTO message, @PathVariable Long id){
        return greetingInterface.updateGreeting(message, id);
    }

    // UC8 (Delete)
    @DeleteMapping("/{id}")
    public String deleteGreeting(@PathVariable Long id){
        return greetingInterface.deleteGreeting(id);
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