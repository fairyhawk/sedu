package com.shangde.edu.cms.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.StringUtil;
import com.shangde.common.util.messageRemind.MessageRemindBean;
import com.shangde.common.util.messageRemind.MessageRemindUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryCommentCondition;
import com.shangde.edu.cms.domain.Comment;
import com.shangde.edu.cms.domain.Commentfrom;
import com.shangde.edu.cms.service.IComment;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.CustomerImpl;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.msg.service.MessageImpl;
import com.shangde.edu.msg.service.UserMsgImpl;
import com.shangde.edu.sys.domain.User;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class CommentAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 评论服务对象
	 */
	private IComment commentService;
	
	/**
	 * 评论实体
	 */
	private Comment comment = new Comment();
	
	private Comment reComment=new Comment();
	
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

	/**
	 * id
	 */
	private String id;
	
	private IMessage messageService;// 消息服务

	private IUserMsg userMsgService;// 用户信息服务
	
	private ICustomer customerService;
	
	/**
	 * 删除评论
	 * @return String
	 * @thorows Exception
	 */
	public String deleteComment(){
		try {
			commentService.delCommentById(comment.getCmtId());
			//userMsgService.delUserMsgById(id);
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "research";
	}

	

	/**
	 * 审核评论
	 * @return String
	 * @thorows Exception
	 */
	public String auditComment(){
		try {
			//根据id获取要通过审核的评论
			comment = commentService.getCommentById(comment.getCmtId());
			if(comment != null) {
				//将审核状态置为通过
				comment.setCheckState(Comment.CMT_CHECK_STATE_PASS);
			}
			commentService.updateComment(comment);
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}
	
	/**
	 * 根据查询条件查询评论列表并分页
	 * @return String
	 * @thorows Exception
	 */
	public String getCommentListBySearch() {
		try {
			//获取评论来源列表
			commentfromList = commentService.getCommentFromList();
			//根据评论内容搜索评论
			String info = getQueryCommentCondition().getCmtInfo();
			if(info != null) {
				getQueryCommentCondition().setCmtInfo(info.trim());
			}
			setPage(commentService.getCommentListByCondition(getQueryCommentCondition()));
			setPageUrlParms();
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "search";
	}
	
	/**
	 * 显示建议列表
	 * @return
	 */
	public String showAdviceList() {
		try {
			//准备查询条件
			String info = getQueryCommentCondition().getCmtInfo();
			if(info != null) {
				getQueryCommentCondition().setCmtInfo(info.trim());
			}
			setPage(commentService.getAdviceListByCondition(getQueryCommentCondition()));
			setPageUrlParms();
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "showAdviceList";
	}
	
	/**
	 * 回复建议
	 * @return
	 */
	public String replyAdvice() {
		try {
			//回复建议的来源id是6
			comment.setCfId(6);
			//设置一些非空数据，无意义
			comment.setGrade(1);
			comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
			int reId=commentService.addComment(comment);
			
			//将被回复的建议的审核状态置为通过。
			comment = commentService.getCommentById(comment.getSourceId());
			comment.setCheckState(Comment.CMT_CHECK_STATE_PASS);
			commentService.updateComment(comment);
			String abs="";
			
			//给提意見者發送信息

			User sender = new User();			
			sender.setUserId(1);
			sender.setUserName("超级管理员");
			Customer cus=customerService.getCustomerById(comment.getCheckmanId());
			MessageRemindBean mrb=new MessageRemindBean();
			mrb.setUrl("管理员回复了您的意见[<a href=\"../cus/pblimit!getMyAdvice.action?comment.cmtId="
							+ comment.getCmtId()+ "&rid="+reId+"\">");
			mrb.setText(comment.getCmtInfo());
			mrb.setReceiver(cus);
			mrb.setRid(reId);
			mrb.setTid(comment.getCmtId());
			mrb.setSender(sender);
			mrb.setType(1);
			MessageRemindUtil.sendMessageRemind(mrb);
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}
	
	//管理员删除用户提出的建议
	public String deleteAdvice(){
		try {
			//先删除建议的回复，再删除建议
			commentService.delAdviceReply(comment.getCmtId());
			commentService.delCommentById(comment.getCmtId());
			//删除消息提醒
			MessageRemindUtil.delMessageRemindForTopic(1, comment.getCmtId());
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "reshowAdviceList";
	}
	/**
	 * 初始化回复意见页面
	 * @return
	 */
	public String initReplyAdvice() {
		comment = commentService.getCommentById(comment.getCmtId());
		return "initReplyAdvice";
	}
	
	/**
	 * 初始化修改回复页面
	 * @return
	 */
	//public String initReplyUpdate() {
	//	comment = commentService.getReplyById(comment.getCmtId());
	//	comment.setSourceObject(commentService.getCommentById(comment.getSourceId()));
	//	return "initReplyUpdate";
	//}
	/**
	 * 
	 */
	public String showReplyList(){
		comment=commentService.getCommentById(comment.getCmtId());
		this.getQueryCommentCondition().setCfId(6);
		this.getQueryCommentCondition().setSourceId(comment.getCmtId());
		this.getQueryCommentCondition().setPageSize(10);
		PageResult pr=commentService.getCommentListBySource(queryCommentCondition);
		List<Comment> recomments=pr.getPageResult();
		for(Comment recomment:recomments){
			recomment.setCmtInfo(StringUtil.filterHTML(recomment.getCmtInfo()));
		}
		setPage(pr);
		setPageUrlParms();
		if(getPage()!=null){
			   getPage().setPageSize(10);
		 }
		return "showReplyList";		
	}
	
	/**
	 * 修改回复
	 * @return
	 */
	public String replyUpdate() {
		//修改回复
		Comment rply = commentService.getCommentById(comment.getCmtId());
		rply.setCmtInfo(comment.getCmtInfo());
		commentService.updateComment(rply);
		return "changeSuccess";
	}
	//管理员删除自己的回复
	public String deleteReply() {
		servletRequest=ServletActionContext.getRequest();
		String t=servletRequest.getParameter("t");
		reComment = commentService.getCommentById(comment.getCmtId());		
		
		commentService.delCommentById(reComment.getCmtId());
		//删除提醒消息
		MessageRemindUtil.delMessageRemindForReply(1, reComment.getCmtId());
		
		this.getQueryCommentCondition().setCfId(6);
		this.getQueryCommentCondition().setSourceId(reComment.getSourceId());
		int count=commentService.getCmtCountBySource(this.getQueryCommentCondition());
		if(count<1){
			comment = commentService.getCommentById(reComment.getSourceId());
			comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
			commentService.updateComment(comment);
		}		
		if(t!=null&&t.equals("2")){
			return "reshowReplyList";
		}else{
			return "reshowAdviceList";
		}		
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCommentService(IComment commentService) {
		this.commentService = commentService;
	}

	public List<Commentfrom> getCommentfromList() {
		return commentfromList;
	}

	public void setCommentfromList(List<Commentfrom> commentfromList) {
		this.commentfromList = commentfromList;
	}

	public IComment getCommentService() {
		return commentService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public IMessage getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}

	public IUserMsg getUserMsgService() {
		return userMsgService;
	}

	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}

	public Comment getReComment() {
		return reComment;
	}

	public void setReComment(Comment reComment) {
		this.reComment = reComment;
	}
}
