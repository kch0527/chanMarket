package com.example.market.service.message;

import com.example.market.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> messageList(Long roomId);
    void messageSave(Message message);


}
