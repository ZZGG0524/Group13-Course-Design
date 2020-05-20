package com.zhuang.group13projectdesign.service;

import com.zhuang.group13projectdesign.bean.Notice;
import com.zhuang.group13projectdesign.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    //公告列表
    public Collection<Notice> listNotice() {
        Collection<Notice> list = noticeMapper.getAllNotice();
        return list;
    }

    //公告详情
    public Notice noticeInfo(int id) {
        Notice notice = noticeMapper.getNoticeInfoById(id);
        return notice;
    }

    //删除公告
    public void deleteNotice(int id) {
        noticeMapper.deleteNotice(id);
    }

    //发布公告
    public void insertNotice(Notice notice) {
        noticeMapper.insertNotice(notice);
    }

    //修改公告
    public void updateNotice(Notice notice) {
        noticeMapper.updateNotice(notice);
    }
}
