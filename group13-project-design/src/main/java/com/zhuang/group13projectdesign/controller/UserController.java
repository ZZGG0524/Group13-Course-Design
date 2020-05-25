package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.User;
import com.zhuang.group13projectdesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //获取个人信息
    @GetMapping(value = "/getUserInfo/{id}")
    public String getUserInfo(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("userInfo", user);
        return "ownInfo";
    }

    //添加课程
//    @PostMapping(value = "/addCourse/{videoId}/{id}")
//    public String addCourse(@PathVariable("videoId") int videoId, @PathVariable("id") int id) {
//        User user = new User();
//        user.setId(id);
//        user.setCourseId(videoId);
//        userService.addCourse(user);
//        return null;
//    }

    //发布告示
//    @PostMapping(value = "/addNotice/{noticeId}/{id}")
//    public String addNotice(@PathVariable("noticeId") int noticeId, @PathVariable("id") int id) {
//        User user = new User();
//        user.setId(id);
//        user.setCourseId(noticeId);
//        userService.addCourse(user);
//        return null;
//    }
//
//    //发布新闻
//    @PostMapping(value = "/addNews/{newsId}/{id}")
//    public String addNews(@PathVariable("newsId") int newsId, @PathVariable("id") int id) {
//        User user = new User();
//        user.setId(id);
//        user.setCourseId(newsId);
//        userService.addCourse(user);
//        return null;
//    }
}
