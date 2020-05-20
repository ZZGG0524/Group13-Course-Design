package com.zhuang.group13projectdesign.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件工具类
 */
@EnableConfigurationProperties
@Component
public class MailUtil {




    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;



    //邮箱验证码
    public  void sendEmail(String emailaddress,String code){
        // 不要使用SimpleEmail,会出现乱码问题
        HtmlEmail email = new HtmlEmail();
        try {
            // 这里是SMTP发送服务器的名字：，普通qq号只能是smtp.qq.com ；
            email.setHostName(host);
            //设置需要鉴权端口
            email.setSmtpPort(465);
            //开启 SSL 加密
            email.setSSLOnConnect(true);
            // 字符编码集的设置
            email.setCharset("utf-8");
            // 收件人的邮箱
            email.addTo(emailaddress);
            // 发送人的邮箱
            email.setFrom(emailaddress, emailaddress);
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和得到的授权码
            email.setAuthentication(username, password);
            email.setSubject(code);
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg("欢迎注册，您的验证码为："+code);
            // 发送
            email.send();
            System.out.println ( "邮件发送成功!" );
        } catch (EmailException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println ( "邮件发送失败!" );
        }
    }
}