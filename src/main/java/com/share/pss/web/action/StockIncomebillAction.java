package com.share.pss.web.action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.share.pss.domain.Employee;
import com.share.pss.domain.StockIncomebill;
import com.share.pss.query.StockIncomebillQuery;
import com.share.pss.query.PageList;
import com.share.pss.service.IStockIncomebillService;
/**
 * @author MrZhang
 * @date 2017年11月1日 下午11:32:44
 * @version V1.0 表现层Action 访问Action的时候默认Action在栈顶，ModelDriven对应的拦截器检测到StockIncomebill有值的时候将其压栈到栈顶
 * 													  Preparable对应的拦截器检测到要执行Action中的方法的时候就执行prepare()
 */
public class StockIncomebillAction extends CRUDAction<StockIncomebill>{
	private static final long serialVersionUID = 1L;
	//Spring管理
	private IStockIncomebillService stockIncomebillService;
	public void setStockIncomebillService(IStockIncomebillService stockIncomebillService) {
		this.stockIncomebillService = stockIncomebillService;
	}
	//Struts2管理 通过值栈(List/Map)向前台提供数据，
	//List栈需要：属性+getter；Map栈需要：ActionContext.getContext.put(key,value)
	private PageList pageList;
	public PageList getPageList() {
		return pageList;
	}
	//Struts2管理 需要setter、getter
	private StockIncomebillQuery baseQuery = new StockIncomebillQuery();
	public StockIncomebillQuery getBaseQuery() {
		return baseQuery;
	}
	//Struts2管理 用于接收和回显前台数据，需要它在栈顶时才放到栈顶
	private StockIncomebill stockIncomebill;
	//================审核===========
	public String auding() throws Exception{
		try {
			Employee auditor = (Employee)ServletActionContext.getRequest().getSession().getAttribute(USER_IN_SESSION);
			stockIncomebillService.auding(id, auditor);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return RELOAD;
	}
	//====================================Action方法=========================================
	//获取所有
	@Override
	protected void list() {
		this.pageList = stockIncomebillService.findByQuery(baseQuery);
	}
	//新建/修改
	@Override
	protected void inputt() {
		/*留空*/
	}
	//保存
	@Override
	protected void savee() {
		//如果是新增用户，则新增后跳转到最后一页,将参数传递到list方法中
		if(id==null){
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
		}
		stockIncomebillService.saveOrUpdate(stockIncomebill);
	}
	//ajax删除
	@Override
	protected void deletee() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {
			if(id!=null){
				stockIncomebillService.delete(id);
				printWriter.print("{\"success\":true,\"msg\":\"删除成功\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			printWriter.print("{\"success\":false,\"msg\":\"删除失败："+e.getMessage()+"\"}");
		}
	}
	//==================================实现ModelDriven和Prepareable接口解决属性丢失问题========================
	@Override
	protected void preparee() {
	}
	@Override
	protected StockIncomebill getModell() {
		return stockIncomebill;
	}
	//=========================prepare拦截器对应的类PrepareInterceptor检测方法前缀prefix='prepare'通过反射调用=======
	@Override
	protected void prepareInputt(){
		if(id!=null){
			stockIncomebill = stockIncomebillService.get(id);//修改需要回显否则不需要(这时会压栈)
		}
	}
	@Override
	protected void prepareSavee() {
		if(id==null){
			stockIncomebill = new StockIncomebill();
		}else{
			stockIncomebill = stockIncomebillService.get(id);
		}
	}
	@Override
	protected void prepareDeletee() {
	}
	
}
 