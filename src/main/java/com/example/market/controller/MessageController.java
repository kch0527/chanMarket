package com.example.market.controller;


import org.springframework.stereotype.Controller;

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

    @OnOpen //접속시 실행
    public void open(Session member){
        System.out.println("connected");
        session.add(member);
        System.out.println("접속중인 유저수:" + session.size());
    }

    @OnMessage //메시지를 받았을 때 실행
    public void getMessage(Session receiveSession, String message){ //메시지를 받았을 때, 세션을 가져옴
        for (int i = 0; i < session.size(); i++){ //세션에 담겨있는 모든 메시지를 전달하기 위해 size만큼 반복
            if (!receiveSession.getId().equals(session.get(i).getId())) {
                try {
                    session.get(i).getBasicRemote().sendText("상대방 : "+message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    session.get(i).getBasicRemote().sendText("나 : "+message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClose
    public void close(Session session) {

    }

}
