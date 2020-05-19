package com.zhuang.group13projectdesign.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zhuang.group13projectdesign.bean.User;
import com.zhuang.group13projectdesign.bean.UserList;
import com.zhuang.group13projectdesign.enums.ResultStatus;
import com.zhuang.group13projectdesign.mapper.UserListMapper;
import com.zhuang.group13projectdesign.result.ResultMap;
import com.zhuang.group13projectdesign.service.UserService;
import com.zhuang.group13projectdesign.utils.MD5;
import com.zhuang.group13projectdesign.utils.MyUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserListMapper userListMapper;

   /* @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session,
                        HttpServletRequest request) {

        User user = userService.login(username);
        System.out.println(user.getId()+"      "+user.getUsername()+"       "+user.getPassword());
        if (user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "main";
        } else {
            map.put("msg", "用户名或密码错误！");
            return "login";
        }
    }*/
    @RequestMapping(value="/regist", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "邮箱注册", notes = "邮箱注册", httpMethod = "POST", response = ResultMap.class)
    public ResultMap regist(HttpServletRequest request,@ApiParam(name = "data", value = "{\"code\":\"验证码\",\"name\":\"名称\",\"password\":\"\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject jsonObject = JSONObject.fromObject(data);
            String code = jsonObject.optString("code");
            if(MyUtil.isEmpty(code)){
                map.setState(ResultMap.ERROR);
                map.setMsg("code不能为空");
                return map;
            }
            String name = jsonObject.optString("name");
            if(MyUtil.isEmpty(name)){
                map.setState(ResultMap.ERROR);
                map.setMsg("name不能为空");
                return map;
            }
            String password = jsonObject.optString("password");
            if(MyUtil.isEmpty(password)){
                map.setState(ResultMap.ERROR);
                map.setMsg("password不能为空");
                return map;
            }
            String _code = request.getSession().getAttribute("code").toString();
            if(!code.equals(_code)){
                map.setMsg("验证码错误");
                map.setState(ResultMap.ERROR);
                return map;
            }
            UserList userList = new UserList();
            userList.setId(null);
            userList.setName(name);
            userList.setPassword(MD5.get(password));
            userListMapper.insertSelective(userList);
            map.setState(ResultMap.SUCCESS);
            map.setMsg("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.setMsg("异常");
            map.setState(ResultMap.ERROR);
        }
        return map;
    }


    @RequestMapping(value="/regists", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "普通注册", notes = "普通注册", httpMethod = "POST", response = ResultMap.class)
    public ResultMap regists(HttpServletRequest request,@ApiParam(name = "data", value = "{\"name\":\"名称\",\"password\":\"\"}", required = true)  @RequestBody String data){
        ResultMap map = new ResultMap();
        try {
            JSONObject jsonObject = JSONObject.fromObject(data);
            String name = jsonObject.optString("name");
            if(MyUtil.isEmpty(name)){
                map.setState(ResultMap.ERROR);
                map.setMsg("name不能为空");
                return map;
            }
            String password = jsonObject.optString("password");
            if(MyUtil.isEmpty(password)){
                map.setState(ResultMap.ERROR);
                map.setMsg("password不能为空");
                return map;
            }
            UserList userList = new UserList();
            userList.setId(null);
            userList.setName(name);
            userList.setPassword(MD5.get(password));
            userList.setCreatetime(new Date());
            userListMapper.insertSelective(userList);
            map.setState(ResultMap.SUCCESS);
            map.setMsg("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.setMsg("异常");
            map.setState(ResultMap.ERROR);
        }
        return map;
    }




    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST", response = ResultMap.class)
    public ResultMap login(@ApiParam(name = "data", value = "{\"name\":\"验证码\",\"password\":\"密码\"}", required = true)  @RequestBody String data) {
      ResultMap map = new ResultMap();
        JSONObject json= JSONObject.fromObject(data);
      String name = json.optString("name");
      if(MyUtil.isEmpty(name)){
        map.setState(ResultMap.ERROR);
        map.setMsg("name不能为空");
        return map;
      }
      String password = json.optString("password");
      if(MyUtil.isEmpty(password)){
          map.setState(ResultMap.ERROR);
          map.setMsg("password不能为空");
          return map;
      }

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            map.setMsg(ResultMap.ERROR);
            map.setMsg(ResultStatus.PARAMETERS_MISTASK.getMsg());
            return  map;
        }
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, MD5.get(password));
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            map.setMsg(ResultStatus.REQUEST_SUCCESS.getMsg());
            map.setState(ResultMap.SUCCESS);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            map.setMsg(ResultMap.ERROR);
            map.setMsg(ResultStatus.AUTH_NOT_ALLOWED.getMsg());
            return map;
        } catch (AuthorizationException e) {
            e.printStackTrace();
            map.setMsg(ResultMap.ERROR);
            map.setMsg(ResultStatus.AUTH_NOT_ALLOWED.getMsg());
        }
        return map;
    }


    @RequestMapping(value="/addRole", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "给用户添加角色", notes = "给用户添加角色", httpMethod = "POST", response = ResultMap.class)
    public ResultMap addRole(@ApiParam(name = "data", value = "{\"userid\":\"用户id\",\"role\":\"role\"}", required = true)  @RequestBody String data) {
        ResultMap map = new ResultMap();
        JSONObject jsonObject = JSONObject.fromObject(data);
        String userid = jsonObject.optString("userid");
        String role = jsonObject.optString("role");
        UserList userList = new UserList();
        userList.setId(Integer.valueOf(userid));
        userList.setRole(role);
        userListMapper.updateByPrimaryKeySelective(userList);
        map.setState(ResultMap.SUCCESS);
        map.setMsg("添加成功");

        return map;
    }

    //注解验角色和权限
    @RequiresRoles("teacher")
    @RequiresPermissions("upload_video")
    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "index!";
    }



}
