package com.predictbit.chartdata.server;

import jakarta.websocket.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HistoryCoinWebSocketServer {
    private Set<Session> sessions
            = Collections.synchronizedSet(new HashSet<Session>());
    private static final Logger log = LoggerFactory.getLogger(HistoryCoinWebSocketServer.class);

    @OnOpen  // 클라이언트 접속 시 실행
    public void onOpen(Session session) {
        // WebSocket 연결이 열리면 세션을 세션 목록에 추가합니다.
        sessions.add(session);
        log.info("WebSocket Open: ", session.getId());
    }

    @OnMessage  // 메시지를 받으면 실행
    public void onMessage(String message, Session session) throws IOException {
        // 클라이언트로부터 메시지를 받았을 때 실행되는 함수입니다.
        // 이 예제에서는 받은 메시지를 모든 클라이언트에게 전송합니다.
        log.info("Send Message: ", session.getId(), ":", message);
        synchronized (sessions) {
            for (Session s : sessions) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendText(message);
                }
            }
        }
    }

    @OnClose  // 클라이언트와의 연결이 끊기면 실행
    public void onClose(Session session) {
        // WebSocket 연결이 닫히면 세션을 세션 목록에서 제거합니다.
        sessions.remove(session);
        log.info("WebSocket Close: ", session.getId());
    }

    public void onError(Throwable error) {
        // 에러가 발생하면 로그를 출력합니다.
        log.error("WebSocket error: ", error);
    }
}
