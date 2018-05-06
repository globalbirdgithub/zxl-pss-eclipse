package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.PurchasebillItem;
import com.share.pss.query.PurchasebillItemQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class PurchasebillItemServiceTest extends BaseServiceTest{
	@Autowired
	IPurchasebillItemService purchasebillItemService;
	@Test
	public void testCRUD() throws Exception {
		List<PurchasebillItem> all = purchasebillItemService.getAll();
		for (PurchasebillItem purchasebillItem : all) {
			System.out.println(purchasebillItem);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		PurchasebillItemQuery purchasebillItemQuery = new PurchasebillItemQuery();
		purchasebillItemQuery.setPageSize(15);
		purchasebillItemQuery.setCurrentPage(2);
		PageList pageList = purchasebillItemService.findByQuery(purchasebillItemQuery);
		System.out.println(pageList);
	}
}
