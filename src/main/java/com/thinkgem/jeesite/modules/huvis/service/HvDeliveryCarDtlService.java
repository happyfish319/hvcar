package com.thinkgem.jeesite.modules.huvis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huvis.dao.HvDeliveryCarDtlDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryCarDtl;


@Service
@Transactional(readOnly = true)
public class HvDeliveryCarDtlService extends CrudService<HvDeliveryCarDtlDao, HvDeliveryCarDtl>{

	@Autowired
	private HvDeliveryCarDtlDao hvDeliveryCarDtlDao;
	
	@Transactional(readOnly = false)
	public Page<HvDeliveryCarDtl> findPage(Page<HvDeliveryCarDtl> page, HvDeliveryCarDtl hvDeliveryCarDtl, boolean isDataScopeFilter) {
		hvDeliveryCarDtl.getSqlMap().put("dsf", dataScopeFilter(hvDeliveryCarDtl.getCurrentUser(), "o", "u"));
		return super.findPage(page, hvDeliveryCarDtl);
	}
	
	@Transactional(readOnly = false)
	public List<HvDeliveryCarDtl> findAllList(HvDeliveryCarDtl hvDeliveryCarDtl) {
		hvDeliveryCarDtl.getSqlMap().put("dsf", dataScopeFilter(hvDeliveryCarDtl.getCurrentUser(), "o", "u"));
		return hvDeliveryCarDtlDao.findAllList(hvDeliveryCarDtl);
	}
	
	@Transactional(readOnly = false)
	public void deleteByMstId(HvDeliveryCarDtl hvDeliveryCarDtl) {
		 hvDeliveryCarDtlDao.deleteByMstId(hvDeliveryCarDtl);
	}
	
}
