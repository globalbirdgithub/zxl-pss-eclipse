package com.share.pss.service.impl;

import java.util.List;

import com.share.pss.domain.ProductType;
import com.share.pss.service.IProductTypeService;

/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:31:44
 * @version V1.0 业务层实现
 */
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType> implements IProductTypeService {

	@Override
	public List<ProductType> getAllProductParentTypes() {
		String hql = "select o from ProductType o where o.parentProductType is null";
		List<ProductType> productParentTypesList = baseDao.findByHql(hql);
		return productParentTypesList;
	}

	@Override
	public List<ProductType> getProductChildrenTypes(Long parentId) {
		String hql = "select o from ProductType o where o.parentProductType.id=?";
		List<ProductType> productChildrenTypesList = baseDao.findByHql(hql, parentId);
		return productChildrenTypesList;
	}
}
