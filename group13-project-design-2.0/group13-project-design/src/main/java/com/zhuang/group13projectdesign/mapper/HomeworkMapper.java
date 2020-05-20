package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.Homework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HomeworkMapper {

//    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into homework (title, path, user) values (#{title}, #{path}, #{user})")
    public int insertHomework(Homework homework);

    @Select("select * from homework where id = #{id}")
    public Homework getHomework(int id);
}
