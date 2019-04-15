package com.springsecurity.demo.controller;


import com.springsecurity.demo.R;
import com.springsecurity.demo.service.CheckUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class CheckUpdateController {
    @Autowired
    private CheckUpdateService checkUpdateService;

    @GetMapping
    private R check(){
        return checkUpdateService.checkUpdate();
    }
}
