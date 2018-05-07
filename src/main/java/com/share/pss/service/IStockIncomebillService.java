package com.share.pss.service;

import com.share.pss.domain.Employee;
import com.share.pss.domain.StockIncomebill;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午12:28:27
 * @version V1.0 业务层接口
 */
public interface IStockIncomebillService extends IBaseService<StockIncomebill>{
	//审核测试：仓管部经理(需要事务管理-get/find开头无事务)
	/*1.判断能否被审核
	 *2.修改审核人/状态
	 *3.仓库总金额/数量改变
	 *4.添加/修改即时库存表
	 */
	void auding(Long stockIncomebillId,Employee auditor);
}
