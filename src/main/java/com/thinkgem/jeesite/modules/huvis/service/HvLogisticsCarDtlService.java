package com.thinkgem.jeesite.modules.huvis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huvis.dao.HvLogisticsCarDtlDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvLogisticsCarDtl;


@Service
@Transactional(readOnly = true)
public class HvLogisticsCarDtlService extends CrudService<HvLogisticsCarDtlDao, HvLogisticsCarDtl>{

	@Autowired
	private HvLogisticsCarDtlDao hvLogisticsCarDtlDao;
	
	@Transactional(readOnly = false)
	public Page<HvLogisticsCarDtl> findPage(Page<HvLogisticsCarDtl> page, HvLogisticsCarDtl hvLogisticsCarDtl, boolean isDataScopeFilter) {
		hvLogisticsCarDtl.getSqlMap().put("dsf", dataScopeFilter(hvLogisticsCarDtl.getCurrentUser(), "o", "u"));
		return super.findPage(page, hvLogisticsCarDtl);
	}
	@Transactional(readOnly = false)
	public int saveOrder(HvLogisticsCarDtl hvLogisticsCarDtl){
		return hvLogisticsCarDtlDao.saveOrder(hvLogisticsCarDtl);
	}
	
}
