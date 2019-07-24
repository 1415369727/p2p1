package com.lishan.p2p.pojo;

import java.util.Date;


public class User {

	private Integer id;
	private String username;
	private String password;
	private String sex;
	private String name;
	private String address;
	private Date birthday;
	private String carID;
	private Double yuemoney;
	private String phone;
	private String email;
	private String state;
	private String dealpass;
	private Double djmoney;
	private Double shouyimoney;
	
	private Double users;
	
	
	public Double getUsers() {
		return users;
	}
	public void setUsers(Double users) {
		this.users = users;
	}
	public Double getShouyimoney() {
		return shouyimoney;
	}
	public void setShouyimoney(Double shouyimoney) {
		this.shouyimoney = shouyimoney;
	}
	public Double getDjmoney() {
		return djmoney;
	}
	public void setDjmoney(Double djmoney) {
		this.djmoney = djmoney;
	}
	public String getDealpass() {
		return dealpass;
	}
	public void setDealpass(String dealpass) {
		this.dealpass = dealpass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCarID() {
		return carID;
	}
	public void setCarID(String carID) {
		this.carID = carID;
	}
	public Double getYuemoney() {
		return yuemoney;
	}
	public void setYuemoney(Double yuemoney) {
		this.yuemoney = yuemoney;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", sex=" + sex + ", name="
				+ name + ", address=" + address + ", birthday=" + birthday + ", carID=" + carID + ", yuemoney="
				+ yuemoney + ", phone=" + phone + "]";
	}
	
}
