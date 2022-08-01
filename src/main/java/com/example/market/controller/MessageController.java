package com.example.market.controller;


import com.example.market.service.chatRoom.ChatRoomService;
import com.example.market.service.member.MemberService;
import com.example.market.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

@Controller
@ServerEndpoint(value = "/chat") //WEB 소켓으로 접속 가능한 URL 정보를 명시하여 소켓 서버를 생성해줌
public class MessageController {

    private static final List<Session> session = new ArrayList<>(); //사용자 정보

    @GetMapping("/")
    public String chat(){
        return "chat/chatRoom";
    }

    @OnOpen //접속시 실행
    public void open(Session member){
        System.out.println("connected");
        session.add(member);
        System.out.println("접속중인 유저수:" + session.size());
    }
    @OnMessage //메시지를 받았을 때 실행
    public void getMessage(Session receiveSession, String sendMessage){ //메시지를 받았을 때, 세션을 가져옴
        for (int i = 0; i < session.size(); i++){ //세션에 담겨있는 모든 메시지를 전달하기 위해 size만큼 반복
            if (!receiveSession.getId().equals(session.get(i).getId())) {
                try {
                    session.get(i).getBasicRemote().sendText("상대방 : "+sendMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    session.get(i).getBasicRemote().sendText("나 : "+sendMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
/*
    @MessageMapping("/chanMarket/chat/{roomId}") // 메세지가 목적지로 전송되면 chat()메서드를 호출
    @SendTo("/chanMarket/chat/{roomId}") // 결과를 리턴시키는 목적지
    public Message postChat(@DestinationVariable Long roomId, Message message, HttpServletRequest request) throws Exception {
        if (chatRoomService.findRoom(roomId).getMember() == memberService.findByEmail((String) request.getSession().getAttribute("loginMember"))) {
            message.setChatRoom(chatRoomService.findRoom(roomId));
            message.setCaller(chatRoomService.findRoom(roomId).getMember());
            message.setReceiver(chatRoomService.findRoom(roomId).getOwner());
            message.setNowTime(LocalDateTime.now());
            messageService.messageSave(message);
            return message;
        } else if (chatRoomService.findRoom(roomId).getOwner() == memberService.findByEmail((String) request.getSession().getAttribute("loginMember"))) {
            message.setChatRoom(chatRoomService.findRoom(roomId));
            message.setCaller(chatRoomService.findRoom(roomId).getOwner());
            message.setReceiver(chatRoomService.findRoom(roomId).getMember());
            message.setNowTime(LocalDateTime.now());
            messageService.messageSave(message);
            return message;
        } else
            return message;

    }
*/

}
