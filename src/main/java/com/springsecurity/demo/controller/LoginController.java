package com.springsecurity.demo.controller;

import com.springsecurity.demo.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: spring-security
 * @description: 跳转登录页面
 * @author: DrongRonglong
 * @create: 2019-03-11 15:10
 **/

@Controller
@RequestMapping("/userLogin")
public class LoginController {

    @GetMapping
    public String login(){
        return "/login";
    }
    public UserEntity getUser() { //为了session从获取用户信息,可以配置如下
        UserEntity user = new UserEntity();
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) user = (UserEntity) auth.getPrincipal();
        return user;
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
