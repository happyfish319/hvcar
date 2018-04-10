package com.thinkgem.jeesite.modules.huvis.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvLogisticsCarDtl;

@MyBatisDao
public interface HvLogisticsCarDtlDao extends CrudDao<HvLogisticsCarDtl> {
	
	public List<HvLogisticsCarDtl> findList(HvLogisticsCarDtl hvLogisticsCarDtl);
	
	public List<HvLogisticsCarDtl> findByIdIn(String[] ids);

	/*public HvLogisticsCarDtl getInfoByName(HvLogisticsCarDtl hvLogisticsCarDtl);*/
	
	public int saveOrder(HvLogisticsCarDtl hvLogisticsCarDtl);
}
