package com.share.pss.service;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.share.pss.domain.Depot;
import com.share.pss.domain.Employee;
import com.share.pss.domain.Product;
import com.share.pss.domain.StockIncomebill;
import com.share.pss.domain.StockIncomebillItem;
import com.share.pss.domain.Supplier;
/**
 * @author MrZhang 
 * @date 2017年10月31日 下午4:31:21
 * @version V1.0 测试类
 */
public class StockIncomebillServiceTest extends BaseServiceTest{
	@Autowired
	IStockIncomebillService stockIncomebillService;
	@Test
	public void save() throws Exception {
		Depot depot = new Depot();
		depot.setId(1L);
		Employee inputUser = new Employee();
		inputUser.setId(1L);
		Supplier supplier = new Supplier();
		supplier.setId(1L);
		Product product = new Product();
		product.setId(1L);
		//一个入库单
		StockIncomebill stockIncomebill = new StockIncomebill();
		stockIncomebill.setDepot(depot);
		stockIncomebill.setInputUser(inputUser);
		stockIncomebill.setKeeper(inputUser);
		stockIncomebill.setSupplier(supplier);
		stockIncomebill.setVdate(new Date());
		//两个入库明细
		List<StockIncomebillItem> items = new ArrayList<>();
		StockIncomebillItem item = new StockIncomebillItem();
		item.setDescs("备注1");
		item.setNum(new BigDecimal(1));
		item.setPrice(new BigDecimal(1));
		item.setProduct(product);
		items.add(item);
		StockIncomebillItem item2 = new StockIncomebillItem();
		item2.setDescs("备注2");
		item2.setNum(new BigDecimal(1));
		item2.setPrice(new BigDecimal(1));
		item2.setProduct(product);
		items.add(item2);
		
		BigDecimal totalAmount = new BigDecimal(0);
		BigDecimal totalNum = new BigDecimal(0);
		//一到多建立关联
		for (StockIncomebillItem stockIncomebillItem : items) {
			//多到一建立关联
			stockIncomebillItem.setStockIncomebill(stockIncomebill);
			//总计，总数
			stockIncomebillItem.setAmount(stockIncomebillItem.getPrice().multiply(stockIncomebillItem.getNum()));
			totalAmount = totalAmount.add(stockIncomebillItem.getAmount());
			totalNum = totalNum.add(stockIncomebillItem.getNum());
		}
		stockIncomebill.setTotalAmount(totalAmount);
		stockIncomebill.setTotalNum(totalNum);
		stockIncomebill.setStockIncomebillItems(items);
		stockIncomebillService.saveOrUpdate(stockIncomebill);
	}
	//审核测试：仓管部经理(需要事务管理)
	/*1.判断能否被审核
	 *2.修改审核人/状态
	 *3.仓库总金额/数量改变
	 *4.添加/修改即时库存表
	 */
	@Test
	public void auding() throws Exception {
		Employee  auditor = new Employee();
		auditor.setId(1L);
		stockIncomebillService.auding(1L,auditor);
	}
}
