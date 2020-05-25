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

        String emailPass = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        //校验登录名：只能输入5-20个以字母开头、可带数字、“_”、“.”的字串
        String usernamePass = "^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$";
        //校验密码：只能输入6-20个字母、数字、下划线
        String passwordPass = "^(\\w){6,20}$";

        boolean flag = userService.registPass(username);

        if (flag) {
            if(!username.matches(usernamePass)) {
                map.put("msg", "用户名验证不合格，格式为6-20个以字母开头、可带数字、“_”、“.”的字串");
                return "register1";
            } else if (!password.matches(passwordPass)) {
                map.put("msg", "密码验证不合格，格式为6-20个字母、数字、下划线");
                return "register1";
            }else if (!mail.matches(emailPass)) {
                map.put("msg", "邮箱地址验证不合格");
                return "register1";
            }else {
                User registUser = userService.regist(username, password, perms, mail);
                System.out.println(registUser.getId()+"      "+registUser.getUsername()+"       "+registUser.getPassword());
//            session.setAttribute("user", registUser);
                return "login2";
            }
        }else {
            map.put("msg", "用户名重复！");
            return "register1";
        }
    }
}
