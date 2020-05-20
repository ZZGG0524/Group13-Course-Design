package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.User;
import com.zhuang.group13projectdesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class RegisteredController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/regist")
    public String regist(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("perms") String perms,
                         @RequestParam("mail") String mail,
                         Map<String, Object> map, HttpSession session) {

        User user = userService.regist(username, password, perms, mail);
        if (user != null){
            System.out.println(user.getId()+"      "+user.getUsername()+"       "+user.getPassword());
            session.setAttribute("user", user);
            return "main";
        } else {
            map.put("msg", "用户名重复！");
            return "regist";
        }
    }
}
