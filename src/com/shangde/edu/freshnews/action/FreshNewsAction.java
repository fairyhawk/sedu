package com.shangde.edu.freshnews.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.Result;
import com.shangde.common.util.json.DateJsonValueProcessor;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.freshnews.condition.QueryActionRecordCondition;
import com.shangde.edu.freshnews.domain.ActionRecord;
import com.shangde.edu.freshnews.service.IActionRecord;
import com.shangde.edu.freshnews.service.IActionReply;

@SuppressWarnings("all")
public class FreshNewsAction extends CommonAction {

	private static Logger logger = Logger.getLogger(FreshNewsAction.class);
	
	private IActionRecord actionRecordService;
	private ICourse courseService;
	private List<ActionRecord> actionRecords;
	private QueryActionRecordCondition queryActionRecordCondition;
	private List list;
	private String subjectId;
	public IActionRecord getActionRecordService() {
		return actionRecordService;
	}

	public void setActionRecordService(IActionRecord actionRecordService) {
		this.actionRecordService = actionRecordService;
	}

	/**
	 * to新鲜事 (默认加载20条)
	 * @return
	 */
	public String toFreshNews(){
		try{
		/**
		 * 获取新鲜事top10
		 */
		this.getQueryActionRecordCondition().setPageSize(20);
		this.getQueryActionRecordCondition().setCusId(this.getLoginUserId());
		QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
		this.getQueryActionRecordCondition().setSubjectId(Integer.valueOf(subjectId));
		list= courseService.getUserCenterSellWayListByCusId(queryCourseCondition);//判断是否购买过课程
		setPage(actionRecordService.getActionRecords(queryActionRecordCondition));
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return "toFreshNews";
	}

	/**
	 * ajax 动态加载新鲜事json 格式
	 */
	public String ajaxFreshNews(){
		
		try{
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			queryCourseCondition.setCusId(this.getLoginUserId());
			
			list= courseService.getUserCenterSellWayListByCusId(queryCourseCondition);//判断是否购买过课程
			this.getQueryActionRecordCondition().setPageSize(20);
			setPage(actionRecordService.getActionRecords(queryActionRecordCondition));
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
			JSONArray json = JSONArray.fromObject(getPage().getPageResult(),jsonConfig);
			this.setResult(new Result<Object>(true,json.toString(),String.valueOf(getPage().getTotalRecord()/20 + 1),null));
			}catch(Exception e){
				System.out.println(e.toString());
		}
		return "nextPage";
	}

	public List<ActionRecord> getActionRecords() {
		return actionRecords;
	}

	public void setActionRecords(List<ActionRecord> actionRecords) {
		this.actionRecords = actionRecords;
	}

	public QueryActionRecordCondition getQueryActionRecordCondition() {
		if (queryActionRecordCondition == null) {
			queryActionRecordCondition = new QueryActionRecordCondition();
		}
		return queryActionRecordCondition;
	}

	public void setQueryActionRecordCondition(
			QueryActionRecordCondition queryActionRecordCondition) {
		this.queryActionRecordCondition = queryActionRecordCondition;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}


}
