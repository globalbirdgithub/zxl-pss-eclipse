package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.SystemDictionaryType;
import com.share.pss.query.SystemDictionaryTypeQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class SystemDictionaryTypeServiceTest extends BaseServiceTest{
	@Autowired
	ISystemDictionaryTypeService systemDictionaryTypeService;
	@Test
	public void testCRUD() throws Exception {
		List<SystemDictionaryType> all = systemDictionaryTypeService.getAll();
		for (SystemDictionaryType systemDictionaryType : all) {
			System.out.println(systemDictionaryType);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		SystemDictionaryTypeQuery systemDictionaryTypeQuery = new SystemDictionaryTypeQuery();
		systemDictionaryTypeQuery.setPageSize(15);
		systemDictionaryTypeQuery.setCurrentPage(2);
		PageList pageList = systemDictionaryTypeService.findByQuery(systemDictionaryTypeQuery);
		System.out.println(pageList);
	}
}
