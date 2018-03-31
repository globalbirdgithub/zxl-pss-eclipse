package com.share.pss.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单模型
 * 
 * @author MrZhang
 * @date 2018年3月30日 上午10:39:33
 * @version V1.0
 */
public class Menu {
	private Long id;
	private String name;
	// 菜单url地址
	private String url;
	// 菜单图标
	private String icon;
	// 父菜单（单向多对一）
	private Menu parent;
	// 作为父菜单时的子菜单（当需要处理存在一个父菜单不需要所有子菜单的情况下，就不能将此属性交给hibernate管理）
	private List<Menu> children = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", url=" + url + ", icon=" + icon + ", parent=" + parent
				+ ",]";
	}

}
