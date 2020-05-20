package com.zhuang.group13projectdesign.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;



/**
 * 基于shiro会话管理的控制器
 * @author jason-zhang
 *
 */
public class ShiroSessionController extends BaseController {

	/**
	 * 获取会话
	 * @return
	 */
	private Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}

	@Override
	public void setSessionAttr(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	@Override
	public void removeSessionAttr(String key) {
		getSession().removeAttribute(key);
	}

	@Override
	public void destroySession() {
        SecurityUtils.getSubject().logout();
	}

	@Override
	public Object getSessionAttr(String key) {
		return getSession().getAttribute(key);
	}



}
