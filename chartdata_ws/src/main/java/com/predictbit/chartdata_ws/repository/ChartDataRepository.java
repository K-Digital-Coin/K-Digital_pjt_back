package com.predictbit.chartdata_ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.predictbit.chartdata_ws.domain.HistoryCoin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartDataRepository extends JpaRepository<HistoryCoin, Integer> {
    List<HistoryCoin> findByIdxBetween(int startId, int endId);
}
