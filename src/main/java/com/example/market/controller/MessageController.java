package com.example.market.controller;


import com.example.market.config.WebSocketSessionConfigurator;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;


import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@Log
@Controller
@ServerEndpoint(value = "/chat", configurator = WebSocketSessionConfigurator.class)
//WEB 소켓으로 접속 가능한 URL 정보를 명시하여 소켓 서버를 생성해줌
public class MessageController {
    private static final Map<Session, HttpSession> map = new HashMap<>(); //로그인세션 정보

    @OnOpen //접속시 실행
    public void open(Session session, EndpointConfig config) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("loginMember");
        System.out.println("connected");
        map.put(session, httpSession);
        System.out.println("접속중인 유저수:" + map.size());
        System.out.println("접속중한 유저:" + (String) httpSession.getAttribute("loginMember"));
    }

    @OnMessage //메시지를 받았을 때 실행
    public void getMessage(Session session, String sendMessage) { //메시지를 받았을 때, 세션을 가져옴
        HttpSession httpSession = map.get(session);
        List<Session> sessionList = new ArrayList(map.keySet());
        for (int i = 0; i < sessionList.size(); i++) { //세션에 담겨있는 모든 메시지를 전달하기 위해 size만큼 반복
            try {
                sessionList.get(i).getBasicRemote().sendText((String) httpSession.getAttribute("loginMember") + " : " + sendMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @OnClose
    public void close(Session session) {
        map.remove(session);
    }

    @OnError
    public void error(Session session, Throwable throwable) {
        log.warning(throwable.getMessage());
        map.remove(session);
    }
}
