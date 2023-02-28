package com.predictbit.chartdata_ws.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChartDataController {

    @GetMapping("/chartdata")
    // 데이터 가져오기
    public String getChartData() {
        log.info("/chartdata");
        return "chartdata";
    }
}
