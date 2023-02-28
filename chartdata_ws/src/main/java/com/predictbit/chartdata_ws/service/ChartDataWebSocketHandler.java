package com.predictbit.chartdata_ws.service;

import com.predictbit.chartdata_ws.domain.HistoryCoin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ChartDataWebSocketHandler extends TextWebSocketHandler {

    private ChartDataService chartDataService;

    @Autowired
    public ChartDataWebSocketHandler(
            ChartDataService chartDataService) {
        this.chartDataService = chartDataService;
    }

    private static List<WebSocketSession> list = new ArrayList<>();
    private int startIdx = 19851;
    private int endIdx = 19952;

    @Scheduled(fixedDelay = 100)
    // 1초마다 idx 100씩 감소시키면서 데이터를 보내줍니다.
    public void scheduledProcessing() {
        if (list.size() == 0) return;
        try {
            List<HistoryCoin> historyCoins = chartDataService.getHistoryCoinByIdxBetween(startIdx, endIdx);
            String msg = historyCoins.toString();

            TextMessage message = new TextMessage(msg.getBytes());

            for (WebSocketSession sess : list) {
                try {
                    sess.sendMessage(message);
                } catch (IOException e) {
                    log.error("Failed to send message to WebSocketSession: " + sess, e);
                    sess.close();
                }
            }
            System.out.println("startIdx: " + startIdx + " / endIdx: " + endIdx);
            endIdx = startIdx-1;
            if (startIdx < 0) {
                startIdx = 0;
            } else if (startIdx == 0) {
                startIdx = 0;
                endIdx = 0;
            } else {
                startIdx = startIdx-100;
            }
        } catch (RuntimeException | IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for(WebSocketSession sess: list) {
            sess.sendMessage(message);
        }
    }

    @Override
    // Client 접속 시 호출
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);
        System.out.println(session + " 클라이언트 접속");
    }

    @Override
    // Client 접속 해제 시 호출
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println(session + " 클라이언트 접속 해제");
        list.remove(session);
    }
}
