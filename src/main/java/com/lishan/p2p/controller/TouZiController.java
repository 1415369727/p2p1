package com.lishan.p2p.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.pojo.User;
import com.lishan.p2p.service.TouZiService;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
@RequestMapping("/touzi")
public class TouZiController {
	@Autowired
	private TouZiService service;
	
	
	/**
	 * 导出投资 Excel
	 */
	@RequestMapping("/touziExcel")
	public void portInvestExcel(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
		 String fileName = sdf.format(new Date()) + ".xls";  
		  
		 response.setContentType("application/x-excel");  
		 response.setCharacterEncoding("UTF-8");  
		 response.addHeader("Content-Disposition", "attachment;filename="  
		            + fileName);// excel文件名  	
		 
		 try {  
		        // 1.创建excel文件  
		        WritableWorkbook book = Workbook.createWorkbook(response  
		                .getOutputStream());  
		        // 居中  
		        WritableCellFormat wf = new WritableCellFormat();  
		        wf.setAlignment(Alignment.CENTRE);  
		  
		        WritableSheet sheet = book.createSheet("shet", 0);  
		        SheetSettings settings = sheet.getSettings();  
		        settings.setVerticalFreeze(2);  
	            // 3.添加第一行及第二行标题数据  
	            sheet.addCell(new Label(0, 0, "ID", wf));  
	            sheet.addCell(new Label(1, 0, "订单号", wf));  
	            sheet.addCell(new Label(2, 0, "项目名称", wf));  
	            sheet.addCell(new Label(3, 0, "投资人", wf));
	            sheet.addCell(new Label(4, 0, "被投资人 ", wf)); 
	            sheet.addCell(new Label(5, 0, "投资金额", wf));  
	            sheet.addCell(new Label(6, 0, "投资时间", wf));
	            sheet.addCell(new Label(7, 0, "月利率%", wf));
	            sheet.addCell(new Label(8, 0, "借款期限", wf));
		            // 4.历史数据，业务数据，不用关注  
	            List<Touzi> list=(List<Touzi>) session.getAttribute("httzlist");
		            if (list != null && list.size() > 0) {  
		                // 5.将历史数据添加到单元格中  
		                for (int j = 0; j < list.size(); j++) {  
		                    sheet.addCell(new Label(0, j+1, list.get(j).getId() + "", wf));
		                    sheet.addCell(new Label(1, j+1, list.get(j).getOrderid() + "", wf)); 
		                    sheet.addCell(new Label(2, j+1, list.get(j).getInvest().getProname() + "", wf)); 
		                    sheet.addCell(new Label(3, j+1, list.get(j).getTuser().getName() + "", wf)); 
		                    sheet.addCell(new Label(4, j+1, list.get(j).getInvest().getBorrow().getUsers().getName() + "", wf));
		                    sheet.addCell(new Label(5, j+1, list.get(j).getTouzimoney() + "", wf)); 
		                    SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        			String date=smf.format(list.get(j).getTouzidate());
		                    sheet.addCell(new Label(6, j+1, date + "", wf)); 
		                    sheet.addCell(new Label(7, j+1, list.get(j).getInvest().getBorrow().getRate() + "", wf));
		                    sheet.addCell(new Label(8, j+1, list.get(j).getInvest().getBorrow().getTlimit() + "", wf));
		                }  
		            }  
		        // 6.写入excel并关闭  
		        book.write();  
		        book.close();  
		      
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
	}
	/**
	 * 导出交易 Excel
	 */
	@RequestMapping("/recordExcel")
	public void portRecordExcel(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
		 String fileName = sdf.format(new Date()) + ".xls";  
		  
		 response.setContentType("application/x-excel");  
		 response.setCharacterEncoding("UTF-8");  
		 response.addHeader("Content-Disposition", "attachment;filename="  
		            + fileName);// excel文件名  	
		 
		 try {  
		        // 1.创建excel文件  
		        WritableWorkbook book = Workbook.createWorkbook(response  
		                .getOutputStream());  
		        // 居中  
		        WritableCellFormat wf = new WritableCellFormat();  
		        wf.setAlignment(Alignment.CENTRE);  
		  
		        WritableSheet sheet = book.createSheet("shet", 0);  
		        SheetSettings settings = sheet.getSettings();  
		        settings.setVerticalFreeze(2);  
	            // 3.添加第一行及第二行标题数据  
	            sheet.addCell(new Label(0, 0, "ID", wf));  
	            sheet.addCell(new Label(1, 0, "交易人", wf));  
	            sheet.addCell(new Label(2, 0, "交易类型", wf));  
	            sheet.addCell(new Label(3, 0, "操作金额", wf));
	            sheet.addCell(new Label(4, 0, "投资时间", wf));
		            // 4.历史数据，业务数据，不用关注  
	            List<Record> list=(List<Record>) session.getAttribute("rlist");
		            if (list != null && list.size() > 0) {  
		                // 5.将历史数据添加到单元格中  
		                for (int j = 0; j < list.size(); j++) {  
		                    sheet.addCell(new Label(0, j+1, list.get(j).getId() + "", wf));
		                    sheet.addCell(new Label(1, j+1, list.get(j).getUser().getName() + "", wf)); 
		                    StringBuilder ty=new StringBuilder();
		                    if(list.get(j).getRecordtype().equals("1")) {
		                    	ty.append("投资");
		                    }
		                    if(list.get(j).getRecordtype().equals("2")) {
		                    	ty.append("借款");
		                    }
		                    if(list.get(j).getRecordtype().equals("3")) {
		                    	ty.append("充值");
		                    }
		                    if(list.get(j).getRecordtype().equals("4")) {
		                    	ty.append("提现");
		                    }
		                    if(list.get(j).getRecordtype().equals("5")) {
		                    	ty.append("还款");
		                    }
		                    if(list.get(j).getRecordtype().equals("6")) {
		                    	ty.append("回款");
		                    }
		                    sheet.addCell(new Label(2, j+1, ty + "", wf)); 
		                    sheet.addCell(new Label(3, j+1, list.get(j).getRecordmoney() + "", wf)); 
		                    SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        			String date=smf.format(list.get(j).getRecorddate());
		                    sheet.addCell(new Label(4, j+1, date + "", wf)); 
		                }  
		            }  
		        // 6.写入excel并关闭  
		        book.write();  
		        book.close();  
		      
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
	}
	/**
	 * 后台查询交易列表
	 */
	@RequestMapping("/myRecordList")
	public String myRecordList(String starttime,String endtime,String names,HttpSession session,Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		PageHelper.startPage(pn,10);
		List<Record> rlist=service.getMyRecordList(starttime,endtime,names);
		PageInfo<Record>  p = new PageInfo<>(rlist);
		m.addAttribute("page", p);
		m.addAttribute("list", rlist);
		session.setAttribute("rlist", rlist);
		session.setAttribute("rtstart", starttime);session.setAttribute("rtend", endtime);
		session.setAttribute("rtnames", names);
		return "jsp/record-list";
	}
	/**
	 * 后台查询投资列表
	 */
	@RequestMapping("/htTouziList")
	public String htTouziList(String starttime,String endtime,String names,HttpSession session,Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		
		PageHelper.startPage(pn,10);
		List<Touzi> httzlist=service.getHouTaiTouZiList(starttime,endtime,names);
		PageInfo<Touzi>  p = new PageInfo<>(httzlist);
		m.addAttribute("page", p);
		session.setAttribute("httzlist", httzlist);
		session.setAttribute("htstart", starttime);session.setAttribute("htend", endtime);
		session.setAttribute("htnames", names);
		m.addAttribute("list", httzlist);
		return "jsp/touzi-list";
	}
	/**
	 * 查询交易列表
	 */
	@RequestMapping("/myJyList")
	public String myJyList(String start,String end,Integer state,HttpSession session,Model m,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		System.out.println(start);System.out.println(end);System.out.println(state);
		User user=(User)session.getAttribute("user");
		session.setAttribute("tstart", start);session.setAttribute("tend", end);
		session.setAttribute("tstate", state);
		if(user==null) {
			m.addAttribute("msg", "您还没有登陆");
			return "forward:/user/showmember";
		}
		PageHelper.startPage(pn,6);
		List<Record> rlist=service.getMyJyList(user.getId(),state,start,end);
		PageInfo<Record>  p = new PageInfo<>(rlist);
		m.addAttribute("page", p);
		m.addAttribute("list", rlist);
		return "templates/Record";
	}
	/**
	 * 查询我的投资列表
	 */
	@RequestMapping("/myTouZiList")
	public String myTouZiList(String start,String end,Integer state,Model m,HttpSession session,@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		User user=(User)session.getAttribute("user");
		session.setAttribute("tstart", start);
		session.setAttribute("tend", end);
		session.setAttribute("tstate", state);
		if(user==null) {
			m.addAttribute("msg", "您还没有登陆");
			return "forward:/user/showmember";
		}
		PageHelper.startPage(pn,6);
		List<Touzi> touzilist=service.getMyTouZiList(user.getId(),state,start,end);
		PageInfo<Touzi>  p = new PageInfo<>(touzilist);
		//我的投资项目个数
		int myTouziCount=service.getMyTouziCount(user.getId());
		m.addAttribute("myTouziCount", myTouziCount);
		//查询我的总投资金额
		Double myTouZiMoney=service.getMyTouZiMoney(user.getId());
		m.addAttribute("myTouZiMoney",myTouZiMoney);
		m.addAttribute("page", p);
		m.addAttribute("list", touzilist);
		return "templates/My-investment";
	}
	/**
	 * 前台投资
	 * @param uid 标的用户id
	 * @param invid 标id
	 * @param tzmoney 投资的金额
	 * @param symoney 可投资金额
	 * @param session
	 * @return
	 */
	@RequestMapping("/insertTouZi")
	@ResponseBody
	public String insertTouZi(Integer uid,Integer invid,Double tzmoney,Double symoney,HttpSession session) {
		User user=(User)session.getAttribute("user");
		if(tzmoney==null || tzmoney==' ') {
			return "4";
		}
		if(user==null) {
			return "2";
		}
		if(tzmoney>symoney) {
			return "3";
		}
		if(user.getYuemoney()<tzmoney) {
			return "5";
		}
		Touzi touzi=new Touzi();
		//订单编号
		Date nowdate=new Date();
		SimpleDateFormat smf=new SimpleDateFormat("yyyyMMddHHmmss");
		String or=smf.format(nowdate);
		String order="DEAL"+or+invid;
		touzi.setOrderid(order);
		//用户id
		touzi.setUid(user.getId());
		//标id
		touzi.setInid(invid);
		//投资时间
		touzi.setTouzidate(new Date());
		//投资金额
		touzi.setTouzimoney(tzmoney);
		service.insertTouZi(touzi,symoney,uid);
		return "1";
	}
}
