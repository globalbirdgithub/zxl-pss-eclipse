package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Permission;
import com.share.pss.query.PermissionQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class PermissionServiceTest extends BaseServiceTest{
	@Autowired
	IPermissionService permissionService;
	@Test
	public void testCRUD() throws Exception {
		List<Permission> all = permissionService.getAll();
		for (Permission permission : all) {
			System.out.println(permission);
		}
	}
	@Test
	public void testQuery() throws Exception {
		PermissionQuery permissionQuery = new PermissionQuery();
		permissionQuery.setPageSize(15);
		permissionQuery.setCurrentPage(2);
		PageList pageList = permissionService.findByQuery(permissionQuery);
		System.out.println(pageList);
	}
}
