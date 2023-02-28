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
import java.util.Set;

@Slf4j
@Service
@ServerEndpoint(value="/chartupdate")
public class ChartUpdateService {
    private ChartDataRepository chartDataRepo;

    @Autowired
    public ChartUpdateService(ChartDataRepository chartDataRepo) {
        this.chartDataRepo = chartDataRepo;
    }

    private static Set<Session> clients
            = Collections.synchronizedSet(new HashSet<>());

    public HistoryCoin getHistoryCoinByIdx(int idx) {
        return chartDataRepo.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("Invalid idx"));
    }

    @OnOpen
    public void onOpen(Session s) {
        log.info("open session : " + s.toString());
        if(!clients.contains(s)) {
            clients.add(s);
            log.info("session open : " + s);
        } else {
            log.info("Already connected session");
        }
    }

    @OnClose
    public void onClose(Session s) {
        log.info("Session close : " + s);
        clients.remove(s);
    }
}
