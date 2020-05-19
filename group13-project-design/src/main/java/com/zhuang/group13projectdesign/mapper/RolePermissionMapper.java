package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.RolePermission;
import com.zhuang.group13projectdesign.bean.RolePermissionQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RolePermissionMapper {
    int countByExample(RolePermissionQuery example);

    int deleteByExample(RolePermissionQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExample(RolePermissionQuery example);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionQuery example);

    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionQuery example);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);


    List<RolePermission>   selectPerssion(Map<String ,Object> map);





}