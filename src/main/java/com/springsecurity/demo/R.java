package com.springsecurity.demo;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: spring-security
 * @description: 封装返回数据
 * @author: DrongRonglong
 * @create: 2019-03-12 17:30
 **/

public class R extends HashMap<String,Object> {
    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public static R info(boolean success, String msg) {
        if(success){
            if(StringUtils.hasText(msg)) {
                return R.ok(msg);
            }
            return R.ok();
        } else {
            if(StringUtils.hasText(msg)) {
                return R.error(msg);
            }
            return R.error();
        }
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
