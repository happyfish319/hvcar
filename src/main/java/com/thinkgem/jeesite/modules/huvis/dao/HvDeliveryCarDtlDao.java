package com.thinkgem.jeesite.modules.huvis.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryCarDtl;

@MyBatisDao
public interface HvDeliveryCarDtlDao extends CrudDao<HvDeliveryCarDtl>{

	public List<HvDeliveryCarDtl> findList(HvDeliveryCarDtl hvDeliveryCarDtl);
	
	public void deleteByMstId(HvDeliveryCarDtl hvDeliveryCarDtl);
	
}
