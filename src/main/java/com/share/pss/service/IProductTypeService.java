package com.share.pss.service;

import java.util.List;

import com.share.pss.domain.ProductType;

/**
 * @author MrZhang 
 * @date 2017年10月31日 下午12:28:27
 * @version V1.0 业务层接口
 */
public interface IProductTypeService extends IBaseService<ProductType>{
	//获取父菜单
	List<ProductType> getAllProductParentTypes();
	//获取子菜单
	List<ProductType> getProductChildrenTypes(Long parentId);
}
