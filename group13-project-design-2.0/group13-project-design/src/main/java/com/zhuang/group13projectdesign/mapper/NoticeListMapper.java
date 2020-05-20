package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.NoticeList;
import com.zhuang.group13projectdesign.bean.NoticeListQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface NoticeListMapper {
    int countByExample(NoticeListQuery example);

    int deleteByExample(NoticeListQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoticeList record);

    int insertSelective(NoticeList record);

    List<NoticeList> selectByExample(NoticeListQuery example);

    NoticeList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoticeList record, @Param("example") NoticeListQuery example);

    int updateByExample(@Param("record") NoticeList record, @Param("example") NoticeListQuery example);

    int updateByPrimaryKeySelective(NoticeList record);

    int updateByPrimaryKey(NoticeList record);
}