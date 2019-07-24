package com.lishan.p2p.service;

import java.util.List;

import com.lishan.p2p.pojo.Back;
import com.lishan.p2p.pojo.Invest;

public interface BackService {
	//获取标的信息
	Invest getInvestById(Integer id);
	//还款
	void backMoney(Back back);
	//判断用户本月是否还款
	List<Back> getDateByUid(Integer id, Integer invid);

}
