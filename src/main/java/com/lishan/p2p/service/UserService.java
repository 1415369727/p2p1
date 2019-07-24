package com.lishan.p2p.service;

import java.util.List;

import javax.validation.Valid;

import com.lishan.p2p.pojo.Bank;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Province;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.User;
import com.lishan.p2p.pojo.UserCard;

public interface UserService {
	//用户登录
	User login(User user);
	//用户注册
	void regist(User user);
	//用户个人中心
	User getUserById(Integer id);
	//检查用户是否存在
	User checkUserName(String username);
	//完善用户信息
	void updateUserInfo(@Valid User user);
	//获取城市
	List<Province> getcity();
	//查询我的交易列表
	List<Record> getRecordList(Integer id, Integer state);
	//查询我的投资总额
	Double getMyZtje(Integer id);
	//查询银行卡列表
	List<Bank> getBankList();
	//查询我的总借入金额
	Double getMyZjje(Integer id);
	//查询我的已还款总额
	Double getMyYhk(Integer id);
	//查询消息数量
	int getMsgNumber(Integer id);
	//查询消息列表
	List<Massage> showMassage(Integer id);
	//修改消息状态为已读
	void updateMassageState(Integer id);
	//查询代收本金
	Double getMyDsbjMoney(Integer id);
	//查询代收收益
	Double getMyDshouSy(Integer id);
	//未还款金额
	Double getMyWeiHkMoney(Integer id);
	//最近应还款
	Double getMyZuiJinMoney(Integer id);
	//添加银行卡
	void insertUserCard(UserCard uc);
	//查询我的银行卡列表
	List<UserCard> getMyBankCard(Integer id);
	//查询银行卡信息
	UserCard getUserCardByJypass(Integer id, Integer bid, String jypass);
	//提现
	void updateTiXian(Double tixmoney, Integer id, Integer bid);
	//查询用户余额
	Double getUserMoney(Integer id);
	//用户充值
	void updateCongZhi(Double czmoney, Integer id, Integer bid);
	//根据id修改消息状态
	void updateMsgStateByOne(Integer id);

}
