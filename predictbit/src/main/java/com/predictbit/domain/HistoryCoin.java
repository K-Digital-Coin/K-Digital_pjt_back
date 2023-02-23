package com.predictbit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "historycoin")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class HistoryCoin {
    @Id
    private int idx;
    private double opening_price;
    private double high_price;
    private double low_price;
    private double trade_price;
    private double candle_acc_trade_volume;
    private Timestamp candle_date_time_kst;
}
