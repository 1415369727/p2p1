package com.lishan.p2p.pojo;

import java.util.Date;
import java.util.List;

public class Invest {
	private Integer id;
	private String proname;
	private Date starttime;
	private Date endtime;
	private Integer biaolimit;
	private Integer state;
	private Date biaodate;
	private Double jemoney;
	private Integer bid;
	private Borrow borrow;
	private String des;
	private String xydj;
	private List<Touzi> touzi;
	private Integer uid;
	private Double touziMoney;
	private Double backMoney;
	
	private User iuser;
	
	public User getIuser() {
		return iuser;
	}
	public void setIuser(User iuser) {
		this.iuser = iuser;
	}
	public Double getBackMoney() {
		return backMoney;
	}
	public void setBackMoney(Double backMoney) {
		this.backMoney = backMoney;
	}
	public Double getTouziMoney() {
		return touziMoney;
	}
	public void setTouziMoney(Double touziMoney) {
		this.touziMoney = touziMoney;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public List<Touzi> getTouzi() {
		return touzi;
	}
	public void setTouzi(List<Touzi> touzi) {
		this.touzi = touzi;
	}
	public String getXydj() {
		return xydj;
	}
	public void setXydj(String xydj) {
		this.xydj = xydj;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Borrow getBorrow() {
		return borrow;
	}
	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Integer getBiaolimit() {
		return biaolimit;
	}
	public void setBiaolimit(Integer biaolimit) {
		this.biaolimit = biaolimit;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getBiaodate() {
		return biaodate;
	}
	public void setBiaodate(Date biaodate) {
		this.biaodate = biaodate;
	}
	public Double getJemoney() {
		return jemoney;
	}
	public void setJemoney(Double jemoney) {
		this.jemoney = jemoney;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "Invest [id=" + id + ", proname=" + proname + ", starttime=" + starttime + ", endtime=" + endtime
				+ ", biaolimit=" + biaolimit + ", state=" + state + ", biaodate=" + biaodate + ", jemoney=" + jemoney
				+ ", bid=" + bid + "]";
	}
	
}
