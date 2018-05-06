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
	//处理登录请求
	@Override
	public Employee findByLoginUser(String username, String password) {
		String hql = "select o from Employee o where o.username=? and o.password=?";
		List<Employee> employees = baseDao.findByHql(hql, username,password);
		if(employees.size()>0){//登录成功
			return employees.get(0);
		}
		return null;
	}
	//获取所有采购员信息
	@Override
	public List<Employee> getAllBuyers() {
		String hql = "select o from Employee o where o.department.name=?";
		List<Employee> buyerList = baseDao.findByHql(hql, "采购部");
		return buyerList;
	}
}
