package com.springsecurity.demo.controller;


import com.springsecurity.demo.R;
import com.springsecurity.demo.service.impl.GetListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: spring-security
 * @description:
 * @author: DrongRonglong
 * @create: 2019-03-22 14:01
 **/

@RequestMapping("/av")
@RestController
public class AvController {
    @Autowired
    private GetListData getListData;

    @GetMapping("/getType")
    public R getType(){
        return getListData.getType();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('TEST') or hasRole('VIP1')")
    @RequestMapping("/getList")
    public R getList(Integer page,Integer limit,String cate){
        Map map=new HashMap();
        map.put("page",page);
        map.put("per_page",limit);
        map.put("cate",cate);
        map.put("order_by","");
        map.put("order_method","");
        System.out.println(map);
        return getListData.getData(map);
    }
}
