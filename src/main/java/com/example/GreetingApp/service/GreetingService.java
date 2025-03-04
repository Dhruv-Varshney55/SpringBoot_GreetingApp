package com.example.GreetingApp.service;

import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {
    @Autowired
    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // UC4
    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    // UC6 (Storing)
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // UC5 (Find)
    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Greeting not found with id: " + id));
    }

    // UC7
    public Greeting updateGreeting(Long id, String newMessage) {
        Optional<Greeting> existingGreeting = greetingRepository.findById(id);
        if (existingGreeting.isPresent()) {
            Greeting greeting = existingGreeting.get();
            greeting.setMessage(newMessage);
            return greetingRepository.save(greeting);
        }
        throw new RuntimeException("Greeting with ID " + id + " not found");
    }

    // UC8
    public void deleteGreeting(Long id) {
        if (!greetingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Greeting not found!");
        }
        greetingRepository.deleteById(id);
    }
}