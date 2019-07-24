package com.lishan.p2p.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lishan.p2p.App;
import com.lishan.p2p.mapper.AdminMapper;
import com.lishan.p2p.mapper.BackMapper;
import com.lishan.p2p.mapper.BorrowMapper;
import com.lishan.p2p.mapper.InvestMapper;
import com.lishan.p2p.mapper.TouziMapper;
import com.lishan.p2p.pojo.Back;
import com.lishan.p2p.pojo.Borrow;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Menu;
import com.lishan.p2p.pojo.Touzi;

import sun.print.resources.serviceui;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class TestMenu {
	@Autowired
	private AdminMapper dao;
	@Autowired
	private BorrowMapper bd;
	@Autowired
	private TouziMapper tdao;
	@Autowired
	private InvestMapper idao;
	@Autowired
	private BackMapper bdao;
	
	
	//测试获取当前月的开始和结束时间
	@Test
	public void fun7() {
		 SimpleDateFormat smf=new SimpleDateFormat("yyyyMMdd");
		 String date=smf.format(new Date());
		 String MM=date.substring(0, 6);
		 System.out.println("MM"+MM);
		 List<Back> backs=bdao.getDateByUid(1,7);
		 String sm=null;
		 for (Back back : backs) {
			Date dd=back.getHuankdate();
			String tt=smf.format(dd);
			sm=tt.substring(0, 6);
			 if(sm.contains(MM)) {
				 System.out.println("他俩相等");
				 break;
			 }
		}
		
		 //String ss="201906";
		
	}
	//测试还款
	@Test
	public void fun6() {
		Date date=new Date();
		Back back=new Back();
		back.setHuankdate(date);
		back.setUid(1);
		back.setHuankmoney(2950.0);
		back.setInvid(7);
	}
	@Test
	public void fun5() {
		Invest inv=tdao.getInvestById(7);
		Date date=new Date();
		int lim=inv.getBorrow().getTlimit();
		Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, lim);// num为增加的天数，可以改变的
        date = ca.getTime();
       long sy=date.getTime();
       long day=sy/(24*60*60*1000);
       System.out.println("获取的天数"+day);
	}
	
	@Test
	public void fun4() {
		Invest invest=bdao.getInvestById(7);
		Calendar cal = Calendar.getInstance();  
		Date startdate=invest.getStarttime();
		System.out.println("借款开始时间"+startdate);
		cal.add(Calendar.MONTH, 1);//取前一个月的同一天  
		cal.setTime(startdate);
		Date date = cal.getTime();
		SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s=smf.format(date);
		System.out.println(s);
	}
	
	@Test
	public void fun3() {
		System.out.println("11111");
		List<Touzi> ts=idao.getTouzis(4);
		/*for (Touzi touzi : ts) {
			System.out.println(touzi);
		}*/
		Double tzmoney=0.0;
		int buid=0;
		for(int i=0;i<ts.size();i++) {
			Integer uid=ts.get(i).getUid();
			tzmoney=tzmoney+ts.get(i).getTouzimoney();
			System.out.println("用户："+uid);
			
			Double tzm=ts.get(i).getTouzimoney();
			System.out.println("投资金额："+tzm);
			
			buid=ts.get(i).getInvest().getUid();
			System.out.println("借款用户id："+buid);
			//标过期  修改投资用户的余额+投资的金额
			idao.updateTouziUserYueMoney(tzm,uid);
		}
		System.out.println("jiekuan yonghu id :"+buid);
		System.out.println("总投资额"+tzmoney);
		// 标过期 修改 借款人的冻结资金 减去总的投资金额
		idao.updateInvestUserDjMoney(tzmoney,buid);
	}
	
	/*@Test
	public void fun2() throws ParseException {
		String stime="2019-06-21 14:14:14";
		String etime="2019-06-21 17:14:14";
		List<Borrow> list=bd.showBorrowByDate(stime,etime);
		for (Borrow borrow : list) {
			System.out.println(borrow);
		}
	}*/
	
	@Test
	public void fun1() {
		List<Menu> list=dao.showMenus(1);
		System.out.println(list);
	}
}
