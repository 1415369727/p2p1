package com.lishan.p2p.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lishan.p2p.mapper.BackMapper;
import com.lishan.p2p.pojo.Back;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.service.BackService;
@Service
@Transactional
public class BackServiceImpl implements BackService {
	@Autowired
	private BackMapper dao;
	/**
	 * 获取标的信息
	 */
	@Override
	public Invest getInvestById(Integer id) {
		return dao.getInvestById(id);
	}
	/**
	 * 还款
	 */
	@Override
	public void backMoney(Back back) {
		//插入还款记录
		dao.backMoney(back);
		//插入交易记录
		Record record=new Record();
		record.setJyuid(back.getUid());record.setRecorddate(back.getHuankdate());
		record.setRecordmoney(back.getHuankmoney());
		record.setUid(back.getUid());
		dao.insertRecord(record);
		//修改用户收益，用户余额（还款人余额减少，还款对应标中的投资人账户余额增加） //修改还款人余额
		dao.updateHkUserYue(back.getHuankmoney(),back.getUid());
		List<Touzi> touzis=dao.getTouzis(back.getInvid());
		for(int i=0;i<touzis.size();i++) {
			//获取投资人id
			Integer uid=touzis.get(i).getUid();
			//获取投资金额
			Double tzmoney=touzis.get(i).getTouzimoney();
			//获取利率
			Double rate=touzis.get(i).getInvest().getBorrow().getRate();
			//获取投资期限
			Integer lim=touzis.get(i).getInvest().getBorrow().getTlimit();
			//获取每月收益
			Double shouyi=tzmoney*rate/100;
			//获取当月总收益金额
			Double zshouyi=(double) Math.round(tzmoney*rate/100+tzmoney/lim);
			//修改投资用户余额   和收益金额
			dao.updateInvestMoney(shouyi,zshouyi,uid);
			//插入交易 （回款）
			Record rc=new Record();
			rc.setJyuid(back.getUid());rc.setRecorddate(new Date());
			rc.setRecordmoney(zshouyi);rc.setUid(uid);
			dao.updateRecordHuiKuan(rc);
			//插入投资人消息提醒
			Massage ms2=new Massage();
			ms2.setMsgdate(back.getHuankdate());
			ms2.setUid(uid);
			ms2.setDes("已成功收到还款");
			dao.insertEverTouziSK(ms2);
		}
		//修改标的还款金额
		dao.updateInvibackMoney(back.getHuankmoney(),back.getInvid());
		//每次还款插入消息
		Massage ms1=new Massage();
		ms1.setMsgdate(back.getHuankdate());
		ms1.setUid(back.getUid());
		ms1.setDes("本次还款成功");
		dao.insertEverHuank(ms1);
		//判断还款完成  修改标状态
		Invest invest=dao.getInvestById(back.getInvid());
		//获得总金额
		Double zje=invest.getJemoney()+invest.getJemoney()*invest.getBorrow().getRate()/100*invest.getBorrow().getTlimit();
		//获取借款期限
		int jielimit=invest.getBorrow().getTlimit();
		//获取还款次数
		int hknum=dao.getHkNum(back.getUid(),back.getInvid());
		if(jielimit==hknum) {
			//修改标状态
			dao.updateInvestState(back.getInvid());
			//修改投资状态
			dao.updateTouziState(back.getInvid());
			//插入消息
			Massage ms=new Massage();
			ms.setMsgdate(back.getHuankdate());
			ms.setUid(back.getUid());
			ms.setDes("恭喜您已完成还款");
			dao.insertMassageByfinishHuan(ms);
		}
	}
	/**
	 * 判断用户本月是否还款
	 */
	@Override
	public List<Back> getDateByUid(Integer id, Integer invid) {
		return dao.getDateByUid(id,invid);
	}
}
