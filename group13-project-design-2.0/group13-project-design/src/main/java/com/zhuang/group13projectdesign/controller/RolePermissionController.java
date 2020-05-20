package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.Permission;
import com.zhuang.group13projectdesign.bean.RolePermission;
import com.zhuang.group13projectdesign.bean.UserList;
import com.zhuang.group13projectdesign.mapper.PermissionMapper;
import com.zhuang.group13projectdesign.mapper.UserListMapper;
import com.zhuang.group13projectdesign.result.ResultMap;
import com.zhuang.group13projectdesign.service.RolePermissionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 角色  权限操作
 */
@RequestMapping("/rolePermission")
@Controller
public class RolePermissionController {

    @Autowired
    private RolePermissionService   rolePermissionService;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserListMapper userListMapper;

    /**
     * 给角色增加权限
     * @param roleId
     * @param
     * @return
     */
    @RequestMapping(value="/addPermissions", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "给角色增加权限", notes = "给角色增加权限", httpMethod = "POST", response = ResultMap.class)
    public ResultMap  addPermissions(@ApiParam(name = "data", value = "{\"roleid\":\"\",\"permissionId\":\"验证码\"}", required = true)  @RequestBody String data){
        ResultMap map=new ResultMap();
        JSONObject json = JSONObject.fromObject(data);
        String roleid = json.optString("roleid");
        String permissionId = json.optString("permissionId");
        int i = rolePermissionService.addPerssion(roleid, permissionId);
        map.setMsg("请求成功");
        map.setState(ResultMap.SUCCESS);
        return map;
    }
    @RequestMapping(value="/permissList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "权限列表", notes = "权限列表", httpMethod = "POST", response = ResultMap.class)
    public ResultMap permissions(){
        ResultMap map = new ResultMap();
        List<Permission> permissions = permissionMapper.selectByExample(null);
        map.setData(permissions);
        map.setState(ResultMap.SUCCESS);
        return map;
    }


}
