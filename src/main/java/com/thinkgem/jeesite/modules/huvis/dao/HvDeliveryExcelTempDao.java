package com.thinkgem.jeesite.modules.huvis.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryExcelTemp;

@MyBatisDao
public interface HvDeliveryExcelTempDao extends CrudDao<HvDeliveryExcelTemp> {
	
	public List<HvDeliveryExcelTemp> findAllList(HvDeliveryExcelTemp hvDeliveryExcelTemp);

}
