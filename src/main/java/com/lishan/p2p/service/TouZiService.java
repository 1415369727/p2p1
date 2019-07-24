package com.lishan.p2p.service;

import java.util.List;

import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;

public interface TouZiService {
	//投资
	void insertTouZi(Touzi touzi, Double symoney, Integer uid);
	//前台查询我的投资列表
	List<Touzi> getMyTouZiList(Integer id, Integer state, String start, String end);
	//获取我的投资项目数
	int getMyTouziCount(Integer id);
	//查询我的投资总额
	Double getMyTouZiMoney(Integer id);
	//查询我的收益
	Double getMyShouYi(Integer id);
	//前台查询交易列表
	List<Record> getMyJyList(Integer id, Integer state, String start, String end);
	//后台查询投资列表
	List<Touzi> getHouTaiTouZiList(String starttime, String endtime, String names);
	//后台查询我的交易列表
	List<Record> getMyRecordList(String starttime, String endtime, String names);

}
