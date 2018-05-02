package com.share.pss.service;

import java.util.List;

import com.share.pss.domain.Menu;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午12:28:27
 * @version V1.0 业务层接口
 */
public interface IMenuService extends IBaseService<Menu>{
	//通过用户id查找菜单列表
	List<Menu> findMenuByLoginUser(Long loginUserId);
	//方案一（遗弃）
	List<Menu> findMenuByLoginUser2(Long loginUserId);
}
