package com.thinkgem.jeesite.modules.huvis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huvis.dao.HvDeliveryExcelTempDao;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryExcelTemp;

@Service
@Transactional(readOnly = true)
public class HvDeliveryExcelTempService extends CrudService<HvDeliveryExcelTempDao, HvDeliveryExcelTemp>{

	@Autowired
	private HvDeliveryExcelTempDao hvDeliveryExcelTempDao;
	
	@Transactional(readOnly = false)
	public HvDeliveryExcelTemp saveTempTable(HvDeliveryExcelTemp hvDeliveryExcelTemp) {
		hvDeliveryExcelTemp.preInsert();
		hvDeliveryExcelTempDao.insert(hvDeliveryExcelTemp);
		return hvDeliveryExcelTemp;
	}
	
	
	public Page<HvDeliveryExcelTemp> findHvDeliveryExcelTemp(Page<HvDeliveryExcelTemp> page, HvDeliveryExcelTemp hvDeliveryExcelTemp) {
		// 设置分页参数
		hvDeliveryExcelTemp.setPage(page);
		// 执行分页查询
		page.setList(hvDeliveryExcelTempDao.findList(hvDeliveryExcelTemp));
		return page;
	}
	
	public List<HvDeliveryExcelTemp> findAllList(HvDeliveryExcelTemp hvDeliveryExcelTemp){
		List<HvDeliveryExcelTemp> list = hvDeliveryExcelTempDao.findAllList(hvDeliveryExcelTemp);
		return list;
	}

}
