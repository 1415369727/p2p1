package com.lishan.p2p.pojo;

import java.util.Date;

public class Touzi {
	private Integer id;
	private String orderid;
	private Integer uid;
	private Integer inid;
	private Integer state;
	private Date touzidate;
	private Double touzimoney;
	private Double biaoMoney;
	private Invest invest;
	private User tuser;
	private String tdate;
	
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
	public User getTuser() {
		return tuser;
	}
	public void setTuser(User tuser) {
		this.tuser = tuser;
	}
	public Invest getInvest() {
		return invest;
	}
	public void setInvest(Invest invest) {
		this.invest = invest;
	}
	public Double getBiaoMoney() {
		return biaoMoney;
	}
	public void setBiaoMoney(Double biaoMoney) {
		this.biaoMoney = biaoMoney;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getInid() {
		return inid;
	}
	public void setInid(Integer inid) {
		this.inid = inid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getTouzidate() {
		return touzidate;
	}
	public void setTouzidate(Date touzidate) {
		this.touzidate = touzidate;
	}
	public Double getTouzimoney() {
		return touzimoney;
	}
	public void setTouzimoney(Double touzimoney) {
		this.touzimoney = touzimoney;
	}
	@Override
	public String toString() {
		return "Touzi [id=" + id + ", orderid=" + orderid + ", uid=" + uid + ", inid=" + inid + ", state=" + state
				+ ", touzidate=" + touzidate + ", touzimoney=" + touzimoney + "]";
	}
	
}
