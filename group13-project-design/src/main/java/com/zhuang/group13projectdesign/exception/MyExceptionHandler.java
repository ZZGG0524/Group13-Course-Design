package com.zhuang.group13projectdesign.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e) {
        System.out.println("没有通过权限验证呀~~~");
        return "没有通过权限验证！";
    }
}