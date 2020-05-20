package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
//@Component(value ="userMapper")
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from user where username=#{username}")
    public User findAllUserByUsername(String username);

    @Select("select * from user where id = #{id}")
    public User getUserById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(username, password, perms, email) values(#{username}, #{password}, #{perms}, #{email})")
    public int insertUser(User user);

    //数据库删除数据后保证id自增连续
    @Update("alter table ${user} AUTO_INCREMENT=1")
    public void autoIncrement();
//    @Update("update user set username=#{username}, password=#{password} where id=#{id}")
//    public int updateUser(User user);
}
