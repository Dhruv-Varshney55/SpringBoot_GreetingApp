package com.example.GreetingApp.service;

import com.example.GreetingApp.model.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public Greeting getGreeting() {
        return new Greeting("Hello World");
    }
}