package com.thinkgem.jeesite.modules.huvis.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class DayDeliveryReport extends DataEntity<DayDeliveryReport> {
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -641965853034992731L;
	private String orderId;
	private Date outDay;
	private String plNm;
	private String titoCd;
	private String carNoCls;
	private String planCnt;
	private String carNo;
	private String drvName;
	private String drvPhone;
	private String provinceNm;
	private String delAddr;
	private String delPlace;
	private String lot;
	private String lotNm;
	private String doSeq;
	private String grade;
	private String balWgt;
	private String balCnt;
	private String totalQty;
	private String logisticsNm;
	private String toQty;
	private String empNm;
	private String rmk;
	
	@ExcelField(title="订单号", align=2, sort=10)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@ExcelField(title="发货时间", align=2, sort=50)
	public Date getOutDay() {
		return outDay;
	}
	public void setOutDay(Date outDay) {
		this.outDay = outDay;
	}
	@ExcelField(title="办事处", align=2, sort=100)
	public String getPlNm() {
		return plNm;
	}
	public void setPlNm(String plNm) {
		this.plNm = plNm;
	}
	@ExcelField(title="出库类别", align=2, sort=200)
	public String getTitoCd() {
		return titoCd;
	}
	public void setTitoCd(String titoCd) {
		this.titoCd = titoCd;
	}
	@ExcelField(title="车辆序号", align=2, sort=250)
	public String getCarNoCls() {
		return carNoCls;
	}
	public void setCarNoCls(String carNoCls) {
		this.carNoCls = carNoCls;
	}
	@ExcelField(title="计划数量", align=2, sort=350)
	public String getPlanCnt() {
		return planCnt;
	}
	public void setPlanCnt(String planCnt) {
		this.planCnt = planCnt;
	}
	@ExcelField(title="车号", align=2, sort=400)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@ExcelField(title="司机姓名", align=2, sort=350)
	public String getDrvName() {
		return drvName;
	}
	public void setDrvName(String drvName) {
		this.drvName = drvName;
	}
	@ExcelField(title="手机电话", align=2, sort=300)
	public String getDrvPhone() {
		return drvPhone;
	}
	public void setDrvPhone(String drvPhone) {
		this.drvPhone = drvPhone;
	}
	@ExcelField(title="省份", align=2, sort=450)
	public String getProvinceNm() {
		return provinceNm;
	}
	public void setProvinceNm(String provinceNm) {
		this.provinceNm = provinceNm;
	}
	@ExcelField(title="地址", align=2, sort=650)
	public String getDelAddr() {
		return delAddr;
	}
	public void setDelAddr(String delAddr) {
		this.delAddr = delAddr;
	}
	@ExcelField(title="收货单位", align=2, sort=550)
	public String getDelPlace() {
		return delPlace;
	}
	public void setDelPlace(String delPlace) {
		this.delPlace = delPlace;
	}
	
	@ExcelField(title="LOT", align=2, sort=800)
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	@ExcelField(title="批号", align=2, sort=700)
	public String getLotNm() {
		return lotNm;
	}
	public void setLotNm(String lotNm) {
		this.lotNm = lotNm;
	}
	@ExcelField(title="产品名称", align=2, sort=750)
	public String getDoSeq() {
		return doSeq;
	}
	public void setDoSeq(String doSeq) {
		this.doSeq = doSeq;
	}
	@ExcelField(title="等级", align=2, sort=850)
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@ExcelField(title="每包重量（kg）bale 重量", align=2, sort=900)
	public String getBalWgt() {
		return balWgt;
	}
	public void setBalWgt(String balWgt) {
		this.balWgt = balWgt;
	}
	@ExcelField(title="发货数量(BALE 数量)", align=2, sort=950)
	public String getBalCnt() {
		return balCnt;
	}
	public void setBalCnt(String balCnt) {
		this.balCnt = balCnt;
	}
	@ExcelField(title="重量", align=2, sort=1050)
	public String getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}
	@ExcelField(title="运输公司", align=2, sort=1250)
	public String getLogisticsNm() {
		return logisticsNm;
	}
	public void setLogisticsNm(String logisticsNm) {
		this.logisticsNm = logisticsNm;
	}
	@ExcelField(title="总重量", align=2, sort=1100)
	public String getToQty() {
		return toQty;
	}
	public void setToQty(String toQty) {
		this.toQty = toQty;
	}
	@ExcelField(title="销售", align=2, sort=1400)
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	@ExcelField(title="备注", align=2, sort=1450)
	public String getRmk() {
		return rmk;
	}
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
}
