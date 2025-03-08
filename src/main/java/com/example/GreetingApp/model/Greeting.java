package com.example.GreetingApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Greeting {

    String message;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public Greeting(){
    }

    public Greeting(String message) {
        this.message = message;
        id = null;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}