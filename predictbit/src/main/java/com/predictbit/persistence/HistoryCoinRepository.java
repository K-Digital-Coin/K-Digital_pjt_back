package com.predictbit.persistence;

import com.predictbit.domain.HistoryCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryCoinRepository extends JpaRepository<HistoryCoin, Integer> {
    @Query("SELECT h FROM HistoryCoin h WHERE h.idx BETWEEN :startIndex AND :endIndex")
    List<HistoryCoin> findByIndexRange(@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

    @Query("SELECT h FROM HistoryCoin h WHERE h.idx = :nextIndex")
    List<HistoryCoin> findByIndex(@Param("nextIndex") int nextIndex);

}
