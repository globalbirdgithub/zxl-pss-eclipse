 package com.share.pss.domain;
import java.math.BigDecimal;

/**产品模型
 * @author MrZhang 
 * @date 2018年4月30日 上午10:06:05
 * @version V1.0
 */
public class Product {
    private Long id;
    private String name;
    private String color;
    private String pic;
    private String smallPic;
    private BigDecimal costPrice;
    private BigDecimal salePrice;
    private ProductType productType;
    private SystemDictionaryDetail unit;
    private SystemDictionaryDetail brand;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public SystemDictionaryDetail getUnit() {
		return unit;
	}
	public void setUnit(SystemDictionaryDetail unit) {
		this.unit = unit;
	}
	public SystemDictionaryDetail getBrand() {
		return brand;
	}
	public void setBrand(SystemDictionaryDetail brand) {
		this.brand = brand;
	}
}
