package com.lishan.p2p.service.impl;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lishan.p2p.mapper.UserMapper;
import com.lishan.p2p.pojo.Bank;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Province;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.pojo.User;
import com.lishan.p2p.pojo.UserCard;
import com.lishan.p2p.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper dao;
	/**
	 * 用户登录
	 */
	@Override
	public User login(User user) {
		return dao.login(user);
	}
	/**
	 * 用户注册
	 */
	@Override
	public void regist(User user) {
		dao.regist(user);
	}
	/**
	 * 用户个人中心
	 */
	@Override
	public User getUserById(Integer id) {
		return dao.getUserById(id);
	}
	/**
	 * 检查用户是否存在
	 */
	@Override
	public User checkUserName(String username) {
		return dao.checkUserName(username);
	}
	/**
	 * 完善用户信息
	 */
	@Override
	public void updateUserInfo(@Valid User user) {
		dao.updateUserInfo(user);
	}
	/**
	 * 获取城市
	 */
	@Override
	public List<Province> getcity() {
		return dao.getcity();
	}
	/**
	 * 查询我的交易列表
	 */
	@Override
	public List<Record> getRecordList(Integer id, Integer state) {
		return dao.getRecordList(id,state);
	}
	//查询我的投资总额
	@Override
	public Double getMyZtje(Integer id) {
		return dao.getMyZtje(id);
	}
	/**
	 * 查询银行卡列表
	 */
	@Override
	public List<Bank> getBankList() {
		return dao.getBankList();
	}
	/**
	 * 查询我的总借入金额
	 */
	@Override
	public Double getMyZjje(Integer id) {
		return dao.getMyZjje(id);
	}
	/**
	 * 查询我的已还款总额
	 */
	@Override
	public Double getMyYhk(Integer id) {
		return dao.getMyYhk(id);
	}
	/**
	 * 查询消息数量
	 */
	@Override
	public int getMsgNumber(Integer id) {
		return dao.getMsgNumber(id);
	}
	/**
	 * 查询消息列表
	 */
	@Override
	public List<Massage> showMassage(Integer id) {
		return dao.showMassage(id);
	}
	/**
	 * 修改消息状态为已读
	 */
	@Override
	public void updateMassageState(Integer id) {
		dao.updateMassageState(id);
	}
	/**
	 * 查询待收本金
	 */
	@Override
	public Double getMyDsbjMoney(Integer id) {
		//计算已还本金
		//查询投资
		List<Touzi> tlist=dao.getTouziList(id);
		Double dsmoney=0.0;
		for (Touzi touzi : tlist) {
			//获取投资金额
			Double tm=touzi.getTouzimoney();
			//获取期限
			int qx=touzi.getInvest().getBorrow().getTlimit();
			//获取还款次数
			int hnum=dao.getHuanNum(id,touzi.getInid());
			dsmoney+=tm-tm/qx*hnum;
		}
		Double ds=(double) Math.round(dsmoney);
		return ds;
	}
	/**
	 * 查询待收收益
	 */
	@Override
	public Double getMyDshouSy(Integer id) {
		List<Touzi> list=dao.getTouziList(id);
		Double dsmoney=0.0;
		for (Touzi touzi : list) {
			//获取投资金额
			Double tm=touzi.getTouzimoney();
			//获取期限
			int qx=touzi.getInvest().getBorrow().getTlimit();
			//获取还款次数
			int hnum=dao.getHuanNum(id,touzi.getInid());
			//获取利率
			Double rate=touzi.getInvest().getBorrow().getRate();
			dsmoney=tm*rate/100*(qx-hnum);
			
		}
		Double ds=(double) Math.round(dsmoney);
		return ds;
	}
	/**
	 * 未还款金额
	 */
	@Override
	public Double getMyWeiHkMoney(Integer id) {
		List<Invest> invlist=dao.getTInvestList(id);
		Double dsmoney=0.0;
		Double hnum=0.0;
		for (Invest invest : invlist) {
			//获取借款金额
			Double tm=invest.getJemoney();
			//获取期限
			int qx=invest.getBiaolimit();
			//获取还款金额
			 hnum=dao.getHuanMoney(id);
			//获取利率
			Double rate=invest.getBorrow().getRate();
			dsmoney+=tm+tm*rate/100*qx;
			
		}
		if(dsmoney!=null && hnum!=null) {
			Double sss=dsmoney-hnum;
			Double ds=(double) Math.round(sss);
			return ds;
		}
		return 0.0;
	}
	/**
	 * 最近应还款
	 */
	@Override
	public Double getMyZuiJinMoney(Integer id) {
		List<Invest> invlist1=dao.getMyZuiJinMoney(id);
		Double dsmoney=0.0;
		for (Invest invest : invlist1) {
			//获取借款金额
			Double tm=invest.getJemoney();
			//获取利率
			Double rate=invest.getBorrow().getRate();
			dsmoney+=tm*rate/100;
			
		}
		Double ds=(double) Math.round(dsmoney);
		return ds;
	}
	/**
	 * 添加银行卡信息
	 */
	@Override
	public void insertUserCard(UserCard uc) {
		dao.insertUserCard(uc);
	}
	/**
	 * 查询我的银行卡列表
	 */
	@Override
	public List<UserCard> getMyBankCard(Integer id) {
		return dao.getMyBankCard(id);
	}
	/**
	 * 查询银行卡信息
	 * @Param("uid")Integer id,@Param("bid") Integer bid, @Param("jypass")String jypass
	 */
	@Override
	public UserCard getUserCardByJypass(Integer id, Integer bid, String jypass) {
		return dao.getUserCardByJypass(id,bid,jypass);
	}
	/**
	 * 提现
	 */
	@Override
	public void updateTiXian(Double tixmoney, Integer uid, Integer bid) {
		//用户余额减少
		dao.updateTiXianYue(tixmoney,uid);
		//银行卡账户余额增加
		dao.updateTiXianCardYue(tixmoney,uid,bid);
		//插入交易记录
		Record rec=new Record();
		rec.setUid(uid);rec.setJyuid(uid);
		rec.setRecorddate(new Date());
		rec.setRecordmoney(tixmoney);
		
		dao.insertRecord(rec);
		//插入消息提醒
		Massage ms=new Massage();
		ms.setMsgdate(new Date());
		ms.setUid(uid);
		ms.setDes("恭喜您已提现成功");
		dao.insertMassageByTiXian(ms);
	}
	/**
	 * 查询用户余额
	 */
	@Override
	public Double getUserMoney(Integer id) {
		return dao.getUserMoney(id);
	}
	/**
	 * 用户充值
	 */
	@Override
	public void updateCongZhi(Double czmoney, Integer id, Integer bid) {
		//用户余额增加
		dao.updateCongZhiUser(czmoney,id);
		//银行卡余额减少
		dao.updateCongZhiCard(czmoney,id,bid);
		//插入交易记录
		Record rec=new Record();
		rec.setUid(id);rec.setJyuid(id);
		rec.setRecorddate(new Date());
		rec.setRecordmoney(czmoney);
		
		dao.insertRecordByCongZhi(rec);
		//插入消息提醒
		Massage ms=new Massage();
		ms.setMsgdate(new Date());
		ms.setUid(id);
		ms.setDes("恭喜您已充值成功");
		dao.insertMassageByCongZhi(ms);
	}
	/**
	 * 根据id修改消息状态
	 */
	@Override
	public void updateMsgStateByOne(Integer id) {
		dao.updateMsgStateByOne(id);
	}

}
