package com.predictbit.chartdata_ws.config;

import com.predictbit.chartdata_ws.service.ChartDataWebSocketHandler;
import com.predictbit.chartdata_ws.service.ChartUpdateWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private ChartDataWebSocketHandler chartDataWebSocketHandler;
    private ChartUpdateWebSocketHandler chartUpdateWebSocketHandler;

    @Autowired
    public WebSocketConfig(
            ChartDataWebSocketHandler chartDataWebSocketHandler,
            ChartUpdateWebSocketHandler chartUpdateWebSocketHandler
    ) {
        this.chartDataWebSocketHandler = chartDataWebSocketHandler;
        this.chartUpdateWebSocketHandler = chartUpdateWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chartDataWebSocketHandler, "ws/chartdata").setAllowedOriginPatterns("*");
        registry.addHandler(chartUpdateWebSocketHandler, "ws/chartupdate").setAllowedOriginPatterns("*");
    }
}
