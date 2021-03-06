package com.share.pss.web.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.share.pss.domain.Permission;
import com.share.pss.domain.Role;
import com.share.pss.query.RoleQuery;
import com.share.pss.query.PageList;
import com.share.pss.service.IPermissionService;
import com.share.pss.service.IRoleService;
/**
 * @author MrZhang
 * @date 2017年11月1日 下午11:32:44
 * @version V1.0 表现层Action 访问Action的时候默认Action在栈顶，ModelDriven对应的拦截器检测到Role有值的时候将其压栈到栈顶
 * 													  Preparable对应的拦截器检测到要执行Action中的方法的时候就执行prepare()
 */
public class RoleAction extends CRUDAction<Role>{
	private static final long serialVersionUID = 1L;
	//Spring管理
	private IRoleService roleService;
	private IPermissionService permissionService;
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public void setPermissionService(IPermissionService permissionService) {
		this.permissionService = permissionService;
	}
	//接收前台多选权限Struts2管理
	private Long[] ids;
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	//Struts2管理 通过值栈(List/Map)向前台提供数据，
	//List栈需要：属性+getter；Map栈需要：ActionContext.getContext.put(key,value)
	private PageList pageList;
	public PageList getPageList() {
		return pageList;
	}
	//Struts2管理 需要setter、getter
	private RoleQuery baseQuery = new RoleQuery();
	public RoleQuery getBaseQuery() {
		return baseQuery;
	}
	//Struts2管理 用于接收和回显前台数据，需要它在栈顶时才放到栈顶
	private Role role;
	
	//====================================Action方法=========================================
	//获取所有
	@Override
	protected void list() {
		this.pageList = roleService.findByQuery(baseQuery);
	}
	//新建/修改
	@Override
	protected void inputt() {
		/*留空*/
		putContext("allPermissions", permissionService.getAll());
	}
	//保存
	@Override
	protected void savee() {
		//保存中间表
		if(ids!=null){
			for (Long perId : ids) {
				role.getPermissions().add(new Permission(perId));//提高效率
			}
		}
		//如果是新增用户，则新增后跳转到最后一页,将参数传递到list方法中
		if(id==null){
			baseQuery.setCurrentPage(Integer.MAX_VALUE);
		}
		roleService.saveOrUpdate(role);
	}
	//ajax删除
	@Override
	protected void deletee() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {
			if(id!=null){
				roleService.delete(id);
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
	protected Role getModell() {
		return role;
	}
	//=========================prepare拦截器对应的类PrepareInterceptor检测方法前缀prefix='prepare'通过反射调用=======
	@Override
	protected void prepareInputt(){
		if(id!=null){
			role = roleService.get(id);//修改需要回显否则不需要(这时会压栈)
			Set<Permission> permissions = role.getPermissions();
			ids = new Long[permissions.size()];
			int index = 0;
			for (Permission permission : permissions) {
				ids[index++] = permission.getId();
			}
		}
	}
	@Override
	protected void prepareSavee() {
		if(id==null){
			role = new Role();//新增后的保存
		}else{
			role = roleService.get(id);//修改后的保存
			role.getPermissions().clear();
		}
	}
	@Override
	protected void prepareDeletee() {
	}
	
}
 