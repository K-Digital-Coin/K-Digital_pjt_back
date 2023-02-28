package com.predictbit.chartdata_ws.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j          // 로그를 사용하기 위한 Lombok 어노테이션
@Controller     // Spring Controller로 사용하기 위한 어노테이션
public class ChartDataController {

    @GetMapping("/chartdata") // GET 요청 처리 및 엔드포인트 지정
    public String getChartData() {
        log.info("/chartdata");
        return "chartdata";
    }
}
