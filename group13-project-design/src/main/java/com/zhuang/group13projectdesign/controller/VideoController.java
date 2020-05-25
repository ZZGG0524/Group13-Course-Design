package com.zhuang.group13projectdesign.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuang.group13projectdesign.bean.Video;
import com.zhuang.group13projectdesign.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    //查看视频列表，学生，老师，管理员皆可
    @GetMapping(value = "/videoList")
    public String getAllVideo(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 2);
        List<Video> list = videoService.getAllVideo();
        PageInfo<Video> pageInfo = new PageInfo<Video>(list);
        model.addAttribute("video", pageInfo.getList());
        model.addAttribute("count", pageInfo.getPages());
        model.addAttribute("pageInfo", pageInfo);
        return "videoList";
    }

    //播放视频，学生，老师，管理员皆可
    @GetMapping(value = "/videoPlay/{id}")
    public String videoPlay(@PathVariable("id") int id, Model model) {
        Video video = videoService.getVideo(id);
//        System.out.println(id+"            "+video.getTitle()+"              "+video.getPath());
        model.addAttribute("title", video.getTitle());
        model.addAttribute("path", video.getPath());

//        return "videoPlay";
        return "class";
    }

    //添加课程
    @PostMapping(value = "/addCourse/{id}/{studentName}")
    public String addCourse(@PathVariable("id") int id, @PathVariable("studentName") String studentName) {

        Video video = new Video();
        video.setStudentName(studentName);
        video.setId(id);
        videoService.addCourse(video);
        return null;
    }

    //获取学生所有课程
    @GetMapping(value = "/getStudentCourse/{studentName}")
    public String getStudentCourse(@PathVariable("studentName") String studentName) {
        List<Video> list = videoService.getStudentCourse(studentName);
        return null;
    }

    //获取上传者所有课程
    @GetMapping(value = "/getUserCourse/{user}")
    public String getUserCourse(@PathVariable("user") String user) {
        List<Video> list = videoService.getUserCourse(user);
        return null;
    }

    @DeleteMapping(value = "/deleteVideo/{id}")
    public String deleteVideo(@PathVariable("id") int id) {
        videoService.deleteVideo(id);
        return null;
    }

    //上传视频，老师
    @PostMapping(value = "/uploadVideo/{user}")
    public String uploadVideo(@PathVariable("user") String username,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("courseName") String courseName,
                              @RequestParam("details") String details) {
        String fileName = file.getOriginalFilename();
//        fileName = fileName+ UUID.randomUUID();
        String path = "E:/upload/video";
        File dest = new File(path+"/"+fileName);


        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            Video video = new Video();
            video.setTitle(fileName);
            video.setCourseName(courseName);
            video.setDetails(details);
            video.setPath(path);
            video.setUser(username);
            videoService.insertVideo(video);
            return "videoList";
        }catch (IllegalStateException e) {
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }
}
