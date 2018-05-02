package com.share.pss.service.impl;

import java.util.List;

import com.share.pss.domain.Department;
import com.share.pss.service.IDepartmentService;

/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:31:44
 * @version V1.0 业务层实现
 */
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {
	@Override
	public Department findeptByName(String name) {
		String hql = "select o from Department o where o.name=?";
		List<Department> list = baseDao.findByHql(hql, name);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
