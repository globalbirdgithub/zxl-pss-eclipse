package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.ProductStock;
import com.share.pss.query.ProductStockQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class ProductStockServiceTest extends BaseServiceTest{
	@Autowired
	IProductStockService productStockService;
	@Test
	public void testCRUD() throws Exception {
		List<ProductStock> all = productStockService.getAll();
		for (ProductStock productStock : all) {
			System.out.println(productStock);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		ProductStockQuery productStockQuery = new ProductStockQuery();
		productStockQuery.setPageSize(15);
		productStockQuery.setCurrentPage(2);
		PageList pageList = productStockService.findByQuery(productStockQuery);
		System.out.println(pageList);
	}
}
