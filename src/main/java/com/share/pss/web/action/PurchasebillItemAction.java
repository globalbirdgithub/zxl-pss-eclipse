package com.share.pss.web.action;
import java.util.List;

import com.share.pss.domain.PurchasebillItem;
import com.share.pss.query.PurchasebillItemQuery;
import com.share.pss.service.IPurchasebillItemService;
/**
 * @author MrZhang
 * @date 2017年11月1日 下午11:32:44
 * @version V1.0 表现层Action 访问Action的时候默认Action在栈顶，ModelDriven对应的拦截器检测到PurchasebillItem有值的时候将其压栈到栈顶
 * 													  Preparable对应的拦截器检测到要执行Action中的方法的时候就执行prepare()
 */
public class PurchasebillItemAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	//Spring管理
	private IPurchasebillItemService purchasebillItemService;
	public void setPurchasebillItemService(IPurchasebillItemService purchasebillItemService) {
		this.purchasebillItemService = purchasebillItemService;
	}
	//Struts2管理 需要setter、getter
	private PurchasebillItemQuery baseQuery = new PurchasebillItemQuery();
	public PurchasebillItemQuery getBaseQuery() {
		return baseQuery;
	}
	//第一句hql数据，列表
	@Override
	public String execute() throws Exception {
		List<Object[]> list = purchasebillItemService.findGroupBy(baseQuery);
		putContext("list",list);
		return SUCCESS;
	}
	//获取第二句hql数据，可从当前purcahsebillItem.jsp调用PurcahsebillItemAction中的方法
	public List<PurchasebillItem> findItems(Object groupByValue)throws Exception{
		return purchasebillItemService.findItemds(baseQuery, groupByValue);
	}
	//饼图(json数据)
	public String chart3d() throws Exception {
		return "chart3d";
	}
	public String chart2d() throws Exception {
		return "chart2d";
	}
	public String chartsJson()throws Exception{
		//第三条hql
		List<Object[]> list = purchasebillItemService.findGroupBy2(baseQuery);
		putContext("chartsJson", list);
		return "chartsJsonResult";
	}
}
