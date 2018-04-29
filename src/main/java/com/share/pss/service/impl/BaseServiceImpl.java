package com.share.pss.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.share.pss.dao.IBaseDao;
import com.share.pss.query.BaseQuery;
import com.share.pss.query.PageList;
import com.share.pss.service.IBaseService;
/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:07:58
 * @version V1.0 业务层公共接口实现类:1.防止抽象类被实例化；2.强制子类重载抽象方法； 
 * 关于getClass()方法：--此时，由于 abstract抽象类不能直接被创建实例，所以getClass()返回的必定是子类实例；
 * --当子类实例被创建的同时会调用父类构造方法；new BaseServiceImpl(){}想当于实现BaseServiceImpl这个类。
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	//通过无参构造方法获取之类设置的泛型Employee
	private Class<T> entityClass;
	public BaseServiceImpl(){
		//返回此 Object 的运行时类（调用底层C++）-EmployeeServiceImpl
		Class clazz = getClass();
		//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type（标识接口）。-BaseServiceImpl<Employee>
		Type type = clazz.getGenericSuperclass();
		//ParameterizedType参数化类型是Type的子接口；判断是否属于参数化类型并强转；-BaseServiceImpl<Employee>
		if(type instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType)type;
			//返回表示此类型实际类型参数的 Type 对象的数组。 -[Ljava.lang.reflect.Type;@297425c3
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			//Class是Type的实现类，强转 -entityClass
			this.entityClass = (Class)actualTypeArguments[0];
		}
	}
	//由子类注入IBaseDao
	protected IBaseDao<T> baseDao;
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public void saveOrUpdate(T t) {
		baseDao.saveOrUpdate(t);
	}
	@Override
	public void delete(Serializable id) {
		baseDao.delete(entityClass, id);
	}
	@Override
	public T get(Serializable id) {
		T t = baseDao.get(entityClass, id);
		return t;
	}

	@Override
	public List<T> getAll() {
		List<T> list = baseDao.getAll(entityClass);
		return list;
	}
	//分页查询
	@Override
	public PageList findByQuery(BaseQuery baseQuery) {
		return baseDao.findByQuery(baseQuery);
	}
	/**下载Excel文件
	 * @param list 数据集合
	 * @param heads 表头，标题
	 * @return 
	 * @throws Exception InputStream
	 * 2018年4月7日上午10:36:54
	 */
	@Override
	public InputStream downloadExcel(List<String[]> list,String[] heads) throws Exception {
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet0");
		//表头
		Row row0 = sheet.createRow(0);
		for (int i = 0; i < heads.length; i++) {
			Cell cell = row0.createCell(i);
			cell.setCellValue(heads[i]);
		}
		//数据
		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.createRow(i+1);
			String[] strings = list.get(i);
			for (int j = 0; j < strings.length; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(strings[j]);
			}
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		wb.write(byteArrayOutputStream);
		byte[] byteArray = byteArrayOutputStream.toByteArray();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
		return byteArrayInputStream;
	}
	/**
	 * 读取Excel文件
	 */
	@Override
	public List<String[]> importExcel(File uploadFile) throws Exception {
		List<String[]> list = new ArrayList<>();
		FileInputStream fileInputStream = new FileInputStream(uploadFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			if(row!=null){
				short cellNum = row.getLastCellNum();
				String[] strings = new String[cellNum];
				for (int j = 0; j <= cellNum; j++) {
					XSSFCell cell = row.getCell(j);
					if(cell!=null){
						strings[j] = cell.getStringCellValue();
					}
				}
				list.add(strings);
			}
		}
		return list;
	}

}
