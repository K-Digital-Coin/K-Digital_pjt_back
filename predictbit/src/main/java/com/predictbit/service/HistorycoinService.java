package com.predictbit.service;

import com.predictbit.domain.Historycoin;
import com.predictbit.persistence.HistorycoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HistorycoinService {

    @Autowired
    private HistorycoinRepository historyRepo;

    private int nextIndex = 19953;

    public List<Historycoin> getHistorycoin() {
        return historyRepo.findByIndexRange(1, 19952);
    }

    // @Scheduled 메서드는 인수를 받거나 값을 반환할 수 없음
//    @Scheduled(fixedDelay = 10000) // 10초마다 갱신
//    public List<Historycoin> updateHistorycoin() {
//        List<Historycoin> newData = historyRepo.findByIndex(nextIndex);
//        nextIndex = nextIndex + 1;
//        System.out.println(newData != null ? newData : Collections.emptyList());
//        return newData != null ? newData : Collections.emptyList();
//    }

    @Scheduled(fixedDelay = 10000) // 10초마다 갱신
    public void updateHistorycoin() {
        List<Historycoin> newData = historyRepo.findByIndex(nextIndex);
        nextIndex = nextIndex + 1;
        System.out.println(newData != null ? newData : Collections.emptyList());
    }
}
