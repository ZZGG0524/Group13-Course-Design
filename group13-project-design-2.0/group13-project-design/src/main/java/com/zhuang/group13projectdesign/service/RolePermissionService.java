package com.zhuang.group13projectdesign.service;

import com.zhuang.group13projectdesign.bean.RolePermission;
import com.zhuang.group13projectdesign.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RolePermissionService {


    @Resource
    private RolePermissionMapper   rolePermissionMapper;


    /**
     * 给角色赋权限
     * @param roleId
     * @param permissionId
     * @return
     */
    public int  addPerssion(String   roleId,String permissionId){
        RolePermission  rolePermission=new RolePermission();
        rolePermission.setRoleId(Integer.valueOf(roleId));
        rolePermission.setPermissionId(Integer.valueOf(permissionId));
        int insert = rolePermissionMapper.insert(rolePermission);
        return insert;

    }




}
