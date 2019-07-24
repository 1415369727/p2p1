package com.lishan.p2p.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Touzi;

public interface InvestMapper {
	//查询注册用户数
	int getUserCount();
	//查询投资总金额
	Double getTzMoney();
	//查询标列表
	List<Invest> showInvestList();
	//查询昨天投资总额
	Double getztMoney();
	//查询标数量
	int getbiaosl();
	//根据标状态查询标列表
	List<Invest> getInvestBystate();
	//查询标详情
	Invest showinvestById(Integer id);
	//修改状态为过期的标
	void updateStateById(Integer id);
	//查询前台我的标列表
	List<Invest> getMyInvest(@Param("id")Integer id,@Param("state") Integer state,@Param("start") String start, @Param("end")String end);
	//查询我的借款个数
	int getCountInve(Integer id);
	//查询我的总借款金额
	Double getZongInvestMoney(Integer id);
	//查询借款中个数
	int jeKunZhong(Integer id);
	//查询还款中个数
	int HuanKunZhong(Integer id);
	//查询已还款个数
	int YiHuanKun(Integer id);
	//查询前4条标
	List<Invest> showInvestTopList();
	//查询标对应的透支记录
	int getMyTouZiCount(Integer id);
	//查询标对应的投资列表
	List<Touzi> getlistTouzi(Integer id);
	//查询那个用户投资了这个过期的标
	List<Touzi> getTouzis(Integer id);
	//过期标   修改投资者账户余额
	void updateTouziUserYueMoney(@Param("tzm")Double tzm,@Param("uid") Integer uid);
	//标过期 修改借款人的冻结资金减去总投资额
	void updateInvestUserDjMoney(@Param("tzmoney")Double tzmoney,@Param("buid") int buid);

}
