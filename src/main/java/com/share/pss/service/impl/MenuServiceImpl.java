package com.share.pss.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.share.pss.domain.Employee;
import com.share.pss.domain.Menu;
import com.share.pss.service.IMenuService;

/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:31:44
 * @version V1.0 业务层实现
 */
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
	@Override
	public List<Menu> findMenuByLoginUser(Long loginUserId) {
		String hql = "select distinct m from Employee e join e.roles r join r.menus m where e.id=?";
		List<Menu> menus = baseDao.findByHql(hql, loginUserId);
		List<Menu> parentMenus = new ArrayList<>();//此集合用于装一级菜单
		//父菜单
		for (Menu menu : menus) {
			Menu menuParent = menu.getParent();
			if(menuParent==null){
				parentMenus.add(menu);
			}
		}
		//子菜单
		for (Menu menu : menus) {
			Menu menuParent = menu.getParent();
			if(menuParent!=null){
				for (Menu parentMenu : parentMenus) {
					if(menuParent.getId()==parentMenu.getId()){
						parentMenu.getChildren().add(menu);
					}
				}
			}
		}
		return parentMenus;
	}
	//方案一（遗弃）效率低
	@Override
	public List<Menu> findMenuByLoginUser2(Long loginUserId){
		//查询用户一级菜单
		String hql = "select distinct m from Employee e join e.roles r join r.menus m where e.id=? and m.parent is null";
		List<Menu> parentMenus = baseDao.findByHql(hql, loginUserId);
		System.out.println("P"+parentMenus.size());
		for (Menu parentMenu : parentMenus) {
			//查询用户二级菜单
			hql = "select distinct m from Employee e join e.roles r join r.menus m where e.id=? and m.parent.id=?";
			List<Menu> childrenMenus = baseDao.findByHql(hql, loginUserId,parentMenu.getId());
			System.out.println("C"+childrenMenus.size());
			parentMenu.setChildren(childrenMenus);
		}
		return parentMenus;
	}
	
}
