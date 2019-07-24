package com.lishan.p2p.pojo;

import java.util.Date;

public class Record {
	private Integer id;
	private Date recorddate;
	private String recordtype;
	private Integer uid;
	private Double recordmoney;
	private Integer jyuid;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getJyuid() {
		return jyuid;
	}
	public void setJyuid(Integer jyuid) {
		this.jyuid = jyuid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getRecorddate() {
		return recorddate;
	}
	public void setRecorddate(Date recorddate) {
		this.recorddate = recorddate;
	}
	public String getRecordtype() {
		return recordtype;
	}
	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Double getRecordmoney() {
		return recordmoney;
	}
	public void setRecordmoney(Double recordmoney) {
		this.recordmoney = recordmoney;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", recorddate=" + recorddate + ", recordtype=" + recordtype + ", uid=" + uid
				+ ", recordmoney=" + recordmoney + "]";
	}
	
}
