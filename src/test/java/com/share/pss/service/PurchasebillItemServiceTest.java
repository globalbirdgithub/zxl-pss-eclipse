package com.share.pss.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.dao.IBaseDao;
import com.share.pss.domain.PurchasebillItem;
import com.share.pss.query.PageList;
import com.share.pss.query.PurchasebillItemQuery;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class PurchasebillItemServiceTest extends BaseServiceTest{
	@Autowired
	IPurchasebillItemService purchasebillItemService;
	@Autowired
	IBaseDao baseDao;
	//无查询条件
	@Test
	public void search() throws Exception {
		String groupBy = "o.purchasebill.supplier.name";
		groupBy = "o.purchasebill.buyer.username";
//		groupBy = "month(o.purchasebill.vdate)";
		String hql = "select "+groupBy+",count(o) from PurchasebillItem o group by "+groupBy;
		List<Object[]> list = baseDao.findByHql(hql);//list：分组条件，相同数据条数-->[东莞供应商, 4]
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
			hql = "select o from PurchasebillItem o where "+groupBy+"=?";
			List<PurchasebillItem> list2 = baseDao.findByHql(hql,objects[0]);
			for (PurchasebillItem purchasebillItem : list2) {
				System.out.println(purchasebillItem);
			}
		}
	}
	//有查询条件
	@Test
	public void search2() throws Exception {
		String groupBy = "o.purchasebill.supplier.name";
		String where = "where o.purchasebill.status=0";
		String hql = "select "+groupBy+",count(o) from PurchasebillItem o "+where+" group by "+groupBy;
		List<Object[]> list = baseDao.findByHql(hql);//list：分组条件，相同数据条数-->[东莞供应商, 4]先决条件
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
			hql = "select o from PurchasebillItem o where "+groupBy+"=?"+ where.replaceAll("where","and");
			List<PurchasebillItem> list2 = baseDao.findByHql(hql,objects[0]);
			for (PurchasebillItem purchasebillItem : list2) {
				System.out.println(purchasebillItem);
			}
		}
	}
	//封装后有查询条件
	@Test
	public void search3() throws Exception {
		PurchasebillItemQuery purchasebillItemQuery = new PurchasebillItemQuery();
//		purchasebillItemQuery.setGroupBy("o.purchasebill.buyer.username");
//		purchasebillItemQuery.setGroupBy("month(o.purchasebill.vdate)");
		purchasebillItemQuery.setStatus(1);
		purchasebillItemQuery.setEndDate(new Date());
		List<Object[]> list = purchasebillItemService.findGroupBy(purchasebillItemQuery);
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
//			hql = "select o from PurchasebillItem o where "+groupBy+"=?"+ where.replaceAll("where","and");
//			List<PurchasebillItem> list2 = baseDao.findByHql(hql,objects[0]);
//			for (PurchasebillItem purchasebillItem : list2) {
//				System.out.println(purchasebillItem);
//			}
		}
	}
	//封装后有查询条件
	@Test
	public void search4() throws Exception {
		PurchasebillItemQuery purchasebillItemQuery = new PurchasebillItemQuery();
		purchasebillItemQuery.setStatus(1);
		purchasebillItemQuery.setEndDate(new Date());
		List<Object[]> list = purchasebillItemService.findGroupBy(purchasebillItemQuery);
		for (Object[] objects : list) {
			System.out.println("第一次查询结果作为第二次条件："+Arrays.toString(objects));
			List<PurchasebillItem> findItemds = purchasebillItemService.findItemds(purchasebillItemQuery,objects[0]);
			for (PurchasebillItem purchasebillItem : findItemds) {
				System.out.println(purchasebillItem);
			}
		}
	}
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
