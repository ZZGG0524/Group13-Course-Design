package com.zhuang.group13projectdesign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

//这个类忽略不用看

//@Controller
public class FileController {


    @PostMapping(value = "/file/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        //获取原始名字
        String fileName = file.getOriginalFilename();

        //获取后缀名
        String lastName = fileName.substring(fileName.lastIndexOf("."));

        //文件保存位置
        String filePath = "E:/upload";

        //文件重命名，防止重复
//        fileName = filePath + UUID.randomUUID() + fileName;

        File dest = new File(fileName);

        System.out.println(fileName);

//        if(!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }

        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @GetMapping(value = "/file/download")
    public void download(HttpServletResponse response) throws Exception {

        //下载地址
        File file = new File("E:\\upload\\a.txt");

        // 创建输入对象
        FileInputStream fileInputStream = new FileInputStream(file);

        // 设置相关格式
        response.setContentType("application/force-download");

        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attchment;fileName="+"a.txt");

        //创建输出对象
        OutputStream outputStream = response.getOutputStream();

        //常规操作
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len= fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0 , len);
        }
        fileInputStream.close();
    }
}
