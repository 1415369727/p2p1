package com.lishan.p2p.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lishan.p2p.mapper.AdminMapper;
import com.lishan.p2p.pojo.Admin;
import com.lishan.p2p.pojo.Menu;
import com.lishan.p2p.pojo.Role;
import com.lishan.p2p.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper dao;
	/**
	 * 用户登录
	 */
	@Override
	public Admin adminLogin(Admin adm) {
		return dao.adminLogin(adm);
	}
	/**
	 * 添加用户
	 */
	@Override
	public void addAdmin(Admin admin) {
		dao.addAdmin(admin);
	}
	/**
	 * 查询角色列表
	 */
	@Override
	public List<Role> showRole() {
		return dao.showRole();
	}
	/**
	 * 修改用户信息
	 */
	@Override
	public void updateAdmin(Admin admin) {
		dao.updateAdmin(admin);
	}
	/**
	 * 查询菜单列表
	 */
	@Override
	public List<Menu> getRoleMenu(Integer id) {
		return dao.getRoleMenu(id);
	}
	/**
	 * 登陆查询菜单列表
	 */
	@Override
	public List<Menu> showMenus(Integer uid) {
		return dao.showMenus(uid);
	}
	/**
	 * 查询用户列表设置角色
	 */
	@Override
	public List<Admin> showszRole() {
		return dao.showszRole();
	}
	/**
	 * 注销用户
	 */
	@Override
	public void updateStateZhuXiao(Integer id) {
		dao.updateStateZhuXiao(id);
	}
	/**
	 * 恢复用户
	 */
	@Override
	public void updateStateHuiFu(Integer id) {
		dao.updateStateHuiFu(id);
	}
	/**
	 * 用户修改角色
	 */
	@Override
	public void updateRoleid(Integer id, Integer uid) {
		dao.updateRoleid(id,uid);
	}
	/**
	 * 查询要修改的角色信息
	 */
	@Override
	public Role showUpateRoles(Integer id) {
		return dao.showUpateRoles(id);
	}
	/**
	 * 修改角色信息
	 */
	@Override
	public void updateRole(Role role) {
		dao.updateRole(role);
	}
	/**
	 * 添加角色
	 */
	@Override
	public void addRole(Role role) {
		dao.addRole(role);
	}
	/**
	 * 初始化密码
	 */
	@Override
	public void updatePass(Integer id) {
		dao.updatePass(id);
	}
	/**
	 * 删除角色
	 */
	@Override
	public void deleteRole(Integer id) {
		dao.updateRidnull(id);
		dao.deleteRole(id);
	}
	/**
	 * 修改权限
	 */
	@Override
	public void updateRoleMenu(Integer roleid, Integer[] ids) {
		//删除原有的权限
		dao.deleteYRole(roleid);
		//添加新权限
		dao.insertNRole(roleid,ids);
	}
	/**
	 * 查询菜单列表
	 */
	@Override
	public List<Menu> showSZMenu() {
		return dao.showSZMenu();
	}
	/**
	 * 查询父菜单
	 */
	@Override
	public List<Menu> showmenuls() {
		return dao.showmenuls();
	}
	/**
	 * 添加菜单
	 */
	@Override
	public void insertMenu(Menu me) {
		dao.insertMenu(me);
	}
	/**
	 * 删除菜单
	 */
	@Override
	public void deleteMenu(Integer id) {
		//删除rolemenu
		dao.deleteRoleMenu(id);
		//删除菜单
		dao.deleteMenu(id);
	}
	/**
	 * 查询菜单信息
	 */
	@Override
	public Menu showUpateMenus(Integer id) {
		return dao.showUpateMenus(id);
	}
	/**
	 * 修改菜单信息
	 */
	@Override
	public void updateMenu(Menu me) {
		dao.updateMenu(me);
	}
	
}
