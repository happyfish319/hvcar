package com.thinkgem.jeesite.modules.huvis.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class HvLogisticsCompMst extends DataEntity<HvLogisticsCompMst> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String logisticsCd;
	private String shortNm;
	private String linkMan;
	private String tel1;
	private String tel2;
	private String addr;
	private User user;		//关联用户
	private boolean yn;
	private Office office;
	private String carCnt;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLogisticsCd() {
		return logisticsCd;
	}
	public void setLogisticsCd(String logisticsCd) {
		this.logisticsCd = logisticsCd;
	}
	public String getShortNm() {
		return shortNm;
	}
	public void setShortNm(String shortNm) {
		this.shortNm = shortNm;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public boolean isYn() {
		return yn;
	}
	public void setYn(boolean yn) {
		this.yn = yn;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public String getCarCnt() {
		return carCnt;
	}
	public void setCarCnt(String carCnt) {
		this.carCnt = carCnt;
	}


}
