package com.thinkgem.jeesite.modules.huvis.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class HvDeliveryExcelTemp  extends DataEntity<HvDeliveryExcelTemp> {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -6068277092446620117L;
	private String orderId;
	private String outDay;
	private String plNm;
	private String plCd;
	private String titoCd;
	private String carNoCls;
	private String carNo;
	private String drvName;
	private String drvPhone;
	private String province;
	private String provinceCd;
	private String delAddr;
	private String cusCd;
	private String delPlace;
	private String doSeq;
	private String lot;
	private String lotNm;
	private String grade;
	private String balWgt;
	private String balCnt;
	private String qtyUnit;
	private String toQty;
	private String logisticsCd;
	private String logistics;
	private String totalQty;
	private String empNo;
	private String empNm;
	private String rmk;
	private String state;
	
	@NotNull(message="订单号不能为空")
	@ExcelField(title="订单号", align=2, sort=10)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@NotNull(message="发货时间不能为空")
	@ExcelField(title="发货时间", align=2, sort=50)
	public String getOutDay() {
		return outDay;
	}
	public void setOutDay(String outDay) {
		this.outDay = outDay;
	}
	@ExcelField(title="办事处", align=2, sort=100)
	public String getPlNm() {
		return plNm;
	}
	public void setPlNm(String plNm) {
		this.plNm = plNm;
	}
	@NotNull(message="办事处编号不能为空")
	@ExcelField(title="办事处编号", align=2, sort=150)
	public String getPlCd() {
		return plCd;
	}
	public void setPlCd(String plCd) {
		this.plCd = plCd;
	}
	@NotNull(message="出库类别不能为空")
	@ExcelField(title="出库类别", align=2, sort=200)
	public String getTitoCd() {
		return titoCd;
	}
	public void setTitoCd(String titoCd) {
		this.titoCd = titoCd;
	}
	@NotNull(message="车辆序号不能为空")
	@ExcelField(title="车辆序号", align=2, sort=250)
	public String getCarNoCls() {
		return carNoCls;
	}
	public void setCarNoCls(String carNoCls) {
		this.carNoCls = carNoCls;
	}
	
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@NotNull(message="司机姓名不能为空")
	@ExcelField(title="司机姓名", align=2, sort=350)
	public String getDrvName() {
		return drvName;
	}
	public void setDrvName(String drvName) {
		this.drvName = drvName;
	}
	@NotNull(message="手机电话不能为空")
	@ExcelField(title="手机电话", align=2, sort=300)
	public String getDrvPhone() {
		return drvPhone;
	}
	@NotNull(message="车号不能为空")
	@ExcelField(title="车号", align=2, sort=400)
	public String getCarNo() {
		return carNo;
	}
	public void setDrvPhone(String drvPhone) {
		this.drvPhone = drvPhone;
	}
	@ExcelField(title="省份", align=2, sort=450)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@NotNull(message="省份编号不能为空")
	@ExcelField(title="省份编号", align=2, sort=500)
	public String getProvinceCd() {
		return provinceCd;
	}
	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}
	@NotNull(message="省份编号不能为空")
	@ExcelField(title="地址", align=2, sort=650)
	public String getDelAddr() {
		return delAddr;
	}
	public void setDelAddr(String delAddr) {
		this.delAddr = delAddr;
	}
	@NotNull(message="收货单位编号不能为空")
	@ExcelField(title="收货单位编号", align=2, sort=600)
	public String getCusCd() {
		return cusCd;
	}
	public void setCusCd(String cusCd) {
		this.cusCd = cusCd;
	}
	@ExcelField(title="收货单位", align=2, sort=550)
	public String getDelPlace() {
		return delPlace;
	}
	public void setDelPlace(String delPlace) {
		this.delPlace = delPlace;
	}
	@NotNull(message="批号不能为空")
	@ExcelField(title="批号", align=2, sort=700)
	public String getDoSeq() {
		return doSeq;
	}
	public void setDoSeq(String doSeq) {
		this.doSeq = doSeq;
	}
	@NotNull(message="LOT不能为空")
	@ExcelField(title="LOT", align=2, sort=800)
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	@NotNull(message="产品名称不能为空")
	@ExcelField(title="产品名称", align=2, sort=750)
	public String getLotNm() {
		return lotNm;
	}
	public void setLotNm(String lotNm) {
		this.lotNm = lotNm;
	}
	@NotNull(message="等级不能为空")
	@ExcelField(title="等级", align=2, sort=850)
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@NotNull(message="每包重量（kg）bale 重量不能为空")
	@ExcelField(title="每包重量（kg）bale 重量", align=2, sort=900)
	public String getBalWgt() {
		return balWgt;
	}
	public void setBalWgt(String balWgt) {
		this.balWgt = balWgt;
	}
	@NotNull(message="发货数量(BALE 数量)不能为空")
	@ExcelField(title="发货数量(BALE 数量)", align=2, sort=950)
	public String getBalCnt() {
		return balCnt;
	}
	public void setBalCnt(String balCnt) {
		this.balCnt = balCnt;
	}
	@NotNull(message="重量单位不能为空")
	@ExcelField(title="重量单位", align=2, sort=1000)
	public String getQtyUnit() {
		return qtyUnit;
	}
	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}
	@NotNull(message="总重量不能为空")
	@ExcelField(title="总重量", align=2, sort=1100)
	public String getToQty() {
		return toQty;
	}
	public void setToQty(String toQty) {
		this.toQty = toQty;
	}
	@NotNull(message="运输公司编码不能为空")
	@ExcelField(title="运输公司编码", align=2, sort=1300)
	public String getLogisticsCd() {
		return logisticsCd;
	}
	public void setLogisticsCd(String logisticsCd) {
		this.logisticsCd = logisticsCd;
	}
	@ExcelField(title="运输公司", align=2, sort=1250)
	public String getLogistics() {
		return logistics;
	}
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	@NotNull(message="重量不能为空")
	@ExcelField(title="重量", align=2, sort=1050)
	public String getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}
	@NotNull(message="销售工号不能为空")
	@ExcelField(title="销售工号", align=2, sort=1350)
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
