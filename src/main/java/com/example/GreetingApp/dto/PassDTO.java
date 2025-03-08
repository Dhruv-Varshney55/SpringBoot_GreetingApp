package com.example.GreetingApp.dto;

public class PassDTO {
    private String password;

    public PassDTO() {}

    public PassDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}