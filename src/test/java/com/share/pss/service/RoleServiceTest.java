package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Role;
import com.share.pss.query.RoleQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class RoleServiceTest extends BaseServiceTest{
	@Autowired
	IRoleService roleService;
	@Test
	public void testCRUD() throws Exception {
		List<Role> all = roleService.getAll();
		for (Role role : all) {
			System.out.println(role);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		RoleQuery roleQuery = new RoleQuery();
		roleQuery.setPageSize(15);
		roleQuery.setCurrentPage(2);
		PageList pageList = roleService.findByQuery(roleQuery);
		System.out.println(pageList);
	}
}
