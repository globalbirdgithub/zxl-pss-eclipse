package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.ProductType;
import com.share.pss.query.ProductTypeQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class ProductTypeServiceTest extends BaseServiceTest{
	@Autowired
	IProductTypeService productTypeService;
	@Test
	public void testCRUD() throws Exception {
		List<ProductType> all = productTypeService.getAll();
		for (ProductType productType : all) {
			System.out.println(productType);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		ProductTypeQuery productTypeQuery = new ProductTypeQuery();
		productTypeQuery.setPageSize(15);
		productTypeQuery.setCurrentPage(2);
		PageList pageList = productTypeService.findByQuery(productTypeQuery);
		System.out.println(pageList);
	}
}
