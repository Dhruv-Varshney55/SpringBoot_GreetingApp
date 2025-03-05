package com.example.GreetingApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginDTO {

    @Email(message = "User email incorrect")
    @NotBlank(message = "Email required")
    String email;

    @NotBlank(message = "Password required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,}$", message = "Invalid password")
    String password;

    LoginDTO(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}