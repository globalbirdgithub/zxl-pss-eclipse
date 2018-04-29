package com.share.pss.web.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.share.pss.domain.Department;
import com.share.pss.domain.Employee;
import com.share.pss.service.IDepartmentService;
import com.share.pss.service.IEmployeeService;

/**Excel文件导入执行类
 * @author MrZhang 
 * @date 2018年4月29日 上午10:22:37
 * @version V1.0
 */
public class ExcelImportAction extends BaseAction{
	private static final long serialVersionUID = -965130581138131944L;
	private File upload;
	private IEmployeeService employeeService;
	private IDepartmentService departmentService;
	@Override
	public String execute() throws Exception {
		if(upload!=null){
			List<String[]> list = employeeService.importExcel(upload);
			for (String[] strings : list) {
				Employee employee = new Employee();
				employee.setUsername(strings[1]+UUID.randomUUID().toString().substring(0, 6));
				employee.setEmail(strings[2]);
				if(StringUtils.isNotBlank(strings[3])){
					employee.setAge(Integer.parseInt(strings[3]));
				}
				if(StringUtils.isNotBlank(strings[4])){
					Department department = departmentService.findeptByName(strings[4]);
					employee.setDepartment(department);
				}
				employeeService.saveOrUpdate(employee);
			}
			putContext("message", "导入"+list.size()+"条数据成功！");
		}
		return SUCCESS;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
}
