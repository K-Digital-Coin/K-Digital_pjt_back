package com.predictbit.websocket;

import com.google.gson.Gson;
import com.predictbit.domain.HistoryCoin;
import com.predictbit.service.HistoryCoinService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryCoinWebSocketHandler implements WebSocketHandler {
    private int currentIndex = 0;
    private final List<HistoryCoin> historyCoins; // HistoryCoin 객체 리스트
    private final List<WebSocketSession> sessions = new ArrayList<>(); // WebSocket 세션 리스트

    private final HistoryCoinService historyCoinService;

    public HistoryCoinWebSocketHandler(HistoryCoinService historyCoinService) {
        this.historyCoinService = historyCoinService;
        historyCoins = getHistoryCoins(); // DB에서 HistoryCoin 데이터를 가져와서 리스트로 저장
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트와 연결되었을 때 실행됩니다.
        sessions.add(session); // 새로 연결된 세션을 리스트에 추가합니다.
        sendMessage(session, historyCoins.get(currentIndex)); // 현재 인덱스의 HistoryCoin 데이터를 전송합니다.
        currentIndex++; // 다음 데이터를 전송하기 위해 인덱스를 증가시킵니다.
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 클라이언트와 연결이 끊어졌을 때 실행됩니다.
        sessions.remove(session); // 세션 리스트에서 제거합니다.
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void sendMessage(WebSocketSession session, HistoryCoin historyCoin) throws IOException {
        session.sendMessage(new TextMessage(new Gson().toJson(historyCoin))); // JSON 형태로 데이터를 전송합니다.
    }

    @Scheduled(fixedRate = 10000)
    public void sendNextHistoryCoin() throws IOException {
        if (currentIndex > 20000) {
            currentIndex = 0; // 인덱스가 20000보다 커지면 0으로 초기화합니다.
        }
        for (WebSocketSession s : sessions) { // 연결된 모든 클라이언트에게 데이터를 전송합니다.
            sendMessage(s, historyCoins.get(currentIndex));
        }
        currentIndex++; // 다음 데이터를 전송하기 위해 인덱스를 증가시킵니다.
    }

    private List<HistoryCoin> getHistoryCoins() {
        // DB에서 HistoryCoin 데이터를 가져와서 리스트로 반환합니다.
        return null;
    }

    public WebSocketSession[] getSessions() {
    }
}