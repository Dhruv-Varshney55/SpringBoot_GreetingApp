package com.example.GreetingApp.controller;

import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.dto.LoginDTO;
import com.example.GreetingApp.dto.MailDTO;
import com.example.GreetingApp.dto.PassDTO;
import com.example.GreetingApp.interfaces.AuthInterface;
import com.example.GreetingApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthUserController {
    @Autowired
    EmailService emailService;

    @Autowired
    AuthInterface authInterface;

    // UC9 (Registration)
    @PostMapping(path = "/register")
    public String register(@RequestBody AuthUserDTO user){
        return authInterface.register(user);
    }

    // UC10 (Login)
    @PostMapping(path ="/login")
    public String login(@RequestBody LoginDTO user){
        return authInterface.login(user);
    }

    // UC11 (Email sender)
    @PostMapping(path="/sendMail")
    public String sendMail(@RequestBody MailDTO user){
        emailService.sendEmail(user.getTo(), user.getSubject(), user.getBody());
        return "Mail Sent";
    }

    // UC13 (Forgot password)
    @PutMapping("/forgotPassword/{email}")
    public AuthUserDTO forgotPassword(@RequestBody PassDTO pass, @PathVariable String email){
        return authInterface.forgotPassword(pass, email);
    }

    // UC14 (Reset password)
    @PutMapping("/resetPassword/{email}")
    public String resetPassword(@PathVariable String email, @RequestBody Map<String, String> requestBody) {
        String currentPass = requestBody.get("currentPass");
        String newPass = requestBody.get("newPass");
        return authInterface.resetPassword(email, currentPass, newPass);
    }
}