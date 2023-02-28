package com.predictbit.chartdata_ws.repository;

import com.predictbit.chartdata_ws.domain.BB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈으로 등록하기 위한 어노테이션
// JpaRepository 인터페이스를 상속받아 BB 엔티티와 Integer 타입의 Primary Key를 가진 JpaRepository를 생성
public interface BBRepository extends JpaRepository<BB, Integer> {
    // BB 엔티티의 Primary Key인 idx 값이 startId 이상 endId 이하인 데이터들을 조회하는 메소드
    List<BB> findByIdxBetween(int startId, int endId);
}
