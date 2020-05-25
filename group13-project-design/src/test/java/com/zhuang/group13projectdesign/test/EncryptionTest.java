package com.zhuang.group13projectdesign.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
public class EncryptionTest {

    @Autowired
    StringEncryptor encryptor;

    @Test
    public void getPass() {
        String url = encryptor.encrypt("jdbc:mysql://localhost:3306/group13?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("root");
        System.out.println("url:"+url);
        System.out.println("name:"+name);
        System.out.println("password:"+password);
    }
}
