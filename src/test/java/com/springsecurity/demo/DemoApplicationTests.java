package com.springsecurity.demo;

import com.springsecurity.demo.service.UserService;
import com.springsecurity.demo.utils.RedisUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class DemoApplicationTests {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testSetRedis(){
        userService.findByName("admin");
    }

    @Test
    public void testGetTRedis(){
//        redisUtil.set("test","aaa");
        System.out.println(redisUtil.hasKey("userinfo::admin"));
    }
    @Test
    public void getPassword(){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String password=passwordEncoder.encode("test");
        System.out.println("密码"+password);
    }

}
