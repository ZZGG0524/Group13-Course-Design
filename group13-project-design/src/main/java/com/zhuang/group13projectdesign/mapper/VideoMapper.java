package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.Video;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

@Mapper
public interface VideoMapper {

//    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from video")
    public List<Video> getAllVideo();

    @Select("select * from video where id=#{id}")
    public Video getVideo(int id);

    @Insert("insert into video (studentName) values(#{studentName}) where id=#{id}")
    public int addCourse(Video video);

    @Select("select * from video where studentName=#{studentName}")
    public List<Video> getStudentCourse(String studentName);

    @Select("select * from video where user=#{user}")
    public List<Video> getUserCourse(String user);

//    @Insert("insert into video (title, path, user, courseName, details) values (#{title}, #{path}, #{user}, #{courseName), #{details})")
//    public void insertVideo(Video video);

    @Insert("insert into video (title, path, user, courseName, details) values (#{title}, #{path}, #{user}, #{courseName}, #{details})")
    public int insertVideo(Video video);

    @Delete("delete from video where id = #{id}")
    public void deleteVideo(int id);
}
