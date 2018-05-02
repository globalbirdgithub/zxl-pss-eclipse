package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Product;
import com.share.pss.query.ProductQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class ProductServiceTest extends BaseServiceTest{
	@Autowired
	IProductService productService;
	@Test
	public void testCRUD() throws Exception {
		List<Product> all = productService.getAll();
		for (Product product : all) {
			System.out.println(product);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		ProductQuery productQuery = new ProductQuery();
		productQuery.setPageSize(15);
		productQuery.setCurrentPage(2);
		PageList pageList = productService.findByQuery(productQuery);
		System.out.println(pageList);
	}
}
