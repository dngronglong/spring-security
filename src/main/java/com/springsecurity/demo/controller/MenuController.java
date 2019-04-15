package com.springsecurity.demo.controller;


import com.springsecurity.demo.R;
import com.springsecurity.demo.service.MenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/list")
    public R getPowerList(){
        return menuService.getUserPower();
    }
}
