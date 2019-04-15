package com.springsecurity.demo.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springsecurity.demo.utils.RedisUtil;
import com.springsecurity.demo.utils.SendHttp;
import org.jsoup.Connection.Response;


import com.springsecurity.demo.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Void refreshToken(Map map) {
        String url="https://login.microsoftonline.com/common/oauth2/v2.0/token";
        Response res=null;
        try {
            res= SendHttp.post(url,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject= JSON.parseObject(res.body());
        redisUtil.set("token",jsonObject.getString("access_token"));
        redisUtil.set("refresh_token",jsonObject.getString("refresh_token"));
        return null;
    }
}
