package com.share.pss.web.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.share.pss.domain.Employee;
import com.share.pss.domain.Purchasebill;
import com.share.pss.domain.PurchasebillItem;
import com.share.pss.query.PageList;
import com.share.pss.query.PurchasebillQuery;
import com.share.pss.service.IEmployeeService;
import com.share.pss.service.IPurchasebillService;
import com.share.pss.service.ISupplierService;
/**
 * @author MrZhang
 * @date 2017年11月1日 下午11:32:44
 * @version V1.0 表现层Action 访问Action的时候默认Action在栈顶，ModelDriven对应的拦截器检测到Purchasebill有值的时候将其压栈到栈顶
 * 													  Preparable对应的拦截器检测到要执行Action中的方法的时候就执行prepare()
 */
public class PurchasebillAction extends CRUDAction<Purchasebill>{
	private static final long serialVersionUID = 1L;
	//Spring管理
	private IPurchasebillService purchasebillService;
	public void setPurchasebillService(IPurchasebillService purchasebillService) {
		this.purchasebillService = purchasebillService;
	}
	private ISupplierService supplierService;
	public void setSupplierService(ISupplierService supplierService) {
		this.supplierService = supplierService;
	}
	private IEmployeeService employeeService;
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	//Struts2管理 通过值栈(List/Map)向前台提供数据，
	//List栈需要：属性+getter；Map栈需要：ActionContext.getContext.put(key,value)
	private PageList pageList;
	public PageList getPageList() {
		return pageList;
	}
	//Struts2管理 需要setter、getter
	private PurchasebillQuery baseQuery = new PurchasebillQuery();
	public PurchasebillQuery getBaseQuery() {
		return baseQuery;
	}
	//Struts2管理 用于接收和回显前台数据，需要它在栈顶时才放到栈顶
	private Purchasebill purchasebill;
	
	//====================================Action方法=========================================
	//获取所有
	@Override
	protected void list() {
		this.pageList = purchasebillService.findByQuery(baseQuery);
	}
	//新建/修改
	@Override
	protected void inputt() {
		/*留空*/
		putContext("allSuppliers", supplierService.getAll());
		putContext("allBuyers", employeeService.getAllBuyers());
	}
	//保存
	@Override
	protected void savee() {
		//如果是新增用户，则新增后跳转到最后一页,将参数传递到list方法中
		if(id==null){
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
			//第一次保存的时候就设置输入人员
			Employee inputUser = (Employee)ServletActionContext.getRequest().getSession().getAttribute(USER_IN_SESSION);
			purchasebill.setInputUser(inputUser);
		}
		BigDecimal totalAmount = new BigDecimal(0);
		BigDecimal totalNum = new BigDecimal(0);
		//一到多建立关联
		List<PurchasebillItem> purchasebillItems = purchasebill.getPurchasebillItems();
		for (PurchasebillItem purchasebillItem : purchasebillItems) {
			//多到一建立关联
			purchasebillItem.setPurchasebill(purchasebill);
			//总计，总数
			purchasebillItem.setAmount(purchasebillItem.getPrice().multiply(purchasebillItem.getNum()));
			totalAmount = totalAmount.add(purchasebillItem.getAmount());
			totalNum = totalNum.add(purchasebillItem.getNum());
		}
		purchasebill.setTotalAmount(totalAmount);
		purchasebill.setTotalNum(totalNum);
		purchasebillService.saveOrUpdate(purchasebill);
	}
	//ajax删除
	@Override
	protected void deletee() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {
			if(id!=null){
				purchasebillService.delete(id);
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
	protected Purchasebill getModell() {
		return purchasebill;
	}
	//=========================prepare拦截器对应的类PrepareInterceptor检测方法前缀prefix='prepare'通过反射调用=======
	@Override
	protected void prepareInputt(){
		if(id!=null){
			purchasebill = purchasebillService.get(id);//修改需要回显否则不需要(这时会压栈)
			System.out.println(purchasebill);
		}else{
			purchasebill = new Purchasebill();
			purchasebill.setVdate(new Date());
		}
	}
	@Override
	protected void prepareSavee() {
		if(id==null){
			purchasebill = new Purchasebill();
		}else{
			purchasebill = purchasebillService.get(id);
			purchasebill.setSupplier(null);
			purchasebill.setBuyer(null);
			purchasebill.getPurchasebillItems().clear();//****
		}
	}
	@Override
	protected void prepareDeletee() {
	
	}
	//=======================审核功能========================
	//返回ajax完成审核功能，返回json：审核状态status，审核人auditor名字，审核时间auditorTime
	public void confirm()throws Exception{
		Purchasebill confirmPurchasebill = purchasebillService.get(id);
		Integer status = 1;
		Employee auditor = (Employee)ServletActionContext.getRequest().getSession().getAttribute(USER_IN_SESSION);
		Date auditorTime = new Date();
		confirmPurchasebill.setStatus(status);
		confirmPurchasebill.setAuditor(auditor);
		confirmPurchasebill.setAuditorTime(auditorTime);
		purchasebillService.saveOrUpdate(confirmPurchasebill);
		Map<String,Object> confirmMap = new HashMap<>();
		confirmMap.put("status", status);
		confirmMap.put("auditor", auditor.getUsername());
		confirmMap.put("auditorDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(auditorTime));
		ObjectMapper objectMapper = new ObjectMapper();
		String confirmMapJsonString = objectMapper.writeValueAsString(confirmMap);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(confirmMapJsonString);
	}
	//遗弃订单
	public void abandon()throws Exception{
		Purchasebill abandonPurchasebill = purchasebillService.get(id);
		Employee auditor = (Employee)ServletActionContext.getRequest().getSession().getAttribute(USER_IN_SESSION);
		Integer status = -1;
		Date auditorTime = new Date();
		abandonPurchasebill.setStatus(status);
		abandonPurchasebill.setAuditor(auditor);
		abandonPurchasebill.setAuditorTime(auditorTime);
		purchasebillService.saveOrUpdate(abandonPurchasebill);
		Map<String,Object> abandonMap = new HashMap<>();
		abandonMap.put("status", status);
		abandonMap.put("auditor", auditor.getUsername());
		abandonMap.put("auditorDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(auditorTime));
		ObjectMapper objectMapper = new ObjectMapper();
		String abandonMapJsonString = objectMapper.writeValueAsString(abandonMap);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(abandonMapJsonString);
	}
}
 