package com.example.market.service.message;

import com.example.market.entity.Message;
import com.example.market.repository.MessageRepository;
import com.example.market.service.chatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomService chatRoomService;

    public List<Message> messageList(Long roomId){
        return messageRepository.findRoomMessage(chatRoomService.findRoom(roomId).getId());
    }

    @Transactional
    public void messageSave(Message message){
        messageRepository.save(message);
    }
}
