package com.springsecurity.demo.service.impl;

import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.AppVersionEntity;
import com.springsecurity.demo.repository.CheckUpdateRepository;
import com.springsecurity.demo.service.CheckUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CheckUpdateServiceImpl implements CheckUpdateService {

    @Autowired
    private CheckUpdateRepository checkUpdateRepository;

    @Override
    public R checkUpdate() {
        AppVersionEntity appVersionEntity=checkUpdateRepository.findOne();
        return R.ok().put("data",appVersionEntity);
    }
}
