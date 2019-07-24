package com.lishan.p2p.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;

public interface TouziMapper {
	//修改标表 的总投资额
	void updateZtouziMoney(Invest invest);
	//插入交易表内容
	void insertDeal(Record record);
	//插入投资表
	void insertTouZi(Touzi touzi);
	//修改标状态为2 
	void updatestateWC(@Param("inid")Integer inid);
	//根据id查询标
	Invest getInvestById(Integer inid);
	//修改标状态为1
	void updatestatejk(Integer inid);
	//修改用户冻结资金
	void updateDJmoney(@Param("touzimoney")Double touzimoney, @Param("uid")Integer uid);
	//修改用户余额 ，冻结资金-借款金额
	void updateYueAndDjMoney(@Param("jemoney")Double jemoney,@Param("uid") Integer uid);
	//前台查询我的投资列表
	List<Touzi> getMyTouZiList(@Param("id")Integer id, @Param("state")Integer state, @Param("start")String start,@Param("end") String end);
	//查询我的投资个数
	int getMyTouziCount(Integer id);
	//查询我的投资总额
	Double getMyTouZiMoney(Integer id);
	//查询我的收益
	Double getMyShouYi(Integer id);
	//前台查询我的交易列表
	List<Record> getMyJyList(@Param("id")Integer id,@Param("state") Integer state, @Param("start")String start,@Param("end") String end);
	//后台查询投资列表
	List<Touzi> getHouTaiTouZiList(@Param("start")String starttime, @Param("end")String endtime,@Param("names") String names);
	//标中插入借款开始时间和借款结束时间
	void insertStartAndEnd(@Param("start")String starttime,@Param("end") String endtime,@Param("id") Integer id);
	//投资时候修改投资者的账户余额
	void updateTzUserYue(@Param("touzimoney")Double touzimoney, @Param("id")Integer uid);
	//投资时投资者插入消息
	void insertTouziSK(Massage ms2);
	//被投资者插入消息
	void insertbiaoSK(Massage ms1);
	//后台查询交易列表
	List<Record> getMyRecordList(@Param("start")String starttime, @Param("end")String endtime,@Param("names") String names);
	
	

}
