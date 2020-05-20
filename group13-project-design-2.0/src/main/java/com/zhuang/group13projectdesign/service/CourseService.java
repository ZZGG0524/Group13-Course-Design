package com.zhuang.group13projectdesign.service;

import com.zhuang.group13projectdesign.bean.Course;
import com.zhuang.group13projectdesign.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    public Course getCourseInfo(int id) {
        return courseMapper.getCourseInfo(id);
    }

    public void insertCourse(Course course) {
        courseMapper.insertCourse(course);
    }

    public void deleteCourse(int id) {
        courseMapper.deleteCourse(id);
    }
}
