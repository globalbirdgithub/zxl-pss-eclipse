package com.share.pss.service.impl;
import java.util.List;

import com.share.pss.domain.SystemDictionaryDetail;
import com.share.pss.domain.SystemDictionaryType;
import com.share.pss.service.ISystemDictionaryDetailService;

/**
 * @author MrZhang
 * @date 2017年10月31日 下午12:31:44
 * @version V1.0 业务层实现
 */
public class SystemDictionaryDetailServiceImpl extends BaseServiceImpl<SystemDictionaryDetail> implements ISystemDictionaryDetailService {
	String hql = "select o from SystemDictionaryDetail o where o.systemDictionaryType.sn=?";
	@Override
	public List<SystemDictionaryDetail> getAllBrands() {
		List<SystemDictionaryDetail> BrandsList = baseDao.findByHql(hql, SystemDictionaryType.PRODUCT_BRAND);
		return BrandsList;
	}

	@Override
	public List<SystemDictionaryDetail> getAllUnits() {
		List<SystemDictionaryDetail> UnitsList = baseDao.findByHql(hql, SystemDictionaryType.PRODUCT_UNIT);
		return UnitsList;
	}
}
