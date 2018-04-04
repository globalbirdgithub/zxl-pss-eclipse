package com.share.pss.web.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.share.pss.domain.Employee;
import com.share.pss.service.IEmployeeService;
/**登录动作执行类
 * @author MrZhang 
 * @date 2018年3月12日 下午10:46:22
 * @version V1.0
 */
public class LoginAction extends BaseAction{
	private String username;
	private String password;
	private String securityCode;
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	private IEmployeeService employeeService;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	//显示登录页面
	@Override
	public String execute() throws Exception {
		return LOGIN;
	}
	/**处理登录请求validateCheck->check
	 * @return
	 * @throws Exception String
	 * 2018年3月31日下午11:58:39
	 */
	@InputConfig(resultName=LOGIN)
	public String check() throws Exception{
		Employee loginUser = employeeService.findByLoginUser(username, password);
		if(loginUser!=null){
			ActionContext.getContext().getSession().put(USER_IN_SESSION, loginUser);
			return "main";
		}else{
			addActionError("用户名或密码错误");
			return LOGIN;
		}
	}
	/**验证用户名密码必填validateXxx开头的方法会自动在执行Xxx之前执行
	 *  void
	 * 2018年3月31日下午11:54:16
	 */
	public void validateCheck(){
		if(StringUtils.isBlank(username)){
			addFieldError("username", "请输入用户名");
		}
		if(StringUtils.isBlank(password)){
			addFieldError("password", "请输入密码");
		}
		String sessionCode = (String)ActionContext.getContext().getSession().get(SESSION_SECURITY_CODE);
		sessionCode += "";
		if(!sessionCode.equals(securityCode)){
			addFieldError("securityCode","验证码错误");
		}
	}
	//注销
	public String logout() throws Exception{
		//ActionContext.getContext().getSession().clear();
		ServletActionContext.getRequest().getSession().removeAttribute(USER_IN_SESSION);
		return LOGIN;
	}
}
