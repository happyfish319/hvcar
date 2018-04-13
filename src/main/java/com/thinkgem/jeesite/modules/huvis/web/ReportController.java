package com.thinkgem.jeesite.modules.huvis.web;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huvis.entity.DayDeliveryReport;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryExcelTemp;
import com.thinkgem.jeesite.modules.huvis.service.DayDeliveryReportService;

@Controller
@RequestMapping(value = "${adminPath}/huvis/report")
public class ReportController extends BaseController{

	@Autowired
	private DayDeliveryReportService dayDeliveryReportService;
	
	@RequestMapping(value = "exportDayDeliveryReport", method=RequestMethod.POST)
    public String exportDayDeliveryReport(DayDeliveryReport dayDeliveryReport, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "日发货单报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            HvDeliveryExcelTemp hvDeliveryExcelTemp = new HvDeliveryExcelTemp();
           /* if(hvDeliveryMst.getOrderId()!=null){
            	hvDeliveryExcelTemp.setOrderId(hvDeliveryMst.getOrderId());
            }
            if(hvDeliveryMst.getOutDay()!=null){
            	hvDeliveryExcelTemp.setOutDay(myFmt2.format(hvDeliveryMst.getOutDay()));
            }
            if(hvDeliveryMst.getCarNo()!=null){
            	hvDeliveryExcelTemp.setCarNo(hvDeliveryMst.getCarNo());
            }
        	hvDeliveryMst.setState(HvConstant.CAR_STATE_01); //默认显示待分配
        	hvDeliveryExcelTemp.setState(hvDeliveryMst.getState());*/
            
            
            List<DayDeliveryReport> list = dayDeliveryReportService.findDayDeliveryReport(dayDeliveryReport);
    		new ExportExcel("日发货单报表", HvDeliveryExcelTemp.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出日发货单报表失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/huvis/delivery/list?repage";
    }
}
