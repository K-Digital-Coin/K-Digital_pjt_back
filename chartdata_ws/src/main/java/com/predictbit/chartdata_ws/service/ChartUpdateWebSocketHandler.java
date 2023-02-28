package com.predictbit.chartdata_ws.service;

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

@Component
public class ChartUpdateWebSocketHandler extends TextWebSocketHandler {

    private final ChartUpdateService chartUpdateService;

    @Autowired
    public ChartUpdateWebSocketHandler(ChartUpdateService chartUpdateService) {
        this.chartUpdateService = chartUpdateService;
    }

    // 세션 리스트
    private static List<WebSocketSession> list = new ArrayList<>();

    // idx, currentIdx 초기값 설정
    private int currentIdx = 19952;

    @Scheduled(fixedDelay = 10000)
    // 10초마다 currentIdx를 증가시키면서 데이터를 보내줍니다.
    public void scheduledProcessing() throws IOException {
        String msg;

        // 클라이언트가 없으면 return
        if (list.size() == 0) return;

        if (currentIdx < 20000 & currentIdx > 0) {
            currentIdx++;
            msg = chartUpdateService.getHistoryCoinByIdx(currentIdx).toString();
        } else {
            // 데이터가 끝까지 갔으면 currentIdx를 초기화
            currentIdx = 0;
            msg = "";
        }

        System.out.println("msg : " + msg);

        TextMessage message = new TextMessage(msg.getBytes());

        // 세션 리스트에 있는 모든 클라이언트에게 메시지 전송
        for (WebSocketSession sess : list) {
            sess.sendMessage(message);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for(WebSocketSession sess: list) {
            sess.sendMessage(message);
        }
    }

    @Override    // Client 접속 시 호출
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 세션 리스트에 세션 추가
        list.add(session);
        System.out.println(session + " 클라이언트 접속");
    }

    @Override    // Client 접속 해제 시 호출
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션 리스트에서 세션 삭제
        System.out.println(session + " 클라이언트 접속 해제");
        list.remove(session);
    }
}
