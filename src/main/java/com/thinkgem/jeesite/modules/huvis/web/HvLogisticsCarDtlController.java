package com.thinkgem.jeesite.modules.huvis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huvis.entity.HvLogisticsCarDtl;
import com.thinkgem.jeesite.modules.huvis.service.HvLogisticsCarDtlService;
import com.thinkgem.jeesite.modules.sys.entity.Office;

@Controller
@RequestMapping(value = "${adminPath}/huvis/logisticsCar")
public class HvLogisticsCarDtlController extends BaseController {
	
	@Autowired
	private HvLogisticsCarDtlService hvLogisticsCarDtlService;
	
	@ModelAttribute("hvLogisticsCarDtl")
	public HvLogisticsCarDtl get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return hvLogisticsCarDtlService.get(id);
		}else{
			return new HvLogisticsCarDtl();
		}
	}
	
	/**
	 * 
	* @Title: list 
	* @Description: 分页查询物流公司
	* @author fish
	* @date 2018年2月27日 上午9:35:50 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = {"list", ""})
	public String list(HvLogisticsCarDtl hvLogisticsCarDtl, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<HvLogisticsCarDtl> page = hvLogisticsCarDtlService.findPage(new Page<HvLogisticsCarDtl>(request, response), hvLogisticsCarDtl, true); 
        model.addAttribute("page", page);
		return "modules/huvis/carDriverList";
	}
	

	/**
	 * 
	* @Title: checkName 
	* @Description: 检查物流公司名称是否重复
	* @author fish
	* @date 2018年2月27日 上午9:36:15 
	* @return String    返回类型 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "checkCarNo")
	public String checkName(String oldCarNo, String carNo) {
		/*if (logisticsNm !=null && logisticsNm.equals(oldName)) {
			return "true";
		} else if (logisticsNm !=null && hvLogisticsCarDtlService.getInfoByName(logisticsNm) == null) {
			return "true";
		}*/
		return "false";
	}

	/**
	 * 
	* @Title: form 
	* @Description: 修改或新增信息
	* @author fish
	* @date 2018年2月27日 上午9:36:41 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = {"form", ""})
	public String form(HvLogisticsCarDtl hvLogisticsCarDtl, Model model) {
		model.addAttribute("hvLogisticsCarDtl",hvLogisticsCarDtl);
		return "modules/huvis/carDriverForm";
	}
	
	/**
	 * 
	* @Title: save 
	* @Description: 修改或新增操作
	* @author fish
	* @date 2018年2月27日 上午9:37:01 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "save")
	public String save(HvLogisticsCarDtl hvLogisticsCarDtl, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		hvLogisticsCarDtl.setOffice(new Office(request.getParameter("office.id")));
		if (!beanValidator(model, hvLogisticsCarDtl)){
			return form(hvLogisticsCarDtl, model);
		}
		hvLogisticsCarDtlService.save(hvLogisticsCarDtl);
		addMessage(redirectAttributes, "保存成功");
	    return "redirect:" + adminPath + "/huvis/logisticsCar/list?repage";
	}
	
	/**
	 * 
	* @Title: delete 
	* @Description: 删除
	* @author fish
	* @date 2018年2月27日 上午9:37:16 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "delete")
	public String delete(HvLogisticsCarDtl hvLogisticsCarDtl, String id, RedirectAttributes redirectAttributes) {
		// 如果没有审核权限，则不允许删除或发布。
		hvLogisticsCarDtlService.delete(hvLogisticsCarDtl);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/huvis/logisticsCar/list?repage";
	}
	
	
	@RequestMapping(value = {"carDriverListPopup", ""})
	public String carDriverListPopup(HvLogisticsCarDtl hvLogisticsCarDtl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<HvLogisticsCarDtl> page = hvLogisticsCarDtlService.findPage(new Page<HvLogisticsCarDtl>(request, response), hvLogisticsCarDtl, true); 
        model.addAttribute("page", page);
		return "modules/huvis/carDriverListPopup";
	}
	
	@RequestMapping(value = "saveOrder")
	public String saveOrder(HvLogisticsCarDtl hvLogisticsCarDtl, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		hvLogisticsCarDtl.setId(request.getParameter("hvLogisticsCarDtl.id"));
		hvLogisticsCarDtl.setOrderId(request.getParameter("hvLogisticsCarDtl.orderId"));
		hvLogisticsCarDtlService.saveOrder(hvLogisticsCarDtl);
		addMessage(redirectAttributes, "保存成功");
	    return "redirect:" + adminPath + "/huvis/delivery/allotCarList";
	}

}
