package com.lishan.p2p.service;

import java.util.List;

import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Touzi;

public interface InvestService {
	//查询注册用户数
	int getUserCount();
	//查询投资总金额
	Double getTzMoney();
	//查询标列表
	List<Invest> showInvestList();
	//获取昨天的投资额
	Double getztMoney();
	//获取标数量
	int getbiaosl();
	//根据状态查询标列表
	List<Invest> getInvestBystate();
	//查询标详情
	Invest showinvestById(Integer id);
	//修改为过期的标
	void updateStateById(Integer id);
	//查询前台我的标
	List<Invest> getMyInvest(Integer id, Integer state, String start, String end);
	//查询我的借款个数
	int getCountInve(Integer id);
	//查询我的总借款金额
	Double getZongInvestMoney(Integer id);
	//查询我的借款中个数
	int jeKunZhong(Integer id);
	//查询我的还款中个数
	int HuanKunZhong(Integer id);
	//查询已还款个数
	int YiHuanKun(Integer id);
	//查询前4条标
	List<Invest> showInvestTopList();
	//查询标对应的投资记录
	int getMyTouZiCount(Integer id);
	//查询标对应投资列表
	List<Touzi> getlistTouzi(Integer id);
	//获取过期标的投资对应的用户
	List<Touzi> getTouzis(Integer id);
	//标过期修改投资者的 余额
	void updateTouziUserYueMoney(Double tzm, Integer uid);
	//标过期 修改借款人的冻结资金
	void updateInvestUserDjMoney(Double tzmoney, int buid);

}
