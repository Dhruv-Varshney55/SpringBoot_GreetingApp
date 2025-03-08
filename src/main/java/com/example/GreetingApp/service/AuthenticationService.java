package com.example.GreetingApp.service;
import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.dto.LoginDTO;
import com.example.GreetingApp.dto.PassDTO;
import com.example.GreetingApp.interfaces.AuthInterface;
import com.example.GreetingApp.model.AuthUser;
import com.example.GreetingApp.repository.AuthUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements AuthInterface {

    AuthUserRepository userRepository;
    EmailService emailService;
    JwtTokenService jwtTokenService;

    public AuthenticationService(AuthUserRepository userRepository, EmailService emailService, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.jwtTokenService = jwtTokenService;
    }

    public String register(AuthUserDTO user){

        List<AuthUser> l1 = userRepository.findAll().stream().filter(authuser -> user.getEmail().equals(authuser.getEmail())).collect(Collectors.toList());

        if(l1.size()>0){
            return "User already registered";
        }

        //creating hashed password using bcrypt
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String hashPass = bcrypt.encode(user.getPassword());

        //creating new user
        AuthUser newUser = new AuthUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), hashPass);

        //setting the new hashed password
        newUser.setHashPass(hashPass);

        //saving the user in the database
        userRepository.save(newUser);

        //sending the confirmation mail to the user
        emailService.sendEmail(user.getEmail(), "Regitration Status", user.getFirstName()+" You are registered!");

        return "User registered";
    }


    public String login(LoginDTO user){

        List<AuthUser> l1 = userRepository.findAll().stream().filter(authuser -> authuser.getEmail().equals(user.getEmail())).collect(Collectors.toList());
        if(l1.size() == 0) {
            return "User not registered";
        }

        AuthUser foundUser = l1.get(0);

        //matching the stored hashed password with the password provided by user
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        if(!bcrypt.matches(user.getPassword(), foundUser.getHashPass())) {
            return "Invalid password";
        }

        //creating Jwt Token
        String token = jwtTokenService.createToken(foundUser.getId());

        //setting token for user login
        foundUser.setToken(token);

        //saving the current status of user in database
        userRepository.save(foundUser);

        return "User logged in"+"\ntoken : "+token;
    }




    // UC13 (Forgot Password)
    public AuthUserDTO forgotPassword(PassDTO pass, String email){

        AuthUser foundUser = userRepository.findByEmail(email);

        if(foundUser == null) {
            throw new RuntimeException("User not registered");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashpass = bCryptPasswordEncoder.encode(pass.getPassword());

        foundUser.setPassword(pass.getPassword());
        foundUser.setHashPass(hashpass);

        userRepository.save(foundUser);

        emailService.sendEmail(email, "Status", "Your password has been reset");

        AuthUserDTO authDTO = new AuthUserDTO(foundUser.getFirstName(), foundUser.getLastName(), foundUser.getEmail(), foundUser.getPassword(), foundUser.getId() );

        return authDTO;
    }
}