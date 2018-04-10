package com.thinkgem.jeesite.modules.huvis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huvis.entity.HvLogisticsCompMst;
import com.thinkgem.jeesite.modules.huvis.service.HvLogisticsCompService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/huvis/logisticsComp")
public class HvLogisticsCompController extends BaseController {
	
	@Autowired
	private HvLogisticsCompService hvLogisticsCompService;
	
	@ModelAttribute("hvLogisticsCompMst")
	public HvLogisticsCompMst get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return hvLogisticsCompService.get(id);
		}else{
			return new HvLogisticsCompMst();
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
	public String list(HvLogisticsCompMst hvLogisticsCompMst, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<HvLogisticsCompMst> page = hvLogisticsCompService.findPage(new Page<HvLogisticsCompMst>(request, response), hvLogisticsCompMst, true); 
        model.addAttribute("page", page);
		return "modules/huvis/logisticsList";
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
	public String form(HvLogisticsCompMst hvLogisticsCompMst, Model model) {
		model.addAttribute("hvLogisticsCompMst",hvLogisticsCompMst);
		return "modules/huvis/logisticsForm";
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
	public String save(HvLogisticsCompMst hvLogisticsCompMst, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		hvLogisticsCompMst.setOffice(new Office(request.getParameter("office.id")));
		if (!beanValidator(model, hvLogisticsCompMst)){
			return form(hvLogisticsCompMst, model);
		}
		hvLogisticsCompService.save(hvLogisticsCompMst);
		addMessage(redirectAttributes, "保存物流公司'" + StringUtils.abbr(hvLogisticsCompMst.getOffice().getName(),50) + "'成功");
	    return "redirect:" + adminPath + "/huvis/logisticsComp/list?repage";
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
	public String delete(HvLogisticsCompMst hvLogisticsCompMst, String id, RedirectAttributes redirectAttributes) {
		// 如果没有审核权限，则不允许删除或发布。
		hvLogisticsCompService.delete(hvLogisticsCompMst);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/huvis/logisticsComp/list?repage";
	}

	/**
	 * 
	* @Title: getLogisticsDtl 
	* @Description: 当物流公司登陆系统，只能看到并修改本公司的信息
	* @author fish
	* @date 2018年2月27日 上午9:37:30 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = {"logisticsDtl", ""})
	public String getLogisticsDtl(HvLogisticsCompMst hvLogisticsCompMst, HttpServletRequest request, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		hvLogisticsCompMst = hvLogisticsCompService.getInfoByCode(currentUser.getOffice().getId());
		model.addAttribute("hvLogisticsCompMst",hvLogisticsCompMst);
		if("admin".equals(currentUser.getLoginName())||"sys".equals(currentUser.getLoginName())){
			return "redirect:" + adminPath + "/huvis/logisticsComp/list";
		}else{
			return "modules/huvis/logisticsDtl";
		}
		
	}
	
	
	@RequestMapping(value = "logisticsDtlSave")
	public String logisticsDtlSave(HvLogisticsCompMst hvLogisticsCompMst, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, hvLogisticsCompMst)){
			return form(hvLogisticsCompMst, model);
		}
		hvLogisticsCompService.save(hvLogisticsCompMst);
		addMessage(redirectAttributes, "保存物流公司'" + StringUtils.abbr(hvLogisticsCompMst.getOffice().getName(),50) + "'成功");
	    return "modules/huvis/logisticsDtl";
	}
	
}
