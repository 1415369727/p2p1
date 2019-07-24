package com.lishan.p2p.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;

import com.lishan.p2p.pojo.Bank;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Province;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.pojo.User;
import com.lishan.p2p.pojo.UserCard;

public interface UserMapper {
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
	List<Record> getRecordList(@Param("id")Integer id,@Param("state") Integer state);
	//查询我的投资
	Touzi getMyTouzi(Integer id);
	//查询我的投资 总额
	Double getMyZtje(Integer id);
	//获取银行卡列表
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
	//查询我的投资
	List<Touzi> getTouziList(Integer id);
	//获取还款次数
	int getHuanNum(@Param("id")Integer id,@Param("invid") Integer invid);
	//获取还款金额
	Double getHuanMoney(@Param("id")Integer id);
	//查询借款列表
	List<Invest> getTInvestList(Integer id);
	//最近应还款
	List<Invest> getMyZuiJinMoney(Integer id);
	//添加银行卡信息
	void insertUserCard(UserCard uc);
	//查询我的银行卡列表
	List<UserCard> getMyBankCard(Integer id);
	//查询银行卡信息
	UserCard getUserCardByJypass(@Param("uid")Integer id,@Param("bid") Integer bid, @Param("jypass")String jypass);
	//提现用户余额减少
	void updateTiXianYue(@Param("tixmoney")Double tixmoney,@Param("uid") Integer uid);
	//提现修改银行卡余额
	void updateTiXianCardYue(@Param("tixmoney")Double tixmoney,@Param("uid") Integer uid,@Param("bid") Integer bid);
	//提现插入交易记录
	void insertRecord(Record rec);
	//查询用户余额
	Double getUserMoney(Integer id);
	//充值用户余额增加
	void updateCongZhiUser(@Param("czmoney")Double czmoney,@Param("id") Integer id);
	//充值银行卡余额减少
	void updateCongZhiCard(@Param("czmoney")Double czmoney,@Param("id") Integer id,@Param("bid") Integer bid);
	//充值插入交易记录
	void insertRecordByCongZhi(Record rec);
	//充值插入消息提醒
	void insertMassageByCongZhi(Massage ms);
	//提现插入消息提醒
	void insertMassageByTiXian(Massage ms);
	//修改消息状态
	void updateMsgStateByOne(Integer id);

}
