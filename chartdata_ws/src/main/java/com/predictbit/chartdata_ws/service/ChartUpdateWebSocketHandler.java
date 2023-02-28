package com.predictbit.chartdata_ws.service;

import com.predictbit.chartdata_ws.domain.HistoryCoin;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ChartUpdateWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private final ChartUpdateService chartUpdateService;
    private static List<WebSocketSession> list = new ArrayList<>();
    private long idx = 0L;
    private int currentIdx = 19952;

    @Scheduled(fixedDelay = 1000)
    // 10초마다 idx 1씩 증가시키면서 데이터를 보내줍니다.
    public void scheduledProcessing() throws IOException {
        if (list.size() == 0) return;
        currentIdx++;

        HistoryCoin historyCoin = chartUpdateService.getHistoryCoinByIdx(currentIdx);
        String msg = historyCoin.toString();
        log.info("msg : " + msg);

        TextMessage message = new TextMessage(msg.getBytes());

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

    @Override
    // Client 접속 시 호출
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);
        log.info(session + " 클라이언트 접속");
    }

    @Override
    // Client 접속 해제 시 호출
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + " 클라이언트 접속 해제");
        list.remove(session);
    }
}
