package com.zhuang.group13projectdesign.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class URLConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置虚拟路径，handler为前台访问的路径，locations为files相对应的本地路径
        registry.addResourceHandler("/videoPlay/video/**").addResourceLocations("file:/E:/upload/video/");
    }
}
