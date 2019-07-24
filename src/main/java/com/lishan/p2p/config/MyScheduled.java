package com.lishan.p2p.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.service.InvestService;

@Component
public class MyScheduled {
	@Autowired
	private InvestService service;
	@Scheduled(cron="0 0 0/1 * * ?")
	public void scheduledMethod() {
		List<Invest> list=service.getInvestBystate();
		for (int i=0;i<list.size();i++) {
			if(list.get(i).getState()==1) {
				Integer id=list.get(i).getId();
				long biaolimit=list.get(i).getBiaolimit();
				Date biaodate=list.get(i).getBiaodate();
			
				Date nowdate=new Date();
				Calendar  calendar =Calendar.getInstance();//日历类
				calendar.setTime(biaodate);
				long now_date =calendar.getTimeInMillis();
				calendar.setTime(nowdate);
				long overdue_time=calendar.getTimeInMillis();
				long overdueDate=(now_date-overdue_time)/(1000*3600*24); 
				if(biaolimit+overdueDate==0) {
					//修改标的状态为过期
					service.updateStateById(id);
					//获取那个用户投资了这个过期的标
					List<Touzi> ts=service.getTouzis(id);
					Double tzmoney=0.0;
					int buid=0;
					for(int j=0;j<ts.size();j++) {
						//获取投资者id
						Integer uid=ts.get(j).getUid();
						//获取标 的投资总金额
						tzmoney=tzmoney+ts.get(j).getTouzimoney();
						//获取每个投资者的投资金额
						Double tzm=ts.get(j).getTouzimoney();
						//获取借款人的用户id
						buid=ts.get(j).getInvest().getUid();
						//标过期  修改投资用户的余额+投资的金额
						service.updateTouziUserYueMoney(tzm,uid);
					}
					// 标过期 修改 借款人的冻结资金 减去总的投资金额
					service.updateInvestUserDjMoney(tzmoney,buid);
				}
			}
		}
	}
}
