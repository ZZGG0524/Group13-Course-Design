package com.zhuang.group13projectdesign.service;

import com.zhuang.group13projectdesign.bean.News;
import com.zhuang.group13projectdesign.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class NewsService {

//    @Autowired
    @Resource
    private NewsMapper newsMapper;

    //新闻列表
    public Collection<News> listNews() {
        Collection<News> collection = newsMapper.findAllNews();
//        System.out.println(list);
//        model.addAttribute("news", list);
        return collection;
    }

    //新闻详情
    public News newsInfo(int id) {
        News news = newsMapper.getNewsInfoById(id);
        return news;
    }

    //删除新闻
    public void deleteNews(int id) {
        newsMapper.deleteNews(id);
    }

    //发布新闻
    public void insertNews(News news) {
        newsMapper.insertNews(news);
    }

    //修改新闻
    public void updateNews(News news) {
        newsMapper.updateNews(news);
    }
}
