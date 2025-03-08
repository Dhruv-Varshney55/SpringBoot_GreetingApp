package com.example.GreetingApp.dto;

public class MessageDTO {
    private String message;
    private Long id;

    public MessageDTO() {}

    public MessageDTO(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public MessageDTO(String message) {
        this.message = message;
        this.id = null;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }
}