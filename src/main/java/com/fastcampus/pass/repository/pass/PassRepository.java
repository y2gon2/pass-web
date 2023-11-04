package com.fastcampus.pass.repository.pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassRepository extends JpaRepository<PassEntity, Integer> {
    @Query(value = "SELECT p FROM PassEntity p " +
            "   JOIN FETCH p.packageEntity " +      // 연관된 엔티티나 컬렉션을 즉시 로딩하기 위한 목적으로 사용
            "        WHERE p.userId = :userId " +
            "     ORDER BY p.endedAt DESC NULLS FIRST ")
    List<PassEntity> findByUserId(String userId);
}
