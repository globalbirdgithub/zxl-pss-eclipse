package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Depot;
import com.share.pss.query.DepotQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class DepotServiceTest extends BaseServiceTest{
	@Autowired
	IDepotService depotService;
	@Test
	public void testCRUD() throws Exception {
		List<Depot> all = depotService.getAll();
		for (Depot depot : all) {
			System.out.println(depot);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		DepotQuery depotQuery = new DepotQuery();
		depotQuery.setPageSize(15);
		depotQuery.setCurrentPage(2);
		PageList pageList = depotService.findByQuery(depotQuery);
		System.out.println(pageList);
	}
}
