package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.User;
import com.zhuang.group13projectdesign.service.UserService;
import com.zhuang.group13projectdesign.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUserInfo/{id}")
    public String getUserInfo(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("userInfo", user);
        return "ownInfo";
    }


    /**
     * 登录
     * @param username
     * @param pwd
     * @param model
     * @return
     */
    @GetMapping(value = "/login")
    public String login(@PathVariable("id") String username , String  pwd,Model model) {
        User user = userService.login(username);
        if (user==null ) return "error";
        if(user!=null && !(MD5.get(pwd)).equals(MD5.get(pwd))) return "error";
        model.addAttribute("userInfo", user);
        return "ownInfo";
    }













}
