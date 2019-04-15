package com.springsecurity.demo.controller;


import com.springsecurity.demo.R;
import com.springsecurity.demo.service.GetTokenService;
import com.springsecurity.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/token")
public class GetTokenController {

    @Autowired
    private GetTokenService getTokenService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/get")
    public R getToken(@RequestBody Map map){
        return getTokenService.getToken(map);
    }

    @GetMapping("/refresh")
    public R refreshToken(){
        return R.ok().put("token",redisUtil.get("token"));
    }
}
