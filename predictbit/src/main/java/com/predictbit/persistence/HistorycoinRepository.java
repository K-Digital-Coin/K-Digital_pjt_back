package com.predictbit.persistence;

import com.predictbit.domain.Historycoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorycoinRepository extends JpaRepository<Historycoin, Integer> {
}
