package com.lishan.p2p.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lishan.p2p.mapper.TouziMapper;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.service.TouZiService;

import sun.print.resources.serviceui;
@Service
@Transactional
public class TouZiServiceImpl implements TouZiService {
	@Autowired
	private TouziMapper dao;
	/**
	 * 投资
	 */
	@Override
	public void insertTouZi(Touzi touzi, Double symoney, Integer uid) {
		
		//插入交易表
		Record record=new Record();
		record.setRecorddate(touzi.getTouzidate());
		record.setUid(touzi.getUid());
		record.setRecordmoney(touzi.getTouzimoney());
		record.setJyuid(uid);
		dao.insertDeal(record);
		//修改标 标的总投资额
		Invest invest=new Invest();
		invest.setTouziMoney(touzi.getTouzimoney());
		invest.setId(touzi.getInid());
		dao.updateZtouziMoney(invest);
		//插入投资表
		dao.insertTouZi(touzi);
		Invest inv=dao.getInvestById(touzi.getInid());
		//修改投资者的账户余额   减掉投资金额
		dao.updateTzUserYue(touzi.getTouzimoney(),touzi.getUid());
		//修改用户冻结资金
		dao.updateDJmoney(touzi.getTouzimoney(),uid);
		//插入投资消息提醒
		Massage ms2=new Massage();
		ms2.setMsgdate(touzi.getTouzidate());
		ms2.setUid(touzi.getUid());
		ms2.setDes("恭喜您本次投资成功");
		dao.insertTouziSK(ms2);
		//被投资者插入消息
		Massage ms1=new Massage();
		ms1.setMsgdate(touzi.getTouzidate());
		ms1.setUid(uid);
		ms1.setDes("恭喜您标已被投资");
		dao.insertbiaoSK(ms1);
		//借款金额 - 总投资额=0 代表标已投满
		if(inv.getJemoney()-inv.getTouziMoney()==0) {
			//修改标状态  为2 已投满
			//Double backMoney=inv.getJemoney()*inv.getBorrow().getRate()/100;
			dao.updatestateWC(touzi.getInid());
			//修改用户余额 ，冻结资金-当前借款金额
			dao.updateYueAndDjMoney(inv.getJemoney(),uid);
			//标中修改借款开始时间和借款结束时间
			Date date=new Date();
			int lim=inv.getBorrow().getTlimit()*30;
			Calendar ca = Calendar.getInstance();
	        ca.setTime(date);
	        ca.add(Calendar.DATE, lim);// num为增加的天数，可以改变的
	        date = ca.getTime();
	        Date star=new Date();
	        SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String starttime=smf.format(star);
	        String endtime=smf.format(date);
	        dao.insertStartAndEnd(starttime,endtime,touzi.getInid());
		}else {
			//修改标状态
			dao.updatestatejk(touzi.getInid());
			
		}
	}
	/**
	 * 前台查询我的投资列表
	 */
	@Override
	public List<Touzi> getMyTouZiList(Integer id, Integer state, String start, String end) {
		return dao.getMyTouZiList(id,state,start,end);
	}
	/**
	 * 查询我的投资个数
	 */
	@Override
	public int getMyTouziCount(Integer id) {
		return dao.getMyTouziCount(id);
	}
	/**
	 * 查询我的投资总额
	 */
	@Override
	public Double getMyTouZiMoney(Integer id) {
		return dao.getMyTouZiMoney(id);
	}
	/**
	 * 查询我的收益
	 */
	@Override
	public Double getMyShouYi(Integer id) {
		return dao.getMyShouYi(id);
	}
	/**
	 * 前台查询交易列表
	 */
	@Override
	public List<Record> getMyJyList(Integer id, Integer state, String start, String end) {
		return dao.getMyJyList(id,state,start,end);
	}
	/**
	 * 后台查询投资列表
	 */
	@Override
	public List<Touzi> getHouTaiTouZiList(String starttime, String endtime, String names) {
		return dao.getHouTaiTouZiList(starttime,endtime,names);
	}
	/**
	 * 后台查询交易列表
	 */
	@Override
	public List<Record> getMyRecordList(String starttime, String endtime, String names) {
		return dao.getMyRecordList(starttime,endtime,names);
	}
}
