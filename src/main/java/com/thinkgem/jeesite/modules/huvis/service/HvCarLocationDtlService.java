package com.thinkgem.jeesite.modules.huvis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huvis.dao.HvCarLocationDtlDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvCarLocationDtl;


@Service
@Transactional(readOnly = true)
public class HvCarLocationDtlService extends CrudService<HvCarLocationDtlDao, HvCarLocationDtl>{

	@Autowired
	private HvCarLocationDtlDao hvCarLocationDtlDao;
	
	@Transactional(readOnly = false)
	public List<HvCarLocationDtl> findAllList(HvCarLocationDtl hvCarLocationDtl) {
		//hvCarLocationDtl.getSqlMap().put("dsf", dataScopeFilter(hvCarLocationDtl.getCurrentUser(), "o", "u"));
		return hvCarLocationDtlDao.findAllList(hvCarLocationDtl);
	}
	
	
	@Transactional(readOnly = false)
	public HvCarLocationDtl findLastData(HvCarLocationDtl hvCarLocationDtl) {
		//hvCarLocationDtl.getSqlMap().put("dsf", dataScopeFilter(hvCarLocationDtl.getCurrentUser(), "o", "u"));
		return hvCarLocationDtlDao.findLastData(hvCarLocationDtl);
	}
	
	
}
