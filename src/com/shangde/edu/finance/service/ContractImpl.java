package com.shangde.edu.finance.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.MessageRemindDTO;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.dto.ContractDTO;
import com.shangde.edu.finance.dto.ContractExcelDTO;

@SuppressWarnings("unchecked")
public class ContractImpl extends BaseService implements IContract {
	public java.lang.Integer addContract(Contract contract) {
		return simpleDao.createEntity("Contract_NS.createContract", contract);
	}

	public void delContractById(int id) {
		simpleDao.deleteEntity("Contract_NS.deleteContractById", id);
	}
	public void updateContract(Contract contract) {
		simpleDao.updateEntity("Contract_NS.updateContract", contract);
	}
	public Contract getContractById(int id) {
		return simpleDao.getEntity("Contract_NS.getContractById", id);
	}
	public Contract getContractByCus_Id(int cus_id) {
		return simpleDao.getEntity("Contract_NS.getContractByCus_Id", cus_id);
	}

	public PageResult getContractList(
			QueryContractCondition queryContractCondition) {

		return simpleDao.getPageResult("Contract_NS.getContractList",
				"Contract_NS.getContractCount", queryContractCondition);
	}
	
	
	
	public List<Contract> getContractLists(QueryContractCondition queryContractCondition)
	{
		return simpleDao.getForList("Contract_NS.getContractLists",queryContractCondition);
	}

	//查询订单金额总计 getContractPriceSum
	public int getContractPriceSum(QueryContractCondition queryContractCondition) {
		if(simpleDao.getEntity("Contract_NS.getContractPriceSum",queryContractCondition)!=null)
		{
			return simpleDao.getEntity("Contract_NS.getContractPriceSum",queryContractCondition);
		}else
		{
			return 0;
		}
		 
		
	}
	
	/**
     * 网盟用查询
     */
    public PageResult getContractListwm(
            QueryContractCondition queryContractCondition) {

        return simpleDao.getPageResult("Contract_NS.getContractListwm",
                "Contract_NS.getContractCountwm", queryContractCondition);
    }
    
	
	//查询订单金额总计 getContractPriceSumwm 网盟用highso0004
    public int getContractPriceSumwm(QueryContractCondition queryContractCondition) {
        if(simpleDao.getEntity("Contract_NS.getContractPriceSumwm",queryContractCondition)!=null)
        {
            return simpleDao.getEntity("Contract_NS.getContractPriceSumwm",queryContractCondition);
        }else
        {
            return 0;
        }
         
        
    }
    
	
	public List<Customer> getContract(
			QueryContractCondition queryContractCondition) {

		return simpleDao.getForList("Contract_NS.getContract",
				queryContractCondition);
	}

	public int getUserContractCount(int loginUserId) {
		return simpleDao.getEntity("Contract_NS.getUserContractCount",
				loginUserId);
	}
	
	public int getUserContractCountNotPay(int loginUserId){
		return simpleDao.getEntity("Contract_NS.getUserContractCountNotPay", loginUserId);
	}

	public String getStatus(CashRecord cr) {
		return simpleDao.getEntity("Contract_NS.getStatus", cr);
	}

	public int getContractNumber(QueryContractCondition queryContractCondition) {
		int number = simpleDao.getEntity("Contract_NS.getContractNumber",
				queryContractCondition);
		return number;
	}
	public int getContractNumberBySubject(QueryContractCondition queryContractCondition){
		int	number=simpleDao.getEntity("Contract_NS.getContractNumberBySubject", queryContractCondition);
		return number;
	}
	public PageResult getContractNumberBySubjectList(
			QueryContractCondition queryContractCondition) {

		return simpleDao.getPageResult("Contract_NS.getContractBySubjectList",
				"Contract_NS.getContractBySubjectCount", queryContractCondition);
	}
	public int getPayContractNumber(
			QueryContractCondition queryContractCondition) {
		int number = simpleDao.getEntity("Contract_NS.getPayContractNumber",
				queryContractCondition);
		return number;
	}

	public int getMonthContractNumber(String month) {
		return simpleDao.getEntity("Contract_NS.getMonthContractNumber", month);
	}

	public int getMonthPayContractNumber(String month) {
		return simpleDao.getEntity("Contract_NS.getMonthPayContractNumber",
				month);
	}

	public List getMonthDayContract(String month) {
		return simpleDao.getForList("Contract_NS.getMonthDayContract", month);
	}

	public List getMonthDayPayContract(String month) {
		return simpleDao
				.getForList("Contract_NS.getMonthDayPayContract", month);
	}

	public void delContractByCusId(int cusId) {
		simpleDao.deleteEntity("Contract_NS.deleteContractByCusId", cusId);
	}

	/**
	 * 查询订单是否支付成
	 */
	public int getIsoder(Contract ct) {

		return simpleDao.getEntity("Contract_NS.getIsoderbyid", ct);
	}

	public Contract getUserOderByid(Contract ct) {
		return simpleDao.getEntity("Contract_NS.getuseroderbyid", ct);
	}

	public List<Contract> getContractByList(
			QueryContractCondition queryContractCondition) {
		return simpleDao.getForList("Contract_NS.getContractByList",
				queryContractCondition);
	}

	public List<MessageRemindDTO> getEmailByContractStatus(
			QueryContractCondition queryContractCondition) {
		return simpleDao.getForList("Contract_NS.getEmailByContractStatus",
				queryContractCondition);
	}

	public List<Contract> getContractBystatus(int status) {
		
		return simpleDao.getForList("Contract_NS.getContractByStatus", status);
	}
	
	 public List<Contract> getContractCount(int cusId){
		 
		 return simpleDao.getForList("Contract_NS.getContractCountByCusId", cusId);
	 }
	 
	 public List<Contract> getContractByPayType(int payType){
		 
		 return simpleDao.getForList("Contract_NS.getContractByPayType", payType);
	 }
	public PageResult getContractCODList(QueryContractCondition queryContractCondition) {

			return simpleDao.getPageResult("Contract_NS.getContractCODList",
					"Contract_NS.getContractCODCount", queryContractCondition);
		}
	 public PageResult getContractByCourseId(
			QueryContractCondition queryContractCondition) {
		 return simpleDao.getPageResult("Contract_NS.getContractByCourseId", "Contract_NS.getContractByCourseIdCount", queryContractCondition);
		
	}

	public void delContractExceptCtId(
			QueryContractCondition queryContractCondition) {
		simpleDao.deleteEntity("Contract_NS.delContractExceptCtId", queryContractCondition);
	}
	
	public List<Contract> getContractByFromAgentList(QueryContractCondition queryContractCondition){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		queryContractCondition.setPayStartTime(sdf.format(cal.getTime()));
		queryContractCondition.setPayEndTime(sdf.format(new Date()));
	  return  simpleDao.getForList("Contract_NS.getContractByFromAgent",queryContractCondition);
	}

	public List<Contract> queryOrderByList(
			QueryContractCondition queryContractCondition) {
		return simpleDao.getForList("Contract_NS.queryOrderByList",
				queryContractCondition);
	}

	public List<CashRecordDTO> getContractSum(QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		
		return simpleDao.getForList("Contract_NS.getContractSum", queryContractCondition);
	}

	public List<CashRecordDTO> getYfContractSum(QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Contract_NS.getYfContractSum", queryContractCondition);
	}

	/**
	 * 货到付款导出excel
	 */
	public List<ContractExcelDTO> getCodContractLists(QueryContractCondition queryContractCondition) {
		return simpleDao.getForList("Contract_NS.getContractCODListExcel",queryContractCondition);
	}

	public PageResult getContractSellWayByCusId(
			QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("Contract_NS.getContractAndSellWayByCusId", "Contract_NS.getContractAndSellWayCount", queryContractCondition);
	}
	
	public int getReconciliationAmout(QueryContractCondition queryContractCondition) {
		if (simpleDao.getEntity("Contract_NS.getReconciliationAmout",
				queryContractCondition) != null) {
			return simpleDao.getEntity("Contract_NS.getReconciliationAmout",
					queryContractCondition);
		} else {
			return 0;
		}
	}

	/**
	 * 为导出excle
	 */
	public List<ContractDTO> getContractDTOList(
			QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Contract_NS.getContractDTOLists",queryContractCondition);
	}

	public List<Teacher> getTeacherBySellWayId(int sellWayId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Contract_NS.getTeacherBySellWayId", sellWayId);
	}
	
	public List<Contract> getContractListForFanli(QueryContractCondition queryContractCondition){
		
		return simpleDao.getForList("Contract_NS.getContractListForFanli",queryContractCondition);
	}
	/**
	 * yanbaixi
	 * 统计支付方式为块钱、网银、支付宝的未付款数量
	 */
	public int getNopayCountContract(int userid){
		return simpleDao.getEntity("Contract_NS.getNopayCountContract", userid);
	}

	@Override
	public Contract getValidNotPayContract(Map map) {
		return simpleDao.getEntity("Contract_NS.getValidContractById", map);
	}

	@Override
	public Contract getContractByCidAndCusId(Map map) {
		
		return simpleDao.getEntity("Contract_NS.getContractByCId", map);
	}

	@Override
	public Contract getNotPayedContractByCnoAndCusId(int cusId, String contractNo) {
		Map paramMap = new HashMap();
		paramMap.put("cusId", cusId);
		paramMap.put("contractNO", contractNo);
		return simpleDao.getEntity("Contract_NS.getNotPayedContractByCnoAndCusId", paramMap);
	}

	@Override
	public PageResult getContractAndSelledContractNumList(
			QueryContractCondition queryContractCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("Contract_NS.getContractListAndSelledChildCount", "Contract_NS.getContractCount", queryContractCondition);
	}

	@Override
	public Contract getContractAndSelledContractNumById(int ctId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Contract_NS.getContractAndSelledChildCount", ctId);
	}

	@Override
	public PageResult getUserCenterContractList(
			QueryContractCondition queryContractCondition) {
		return  simpleDao.getPageResult("Contract_NS.getContractListUserCenter", "Contract_NS.getContractCount", queryContractCondition);
	}

	@Override
	public Contract getContractByCode(String orderCode) {
		return simpleDao.getEntity("Contract_NS.getContractByCode", orderCode);
	}
	
	public int getPaySumByCusId(int cusId){
		return simpleDao.getEntity("Contract_NS.getUserContractCountPay", cusId);
	}
	
	public int getCancelSumByCusId(int cusId){
		return simpleDao.getEntity("Contract_NS.getUserContractCountCancel", cusId);
	}
	
}
