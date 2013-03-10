package com.shangde.edu.finance.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;


public interface ICashRecord extends Serializable {
	/**
     * 添加流水
     */
    public java.lang.Integer addCashRecord(CashRecord cashRecord);

	/**
     * 删除流水
     */
    public void delCashRecordById(int id);

	/**
     * 更新流水
     */
    public void updateCashRecord(CashRecord cashRecord);

	/**
     * 根据id获得流水对象
     */
    public CashRecord getCashRecordById(int id);
    
	/**
     * 分页查询
     */
    public PageResult getCashRecordList(QueryCashRecordCondition queryCashRecordCondition);

    /**
	 * 查询此用户是否已经购买此书
	 * @return
	 */
    public int getshu(CashRecord aa); 
    
    /**
     * 条件查询
     */
    public List<CashRecord> getCashRecordByList(QueryCashRecordCondition queryCashRecordCondition);
    public List<CashRecord> getSimpleCashRecordByList(QueryCashRecordCondition queryCashRecordCondition);
    
    /**
     * 查询所有流水
     * @return
     */
    public List<CashRecord> getAllCash(String strId);
    
    
    public List<Integer> getCashPackId(String strId);
	/**
     * 删除流水
     */
    public void delCashRecordByCusId(int cusId);

    /**
     * 查询订单编号
     */
    public String getUseroderbyid(CashRecord cr);

    /**
     * 
     */
    public int getSendCount(CashRecord cr);

    /**
     * 删除学员的流水，但保留传入得订单相关的流水
     * @param cusId
     * @param ctId
     */
	public void delCashRecordExceptCtId(QueryCashRecordCondition queryCashRecordCondition);
	/**
	 * 为董元提供的方法
	 */
	public List<CashRecordDTO> getCashRecordByWebFromAgentList(QueryCashRecordCondition queryCashRecordCondition);
	/**
	 * 支付成功后，修改流水状态 status 0 无效，1 有效
	 * @param cashRecordList
	 */
	public void updateCashReocrdStatus(List<CashRecord> cashRecordList);

	/**
	 *  fanxin  2011-10-13
	 * 支付成功后，修改流水
	 * @param cRecordIdList
	 */
	public void updateCashReocrdStatusByIds(CashRecord cr);
	
	/**
	 * 功能:在线支付成功后更新流水
	 * @param cr
	 * Author:Yangning
	 * CreateDate:2011-12-21
	 */
	public void upateCashRecordForOnlinePay(CashRecord cr);
	
	/**
	 * 根据订单ID删除流水
	 * @param ctId
	 */
	public void delCashRecordByCtId(int ctId);
	/**
	 * 何海强 考试查询用
	 * @param cr
	 * @return
	 */
	public int getcashexam(CashRecord cr);
	
	/**
	 * 订单流水 添加售卖方式名字显示
	 * @param queryCashRecordCondition
	 * @return
	 */
	public PageResult getCashRecordListAndSellWay(QueryCashRecordCondition queryCashRecordCondition);
	
	/**
	 * 获取指定订单编号下的流水列表
	 * 
	 * @param contractId
	 * 			订单编号
	 * @return
	 * 			流水列表
	 */
	public List<CashRecord> getCashRecordByCtIdForFanli(String contractId);
	
	public void updateCashRecordStatus(Map<String,String> contractMap);
	
	/**
	 * 查看用户已购买课程数量
	 * @param userIds
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-1-4
	 */
	public int getShuByCusAndPkgIds(Map<String,String> crMap);
	
	
	public int getSendShuByCusAndPkgIds(Map<String,String> crMap);
	
	/**
	 * 新版本订单详情页显示
	 * @param queryCashRecordCondition
	 * @return
	 */
	 public List<CashRecordDTO> getCashRecordByListNew(int ctId);
	 
	 /**
	  * @author 王超
	  * 通过cus_id获取最新购买的流水
	  * @param cusId
	  * @return
	  */
	 public CashRecord getCurrentCashRecordByCusId(int cusId);
}