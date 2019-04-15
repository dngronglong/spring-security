package com.springsecurity.demo.repository;

import com.springsecurity.demo.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity,Integer> {

    @Query(value = "select * from user_power where role=?1",nativeQuery = true)
    List<MenuEntity> getUserPower(String role);
}
