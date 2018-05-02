package com.share.pss.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Menu;
import com.share.pss.query.MenuQuery;
import com.share.pss.query.PageList;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class MenuServiceTest extends BaseServiceTest{
	@Autowired
	IMenuService menuService;
	@Test
	public void testCRUD() throws Exception {
		List<Menu> all = menuService.getAll();
		for (Menu menu : all) {
			System.out.println(menu);
		}
		System.out.println(all.size());
	}
	@Test
	public void testQuery() throws Exception {
		MenuQuery menuQuery = new MenuQuery();
		menuQuery.setPageSize(15);
		menuQuery.setCurrentPage(2);
		PageList pageList = menuService.findByQuery(menuQuery);
		System.out.println(pageList);
	}
	//方案一（遗弃）
	@Test
	public void testMenu2() throws Exception {
		List<Menu> parents = menuService.findMenuByLoginUser2(1L);
		for (Menu parent : parents) {
			System.out.println("一级菜单"+parent);
			System.out.println("-----------------");
			List<Menu> children = parent.getChildren();
			for (Menu child : children) {
				System.out.println("二级菜单"+child);
			}
			System.out.println("==================================");
		}
	}
	@Test
	public void testMenu() throws Exception {
		List<Menu> parents = menuService.findMenuByLoginUser(1L);
		for (Menu parent : parents) {
			System.out.println("一级菜单"+parent);
			System.out.println("-----------------");
			List<Menu> children = parent.getChildren();
			for (Menu child : children) {
				System.out.println("二级菜单"+child);
			}
			System.out.println("===================================");
		}
	}
}
