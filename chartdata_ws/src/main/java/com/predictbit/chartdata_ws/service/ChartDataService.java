package com.predictbit.chartdata_ws.service;

import com.predictbit.chartdata_ws.domain.HistoryCoin;
import com.predictbit.chartdata_ws.repository.ChartDataRepository;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j // 로그 처리를 위한 lombok 어노테이션
@Service // Spring에서 Service 역할을 하는 클래스임을 명시하는 어노테이션
@ServerEndpoint(value="/chartdata") // WebSocket endpoint로 등록하는 어노테이션
public class ChartDataService {
    // ChartDataRepository를 사용하기 위한 객체 생성
    private ChartDataRepository chartDataRepo;

    @Autowired // 생성자를 이용한 의존성 주입을 위한 어노테이션
    public ChartDataService(ChartDataRepository chartDataRepo) {
        this.chartDataRepo = chartDataRepo;
    }

    // WebSocket으로 접속한 클라이언트들의 세션을 저장할 Set 객체 생성
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    // ChartDataRepository에서 id 범위에 따른 HistoryCoin 객체를 조회하는 메서드
    public List<HistoryCoin> getHistoryCoinByIdxBetween(int startIdx, int endIdx) {
        return chartDataRepo.findByIdxBetween(startIdx, endIdx);
    }

    // WebSocket 클라이언트와의 연결을 맺은 경우 로그 메시지를 출력
    @OnOpen
    public void onOpen(Session s) {
        log.info("open session : " + s.toString());
        if(!clients.contains(s)) {
            // 연결된 세션이 없다면 새로운 세션을 clients에 추가
            clients.add(s);
            log.info("session open : " + s);
        } else {
            // 이미 연결된 세션이 존재한다면 로그 메시지 출력
            log.info("Already connected session");
        }
    }

    // WebSocket 클라이언트와의 연결을 종료한 경우 로그 메시지를 출력하고 해당 세션을 clients에서 제거
    @OnClose
    public void onClose(Session s) {
        log.info("Session close : " + s);
        clients.remove(s);
    }
}
