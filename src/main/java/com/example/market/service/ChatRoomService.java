package com.example.market.service;

import com.example.market.entity.Board;
import com.example.market.entity.ChatRoom;
import com.example.market.entity.Member;

import java.util.List;

public interface ChatRoomService {
    public void createChatRoom(ChatRoom chatRoom, Board board, Member member);
    ChatRoom findRoom(Long RoomId);
    List<ChatRoom> findMyRoom(Long memberId);
    List<ChatRoom> findChatRoomAll();
    boolean chatRoomDeduplication(Long ownerId, Long memberId);
    void delChatRoom(Long roomId);
}
