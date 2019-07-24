package com.lishan.p2p.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.lishan.p2p.pojo.Bank;
import com.lishan.p2p.pojo.Borrow;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Province;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.User;
import com.lishan.p2p.pojo.UserCard;
import com.lishan.p2p.service.UserService;
import com.lishan.p2p.util.HideDateUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	
	
	
	/**
	 * 修改消息状态
	 */
	@RequestMapping("/updateMsgStateByOne")
	@ResponseBody
	public String updateMsgStateByOne(Integer id) {
		service.updateMsgStateByOne(id);
		return "1";
	}
	/**
	 * 查询未读消息数量
	 */
	@RequestMapping("/getMassageByState")
	@ResponseBody
	private Integer getMassageByState(HttpSession session) {
		//查询消息数量
		User us=(User)session.getAttribute("user");
		int msgnumber=0;
		if(us!=null) {
			msgnumber=service.getMsgNumber(us.getId());
			session.setAttribute("msgnumber", msgnumber);
			
		}
		return msgnumber;
		
	}
	/**
	 * 获取银行卡id
	 */
	@RequestMapping("/getCardid")
	@ResponseBody
	public Integer getCardid(Integer bid,Model m) {
		m.addAttribute("bid", bid);
		return bid;
	}
	/**
	 * 用户充值
	 */
	@RequestMapping("/UserChongZhi")
	@ResponseBody
	public String UserChongZhi(HttpSession session,Integer bid,Double czmoney,String jypass,Model m) {
		
		User user=(User)session.getAttribute("user");
		
		if(user==null) {
			return "2";
		}
		if(czmoney==null) {
			return "6";
		}
		if(bid==null || bid==' ') {
			return "3";
			}
		UserCard ucd=service.getUserCardByJypass(user.getId(),bid,jypass);
		if(ucd==null) {
			return "4";
		}
		if(czmoney>ucd.getMoney()) {
			return "5";
		}
		
		if(ucd!=null) {
			//充值
			service.updateCongZhi(czmoney,user.getId(),bid);
			
			return "1";
		
			}
		
		return "7";
	}
	/**
	 * 用户提现
	 */
	@RequestMapping("/UserTiXian")
	@ResponseBody
	public String UserTiXian(HttpSession session,Integer bid,Double tixmoney,String jypass) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			return "2";
		}
		if(tixmoney==null) {
			return "6";
		}
		if(tixmoney>user.getYuemoney()) {
			return "5";
		}
		
		if(bid!=null && bid!=' ') {
			UserCard ucd=service.getUserCardByJypass(user.getId(),bid,jypass);
			if(ucd==null) {
				return "4";
			}else {
				//提现
				service.updateTiXian(tixmoney,user.getId(),bid);
				return "1";
			}
		}else {
			return "3";
		}
		
	}
	/**
	 * 添加银行卡
	 */
	@RequestMapping("/insertUserCard")
	public String insertUserCard(HttpSession session,UserCard uc,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还有登陆");
			return "redirect:/tz/show";
		}
		if(uc.getCnumber()==null || "".equals(uc.getCnumber())) {
			m.addAttribute("cardmsg", "请输入卡号");
			return "forward:addCard";
		}if(uc.getJypass()==null || "".equals(uc.getJypass())) {
			m.addAttribute("jymsg", "请输入交易密码");
			return "forward:addCard";
		}
		uc.setUid(user.getId());
		service.insertUserCard(uc);
		return "forward:showMyBank";
	}
	/**
	 * 修改消息状态
	 * 
	 */
	@RequestMapping("/updateMassageState")
	@ResponseBody
	public String updateMassageState(HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还有登陆");
			return "redirect:/tz/show";
		}
		service.updateMassageState(user.getId());
		return "1";
	}
	/**
	 * 跳转到消息列表
	 */
	@RequestMapping("/showMassage")
	public String showMassage(HttpSession session,Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还有登陆");
			return "redirect:/tz/show";
		}
		PageHelper.startPage(pn,10);
		List<Massage> mslist=service.showMassage(user.getId());
		PageInfo<Massage>  p = new PageInfo<>(mslist);
		session.setAttribute("pn", pn);
		m.addAttribute("page", p);
		m.addAttribute("mslist", mslist);
		return "templates/Infomation";
	}
	/**
	 * 跳转到添加银行卡页面
	 */
	@RequestMapping("/addCard")
	public String addCard(HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还有登陆");
			return "redirect:/tz/show";
		}
		List<Bank> banks=service.getBankList();
		m.addAttribute("list", banks);
		return "templates/addcard";
	}
	/**
	 * 银行卡管理
	 */
	@RequestMapping("/showMyBank")
	public String showMyBank(HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还有登陆");
			return "redirect:/tz/show";
		}
		List<UserCard> uclist=service.getMyBankCard(user.getId());
		for (UserCard userCard : uclist) {
			String cnum=userCard.getCnumber();
			String cn=HideDateUtil.hideCardNo(cnum);
			userCard.setCnumber(cn);
		}
		m.addAttribute("uclist", uclist);
		return "templates/Bank-Card";
	}
	/**
	 * 查询提现页面
	 */
	@RequestMapping("/shoWdrawals")
	public String shoWdrawals(HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还有登陆");
			return "redirect:/tz/show";
		}
		List<UserCard> uctx=service.getMyBankCard(user.getId());
		for (UserCard userCard : uctx) {
			String uca=userCard.getCnumber();
			String scs=HideDateUtil.hideCardNo(uca);
			userCard.setCnumber(scs);
		}
		Double userMoney=service.getUserMoney(user.getId());
		m.addAttribute("userMoney", userMoney);
		m.addAttribute("list", uctx);
		return "templates/Withdrawals";
	}
	/**
	 * 查询充值页面
	 */
	@RequestMapping("/showRecharge")
	public String showRecharge(HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还有登陆");
			return "redirect:/tz/show";
		}
		List<UserCard> uccz=service.getMyBankCard(user.getId());
		for (UserCard userCard : uccz) {
			String uca=userCard.getCnumber();
			String scs=HideDateUtil.hideCardNo(uca);
			userCard.setCnumber(scs);
		}
		Double yuemoney=service.getUserMoney(user.getId());
		m.addAttribute("yuemoney", yuemoney);
		m.addAttribute("list", uccz);
		return "templates/recharge";
	}
	/**
	 * 查询我的资产总计
	 */
	@RequestMapping("/myzjMoney")
	public String myzjMoney(HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还有登陆");
			return "redirect:/tz/show";
		}
		//查询我的总投资金额
		Double myztje=service.getMyZtje(user.getId());
		if(myztje!=null) {
			m.addAttribute("myztje", myztje);
		}else {
			m.addAttribute("myztje", 0);
		}
		//查询我的总借入资金
		Double myzjje=service.getMyZjje(user.getId());
		if(myzjje!=null) {
			m.addAttribute("myzjje", myzjje);
		}else {
			m.addAttribute("myzjje", 0);
		}
		//查询我的已还款总额
		Double myYhk=service.getMyYhk(user.getId());
		if(myYhk!=null) {
			m.addAttribute("myYhk", myYhk);
		}else {
			m.addAttribute("myYhk", 0);
		}
		//待收本金
		Double DsbjMoney=service.getMyDsbjMoney(user.getId());
		if(DsbjMoney!=null) {
			m.addAttribute("DsbjMoney", DsbjMoney);
		}else {
			m.addAttribute("DsbjMoney", 0);
		}
		//待收收益
		Double DshouSy=service.getMyDshouSy(user.getId());
		if(DshouSy!=null) {
			m.addAttribute("DshouSy", DshouSy);
		}else {
			m.addAttribute("DshouSy", 0);
		}
		//未还款金额
		Double WeiHkMoney=service.getMyWeiHkMoney(user.getId());
		if(WeiHkMoney!=null) {
			m.addAttribute("WeiHkMoney", WeiHkMoney);
		}else {
			m.addAttribute("WeiHkMoney", 0);
		}
		//最近应还款
		Double ZuiJinMoney=service.getMyZuiJinMoney(user.getId());
		if(ZuiJinMoney!=null) {
			m.addAttribute("ZuiJinMoney", ZuiJinMoney);
		}else {
			m.addAttribute("ZuiJinMoney", 0);
		}
		Double yuemoney=service.getUserMoney(user.getId());
		m.addAttribute("yuemoney", yuemoney);
		return "templates/Asset-Statistics";
	}
	/**
	 * 用户登录
	 * @param session
	 * @param m
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpSession session,Model m, String name, String pass) {
		
		User user=new User();
		user.setUsername(name);user.setPassword(pass);
		User us=service.login(user);
		if(us!=null) {
			
			session.setAttribute("user", us);
			return "1";
		}
		return "2";
	}
	/**
	 * 用户退出
	 */
	@RequestMapping("/quert")
	public String quert(HttpServletRequest request) {
		request.getSession().invalidate();
		return "templates/index";
	}
	/**
	 * 用户注册
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public String regist(String username,String phone,String password,String repassword) {
		User user=new User();
		user.setUsername(username);user.setPhone(phone);user.setPassword(repassword);
		if(!password.equals(repassword)) {
			return "1";
		}
		if(username==null ||"".equals(username)) {
			return "4";
		}
		if(phone==null ||"".equals(phone)) {
			return "5";
		}
		if(password==null ||"".equals(password)) {
			return "6";
		}
		User us=service.checkUserName(username);
		if(us!=null) {
			return "3";
		}else {
			service.regist(user);
			return "2";
		}
	}
	/**
	 * 个人中心
	 */
	@RequestMapping("/showmember")
	public String showmember(Integer state,HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			m.addAttribute("msg", "您还有登陆");
			return "redirect:/tz/show";
		}
		User us=service.getUserById(user.getId());
		m.addAttribute("us", us);
		//查询wode 交易列表
		List<Record> record=service.getRecordList(user.getId(),state);
		Double yuemoney=service.getUserMoney(user.getId());
		m.addAttribute("yuemoney", yuemoney);
		m.addAttribute("recordlist", record);
		return "templates/member";
	}
	/**
	 * 跳转到完善
	 */
	@RequestMapping("/showUserInfo")
	public String showUserInfo(HttpSession session,Model m) {
		User user=(User)session.getAttribute("user");
		if(user==null) {
			return "redirect:/tz/show";
		}
		List<Province> listcity=service.getcity();
		m.addAttribute("list", listcity);
		User uss=service.getUserById(user.getId());
		m.addAttribute("us", uss);
		return "templates/userinfo";
	}
	/**
	 * 判断用户资料是否完善
	 */
	@RequestMapping("/panduanUser")
	@ResponseBody
	public String panduanUser(HttpSession session) {
		User user=(User)session.getAttribute("user");
		if(user.getState().equals("1")) {
			return "2";
		}
		return "1";
	}
	/**
	 * 验证用户是否存在
	 */
	@RequestMapping("/checkUserName")
	@ResponseBody
	public String checkUserName(String username) {
		User user=service.checkUserName(username);
		if(user==null) {
			return "2";
		}
		return "1";
	}
	/**
	 * 完善用户个人资料
	 * @throws ParseException 
	 */
	@RequestMapping("/updateUserInfo")
	public String updateUserInfo(User user,Model m,HttpSession session,String riqi) throws ParseException {
		
		User us=(User)session.getAttribute("user");
		if(us==null) {
			return "redirect:/tz/show";
		}
		if(riqi==null || "".equals(riqi)) {
			return "forward:/user/showUserInfo";
		}
		
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sim.parse(riqi);
		user.setBirthday(date);
		
		service.updateUserInfo(user);
		m.addAttribute("msg", "信息已完善成功");
		return "forward:/user/showUserInfo";
	}
	
	/**
	 * 获取城市
	 */
	@RequestMapping("/getcity")
	@ResponseBody
	public List<Province> getcity(){
		List<Province> list=service.getcity();
		return list;
	}
}
