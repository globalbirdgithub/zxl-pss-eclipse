package com.share.pss.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author MrZhang 
 * @date 2017年11月1日 下午7:29:20
 * @version V1.0 表现层
 */
public abstract class BaseAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public static final String RELOAD = "reload";
	public static final String USER_IN_SESSION = "userInSession";
	public static final String SESSION_SECURITY_CODE = "sessionSecurityCode";
	
	//将部门对象添加到Map栈
	protected void putContext(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}
	//日志
	Logger logger = LoggerFactory.getLogger(getClass());
}
