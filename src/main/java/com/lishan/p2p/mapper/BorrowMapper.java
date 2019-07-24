package com.lishan.p2p.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lishan.p2p.pojo.Borrow;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Rate;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;

public interface BorrowMapper {
	//获取利率列表
	List<Rate> getRate();
	//获取期限
	Integer getDateLimitByRate(Double rate);
	//提交申请
	void insertBorrow(Borrow bor);
	//查询申请列表
	List<Borrow> showBorrow();
	//根据时间查询申请列表
	List<Borrow> showBorrowByDate(@Param("start")String starttime,@Param("end") String endtime,@Param("names") String names);
	//借款申请审核通过
	void updateTongg(Integer id);
	//借款申请审核不通过
	void updateBuTongg(Integer id);
	//前台查询借款列表
	List<Borrow> getMyByBorrow(@Param("uid")Integer uid, @Param("starttime")String start,@Param("endtime") String end,@Param("state") Integer state);
	//发标查询申请
	Borrow getMyByBorrowId(Integer id);
	//发布标
	void insertInvest(Invest invest);
	//条件查询标列表
	List<Invest> showInvestList(@Param("start")String starttime,@Param("end") String endtime,@Param("names") String names);
	//发标时修改申请状态
	void updateStateToFaBiao(Integer bid);
	//查询标列表
	List<Invest> showInvest();
	//插入借款的交易
	void insertRecord(Record record);
	//审核通过插入消息内容
	void insertMassage(Massage ms);
	//标发布成功插入消息
	void insertMassageByInvest(Massage ms);
	//查询用户状态
	String getUserState(Integer id);
	//根据标id查询投资列表
	List<Touzi> getTouZiByInvid(Integer id);

}
