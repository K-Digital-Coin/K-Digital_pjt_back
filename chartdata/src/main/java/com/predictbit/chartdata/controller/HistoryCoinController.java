package com.predictbit.chartdata.controller;

import com.predictbit.chartdata.domain.HistoryCoin;
import com.predictbit.chartdata.service.HistoryCoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryCoinController {
    @Autowired
    HistoryCoinService hcs;
    private static final Logger log = LoggerFactory.getLogger(HistoryCoinController.class);

    public HistoryCoinController(HistoryCoinService hcs) {
        this.hcs = hcs;
        log.info("Called HistorycoinController");
    }

    @GetMapping("/api/chartData")
    public List<HistoryCoin> getHistorycoin() {
        log.info("HistorycoinController called getHistoryCoin()");
        return hcs.getHistoryCoinData();
    }
}
