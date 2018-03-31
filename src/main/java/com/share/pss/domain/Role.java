package com.share.pss.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色模型
 * 
 * @author MrZhang
 * @date 2018年3月27日 下午12:55:56
 * @version V1.0
 */
public class Role {
	private Long id;
	private String name;
	private Set<Permission> permissions = new HashSet<>();
	private Set<Menu> menus = new HashSet<>();

	public Role() {
	}

	public Role(Long roleId) {
		this.id = roleId;
	}

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

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return name;
	}
}
