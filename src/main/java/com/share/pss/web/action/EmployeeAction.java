package com.share.pss.web.action;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.share.pss.domain.Department;
import com.share.pss.domain.Employee;
import com.share.pss.query.EmployeeQuery;
import com.share.pss.query.PageList;
import com.share.pss.service.IDepartmentService;
import com.share.pss.service.IEmployeeService;
/**
 * @author MrZhang
 * @date 2017年11月1日 下午11:32:44
 * @version V1.0 表现层Action 访问Action的时候默认Action在栈顶，ModelDriven对应的拦截器检测到Employee有值的时候将其压栈到栈顶
 * 													  Preparable对应的拦截器检测到要执行Action中的方法的时候就执行prepare()
 */
public class EmployeeAction extends CRUDAction<Employee>{
	private static final long serialVersionUID = 1L;
	//Spring管理
	private IEmployeeService employeeService;
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	private IDepartmentService departmentService;
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	//Struts2管理 通过值栈(List/Map)向前台提供数据，
	//List栈需要：属性+getter；Map栈需要：ActionContext.getContext.put(key,value)
	private PageList pageList;
	public PageList getPageList() {
		return pageList;
	}
	//Struts2管理 需要setter、getter
	private EmployeeQuery employeeQuery = new EmployeeQuery();
	public EmployeeQuery getEmployeeQuery() {
		return employeeQuery;
	}
	//Struts2管理 用于接收和回显前台数据，需要它在栈顶时才放到栈顶
	private Employee employee;
	
	//====================================Action方法=========================================
	//获取所有
	@Override
	protected void list() {
		this.pageList = employeeService.findByQuery(employeeQuery);
		putContext("allDepts", departmentService.getAll());
	}
	//新建/修改
	@Override
	protected void inputt() {
		/*留空*/
		putContext("allDepts", departmentService.getAll());
	}
	//保存
	@Override
	protected void savee() {
		Department department = employee.getDepartment();
		if(department!=null && department.getId()==-1L){
			employee.setDepartment(null);
		}
		employeeService.saveOrUpdate(employee);
	}
	//删除
	@Override
	protected void deletee(){
		if(id!=null){
			employeeService.delete(id);
		}
	}
	//接收前台用户名
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	//修改或新增时检测用户名是否重复
	public String checkUsername ()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		if(id==null){														//新增
			boolean findByUsername = employeeService.findByUsername(username);
			writer.print("{\"valid\":"+findByUsername+"}");
		}else{																//修改
			Employee dbEmployee = employeeService.get(id);
			if(dbEmployee==null){                  							 //用户手动修改了id或者当前id被删除
				writer.print("{\"valid\":false,\"message\":\"用户不存在\"}");
				return NONE;												 //立即返回
			}
			String dbUsername = dbEmployee.getUsername();
			if(username.equals(dbUsername)){
				writer.print("{\"valid\":true}");
			}else{
				boolean findByUsername = employeeService.findByUsername(username);
				writer.print("{\"valid\":"+findByUsername+"}");
			}
		}
		return NONE;
	}
	//==================================实现ModelDriven和Prepareable接口解决属性丢失问题========================
	@Override
	protected void preparee() {
	}
	@Override
	protected Employee getModell() {
		return employee;
	}
	//=========================prepare拦截器对应的类PrepareInterceptor检测方法前缀prefix='prepare'通过反射调用=======
	@Override
	protected void prepareInputt(){
		if(id!=null){
			employee = employeeService.get(id);//修改需要回显否则不需要(这时会压栈)
		}
	}
	@Override
	protected void prepareSavee() {
		if(id==null){
			employee = new Employee();
		}else{
			employee = employeeService.get(id);
		}
		employee.setDepartment(null);
	}
	@Override
	protected void prepareDeletee() {
	}
	
}
 