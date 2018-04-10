package com.thinkgem.jeesite.modules.huvis.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvCarLocationDtl;

@MyBatisDao
public interface HvCarLocationDtlDao extends CrudDao<HvCarLocationDtl>{
	
	public List<HvCarLocationDtl> findAllList(HvCarLocationDtl hvCarLocationDtl);
	
	public HvCarLocationDtl findLastData(HvCarLocationDtl hvCarLocationDtl);

}
