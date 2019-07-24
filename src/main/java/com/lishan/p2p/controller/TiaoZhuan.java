package com.lishan.p2p.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.service.InvestService;

@Controller
@RequestMapping("/tz")
public class TiaoZhuan {
	@Autowired
	private InvestService service;
	/**
	 * 进入首页  查询前4条标
	 * @param m
	 * @return
	 */
	@RequestMapping("/show")
	public String show(Model m) {
		List<Invest> inlist=service.showInvestTopList();
		m.addAttribute("list", inlist);
		return "templates/index";
	}
	/**
	 * 跳转到关于和帮助页面
	 */
	@RequestMapping("/about")
	public String showabout() {
		return "templates/about";
	}
	/**
	 * 跳转投标列表
	 */
	@RequestMapping("/investList")
	public String investList() {
		return "templates/invest-list";
	}
	
	/**
	 * 跳转个人中心
	 */
	@RequestMapping("/showinfo")
	public String shwoinfo() {
		return "templates/member";
	}
	 
	/**
	 * 访问头部
	 */
	@RequestMapping("/header")
	public String  header() {
		return "templates/header";
	}
	/**
	 * 访问底部
	 */
	@RequestMapping("/footer")
	public String  footer() {
		return "templates/footer";
	}
	
}
