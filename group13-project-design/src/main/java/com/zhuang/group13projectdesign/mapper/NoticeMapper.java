package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.Notice;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

@Mapper
public interface NoticeMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from notice")
    public Collection<Notice> getAllNotice();

    @Select("select * form notice where id= #{id}")
    public Notice getNoticeInfoById(int id);

    @Update("update notice set title=#{title}, context=#{context} where id = #{id}")
    public void updateNotice(Notice notice);

    @Insert("insert into notice(title, context, user) values (#{title}, #{context}, #{user})")
    public int insertNotice(Notice notice);

    @Delete("delete from notice where id=#{id}")
    public int deleteNotice(int id);
}
