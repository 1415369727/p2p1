package com.lishan.p2p.pojo;

public class Bank {
	private Integer id;
	private String bname;
	private String image;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Bank [id=" + id + ", bname=" + bname + ", image=" + image + "]";
	}
	
}
