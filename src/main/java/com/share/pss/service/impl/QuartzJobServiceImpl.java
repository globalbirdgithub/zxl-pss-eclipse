package com.share.pss.service.impl;
import java.util.Date;
import java.util.List;
import com.share.pss.dao.IBaseDao;
import com.share.pss.domain.ProductStock;
import com.share.pss.service.IQuartzJobService;

public class QuartzJobServiceImpl implements IQuartzJobService{
	private IBaseDao baseDao;
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public void work() {
		System.out.println(new Date());
		String hql = "select o from ProductStock o where o.warning=? and (o.num<o.bottomNum or o.num>topNum)";
		List<ProductStock> list = baseDao.findByHql(hql,true);
		for (ProductStock productStock : list) {
			System.out.println(productStock);
		}
	}
}
