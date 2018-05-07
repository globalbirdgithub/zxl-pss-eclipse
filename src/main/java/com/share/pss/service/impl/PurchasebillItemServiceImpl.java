package com.share.pss.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.share.pss.domain.PurchasebillItem;
import com.share.pss.query.PurchasebillItemQuery;
import com.share.pss.service.IPurchasebillItemService;

/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:31:44
 * @version V1.0 业务层实现
 */
public class PurchasebillItemServiceImpl extends BaseServiceImpl<PurchasebillItem> implements IPurchasebillItemService {
	// 报表中第一个分组查询（分组）
	@Override
	public List<Object[]> findGroupBy(PurchasebillItemQuery purchasebillItemQuery) {
		String groupBy = purchasebillItemQuery.getGroupBy();
		List<Object> paramList = purchasebillItemQuery.getParamList();
		if (paramList.size() == 0) {
			String hql = "select " + groupBy + ",count(o) from PurchasebillItem o group by " + groupBy;
			return baseDao.findByHql(hql);
		} else {
			String where = purchasebillItemQuery.getWhereHql();
			String hql = "select " + groupBy + ",count(o) from PurchasebillItem o " + where + " group by " + groupBy;
			Object[] objects = paramList.toArray();
			return baseDao.findByHql(hql, objects);
		}
	}

	@Override
	public List<PurchasebillItem> findItemds(PurchasebillItemQuery purchasebillItemQuery, Object groupByValue) {
		String groupBy = purchasebillItemQuery.getGroupBy();
		List<Object> paramList = purchasebillItemQuery.getParamList();
		if (paramList.size() == 0) {
			String hql = "select o from PurchasebillItem o where " + groupBy + "=?";
			return baseDao.findByHql(hql, groupByValue);
		} else {
			String where = purchasebillItemQuery.getWhereHql();
			String hql = "select o from PurchasebillItem o where " + groupBy + "=?" + where.replaceAll("where", "and");
			List list = new ArrayList<>();
			list.add(groupByValue);
			list.addAll(paramList);
			Object[] objects = list.toArray();
			return baseDao.findByHql(hql, objects);
		}
	}

	@Override
	public List<Object[]> findGroupBy2(PurchasebillItemQuery purchasebillItemQuery) {
		String groupBy = purchasebillItemQuery.getGroupBy();
		List<Object> paramList = purchasebillItemQuery.getParamList();
		if (paramList.size() == 0) {
			String hql = "select " + groupBy + ",sum(o.amount) from PurchasebillItem o group by " + groupBy;
			return baseDao.findByHql(hql);
		} else {
			String where = purchasebillItemQuery.getWhereHql();
			String hql = "select " + groupBy + ",sum(o.amount) from PurchasebillItem o " + where + " group by " + groupBy;
			Object[] objects = paramList.toArray();
			return baseDao.findByHql(hql, objects);
		}
	}

}
