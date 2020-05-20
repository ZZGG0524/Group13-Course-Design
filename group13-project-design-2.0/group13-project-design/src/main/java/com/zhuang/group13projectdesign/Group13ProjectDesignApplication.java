package com.zhuang.group13projectdesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@MapperScan
public class Group13ProjectDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(Group13ProjectDesignApplication.class, args);
    }

}
