package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.VideoList;
import com.zhuang.group13projectdesign.bean.VideoListQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface VideoListMapper {
    int countByExample(VideoListQuery example);

    int deleteByExample(VideoListQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(VideoList record);

    int insertSelective(VideoList record);

    List<VideoList> selectByExample(VideoListQuery example);

    VideoList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VideoList record, @Param("example") VideoListQuery example);

    int updateByExample(@Param("record") VideoList record, @Param("example") VideoListQuery example);

    int updateByPrimaryKeySelective(VideoList record);

    int updateByPrimaryKey(VideoList record);
}