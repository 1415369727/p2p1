package com.lishan.p2p.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lishan.p2p.pojo.Borrow;
import com.lishan.p2p.pojo.Rate;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.pojo.User;
import com.lishan.p2p.service.BorrowService;


@Controller
@RequestMapping("/borrow")
public class BorrowController {
	@Autowired
	private BorrowService service;
	
	
	
	/**
	 * 查询投资根据标id
	 */
	@RequestMapping("/getTouZiByInvid")
	@ResponseBody
	public List<Touzi> getTouZiByInvid(Integer id){
		List<Touzi> tlist=service.getTouZiByInvid(id);
		for (Touzi touzi : tlist) {
			SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=touzi.getTouzidate();
			String d=smf.format(date);
			touzi.setTdate(d);
		}
		return tlist;
	}
	/**
	 * 跳转到借款
	 */
	@RequestMapping("/showborrow")
	public String showborrow(Model m) {
		List<Rate> rates=service.getRate();
		m.addAttribute("list", rates);
		return "templates/borrow";
	}
	
	/**
	 * 提交申请
	 */
	@RequestMapping("/insertBorrow")
	public String insertBorrow(HttpSession session,Borrow bor,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还没有登陆");
			return "forward:showborrow";
		}
		//查询状态
		String state=service.getUserState(user.getId());
		if(state.equals("1")) {
			m.addAttribute("msg", "您还没有完善资料");
			return "forward:/user/showUserInfo";
		}
		if(bor.getTel()==null ||"".equals(bor.getTel())) {
			m.addAttribute("msg", "请输入手机号");
			return "forward:showborrow";
		}if(bor.getYuecom()==null) {
			m.addAttribute("msg", "请输入月收入");
			return "forward:showborrow";
		}if(bor.getJemoney()==null) {
			m.addAttribute("msg", "请输入借款金额");
			return "forward:showborrow";
		}
		Integer qixian=service.getDateLimitByRate(bor.getRate());
		bor.setTlimit(qixian);
		bor.setUid(user.getId());
		bor.setSqdate(new Date());
		service.insertBorrow(bor);
		m.addAttribute("msgs", "借款申请提交成功,请耐心等待审核");
		return "forward:showborrow";
	}
	/**
	 * 查询我的申请
	 */
	@RequestMapping("/myBorrow")
	public String myBorrow(String start,String end,Integer state,HttpSession session,Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		
		User user=(User)session.getAttribute("user");
		session.setAttribute("state", state);
		session.setAttribute("bstart", start);
		session.setAttribute("bend", end);
		if(user==null) {
			m.addAttribute("msg", "您还没有登陆");
			return "forward:/user/showmember";
		}
		PageHelper.startPage(pn,6);
		List<Borrow> myBorrow=service.getMyByBorrow(user.getId(),start,end,state);
		PageInfo<Borrow>  p = new PageInfo<>(myBorrow);
		
		m.addAttribute("page", p);
		m.addAttribute("list", myBorrow);
		return "templates/myborrow";
	}
}
