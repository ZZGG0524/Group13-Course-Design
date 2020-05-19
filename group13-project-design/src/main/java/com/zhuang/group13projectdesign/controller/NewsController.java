package com.zhuang.group13projectdesign.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuang.group13projectdesign.bean.News;
import com.zhuang.group13projectdesign.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    //新闻列表，学生、老师和管理员皆可查看
//    @GetMapping(value = "/news")
//    public String updateNews(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
//        PageHelper.startPage(pageNum,2);
//        List<News> list = newsService.listNews();
//        PageInfo<News> pageInfo = new PageInfo<News>(list);
//        model.addAttribute("pageInfo", pageInfo);
//        return "news";
//    }

    @GetMapping(value = "/news")
    public String getNews(Model model) {
        Collection<News> collection = newsService.listNews();
        model.addAttribute("news", collection);
        return "news";
    }

    //新闻详情，学生、老师和管理员皆可查看
    @GetMapping(value = "/news/{id}")
    public String newsInfo(@PathVariable("id") int id, Model model) {
        News news = newsService.newsInfo(id);
        model.addAttribute("newsInfo", news);
        return "newsInfo";
    }

    //来到修改页面，回显数据，，，老师或者管理员可修改
    @GetMapping(value = "/updateNews/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        News news = newsService.newsInfo(id);
        model.addAttribute("updateNewsInfo", news);
        return "updateNews";
    }

    //修改新闻，，，老师或者管理员可修改
    @PutMapping(value = "updateNews/{id}")
    public String updateNews(@PathVariable("id") int id, @RequestParam("title") String title, @RequestParam("context") String context) {
        News news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setContext(context);
        newsService.updateNews(news);
        return "news";
    }
    //发布新闻，，，老师或者管理员可发布

    @PostMapping(value = "/insertNews/{user}")
    public String publicNews(@PathVariable("user") String username, @RequestParam("title") String title,
                             @RequestParam("context") String context, HttpSession session) {
        News news = new News();
        news.setTitle(title);
        news.setContext(context);
        news.setUser(username);
        newsService.insertNews(news);
        return "news";
    }
    //删除新闻，，，老师或者管理员可删除
    @DeleteMapping(value = "deleteNews/{id}")
    public String deleteNews(@PathVariable("id") int id) {
        newsService.deleteNews(id);
        return "news";
    }
}
