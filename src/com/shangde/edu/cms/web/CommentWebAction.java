package com.shangde.edu.cms.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.action.RandomCodeAction;
import com.shangde.common.exception.CommException;
import com.shangde.common.util.KeyWordFilter;
import com.shangde.common.util.PreventInfusion;
import com.shangde.common.util.Result;
import com.shangde.common.util.json.JsonUtil;
import com.shangde.edu.cms.condition.QueryCommentCondition;
import com.shangde.edu.cms.domain.Comment;
import com.shangde.edu.cms.domain.Commentfrom;
import com.shangde.edu.cms.service.IComment;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.web.CustomerWebAction;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.service.ITaskCus;

/**  
 * 前台评论接口，支持静态html的ajax调用
 * @author zhouzhiqiang
 * @version 1.0
 */

public class CommentWebAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(CommentWebAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 评论服务对象
	 */
	private IComment commentService;
	
	private ICourse courseService;
	
	CustomerWebAction cwa=new CustomerWebAction();
	
	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;
	
	private boolean ishavebuy=false;
	
	private ITaskCus taskCusService;
	
	/**
	 * 评论实体
	 */
	private Comment comment = new Comment();
	
	/**
	 * 评论查询条件
	 */
	private QueryCommentCondition queryCommentCondition;
	
	/**
	 * 评论来源list
	 */
	private List<Commentfrom> commentfromList = new ArrayList<Commentfrom>();
	
	/**
	 * 评论list
	 */
	private List<Comment> commentList=new ArrayList<Comment>();
	
	private String sourceURL;
	
	private String sourceName;
	
	private String isLogin = "false";
	
	private String confirmCode;
	
	private String returnMessage;
	
	private boolean needConfirmCode = true;
	
	/**
	 * 反馈类型
	 */
	public Integer feedBackType;
	
	private ICashRecord cashRecordService;
	
	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}


	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}


	/**
	 * 保存评论
	 * @return String
	 * @thorows Exception
	 */
	public String saveComment(){
		try {
			String sessionConfirmCode = servletRequest.getSession().getAttribute(RandomCodeAction.RAND_CODE_NAME).toString();
			if(confirmCode == null  ||  !confirmCode.equals(sessionConfirmCode)) {
				returnMessage = "confirmCommentFail";
				return showCommentList();
			}
			if(checkSqlInj(comment)) {
				returnMessage = "cmtDangerWord";
				return showCommentList();
			}
			
			comment.setCmtInfo(PreventInfusion.xssEncode(comment.getCmtInfo()));
			comment.setVisitorName(PreventInfusion.xssEncode(comment.getVisitorName()));
			
			int userId = getLoginUserId();
			if(userId == 0) {
				comment.setCheckmanType(0);
			} else {
				Customer customer = customerService.getCustomerById(userId);
				comment.setVisitorName(customer.getEmail());
				comment.setCheckmanId(userId);
				comment.setCheckmanType(1);
			}
			comment.setCmtInfo(comment.getCmtInfo().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			comment.setGrade(3);
			comment.setIs_freeze(0);
			comment.setIs_top(0);
			comment.setAuthor("");
			comment.setCheckState(0);
			comment.setCmtInfo(KeyWordFilter.doFilter(comment.getCmtInfo()));
			commentService.addComment(comment);
			
			//第一次评论课程任务
			if(comment.getCfId() == 1){
				QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
				queryTaskCusCondition.setCusId(userId);
				queryTaskCusCondition.setKeyWord(Task.TASK_KEY_EVALUATECOURSE);
				
				TaskCus tc = taskCusService.getTaskCusByKey(queryTaskCusCondition);
				
				if(tc != null && tc.getIsComplete() == 0){//若果未完成则设置完成
					tc.setIsComplete(1);
					taskCusService.updateTaskCus(tc);
				}
			}
			//第一次评论课程任务结束
		} catch(Exception e) {
			logger.error("CommentWebAction.saveComment", e);
			return ERROR;
		}
		return "reshowCommentList";
	}

	
	/**
	 * 显示意见建议列表
	 * @return
	 */
	public String showAdviceList() {
		try{
			if(getLoginUserId() != 0) {
				isLogin = "true";
			}
			setPage(commentService.getAdviceList(getQueryCommentCondition()));
			List<Comment> list = getPage().getPageResult();
			for(int i=0; i<list.size(); i++) {
				Comment cmt = commentService.getReplyById(list.get(i).getCmtId());
				list.get(i).setSourceObject(cmt);
			}
			setPageUrlParms();
		}catch(Exception e){
			logger.error("CommentWebAction.showAdviceList", e);
			return ERROR;
		}
		return "answer";
	}
	
	/**
	 * 显示意见建议列表
	 * @return
	 */
	public String getAdviceList4ajax() {
		try{
			setPage(commentService.getAdviceList(getQueryCommentCondition()));
			this.setResult(new Result(false, JsonUtil.getJsonString4JavaCollection(getPage().getPageResult()), "", null));
		}catch(Exception e){
			logger.error("CommentWebAction.getAdviceList4ajax", e);
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 显示意见建议列表
	 * @return
	 */
	public String getLastAdviceByCus() {
		try{
			comment = commentService.getLastAdviceByCus(getLoginUserId());
			if(comment != null) {
				setResult(new Result(false, "success", "", comment.getCmtInfo()));
			} else {
				setResult(new Result(false, "failure", "", null));
			}
		}catch(Exception e){
			logger.error("CommentWebAction.getLastAdviceByCus", e);
			setResult(new Result(false, "failure", "", null));
		}
		return "json";
	}
	
	/**
	 * 添加意见建议列表
	 * 意见建议评论来源ID为5
	 * 资源ID为1
	 * @return
	 */
	public String saveAdvice() {
		try{
			String sessionConfirmCode = servletRequest.getSession().getAttribute(RandomCodeAction.RAND_CODE_NAME).toString();
			if(confirmCode == null  ||  !confirmCode.equals(sessionConfirmCode)) {
				returnMessage = "confirmAdviceFail";
				return showAdviceList();
			}
			int userId = getLoginUserId();
			if(userId != 0) {
				Customer customer = customerService.getCustomerById(userId);
				comment.setVisitorName(customer.getEmail());
				comment.setCheckmanId(userId);
				comment.setCheckmanType(1);
			}
			comment.setCfId(5);
			comment.setSourceId(1);
			comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
			comment.setGrade(3);
			comment.setIs_freeze(0);
			comment.setIs_top(0);
			comment.setAuthor("");
			comment.setCreateTime(new Date());
			comment.setUpdateTime(new Date());
			comment.setTopTime(new Date());
			commentService.addComment(comment);
		}catch(Exception e){
			logger.error("CommentWebAction.saveAdvice", e);
			return ERROR;
		}
		return "reanswer";
	}
	
	/**
	 * 保存评论
	 * @return String
	 * @thorows Exception
	 */
	public String saveComment4ajax(){
		try {
			String sessionConfirmCode = servletRequest.getSession().getAttribute(RandomCodeAction.RAND_CODE_NAME).toString();
			if(confirmCode == null  ||  !confirmCode.equals(sessionConfirmCode)) {
				setResult(new Result(false, "confirmCommentFail", "", null));
				return "json";
			}
			if(checkSqlInj(comment)) {
				setResult(new Result(false, "CommentDangerWord", "", null));
				return "json";
			}
			
			comment.setCmtInfo(PreventInfusion.xssEncode(comment.getCmtInfo()));
			comment.setVisitorName(PreventInfusion.xssEncode(comment.getVisitorName()));
			
			int userId = getLoginUserId();
			if(userId == 0) {
				comment.setCheckmanType(0);
			} else {
				Customer customer = customerService.getCustomerById(userId);
				comment.setVisitorName(customer.getEmail());
				comment.setCheckmanId(userId);
				comment.setCheckmanType(1);
			}
			comment.setCmtInfo(comment.getCmtInfo().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			comment.setGrade(3);
			comment.setCheckState(0);
			comment.setCmtInfo(KeyWordFilter.doFilter(comment.getCmtInfo()));
			comment.setIs_freeze(0);
			comment.setIs_top(0);
			comment.setAuthor("");
			comment.setCreateTime(new Date());
			comment.setUpdateTime(new Date());
			comment.setTopTime(new Date());
			commentService.addComment(comment);
			
			//第一次评论课程任务
			if(comment.getCfId() == 1){
				QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
				queryTaskCusCondition.setCusId(userId);
				queryTaskCusCondition.setKeyWord(Task.TASK_KEY_EVALUATECOURSE);
				
				TaskCus tc = taskCusService.getTaskCusByKey(queryTaskCusCondition);
				
				if(tc != null && tc.getIsComplete() == 0){//若果未完成则设置完成
					tc.setIsComplete(1);
					taskCusService.updateTaskCus(tc);
				}
			}
			//第一次评论课程任务结束
			
			setResult(new Result(false, "success", "", null));
		} catch(Exception e) {
			logger.error("CommentWebAction.saveComment4ajax", e);
		}
		return "json";
	}
	
	/**
	 * 添加意见建议列表
	 * 意见建议评论来源ID为5
	 * 资源ID为1
	 * @return
	 */
	public String saveAdvice4ajax() {
		try{
			Object sessionConfirmCode = servletRequest.getSession().getAttribute(RandomCodeAction.RAND_CODE_NAME);
			if(needConfirmCode && sessionConfirmCode != null && (sessionConfirmCode != null && (confirmCode == null  ||  !confirmCode.equals(sessionConfirmCode.toString())))) {
				setResult(new Result(false, "confirmAdviceFail", "", null));
				return "json";
			}
			if(checkSqlInj(comment)) {
				setResult(new Result(false, "adviceDangerWord", "", null));
				return "json";
			}
			
			/** 是否购买过二建或英语四六级 买过则isLimit为1 否则为0  add by 王超 */
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			queryCourseCondition.setCusId(this.getLoginUserId());
			List<SellWay> butSellWayList=new ArrayList<SellWay>();// 购买的包
			//取得购买成功的包
			butSellWayList= courseService.getUserCenterSellWayListByCusId(queryCourseCondition);
			int subjectid=butSellWayList.get(0).getSubjectId();
			comment.setIs_freeze(0);
			/**  找出价格最贵的商品 王超 */
			float price=0;
			String sellName="";
		    for(SellWay sellway:butSellWayList){
		    	/** 是否购买过二建或英语四六级 */
		    	   if(sellway.getSubjectId()==16||sellway.getSubjectId()==39){
		    		   comment.setIs_freeze(1);
		    	   }
		    	   /**  找出价格最贵的商品 */
		    	   if(price<sellway.getSellPrice()){
		    		   price=sellway.getSellPrice();
		    		   sellName=sellway.getSellName();
		    	   }
		       }
		    /**  找出价格最贵的商品 王超 */
		    
		    
		    /** 是否购买过二建或英语四六级 买过则isLimit为1 否则为0  add by 王超  */

			
			comment.setCmtInfo(PreventInfusion.xssEncode(comment.getCmtInfo()));
			comment.setVisitorName(PreventInfusion.xssEncode(comment.getVisitorName()));
			int userId = getLoginUserId();
			if(userId != 0) {
				Customer customer = customerService.getCustomerById(userId);
				if(customer.getCusName()==null||"".equals(customer.getCusName())){
					
					comment.setVisitorName(customer.getEmail());
				}else{
					comment.setVisitorName(customer.getCusName());
				}
				comment.setCheckmanId(userId);
				comment.setCheckmanType(1);
			}
			if(price!=0)
			comment.setSellName(sellName);
			//判断用户的反馈方式：5意见建议 7咨询 8投诉 9表扬
			if(null!=feedBackType){
				comment.setCfId(feedBackType);
			}else{
				comment.setCfId(5);
			}
			comment.setSourceId(1);
			comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
			comment.setGrade(3);
			comment.setCreateTime(new Date());
			comment.setUpdateTime(new Date());
			comment.setTopTime(new Date());
			/** 通过cusId获取最新购买的流水以获取专业   王超*/
			 CashRecord cr=this.cashRecordService.getCurrentCashRecordByCusId(userId);
			comment.setSubjectId(cr.getCRSubjectId());
			comment.setIs_top(0);
			comment.setAuthor("");
			commentService.addComment(comment);
			setResult(new Result(false, "success", "", comment));
		}catch(Exception e){
			logger.error("CommentWebAction.saveAdvice4ajax", e);
		}
		return "json";
	}
	
	public String moreAdvice(){
		QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
		queryCourseCondition.setCusId(this.getLoginUserId());
		 List<SellWay> butSellWayList=new ArrayList<SellWay>();// 购买的包
		//取得购买成功的包
		butSellWayList= courseService.getUserCenterSellWayListByCusId(queryCourseCondition);
		 /** 是否购买过二建或英语四六级 买过则isLimit为1 否则为0  add by 王超 */
	   /* for(SellWay sellway:butSellWayList){
	    	   if(sellway.getSubjectId()==16||sellway.getSubjectId()==39){
	    		   this.getQueryCommentCondition().setIsLimit(1);   
	    		   this.getQueryCommentCondition().setCusId(this.getLoginUserId());
	    		   break;
	    	   }
	       }*/
	    /** 是否购买过二建或英语四六级 买过则isLimit为1 否则为0  add by 王超  */
		if(butSellWayList==null||butSellWayList.size()==0){
			this.getQueryCommentCondition().setIsTop(1);
		} else ishavebuy=true;
		this.getQueryCommentCondition().setPageSize(10);
		if(null!=feedBackType){
			queryCommentCondition.setCfId(feedBackType);
		}else{
			queryCommentCondition.setCfId(5);
		}
		queryCommentCondition.setUserId(this.getLoginUserId());
		if(queryCommentCondition.getUserId()==queryCommentCondition.getCusId()||queryCommentCondition.getCusId()==0)
			queryCommentCondition.setIsLimit(1);
		this.setPage(commentService.getCommentPage(queryCommentCondition));
		this.getPage().setPageSize(10);
		setPageUrlParms();
		return "moreAdvice";
	}
	
	public String adviceList(){
		try {
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			queryCourseCondition.setCusId(this.getLoginUserId());
			 List<SellWay> butSellWayList=new ArrayList<SellWay>();// 购买的包
			//取得购买成功的包
			butSellWayList= courseService.getUserCenterSellWayListByCusId(queryCourseCondition);
			if(butSellWayList==null||butSellWayList.size()==0){
				this.getQueryCommentCondition().setIsTop(1);
			} else ishavebuy=true;
			this.getQueryCommentCondition().setPageSize(10);
			queryCommentCondition.setCfId(5);
			queryCommentCondition.setUserId(this.getLoginUserId());
			if(queryCommentCondition.getUserId()==queryCommentCondition.getCusId()||queryCommentCondition.getCusId()==0)
				queryCommentCondition.setIsLimit(1);
			List <Comment> commentList=this.commentService.getCommentListNew(queryCommentCondition);
			if(commentList!=null&&commentList.size()>0){
			this.setResult(new Result(true,null,null,commentList));
			}
			else this.setResult(new Result(false,null,null,null));
		} catch (Exception e) {
			this.setResult(new Result(false,null,null,null));
			e.printStackTrace();
		}
		
		return "json";
	}
	
	/**
	 * 添加意见建议列表
	 * 意见建议评论来源ID为5
	 * 资源ID为1
	 * @return
	 */
	public String replyAdvice() {
		try{
			Object sessionConfirmCode = servletRequest.getSession().getAttribute(RandomCodeAction.RAND_CODE_NAME);
			if(needConfirmCode && sessionConfirmCode != null && (sessionConfirmCode != null && (confirmCode == null  ||  !confirmCode.equals(sessionConfirmCode.toString())))) {
				setResult(new Result(false, "confirmAdviceFail", "", null));
				return "json";
			}
			if(checkSqlInj(comment)) {
				setResult(new Result(false, "adviceDangerWord", "", null));
				return "json";
			}
			comment.setCmtInfo(PreventInfusion.xssEncode(comment.getCmtInfo()));
			comment.setVisitorName(PreventInfusion.xssEncode(comment.getVisitorName()));
			int userId = getLoginUserId();
			if(userId != 0) {
				Customer customer = customerService.getCustomerById(userId);
				if(customer.getCusName()==null||"".equals(customer.getCusName())){
					
					comment.setVisitorName(customer.getEmail());
				}else{
					comment.setVisitorName(customer.getCusName());
				}
				comment.setCheckmanId(userId);
				comment.setCheckmanType(1);
			}
			comment.setCfId(5);
			comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
			comment.setGrade(3);
			comment.setIs_freeze(0);
			comment.setIs_top(0);
			comment.setAuthor("");
			comment.setCreateTime(new Date());
			comment.setUpdateTime(new Date());
			comment.setTopTime(new Date());
			commentService.addComment(comment);
			setResult(new Result(true, "success", "", comment));
		}catch(Exception e){
			logger.error("CommentWebAction.saveAdvice4ajax", e);
		}
		return "json";
	}
	
	private boolean checkSqlInj(Comment cmt) {
		if(PreventInfusion.sql_inj(cmt.getCmtInfo()) || 
				PreventInfusion.sql_inj(cmt.getVisitorName())) {
			return true;
		}
		return false;
	}

	/**
	 * 根据资源id分页列表
	 * @return String
	 * @thorows Exception
	 */
	@SuppressWarnings("unchecked")
	public String getCommentListBySource()throws CommException, IOException{
		try {
			getQueryCommentCondition().setPageSize(7);
			setPage(commentService.getCommentListBySource(getQueryCommentCondition()));
			List<Integer> countList = commentService.getGradeCountBySource(getQueryCommentCondition());
			String newComment = commentService.getNewCommentBySource(getQueryCommentCondition());
			
			if(getPage() != null) {
				commentList = getPage().getPageResult();
			}
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = new HashMap<String, String>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			getPage().setPageSize(7);
			map.put("cfId", "" + queryCommentCondition.getCfId());
			map.put("sourceId", "" + queryCommentCondition.getSourceId());
			
			if(getPage() != null) {
				map.put("totalRecord", "" + getPage().getTotalRecord());
				map.put("totalPage", "" + getPage().getTotalPage());
				map.put("currentPage", "" + getPage().getCurrentPage());
				map.put("pageSize", "" + getPage().getPageSize());
			}
			if(countList != null && countList.size() > 2) {
				map.put("niceGrade", "" + countList.get(2));
				map.put("normalGrade", "" + countList.get(1));
				map.put("badGrade", "" + countList.get(0));
				map.put("newComment", newComment);
			}
			list.add(map);
			
			if(commentList != null) {
				for(int i=0; i < commentList.size(); i++) {
					map = new HashMap<String, String>();
					Comment comment = commentList.get(i);
					map.put("cmtId", "" + comment.getCmtId());
					map.put("cmtParentId", "" + comment.getCmtParentId());
					map.put("cmtInfo", comment.getCmtInfo());
					map.put("cfId", "" + comment.getCfId());
					map.put("checkState", "" + comment.getCheckState());
					map.put("createTime", format.format(comment.getCreateTime()));
					map.put("updateTime", format.format(comment.getUpdateTime()));
					map.put("grade", "" + comment.getGrade());
					if(comment.getCheckmanType() == 1) {
						Customer customer = customerService.getCustomerById(comment.getCheckmanId());
						if(customer != null) {
							map.put("checkmanName", customer.getCusName()==null?customer.getEmail():customer.getCusName());
							map.put("photo", customer.getPhoto()==null||customer.getPhoto()==""?"default.jpg":customer.getPhoto());
						} else {
							map.put("checkmanName", "嗨学网会员");
							map.put("photo", "default.jpg");
						}
					} else {
						map.put("checkmanName", comment.getVisitorName());
						map.put("photo", "default.jpg");
					}
					map.put("sourceId", "" + comment.getSourceId());
					list.add(map);
				}
			}
    		setResult(new Result<List<Map<String, String>>>(true, "返回成功!", null, list));
		}catch(Exception e) {
			logger.error("CommentWebAction.getCommentListBySource", e);
		}
		return "json";
	}

	/**
	 * 根据资源id分页列表
	 * @return String
	 * @thorows Exception
	 */
	@SuppressWarnings("unchecked")
	public String showCommentList()throws CommException, IOException{
		try {
			if(getLoginUserId() != 0) {
				isLogin = "true";
			}
			getQueryCommentCondition().setCfId(comment.getCfId());
			getQueryCommentCondition().setSourceId(comment.getSourceId());
			
			getQueryCommentCondition().setPageSize(8);
			setPage(commentService.getCommentListBySource(getQueryCommentCondition()));
			setPageUrlParms();
			getPage().setPageSize(8);
			
			if(getQueryCommentCondition().getCfId() == 1) {
				Course course = courseService.getCourseById(getQueryCommentCondition().getSourceId());
				sourceName = course.getTitle(); 
			}
		}catch(Exception e) {
			logger.error("CommentWebAction.showCommentList", e);
			return ERROR;
		}
		return "showCommentList";
	}
	
	public String getCmtCount() {
		try {
    		setResult(new Result<Integer>(true, "!", null, commentService.getCmtCount(getQueryCommentCondition())));
		}catch(Exception e) {
			logger.error("CommentWebAction.getCmtCount", e);
			return ERROR;
		}
		return "json";
	}
	
	public void sendMessage(String message) throws IOException {
		getServletResponse().setCharacterEncoding("utf-8");
		getServletResponse().getWriter().write(message);
	}
	
	
	
	public boolean isIshavebuy() {
		return ishavebuy;
	}


	public void setIshavebuy(boolean ishavebuy) {
		this.ishavebuy = ishavebuy;
	}


	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public QueryCommentCondition getQueryCommentCondition() {
		if(queryCommentCondition == null) {
			queryCommentCondition = new QueryCommentCondition();
		}
		return queryCommentCondition;
	}
	public void setQueryCommentCondition(QueryCommentCondition queryCommentCondition) {
		this.queryCommentCondition = queryCommentCondition;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public void setCommentService(IComment commentService) {
		this.commentService = commentService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public String getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public List<Commentfrom> getCommentfromList() {
		return commentfromList;
	}

	public void setCommentfromList(List<Commentfrom> commentfromList) {
		this.commentfromList = commentfromList;
	}

	public boolean isNeedConfirmCode() {
		return needConfirmCode;
	}

	public void setNeedConfirmCode(boolean needConfirmCode) {
		this.needConfirmCode = needConfirmCode;
	}

	public IComment getCommentService() {
		return commentService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}


	public Integer getFeedBackType() {
		return feedBackType;
	}


	public void setFeedBackType(Integer feedBackType) {
		this.feedBackType = feedBackType;
	}
}
