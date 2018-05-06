package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Purchasebill;
import com.share.pss.query.PurchasebillQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class PurchasebillServiceTest extends BaseServiceTest{
	@Autowired
	IPurchasebillService purchasebillService;
	@Test
	public void testCRUD() throws Exception {
		List<Purchasebill> all = purchasebillService.getAll();
		for (Purchasebill purchasebill : all) {
			System.out.println(purchasebill);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		PurchasebillQuery purchasebillQuery = new PurchasebillQuery();
		purchasebillQuery.setPageSize(15);
		purchasebillQuery.setCurrentPage(2);
		PageList pageList = purchasebillService.findByQuery(purchasebillQuery);
		System.out.println(pageList);
	}
}
