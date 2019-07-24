package com.lishan.p2p.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lishan.p2p.pojo.Back;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;

public interface BackMapper {
	//获取标的信息
	Invest getInvestById(Integer id);
	//还款插入还款
	void backMoney(Back back);
	//插入交易
	void insertRecord(Record record);
	//还款时修改表中的还款记录
	void updateInvibackMoney(@Param("hkmoney")Double hkmoney,@Param("invid") Integer invid);
	//还款完成修改标状态
	void updateInvestState(Integer invid);
	//修改还款人余额
	void updateHkUserYue(@Param("huankmoney")Double huankmoney,@Param("uid") Integer uid);
	//根据标id查询投资人列表
	List<Touzi> getTouzis(Integer invid);
	//修改投资人的用户余额 和 收益金额
	void updateInvestMoney(@Param("shouyi")Double shouyi, @Param("zshouyi")Double zshouyi,@Param("uid") Integer uid);
	//插入交易（回款）
	void updateRecordHuiKuan(Record rc);
	//查询时间
	List<Back> getDateByUid(@Param("uid")int uid,@Param("invid") Integer invid);
	//修改投资状态
	void updateTouziState(Integer invid);
	//还款成功插入消息
	void insertMassageByfinishHuan(Massage ms);
	//每次还款插入还款人消息
	void insertEverHuank(Massage ms1);
	//每个投资人插入消息
	void insertEverTouziSK(Massage ms2);
	//获取还款次数
	int getHkNum(@Param("uid")Integer uid,@Param("invid") Integer invid);

}
