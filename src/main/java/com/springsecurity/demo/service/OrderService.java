package com.springsecurity.demo.service;

import com.springsecurity.demo.entity.OrderEntity;
import org.springframework.data.domain.Page;


public interface OrderService {
    Page<OrderEntity> selectList(Integer page,Integer limit);
    OrderEntity addOrder(OrderEntity orderEntity);
    Double countMoney(String date,String userId);
    Double countDayMoney(String date,String userId);
}
