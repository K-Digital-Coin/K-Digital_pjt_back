package com.predictbit.service;

import com.predictbit.domain.HistoryCoin;
import com.predictbit.persistence.HistoryCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HistoryCoinService {

    @Autowired
    private HistoryCoinRepository historyRepo;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private int nextIndex = 19953;

    public List<HistoryCoin> getHistorycoin() {
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
        List<HistoryCoin> newData = historyRepo.findByIndex(nextIndex);
        System.out.println(newData != null ? newData : Collections.emptyList());
        nextIndex = nextIndex + 1;
        messagingTemplate.convertAndSend("/api/updateChartData", newData);
    }
}
