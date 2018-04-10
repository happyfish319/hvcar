package com.thinkgem.jeesite.modules.huvis.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class HvCustCompMst extends DataEntity<HvCustCompMst> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String custCompId;
	private String custCompCd;
	private String custCompNm;
	private String shortNm;
	private String linkMan;
	private String tel1;
	private String tel2;
	private String addr;
	private String remarks;
	private User user;		//关联用户
	private boolean yn;
	private Office office;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public boolean isYn() {
		return yn;
	}
	public void setYn(boolean yn) {
		this.yn = yn;
	}
	public String getCustCompId() {
		return custCompId;
	}
	public void setCustCompId(String custCompId) {
		this.custCompId = custCompId;
	}
	public String getCustCompCd() {
		return custCompCd;
	}
	public void setCustCompCd(String custCompCd) {
		this.custCompCd = custCompCd;
	}
	public String getCustCompNm() {
		return custCompNm;
	}
	public void setCustCompNm(String custCompNm) {
		this.custCompNm = custCompNm;
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
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}

}
