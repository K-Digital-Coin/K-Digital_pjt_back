package com.predictbit.chartdata_ws.controller;

import com.predictbit.chartdata_ws.domain.HistoryCoin;
import com.predictbit.chartdata_ws.service.ChartDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChartDataController {
    private final ChartDataService chartDataService;

    @GetMapping("/chartdata")
    // 데이터 가져오기
    public List<HistoryCoin> getChartData() {
        log.info("/chartData");
        return chartDataService.getHistoryCoinList();
    }
}
