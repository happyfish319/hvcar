package com.thinkgem.jeesite.modules.huvis.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class HvDeliveryMst extends DataEntity<HvDeliveryMst> {
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -641965853034992731L;
	private String tempId;
	private String orderId;
	private Date outDay;
	private String plCd;
	private String plNm;
	private String titoCd;
	private String carNoCls;
	private String state;
	private String carNo;
	private String logisticsCd;
	private String logisticsNm;
	private String factoryOutTime;
	private String factoryInTime;
	private String endTime;
	private String drvName;
	private String drvPhone;
	private String provinceCd;
	private String provinceNm;
	private String delAddr;
	private String cusCd;
	private String delPlace;
	private String empNo;
	private String empNm;
	private Boolean warnYn;
	private String warnCnt;
	private String warnOptionTime;
	private String warnDistance;
	private String imgUrl;
	private String stateNm;
	
	private User user;		//关联用户
	private List<HvDeliveryCarDtl> hvDeliveryCarDtl;
	private List<HvCarLocationDtl> hvCarLocationDtl;
	
	private Office office;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOutDay() {
		return outDay;
	}
	public void setOutDay(Date outDay) {
		this.outDay = outDay;
	}
	public String getPlCd() {
		return plCd;
	}
	public void setPlCd(String plCd) {
		this.plCd = plCd;
	}
	public String getTitoCd() {
		return titoCd;
	}
	public void setTitoCd(String titoCd) {
		this.titoCd = titoCd;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public String getCarNoCls() {
		return carNoCls;
	}
	public void setCarNoCls(String carNoCls) {
		this.carNoCls = carNoCls;
	}
	public String getPlNm() {
		return plNm;
	}
	public void setPlNm(String plNm) {
		this.plNm = plNm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getLogisticsCd() {
		return logisticsCd;
	}
	public void setLogisticsCd(String logisticsCd) {
		this.logisticsCd = logisticsCd;
	}
	public String getFactoryOutTime() {
		return factoryOutTime;
	}
	public void setFactoryOutTime(String factoryOutTime) {
		this.factoryOutTime = factoryOutTime;
	}
	public String getFactoryInTime() {
		return factoryInTime;
	}
	public void setFactoryInTime(String factoryInTime) {
		this.factoryInTime = factoryInTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDrvName() {
		return drvName;
	}
	public void setDrvName(String drvName) {
		this.drvName = drvName;
	}
	public String getDrvPhone() {
		return drvPhone;
	}
	public void setDrvPhone(String drvPhone) {
		this.drvPhone = drvPhone;
	}
	public String getProvinceCd() {
		return provinceCd;
	}
	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}
	public String getDelAddr() {
		return delAddr;
	}
	public void setDelAddr(String delAddr) {
		this.delAddr = delAddr;
	}
	public String getCusCd() {
		return cusCd;
	}
	public void setCusCd(String cusCd) {
		this.cusCd = cusCd;
	}
	public String getDelPlace() {
		return delPlace;
	}
	public void setDelPlace(String delPlace) {
		this.delPlace = delPlace;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	public String getWarnOptionTime() {
		return warnOptionTime;
	}
	public void setWarnOptionTime(String warnOptionTime) {
		this.warnOptionTime = warnOptionTime;
	}
	public List<HvDeliveryCarDtl> getHvDeliveryCarDtl() {
		return hvDeliveryCarDtl;
	}
	public void setHvDeliveryCarDtl(List<HvDeliveryCarDtl> hvDeliveryCarDtl) {
		this.hvDeliveryCarDtl = hvDeliveryCarDtl;
	}
	public Boolean getWarnYn() {
		return warnYn;
	}
	public void setWarnYn(Boolean warnYn) {
		this.warnYn = warnYn;
	}
	public String getLogisticsNm() {
		return logisticsNm;
	}
	public void setLogisticsNm(String logisticsNm) {
		this.logisticsNm = logisticsNm;
	}
	public String getProvinceNm() {
		return provinceNm;
	}
	public void setProvinceNm(String provinceNm) {
		this.provinceNm = provinceNm;
	}
	public String getWarnDistance() {
		return warnDistance;
	}
	public void setWarnDistance(String warnDistance) {
		this.warnDistance = warnDistance;
	}
	public List<HvCarLocationDtl> getHvCarLocationDtl() {
		return hvCarLocationDtl;
	}
	public void setHvCarLocationDtl(List<HvCarLocationDtl> hvCarLocationDtl) {
		this.hvCarLocationDtl = hvCarLocationDtl;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getStateNm() {
		return stateNm;
	}
	public void setStateNm(String stateNm) {
		this.stateNm = stateNm;
	}
	public String getWarnCnt() {
		return warnCnt;
	}
	public void setWarnCnt(String warnCnt) {
		this.warnCnt = warnCnt;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	
}
