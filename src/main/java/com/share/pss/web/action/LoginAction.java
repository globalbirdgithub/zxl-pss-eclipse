package com.share.pss.web.action;

/**登录动作执行类
 * @author MrZhang 
 * @date 2018年3月12日 下午10:46:22
 * @version V1.0
 */
@SuppressWarnings("serial")
public class LoginAction extends BaseAction{
	private String username;
	private String password;
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//跳转登录页面
	@Override
	public String execute() throws Exception {
		if("admin".equals(username) && "123".equals(password)){
			return SUCCESS;
		}else{
			return LOGIN_FAIL;
		}
	}
	
}
