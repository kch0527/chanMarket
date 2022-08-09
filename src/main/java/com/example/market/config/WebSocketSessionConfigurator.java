package com.example.market.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import static javax.websocket.server.ServerEndpointConfig.*;

public class WebSocketSessionConfigurator extends Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response)
    {
        HttpSession session = (HttpSession) request.getHttpSession();
        if (session != null)
        {
            sec.getUserProperties().put("loginMember", session);
        }
    }
}
