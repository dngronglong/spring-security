package com.springsecurity.demo.controller;


import com.springsecurity.demo.PageUtils;
import com.springsecurity.demo.R;
import com.springsecurity.demo.entity.MenuEntity;
import com.springsecurity.demo.service.PowerService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/power")
public class PowerController {

    @Resource
    private PowerService powerService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public R getList(Integer page,Integer limit){
        Page<MenuEntity> pager=powerService.getList(page-1,limit);
        PageUtils pageUtil = new PageUtils(pager, page, limit);
        return R.ok().put("page", pageUtil).put("count", pageUtil.getTotalCount()).put("data", pageUtil.getList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete")
    public R delete(@RequestBody Map map){
        MenuEntity menuEntity=new MenuEntity();
        menuEntity.setId(Integer.parseInt(map.get("id").toString()));
        return powerService.delete(menuEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/put")
    public R add(@RequestBody MenuEntity menuEntity){
        return powerService.addPower(menuEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public R updateData(@RequestBody MenuEntity menuEntity){
        return powerService.update(menuEntity);
    }
}
