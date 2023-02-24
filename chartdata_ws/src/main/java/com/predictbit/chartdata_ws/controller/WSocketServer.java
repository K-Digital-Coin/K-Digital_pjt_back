package com.predictbit.chartdata_ws.controller;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@Slf4j
@ServerEndpoint("/websocket")
public class WSocketServer {

    static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<Session>());
    static Boolean runCheck = false;

    @OnOpen
    // 클라이언트와 연결 성공 시 호출
    public void onOpen(Session userSession) {
        // 연결된 클라이언트 세션 객체 리스트에 추가
        sessionUsers.add(userSession);
        runCheck = true;
        log.info("Open Connection!...");
    }

    @OnClose
    // 클라이언트와 연결 종료 시 호출
    public void onClose(Session userSession) {
        // 종료된 클라이언트 세션 객체 리스트에서 제거
        sessionUsers.remove(userSession);
        if(sessionUsers.size() == 0)
            runCheck = false;
        log.info("Close Connection!...");
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnMessage
    // check Sensor Receiver에서 데이터 정보를 받아 데이터의 상태 정보를 전달
    public void onMessage(String message) throws IOException {
        if(runCheck == false) {

        }
    }
}
