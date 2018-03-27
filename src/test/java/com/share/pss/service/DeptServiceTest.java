package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Dept;
import com.share.pss.query.DeptQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class DeptServiceTest extends BaseServiceTest{
	@Autowired
	IDeptService deptService;
	@Test
	public void testCRUD() throws Exception {
		List<Dept> all = deptService.getAll();
		for (Dept dept : all) {
			System.out.println(dept);
		}
	}
	@Test
	public void testQuery() throws Exception {
		DeptQuery deptQuery = new DeptQuery();
		deptQuery.setPageSize(15);
		deptQuery.setCurrentPage(2);
		PageList pageList = deptService.findByQuery(deptQuery);
		System.out.println(pageList);
	}
}
