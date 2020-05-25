package com.zhuang.group13projectdesign.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //单个文件限制大小
        factory.setMaxFileSize(DataSize.parse("2048MB"));

        //设置上传总数据大小
        factory.setFileSizeThreshold(DataSize.parse("5120MB"));

        return factory.createMultipartConfig();
    }
}
