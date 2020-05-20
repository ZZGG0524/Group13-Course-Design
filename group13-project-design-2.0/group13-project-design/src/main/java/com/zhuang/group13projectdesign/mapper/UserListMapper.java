package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.UserList;
import com.zhuang.group13projectdesign.bean.UserListQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserListMapper {
    int countByExample(UserListQuery example);

    int deleteByExample(UserListQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserList record);

    int insertSelective(UserList record);

    List<UserList> selectByExample(UserListQuery example);

    UserList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserList record, @Param("example") UserListQuery example);

    int updateByExample(@Param("record") UserList record, @Param("example") UserListQuery example);

    int updateByPrimaryKeySelective(UserList record);

    int updateByPrimaryKey(UserList record);

    UserList selectByUsername(String username);

    int updateRole(String name,String roleId);


}