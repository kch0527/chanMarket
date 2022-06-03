package com.example.market.service;

import com.example.market.entity.Board;
import com.example.market.entity.ChatRoom;
import com.example.market.entity.Member;
import com.example.market.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{

    private final ChatRoomRepository chatRoomRepository;

    public void createChatRoom(ChatRoom chatRoom, Board board, Member member){
        chatRoom.setBoard(board);
        chatRoom.setOwner(board.getMember());
        chatRoom.setMember(member);
        chatRoomRepository.save(chatRoom);
    }
}
