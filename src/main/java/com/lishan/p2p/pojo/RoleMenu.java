package com.lishan.p2p.pojo;

import java.util.List;

public class RoleMenu {
	private Integer id;
	private Integer rid;
	private Integer mid;
	private List<Menu> menus;
	
	
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "RoleMenu [id=" + id + ", rid=" + rid + ", mid=" + mid + ", menus=" + menus + "]";
	}
	
}
