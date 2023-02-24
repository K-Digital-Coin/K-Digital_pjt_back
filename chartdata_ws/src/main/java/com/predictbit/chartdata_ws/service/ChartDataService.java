package com.predictbit.chartdata_ws.service;

import com.predictbit.chartdata_ws.domain.HistoryCoin;
import com.predictbit.chartdata_ws.repository.ChartDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartDataService {
    private final ChartDataRepository chartDataRepo;

    public List<HistoryCoin> getHistoryCoinList() {
        return chartDataRepo.findByIdxBetween(1, 19952);
    }

    public HistoryCoin getHistoryCoinByIdx(int idx) {
        return chartDataRepo.findById(idx).orElseThrow(() -> new IllegalArgumentException("Invalid idx"));
    }
}
