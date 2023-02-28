package com.predictbit.chartdata_ws.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity // JPA Entity 클래스임을 나타내는 어노테이션
@Table(name = "hc_rsi") // 연결할 테이블명을 지정하는 어노테이션
@Getter @Setter @ToString // Getter, Setter, toString 메소드를 자동으로 생성해주는 롬복 어노테이션
@NoArgsConstructor @AllArgsConstructor // 디폴트 생성자와 전체 변수를 초기화하는 생성자를 자동으로 생성해주는 롬복 어노테이션
public class RSI {
    @Id // Primary Key임을 나타내는 어노테이션
    private int idx; // RSI 데이터의 Primary Key
    private Timestamp candle_date_time_kst; // 캔들 시간

    private double rsi; // 가격의 상승압력과 하락압력 간의 상대적인 강도를 측정하여 과매수/과매도 구간 파악 지표 (RSI)
}
