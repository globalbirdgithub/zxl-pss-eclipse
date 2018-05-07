package com.share.pss.domain;
import java.math.BigDecimal;
/**
 * 仓库
 * @author MrZhang
 * @date 2018年5月7日 下午12:10:48
 * @version V1.0
 */
public class Depot {
	private Long id;
	private String name;
	private BigDecimal maxCapacity;
	private BigDecimal currentCapacity;
	private BigDecimal totalAmount;

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

	public BigDecimal getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(BigDecimal maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public BigDecimal getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(BigDecimal currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
