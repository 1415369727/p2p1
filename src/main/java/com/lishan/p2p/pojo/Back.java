package com.lishan.p2p.pojo;

import java.util.Date;

public class Back {
	private Integer id;
	private Integer uid;
	private Integer invid;
	private Double huankmoney;
	private Date huankdate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getInvid() {
		return invid;
	}
	public void setInvid(Integer invid) {
		this.invid = invid;
	}
	public Double getHuankmoney() {
		return huankmoney;
	}
	public void setHuankmoney(Double huankmoney) {
		this.huankmoney = huankmoney;
	}
	public Date getHuankdate() {
		return huankdate;
	}
	public void setHuankdate(Date huankdate) {
		this.huankdate = huankdate;
	}
}
