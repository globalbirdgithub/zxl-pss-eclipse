package com.share.pss.query;

import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import com.share.pss.domain.PurchasebillItem;

/**
 * @author MrZhang
 * @date 2017年12月13日 上午2:36:19
 * @version V1.0 封装高级查询所需条件 用户名、部门、邮箱
 */
public class PurchasebillItemQuery extends BaseQuery {
	// 高级查询参数
	private Integer status;
	private Date beginDate;
	private Date endDate;
	// 分组查询条件(默认按照供应商名称)
	private String groupBy = "o.purchasebill.supplier.name";

	// 将类名Purchasebill传递给父类的hql查询语句
	public PurchasebillItemQuery() {
		super(PurchasebillItem.class.getSimpleName());
	}

	// 覆写父类抽象方法；传递具体高级查询参数给父类；
	@Override
	protected void addCondition() {
		if (status != null && status != -2) {
			addCondition("o.purchasebill.status=?", status);
		}
		if (beginDate != null) {
			addCondition("o.purchasebill.vdate>=?", beginDate);
		}
		if (endDate != null) {
			Date date = DateUtils.addDays(endDate, 1);
			addCondition("o.purchasebill.vdate<?", date);
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

}
