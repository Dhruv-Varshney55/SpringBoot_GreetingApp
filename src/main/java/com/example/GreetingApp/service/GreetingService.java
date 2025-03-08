package com.example.GreetingApp.service;

import com.example.GreetingApp.dto.MessageDTO;
import com.example.GreetingApp.interfaces.GreetingInterface;
import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.repository.GreetingRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GreetingService implements GreetingInterface {
    String message;
    GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
        message = "Hello World!";
    }

    public String getGreetings(){
        return this.message;
    }

    // UC4
    public MessageDTO saveGreeting(MessageDTO message){
        Greeting gr = new Greeting(message.getMessage());
        greetingRepository.save(gr);
        MessageDTO dto = new MessageDTO(gr.getMessage());
        dto.setId(gr.getId());
        return dto;
    }

    // UC5
    public List<MessageDTO> getAllGreetings(){
        List<MessageDTO> list = greetingRepository.findAll().stream().map(entity -> {
            MessageDTO md = new MessageDTO(entity.getMessage());
            md.setId(entity.getId());
            return md;
        }).collect(Collectors.toList());
        return list;
    }

    // UC6
    public MessageDTO getGreetingById(Long id){
        Greeting gr = greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("No messages found with given id"));
        MessageDTO md = new MessageDTO(gr.getMessage());
        md.setId(gr.getId());
        return md;
    }

    // UC7
    public MessageDTO updateGreeting(MessageDTO message, Long id){
        Greeting gr = greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("No messages found with given id"));
        gr.setMessage(message.getMessage());
        greetingRepository.save(gr);
        MessageDTO m2 = new MessageDTO(gr.getMessage());
        m2.setId(gr.getId());
        return m2;
    }

    // UC8
    public String deleteGreeting(Long id){
        Greeting gr = greetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find message with given id"));
        greetingRepository.delete(gr);
        return "Message Deleted";
    }
}