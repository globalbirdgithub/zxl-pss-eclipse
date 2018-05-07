package com.share.pss.service;

import java.util.List;

import com.share.pss.domain.PurchasebillItem;
import com.share.pss.query.PurchasebillItemQuery;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午12:28:27
 * @version V1.0 业务层接口
 */
public interface IPurchasebillItemService extends IBaseService<PurchasebillItem>{
	//报表中第一个分组查询（分组）
	List<Object[]> findGroupBy(PurchasebillItemQuery purchasebillItemQuery);
	//报表饼图中第一个分组查询（分组）
	List<Object[]> findGroupBy2(PurchasebillItemQuery purchasebillItemQuery);
	//需要第一条hql查询结果
	List<PurchasebillItem> findItemds(PurchasebillItemQuery purchasebillItemQuery,Object groupByValue);
}
