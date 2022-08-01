package com.example.market.service.chatRoom;

import com.example.market.entity.Board;
import com.example.market.entity.ChatRoom;
import com.example.market.entity.member.Member;
import com.example.market.repository.ChatRoomRepository;
import com.example.market.service.chatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public void createChatRoom(ChatRoom chatRoom, Board board, Member member){
        ChatRoom room = ChatRoom.builder()
                .board(board)
                .owner(board.getMember())
                .member(member)
                .build();
        chatRoomRepository.save(room);
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
