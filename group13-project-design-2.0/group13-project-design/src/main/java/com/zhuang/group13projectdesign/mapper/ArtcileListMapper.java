package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.ArtcileList;
import com.zhuang.group13projectdesign.bean.ArtcileListQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ArtcileListMapper {
    int countByExample(ArtcileListQuery example);

    int deleteByExample(ArtcileListQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArtcileList record);

    int insertSelective(ArtcileList record);

    List<ArtcileList> selectByExample(ArtcileListQuery example);

    ArtcileList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArtcileList record, @Param("example") ArtcileListQuery example);

    int updateByExample(@Param("record") ArtcileList record, @Param("example") ArtcileListQuery example);

    int updateByPrimaryKeySelective(ArtcileList record);

    int updateByPrimaryKey(ArtcileList record);
}