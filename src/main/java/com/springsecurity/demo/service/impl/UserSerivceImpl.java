package com.springsecurity.demo.service.impl;

import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.UserEntity;
import com.springsecurity.demo.repository.UserRepository;
import com.springsecurity.demo.service.UserService;
import com.springsecurity.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;


/**
 * @program: spring-security
 * @description:
 * @author: DrongRonglong
 * @create: 2019-03-20 14:22
 **/

@Service
public class UserSerivceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Cacheable(value = "userinfo",key = "#p0")
    @Override
    public UserEntity findByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Page<UserEntity> userList(Integer page, Integer limit) {
//        Sort sort = new Sort(Sort.Direction.DESC,"lastTime"); //创建时间降序排序
        Pageable pageable=new PageRequest(page,limit);
        return userRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public Integer coutUser() {
        return userRepository.coutUser();
    }

    @Override
    public R register(Map map) {
        UserEntity userEntity=new UserEntity();
        String code="";
        try {
            code=map.get("email").toString();
        }catch (Exception e){
            return R.error("验证码错误！");
        }
//        System.out.println(code);
        if (!StringUtils.isEmpty(map.get("username"))&&!StringUtils.isEmpty(map.get("password"))&&!StringUtils.isEmpty(map.get("realname"))&&!StringUtils.isEmpty(map.get("email"))&&!StringUtils.isEmpty(map.get("code"))){
            if(null!=userRepository.checkUser(map.get("username").toString())){
                return R.error("用户名重复");
            }
            if (null==redisUtil.get(code)||!redisUtil.get(code).equals(map.get("code"))){
                return R.error("验证码错误！");
            }
            userEntity.setUsername(map.get("username").toString());
            userEntity.setPassword(bCryptPasswordEncoder.encode(map.get("password").toString()));
            userEntity.setRealname(map.get("realname").toString());
            userEntity.setEmail(map.get("email").toString());
            userEntity.setRole("ROLE_VIP0");
            userEntity.setCreat_time(new Date());
            userRepository.saveAndFlush(userEntity);
            redisUtil.del(map.get("email").toString());
            System.out.println("redis验证码"+redisUtil.get(map.get("email").toString()));
            return R.ok();
        }
        return R.error("参数错误");
    }

}
