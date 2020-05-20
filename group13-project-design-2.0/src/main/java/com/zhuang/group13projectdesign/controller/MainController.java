package com.zhuang.group13projectdesign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @GetMapping(value = "/sendMail")
    public void sendMail(@RequestParam("mail") String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");
        message.setText("我菜得睡不着哈哈哈哈哈哈");
        message.setTo(mail);
        message.setFrom("18384835774@163.com");

        javaMailSender.send(message);
    }

//    @PostMapping(value = "/sendMail")
//    public void sendCaptcha(@RequestParam("mail") String mail) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject("验证码");
//        message.setText("内容");
//        message.setTo(mail);
//        message.setFrom("18384835774@163.com");
//
//        javaMailSender.send(message);
//    }
}
