package com.share.pss.domain;
import java.math.BigDecimal;

/**
 * 采购订单明细
 * @author MrZhang
 * @date 2018年5月3日 上午9:58:48
 * @version V1.0
 */
public class PurchasebillItem {
	private Long id;
	private BigDecimal price;// 产品价格
	private BigDecimal num;// 产品数量
	private BigDecimal amount;// 小计
	private String descs;//备注
	private Product product;// 产品（多对一，非空）
	private Purchasebill purchasebill;// 订单（组合关系，非空）

	public PurchasebillItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchasebill getPurchasebill() {
		return purchasebill;
	}

	public void setPurchasebill(Purchasebill purchasebill) {
		this.purchasebill = purchasebill;
	}

	@Override
	public String toString() {
		return "PurchasebillItem [id=" + id + ", price=" + price + ", num=" + num + ", amount=" + amount + ", descs="
				+ descs + "]";
	}
}
