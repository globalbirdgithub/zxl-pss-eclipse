package com.share.pss.web.action;

import com.share.pss.service.IMenuService;

/**
 * @author MrZhang 
 * @date 2017年11月3日 上午1:55:51
 * @version V1.0 主页面跳转
 */
public class MainAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private IMenuService menuService;
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	//后台主页
	@Override
	public String execute() throws Exception {
		putContext("menus", menuService.findMenuByLoginUser(1L));
		return SUCCESS;
	}
	//主页框架右侧页面访问
	public String right() throws Exception {
		return "right";
	}
}
