package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.Video;
import com.zhuang.group13projectdesign.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    //查看视频列表，学生，老师，管理员皆可
    @GetMapping(value = "/videoList")
    public String getAllVideo(Model model) {
        model.addAttribute("video", videoService.getAllVideo());
        return "videoList";
    }

    //播放视频，学生，老师，管理员皆可
    @GetMapping(value = "/videoPlay/{id}")
    public String videoPlay(@PathVariable("id") int id, Model model) {
        Video video = videoService.getVideo(id);
//        System.out.println(id+"            "+video.getTitle()+"              "+video.getPath());
        model.addAttribute("title", video.getTitle());
        model.addAttribute("path", video.getPath());

        return "videoPlay";
    }

    //上传视频，老师
    @PostMapping(value = "/uploadVideo/{user}")
    public String uploadVideo(@PathVariable("user") String username,
                              @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String path = "D:/upload/video";
        File dest = new File(path+"/"+fileName);


        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            Video video = new Video();
            video.setTitle(fileName);
            video.setPath(path);
            video.setUser(username);
            videoService.insertVideo(video);
            return "main";
        }catch (IllegalStateException e) {
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }
}
