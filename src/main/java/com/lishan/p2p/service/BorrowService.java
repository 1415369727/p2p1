package com.lishan.p2p.service;

import java.text.ParseException;
import java.util.List;

import com.lishan.p2p.pojo.Borrow;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Rate;
import com.lishan.p2p.pojo.Touzi;

public interface BorrowService {
	//查询利率
	List<Rate> getRate();
	//查询期限
	Integer getDateLimitByRate(Double rate);
	//提交申请
	void insertBorrow(Borrow bor);
	//查询申请列表
	List<Borrow> showBorrow();
	//根据时间查询申请列表
	List<Borrow> showBorrowByDate(String starttime, String endtime, String names) throws ParseException;
	//申请借款审核通过
	void updateTongg(Integer id, Integer uid);
	//借款申请审核不通过
	void updateBuTongg(Integer id);
	//前台查询我的审核
	List<Borrow> getMyByBorrow(Integer uid, String start, String end, Integer state);
	//发标查询申请
	Borrow getMyByBorrowId(Integer id);
	//发布标
	void insertInvest(Invest invest);
	//条件查询标列表
	List<Invest> showInvestList(String starttime, String endtime, String names);
	//查询标列表
	List<Invest> showInvest();
	//查询用户状态
	String getUserState(Integer id);
	//根据标id查询投资列表
	List<Touzi> getTouZiByInvid(Integer id);

}
