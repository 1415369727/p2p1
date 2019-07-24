package com.lishan.p2p.pojo;

import java.util.Date;


public class Borrow {
	private Integer id;
	private Integer uid;
	private Double jemoney;
	private Date sqdate;
	private Double rate;
	private Integer tlimit;
	private Integer state;
	private String tel;
	private Integer yuecom;
	private User users;
	
	public Date getSqdate() {
		return sqdate;
	}
	public void setSqdate(Date sqdate) {
		this.sqdate = sqdate;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public Integer getYuecom() {
		return yuecom;
	}
	public void setYuecom(Integer yuecom) {
		this.yuecom = yuecom;
	}
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
	public Double getJemoney() {
		return jemoney;
	}
	public void setJemoney(Double jemoney) {
		this.jemoney = jemoney;
	}
	
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getTlimit() {
		return tlimit;
	}
	public void setTlimit(Integer tlimit) {
		this.tlimit = tlimit;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Borrow [id=" + id + ", uid=" + uid + ", jemoney=" + jemoney + ", sqdate=" + sqdate + ", rate=" + rate
				+ ", tlimit=" + tlimit + ", state=" + state + ", tel=" + tel + ", yuecom=" + yuecom + ", user=" + users
				+ "]";
	}
	
}
