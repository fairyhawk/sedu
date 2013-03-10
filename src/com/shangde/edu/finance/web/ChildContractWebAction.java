package com.shangde.edu.finance.web;


import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.GetHttpMessage;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.IAddress;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryChildContractCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.ChildContract;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.domain.Coupon;
import com.shangde.edu.finance.domain.Coupontype;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IChildContract;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sys.domain.User;

/**
 * ChildContract管理action
 * 
 * @author fanxin
 * @version 1.0
 */
public class ChildContractWebAction extends CommonAction {
	private static final Logger logger = Logger.getLogger(ChildContractWebAction.class);
	private static final long serialVersionUID = 1L;
	
	/**
	 * 声名ChildContract的PO对象
	 */
	private ChildContract childContract = new ChildContract();
	private QueryChildContractCondition queryChildContractCondition;
	private int id;
	
	/**
	 * 声明订单服务
	 */
	private IChildContract childContractService;
	private List<ChildContract> childConList = new ArrayList<ChildContract>();
	
	
	/**
     * 添加 ChildContract
     * @return  String
     * @author fanxin
     * @version 1.0
     */
    public String addChildContract() {
    	try{
    		Date date = new Date();
    		String get_order = String.valueOf(getLoginUserId()) + date.getTime() + "";
    		
    		ChildContract addChContract = new ChildContract();
    		//设置属性
    		
    		addChContract.setCusId(getLoginUserId());
    		addChContract.setChildContractId(get_order);
    		addChContract.setContractId("34720111028001857");
    		addChContract.setCreateTime(date);
    		addChContract.setMoney(new BigDecimal(500));
    		addChContract.setPayTime(null);
    		addChContract.setPayType(0); // 0:赠送（内部开通）/1:网上支付（支付宝）/2:货到付款/3:网银在线/4:快钱/5:代理商开通/7:银行汇款
    		addChContract.setStatus(0);   //0 未支付，  1 已支付 ， 2 已取消
    		addChContract.setCtId(182339);
    		childContractService.addChildContract(addChContract);
    		return "addChildContract";
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("addChildContract方法出现异常！");
    	}
    	return "error";
    	
    }
	
	/**
     * 根据id获取 ChildContract对象
     * @return  String
     * @author fanxin
     * @version 1.0
     */
    public String getChildContractById(){
    	try{
    		childContract = childContractService.getChildContractById(id);
    		
    		return "getChildContractById";
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("getChildContractById方法出现异常！");
    	}
    	return "error";
    }
	
	/**
     * 获取 ChildContract对象
     * @return  String
     * @author fanxin
     * @version 1.0
     */
    public String getChildContractByCondition(){
    	try{
    		childContract = childContractService.getChildContract(getQueryChildContractCondition());
    		
    		return "getChildContractByCondition";
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("getChildContractByCondition方法出现异常！");
    	}
    	return "error";
    }  
    
    
	/**
     * 根据条件获取 ChildContract集合
     * @return  String
     * @author fanxin
     * @version 1.0
     */
    public String getChildContractList(){
    	try{
    		childConList = childContractService.getChildContractList(getQueryChildContractCondition());
    		
    		return "getChildContractList";
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("getChildContractList方法出现异常！");
    	}
    	return "error";
    }
	
	
	public ChildContract getChildContract() {
		return childContract;
	}
	
	public void setChildContract(ChildContract childContract) {
		this.childContract = childContract;
	}
	
	public QueryChildContractCondition getQueryChildContractCondition() {
		if (queryChildContractCondition == null) {
			queryChildContractCondition = new QueryChildContractCondition();
		}
		return queryChildContractCondition;
	}
	
	public void setQueryChildContractCondition(
			QueryChildContractCondition queryChildContractCondition) {
		this.queryChildContractCondition = queryChildContractCondition;
	}
	
	public IChildContract getChildContractService() {
		return childContractService;
	}
	
	public void setChildContractService(IChildContract childContractService) {
		this.childContractService = childContractService;
	}

	public List<ChildContract> getChildConList() {
		return childConList;
	}

	public void setChildConList(List<ChildContract> childConList) {
		this.childConList = childConList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 

	



}
