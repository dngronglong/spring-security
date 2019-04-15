package com.springsecurity.demo.service.impl;


import com.springsecurity.demo.entity.UserEntity;
import com.springsecurity.demo.repository.UserRepository;
import com.springsecurity.demo.utils.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(s);
        if (null==user){
            throw new UsernameNotFoundException("账号密码错误！");
        }
        return new JwtUser(user);
    }
}
