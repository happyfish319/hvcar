package com.thinkgem.jeesite.modules.huvis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.huvis.entity.HvCustCompMst;
import com.thinkgem.jeesite.modules.huvis.service.HvCustCompMstService;

@Controller
@RequestMapping(value = "${adminPath}/huvis/custComp")
public class HvCustCompController extends BaseController {
	
	@Autowired
	private HvCustCompMstService hvCustCompMstService;
	
	@ModelAttribute("hvCustCompMst")
	public HvCustCompMst get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return hvCustCompMstService.get(id);
		}else{
			return new HvCustCompMst();
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
	public String list(HvCustCompMst hvCustCompMst, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<HvCustCompMst> page = hvCustCompMstService.findPage(new Page<HvCustCompMst>(request, response), hvCustCompMst, true); 
        model.addAttribute("page", page);
		return "modules/huvis/custCompList";
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
	public String form(HvCustCompMst hvCustCompMst, Model model) {
		model.addAttribute("hvCustCompMst",hvCustCompMst);
		return "modules/huvis/custCompForm";
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
	public String save(HvCustCompMst hvCustCompMst, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, hvCustCompMst)){
			return form(hvCustCompMst, model);
		}
		hvCustCompMstService.save(hvCustCompMst);
		addMessage(redirectAttributes, "保存公司'" + StringUtils.abbr(hvCustCompMst.getCustCompNm(),50) + "'成功");
	    return "redirect:" + adminPath + "/huvis/custComp/list?repage";
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
	public String delete(HvCustCompMst hvCustCompMst, String id, RedirectAttributes redirectAttributes) {
		// 如果没有审核权限，则不允许删除或发布。
		hvCustCompMstService.delete(hvCustCompMst);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/huvis/custComp/list?repage";
	}

}
