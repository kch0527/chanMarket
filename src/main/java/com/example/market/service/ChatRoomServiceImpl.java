package com.example.market.service;

import com.example.market.entity.Board;
import com.example.market.entity.ChatRoom;
import com.example.market.entity.Member;
import com.example.market.repository.ChatRoomRepository;
import com.example.market.repository.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

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

    public boolean chatRoomDeduplication(Long boardId, Long memberId){
        Iterator<ChatRoom> iter = findChatRoomAll().iterator();
        while (iter.hasNext()){
            ChatRoom chatRoom = iter.next();
            if (chatRoom.getBoard().getId() == boardId && chatRoom.getMember().getId() == memberId){
                return false;
            }
        }
        return true;
    }

    public ChatRoom findRoom(Long RoomId){
        return chatRoomRepository.getById(RoomId);
    }

    public List<ChatRoom> findChatRoomAll(){
        return chatRoomRepository.findAll();
    }

    public List<ChatRoom> findMyRoom(Long memberId){
       return chatRoomRepository.findMyChatRooms(memberId);
    }

    public void delChatRoom(Long roomId){
        chatRoomRepository.deleteById(roomId);
    }

}
