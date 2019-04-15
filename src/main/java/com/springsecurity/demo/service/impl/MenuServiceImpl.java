package com.springsecurity.demo.service.impl;

import com.springsecurity.demo.R;
import com.springsecurity.demo.SysUser;
import com.springsecurity.demo.User;
import com.springsecurity.demo.entity.MenuEntity;
import com.springsecurity.demo.repository.MenuRepository;
import com.springsecurity.demo.security.JWTAuthorizationFilter;
import com.springsecurity.demo.service.MenuService;
import com.springsecurity.demo.utils.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public R getUserPower() {
        Object object= SecurityContextHolder.getContext().getAuthentication().getAuthorities();//获取当前登录用户信息
        String role = "";
        Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) object;
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        if (null==role){
            return R.error("请重新登录！");
        }
        return R.ok().put("data",menuRepository.getUserPower(role));
    }
}
