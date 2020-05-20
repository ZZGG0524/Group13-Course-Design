package com.zhuang.group13projectdesign.service;

import com.zhuang.group13projectdesign.bean.User;
import com.zhuang.group13projectdesign.bean.UserList;
import com.zhuang.group13projectdesign.mapper.UserListMapper;
import com.zhuang.group13projectdesign.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserListService {


    @Resource
    private UserListMapper userListMapper;








    //登录
    public UserList login(String username) {
        UserList userList = userListMapper.selectByUsername(username);
        return userList;
    }

    /**
     * 为用户修改角色
     * @param name
     * @param roleId
     * @return
     */
    public int  updateRole(String name,String roleId){
        int i = userListMapper.updateRole(name, roleId);
        return  i;
    }






}
