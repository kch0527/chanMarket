package com.example.market.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocketMessageBroker //웹소켓 서버 사용 설정
public class SocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS(); //양 사용자 간 웹소켓 핸드 셰이크를 위해 지정
    }
}
