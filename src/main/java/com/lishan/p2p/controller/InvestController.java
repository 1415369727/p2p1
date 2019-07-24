package com.lishan.p2p.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.pojo.User;
import com.lishan.p2p.service.InvestService;

@Controller
@RequestMapping("/invest")
public class InvestController {
	@Autowired
	private InvestService service;
	
	
	
	
	/**
	 * 前台查询我的标列表
	 */
	@RequestMapping("/myInvest")
	public String myInvest(String start,String end,Integer state,Model m,HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		User user=(User)session.getAttribute("user");
		session.setAttribute("instate", state);
		session.setAttribute("istart", start);
		session.setAttribute("iend", end);
		if(user==null) {
			m.addAttribute("msg", "您还没有登陆");
			return "forward:/user/showmember";
		}
		PageHelper.startPage(pn,6);
		List<Invest> invlist=service.getMyInvest(user.getId(),state,start,end);
		PageInfo<Invest>  p = new PageInfo<>(invlist);
		//查询我的借款个数
		int countinv=service.getCountInve(user.getId());
		m.addAttribute("countinv", countinv);
		//查询的总借款金额
		Double zonginvestmoney=service.getZongInvestMoney(user.getId());
		System.out.println("用户id"+user.getId());
		System.out.println(zonginvestmoney);
		m.addAttribute("zonginvestmoney", zonginvestmoney);
		//我的借款中项目个数
		int jeKunZhong=service.jeKunZhong(user.getId());
		m.addAttribute("jeKunZhong", jeKunZhong);
		//查询还款中
		int HuanKunZhong=service.HuanKunZhong(user.getId());
		m.addAttribute("HuanKunZhong", HuanKunZhong);
		//查询已还款
		int YiHuanKun=service.YiHuanKun(user.getId());
		m.addAttribute("YiHuanKun", YiHuanKun);
		m.addAttribute("page", p);
		m.addAttribute("invlist", invlist);
		return "templates/borrowList";
	}
	/**
	 * 跳转投标详情列表
	 */
	@RequestMapping("/showinvest")
	public String showinvest(Integer id,Model m) {
		Invest invest=service.showinvestById(id);
		//剩余时间
		Date date=invest.getBiaodate();
		int lim=invest.getBiaolimit();
		Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, lim);// num为增加的天数，可以改变的
        date = ca.getTime();
        
        long sytime=date.getTime()-new Date().getTime();
        long day=sytime/(24*60*60*1000);
        long hour=sytime%(24*60*60*1000)/(60*60*1000);
        long min=sytime%(24*60*60*1000)%(60*60*1000)/(60*1000);
        m.addAttribute("day", day);
        m.addAttribute("hour", hour);
        m.addAttribute("min", min);
       /* System.out.println("剩余天数："+day+"天"+(hour-day*24)+"小时"+(min-day*24*60)+"分钟");*/
		m.addAttribute("invest", invest);
		//查询标对应的投资记录
		int mytouziCount=service.getMyTouZiCount(id);
		m.addAttribute("mytouziCount", mytouziCount);
		List<Touzi> tlisTouzi=service.getlistTouzi(id);
		m.addAttribute("tlist", tlisTouzi);
		return "templates/invest-show";
	}
	
	
	/**
	 * 查询投标显示页面
	 * @param m
	 * @return
	 */
	@RequestMapping("/showInvest")
	public String showInvest(Model m) {
		int usercount=service.getUserCount();
		m.addAttribute("usercount", usercount);
		Double tzmoney=service.getTzMoney();
		m.addAttribute("tzmoney", tzmoney);
		return "templates/invest";
	}
	/**
	 * 查询标列表
	 */
	@RequestMapping("/showInvestList")
	public String showInvestList(Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		PageHelper.startPage(pn,3);
		List<Invest> invests=service.showInvestList();
		PageInfo<Invest>  p = new PageInfo<>(invests);
		m.addAttribute("page", p);
		m.addAttribute("list", invests);
		//查询昨天所有投资总额
		Double ztMoney=service.getztMoney();
		m.addAttribute("ztMoney", ztMoney);
		//查询标的总数量
		int biaosl=service.getbiaosl();
		m.addAttribute("biaosl", biaosl);
		
		return "templates/invest-list";
	}
}
