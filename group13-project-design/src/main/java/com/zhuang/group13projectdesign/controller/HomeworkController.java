package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.Homework;
import com.zhuang.group13projectdesign.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;


    //学生上传作业，管理员可查看
    @PostMapping(value = "/uploadHomework/{user}")
    public String uploadHomework(@PathVariable("user") String username,
                             @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();

        String path = "E:/upload/homework";

        File dest = new File(path+"/"+fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            Homework homework = new Homework();
            homework.setUser(username);
            homework.setTitle(username+"_"+fileName);
            homework.setPath(path);
            homeworkService.insertHomework(homework);
            return "homework";
        }catch (IllegalStateException e) {
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }


    //老师下载作业
    @GetMapping(value = "/downloadHomework/{id}")
    public String downloadHomework(@PathVariable("id") int id,
                               HttpServletResponse response) throws UnsupportedEncodingException {
        Homework homework = homeworkService.getHomework(id);
        String fileName = homework.getTitle();
        String path = homework.getPath();

        File file = new File(path+"/"+fileName);

        if (file.exists()) {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            //response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName,"UTF-8"));

            // 设置下载后的文件名以及header
            response.addHeader("Content-disposition", "attchment;fileName="+fileName);

            byte[] buffer = new byte[1024];

            FileInputStream fis = null;

            BufferedInputStream bis = null;

            OutputStream os = null;

            try {
                os= response.getOutputStream();
                fis= new FileInputStream(file);
                bis= new BufferedInputStream(fis);
                int i = bis.read(buffer);

                while (i!= -1) {
                    os.write(buffer);
                    i= bis.read(buffer);
                }
                bis.close();
                fis.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "main";
    }
}
