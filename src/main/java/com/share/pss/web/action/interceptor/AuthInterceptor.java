package com.share.pss.web.action.interceptor;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.share.pss.domain.Employee;
import com.share.pss.web.action.BaseAction;
/**权限拦截器
 * @author MrZhang 
 * @date 2018年4月2日 下午5:13:34
 * @version V1.0
 */
@SuppressWarnings("serial")
public class AuthInterceptor extends AbstractInterceptor{
	private String excludeActions;
	public void setExcludeActions(String excludeActions) {
		this.excludeActions = excludeActions;
	}
	/**@return String struts.xml中视图名称"login"/"main"/...
	 * @param ActionInvocation就是Action的调用者， Interceptor通过ActionInvocation可以完全的
	 * 改变Action行为:不让它执行、改变返回值、甚至可以细颗粒的操作Action的方法.
	 * @see A.对一些不需要拦截的action放行；登录、注册、验证码
	 * 		B.判断是否已经登录
	 * 		C.判断是否具有访问此资源的权限
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String host = ServletActionContext.getRequest().getRemoteAddr();
		System.out.println("访问IP:"+host);
		Object action = invocation.getAction();
		String actionName = action.getClass().getSimpleName();
		if(excludeActions.contains(actionName)){
			return invocation.invoke();
		}
		Employee loginUser = (Employee)ActionContext.getContext().getSession().get(BaseAction.USER_IN_SESSION);
		if(loginUser==null){
			return Action.LOGIN;
		}
		return invocation.invoke();
	}
}
