package com.lishan.p2p.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lishan.p2p.mapper.InvestMapper;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.service.InvestService;
@Service
public class InvestServiceImpl implements InvestService {
	@Autowired
	private InvestMapper dao;
	/**
	 * 查询注册用户数
	 */
	@Override
	public int getUserCount() {
		return dao.getUserCount();
	}
	/**
	 * 查询投资总金额
	 */
	@Override
	public Double getTzMoney() {
		return dao.getTzMoney();
	}
	/**
	 * 查询标列表
	 */
	@Override
	public List<Invest> showInvestList() {
		return dao.showInvestList();
	}
	/**
	 * 查询昨天投资总额
	 */
	@Override
	public Double getztMoney() {
		return dao.getztMoney();
	}
	/**
	 * 查询标数量
	 */
	@Override
	public int getbiaosl() {
		return dao.getbiaosl();
	}
	/**
	 * 根据状态查询标列表
	 */
	@Override
	public List<Invest> getInvestBystate() {
		return dao.getInvestBystate();
	}
	/**
	 * 查询标详情
	 */
	@Override
	public Invest showinvestById(Integer id) {
		return dao.showinvestById(id);
	}
	/**
	 * 修改为过期的标
	 */
	@Override
	public void updateStateById(Integer id) {
		dao.updateStateById(id);
	}
	/**
	 * 查询前台我的标
	 */
	@Override
	public List<Invest> getMyInvest(Integer id, Integer state, String start, String end) {
		return dao.getMyInvest(id,state,start,end);
	}
	/**
	 * 查询我的借款个数
	 */
	@Override
	public int getCountInve(Integer id) {
		return dao.getCountInve(id);
	}
	/**
	 * 查询我的总借款金额
	 */
	@Override
	public Double getZongInvestMoney(Integer id) {
		return dao.getZongInvestMoney(id);
	}
	/**
	 * 查询借款中个数
	 */
	@Override
	public int jeKunZhong(Integer id) {
		return dao.jeKunZhong(id);
	}
	/**
	 * 查询还款中个数
	 */
	@Override
	public int HuanKunZhong(Integer id) {
		return dao.HuanKunZhong(id);
	}
	/**
	 * 查询已还款个数
	 */
	@Override
	public int YiHuanKun(Integer id) {
		return dao.YiHuanKun(id);
	}
	/**
	 * 查询前4条标
	 */
	@Override
	public List<Invest> showInvestTopList() {
		return dao.showInvestTopList();
	}
	//查询标对应的投资记录
	@Override
	public int getMyTouZiCount(Integer id) {
		return dao.getMyTouZiCount(id);
	}
	/**
	 * 查询标对应投资列表
	 */
	@Override
	public List<Touzi> getlistTouzi(Integer id) {
		return dao.getlistTouzi(id);
	}
	//获取那个用户投资的过期的标  状态为3
	@Override
	public List<Touzi> getTouzis(Integer id) {
		return dao.getTouzis(id);
	}
	@Override
	public void updateTouziUserYueMoney(Double tzm, Integer uid) {
		dao.updateTouziUserYueMoney(tzm, uid);
		
	}
	@Override
	public void updateInvestUserDjMoney(Double tzmoney, int buid) {
		dao.updateInvestUserDjMoney(tzmoney, buid);
		
	}
}
