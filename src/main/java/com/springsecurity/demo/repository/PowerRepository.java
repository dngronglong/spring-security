package com.springsecurity.demo.repository;

import com.springsecurity.demo.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PowerRepository extends JpaRepository<MenuEntity,Integer> {
}
