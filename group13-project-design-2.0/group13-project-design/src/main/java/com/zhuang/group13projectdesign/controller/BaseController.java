package com.zhuang.group13projectdesign.controller;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class BaseController {
  
  /**
   * 获取请求域
   * @return
   */
  public HttpServletRequest getRequeset(){
	  return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }
  /**
   * 获取相应域
   * @return
   */
  public HttpServletResponse getResponse(){
	  return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
  }
  /**
   * 保存会话属性
   * @param key
   * @param value
   */
  public abstract void setSessionAttr(String key,Object value);

    /**
     * 移除回话属性
     * @param key
     */
  public abstract void removeSessionAttr(String key);

    /**
     * 注销会话
     */
  public abstract void destroySession();
  
  /**
   * 获取会话属性
   * @param key
   */
  public abstract Object getSessionAttr(String key);
  


}
