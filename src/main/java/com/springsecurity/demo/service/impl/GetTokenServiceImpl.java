package com.springsecurity.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springsecurity.demo.R;
import com.springsecurity.demo.service.GetTokenService;
import com.springsecurity.demo.utils.RedisUtil;
import com.springsecurity.demo.utils.SendHttp;
import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


@Service
public class GetTokenServiceImpl implements GetTokenService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public R getToken(Map map) {
        System.out.println("参数为："+map);
        String url="https://login.microsoftonline.com/common/oauth2/v2.0/token";
        Response res=null;
        try {
            res= SendHttp.post(url,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject= JSON.parseObject(res.body());
        System.out.println(jsonObject.getString("access_token"));
        redisUtil.set("token",jsonObject.getString("access_token"));
        System.out.println(jsonObject.getString("refresh_token"));
        redisUtil.set("refresh_token",jsonObject.getString("refresh_token"));
        redisUtil.set("client_id",map.get("client_id"));
        redisUtil.set("redirect_uri",map.get("redirect_uri"));
        redisUtil.set("client_secret",map.get("client_secret"));
        redisUtil.set("code",map.get("code"));
        //redisUtil.set("client_id",res.body())
        return R.ok().put("data",res.body());
    }
}
