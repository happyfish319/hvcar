package com.thinkgem.jeesite.modules.huvis.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.huvis.entity.DayDeliveryReport;

@MyBatisDao
public interface DayDeliveryReportDao extends CrudDao<DayDeliveryReport>{

	public List<DayDeliveryReport> findDayDeliveryReport(DayDeliveryReport dayDeliveryReport);
}
