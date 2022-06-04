package com.example.market.service;

import com.example.market.entity.Message;
import com.example.market.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    public List<Message> messages(){
        return messageRepository.findAll();
    }
}
