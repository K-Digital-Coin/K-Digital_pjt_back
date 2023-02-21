package com.predictbit.service;

import com.predictbit.domain.Historycoin;
import com.predictbit.persistence.HistorycoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorycoinService {

    private HistorycoinRepository historyRepo;

    @Autowired
    public HistorycoinService(HistorycoinRepository historyRepo) {
        this.historyRepo = historyRepo;
    }
    public List<Historycoin> getHistoryCoin() {
        List<Historycoin> list = historyRepo.findAll();
        return list;
    }
}
