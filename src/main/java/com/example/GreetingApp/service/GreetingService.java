package com.example.GreetingApp.service;

import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    // UC5
    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Greeting not found with id: " + id));
    }
}