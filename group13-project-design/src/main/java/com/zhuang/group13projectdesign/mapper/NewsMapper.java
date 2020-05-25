package com.zhuang.group13projectdesign.mapper;

import com.zhuang.group13projectdesign.bean.News;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

@Mapper
public interface NewsMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from news")
    public Collection<News> findAllNews();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from news")
    public List<News> getAllNews();

    @Select("select * from news where id=#{id}")
    public News getNewsInfoById(int id);

    @Update("update news set title=#{title}, context=#{context} where id = #{id}")
    public void updateNews(News news);

    @Insert("insert into news(title, context, user) values (#{title}, #{context}, #{user})")
    public void insertNews(News news);

    @Delete("delete from news where id=#{id}")
    public void deleteNews(int id);
}
