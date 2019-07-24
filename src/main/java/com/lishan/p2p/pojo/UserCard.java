package com.lishan.p2p.pojo;

public class UserCard {
	private Integer bid;
	private Integer uid;
	private String cnumber;
	private Double money;
	private String jypass;
	private Bank bank;
	
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public String getJypass() {
		return jypass;
	}
	public void setJypass(String jypass) {
		this.jypass = jypass;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "UserCard [bid=" + bid + ", uid=" + uid + ", cnumber=" + cnumber + ", money=" + money + ", jypass="
				+ jypass + ", bank=" + bank + "]";
	}
	
}
