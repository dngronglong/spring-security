package com.springsecurity.demo.repository;

import com.springsecurity.demo.entity.MoneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MoneyRepository extends JpaRepository<MoneyEntity,Integer> {
    @Query(value = "select * from money",nativeQuery = true)
    MoneyEntity findMoney();
}
