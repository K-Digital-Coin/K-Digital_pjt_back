package com.predictbit.chartdata_ws.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity // JPA Entity 클래스임을 나타내는 어노테이션
@Table(name = "hc_bb") // 연결할 테이블명을 지정하는 어노테이션
@Getter @Setter @ToString // Getter, Setter, toString 메소드를 자동으로 생성해주는 롬복 어노테이션
@NoArgsConstructor @AllArgsConstructor // 디폴트 생성자와 전체 변수를 초기화하는 생성자를 자동으로 생성해주는 롬복 어노테이션
public class BB {
    @Id // Primary Key임을 나타내는 어노테이션
    private int idx; // BB 데이터의 Primary Key
    private Timestamp candle_date_time_kst; // 캔들 시간
    private double bbp; // 볼린저 밴드 하단 대비 현재 가격 비율 (BBP)
    private double bbc; // 볼린저 밴드 하단 값 (BBC)
    private double bbm; // 볼린저 밴드 중간 값 (BBM)
}
