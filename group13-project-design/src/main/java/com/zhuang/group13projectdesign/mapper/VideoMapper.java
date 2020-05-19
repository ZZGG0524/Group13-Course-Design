package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.Video;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

@Mapper
public interface VideoMapper {

//    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from video")
    public Collection<Video> getAllVideo();

    @Select("select * from video where id=#{id}")
    public Video getVideo(int id);

    @Insert("insert into video (title, path, user) values (#{title}, #{path}, #{user})")
    public void insertVideo(Video video);

    @Delete("delete from video where id = #{id}")
    public void deleteVideo(int id);
}
