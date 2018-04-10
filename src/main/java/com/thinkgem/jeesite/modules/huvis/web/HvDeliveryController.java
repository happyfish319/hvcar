package com.thinkgem.jeesite.modules.huvis.web;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.huvis.entity.HvCarLocationDtl;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryCarDtl;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryExcelTemp;
import com.thinkgem.jeesite.modules.huvis.entity.HvDeliveryMst;
import com.thinkgem.jeesite.modules.huvis.service.HvCarLocationDtlService;
import com.thinkgem.jeesite.modules.huvis.service.HvDeliveryCarDtlService;
import com.thinkgem.jeesite.modules.huvis.service.HvDeliveryExcelTempService;
import com.thinkgem.jeesite.modules.huvis.service.HvDeliveryMstService;
import com.thinkgem.jeesite.modules.huvis.util.HvConstant;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "${adminPath}/huvis/delivery")
public class HvDeliveryController extends BaseController{
	
	@Autowired
	private HvDeliveryMstService hvDeliveryMstService;
	
	@Autowired
	private HvDeliveryExcelTempService hvDeliveryExcelTempService;
	
	@Autowired
	private HvDeliveryCarDtlService hvDeliveryCarDtlService;
	
	@Autowired
	private HvCarLocationDtlService hvCarLocationDtlService;
	
	@Autowired
	private SystemService systemService;
	
	
	
	@ModelAttribute("hvDeliveryMst")
	public HvDeliveryMst get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return hvDeliveryMstService.get(id);
		}else{
			return new HvDeliveryMst();
		}
	}
	
	/**
	 * 下载发货单数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "发货单导入模板.xlsx";
    		List<HvDeliveryExcelTemp> list = Lists.newArrayList();// list.add(UserUtils.getUser());
    		new ExportExcel("发货单", HvDeliveryExcelTemp.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/modules/huvis/deliveryList?repage";
    }
    
    
    /**
	 * 导入数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file,1, 0);
			List<HvDeliveryExcelTemp> list = ei.getDataList(HvDeliveryExcelTemp.class);
			HvDeliveryMst hvDeliveryMst = null;
			HvDeliveryCarDtl hvDeliveryCarDtl = null;
			String preOrderId = "";
			String preCarNoCls ="";
			boolean isSuccess = false;
			for(HvDeliveryExcelTemp hvDeliveryExcelTemp : list){
				if("".equals(hvDeliveryExcelTemp.getOrderId())||"".equals(hvDeliveryExcelTemp.getCarNoCls())){
					continue;
				}else{
					hvDeliveryMst = new HvDeliveryMst();
					hvDeliveryMst.setCarNoCls(hvDeliveryExcelTemp.getCarNoCls());
					hvDeliveryMst.setOrderId(hvDeliveryExcelTemp.getOrderId());
					int cnt = hvDeliveryMstService.getCount(hvDeliveryMst);
					if(cnt==0){
						isSuccess = true;
					}else{
						isSuccess = false;
						addMessage(redirectAttributes, "订单号重复: "+hvDeliveryExcelTemp.getOrderId()+" 请修改后重新导入。");
						break;
					}
				}
			}
			
			if(isSuccess){
				for (HvDeliveryExcelTemp hvDeliveryExcelTemp : list){
					if("".equals(hvDeliveryExcelTemp.getOrderId())||"".equals(hvDeliveryExcelTemp.getCarNoCls())){
						continue;
					}else{
					try{
							BeanValidators.validateWithException(validator, hvDeliveryExcelTemp);
							if(StringUtils.isNotBlank(hvDeliveryExcelTemp.getOrderId())){
							HvDeliveryExcelTemp entity = hvDeliveryExcelTempService.saveTempTable(hvDeliveryExcelTemp);
							boolean isTrue = preOrderId.equals(hvDeliveryExcelTemp.getOrderId())&&preCarNoCls.equals(hvDeliveryExcelTemp.getCarNoCls());
								if(!isTrue){
									hvDeliveryMst = new HvDeliveryMst();
									hvDeliveryMst.setTempId(entity.getId());
									hvDeliveryMst.setCarNoCls(entity.getCarNoCls());
									hvDeliveryMst.setOrderId(entity.getOrderId());
									hvDeliveryMst.setOutDay(DateUtils.parseDate(entity.getOutDay()));
									hvDeliveryMst.setPlCd(entity.getPlCd());
									hvDeliveryMst.setTempId(entity.getId());
									hvDeliveryMst.setTitoCd(entity.getTitoCd());
									hvDeliveryMst.setCarNo(hvDeliveryExcelTemp.getCarNo());
									hvDeliveryMst.setCusCd(hvDeliveryExcelTemp.getCusCd());
									hvDeliveryMst.setDelAddr(hvDeliveryExcelTemp.getDelAddr());
									hvDeliveryMst.setDelPlace(hvDeliveryExcelTemp.getDelPlace());
									hvDeliveryMst.setDrvName(hvDeliveryExcelTemp.getDrvName());
									hvDeliveryMst.setDrvPhone(hvDeliveryExcelTemp.getDrvPhone());
									hvDeliveryMst.setEmpNm(hvDeliveryExcelTemp.getEmpNm());
									hvDeliveryMst.setEmpNo(hvDeliveryExcelTemp.getEmpNo());
									User user = systemService.getUserByLoginName(hvDeliveryExcelTemp.getEmpNo());
									hvDeliveryMst.setCreateBy(user);
									hvDeliveryMst.setLogisticsCd(hvDeliveryExcelTemp.getLogisticsCd());
									hvDeliveryMst.setProvinceCd(hvDeliveryExcelTemp.getProvinceCd());
									hvDeliveryMst.setState(HvConstant.CAR_STATE_01);
									hvDeliveryMstService.save(hvDeliveryMst);
									successNum++;
								}
								hvDeliveryCarDtl = new HvDeliveryCarDtl();
								hvDeliveryCarDtl.setOrderId(entity.getOrderId());
								hvDeliveryCarDtl.setCarNoCls(entity.getCarNoCls());
								hvDeliveryCarDtl.setDoSeq(entity.getDoSeq());
								hvDeliveryCarDtl.setBalCnt(entity.getBalCnt());
								hvDeliveryCarDtl.setBalWgt(entity.getBalWgt());
								hvDeliveryCarDtl.setGrade(entity.getGrade());
								hvDeliveryCarDtl.setLot(entity.getLot());
								hvDeliveryCarDtl.setLotNm(entity.getLotNm());
								hvDeliveryCarDtl.setQtyUnit(entity.getQtyUnit());
								hvDeliveryCarDtl.setToQty(entity.getToQty());
								hvDeliveryCarDtl.setTotalQty(entity.getTotalQty());
								hvDeliveryCarDtl.setRemarks(entity.getRmk());
								hvDeliveryCarDtlService.save(hvDeliveryCarDtl);
							
						}
						preOrderId = hvDeliveryExcelTemp.getOrderId();
						preCarNoCls = hvDeliveryExcelTemp.getCarNoCls();
					}catch(ConstraintViolationException ex){
						failureMsg.append("<br/>订单号 "+hvDeliveryExcelTemp.getOrderId()+" 导入失败：");
						List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
						for (String message : messageList){
							failureMsg.append(message+"; ");
							failureNum++;
						}
					}catch (Exception ex) {
						failureMsg.append("<br/>订单号 "+hvDeliveryExcelTemp.getOrderId()+" 导入失败："+ex.getMessage());
					}
				}
					if (failureNum>0){
						failureMsg.insert(0, "，失败 "+failureNum+" 条数据，导入信息如下：");
					}
					addMessage(redirectAttributes, "已成功导入 "+successNum+" 条数据");
				}
			}
			
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/huvis/delivery/list?repage";
    }
    
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "发货单数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            HvDeliveryExcelTemp hvDeliveryExcelTemp = new HvDeliveryExcelTemp();
            if(hvDeliveryMst.getOrderId()!=null){
            	hvDeliveryExcelTemp.setOrderId(hvDeliveryMst.getOrderId());
            }
            if(hvDeliveryMst.getOutDay()!=null){
            	hvDeliveryExcelTemp.setOutDay(myFmt2.format(hvDeliveryMst.getOutDay()));
            }
            if(hvDeliveryMst.getCarNo()!=null){
            	hvDeliveryExcelTemp.setCarNo(hvDeliveryMst.getCarNo());
            }
        	hvDeliveryMst.setState(HvConstant.CAR_STATE_01); //默认显示待分配
        	hvDeliveryExcelTemp.setState(hvDeliveryMst.getState());
            
            
            Page<HvDeliveryExcelTemp> page = hvDeliveryExcelTempService.findHvDeliveryExcelTemp(new Page<HvDeliveryExcelTemp>(request, response, -1), hvDeliveryExcelTemp);
    		new ExportExcel("发货单数据", HvDeliveryExcelTemp.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出发货单数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/huvis/delivery/list?repage";
    }
	
	@RequestMapping(value = {"list", ""})
	public String list(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(hvDeliveryMst.getState()==null){
			hvDeliveryMst.setState(HvConstant.CAR_STATE_01); //默认显示待分配
		}
        Page<HvDeliveryMst> page = hvDeliveryMstService.findPage(new Page<HvDeliveryMst>(request, response), hvDeliveryMst, true); 
        model.addAttribute("page", page);
		return "modules/huvis/deliveryList";
	}
	
	@RequestMapping(value = {"form", ""})
	public String form(HvDeliveryMst hvDeliveryMst, Model model) {
		HvDeliveryCarDtl hvDeliveryCarDtl = new HvDeliveryCarDtl();
		hvDeliveryCarDtl.setOrderId(hvDeliveryMst.getOrderId());
		hvDeliveryCarDtl.setCarNoCls(hvDeliveryMst.getCarNoCls());
		List<HvDeliveryCarDtl> hvDeliveryCarDtlList = hvDeliveryCarDtlService.findAllList(hvDeliveryCarDtl);
		hvDeliveryMst.setHvDeliveryCarDtl(hvDeliveryCarDtlList);
		model.addAttribute("hvDeliveryMst",hvDeliveryMst);
		return "modules/huvis/deliveryForm";
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
	public String save(HvDeliveryMst hvDeliveryMst, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, hvDeliveryMst)){
			return form(hvDeliveryMst, model);
		}
		hvDeliveryMstService.save(hvDeliveryMst);
		addMessage(redirectAttributes, "保存成功,订单号：'" + hvDeliveryMst.getOrderId() + "'");
	    return "redirect:" + adminPath + "/huvis/delivery/list?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(HvDeliveryMst hvDeliveryMst, String id, RedirectAttributes redirectAttributes) {
		// 如果没有审核权限，则不允许删除或发布。
		hvDeliveryMstService.delete(hvDeliveryMst);
		HvDeliveryCarDtl hvDeliveryCarDtl = new HvDeliveryCarDtl();
		hvDeliveryCarDtl.setOrderId(hvDeliveryMst.getOrderId());
		hvDeliveryCarDtl.setCarNoCls(hvDeliveryMst.getCarNoCls());
		hvDeliveryCarDtlService.deleteByMstId(hvDeliveryCarDtl);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/huvis/delivery/list?repage";
	}
	
	
	@RequestMapping(value = {"carStateList", ""})
	public String getCarState(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(hvDeliveryMst.getState()==null){
			hvDeliveryMst.setState(HvConstant.CAR_STATE_02); //默认显示待分配
		}
	 Page<HvDeliveryMst> page = hvDeliveryMstService.getCarState(new Page<HvDeliveryMst>(request, response), hvDeliveryMst, true); 
        model.addAttribute("page", page);
		return "modules/huvis/carStateList";
	}
	
	@RequestMapping(value = "carStateSave")
	public String carStateSave(HvDeliveryMst hvDeliveryMst,String state, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if(hvDeliveryMst.getState()==null){
			hvDeliveryMst.setState(HvConstant.CAR_STATE_02); //默认显示已分配
		}
		if(HvConstant.CAR_STATE_03.equals(state)){
			hvDeliveryMst.setState(HvConstant.CAR_STATE_03);
			hvDeliveryMst.setFactoryInTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		}
		if(HvConstant.CAR_STATE_04.equals(state)){
			hvDeliveryMst.setState(HvConstant.CAR_STATE_04);
			hvDeliveryMst.setFactoryOutTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		}
		hvDeliveryMstService.save(hvDeliveryMst);
		addMessage(redirectAttributes, "保存成功");
	    return "redirect:" + adminPath + "/huvis/delivery/carStateList?repage";
	}
	
	
	
	@RequestMapping(value = {"allotCompForm", ""})
	public String saveLogisticsComp(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, Model model) {
		HvDeliveryCarDtl hvDeliveryCarDtl = new HvDeliveryCarDtl();
		hvDeliveryCarDtl.setOrderId(hvDeliveryMst.getOrderId());
		hvDeliveryCarDtl.setCarNoCls(hvDeliveryMst.getCarNoCls());
		List<HvDeliveryCarDtl> hvDeliveryCarDtlList = hvDeliveryCarDtlService.findAllList(hvDeliveryCarDtl);
		hvDeliveryMst.setHvDeliveryCarDtl(hvDeliveryCarDtlList);
		model.addAttribute("hvDeliveryMst",hvDeliveryMst);
		return "modules/huvis/allotLogisticsCompForm";
	}
	
	@RequestMapping(value = {"allotCompList", ""})
	public String getLogisticsComp(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(hvDeliveryMst.getState()==null){
			hvDeliveryMst.setState(HvConstant.CAR_STATE_01); //默认显示待分配
		}
		Page<HvDeliveryMst> page = hvDeliveryMstService.findPage(new Page<HvDeliveryMst>(request, response), hvDeliveryMst, true); 
        model.addAttribute("page", page);
		return "modules/huvis/allotLogisticsCompList";
	}
	
	@RequestMapping(value = "allotCompFormSave")
	public String allotCompFormSave(HvDeliveryMst hvDeliveryMst, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, hvDeliveryMst)){
			return form(hvDeliveryMst, model);
		}
		hvDeliveryMstService.save(hvDeliveryMst);
		addMessage(redirectAttributes, "分配物流公司成功");
	    return "redirect:" + adminPath + "/huvis/delivery/allotCompList?repage";
	}
	
	
	@RequestMapping(value = {"allotCarForm", ""})
	public String saveAllotCar(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(hvDeliveryMst.getState()==null){
			hvDeliveryMst.setState(HvConstant.CAR_STATE_02); //默认显示待分配
		}
		Page<HvDeliveryMst> page = hvDeliveryMstService.findPage(new Page<HvDeliveryMst>(request, response), hvDeliveryMst, true); 
        model.addAttribute("page", page);
		return "modules/huvis/allotCarForm";
	}
	
	@RequestMapping(value = {"allotCarList", ""})
	public String getAllotCar(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(hvDeliveryMst.getState()==null){
			hvDeliveryMst.setState(HvConstant.CAR_STATE_01); //默认显示待分配
		}
		Page<HvDeliveryMst> page = hvDeliveryMstService.findPage(new Page<HvDeliveryMst>(request, response), hvDeliveryMst, true); 
        model.addAttribute("page", page);
		return "modules/huvis/allotCarList";
	}
	
	
	@RequestMapping(value = {"carManageList", ""})
	public String getCarManageList(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(hvDeliveryMst.getState()==null){
			hvDeliveryMst.setState(HvConstant.CAR_STATE_04); //默认显示运输中的列表
		}
		Page<HvDeliveryMst> page = hvDeliveryMstService.findPage(new Page<HvDeliveryMst>(request, response), hvDeliveryMst, true); 
        model.addAttribute("page", page);
		return "modules/huvis/carManageList";
	}
	
	@RequestMapping(value = {"carManageDtl", ""})
	public String getCarManageDtl(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, Model model) {
		HvDeliveryCarDtl hvDeliveryCarDtl = new HvDeliveryCarDtl();
		hvDeliveryCarDtl.setOrderId(hvDeliveryMst.getOrderId());
		hvDeliveryCarDtl.setCarNoCls(hvDeliveryMst.getCarNoCls());
		List<HvDeliveryCarDtl> hvDeliveryCarDtlList = hvDeliveryCarDtlService.findAllList(hvDeliveryCarDtl);
		hvDeliveryMst.setHvDeliveryCarDtl(hvDeliveryCarDtlList);
		
		HvCarLocationDtl hvCarLocationDtl = new HvCarLocationDtl();
		hvCarLocationDtl.setOrderMstId(hvDeliveryMst.getId());
		List<HvCarLocationDtl> hvCarLocationDtlList = hvCarLocationDtlService.findAllList(hvCarLocationDtl);
		hvDeliveryMst.setHvCarLocationDtl(hvCarLocationDtlList);
		
		model.addAttribute("hvDeliveryMst",hvDeliveryMst);
		return "modules/huvis/carManageDtl";
	}
	
	@ResponseBody
	@RequestMapping(value = "locationList")
	public HvDeliveryMst getLocationList(@RequestParam(required=false) String id, HttpServletResponse response) {
		HvDeliveryMst hvDeliveryMst = new HvDeliveryMst();
		hvDeliveryMst.setId(id);
		HvDeliveryCarDtl hvDeliveryCarDtl = new HvDeliveryCarDtl();
		hvDeliveryCarDtl.setOrderId(hvDeliveryMst.getOrderId());
		hvDeliveryCarDtl.setCarNoCls(hvDeliveryMst.getCarNoCls());
		List<HvDeliveryCarDtl> hvDeliveryCarDtlList = hvDeliveryCarDtlService.findAllList(hvDeliveryCarDtl);
		hvDeliveryMst.setHvDeliveryCarDtl(hvDeliveryCarDtlList);
		
		HvCarLocationDtl hvCarLocationDtl = new HvCarLocationDtl();
		hvCarLocationDtl.setOrderMstId(hvDeliveryMst.getId());
		List<HvCarLocationDtl> hvCarLocationDtlList = hvCarLocationDtlService.findAllList(hvCarLocationDtl);
		hvDeliveryMst.setHvCarLocationDtl(hvCarLocationDtlList);
		
		return hvDeliveryMst;
	}
	
	@RequestMapping(value = {"carDriverList", ""})
	public String getCarDriverList(HvDeliveryMst hvDeliveryMst, HttpServletRequest request, HttpServletResponse response, Model model) {
//		User user = UserUtils.getUser();
//		if (!user.isAdmin() && !SecurityUtils.getSubject().isPermitted("cms:link:audit")){
//			link.setUser(user);
//		}
		Page<HvDeliveryMst> page = hvDeliveryMstService.findPage(new Page<HvDeliveryMst>(request, response), hvDeliveryMst, true); 
        model.addAttribute("page", page);
		return "modules/huvis/carDriverList";
	}
	
	
	
}
