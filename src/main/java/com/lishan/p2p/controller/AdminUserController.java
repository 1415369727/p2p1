package com.lishan.p2p.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lishan.p2p.pojo.Admin;
import com.lishan.p2p.pojo.Menu;
import com.lishan.p2p.pojo.Role;
import com.lishan.p2p.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
	@Autowired
	private AdminService service;
	/**
	 * 跳转到后台登陆
	 * @param user
	 * @return
	 */
	@RequestMapping("/showLogin")
	public String showLogin() {
		return "jsp/adminlogin";
	}
	/**
	 * 跳转到欢迎页面
	 */
	@RequestMapping("/welcom")
	public String welcom() {
		return "jsp/welcom";
	}
	/**
	 * 跳转到首页
	 */
	@RequestMapping("/showIndex")
	public String showIndex(HttpSession session,Model m) {
		Admin admin=(Admin)session.getAttribute("admin");
		if(admin==null) {
			return "redirect:/admin/showLogin";
		}
		List<Menu> rmenu=service.showMenus(admin.getRid());
		
		m.addAttribute("rmenu", rmenu);
		return "jsp/adminIndex";
	}
	/**
	 * 跳转到添加用户页面
	 */
	@RequestMapping("/showaddAdmin")
	public String showaddAdmin(Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		List<Role> rlist=service.showRole();
		m.addAttribute("rlist",rlist );
		PageHelper.startPage(pn,6);
		List<Admin> adminss=service.showszRole();
		PageInfo<Admin>  p = new PageInfo<>(adminss);
		m.addAttribute("page", p);
		m.addAttribute("list", adminss);
		return "jsp/addAdmin";
	}
	/**
	 * 退出session
	 */
	@RequestMapping("/adminQueit")
	public String adminQueit(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:showLogin.action";
	}
	/**
	 * 登陆方法
	 */
	@RequestMapping("/adminLogin")
	public String adminLogin(Admin adm,Model m,HttpSession session) {
		Admin admin=service.adminLogin(adm);
		if(admin==null) {
			m.addAttribute("msg","用户名或密码错误，如有问题请联系下方管理员");
			return "forward:showLogin.action";
		}
		session.setAttribute("admin", admin);
		return "redirect:showIndex.action";
	}
	/**
	 * 添加用户
	 */
	@RequestMapping("/addAdmin")
	@ResponseBody
	public String addAdmin(Model m,Integer role,String name,String pass) {
		Admin admin=new Admin();
		admin.setCreattime(new Date());
		admin.setRid(role);admin.setUsername(name);admin.setPassword(pass);
		service.addAdmin(admin);
		return "1";
	}
	/**
	 * 查询角色列表
	 */
	@RequestMapping("/showRole")
	@ResponseBody
	public List<Role> showRole() {
		List<Role> role=service.showRole();
		return role;
	}
	/**
	 * 修改用户信息
	 */
	@RequestMapping("/updateAdmin")
	@ResponseBody
	public String updateAdmin(String name,String pass,Integer id,Model m) {
		Admin admin=new Admin();
		admin.setUsername(name);admin.setPassword(pass);admin.setId(id);
		service.updateAdmin(admin);
		m.addAttribute("msg", "用户信息修改成功，请重新登录");
		return "1";
	}
	/**
	 * 角色权限列表设置页面
	 */
	@RequestMapping("/getRole")
	public String getRoleMenu(Model m,Integer id) {
		List<Role> rolist=service.showRole();
		List<Menu> menu=service.getRoleMenu(id);
		m.addAttribute("rolist", rolist);
		m.addAttribute("rid", id);
		m.addAttribute("menu", menu);
		return "jsp/role";
	}
	/**
	 * 给用户设置角色
	 */
	@RequestMapping("/showszRole")
	public String showszRole(Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		PageHelper.startPage(pn,6);
		List<Role> roleslist=service.showRole();
		PageInfo<Role>  p = new PageInfo<>(roleslist);
		m.addAttribute("page", p);
		m.addAttribute("list", roleslist);
		return "jsp/showszRole";
	}
	/**
	 * 用户注销
	 */
	@RequestMapping("/updateStateZhuXiao")
	@ResponseBody
	public String updateStateZhuXiao(Integer id) {
		service.updateStateZhuXiao(id);
		return "1";
	}
	/**
	 * 恢复用户
	 */
	@RequestMapping("/updateStateHuiFu")
	@ResponseBody
	public String updateStateHuiFu(Integer id) {
		service.updateStateHuiFu(id);
		return "1";
	}
	/**
	 * 修改用户的角色 updateRoleid
	 */
	@RequestMapping("/updateRoleid")
	@ResponseBody
	public String updateRoleid(Integer id,Integer uid) {
		service.updateRoleid(id,uid);
		return "1";
	}
	/**
	 * 查询要修改的角色信息
	 */
	@RequestMapping("/showUpateRoles")
	@ResponseBody
	public Role showUpateRoles(Integer id) {
		Role roles=service.showUpateRoles(id);
		return roles;
	}
	/**
	 * 修改角色信息
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public String updateRole(Integer id,String rolename,String des,Model m) {
		Role role=new Role();
		role.setId(id);role.setRolename(rolename);role.setDes(des);
		service.updateRole(role);
		m.addAttribute("msg", "角色修改成功");
		return "1";
	}
	/**
	 * 添加角色
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public String addRole(Model m,String name,String des) {
		Role role=new Role();
		role.setRolename(name);role.setDes(des);
		service.addRole(role);
		return "1";
	}
	/**
	 * 初始化密码
	 */
	@RequestMapping("/updatePass")
	@ResponseBody
	public String updatePass(Integer id) {
		service.updatePass(id);
		return "1";
	}
	/**
	 * 删除角色 deleteRole
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public String deleteRole(Integer id) {
		service.deleteRole(id);
		return "1";
	}
	/**
	 * 添加修改权限
	 * @return
	 */
	@RequestMapping("/updateRoleMenu")
	public String updateRoleMenu(Integer roleid,Integer [] ids,Model m) {
		service.updateRoleMenu(roleid,ids);
		m.addAttribute("msg", "权限修改成功");
		return "forward:getRole.action?id=1";
	}
	/**
	 * 查询菜单管理
	 */
	@RequestMapping("/showSZMenu")
	public String showSZMenu(Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		
		PageHelper.startPage(pn,6);
		List<Menu> mlist=service.showSZMenu();
		PageInfo<Menu>  p = new PageInfo<>(mlist);
		m.addAttribute("page", p);
		List<Menu> mls=service.showmenuls();
		m.addAttribute("mls", mls);
		m.addAttribute("list", mlist);
		return "jsp/showSZMenu";
	}
	/**
	 * 查询父级菜单
	 */
	@RequestMapping("/showFuMenu")
	@ResponseBody
	public List<Menu> showFuMenu(Model m) {
		List<Menu> menuls=service.showmenuls();
		return menuls;
	}
	/**
	 * 添加菜单
	 */
	@RequestMapping("/insertMenu")
	@ResponseBody
	public String insertMenu(String menu,String url,String des,String icon,Integer pid,Model m) {
		Menu me=new Menu();
		me.setMenu(menu);me.setUrl(url);me.setDes(des);me.setIcon(icon);me.setPid(pid);
		service.insertMenu(me);
		m.addAttribute("tjmsg", "菜单添加成功");
		return "1";
	}
	
	/**
	 * 删除菜单
	 */
	@RequestMapping("/deleteMenu")
	@ResponseBody
	public String deleteMenu(Integer id) {
		service.deleteMenu(id);
		return "1";
	}
	/**
	 * 根据菜单id查询菜单信息
	 */
	@RequestMapping("/showUpateMenus")
	@ResponseBody
	public Menu showUpateMenus(Integer id,Model m) {
		List<Menu> rl=service.showmenuls();
		m.addAttribute("rl", rl);
		Menu menu=service.showUpateMenus(id);
		return menu;
	}
	/**
	 * 修改菜单信息
	 */
	@RequestMapping("/updateMenu")
	@ResponseBody
	public String updateMenu(String menu,String url,String des,String icon,Integer pid,Integer mids) {
		System.out.println(url);
		Menu me=new Menu();
		me.setMenu(menu);me.setUrl(url);me.setDes(des);me.setIcon(icon);me.setPid(pid);me.setId(mids);
		service.updateMenu(me);
		return "1";
	}
	
	
}
