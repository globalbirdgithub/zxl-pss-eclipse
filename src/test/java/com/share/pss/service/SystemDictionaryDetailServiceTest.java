package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.SystemDictionaryDetail;
import com.share.pss.query.PageList;
import com.share.pss.query.SystemDictionaryDetailQuery;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class SystemDictionaryDetailServiceTest extends BaseServiceTest{
	@Autowired
	ISystemDictionaryDetailService systemDictionaryDetailService;
	@Test
	public void testCRUD() throws Exception {
		List<SystemDictionaryDetail> all = systemDictionaryDetailService.getAll();
		for (SystemDictionaryDetail systemDictionaryDetail : all) {
			System.out.println(systemDictionaryDetail);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		SystemDictionaryDetailQuery systemDictionaryDetailQuery = new SystemDictionaryDetailQuery();
		systemDictionaryDetailQuery.setPageSize(15);
		systemDictionaryDetailQuery.setCurrentPage(2);
		PageList pageList = systemDictionaryDetailService.findByQuery(systemDictionaryDetailQuery);
		System.out.println(pageList);
	}
	@Test
	public void ss() throws Exception {
		List<SystemDictionaryDetail> allBrands = systemDictionaryDetailService.getAllBrands();
		System.out.println(allBrands.size());
		System.out.println(systemDictionaryDetailService.getAllUnits().size());
	}
}
