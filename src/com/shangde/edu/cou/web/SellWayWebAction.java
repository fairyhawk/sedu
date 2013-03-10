package com.shangde.edu.cou.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.testng.log4testng.Logger;

import net.sf.json.JSONArray;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cou.webdto.StudyStatisticsDTO;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;
import com.shangde.edu.cou.webdto.UserCenterSubjectCourseDTO;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;

public class SellWayWebAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(SellWayWebAction.class);
	
	
	/**
	 * 
	 * @return
	 */
	private SellWay sellWay;
	private int sellId;
	private ISellWay sellWayService;
	private SellWay sellWayWeb;
	private ICourse courseService;
	
	private List<SellWay> sellWayList;
	public List<SellWay> getSellWayList() {
		return sellWayList;
	}
	public void setSellWayList(List<SellWay> sellWayList) {
		this.sellWayList = sellWayList;
	}
	
	private String sellIds;
	public String getSellIds() {
		return sellIds;
	}
	public void setSellIds(String sellIds) {
		this.sellIds = sellIds;
	}
	
	private List<Integer> sellIdList;
	public List<Integer> getSellIdList() {
		return sellIdList;
	}
	public void setSellIdList(List<Integer> sellIdList) {
		this.sellIdList = sellIdList;
	}


	public String BuyNow(){
		try {
			//批量购买时 （传入多个售卖方式Id）
			if(sellIds!=null){
				String[] sellIdArray = sellIds.split(",");
				if(sellIdArray.length>0){
					List<Integer> sellIdListTemp = new ArrayList<Integer>();
					for(int i=0;i<sellIdArray.length;i++){
						sellIdListTemp.add(Integer.parseInt(sellIdArray[i]));
					}
					sellWayList = sellWayService.getSellWayByIdList(sellIdListTemp);
				}
				if(sellWayList!=null && sellWayList.size()>0){
					setResult( new Result<List> (false,"success",null,sellWayList));
				}else{
					setResult( new Result<String>(false,"failure",null,null));
				}
			}
			
		} catch (RuntimeException e) {
			logger.error("SellWayWebAction.BuyNow", e);
			setResult( new Result<String>(false,"failure",null,null));
			return ERROR;
		}
		return "json";
	}

	public SellWay getSellWay() {
		return sellWay;
	}

	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
	}

	public int getSellId() {
		return sellId;
	}
	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
	public ISellWay getSellWayService() {
		return sellWayService;
	}
	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}
	public SellWay getSellWayWeb() {
		return sellWayWeb;
	}
	public void setSellWayWeb(SellWay sellWayWeb) {
		this.sellWayWeb = sellWayWeb;
	}

	// fanxin 2011-08-29
	private List<SellWay> DisproList;

	public List<SellWay> getDisproList() {
		return DisproList;
	}

	public void setDisproList(List<SellWay> disproList) {
		DisproList = disproList;
	}

	private QuerySellWayCondition querySellWayCondition;
	public QuerySellWayCondition getQuerySellWayCondition() {
		if (querySellWayCondition ==null){
			querySellWayCondition = new QuerySellWayCondition();
		}
		return querySellWayCondition;
	}

	public void setQuerySellWayCondition(QuerySellWayCondition querySellWayCondition) {
		this.querySellWayCondition = querySellWayCondition;
	}

	//fanxin 注册成功--推荐
	public String getSellWayDisproList(){
		List<SellWay> sellWayList = new ArrayList<SellWay>();
		try{
			QuerySellWayCondition querySellWayCondition = getQuerySellWayCondition();
			int subjectId = sellWay.getSubjectId(); 
			String disPosition = sellWay.getDisPosition();
			
			querySellWayCondition.setSubjectId(subjectId);
			//加上 disPosition 属性
			querySellWayCondition.setDisPosition(disPosition);
			
			sellWayList = sellWayService.getSellWayDisproList(querySellWayCondition);
			
			if(sellWayList!=null && sellWayList.size()>0){
				
				setResult( new Result<List> (false,"success",null,sellWayList));
			}else{
				setResult( new Result<String>(false,"failure",null,null));
			}
		}catch(Exception e){
			logger.error("SellWayWebAction.getSellWayDisproList", e);
			setResult( new Result<String>(false,"failure",null,null));
		}
		return "json";
	}
	
	
	/*
	 * fanxin 2011-09-01
     * 获得相关课程下 "推荐" 售卖方式集合
	 * */
	public String getTuiJianSellWay(){
		List<SellWay> tuiJianSellWayList = new ArrayList<SellWay>();
		try{
			QuerySellWayCondition querySellWayCondition = getQuerySellWayCondition();
			int sellWayId = sellWay.getSellId();
			SellWay tempsellWay = sellWayService.getSellWayById(sellWayId);
			int subjectId = tempsellWay.getSubjectId();
			querySellWayCondition.setSubjectId(subjectId);
			
			String disPosition = sellWay.getDisPosition();
			//加上 disPosition 属性
			querySellWayCondition.setDisPosition(disPosition);
			
			tuiJianSellWayList = sellWayService.getSellWayDisproList(querySellWayCondition);
			
			//tuiJianSellWayList = sellWayService.getTuiJianSellWay(querySellWayCondition);
			
			if(tuiJianSellWayList!=null && tuiJianSellWayList.size()>0){
				
				setResult( new Result<List> (false,"success",null,tuiJianSellWayList));
			}else{
				setResult( new Result<String>(false,"failure",null,null));
			}
		}catch(Exception e){
			logger.error("SellWayWebAction.getTuiJianSellWay", e);
			setResult( new Result<String>(false,"failure",null,null));
		}
		return "json";
	}
	
	public String chooseSellWay() {

		int userId = getLoginUserId();
		if (userId != 0) {
			try {
				QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
				queryCourseCondition.setCusId(this.getLoginUserId());
				//查询正在销售中的班型
				sellWayList = courseService
						.getUserCenterSellWayListByCusId(queryCourseCondition);
				UserCenterSubjectCourseDTO sellwayCourseTemp = null;// 存放到最后存储的包的list
				QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
				queryCusCouKpointCondition.setCusId(userId);
				List<UserCenterCourseDTO> templist = new ArrayList<UserCenterCourseDTO>();
				if (null != sellWayList && sellWayList.size() > 0) {
					for (int i = 0; i < sellWayList.size(); i++) {
						UserCenterSubjectCourseDTO subjectCourse = new UserCenterSubjectCourseDTO();
						SellWay sellWay = sellWayList.get(i);
						templist =null;// cusCouKpointService.getCouserListBySellId(sellWay.getSellId()+"");
						if(sellWay != null && templist != null && templist.size() > 0){
							//组合包与课程
							subjectCourse.setCourseList(templist);
							subjectCourse.setSellWay(sellWay);
						}
						subjectCourse.setSellWay(sellWay);
						
						//sellWayCourseList.add(subjectCourse);
					}
				} 
			} catch (Exception e) {
				logger.error("个人中心跳转错误！", e);
			}
		}
	
		return "chooseSellWay";
	}
}
