package com.thinkgem.jeesite.modules.huvis.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvLogisticsCompMst;

@MyBatisDao
public interface HvLogisticsCompMstDao extends CrudDao<HvLogisticsCompMst> {
	
	public List<HvLogisticsCompMst> findList(HvLogisticsCompMst hvLogisticsCompMst);
	
	public List<HvLogisticsCompMst> findByIdIn(String[] ids);

	public HvLogisticsCompMst getInfoByName(HvLogisticsCompMst hvLogisticsCompMst);
	
	public HvLogisticsCompMst getInfoByCode(HvLogisticsCompMst hvLogisticsCompMst);
	
}
