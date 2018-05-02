package com.share.pss.service;
import java.util.List;
import com.share.pss.domain.Employee;
import com.share.pss.domain.Permission;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午12:28:27
 * @version V1.0 业务层接口
 */
public interface IPermissionService extends IBaseService<Permission>{
	//获取所有配置了权限的方法（资源）url
	List<String> getAllPermissionMethods();
	//获取当前登录用户所具有的访问资源的权限url
	List<String> findAllPermissionMethodsByLoginUser(Employee loginUser);
}
