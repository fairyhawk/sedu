package com.shangde.edu.msg.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCourseBeforeTodayCondition;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.exam.service.IExampaper;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.msg.condition.QueryMessageCondition;
import com.shangde.edu.msg.condition.QueryUserMsgCondition;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.service.ITaskCus;

public class UserMsgWebAction extends CommonAction {
	private static final Logger logger  = Logger.getLogger(UserMsgWebAction.class);
	private Message msg;//信息
	private UserMsg userMsg;
	private Map<String,String> msgTypeMap;
	private IMessage messageService;//消息服务
	private ITaskCus taskCusService;
	private ICustomer customerService;
	private IUserMsg userMsgService;
	private QueryMessageCondition queryMessageCondition;
	private QueryUserMsgCondition queryUserMsgCondition;
	private List<TaskCus> taskCusList;
	private boolean ishavebuy;//是否购买
	
	//订单服务
	private IContract contractService;
	//试卷服务 
	private IExampaper exampaperService;
	//课程服务
	private ICourse courseService;
	
	private String tabType;
	
	/**
	 * @author chenshuai
	 * 功能：发送信息
	 * @param args
	 * @return
	 */
	public String sendMsg(){
		try{
			
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "sendMsg";
	}
	
	/**
	 * @author chenshuai
	 * 功能：用户删除信息
	 * @param args
	 * @return
	 */
	public String deleteMsg(){
		try{
			if(userMsg != null && userMsg.getId() > 0){
				int userId = this.getLoginUserId();
				userMsg = userMsgService.getUserMsgById(userMsg.getId());
				
				if(userId!=0 && userMsg.getReceiverId()!=0 && userId == userMsg.getReceiverId()){
					userMsgService.delUserMsgById(userMsg.getId());
				}
			}
		}catch(Exception ex){
			logger.error("UserMsgWebAction.deleteMsg", ex);
			return ERROR;
		}
		return "deleteMsg";
	}
	
	/**
	 * @author chenshuai
	 * 功能：列出发件箱
	 * @param args
	 * @return
	 */
	public String listSendMsgs(){
		try{
			this.getQueryUserMsgCondition().setSenderId(this.getLoginUserId());
			queryUserMsgCondition.setSenderType(0);
			queryUserMsgCondition.setPageSize(5);
			
			this.setPage(userMsgService.getUserMsgListByConditon(queryUserMsgCondition));
			this.getPage().setPageSize(5);
			this.setPageUrlParms();
		}catch(Exception ex){
			logger.error("UserMsgWebAction.listSendMsgs", ex);
			return ERROR;
		}
		return "listSendMsgs";
	}
	
	/**
	 * @author chenshuai
	 * 功能：列出收件箱
	 * @param args
	 * @return
	 */
	public String listReceiveMsgs(){
		try{
			int userId = this.getLoginUserId();
			
			if(userId > 0){
				userMsgService.updateReadStatus(userId);
				
				this.getQueryUserMsgCondition().setReceiverId(userId);
				queryUserMsgCondition.setSenderType(0);
				queryUserMsgCondition.setPageSize(12);
				
				this.setPage(userMsgService.getUserMsgListByConditon(queryUserMsgCondition));
				this.getPage().setPageSize(12);
				this.setPageUrlParms();
				
				QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
				queryTaskCusCondition.setCusId(userId);
				taskCusList = taskCusService.getWebTaskCusList(queryTaskCusCondition);
				
				//判断用户是否购买课程
				QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
				queryCourseCondition.setCusId(this.getLoginUserId());
				
				List<SellWay> butSellWayList = courseService.getUserCenterSellWayListByCusId(queryCourseCondition);
				if(!butSellWayList.isEmpty()){
					ishavebuy = true;
				}
				
				if(taskCusList != null){//对任务集合进行操作
					TaskCus taskCusTemp = null;
					TaskCus taskCusTemp2 = null;
					for(int i = taskCusList.size() -1; i >= 0; i --){//去掉前置任务未完成的任务
						taskCusTemp = taskCusList.get(i);
						if(taskCusTemp.getTask().getPreTaskId() > 0){
							queryTaskCusCondition.setTaskId(taskCusTemp.getTask().getPreTaskId());
							taskCusTemp2 = taskCusService.getTaskCusByTaskIdAndCusId(queryTaskCusCondition);
							
							if(taskCusTemp2 != null && taskCusTemp2.getIsComplete() == 0){
								taskCusList.remove(taskCusTemp);
							}
						}
					}
					
					if(taskCusList.size() > 4){//只留4个
						for(int i = 4; i < taskCusList.size(); i ++){
							taskCusList.remove(i);
						}
					}
				}
			}
		}catch(Exception ex){
			logger.error("UserMsgWebAction.listReceiveMsgs", ex);
			return ERROR;
		}
		return "listReceiveMsgs";
	}
	
	/**
	 * @author chenshuai
	 * 功能：删除用户收件箱信息
	 * @param args
	 * @return
	 */
	public String deleteReceiveMsg(){
		try{
			if(userMsg != null && userMsg.getId() > 0){
				this.getQueryUserMsgCondition().setReceiverId(this.getLoginUserId());
				queryUserMsgCondition.setId(userMsg.getId());
				int msgSize = userMsgService.getMsgSizeByUserIdAndUserMsgId(queryUserMsgCondition);
				if(msgSize > 0){
					userMsgService.delUserMsgById(userMsg.getId());
				}
			}
		}catch(Exception ex){
			logger.error("UserMsgWebAction.deleteReceiveMsg", ex);
			return ERROR;
		}
		return "deleteReceiveMsg";
	}
	
	/**
	 * @author chenshuai
	 * 功能：批量删除信息
	 * @param args
	 * @return
	 */
	public String deleteMsgs(){
		try{
			
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "deleteMsgs";
	}
	
	/**
	 * @author chenshuai
	 * 功能：检测消息未读信息
	 * @param args
	 * @return
	 */
	public String getCountUnreadMsgs(){
		try{
			int userId = this.getLoginUserId();
			int size = 0;
			
			if(userId > 0){
				size = userMsgService.getCountUnreadMsgs(userId);
			}
			
			this.setResult(new Result<Object>(true, "" + size, null, null));
		}catch(Exception ex){
			logger.error("UserMsgWebAction.getCountUnreadMsgs", ex);
			return ERROR;
		}
		return "json";
	}
	
	
	/**
	 * <br>
	 * <b>功能：插入课程更新的消息</b><br>
	 * <b>作者：</b>李志强 Kobe.Lee<br>
	 * <b>日期：</b> 2012.06.19 <br>
	 */
	public String insertCourseMsgs(){
		try{
			int userId = this.getLoginUserId();
			//判断今天是否已经插入过消息，如果插入过不在进行操作
			QueryCourseBeforeTodayCondition query = new QueryCourseBeforeTodayCondition();
			Date dt=new Date();
		    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
		    String today_time = matter.format(dt);
			query.setCus_id(userId);
			query.setToday_time(today_time);
			int count = userMsgService.searchAddCourseMsg(query);
			if(count==0){
				//获取当前日期前二天的时间
				Calendar canlendar = Calendar.getInstance(); //java.util包 
				canlendar.add(Calendar.DATE, -2); //日期减 如果不够减会将月变动
				matter.applyPattern("yyyy-MM-dd 00:00:00");
				String before2DaysDate = matter.format(canlendar.getTime());
				query.setBefore_create_time(before2DaysDate);
				userMsgService.insertCourseMsgs(query);
			}
			this.setResult(new Result<Object>(true,"success",null,null));
		}catch (Exception e){
			logger.error("UserMsgWebAction.insertCourseMsgs",e);
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * <br>
	 * <b>功能：加载通知数量</b><br>
	 * <b>作者：</b>李志强 Kobe.Lee<br>
	 * <b>日期：</b> 2012.06.27 <br>
	 */
	public String loadNotification(){
		int cusId = this.getLoginUserId();
		//订单数量
		int orderNum = contractService.getUserContractCountNotPay(cusId);
		//消息数量
		int msgNum = userMsgService.getCountUnreadMsgs(cusId);
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("orderNum", orderNum);
		map.put("msgNum", msgNum);
		setResult(new Result<Map<String,Integer>>(false,"success",null,map));
		return "json";
	} 
	
	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public Map<String, String> getMsgTypeMap() {
		return msgTypeMap;
	}

	public void setMsgTypeMap(Map<String, String> msgTypeMap) {
		this.msgTypeMap = msgTypeMap;
	}

	public IMessage getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public IUserMsg getUserMsgService() {
		return userMsgService;
	}

	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}
	
	public QueryMessageCondition getQueryMessageCondition() {
		if(queryMessageCondition == null){
			queryMessageCondition = new QueryMessageCondition();
		}
		return queryMessageCondition;
	}

	public void setQueryMessageCondition(QueryMessageCondition queryMessageCondition) {
		this.queryMessageCondition = queryMessageCondition;
	}

	public QueryUserMsgCondition getQueryUserMsgCondition() {
		if(queryUserMsgCondition == null){
			queryUserMsgCondition = new QueryUserMsgCondition();
		}
		return queryUserMsgCondition;
	}

	public void setQueryUserMsgCondition(QueryUserMsgCondition queryUserMsgCondition) {
		this.queryUserMsgCondition = queryUserMsgCondition;
	}

	public UserMsg getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(UserMsg userMsg) {
		this.userMsg = userMsg;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}

	public List<TaskCus> getTaskCusList() {
		return taskCusList;
	}

	public void setTaskCusList(List<TaskCus> taskCusList) {
		this.taskCusList = taskCusList;
	}

	public String getTabType() {
		return tabType;
	}

	public void setTabType(String tabType) {
		this.tabType = tabType;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public IExampaper getExampaperService() {
		return exampaperService;
	}

	public void setExampaperService(IExampaper exampaperService) {
		this.exampaperService = exampaperService;
	}

	public boolean isIshavebuy() {
		return ishavebuy;
	}

	public void setIshavebuy(boolean ishavebuy) {
		this.ishavebuy = ishavebuy;
	}
}
