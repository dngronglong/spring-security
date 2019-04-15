package com.springsecurity.demo.service;

import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface UserService {
    UserEntity findByName(String name);
    Page<UserEntity> userList(Integer page, Integer limit);
    UserEntity updateUser(UserEntity userEntity);
    Integer coutUser();
    R register(Map map);
}
