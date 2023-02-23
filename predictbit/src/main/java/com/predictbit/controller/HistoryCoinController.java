package com.predictbit.controller;

import com.predictbit.domain.HistoryCoin;
import com.predictbit.service.HistoryCoinService;
import com.predictbit.websocket.HistoryCoinWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HistoryCoinController {
    HistoryCoinService hcs;
    private static final Logger log = LoggerFactory.getLogger(HistoryCoinController.class);

    public HistoryCoinController(HistoryCoinService hcs) {
        this.hcs = hcs;
        log.info("Called HistorycoinController");
    }

    @Autowired
    public void setHistoryCoinService(HistoryCoinService hcs) {
        this.hcs = hcs;
        log.info("HistorycoinController called setHistoryCoinService()");
    }

    @GetMapping("/api/chartData")
    public List<HistoryCoin> getHistorycoin() {
        log.info("HistorycoinController called getHistoryCoin()");
        return hcs.getHistorycoin();
    }

    @GetMapping("/api/updatecoin")
    public void updateHistorycoin() {
        log.info("HistorycoinController called updateHistorycoin()");
        hcs.updateHistorycoin();
//        return hcs.updateHistorycoin();
    }
    @MessageMapping("/api/chartData")
    @SendTo("/api/updateChartData")
    public List<HistoryCoin> updateHistorycoin() {
        log.info("HistorycoinController called updateHistorycoin()");
        List<HistoryCoin> historyCoins = historyRepo.findAll();
        return null;
    }
}
