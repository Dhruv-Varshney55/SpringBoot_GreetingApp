package com.example.GreetingApp.interfaces;

import com.example.GreetingApp.dto.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GreetingInterface {
    public String getGreetings();
    public MessageDTO saveGreeting(MessageDTO message);
    public MessageDTO getGreetingById(Long id);
    public List<MessageDTO> getAllGreetings();
    public MessageDTO updateGreeting(MessageDTO message, Long id);
    public String deleteGreeting(Long id);
}