package com.share.pss.domain;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 采购订单
 * 
 * @author MrZhang
 * @date 2018年5月3日 上午9:58:23
 * @version V1.0
 */
public class Purchasebill {
	private Long id;
	private Date vdate;// 交易时间
	private BigDecimal totalAmount;// 总计
	private BigDecimal totalNum;// 总数量
	private Date inputTime = new Date();// 订单填写时间
	private Date auditorTime;// 审核时间
	private Integer status = 0;// 审核状态(0:待审，1：已审，-1：作废)
	private Supplier supplier;// 供应商（多对一，非空）
	private Employee auditor;// 审核员（多对一，可以为空）
	private Employee inputUser;// 订单填写人（多对一，非空）
	private Employee buyer;// 采购员（多对一，非空）
	List<PurchasebillItem> purchasebillItems = new ArrayList<>();// 订单明细

	public Purchasebill() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	
	public Date getInputTime() {
		return inputTime;
	}
	
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Date getAuditorTime() {
		return auditorTime;
	}
	
	public void setAuditorTime(Date auditorTime) {
		this.auditorTime = auditorTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Employee getAuditor() {
		return auditor;
	}

	public void setAuditor(Employee auditor) {
		this.auditor = auditor;
	}

	public Employee getInputUser() {
		return inputUser;
	}

	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}

	public Employee getBuyer() {
		return buyer;
	}

	public void setBuyer(Employee buyer) {
		this.buyer = buyer;
	}

	public List<PurchasebillItem> getPurchasebillItems() {
		return purchasebillItems;
	}

	public void setPurchasebillItems(List<PurchasebillItem> purchasebillItems) {
		this.purchasebillItems = purchasebillItems;
	}

	@Override
	public String toString() {
		return "Purchasebill [id=" + id + ", vdate=" + vdate + ", totalAmount=" + totalAmount + ", totalNum=" + totalNum
				+ ", inputTime=" + inputTime + ", auditorTime=" + auditorTime + ", status=" + status + "]";
	}
}
