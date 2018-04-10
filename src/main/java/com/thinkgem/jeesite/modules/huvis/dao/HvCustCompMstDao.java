package com.thinkgem.jeesite.modules.huvis.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvCustCompMst;

@MyBatisDao
public interface HvCustCompMstDao extends CrudDao<HvCustCompMst> {
	
	public List<HvCustCompMst> findList(HvCustCompMst hvCustCompMst);
	
	public List<HvCustCompMst> findByIdIn(String[] ids);

	public HvCustCompMst getInfoByName(HvCustCompMst hvCustCompMst);
	
	public HvCustCompMst getInfoByCode(HvCustCompMst hvCustCompMst);
	
}
