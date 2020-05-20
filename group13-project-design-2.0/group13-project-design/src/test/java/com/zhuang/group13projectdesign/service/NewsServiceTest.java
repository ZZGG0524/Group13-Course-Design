package com.zhuang.group13projectdesign.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewsServiceTest {

    @Autowired
    NewsService newsService;

    @Test
    void listNews() {
        System.out.println(newsService.listNews());
    }

    @Test
    void newsInfo() {
        System.out.println(newsService.newsInfo(1));
    }
}