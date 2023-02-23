package com.predictbit.chartdata;

import com.predictbit.chartdata.handler.HistoryCoinWebSocketHandler;
import com.predictbit.chartdata.service.HistoryCoinService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
@EnableWebSocket
@EnableWebSocketMessageBroker
@EnableScheduling
public class ChartdataApplication implements WebSocketConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ChartdataApplication.class, args);
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new HistoryCoinWebSocketHandler(
                new HistoryCoinService()), "/websocket");
    }
}
