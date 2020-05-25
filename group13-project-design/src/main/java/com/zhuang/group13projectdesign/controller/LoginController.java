package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.User;
import com.zhuang.group13projectdesign.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session,
                        HttpServletRequest request) {

        //校验登录名：只能输入6-20个以字母开头、可带数字、“_”、“.”的字串
        String usernamePass = "^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,19}$";
        //校验密码：只能输入6-20个字母、数字、下划线
        String passwordPass = "^(\\w){6,20}$";
        User user = userService.login(username);
        System.out.println(user.getId()+"      "+user.getUsername()+"       "+user.getPassword());

        if(!username.matches(usernamePass)) {
            map.put("msg", "用户名验证不合格，格式为6-20个以字母开头、可带数字、“_”、“.”的字串");
            return "login2";
        } else if (!password.matches(passwordPass)) {
            map.put("msg", "密码验证不合格，格式为6-20个字母、数字、下划线");
            return "login2";
        }

//        Subject subject = SecurityUtils.getSubject();
//
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//
//        try {
//            subject.login(token);
//            return "homepage";
//        } catch (UnknownAccountException e) {
//            map.put("msg", "用户名错误");
//            return "login2";
//        } catch (IncorrectCredentialsException e) {
//            map.put("msg", "密码错误");
//            return "login2";
//        }



        if (user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "homepage";
        } else {
            map.put("msg", "用户名或密码错误！");
            return "login2";
        }
    }

    @GetMapping(value = "/outLogin")
    public String outLogin(HttpSession session) {
        session.removeAttribute("user");
        return "login2";
    }
}
