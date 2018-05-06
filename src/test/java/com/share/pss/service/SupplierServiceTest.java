package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Supplier;
import com.share.pss.query.SupplierQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class SupplierServiceTest extends BaseServiceTest{
	@Autowired
	ISupplierService supplierService;
	@Test
	public void testCRUD() throws Exception {
		List<Supplier> all = supplierService.getAll();
		for (Supplier supplier : all) {
			System.out.println(supplier);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		SupplierQuery supplierQuery = new SupplierQuery();
		supplierQuery.setPageSize(15);
		supplierQuery.setCurrentPage(2);
		PageList pageList = supplierService.findByQuery(supplierQuery);
		System.out.println(pageList);
	}
}
