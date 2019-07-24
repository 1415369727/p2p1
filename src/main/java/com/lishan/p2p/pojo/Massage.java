package com.lishan.p2p.pojo;

import java.util.Date;

public class Massage {
	private Integer id;
	private Integer uid;
	private String des;
	private Date msgdate;
	private Integer state;
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
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Date getMsgdate() {
		return msgdate;
	}
	public void setMsgdate(Date msgdate) {
		this.msgdate = msgdate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Massage [id=" + id + ", uid=" + uid + ", des=" + des + ", msgdate=" + msgdate + ", state=" + state
				+ "]";
	}
	
}
