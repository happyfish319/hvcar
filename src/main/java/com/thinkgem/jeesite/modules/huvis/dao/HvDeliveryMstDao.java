package com.thinkgem.jeesite.modules.huvis.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryMst;

@MyBatisDao
public interface HvDeliveryMstDao extends CrudDao<HvDeliveryMst>{

	public List<HvDeliveryMst> findList(HvDeliveryMst hvDeliveryMst);
	
	public Integer getCount(HvDeliveryMst hvDeliveryMst);
	
}
