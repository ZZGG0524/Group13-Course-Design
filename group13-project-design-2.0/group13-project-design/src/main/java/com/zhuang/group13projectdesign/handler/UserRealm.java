package com.zhuang.group13projectdesign.handler;

import com.zhuang.group13projectdesign.bean.*;
import com.zhuang.group13projectdesign.mapper.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserListMapper userListMapper ;
    @Resource
    private MenuMapper menuMapper ;

    @Resource
    private RolePermissionMapper rolePermissionMapper;


    @Resource
    private RoleMapper  roleMapper;

    @Resource
    private PermissionMapper  permissionMapper;







    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名去数据库查询用户信息
        UserList user = userListMapper.selectByUsername(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取用户角色
        String  roleId=user.getRole();
        //根据roleId查询权限信息

        Map<String,Object> map=new HashMap<>();
        map.put("roleId",roleId);

        List<RolePermission> permissions = rolePermissionMapper.selectPerssion(map);
        //查看角色
        Optional<Role> first = roleMapper.selectRoles(map).stream().findFirst();
        if(first.isPresent()){
            simpleAuthorizationInfo.addRole(first.get().getRoleName());
        };
        permissions.forEach(permission->{
            Integer permissionId = permission.getPermissionId();
            Map<String,Object> map1=new HashMap<>();
            map.put("permissionId",permissionId);
            Permission permission1 =  permissionMapper.selectByPrimaryKey(permissionId);
            simpleAuthorizationInfo.addStringPermission(permission1.getPermissionsName());
            map1.clear();
        });
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        UserList user = userListMapper.selectByUsername(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}