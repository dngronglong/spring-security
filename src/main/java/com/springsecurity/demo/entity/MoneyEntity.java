package com.springsecurity.demo.entity;

import javax.persistence.*;

/**
 * @program: spring-security
 * @description: 室费
 * @author: DrongRonglong
 * @create: 2019-03-21 13:20
 **/

@Entity
@Table(name = "money")
public class MoneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "money")
    private Double money;
    @Column(name = "summoney")
    private Double summoney;
    public MoneyEntity() {
    }

    public Double getSummoney() {
        return summoney;
    }

    public void setSummoney(Double summoney) {
        this.summoney = summoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "MoneyEntity{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}
