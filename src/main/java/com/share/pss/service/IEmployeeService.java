package com.share.pss.service;

import com.share.pss.domain.Employee;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午12:28:27
 * @version V1.0 业务层接口
 */
public interface IEmployeeService extends IBaseService<Employee>{
	//用户名检查方法
	boolean findByUsername(String username);
	//处理登录请求
	Employee findByLoginUser(String username, String password);
}
