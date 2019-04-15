package com.springsecurity.demo.repository;

import com.springsecurity.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUsername(String username);
    @Query(value = "select count(id) from user",nativeQuery = true)
    Integer coutUser();

    @Query(value = "select * from user where username =?1",nativeQuery = true)
    UserEntity checkUser(String username);
}
