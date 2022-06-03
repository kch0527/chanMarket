package com.example.market.service;

import com.example.market.entity.Board;
import com.example.market.entity.ChatRoom;
import com.example.market.entity.Member;

public interface ChatRoomService {
    public void createChatRoom(ChatRoom chatRoom, Board board, Member member);
}
