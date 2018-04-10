package com.thinkgem.jeesite.modules.huvis.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class HvDeliveryCarDtl extends DataEntity<HvDeliveryCarDtl>{

	/** 
	*/ 
	private static final long serialVersionUID = -3105100442500650403L;
	private String orderId;
	private String carNoCls;
	private String mstId;
	private String doSeq;
	private String lot;
	private String lotNm;
	private String grade;
	private String balWgt;
	private String balCnt;
	private String qtyUnit;
	private String toQty;
	private String totalQty;
	private String rmk;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCarNoCls() {
		return carNoCls;
	}
	public void setCarNoCls(String carNoCls) {
		this.carNoCls = carNoCls;
	}
	public String getDoSeq() {
		return doSeq;
	}
	public void setDoSeq(String doSeq) {
		this.doSeq = doSeq;
	}
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getLotNm() {
		return lotNm;
	}
	public void setLotNm(String lotNm) {
		this.lotNm = lotNm;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getBalWgt() {
		return balWgt;
	}
	public void setBalWgt(String balWgt) {
		this.balWgt = balWgt;
	}
	public String getBalCnt() {
		return balCnt;
	}
	public void setBalCnt(String balCnt) {
		this.balCnt = balCnt;
	}
	public String getQtyUnit() {
		return qtyUnit;
	}
	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}
	public String getToQty() {
		return toQty;
	}
	public void setToQty(String toQty) {
		this.toQty = toQty;
	}
	public String getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}
	public String getRmk() {
		return rmk;
	}
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	public String getMstId() {
		return mstId;
	}
	public void setMstId(String mstId) {
		this.mstId = mstId;
	}
	
}
