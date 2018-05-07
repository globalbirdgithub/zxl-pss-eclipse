package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.StockIncomebillItem;
import com.share.pss.query.StockIncomebillItemQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class StockIncomebillItemServiceTest extends BaseServiceTest{
	@Autowired
	IStockIncomebillItemService stockIncomebillItemService;
	@Test
	public void testCRUD() throws Exception {
		List<StockIncomebillItem> all = stockIncomebillItemService.getAll();
		for (StockIncomebillItem stockIncomebillItem : all) {
			System.out.println(stockIncomebillItem);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		StockIncomebillItemQuery stockIncomebillItemQuery = new StockIncomebillItemQuery();
		stockIncomebillItemQuery.setPageSize(15);
		stockIncomebillItemQuery.setCurrentPage(2);
		PageList pageList = stockIncomebillItemService.findByQuery(stockIncomebillItemQuery);
		System.out.println(pageList);
	}
}
