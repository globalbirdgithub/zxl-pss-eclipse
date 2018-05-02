package com.share.pss.service;
import java.util.List;

import com.share.pss.domain.SystemDictionaryDetail;
/**
 * @author MrZhang 
 * @date 2017年10月31日 下午12:28:27
 * @version V1.0 业务层接口
 */
public interface ISystemDictionaryDetailService extends IBaseService<SystemDictionaryDetail>{
	//获取所有品牌
	List<SystemDictionaryDetail> getAllBrands();
	//获取所有单位
	List<SystemDictionaryDetail> getAllUnits();
}
