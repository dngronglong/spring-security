package com.springsecurity.demo.service.impl;

import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.MenuEntity;
import com.springsecurity.demo.repository.PowerRepository;
import com.springsecurity.demo.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerRepository powerRepository;

    @Transactional
    @Override
    public R addPower(MenuEntity menuEntity) {
        return R.ok().put("data",powerRepository.saveAndFlush(menuEntity));
    }

    @Override
    public Page<MenuEntity> getList(Integer page, Integer limit) {
        //Sort sort = new Sort(Sort.Direction.DESC,"lastTime");
        Pageable pageable=new PageRequest(page,limit);
        return powerRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public R delete(MenuEntity menuEntity) {
        powerRepository.delete(menuEntity);
        return R.ok();
    }

    @Transactional
    @Override
    public R update(MenuEntity menuEntity) {
        return R.ok().put("data",powerRepository.saveAndFlush(menuEntity));
    }
}
