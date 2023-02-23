package com.predictbit.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final HistoryCoinWebSocketHandler historyCoinWebSocketHandler;

    @Autowired
    public WebSocketConfig(HistoryCoinWebSocketHandler historyCoinWebSocketHandler) {
        this.historyCoinWebSocketHandler = historyCoinWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(historyCoinWebSocketHandler, "/historyCoin"); // WebSocket 핸들러 등록
    }
}
