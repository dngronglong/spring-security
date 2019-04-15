package com.springsecurity.demo.service.impl;

import com.springsecurity.demo.entity.OrderEntity;
import com.springsecurity.demo.repository.OrderRepository;
import com.springsecurity.demo.service.MoneyService;
import com.springsecurity.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: spring-security
 * @description: 订单逻辑操作
 * @author: DrongRonglong
 * @create: 2019-03-12 17:50
 **/

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MoneyService moneyService;

    @Override
    public Page<OrderEntity> selectList(Integer page,Integer limit) {
        Sort sort = new Sort(Sort.Direction.DESC,"date"); //创建时间降序排序
//        Integer page=Integer.parseInt(map.get("page").toString());
//        Integer limit=Integer.parseInt(map.get("limit").toString());
        Pageable pageable=new PageRequest(page,limit,sort);
        return orderRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public OrderEntity addOrder(OrderEntity orderEntity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss") ;
        String dateStr=dateFormat.format(new Date());
        Date date=null;
        try {
             date=dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orderEntity.setDate(date);
        orderRepository.saveAndFlush(orderEntity);
        moneyService.updateMoney(Double.parseDouble(orderEntity.getPayMoney()),"pay");
        return orderEntity;
    }

    @Override
    public Double countMoney(String date, String userId) {
        return orderRepository.countMoneyByUserMonth(date,userId);
    }

    @Override
    public Double countDayMoney(String date, String userId) {
        return orderRepository.countMoneyByUserDay(date,userId);
    }
}
