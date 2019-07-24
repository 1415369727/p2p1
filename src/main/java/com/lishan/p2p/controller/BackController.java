package com.lishan.p2p.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lishan.p2p.pojo.Back;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.User;
import com.lishan.p2p.service.BackService;
import com.lishan.p2p.service.UserService;

@Controller
@RequestMapping("/back")
public class BackController {
	@Autowired
	private BackService service;
	@Autowired
	private UserService uservice;
	/**
	 * 还款
	 */
	@RequestMapping("/backmoney")
	@ResponseBody
	public String backMoney(Integer invid,Double hmoney,HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			return "2";
		}
		if(user.getYuemoney()<hmoney) {
			return "3";
		}
		//判断本月是否已经还款
		SimpleDateFormat smf=new SimpleDateFormat("yyyyMMdd");
		String da=smf.format(new Date());
		String MM=da.substring(0, 6);
		
		 List<Back> backs=service.getDateByUid(user.getId(),invid);
		 String sm=null;
		 for (Back back : backs) {
			Date dd=back.getHuankdate();
			String tt=smf.format(dd);
			sm=tt.substring(0, 6);
			 if(sm.contains(MM)) {
				return "4";
			 }
		}
		//插入还款记录
		Date date=new Date();
		Back back=new Back();
		back.setHuankdate(date);
		back.setUid(user.getId());
		back.setHuankmoney(hmoney);
		back.setInvid(invid);
		service.backMoney(back);
		
		return "1";
	}
	/**
	 * //跳转到还款界面
	 */
	@RequestMapping("/showBack")
	public String showBack(Integer id,HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还没有登陆");
			return "forward:/user/showmember";
		}
		Invest invest=service.getInvestById(id);
		Calendar cal = Calendar.getInstance();  
		Date startdate=invest.getStarttime();
		cal.setTime(startdate);
		cal.add(Calendar.MONTH,1);//取前一个月的同一天  
		Date date = cal.getTime();
		SimpleDateFormat smf=new SimpleDateFormat("yyyy年MM月dd日");
		String s=smf.format(date);
		m.addAttribute("stime", s);
		m.addAttribute("invest", invest);
		Double yumoney=uservice.getUserMoney(user.getId());
		m.addAttribute("yumoney", yumoney);
		return "templates/showback";
	}
}
