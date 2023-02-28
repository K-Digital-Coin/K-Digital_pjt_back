package com.predictbit.chartdata_ws.service;

import com.predictbit.chartdata_ws.domain.*;
import com.predictbit.chartdata_ws.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IndicatorDataService {

    private MARepository maRepository;
    private MACDRepository macdRepository;
    private RSIRepository rsiRepository;
    private BBRepository bbRepository;

    // startId, endId 변수 초기화
    private int startId = 19851;
    private int endId = 19952;

    @Autowired  // 생성자 주입으로 받아 초기화
    public IndicatorDataService(MARepository maRepository, MACDRepository macdRepository, RSIRepository rsiRepository, BBRepository bbRepository) {
        this.maRepository = maRepository;
        this.macdRepository = macdRepository;
        this.rsiRepository = rsiRepository;
        this.bbRepository = bbRepository;
    }

    // 각각의 리포지토리 내 메소드를 통해 startId와 endId 범위의 MA, MACD, RSI, BB 데이터 조회
    public List<MA> getMA() {
        return maRepository.findByIdxBetween(startId, endId);
    }
    public List<MACD> getMACD() {
        return macdRepository.findByIdxBetween(startId, endId);
    }
    public List<RSI> getRSI() {
        return rsiRepository.findByIdxBetween(startId, endId);
    }
    public List<BB> getBB() {
        return bbRepository.findByIdxBetween(startId, endId);
    }
}
