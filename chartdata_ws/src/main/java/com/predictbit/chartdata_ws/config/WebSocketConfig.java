package com.predictbit.chartdata_ws.config;

import com.predictbit.chartdata_ws.service.ChartDataWebSocketHandler;
import com.predictbit.chartdata_ws.service.ChartUpdateWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket // WebSocket을 사용하기 위한 어노테이션
public class WebSocketConfig implements WebSocketConfigurer {

    // 객체 저장할 변수
    private ChartDataWebSocketHandler chartDataWebSocketHandler;
    private ChartUpdateWebSocketHandler chartUpdateWebSocketHandler;

    @Autowired // 생성자를 통해 의존성 주입
    public WebSocketConfig(
            ChartDataWebSocketHandler chartDataWebSocketHandler,
            ChartUpdateWebSocketHandler chartUpdateWebSocketHandler
    ) {
        this.chartDataWebSocketHandler = chartDataWebSocketHandler;
        this.chartUpdateWebSocketHandler = chartUpdateWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 엔드포인트 등록하고 모든 도메인에서의 연결 허용
        registry.addHandler(chartDataWebSocketHandler, "ws/chartdata").setAllowedOriginPatterns("*");
        registry.addHandler(chartUpdateWebSocketHandler, "ws/chartupdate").setAllowedOriginPatterns("*");
    }
}
