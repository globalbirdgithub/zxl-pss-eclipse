package com.share.pss.service.impl;

import java.util.List;

import com.share.pss.domain.Employee;
import com.share.pss.service.IEmployeeService;

/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:31:44
 * @version V1.0 业务层实现
 */
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {
	@SuppressWarnings("unchecked")
	@Override
	public boolean findByUsername(String username) {
		String hql = "select count(o) from Employee o where o.username=?";
		List<Long> list = baseDao.findByHql(hql, username);
		if(list.get(0)>0){
			return false;
		}
		return true;
	}
}
