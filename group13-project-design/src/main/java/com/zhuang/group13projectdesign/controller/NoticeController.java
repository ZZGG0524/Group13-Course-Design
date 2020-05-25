package com.zhuang.group13projectdesign.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuang.group13projectdesign.bean.News;
import com.zhuang.group13projectdesign.bean.Notice;
import com.zhuang.group13projectdesign.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //公告列表，学生老师皆可查看
    @GetMapping(value = "/notice")
    public String getNotice(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum,2);
        List<Notice> list = noticeService.getAllNotice();
        PageInfo<Notice> pageInfo = new PageInfo<Notice>(list);
        model.addAttribute("notice", pageInfo.getList());
        model.addAttribute("count", pageInfo.getPages());
        model.addAttribute("pageInfo", pageInfo);
        return "notice1";
    }

    //公告详情，学生老师皆可查看
    @GetMapping(value = "/notice/{id}")
    public String getNoticeInfo(@PathVariable("id") int id, Notice notice, Model model) {
        notice = noticeService.noticeInfo(id);
        model.addAttribute("noticeInfo", notice);
        return "notice_detail";
    }

    //来到修改页面，回显数据，，，老师和管理员可修改
    @GetMapping(value = "/updateNotice/{id}")
    public String toEditPage(@PathVariable("id") int id, Model model) {
        Notice notice = noticeService.noticeInfo(id);
        model.addAttribute("updateNoticeInfo", notice);
        return null;
    }

    //修改公告，，，老师和管理员可修改
    @PutMapping(value = "/updateNotice/{id}")
    public String updateNotice(@PathVariable("id") int id, @RequestParam("title") String title, @RequestParam("context") String context) {
        Notice notice = new Notice();
        notice.setId(id);
        notice.setTitle(title);
        notice.setContext(context);
        noticeService.updateNotice(notice);
        return "notice1";
    }
    //发布公告，，，老师和管理员可发布
    @PostMapping(value = "/insertNotice/{user}")
    public String publicNotice(@PathVariable("user") String username, @RequestParam("title") String title,
                             @RequestParam("context") String context, HttpSession session) {
        Notice notice = new Notice();
        notice.setUser(username);
        notice.setTitle(title);
        notice.setContext(context);
        noticeService.insertNotice(notice);
        return "notice1";
    }

    //删除公告，，，老师和管理员可删除
    @GetMapping(value = "/deleteNotice/{id}")
    public String deleteNotice(@PathVariable("id") int id) {
        noticeService.deleteNotice(id);
        return "notice1";
    }
}
