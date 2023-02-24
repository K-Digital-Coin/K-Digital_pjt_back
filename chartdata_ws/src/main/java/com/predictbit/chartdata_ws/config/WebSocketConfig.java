package com.predictbit.chartdata_ws.config;

import com.predictbit.chartdata_ws.service.ChartDataWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
@EnableWebSocketMessageBroker
// WebSocket 구성에 대한 추가 구현
public class WebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {

    private final ChartDataWebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/chartdata-ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    // WebSocket 메시징 구성
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 모든 클라이언트에게 메시지를 브로드캐스트
        config.enableSimpleBroker("/topic");
        // 클라이언트에서 메시지 보낼 때 사용되는 애플리케이션 목적지 접두사
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    // Stomp 프로토콜을 사용하여 WebSocket 연결 구성
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 엔드포인트 등록
        registry.addEndpoint("/chartdata-ws")
                // 모든 도메인에서 접근 가능하도록 설정
                .setAllowedOriginPatterns("*")
                // SockJS 지원 활성화 - fallback 옵션 제공
                .withSockJS();
    }
}