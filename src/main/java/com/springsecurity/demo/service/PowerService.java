package com.springsecurity.demo.service;


import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.MenuEntity;
import org.springframework.data.domain.Page;

public interface PowerService {

    R addPower(MenuEntity menuEntity);
    Page<MenuEntity> getList(Integer page,Integer limit);
    R delete(MenuEntity menuEntity);
    R update(MenuEntity menuEntity);
}
