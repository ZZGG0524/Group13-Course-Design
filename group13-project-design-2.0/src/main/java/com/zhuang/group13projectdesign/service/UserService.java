package com.zhuang.group13projectdesign.service;

import com.zhuang.group13projectdesign.bean.User;
import com.zhuang.group13projectdesign.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    //@Autowired
    @Resource
    private UserMapper userMapper;

    private boolean flag;

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    //注册
    public User regist(String username, String password, String perms, String mail) {
        User existUsername = userMapper.findAllUserByUsername(username);
        if (existUsername!= null){
            return null;
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPerms(perms);
            user.setEmail(mail);
            userMapper.insertUser(user);
            return user;
        }
    }

    //登录
    public User login(String username) {
        User user = userMapper.findAllUserByUsername(username);
        return user;
    }




}
