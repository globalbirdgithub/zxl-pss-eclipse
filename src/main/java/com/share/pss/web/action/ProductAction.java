package com.share.pss.web.action;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.share.pss.domain.Product;
import com.share.pss.domain.ProductType;
import com.share.pss.query.PageList;
import com.share.pss.query.ProductQuery;
import com.share.pss.service.IProductService;
import com.share.pss.service.IProductTypeService;
import com.share.pss.service.ISystemDictionaryDetailService;

import net.coobird.thumbnailator.Thumbnails;
/**
 * @author MrZhang
 * @date 2017年11月1日 下午11:32:44
 * @version V1.0 表现层Action 访问Action的时候默认Action在栈顶，ModelDriven对应的拦截器检测到Product有值的时候将其压栈到栈顶
 * 													  Preparable对应的拦截器检测到要执行Action中的方法的时候就执行prepare()
 */
public class ProductAction extends CRUDAction<Product>{
	private static final long serialVersionUID = 1L;
	//Spring管理
	private IProductService productService;
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	private ISystemDictionaryDetailService systemDictionaryDetailService;
	public void setSystemDictionaryDetailService(ISystemDictionaryDetailService systemDictionaryDetailService) {
		this.systemDictionaryDetailService = systemDictionaryDetailService;
	}
	private IProductTypeService productTypeService;
	public void setProductTypeService(IProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}
	//产品图片上传
	private File uploadProductPicture;
	public File getUploadProductPicture() {
		return uploadProductPicture;
	}
	public void setUploadProductPicture(File uploadProductPicture) {
		this.uploadProductPicture = uploadProductPicture;
	}
	//Struts2管理 通过值栈(List/Map)向前台提供数据，
	//List栈需要：属性+getter；Map栈需要：ActionContext.getContext.put(key,value)
	private PageList pageList;
	public PageList getPageList() {
		return pageList;
	}
	//Struts2管理 需要setter、getter
	private ProductQuery baseQuery = new ProductQuery();
	public ProductQuery getBaseQuery() {
		return baseQuery;
	}
	//Struts2管理 用于接收和回显前台数据，需要它在栈顶时才放到栈顶
	private Product product;
	
	//====================================Action方法=========================================
	//获取所有
	@Override
	protected void list() {
		this.pageList = productService.findByQuery(baseQuery);
	}
	//新建/修改
	@Override
	protected void inputt() {
		List<ProductType> productChildrenTypes = new ArrayList<>();
		//修改
		if(id!=null){
			ProductType productType = product.getProductType();
			ProductType parentProductType = productType.getParentProductType();
			if(parentProductType!=null&&parentProductType.getId()!=-1){
				List<ProductType> productChildrenTypes2 = productTypeService.getProductChildrenTypes(parentProductType.getId());
				productChildrenTypes.addAll(productChildrenTypes2);
			}
		//新增
		}else{
			ProductType productType2 = new ProductType();
			productType2.setId(-1L);
			productType2.setName("--请选择--");
			productChildrenTypes.add(productType2);
		}
		putContext("productChilrenTypes", productChildrenTypes);
		putContext("productParentTypes", productTypeService.getAllProductParentTypes());
		putContext("allBrands", systemDictionaryDetailService.getAllBrands());
		putContext("allUnits", systemDictionaryDetailService.getAllUnits());
	}
	//保存
	private static final SimpleDateFormat diyDate = new SimpleDateFormat("yyyyMMdd-HHmmssS");
	@Override
	protected void savee() throws Exception{
		if(uploadProductPicture!=null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/");
			// 修改怎样处理原图:直接删除,月底在一次性删除
			if(id!=null&&StringUtils.isNoneBlank(product.getPic())){
				File picFile = new File(realPath,product.getPic());
				if(picFile.exists()){
					picFile.delete();
				}
				File smallPicFile = new File(realPath,product.getSmallPic());
				if(smallPicFile.exists()){
					smallPicFile.delete();
				}
			}
			// 上传文件命名,拷贝到webapp的位置,设置pic到product
			Date date = new Date();
			String picFileName = "productPictures/"+diyDate.format(date)+".png";
			String smallPicFileName = "productPictures/"+diyDate.format(date)+"_small.png";
			File picFile = new File(realPath,picFileName);
			File smallPicFile = new File(realPath,smallPicFileName);
			File parentFile = picFile.getParentFile();
			if(!parentFile.exists()){
				parentFile.mkdirs();
			}
			FileUtils.copyFile(uploadProductPicture, picFile);
			//缩略图
			Thumbnails.of(uploadProductPicture).scale(0.1F).toFile(smallPicFile);
			product.setPic(picFileName);
			product.setSmallPic(smallPicFileName);
		}
		try {
			//如果是新增用户，则新增后跳转到最后一页,将参数传递到list方法中
			if(id==null){
				baseQuery.setCurrentPage(Integer.MAX_VALUE);
			}
			productService.saveOrUpdate(product);
			addActionMessage("保存成功！");
		} catch (Exception e) {
			addActionError("保存异常！："+e.getMessage());
		}
	}
	//ajax删除
	@Override
	protected void deletee() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {
			if(id!=null){
				Product delProduct = productService.get(id);
				productService.delete(id);
				//删除图片
				String realPath = ServletActionContext.getServletContext().getRealPath("/");
				if(id!=null&&StringUtils.isNoneBlank(delProduct.getPic())){
					File delPicFile = new File(realPath,delProduct.getPic());
					if(delPicFile.exists()){
						delPicFile.delete();
					}
					File delSmallPicFile = new File(realPath,delProduct.getSmallPic());
					if(delSmallPicFile.exists()){
						delSmallPicFile.delete();
					}
				}
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
	protected Product getModell() {
		return product;
	}
	//=========================prepare拦截器对应的类PrepareInterceptor检测方法前缀prefix='prepare'通过反射调用=======
	@Override
	protected void prepareInputt(){
		if(id!=null){
			product = productService.get(id);//修改需要回显否则不需要(这时会压栈)
		}
	}
	@Override
	protected void prepareSavee() {
		if(id==null){
			product = new Product();
		}else{
			product = productService.get(id);
			product.setProductType(null);
			product.setBrand(null);
			product.setUnit(null);
		}
	}
	@Override
	protected void prepareDeletee() {
	}
	//=======================二级联动=========================
	// ajax:处理二级联动的数据
	public String findChildren() {
		List<ProductType> childrenList = productTypeService.getProductChildrenTypes(id);
		putContext("productChilrenTypes", childrenList);
		return "productTypeJsonResult";
	}
	//=============================订单明细产品选择模态框====================
	public String bill() throws Exception{
		this.pageList = productService.findByQuery(baseQuery);
		return "bill";
	}
}
