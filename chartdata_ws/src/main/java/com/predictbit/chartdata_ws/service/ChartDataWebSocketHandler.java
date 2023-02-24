package com.predictbit.chartdata_ws.service;

import com.google.gson.Gson;
import com.predictbit.chartdata_ws.domain.HistoryCoin;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.List;

@Component
public class ChartDataWebSocketHandler extends AbstractWebSocketHandler {

    private final ChartDataService chartDataService;

    public ChartDataWebSocketHandler(ChartDataService chartDataService) {
        this.chartDataService = chartDataService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // WebSocket을 사용하여 데이터를 수신받을 때 호출됩니다.
        // 별도의 처리를 하지 않고, 데이터를 무시하고 넘어갑니다.
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // WebSocket 연결이 성공하면 호출됩니다.
        List<HistoryCoin> historyCoins = chartDataService.getHistoryCoinList();
        String json = new Gson().toJson(historyCoins);

        // idx 1~19952 데이터를 한번에 보내줍니다.
//        System.out.println(chartDataService.getHistoryCoinList());
//        session.sendMessage(new TextMessage(json));

        // 10초마다 idx 1씩 증가시키면서 데이터를 보내줍니다.
        int currentIdx = 19952;
        while (currentIdx <= 20000) {
            Thread.sleep(10000); // 10초마다 실행하기 위해 10초 동안 sleep 합니다.
            currentIdx++;
//            System.out.println(chartDataService.getHistoryCoinByIdx(currentIdx));
            HistoryCoin historyCoin = chartDataService.getHistoryCoinByIdx(currentIdx);
            if (historyCoin != null) {
                json = new Gson().toJson(historyCoin);
                session.sendMessage(new TextMessage(json));
            }
        }
    }
}
