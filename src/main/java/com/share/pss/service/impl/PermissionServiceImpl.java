package com.share.pss.service.impl;
import java.util.List;
import com.share.pss.domain.Employee;
import com.share.pss.domain.Permission;
import com.share.pss.service.IPermissionService;
/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:31:44
 * @version V1.0 业务层实现
 */
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {
	@Override
	public List<String> getAllPermissionMethods() {
		String hql = "select p.method from Permission p";
		List<String> allPermissionMethods = baseDao.findByHql(hql);
		return allPermissionMethods;
	}
	@Override
	public List<String> findAllPermissionMethodsByLoginUser(Employee loginUser) {
		String hql = "select distinct p.method from Employee e join e.roles r join r.permissions p where e.id=?";
		List<String> allPermissionMethodsByLoginUser = baseDao.findByHql(hql, loginUser.getId());
		return allPermissionMethodsByLoginUser;
	}
}
