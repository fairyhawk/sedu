package com.shangde.edu.finance.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.MessageRemindDTO;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.dto.ContractDTO;
import com.shangde.edu.finance.dto.ContractExcelDTO;


public interface IContract {

	/**
     * 添加订单
     */
    public java.lang.Integer addContract(Contract contract);

	/**
     * 删除订单
     */
    public void delContractById(int id);

	/**
     * 更新订单
     */
    public void updateContract(Contract contract);

	/**
     * 根据id获得订单对象
     */
    public Contract getContractById(int id);
    public Contract getContractByCus_Id(int cus_id) ;
    /**
     * 根据status获得订单对象
     */
    public List<Contract> getContractBystatus(int status);

	/**
     * 分页查询
     */
    public PageResult getContractList(QueryContractCondition queryContractCondition);
    /**
     * 网盟用查询
     */
    public PageResult getContractListwm(QueryContractCondition queryContractCondition);
    
    /**
     * 分页查询
     */
    public PageResult getContractCODList(QueryContractCondition queryContractCondition);
    
	/**
     * 根据查询条件获得List
     */
    public List<Customer> getContract(QueryContractCondition queryContractCondition);

    /**
     * 获取用户订单张数
     * @param loginUserId
     * @return
     */
	public int getUserContractCount(int loginUserId);
	
	/**
	 *根据用户id和课程id获取订单状态
	 */
	 public String getStatus(CashRecord cr);
	/**
	 * 按时间段统计订单量
	 * @return
	 */
	public int getContractNumber(QueryContractCondition queryContractCondition);
	public int getContractNumberBySubject(QueryContractCondition queryContractCondition);
	public PageResult getContractNumberBySubjectList(QueryContractCondition queryContractCondition);
	/**
	 * 按时间段统计已付款订单量
	 * @return
	 */
	public int getPayContractNumber(QueryContractCondition queryContractCondition);
	/**
	 *统计每个月的订单量
	 * @return
	 */
	public int getMonthContractNumber(String month);
	/**
	 *统计每个月的已付订单量
	 * @return
	 */
	public int getMonthPayContractNumber(String month);
	/**
	 * 统计每天的订单量,月下面的日期
	 * @return
	 */
	public List getMonthDayContract(String month);
	/**
	 * 统计每天的已付订单量,月下面的日期
	 * @return
	 */
	public List getMonthDayPayContract(String month);
	/**
     * 删除订单
     */
    public void delContractByCusId(int cusId);
    
    /**
     *查询订单是否支付成 
     */
    public int getIsoder(Contract ct);
    
   /**
    * 
    * 查询一条订单数据
    */
  
    public Contract  getUserOderByid (Contract ct);
    
    /**
     * 
     * 根据条件查询订单list
     */
    public List<Contract> getContractByList(QueryContractCondition queryContractCondition);
    public List<Contract> queryOrderByList(QueryContractCondition queryContractCondition);
     
    public List<MessageRemindDTO> getEmailByContractStatus(QueryContractCondition queryContractCondition);
    
    /**
     * 根据用户id查询货到付款的订单
     * 
     */
    public List<Contract> getContractCount(int cusId);
    /**
     * 查出支付类型的订单
     * 
     */
    public List<Contract> getContractByPayType(int payType);
    /***
     * 	根据课程ID查订单
     */
    public PageResult getContractByCourseId(QueryContractCondition queryContractCondition);

    /**
     * 删除学员的订单，但保留传入得订单
     * @param queryContractCondition
     */
	public void delContractExceptCtId(
			QueryContractCondition queryContractCondition);
	/**
	 * 查询订单金额总计
	 * @param queryContractCondition
	 * @return
	 */
	public int getContractPriceSum(QueryContractCondition queryContractCondition);
	/**
     * 查询订单金额总计网盟用
     * @param queryContractCondition
     * @return
     */
	public int getContractPriceSumwm(QueryContractCondition queryContractCondition);
	/**
	 * 订单导出excel的查询语句
	 * @param queryContractCondition
	 * @return
	 */
	public List<Contract> getContractLists(QueryContractCondition  queryContractCondition);
	
	/**
	 * 为导出excle提供方法
	 */
	public List<ContractDTO> getContractDTOList(QueryContractCondition queryContractCondition);
	
	/**
	 * 货到付款导出excle查询
	 * @param queryContractCondition
	 * @return
	 */
	public List<ContractExcelDTO> getCodContractLists(QueryContractCondition  queryContractCondition);
	/**
	 * 为董元提供的接口
	 */
	public List<Contract> getContractByFromAgentList(QueryContractCondition queryContractCondition);
	
	/**
	 * 根据专业ID 得到当前专业订单总数
	 * @param queryContractCondition
	 * @return
	 */
	public List<CashRecordDTO> getContractSum(QueryContractCondition queryContractCondition);
	
	/**
	 * 根据专业ID 得到当前专业已付订单总数
	 * @param queryContractCondition
	 * @return
	 */
	public List<CashRecordDTO> getYfContractSum(QueryContractCondition queryContractCondition);
	
	
//	/**
//	 * 根据专业ID 支付方式ID 得到当前支付方式订单总数
//	 */
//	public int getContractSumByPayType(QueryContractCondition queryContractCondition);
//	
//	/**
//	 * 根据专业ID 支付方式ID 得到当前支付方式支付总数
//	 */
//	public int getYfContractSumByPayType(QueryContractCondition queryContractCondition);
	
	/**
	 * 获取对账的金额
	 * 对账金额为已经完成购买流程的订单的金额。即支付方式为支付宝、网银在线、快钱和货到付款的订单
	 * 购买流程成功结束的订单的总金额的合计。
	 * @param queryContractCondition
	 * @return
	 */
	public int getReconciliationAmout(QueryContractCondition queryContractCondition);
	
	/**
	 * 获取返利网数据信息
	 * 
	 * @param queryContractCondition
	 * 				返利网查询条件
	 * @return
	 * 				订单的集合
	 */
	public List<Contract> getContractListForFanli(QueryContractCondition queryContractCondition);
	/**
	 * yanbaixi
	 * 统计支付方式为块钱、网银、支付宝的未付款数量
	 */
	public int getNopayCountContract(int userid);
	
	/**
	 * 功能:是否未支付网银订单
	 * @param map
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-19
	 */
	public Contract getValidNotPayContract(Map map);
	
	
	public Contract getContractByCidAndCusId(Map map);
	
	/**
	 * 根据cusId和contractNO查找订单
	 * @param cusId
	 * @param contractNo
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-2-23
	 */
	public Contract getNotPayedContractByCnoAndCusId(int cusId,String contractNo);
	
	/**
	 * 查询定单与已支付子订单数量
	 * @param queryContractCondition
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-2-28
	 */
	public PageResult getContractAndSelledContractNumList(QueryContractCondition queryContractCondition);
	
	public Contract getContractAndSelledContractNumById(int ctId);
	
	/**
	 *订单级联查询   ->>订单  --->流水-->商品 
	 * @param queryContractCondition
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-3-14
	 */
	public PageResult getUserCenterContractList(QueryContractCondition queryContractCondition);
	/**
	 * 根据orderCode查询订单对象
	 * @param orderCode
	 */
	Contract getContractByCode(String orderCode);
	//查询未支付订单
	public int getUserContractCountNotPay(int loginUserId);
	
	/**
	 * 根据用户id 查询个需求数量 
	 * @param cusId
	 * @return
	 */
	public int getPaySumByCusId(int cusId);
	
	/**
	 * 根据用户id查询取消的数量 
	 * @param cusId
	 * @return
	 */
	public int getCancelSumByCusId(int cusId);
}
