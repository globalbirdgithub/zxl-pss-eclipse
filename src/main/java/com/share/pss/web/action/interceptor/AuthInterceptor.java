package com.share.pss.web.action.interceptor;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.share.pss.domain.Employee;
import com.share.pss.service.IPermissionService;
import com.share.pss.web.action.BaseAction;
/**权限拦截器
 * @author MrZhang 
 * @date 2018年4月2日 下午5:13:34
 * @version V1.0
 */
@SuppressWarnings("serial")
public class AuthInterceptor extends AbstractInterceptor{
	//struts.xml自动注入属性
	private String excludeActions;
	public void setExcludeActions(String excludeActions) {
		this.excludeActions = excludeActions;
	}
	//Spring自动注入
	private IPermissionService permissionService;
	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	/**@return String struts.xml中视图名称"login"/"main"/...
	 * @param ActionInvocation就是Action的调用者， Interceptor通过ActionInvocation可以完全的
	 * 改变Action行为:不让它执行、改变返回值、甚至可以细颗粒的操作Action的方法.
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String host = ServletActionContext.getRequest().getRemoteAddr();
		//A.对一些不需要拦截的action放行；登录、注册、验证码
		Object action = invocation.getAction();
		String actionName = action.getClass().getSimpleName();
		System.out.println("访问来源IP>>>>>>>>>:"+host);
		System.out.println("访问动作Action>>>>>>>>>:"+actionName);
		if(excludeActions.contains(actionName)){
			return invocation.invoke();
		}
		//B.判断是否已经登录
		Employee loginUser = (Employee)ActionContext.getContext().getSession().get(BaseAction.USER_IN_SESSION);
		if(loginUser==null){
			return Action.LOGIN;
		}
		//C.判断已经登录成功的用户是否具有访问此资源的权限
		String methodName = invocation.getProxy().getMethod();
		//获取所有配置了权限的方法（资源）url
		List<String> allPermissionMethods = permissionService.getAllPermissionMethods();
		String permissionMethod = actionName+"."+methodName;
		String permissionMethods = actionName+".ALL";
		System.out.println("动作ActionName:>>>>>>>>>"+actionName);
		System.out.println("方法MethodName:>>>>>>>>>"+methodName);
		//如果请求访问的url路径-包含于-配置了权限的方法-中，那就表明此url路径访问的资源需要权限判断
		if(allPermissionMethods.contains(permissionMethod)||allPermissionMethods.contains(permissionMethods)){
			System.out.println("--------不需要对当前登录用户进行资源访问拦截");
			//获取当前登录用户所具有的访问资源的权限url
			List<String> allPermissionMethodsByLoginUser = permissionService.findAllPermissionMethodsByLoginUser(loginUser);
			//如果当前用户访问的方法（资源）url包含在他所具有的访问资源的权限url中，就表明有访问权限
			if(allPermissionMethodsByLoginUser.contains(permissionMethod)||allPermissionMethodsByLoginUser.contains(permissionMethods)){
				System.out.println("--------当前用户有权限访问资源");
			}else{
				System.out.println("--------当前用户无权限访问资源");
				return "noAuth";
			}
		}else{
			System.out.println("--------不需要对当前登录用户进行资源访问拦截");
		}
		return invocation.invoke();
	}
}
