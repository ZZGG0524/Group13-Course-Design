package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.bean.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

//这个类忽略不用看

//@Controller
public class FileUploadController {

    @PostMapping(value = "/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()){
            return "false";
        }

        String fileName = file.getOriginalFilename();

        int size = (int) file.getSize();

        System.out.println(fileName+ " ---->  "+ size);

        String path = "E:/upload";

        File dest = new File(path+"/"+fileName);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            return "true";
        }catch (IllegalStateException e) {
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

    @GetMapping(value = "/download")
    public void fileDownload(HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = "sql.txt";
        String filePath = "E:/upload";

        File file = new File(filePath+"/"+fileName);

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
    }
}
