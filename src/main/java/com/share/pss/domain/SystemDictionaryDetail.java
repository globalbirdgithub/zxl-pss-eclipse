package com.share.pss.domain;

public class SystemDictionaryDetail {
	private Long id;
	private String name;
	private SystemDictionaryType systemDictionaryType;
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
	public SystemDictionaryType getSystemDictionaryType() {
		return systemDictionaryType;
	}
	public void setSystemDictionaryType(SystemDictionaryType systemDictionaryType) {
		this.systemDictionaryType = systemDictionaryType;
	}
	
}
