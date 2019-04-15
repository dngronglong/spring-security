package com.springsecurity.demo.service;

import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.MoneyEntity;

public interface MoneyService {
    R updateMoney(Double moneyEntity, String type);
    Double getMoney();
    Double getSumMoney();
}
