package com.predictbit.chartdata_ws.controller;

import com.predictbit.chartdata_ws.domain.HistoryCoin;
import com.predictbit.chartdata_ws.service.ChartDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableScheduling
@RequiredArgsConstructor
public class ChartDataController {
    private final ChartDataService chartDataService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private int currentIdx = 19953;

    @GetMapping("/chartdata")
    // 데이터 가져오기
    public List<HistoryCoin> getHistoryCoinList() {

        return chartDataService.getHistoryCoinList();
    }

    @Scheduled(fixedDelay = 100)
    // 10초마다 주기적으로 실행
    public void sendChartData() {
        HistoryCoin historyCoin = chartDataService.getHistoryCoinByIdx(currentIdx);
        if (historyCoin != null && historyCoin.getIdx() < 20000) {
            System.out.println(historyCoin);
            simpMessagingTemplate.convertAndSend("/topic/chartdata", historyCoin);
            currentIdx++;
        } else if (historyCoin != null && historyCoin.getIdx() == 20000) {
            System.out.println(historyCoin);
            simpMessagingTemplate.convertAndSend("/topic/chartdata", historyCoin);
            // 여기서 멈출 방법 찾아야 함, json으로 보내주는 거 맞나..?
        }
    }
}
