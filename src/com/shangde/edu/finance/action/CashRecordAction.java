package com.shangde.edu.finance.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;


import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.service.ICashRecord;


/**
 * 流水管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class CashRecordAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(CashRecordAction.class);
	
	/**
	 * 声名流水的PO对象
	 */
	private CashRecord cashRecord=new CashRecord();
	/**
	 * 查询条件
	 */
	private String searchKey;

	/**
	 * 知识点查询条件
	 */
	private QueryCashRecordCondition queryCashRecordCondition;
	
	/**
	 * 声明流水服务
	 */
	private ICashRecord cashRecordService;

	/**
	 * 声明用户的服务
	 */
	private ICustomer customerService;

	private QueryCustomerCondition queryCustomerCondition;
	private Customer customer;
	private List customerList=new ArrayList();
    private String startTime;
    private String endTime;
    private String mail;
    private String startHH;
    private String endHH;
    /**
	 * 课程分类查询条件
	 */
	private QueryCoursesortCondition queryCourseSortCondition;

	/**
	 * 课程分类集
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 获得流水列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	
	private ISellWay sellWayService;
	
	private List<CashRecordDTO> cashList;
	
	public static final String COOKIE_REMEMBERME_KEY="sedu.cookie.ukey"; 
	
	public String getCashRecordList() {
		try {
			if (startTime != null && !"".equals(startTime)) {
				startTime=startTime+startHH;
				this.getQueryCashRecordCondition().setStartTime(startTime);
			}
			if(endTime!=null &&!"".equals(endTime)){
				endTime=endTime+endHH;
				this.getQueryCashRecordCondition().setEndTime(endTime);
			}
			if(cashRecord.getContractId()!=null&&!"".equals(cashRecord.getContractId().trim())){
				this.getQueryCashRecordCondition().setContractId(cashRecord.getContractId().trim());
			}
			if(cashRecord.getCtId()!=0){
				this.getQueryCashRecordCondition().setCtId(cashRecord.getCtId());
			}
			if(cashRecord.getType()!=0){
				this.getQueryCashRecordCondition().setType(cashRecord.getType());
			}
			if(cashRecord.getCusId()!=0){
				this.getQueryCashRecordCondition().setUserId(cashRecord.getCusId());
			}
			if(mail!=null&&!"".equals(mail)){
				Customer customer = this.customerService.getCustomerByEmail(mail.trim());
				if(customer!=null){
					if(customer.getCusId()!=0){
						this.getQueryCashRecordCondition().setUserId(customer.getCusId());
					}
				}else {
					this.getQueryCashRecordCondition().setUserId(1);  //添加一个数据库里不会出现的用户id
				}
			}

			this.getQueryCashRecordCondition().setPageSize(30);
			
			PageResult pageResultList = this.cashRecordService.getCashRecordListAndSellWay(getQueryCashRecordCondition());
			this.cashList=pageResultList.getPageResult();
			pageResultList.setPageResult(getCashList());
			setPage(pageResultList);
			setPageUrlParms();
			 if(getPage()!=null){
				   getPage().setPageSize(30);
			}
			 if(startTime!=null &&!startTime.equals("")){

				 startTime=startTime.substring(0,startTime.indexOf(startHH));
			 }
			 if(endTime!=null&& !endTime.equals("")){

				 endTime=endTime.substring(0,endTime.indexOf(endHH));
			 }
		} catch (Exception e) {
			logger.error("CashRecordAction.Exception", e);
			return ERROR;
		}
		return "listCashRecord";
	}
	


	/*
	 * fanxin  2011-08-09
	 * 
	 * （无需登陆后台）查看订单信息
	 */
	public String getCashRecordListNoLogin() {
		try {
			if (startTime != null && !"".equals(startTime)) {
				startTime=startTime+startHH;
				this.getQueryCashRecordCondition().setStartTime(startTime);
			}
			if(endTime!=null &&!"".equals(endTime)){
				endTime=endTime+endHH;
				this.getQueryCashRecordCondition().setEndTime(endTime);
			}
			if(cashRecord.getContractId()!=null&&!"".equals(cashRecord.getContractId().trim())){
				this.getQueryCashRecordCondition().setContractId(cashRecord.getContractId().trim());
			}
			if(cashRecord.getCtId()!=0){
				this.getQueryCashRecordCondition().setCtId(cashRecord.getCtId());
			}
			if(cashRecord.getType()!=0){
				this.getQueryCashRecordCondition().setType(cashRecord.getType());
			}
			if(cashRecord.getCusId()!=0){
				this.getQueryCashRecordCondition().setUserId(cashRecord.getCusId());
			}
			if(mail!=null&&!"".equals(mail)){
				Customer customer = this.customerService.getCustomerByEmail(mail.trim());
				if(customer!=null){
					if(customer.getCusId()!=0){
						this.getQueryCashRecordCondition().setUserId(customer.getCusId());
					}
				}else {
					this.getQueryCashRecordCondition().setUserId(1);  //添加一个数据库里不会出现的用户id
				}
			}

			this.getQueryCashRecordCondition().setPageSize(30);
			
			PageResult pageResultList = this.cashRecordService.getCashRecordListAndSellWay(getQueryCashRecordCondition());
			this.cashList=pageResultList.getPageResult();
			pageResultList.setPageResult(getCashList());
			setPage(pageResultList);
			setPageUrlParms();
			 if(getPage()!=null){
				   getPage().setPageSize(30);
			}
			 if(startTime!=null &&!startTime.equals("")){

				 startTime=startTime.substring(0,startTime.indexOf(startHH));
			 }
			 if(endTime!=null&& !endTime.equals("")){

				 endTime=endTime.substring(0,endTime.indexOf(endHH));
			 }
			 
		} catch (Exception e) {
			logger.error("CashRecordAction.getCashRecordListNoLogin", e);
			return ERROR;
		}
		return "getCashRecordListNoLogin";
	}
	

	
	private List<CashRecordDTO> getCashList(){
		
		if(this.cashList.size()!=0){
			for (int i = 0; i < this.cashList.size(); i++) {
				SellWay sellWay = sellWayService.getSellWayById(this.cashList.get(i).getPackId());
				if(sellWay!=null){
					this.cashList.get(i).setPackName(sellWay.getSellName());
				}
			}
		}
		return this.cashList;
	}
	
	//给客服人员看的
	public String getUserCashRecordList() {
		try {
			
			if(cashRecord.getCtId()!=0){
				this.getQueryCashRecordCondition().setCtId(cashRecord.getCtId());
			}
			if(cashRecord.getCusId()!=0){
				this.getQueryCashRecordCondition().setUserId(cashRecord.getCusId());
			}
		
			this.getQueryCashRecordCondition().setPageSize(30);
			setPage(this.cashRecordService.getCashRecordList(getQueryCashRecordCondition()));
			setPageUrlParms();
			 if(getPage()!=null){
				   getPage().setPageSize(30);
			}
		} catch (Exception e) {
			logger.error("CashRecordAction.getUserCashRecordList", e);
			return ERROR;
		}
		return "userlistCashRecord";
	}
	
	
	public String openContract(){
		
		return "success";
	}
	public String contract1(){
		Date date=new Date();
	
		return "changeSuccess";
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public CashRecord getCashRecord() {
		return cashRecord;
	}
	public void setCashRecord(CashRecord cashRecord) {
		this.cashRecord = cashRecord;
	}
	public QueryCashRecordCondition getQueryCashRecordCondition() {
		if (queryCashRecordCondition == null) {
			queryCashRecordCondition = new QueryCashRecordCondition();
		}
		return queryCashRecordCondition;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	}
	public void setQueryCashRecordCondition(
			QueryCashRecordCondition queryCashRecordCondition) {
		this.queryCashRecordCondition = queryCashRecordCondition;
	}
	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}
	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}
	public ICustomer getCustomerService() {
		return customerService;
	}
	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}
	public QueryCustomerCondition getQueryCustomerCondition() {
		if (queryCustomerCondition == null) {
			queryCustomerCondition = new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}
	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List customerList) {
		this.customerList = customerList;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public QueryCoursesortCondition getQueryCourseSortCondition() {
		return queryCourseSortCondition;
	}
	public void setQueryCourseSortCondition(
			QueryCoursesortCondition queryCourseSortCondition) {
		this.queryCourseSortCondition = queryCourseSortCondition;
	}
	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}
	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}
	public ICoursesort getCoursesortService() {
		return coursesortService;
	}
	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getStartHH() {
		return startHH;
	}
	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}
	public String getEndHH() {
		return endHH;
	}
	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

}
