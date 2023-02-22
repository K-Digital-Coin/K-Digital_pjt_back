package com.predictbit.persistence;

import com.predictbit.domain.Historycoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistorycoinRepository extends JpaRepository<Historycoin, Integer> {
    @Query("SELECT h FROM Historycoin h WHERE h.idx BETWEEN :startIndex AND :endIndex")
    List<Historycoin> findByIndexRange(@Param("startIndex") int startIndex, @Param("endIndex") int endIndex);

    @Query("SELECT h FROM Historycoin h WHERE h.idx = :nextIndex")
    List<Historycoin> findByIndex(@Param("nextIndex") int nextIndex);

}
