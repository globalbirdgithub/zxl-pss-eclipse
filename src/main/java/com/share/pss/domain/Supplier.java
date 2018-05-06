package com.share.pss.domain;
/**
 * 供应商模型
 * @author MrZhang
 * @date 2018年5月3日 上午10:14:54
 * @version V1.0
 */
public class Supplier {
	private Long id;
	private String name;

	public Supplier() {
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
}
