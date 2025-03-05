package com.example.GreetingApp.controller;

import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.dto.LoginDTO;
import com.example.GreetingApp.service.AuthenticationService;
import com.example.GreetingApp.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthUserController {
    EmailService emailService;
    AuthenticationService authenticationService;

    public AuthUserController(EmailService emailService, AuthenticationService authenticationService) {
        this.emailService = emailService;
        this.authenticationService = authenticationService;
    }

    // UC9 (Registration)
    @PostMapping(path = "/register")
    public String register(@RequestBody AuthUserDTO user){
        return authenticationService.register(user);
    }

    // UC10 (Login)
    @PostMapping(path ="/login")
    public String login(@RequestBody LoginDTO user){
        return authenticationService.login(user);
    }
}