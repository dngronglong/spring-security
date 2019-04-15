package com.springsecurity.demo.controller;


import com.springsecurity.demo.R;
import com.springsecurity.demo.service.SendCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class SendCodeController {
    @Autowired
    private SendCodeService sendCodeService;

    @GetMapping
    public R send(String email){
        return sendCodeService.sendCode(email);
    }
}
