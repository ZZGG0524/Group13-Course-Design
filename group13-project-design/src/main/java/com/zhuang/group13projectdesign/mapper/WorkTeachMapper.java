package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.WorkTeach;
import com.zhuang.group13projectdesign.bean.WorkTeachQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface WorkTeachMapper {
    int countByExample(WorkTeachQuery example);

    int deleteByExample(WorkTeachQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkTeach record);

    int insertSelective(WorkTeach record);

    List<WorkTeach> selectByExample(WorkTeachQuery example);

    WorkTeach selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkTeach record, @Param("example") WorkTeachQuery example);

    int updateByExample(@Param("record") WorkTeach record, @Param("example") WorkTeachQuery example);

    int updateByPrimaryKeySelective(WorkTeach record);

    int updateByPrimaryKey(WorkTeach record);
}