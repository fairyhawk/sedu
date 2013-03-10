package com.shangde.edu.dis.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

/**
 * 话题表查询类
 * 
 * @author Basil
 * 
 */
public class QueryTopicCondition extends PageQuery implements
		java.io.Serializable {
	/**
	 * 主键id
	 */
	private int id;
	/**
	 * 100 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 创建人
	 */
	private int cusId;
	/**
	 * 所属组
	 */
	private int disId;
	/**
	 * 所属区域
	 */
	private int disAreaId;
	/**
	 * 是否投票
	 */
	private int isVote;
	/**
	 * 投票ID
	 */
	private int voteId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后回复时间
	 */
	private Date replyTime;
	/**
	 * 点击次数
	 */
	private int clickCounts;
	/**
	 * 回复次数
	 */
	private int replyCounts;
	/**
	 * 推荐积分
	 */
	private int recNum;
	/**
	 * 默认值0 推荐次数
	 */
	private int recCount;
	/**
	 * 默认值0 是否允许回复
	 */
	private int canReply;
	/**
	 * 默认值0 是否置顶
	 */
	private int isTop;

	/**
	 * 搜索关键词
	 */
	private String keyWorld;
	/**
	 * 检索条件
	 */
	private String searchCriteria;
	/**
	 * 项目ID，科目id
	 */
	private int subjectId = -1;
	/**
	 * 帖子类型
	 */
	private int type = -1;
	/**
	 * 帖子状态
	 */
	private int status = -1;
	/**
	 * 发布时间，起
	 */
	private String createTimeStart;
	/**
	 * 发布时间，结
	 */
	private String createTimeEnd;
	/**
	 * 修改时间，起
	 */
	private String modifiedStart;
	/**
	 * 修改时间，结
	 */
	private String modifiedEnd;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the cusId
	 */
	public int getCusId() {
		return cusId;
	}

	/**
	 * @param cusId
	 *            the cusId to set
	 */
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	/**
	 * @return the disId
	 */
	public int getDisId() {
		return disId;
	}

	/**
	 * @param disId
	 *            the disId to set
	 */
	public void setDisId(int disId) {
		this.disId = disId;
	}

	/**
	 * @return the disAreaId
	 */
	public int getDisAreaId() {
		return disAreaId;
	}

	/**
	 * @param disAreaId
	 *            the disAreaId to set
	 */
	public void setDisAreaId(int disAreaId) {
		this.disAreaId = disAreaId;
	}

	/**
	 * @return the isVote
	 */
	public int getIsVote() {
		return isVote;
	}

	/**
	 * @param isVote
	 *            the isVote to set
	 */
	public void setIsVote(int isVote) {
		this.isVote = isVote;
	}

	/**
	 * @return the voteId
	 */
	public int getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId
	 *            the voteId to set
	 */
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the replyTime
	 */
	public Date getReplyTime() {
		return replyTime;
	}

	/**
	 * @param replyTime
	 *            the replyTime to set
	 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * @return the clickCounts
	 */
	public int getClickCounts() {
		return clickCounts;
	}

	/**
	 * @param clickCounts
	 *            the clickCounts to set
	 */
	public void setClickCounts(int clickCounts) {
		this.clickCounts = clickCounts;
	}

	/**
	 * @return the replyCounts
	 */
	public int getReplyCounts() {
		return replyCounts;
	}

	/**
	 * @param replyCounts
	 *            the replyCounts to set
	 */
	public void setReplyCounts(int replyCounts) {
		this.replyCounts = replyCounts;
	}

	/**
	 * @return the recNum
	 */
	public int getRecNum() {
		return recNum;
	}

	/**
	 * @param recNum
	 *            the recNum to set
	 */
	public void setRecNum(int recNum) {
		this.recNum = recNum;
	}

	/**
	 * @return the recCount
	 */
	public int getRecCount() {
		return recCount;
	}

	/**
	 * @param recCount
	 *            the recCount to set
	 */
	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	/**
	 * @return the canReply
	 */
	public int getCanReply() {
		return canReply;
	}

	/**
	 * @param canReply
	 *            the canReply to set
	 */
	public void setCanReply(int canReply) {
		this.canReply = canReply;
	}

	/**
	 * @return the isTop
	 */
	public int getIsTop() {
		return isTop;
	}

	/**
	 * @param isTop
	 *            the isTop to set
	 */
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	/**
	 * @return the keyWorld
	 */
	public String getKeyWorld() {
		return keyWorld;
	}

	/**
	 * @param keyWorld
	 *            the keyWorld to set
	 */
	public void setKeyWorld(String keyWorld) {
		this.keyWorld = keyWorld;
	}

	/**
	 * @return the searchCriteria
	 */
	public String getSearchCriteria() {
		return searchCriteria;
	}

	/**
	 * @param searchCriteria
	 *            the searchCriteria to set
	 */
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * @return the subjectId
	 */
	public int getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the createTimeStart
	 */
	public String getCreateTimeStart() {
		return createTimeStart;
	}

	/**
	 * @param createTimeStart
	 *            the createTimeStart to set
	 */
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	/**
	 * @return the createTimeEnd
	 */
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	/**
	 * @param createTimeEnd
	 *            the createTimeEnd to set
	 */
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/**
	 * @return the modifiedStart
	 */
	public String getModifiedStart() {
		return modifiedStart;
	}

	/**
	 * @param modifiedStart
	 *            the modifiedStart to set
	 */
	public void setModifiedStart(String modifiedStart) {
		this.modifiedStart = modifiedStart;
	}

	/**
	 * @return the modifiedEnd
	 */
	public String getModifiedEnd() {
		return modifiedEnd;
	}

	/**
	 * @param modifiedEnd
	 *            the modifiedEnd to set
	 */
	public void setModifiedEnd(String modifiedEnd) {
		this.modifiedEnd = modifiedEnd;
	}

}