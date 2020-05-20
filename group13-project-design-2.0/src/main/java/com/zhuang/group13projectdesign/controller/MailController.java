package com.zhuang.group13projectdesign.controller;

import com.zhuang.group13projectdesign.enums.ResultStatus;
import com.zhuang.group13projectdesign.result.ResultMap;
import com.zhuang.group13projectdesign.utils.MailUtil;
import com.zhuang.group13projectdesign.utils.VerifyCodeUtil;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;


/**
 * 发送邮件验证码
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailUtil  mailUtil;
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap  sendEmail(HttpServletRequest request, @ApiParam(name = "data", value = "{\"address\":\"邮箱\"}", required = true)  @RequestBody String data){
        JSONObject json = JSONObject.fromObject(data);
        String address = json.optString("address");
        if(StringUtils.isEmpty(address) || !address.contains("@")){
            return  new ResultMap(ResultStatus.PARAMETERS_MISTASK.getMsg(),ResultMap.ERROR);
        };
        String code = VerifyCodeUtil.generateVerifyCode(6);
        mailUtil.sendEmail(address, code);
        request.getSession().setAttribute("code",code);
        return  new ResultMap(ResultStatus.REQUEST_SUCCESS.getMsg(),ResultMap.SUCCESS);

    }

}
