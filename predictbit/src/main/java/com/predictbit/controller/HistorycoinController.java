package com.predictbit.controller;

import com.predictbit.domain.Historycoin;
import com.predictbit.service.HistorycoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistorycoinController {
    HistorycoinService hcs;
    private static final Logger log = LoggerFactory.getLogger(HistorycoinController.class);

    public HistorycoinController(HistorycoinService hcs) {
        this.hcs = hcs;
        log.info("Called HistorycoinController");
    }

    @Autowired
    public void setHistoryCoinService(HistorycoinService hcs) {
        this.hcs = hcs;
        log.info("HistorycoinController called setHistoryCoinService()");
    }

    @GetMapping("/api/historycoin")
    public List<Historycoin> getHistorycoin() {
        log.info("HistorycoinController called getHistoryCoin()");
        return hcs.getHistorycoin();
    }

    @GetMapping("/api/updatecoin")
    public void updateHistorycoin() {
        log.info("HistorycoinController called updateHistorycoin()");
        hcs.updateHistorycoin();
//        return hcs.updateHistorycoin();
    }

}
