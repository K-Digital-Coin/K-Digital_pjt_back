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

    // WebSocketSession을 담아둘 리스트 생성
    private static List<WebSocketSession> list = new ArrayList<>();
    // 데이터 범위를 지정할 startIdx와 endIdx 초기값 설정
    private int startIdx = 19851;
    private int endIdx = 19952;

    @Scheduled(fixedDelay = 1000) // fixedDelay를 이용해 일정 시간마다 스케줄링
    public void scheduledProcessing() {
        // list에 WebSocketSession이 없으면 작업 중지
        if (list.size() == 0) return;
        try {
            // ChartDataService의 getHistoryCoinByIdxBetween 메서드를 호출해 startIdx와 endIdx 범위의 데이터를 가져옴
            List<HistoryCoin> historyCoins = chartDataService.getHistoryCoinByIdxBetween(startIdx, endIdx);
            // 가져온 데이터를 문자열로 변환
            String msg = historyCoins.toString();
            // 문자열을 WebSocketSession에 전달하기 위해 TextMessage 객체 생성
            TextMessage message = new TextMessage(msg.getBytes());

            // WebSocketSession 리스트를 순회하며 메시지 전송
            for (WebSocketSession sess : list) {
                try {
                    sess.sendMessage(message);
                } catch (IOException e) {
                    log.error("Failed to send message to WebSocketSession: " + sess, e);
                    sess.close();
                }
            }
            // startIdx와 endIdx 범위 업데이트
            endIdx = startIdx-1;
            if (startIdx < 0) {
                startIdx = 0;
            } else if (startIdx == 0) {
                endIdx = 0;
            } else {
                startIdx = startIdx-100;
            }
        } catch (RuntimeException | IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override // 클라이언트에서 메시지를 보냈을 때 호출됨
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // WebSocketSession 리스트를 순회하며 메시지 전송
        for(WebSocketSession sess: list) {
            sess.sendMessage(message);
        }
    }

    @Override // WebSocketSession이 열리면 호출됨
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // WebSocketSession 리스트에 추가
        list.add(session);
        System.out.println(session + " 클라이언트 접속");
    }

    @Override // WebSocketSession이 닫히면 호출됨
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println(session + " 클라이언트 접속 해제");
        list.remove(session);
    }
}
