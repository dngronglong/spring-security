package com.springsecurity.demo.service.impl;

import com.springsecurity.demo.R;
import com.springsecurity.demo.service.SendCodeService;
import com.springsecurity.demo.utils.Code;
import com.springsecurity.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendCodeServiceImpl implements SendCodeService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    public R sendCode(String email) {
        String code = new Code().achieveCode();
        redisUtil.set(email, code, 300);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(email);
        message.setSubject("注册验证码");
        message.setText("您的验证码为："+code+"\n有效期5分钟");
        try {
            mailSender.send(message);
        } catch (Exception e) {
            return R.error("发送邮件发生异常");
        }
        return R.ok();
    }
}
