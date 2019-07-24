package com.lishan.p2p.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lishan.p2p.pojo.Admin;
import com.lishan.p2p.pojo.Menu;
import com.lishan.p2p.pojo.Role;

public interface AdminMapper {
	//用户登录
	Admin adminLogin(Admin adm);
	//添加用户
	void addAdmin(Admin admin);
	//查询角色列表
	List<Role> showRole();
	//修改用户信息
	void updateAdmin(Admin admin);
	//查询菜单列表
	List<Menu> getRoleMenu(Integer id);
	//登陆查询菜单列表
	List<Menu> showMenus(Integer rid);
	//查询用户列表，设置角色
	List<Admin> showszRole();
	//注销用户
	void updateStateZhuXiao(Integer id);
	//恢复用户
	void updateStateHuiFu(Integer id);
	//修改用户角色
	void updateRoleid(@Param("id")Integer id, @Param("uid")Integer uid);
	//查询要修改的角色信息
	Role showUpateRoles(Integer id);
	//修改角色信息
	void updateRole(Role role);
	//添加角色
	void addRole(Role role);
	//初始化密码
	void updatePass(Integer id);
	//用户表权限设置为空
	void updateRidnull(Integer id);
	//删除角色
	void deleteRole(Integer id);
	//删除rolemenu中原有的权限
	void deleteYRole(Integer roleid);
	//添加权限
	void insertNRole(@Param("rid")Integer roleid,@Param("ids") Integer[] ids);
	//查询菜单列表
	List<Menu> showSZMenu();
	//查询父菜单
	List<Menu> showmenuls();
	//添加菜单
	void insertMenu(Menu me);
	//删除菜单
	void deleteMenu(Integer id);
	//删除中间表菜单
	void deleteRoleMenu(Integer id);
	//查询菜单信息
	Menu showUpateMenus(Integer id);
	//修改菜单信息
	void updateMenu(Menu me);

}
