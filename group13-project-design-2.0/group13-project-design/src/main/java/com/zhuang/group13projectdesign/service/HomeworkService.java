package com.zhuang.group13projectdesign.service;

import com.zhuang.group13projectdesign.bean.Homework;
import com.zhuang.group13projectdesign.mapper.HomeworkMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HomeworkService {

    @Resource
    private HomeworkMapper homeworkMapper;

    public void insertHomework(Homework homework) {
        homeworkMapper.insertHomework(homework);
    }

    public Homework getHomework(int id) {
        Homework homework = homeworkMapper.getHomework(id);
        return homework;
    }
}
