package com.thinkgem.jeesite.modules.huvis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huvis.dao.HvCustCompMstDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvCustCompMst;


@Service
@Transactional(readOnly = true)
public class HvCustCompMstService extends CrudService<HvCustCompMstDao, HvCustCompMst>{

	@Autowired
	private HvCustCompMstDao hvCustCompMstDao;
	
	@Transactional(readOnly = false)
	public Page<HvCustCompMst> findPage(Page<HvCustCompMst> page, HvCustCompMst hvCustCompMst, boolean isDataScopeFilter) {
		hvCustCompMst.getSqlMap().put("dsf", dataScopeFilter(hvCustCompMst.getCurrentUser(), "o", "u"));
		return super.findPage(page, hvCustCompMst);
	}
	
	@Transactional(readOnly = false)
	public HvCustCompMst getInfoByCode(String logisticsCd) {
		HvCustCompMst hvCustCompMst = new HvCustCompMst();
		return hvCustCompMstDao.getInfoByCode(hvCustCompMst);
	}
	
}
