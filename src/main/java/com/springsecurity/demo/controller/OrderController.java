package com.springsecurity.demo.controller;

import com.springsecurity.demo.PageUtils;
import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.OrderEntity;
import com.springsecurity.demo.service.MoneyService;
import com.springsecurity.demo.service.OrderService;
import com.springsecurity.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: spring-security
 * @description: 订单
 * @author: DrongRonglong
 * @create: 2019-03-12 17:29
 **/

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private MoneyService moneyService;

    @PostMapping("/add")
    public R addOrder(@RequestBody OrderEntity orderEntity){
        return R.ok().put("entity",orderService.addOrder(orderEntity));
    }
    @GetMapping("/list")
    public R getList(Integer page,Integer limit){
        Page<OrderEntity> pager=orderService.selectList(page-1, limit);
        System.out.println("分页数据："+pager);
        PageUtils pageUtil = new PageUtils(pager, page, limit);
        return R.ok().put("page", pageUtil).put("count", pageUtil.getTotalCount()).put("data", pageUtil.getList());
    }
    @GetMapping("/count")
    public R countMoney(String date,String userId){
        System.out.println("参数为："+date+","+userId);
        return R.ok().put("moneyMonth",orderService.countMoney(date,userId)).put("moneyDay",orderService.countDayMoney(date,userId)).put("userNum",userService.coutUser()).put("balance",moneyService.getMoney()).put("sumMoney",moneyService.getSumMoney());
    }
}
