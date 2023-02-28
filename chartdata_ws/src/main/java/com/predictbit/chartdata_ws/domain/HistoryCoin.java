package com.predictbit.chartdata_ws.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Data   // 클래스 내 모든 필드에 대해 Getter, Setter, equals, hashCode, toString 등을 자동으로 생성해주는 Lombok 어노테이션
@Entity // JPA Entity 클래스임을 나타내는 어노테이션
@Table(name = "historycoin") // 연결할 테이블명을 지정하는 어노테이션
@NoArgsConstructor @AllArgsConstructor // 디폴트 생성자와 전체 변수를 초기화하는 생성자를 자동으로 생성해주는 롬복 어노테이션
public class HistoryCoin {
    @Id // Primary Key임을 나타내는 어노테이션
    private int idx; // HistoryCoin 데이터의 Primary Key
    private double opening_price; // 시가
    private double high_price; // 고가
    private double low_price; // 저가
    private double trade_price; // 종가(현재가)
    private double candle_acc_trade_volume; // 누적 거래량
    private Timestamp candle_date_time_kst; // 캔들 시간
}

