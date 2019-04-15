package com.springsecurity.demo.controller;

import com.springsecurity.demo.PageUtils;
import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.UserEntity;
import com.springsecurity.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: spring-security
 * @description: 用户controller
 * @author: DrongRonglong
 * @create: 2019-03-11 14:22
 **/

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "success!";
    }

    @GetMapping("/list")
    public R userList(Integer page,Integer limit){
        Page<UserEntity> pager=userService.userList(page-1, limit);
        PageUtils pageUtil = new PageUtils(pager, page, limit);
        return R.ok().put("page", pageUtil).put("count", pageUtil.getTotalCount()).put("data", pageUtil.getList());
    }

    @PostMapping("/register")
    public R register(@RequestBody Map parameters){
        return userService.register(parameters);
    }
}
