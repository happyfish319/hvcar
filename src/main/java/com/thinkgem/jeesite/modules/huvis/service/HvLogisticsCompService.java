package com.thinkgem.jeesite.modules.huvis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huvis.dao.HvLogisticsCompMstDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvLogisticsCompMst;


@Service
@Transactional(readOnly = true)
public class HvLogisticsCompService extends CrudService<HvLogisticsCompMstDao, HvLogisticsCompMst>{

	@Autowired
	private HvLogisticsCompMstDao hvLogisticsCompMstDao;
	
	@Transactional(readOnly = false)
	public Page<HvLogisticsCompMst> findPage(Page<HvLogisticsCompMst> page, HvLogisticsCompMst hvLogisticsCompMst, boolean isDataScopeFilter) {
		hvLogisticsCompMst.getSqlMap().put("dsf", dataScopeFilter(hvLogisticsCompMst.getCurrentUser(), "o", "u"));
		return super.findPage(page, hvLogisticsCompMst);
	}
	
	@Transactional(readOnly = false)
	public HvLogisticsCompMst getInfoByCode(String logisticsCd) {
		HvLogisticsCompMst hvLogisticsCompMst = new HvLogisticsCompMst();
		hvLogisticsCompMst.setLogisticsCd(logisticsCd);
		return hvLogisticsCompMstDao.getInfoByCode(hvLogisticsCompMst);
	}
	
}
