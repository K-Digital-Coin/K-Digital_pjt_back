package com.predictbit.chartdata_ws.controller;

import com.predictbit.chartdata_ws.domain.BB;
import com.predictbit.chartdata_ws.domain.MA;
import com.predictbit.chartdata_ws.domain.MACD;
import com.predictbit.chartdata_ws.domain.RSI;
import com.predictbit.chartdata_ws.service.IndicatorDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j // 로그를 사용하기 위한 Lombok 어노테이션
@RestController // @Controller와 @ResponseBody를 합친 어노테이션
public class IndicatorDataController {

    private final IndicatorDataService indiDataService;

    @Autowired
    public IndicatorDataController(IndicatorDataService indiDataService) {
        this.indiDataService = indiDataService;
    }

    // 요청 방식 및 엔드포인트 지정
    @GetMapping("/ma")
    public List<MA> getMA() {
        log.info("/ma");
        return indiDataService.getMA();
    }
    @GetMapping("/macd")
    public List<MACD> getMACD() {
        log.info("/macd");
        return indiDataService.getMACD();
    }
    @GetMapping("/rsi")
    public List<RSI> getRSI() {
        log.info("/rsi");
        return indiDataService.getRSI();
    }
    @GetMapping("/bb")
    public List<BB> getBB() {
        log.info("/bb");
        return indiDataService.getBB();
    }
}
