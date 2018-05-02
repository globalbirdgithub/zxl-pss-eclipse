package com.share.pss.domain;

public class SystemDictionaryType {
	//系統初始化常量
	public static final String PRODUCT_BRAND = "productBrand";
	public static final String PRODUCT_UNIT = "productUnit";
	private Long id; 
	private String sn;//标记
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
