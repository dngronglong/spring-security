package com.springsecurity.demo.utils;


import com.springsecurity.demo.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时刷新token
 */
@Component
@Configuration
@EnableScheduling
public class RefreshTokenUtil {

    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private RedisUtil redisUtil;

    @Scheduled(cron = "0 0/30 * * * ?")
    private void refreshToken(){
        System.out.println("开始执行定时任务时间: "+ LocalDateTime.now());
        Map map=new HashMap();
        map.put("client_id",redisUtil.get("client_id"));
        map.put("redirect_uri",redisUtil.get("redirect_uri"));
        map.put("client_secret",redisUtil.get("client_secret"));
        map.put("grant_type","refresh_token");
        map.put("refresh_token",redisUtil.get("refresh_token"));
        System.out.println(map);
        refreshTokenService.refreshToken(map);
        System.out.println("结束执行定时任务时间: "+ LocalDateTime.now());
    }
}
