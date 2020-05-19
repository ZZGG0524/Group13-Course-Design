package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.result.ResultMap;
import com.zhuang.group13projectdesign.service.RolePermissionService;
import com.zhuang.group13projectdesign.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;


/**
 * 用户
 */
@RequestMapping("/userList")
@Controller
public class UserListController {

    @Autowired
    private UserListService userListService;

    /**
     * 给 用户增加角色
     * @param name
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping("addRole")
    public ResultMap  addPermissions(@NotNull  String name,@NotNull String roleId){
        ResultMap map=new ResultMap();
        int i = userListService.updateRole(name, roleId);
        map.setMsg("请求成功");
        map.setState(ResultMap.SUCCESS);
        return map;
    }
}
