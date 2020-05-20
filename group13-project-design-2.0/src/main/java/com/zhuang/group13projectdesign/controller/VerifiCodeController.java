package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.utils.ImageVerificationCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Controller
public class VerifiCodeController {


    /**
     * 生成图形验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("getVerifiCode")
    @ResponseBody
    public void getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageVerificationCode ivc = new ImageVerificationCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();  //获取验证码
        request.getSession().setAttribute("text", ivc.getText()); //将验证码的文本存在session中
        ivc.output(image, response.getOutputStream());//将验证码图片响应给客户端
    }





    /**
     * 从session中获取真正的验证码
     * 验证使用
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/authentication")
    @ResponseBody
    public String Login_authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String session_vcode=(String) request.getSession().getAttribute("text");    //从session中获取真正的验证码
        return session_vcode;
    }



}
