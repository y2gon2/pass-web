package com.fastcampus.pass.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGroupMappingRepository extends JpaRepository<UserGroupMappingEntity, String> {
    @Query(value = "SELECT DISTINCT u.userGroupId " + // DISTINCT : SQL 쿼리에서 중복 값을 제거하고 유일한 값들만 선택
                   "           FROM UserGroupMappingEntity u " +
                   "       ORDER BY u.userGroupId")
    List<String> findDistinctUserGroupId();
}
