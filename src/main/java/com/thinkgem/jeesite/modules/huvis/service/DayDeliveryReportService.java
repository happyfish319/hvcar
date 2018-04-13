package com.thinkgem.jeesite.modules.huvis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.huvis.dao.DayDeliveryReportDao;
import com.thinkgem.jeesite.modules.huvis.entity.DayDeliveryReport;


@Service
@Transactional(readOnly = true)
public class DayDeliveryReportService extends CrudService<DayDeliveryReportDao, DayDeliveryReport>{

	@Autowired
	private DayDeliveryReportDao dayDeliveryReportDao;
	
	@Transactional(readOnly = false)
	public List<DayDeliveryReport> findDayDeliveryReport(DayDeliveryReport dayDeliveryReport) {
		return dayDeliveryReportDao.findDayDeliveryReport(dayDeliveryReport);
	}
	
	
}
