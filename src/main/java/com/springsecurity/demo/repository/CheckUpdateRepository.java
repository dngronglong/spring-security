package com.springsecurity.demo.repository;

import com.springsecurity.demo.entity.AppVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CheckUpdateRepository extends JpaRepository<AppVersionEntity,Integer> {

    @Query(value = "SELECT * FROM app_version ORDER BY time desc limit 1",nativeQuery = true)
    AppVersionEntity findOne();
}
