package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.WorkList;
import com.zhuang.group13projectdesign.bean.WorkListQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface WorkListMapper {
    int countByExample(WorkListQuery example);

    int deleteByExample(WorkListQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkList record);

    int insertSelective(WorkList record);

    List<WorkList> selectByExample(WorkListQuery example);

    WorkList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkList record, @Param("example") WorkListQuery example);

    int updateByExample(@Param("record") WorkList record, @Param("example") WorkListQuery example);

    int updateByPrimaryKeySelective(WorkList record);

    int updateByPrimaryKey(WorkList record);
}