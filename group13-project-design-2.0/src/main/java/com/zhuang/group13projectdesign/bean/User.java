package com.zhuang.group13projectdesign.bean;

import org.springframework.stereotype.Component;

//@Component
public class User {
    private Integer id;
    private String username;
    private String password;
//    private short sex;    //1代表男，2代表女，3代表保密
    private String email;
    private String perms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public short getSex() {
//        return sex;
//    }
//
//    public void setSex(short sex) {
//        this.sex = sex;
//    }
//
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}
