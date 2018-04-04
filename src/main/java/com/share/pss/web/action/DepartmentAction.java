package com.share.pss.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.share.pss.domain.Department;
import com.share.pss.query.DepartmentQuery;
import com.share.pss.query.PageList;
import com.share.pss.service.IDepartmentService;
/**
 * @author MrZhang 
 * @date 2017年12月21日 下午2:29:48
 * @version V1.0 部门表现层
 */
public class DepartmentAction extends CRUDAction<Department>{
	private static final long serialVersionUID = 1L;
	//Spring管理
	private IDepartmentService departmentService;
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	//Struts2管理(后台向前台)
	private PageList pageList;
	public PageList getPageList() {
		return pageList;
	}
	//Struts2管理(后台向前台提供)
	private DepartmentQuery baseQuery = new DepartmentQuery();
	
	public DepartmentQuery getBaseQuery() {
		return baseQuery;
	}
	//值栈
	private Department department;
	//=====================Action方法======================
	@Override
	protected void list() {
		this.pageList = departmentService.findByQuery(baseQuery);
	}

	@Override
	protected void inputt() {
		/*留空*/
	}

	@Override
	protected void savee() {
		if(id==null){
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
		}
		departmentService.saveOrUpdate(department);
	}

	@Override
	protected void deletee() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {
			if(id!=null){
				departmentService.delete(id);
				printWriter.print("{\"success\":true,\"msg\":\"删除成功\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			printWriter.print("{\"success\":false,\"msg\":\"删除失败"+e.getMessage()+"\"}");
		}
	}
	//==================ModelDriven/Preparable=====================
	@Override
	protected void preparee() {
	}

	@Override
	protected Department getModell() {
		return department;
	}

	@Override
	protected void prepareInputt() {
		if(id!=null){
			department = departmentService.get(id);
		}
	}

	@Override
	protected void prepareSavee() {
		if(id==null){
			department = new Department();
		}else{
			department = departmentService.get(id);
		}
	}

	@Override
	protected void prepareDeletee() {
		
	}
	
}
