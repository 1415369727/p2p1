package com.lishan.p2p.pojo;

import java.util.List;

public class Menu {
	private Integer id;
	private String menu;
	private Integer pid;
	private String url;
	private String des;
	private String icon;
	private List<Menu> lmenu;
	private RoleMenu rmenu;
	
	public RoleMenu getRmenu() {
		return rmenu;
	}
	public void setRmenu(RoleMenu rmenu) {
		this.rmenu = rmenu;
	}
	public List<Menu> getLmenu() {
		return lmenu;
	}
	public void setLmenu(List<Menu> lmenu) {
		this.lmenu = lmenu;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menu=" + menu + ", pid=" + pid + ", url=" + url + ", des=" + des + ", icon=" + icon
				+ ", lmenu=" + lmenu + ", rmenu=" + rmenu + "]";
	}
	
}
