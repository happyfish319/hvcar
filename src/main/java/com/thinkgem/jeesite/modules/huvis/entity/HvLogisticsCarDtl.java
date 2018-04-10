package com.thinkgem.jeesite.modules.huvis.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class HvLogisticsCarDtl extends DataEntity<HvLogisticsCarDtl> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String logisticsId;
	private String carNo;
	private String drvName;
	private String drvId;
	private String drvType;
	private String drvTel;
	private String carTon;
	private String carType;
	private String carState;
	private Date checkDate;
	private String useYn;
	private User user;		//关联用户
	private Office office;
	private boolean yn;
	private String orderId;
	private String carStateNm;
	private String carTypeNm;
	private String drvTypeNm;
	
	public String getLogisticsId() {
		return logisticsId;
	}
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getDrvName() {
		return drvName;
	}
	public void setDrvName(String drvName) {
		this.drvName = drvName;
	}
	public String getDrvId() {
		return drvId;
	}
	public void setDrvId(String drvId) {
		this.drvId = drvId;
	}
	public String getDrvType() {
		return drvType;
	}
	public void setDrvType(String drvType) {
		this.drvType = drvType;
	}
	public String getDrvTel() {
		return drvTel;
	}
	public void setDrvTel(String drvTel) {
		this.drvTel = drvTel;
	}
	public String getCarTon() {
		return carTon;
	}
	public void setCarTon(String carTon) {
		this.carTon = carTon;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarState() {
		return carState;
	}
	public void setCarState(String carState) {
		this.carState = carState;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public boolean isYn() {
		return yn;
	}
	public void setYn(boolean yn) {
		this.yn = yn;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCarStateNm() {
		return carStateNm;
	}
	public void setCarStateNm(String carStateNm) {
		this.carStateNm = carStateNm;
	}
	public String getCarTypeNm() {
		return carTypeNm;
	}
	public void setCarTypeNm(String carTypeNm) {
		this.carTypeNm = carTypeNm;
	}
	public String getDrvTypeNm() {
		return drvTypeNm;
	}
	public void setDrvTypeNm(String drvTypeNm) {
		this.drvTypeNm = drvTypeNm;
	}
	
}
