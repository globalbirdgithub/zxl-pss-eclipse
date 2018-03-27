package com.share.pss.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.${domain};
import com.share.pss.query.${domain}Query;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class ${domain}ServiceTest extends BaseServiceTest{
	@Autowired
	I${domain}Service ${domainLower}Service;
	@Test
	public void testCRUD() throws Exception {
		List<${domain}> all = ${domainLower}Service.getAll();
		for (${domain} ${domainLower} : all) {
			System.out.println(${domainLower});
		}
	}
	@Test
	public void testQuery() throws Exception {
		${domain}Query ${domainLower}Query = new ${domain}Query();
		${domainLower}Query.setPageSize(15);
		${domainLower}Query.setCurrentPage(2);
		PageList pageList = ${domainLower}Service.findByQuery(${domainLower}Query);
		System.out.println(pageList);
	}
}
