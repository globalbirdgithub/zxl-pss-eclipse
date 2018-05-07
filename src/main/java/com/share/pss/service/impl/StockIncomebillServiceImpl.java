package com.share.pss.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.el.lang.ELArithmetic.BigDecimalDelegate;

import com.share.pss.domain.Depot;
import com.share.pss.domain.Employee;
import com.share.pss.domain.Product;
import com.share.pss.domain.ProductStock;
import com.share.pss.domain.StockIncomebill;
import com.share.pss.domain.StockIncomebillItem;
import com.share.pss.service.IDepotService;
import com.share.pss.service.IProductStockService;
import com.share.pss.service.IStockIncomebillService;

/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:31:44
 * @version V1.0 业务层实现
 */
public class StockIncomebillServiceImpl extends BaseServiceImpl<StockIncomebill> implements IStockIncomebillService {
	private IDepotService depotService;
	private IProductStockService productStockService;
	public IDepotService getDepotService() {
		return depotService;
	}
	public void setDepotService(IDepotService depotService) {
		this.depotService = depotService;
	}
	public IProductStockService getProductStockService() {
		return productStockService;
	}
	public void setProductStockService(IProductStockService productStockService) {
		this.productStockService = productStockService;
	}

	// 审核测试：仓管部经理(需要事务管理-get/find开头无事务)
	@Override
	public void auding(Long stockIncomebillId, Employee auditor) {
		StockIncomebill stockIncomebill = get(stockIncomebillId);
		// 1.判断能否被审核
		if (stockIncomebill == null) {
			throw new RuntimeException("此入库单不存在！");
		}
		if (stockIncomebill.getStatus() == 1) {
			throw new RuntimeException("此入库单已审核！");
		}
		if (stockIncomebill.getStatus() == -1) {
			throw new RuntimeException("此入库单已作废！");
		}
		// 2.修改审核人/状态
		stockIncomebill.setAuditor(auditor);
		stockIncomebill.setAuditorTime(new Date());
		stockIncomebill.setStatus(1);
		// 如果持久化对象被修改，出现脏数据，此对象如果在事务管理范围内则会被修改s
		saveOrUpdate(stockIncomebill);
		// 3.仓库总金额/数量改变
		Depot depot = stockIncomebill.getDepot();
		depot.setCurrentCapacity(depot.getCurrentCapacity().add(depot.getTotalAmount()));
		depot.setTotalAmount(depot.getTotalAmount().add(depot.getTotalAmount()));
		// 4.添加/修改即时库存表(要求统一仓库，产品唯一)
		String hql = "select o from ProductStock o where o.depot=? and o.product=?";//传递的是对象，查询的时候会自动变成外键
		List<StockIncomebillItem> stockIncomebillItems = stockIncomebill.getStockIncomebillItems();
		for (StockIncomebillItem stockIncomebillItem : stockIncomebillItems) {
			Product product = stockIncomebillItem.getProduct();
			List<ProductStock> list = baseDao.findByHql(hql,depot,product);
			if(list.size()==0){
				ProductStock productStock = new ProductStock();
				productStock.setAmount(stockIncomebillItem.getAmount());
				productStock.setPrice(stockIncomebillItem.getPrice());
				productStock.setNum(stockIncomebillItem.getNum());
				productStock.setIncomeDate(new Date());
				productStock.setDepot(depot);
				productStock.setProduct(product);
				//其他数据有默认值
				productStockService.saveOrUpdate(productStock);
			}else if(list.size()==1){//加权平均(累加金额/累加数量)
				ProductStock productStock = list.get(0);
				BigDecimal amount = productStock.getAmount().add(stockIncomebillItem.getAmount());
				BigDecimal num = productStock.getNum().add(stockIncomebillItem.getNum());
				BigDecimal price = amount.divide(num,2,BigDecimal.ROUND_HALF_EVEN);
				productStock.setAmount(amount);
				productStock.setPrice(price);
				productStock.setNum(num);
				productStock.setIncomeDate(new Date());
				//其他数据有默认值
				productStockService.saveOrUpdate(productStock);
			}else{//list.size()>1
				throw new RuntimeException("同一个仓库产品不唯一！");
			}
		}
	}
}
