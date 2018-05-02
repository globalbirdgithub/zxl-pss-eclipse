package com.share.pss.domain;
/**
 * 权限模型
 * @author MrZhang
 * @date 2018年3月27日 下午12:54:00
 * @version V1.0
 */
public class Permission {
	// 只要在permission表method里面的出现的Action的方法,要就需要拦截
	// PermissionAction.ALL 拦截所有方法
	// RoleAction.execute 拦截execute方法
	private Long id;
	private String name;
	private String method;// 方法
	private String desc;// 描述

	public Permission() {
	}

	public Permission(Long perId) {
		this.id = perId;
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
