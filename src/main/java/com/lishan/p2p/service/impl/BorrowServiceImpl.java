package com.lishan.p2p.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lishan.p2p.mapper.BorrowMapper;
import com.lishan.p2p.pojo.Borrow;
import com.lishan.p2p.pojo.Invest;
import com.lishan.p2p.pojo.Massage;
import com.lishan.p2p.pojo.Rate;
import com.lishan.p2p.pojo.Record;
import com.lishan.p2p.pojo.Touzi;
import com.lishan.p2p.service.BorrowService;
@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {
	@Autowired
	private BorrowMapper dao;
	/**
	 * 查询利率
	 */
	@Override
	public List<Rate> getRate() {
		return dao.getRate();
	}
	/**
	 * 查询期限
	 */
	@Override
	public Integer getDateLimitByRate(Double rate) {
		
		return dao.getDateLimitByRate(rate);
	}
	/**
	 * 提交借款申请
	 */
	@Override
	public void insertBorrow(Borrow bor) {
		dao.insertBorrow(bor);
	}
	/**
	 * 查询申请列表
	 */
	@Override
	public List<Borrow> showBorrow() {
		return dao.showBorrow();
	}
	/**
	 * 根据时间查询申请列表
	 * @throws ParseException 
	 */
	@Override
	public List<Borrow> showBorrowByDate(String starttime, String endtime, String names) throws ParseException {
		return dao.showBorrowByDate(starttime,endtime,names);
	}
	/**
	 * 借款申请审核通过
	 */
	@Override
	public void updateTongg(Integer id, Integer uid) {
		Massage ms=new Massage();
		ms.setMsgdate(new Date());
		ms.setUid(uid);
		ms.setDes("恭喜您已通过借款审核");
		dao.insertMassage(ms);
		dao.updateTongg(id);
	}
	/**
	 * 借款申请审核不通过
	 */
	@Override
	public void updateBuTongg(Integer id) {
		dao.updateBuTongg(id);
	}
	/**
	 * 前台查询我的借款
	 */
	@Override
	public List<Borrow> getMyByBorrow(Integer uid, String start, String end, Integer state) {
		return dao.getMyByBorrow(uid,start,end,state);
	}
	/**
	 * 发表查询申请
	 */
	@Override
	public Borrow getMyByBorrowId(Integer id) {
		return dao.getMyByBorrowId(id);
	}
	/**
	 * 发布标
	 */
	@Override
	public void insertInvest(Invest invest) {
		//修改标状态
		dao.updateStateToFaBiao(invest.getBid());
		//插入交易
		Record record=new Record();
		record.setUid(invest.getUid());
		record.setJyuid(invest.getUid());
		record.setRecorddate(invest.getBiaodate());
		record.setRecordmoney(invest.getJemoney());
		dao.insertRecord(record);
		//插入消息
		Massage ms=new Massage();
		ms.setUid(invest.getUid());
		ms.setMsgdate(invest.getBiaodate());
		ms.setDes("恭喜您标已成功发布");
		dao.insertMassageByInvest(ms);
		//插入标
		dao.insertInvest(invest);
	}
	/**
	 * 条件查询标列表
	 */
	@Override
	public List<Invest> showInvestList(String starttime, String endtime, String names) {
		return dao.showInvestList(starttime,endtime,names);
	}
	/**
	 * 查询标列表
	 */
	@Override
	public List<Invest> showInvest() {
		return dao.showInvest();
	}
	/**
	 * 查询用户状态
	 */
	@Override
	public String getUserState(Integer id) {
		return dao.getUserState(id);
	}
	/**
	 * 根据标id查询投资列表
	 */
	@Override
	public List<Touzi> getTouZiByInvid(Integer id) {
		return dao.getTouZiByInvid(id);
	}
}
