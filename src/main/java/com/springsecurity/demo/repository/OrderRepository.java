package com.springsecurity.demo.repository;

import com.springsecurity.demo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
    @Query(value = "select sum(payMoney) from user_order where date_format(date,'%Y-%m')=date_format(?1,'%Y-%m') and userId=?2" ,nativeQuery = true)
    Double countMoneyByUserMonth(String date,String userId);
    @Query(value = "select sum(payMoney) from user_order where to_days(date)=to_days(?1) and userId=?2",nativeQuery = true)
    Double countMoneyByUserDay(String date,String userId);
}
