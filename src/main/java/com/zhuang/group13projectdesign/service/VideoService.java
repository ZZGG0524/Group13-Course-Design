package com.zhuang.group13projectdesign.service;

import com.zhuang.group13projectdesign.bean.Video;
import com.zhuang.group13projectdesign.mapper.VideoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class VideoService {

    @Resource
    private VideoMapper videoMapper;

    //获取所有视频信息
    public List<Video> getAllVideo() {
        List<Video> list = videoMapper.getAllVideo();
        return list;
    }

    //将视频文件名和地址上传到数据库
    public void insertVideo(Video video) {
        videoMapper.insertVideo(video);
    }

    //获取视频文件名和地址
    public Video getVideo(int id) {
        Video video = videoMapper.getVideo(id);
        return video;
    }
}
