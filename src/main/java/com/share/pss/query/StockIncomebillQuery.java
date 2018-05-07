package com.share.pss.query;

import org.apache.commons.lang3.StringUtils;
import com.share.pss.domain.StockIncomebill;

/**
 * @author MrZhang
 * @date 2017年12月13日 上午2:36:19
 * @version V1.0 封装高级查询所需条件 用户名、部门、邮箱
 */
public class StockIncomebillQuery extends BaseQuery {
	// 高级查询参数
	private String name;

	// 将类名StockIncomebill传递给父类的hql查询语句
	public StockIncomebillQuery() {
		super(StockIncomebill.class.getSimpleName());
	}

	// 覆写父类抽象方法；传递具体高级查询参数给父类；
	@Override
	protected void addCondition() {
		if (StringUtils.isNoneBlank(name)) {
			addCondition("o.name like ?", "%" + name + "%");
		}
	}

	// 以下setter/getter提供给Struts2使用
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
