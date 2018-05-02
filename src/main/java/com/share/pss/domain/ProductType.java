package com.share.pss.domain;

import org.apache.struts2.json.annotations.JSON;

/**产品类型模型
 * @author MrZhang 
 * @date 2018年4月30日 上午10:06:17
 * @version V1.0
 */
public class ProductType {
	private Long id;
	private String name;
	private String descs;
	private ProductType parentProductType;
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
	@JSON(serialize=false)//当前属性不输出
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
		this.descs = descs;
	}
	@JSON(serialize=false)//当前属性不输出
	public ProductType getParentProductType() {
		return parentProductType;
	}
	public void setParentProductType(ProductType parentProductType) {
		this.parentProductType = parentProductType;
	}
	
}
