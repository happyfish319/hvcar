package com.thinkgem.jeesite.modules.huvis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huvis.dao.HvDeliveryMstDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryMst;


@Service
@Transactional(readOnly = true)
public class HvDeliveryMstService extends CrudService<HvDeliveryMstDao, HvDeliveryMst>{

	@Autowired
	private HvDeliveryMstDao hvDeliveryMstDao;
	
	@Transactional(readOnly = false)
	public Page<HvDeliveryMst> findPage(Page<HvDeliveryMst> page, HvDeliveryMst hvDeliveryMst, boolean isDataScopeFilter) {
		hvDeliveryMst.getSqlMap().put("dsf", dataScopeFilter(hvDeliveryMst.getCurrentUser(), "o", "u"));
		return super.findPage(page, hvDeliveryMst);
	}
	
	/*@Transactional(readOnly = false)
	public Page<HvDeliveryMst> findPage2(Page<HvDeliveryMst> page, HvDeliveryMst hvDeliveryMst, boolean isDataScopeFilter) {
		hvDeliveryMst.getSqlMap().put("dsf", dataScopeFilter(hvDeliveryMst.getCurrentUser(), "o", "u"));
		return super.findPage(page, hvDeliveryMst);
	}*/
	
	@Transactional(readOnly = false)
	public void saveDeliveryMst(HvDeliveryMst hvDeliveryMst) {
		hvDeliveryMst.preInsert();
		hvDeliveryMstDao.insert(hvDeliveryMst);
	}
	
	@Transactional(readOnly = false)
	public Page<HvDeliveryMst> getCarState(Page<HvDeliveryMst> page, HvDeliveryMst hvDeliveryMst, boolean isDataScopeFilter) {
		return super.findPage(page, hvDeliveryMst);
	}
	
	@Transactional(readOnly = false)
	public Integer getCount(HvDeliveryMst hvDeliveryMst) {
		return hvDeliveryMstDao.getCount(hvDeliveryMst);
	}
	
}
