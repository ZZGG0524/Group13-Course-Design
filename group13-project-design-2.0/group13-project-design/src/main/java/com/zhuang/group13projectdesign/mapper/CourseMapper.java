package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from course")
    public List<Course> getAllCourse();

    @Options(useGeneratedKeys = true, keyProperty = "videoId")
    @Select("select * from course where id = #{id}")
    public Course getCourseInfo(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from course where id = #{id}")
    public Course getCourse(int id);

    @Insert("insert into course (username, title) values(#{username}, #{title})")
    public int insertCourse(Course course);

    @Delete("delete from course where id = #{id}")
    public int deleteCourse(int id);
}
