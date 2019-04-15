package com.springsecurity.demo.service.impl;

import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.MoneyEntity;
import com.springsecurity.demo.repository.MoneyRepository;
import com.springsecurity.demo.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: spring-security
 * @description:
 * @author: DrongRonglong
 * @create: 2019-03-21 13:25
 **/

@Service
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    private MoneyRepository moneyRepository;

    @Override
    public R updateMoney(Double money, String type) {
        if (type.equals("pay")){
            MoneyEntity moneyEntity=moneyRepository.findMoney();
            if (moneyEntity.getMoney()>=moneyEntity.getMoney()-money&&money-0>=0){
                moneyEntity.setMoney(moneyEntity.getMoney()-money);
                moneyRepository.saveAndFlush(moneyEntity);
            }else if (type.equals("income")){
                moneyEntity.setSummoney(moneyEntity.getSummoney()+money);
                moneyRepository.saveAndFlush(moneyEntity);
            }else{
                return R.error("参数不正确！");
            }
        }
        return R.ok();
    }

    @Override
    public Double getMoney() {
        return moneyRepository.findMoney().getMoney();
    }

    @Override
    public Double getSumMoney() {
        return moneyRepository.findMoney().getSummoney();
    }
}
