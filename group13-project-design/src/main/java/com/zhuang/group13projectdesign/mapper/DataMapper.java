package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DataMapper {

//    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into data (title, path, user) values (#{title}, #{path}, #{user})")
    public int insertData(Data data);

    @Select("select * from data where id = #{id}")
    public Data getData(int id);
}
