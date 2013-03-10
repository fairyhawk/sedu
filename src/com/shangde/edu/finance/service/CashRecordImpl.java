package com.shangde.edu.finance.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;

/**
 * CashRecord�������ʵ����?
 * User: guoqiang.liu
 * Date: 2010-08-13
 */
public class CashRecordImpl extends BaseService implements ICashRecord{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addCashRecord(CashRecord cashRecord) {
    	return simpleDao.createEntity("CashRecord_NS.createCashRecord",cashRecord);
    }

    public void delCashRecordById(int id){
        simpleDao.deleteEntity("CashRecord_NS.deleteCashRecordById",id);
    }

    public void updateCashRecord(CashRecord cashRecord) {
        simpleDao.updateEntity("CashRecord_NS.updateCashRecord",cashRecord);
    }

    public CashRecord getCashRecordById(int id) {
        return simpleDao.getEntity("CashRecord_NS.getCashRecordById",id);
    }

    public PageResult getCashRecordList(QueryCashRecordCondition queryCashRecordCondition) {
        return simpleDao.getPageResult("CashRecord_NS.getCashRecordList","CashRecord_NS.getCashRecordCount",queryCashRecordCondition);
    }
    public List<CashRecord> getCashRecordByList(QueryCashRecordCondition queryCashRecordCondition) {
		return simpleDao.getForList("CashRecord_NS.getCashRecordByList", queryCashRecordCondition);
	}
    public List<CashRecord> getSimpleCashRecordByList(
			QueryCashRecordCondition queryCashRecordCondition) {
		return  simpleDao.getForList("CashRecord_NS.getSimpleCashRecordByList", queryCashRecordCondition);
	}
    
    public List<CashRecord> getAllCash(String strId) {
		return simpleDao.getForList("CashRecord_NS.getAllCash", strId);
	}
    
    public List<Integer> getCashPackId(String strId) {
    	return simpleDao.getForList("CashRecord_NS.getCashPackId", strId);
	}
    
    /**
	 * 查询此用户是否已经购买此书
	 * @return
	 */
    public int getshu(CashRecord aa) {
    	return simpleDao.getEntity("CashRecord_NS.getShuid", aa);
    }

	public void delCashRecordByCusId(int cusId) {
		 simpleDao.deleteEntity("CashRecord_NS.deleteCashRecordByCusId",cusId);
	}
	
	public String getUseroderbyid(CashRecord cr) {
		return simpleDao.getEntity("CashRecord_NS.getuseroderid",cr);
	}

	public int getSendCount(CashRecord cr) {
		return simpleDao.getEntity("CashRecord_NS.getSendCount", cr);
	}

	public void delCashRecordExceptCtId(QueryCashRecordCondition queryCashRecordCondition){
		 simpleDao.deleteEntity("CashRecord_NS.delCashRecordExceptCtId", queryCashRecordCondition);
	}
	public List<CashRecordDTO> getCashRecordByWebFromAgentList(QueryCashRecordCondition queryCashRecordCondition){
		return simpleDao.getForList("CashRecord_NS.getCashRecordByWebFromAgentList", queryCashRecordCondition);
	}
	/**
	 * 根据订单ID删除流水
	 * @param ctId
	 */
	public void delCashRecordByCtId(int ctId)
	{
		simpleDao.deleteEntity("CashRecord_NS.deleteCashRecordByCtId", ctId);
	}
	
	/**
	 * 购买成功后修改流水状态
	 */
	public void updateCashReocrdStatus(List<CashRecord> cashRecordList)
	{
		try{
			Date date = new Date();
			for(int i=0;cashRecordList!=null&&cashRecordList.size()!=0&&i<cashRecordList.size();i++)
			{
				CashRecord cashRecordTemp=cashRecordList.get(i);
				cashRecordTemp.setStatus(1);         //流水的支付状态 0 未支付，  1 已支付 ， 2 已取消
				//cashRecordTemp.setShopStatus(1);     // 商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
				//cashRecordTemp.setShopPayTime(new Date()); //货到付款的支付时间 = 激活时间
				
				cashRecordTemp.setShopStatus(1);
				cashRecordTemp.setShopPayTime(date);
				updateCashRecord(cashRecordTemp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *  fanxin  2011-10-13
	 * 支付成功后，修改流水
	 * @param cRecordIdList
	 */
	public void updateCashReocrdStatusByIds(CashRecord cr){
		simpleDao.update("CashRecord_NS.updateCashReocrdStatusByIds", cr);
	}
	/**
	 * yangning 2011-12/21
	 * 支付成功后，修改流水，加入修改paytype类型
	 */
	public void upateCashRecordForOnlinePay(CashRecord cr){
		simpleDao.update("CashRecord_NS.upateCashRecordForOnlinePay", cr);
	}
	
	/**
	 * 何海强 考试查询用
	 * @param cr
	 * @return
	 */
	public int getcashexam(CashRecord cr){
		
		return simpleDao.getEntity("CashRecord_NS.getcashexam", cr);
	}
	
	public PageResult getCashRecordListAndSellWay(QueryCashRecordCondition queryCashRecordCondition){
		return simpleDao.getPageResult("CashRecord_NS.getCashRecordListAndSellWay","CashRecord_NS.getCashRecordCount",queryCashRecordCondition);
	}

	public List<CashRecord> getCashRecordByCtIdForFanli(String contractId) {
		
		return simpleDao.getForList("CashRecord_NS.getCashRecordByCtIdForFanli", contractId);
	}
	/**
	 * 王迪取消流水
	 * @param contractMap
	 */
	public void updateCashRecordStatus(Map<String, String> contractMap) {
		simpleDao.updateEntity("CashRecord_NS.updateCashRecordStatus",contractMap);
	}

	@Override
	public int getShuByCusAndPkgIds(Map<String, String> crMap) {
		return simpleDao.getEntity("CashRecord_NS.getShuids",crMap);
	}

	@Override
	public int getSendShuByCusAndPkgIds(Map<String, String> crMap) {
		return simpleDao.getEntity("CashRecord_NS.getsendShuids",crMap);
	}
	
	 public List<CashRecordDTO> getCashRecordByListNew(int ctId) {
			return simpleDao.getForList("CashRecord_NS.getCashRecordByListNew", ctId);
		}
	 
	 /**
	  * @author 王超
	  * 通过cus_id获取最新购买的流水
	  * @param cusId
	  * @return
	  */
	 public CashRecord getCurrentCashRecordByCusId(int cusId){
		 return simpleDao.getEntity("CashRecord_NS.getCurrentCashRecordByCusId", cusId);
	 }
}
