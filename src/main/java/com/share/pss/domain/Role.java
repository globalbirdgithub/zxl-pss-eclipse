package com.share.pss.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色模型
 * @author MrZhang
 * @date 2018年3月27日 下午12:55:56
 * @version V1.0
 */
public class Role {
	private Long id;
	private String name;
	private Set<Permission> permissions = new HashSet<>();

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

}
