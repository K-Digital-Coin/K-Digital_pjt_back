package com.predictbit.chartdata_ws.service;

import com.predictbit.chartdata_ws.domain.HistoryCoin;
import com.predictbit.chartdata_ws.repository.ChartDataRepository;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@ServerEndpoint(value="/chartdata")
public class ChartDataService {
    private final ChartDataRepository chartDataRepo;

    public List<HistoryCoin> getHistoryCoinList() {
        return chartDataRepo.findByIdxBetween(1, 19952);
    }

}
