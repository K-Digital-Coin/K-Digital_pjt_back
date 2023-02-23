package com.predictbit.chartdata.service;

import com.predictbit.chartdata.domain.HistoryCoin;
import com.predictbit.chartdata.repository.HistoryCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryCoinService {
    @Autowired
    private HistoryCoinRepository historyRepo;

    public List<HistoryCoin> getHistoryCoinData() {
        return historyRepo.findByIndexRange(1, 19952);
    }

//    public List<HistoryCoin> getHistoryCoins() {
//        return historyRepo.findByIndex();
//    }
}