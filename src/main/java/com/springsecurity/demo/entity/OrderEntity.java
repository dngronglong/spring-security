package com.springsecurity.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: spring-security
 * @description: 订单实体类
 * @author: DrongRonglong
 * @create: 2019-03-12 17:41
 **/

@Entity
@Table(name = "user_order")
public class OrderEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "payPersion")
    private String payPersion;//付款人
    @Column(name = "payMoney")
    private String payMoney;//付款金额
    @Column(name = "payMode")
    private String payMode;//支付方式4
    @Column(name = "description")
    private String description;//备注
    @Column(name = "date")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;//下单日期
    @Column(name = "userId")
    private Integer userId;//关联用户ID

    public OrderEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayPersion() {
        return payPersion;
    }

    public void setPayPersion(String payPersion) {
        this.payPersion = payPersion;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", payPersion='" + payPersion + '\'' +
                ", payMoney='" + payMoney + '\'' +
                ", payMode='" + payMode + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
