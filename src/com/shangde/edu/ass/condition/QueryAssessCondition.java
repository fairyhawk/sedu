package com.shangde.edu.ass.condition;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;

public class QueryAssessCondition extends PageQuery{
    private int assLeavel;//用户评分
    private int subId;//专业ID
    private int isGood;//查询已评价课程时，条件是否为好评
    private int cusId;//用户ID
    private int kpointId;//知识点ID
    private int sellwayId;//售卖方式ID
    private int courseId;//课程ID
    private int isPage;//标记某个查询是否需要分页
    private int status=-1;//评价状态。0:未审核；1：已审核；2：审核未通过；
    private String startTime;//用于查询时间范围，评价发表开始时间
    private String endTime;//用于查询时间范围，评价发表结束时间
    private String assTitle;//评价标题
    private String verifyStartTime;//用于查询时间范围，评价审核开始时间
    private String verifyEndTime;//用于查询时间范围，评价审核结束时间
    
    private List<String> watchKids=new ArrayList<String>();//已观看的知识点ID集合
    private List<Integer> assKids=new ArrayList<Integer>();//已评价的知识点ID集合
    

	public List<String> getWatchKids() {
		return watchKids;
	}

	public void setWatchKids(List<String> watchKids) {
		this.watchKids = watchKids;
	}

	public List<Integer> getAssKids() {
		return assKids;
	}

	public void setAssKids(List<Integer> assKids) {
		this.assKids = assKids;
	}

	public int getIsPage() {
		return isPage;
	}

	public void setIsPage(int isPage) {
		this.isPage = isPage;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getKpointId() {
		return kpointId;
	}

	public void setKpointId(int kpointId) {
		this.kpointId = kpointId;
	}

	public int getSellwayId() {
		return sellwayId;
	}

	public void setSellwayId(int sellwayId) {
		this.sellwayId = sellwayId;
	}

	public int getIsGood() {
		return isGood;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public void setIsGood(int isGood) {
		this.isGood = isGood;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public int getAssLeavel() {
		return assLeavel;
	}

	public void setAssLeavel(int assLeavel) {
		this.assLeavel = assLeavel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getAssTitle() {
		return assTitle;
	}

	public void setAssTitle(String assTitle) {
		this.assTitle = assTitle;
	}

	public String getVerifyStartTime() {
		return verifyStartTime;
	}

	public void setVerifyStartTime(String verifyStartTime) {
		this.verifyStartTime = verifyStartTime;
	}

	public String getVerifyEndTime() {
		return verifyEndTime;
	}

	public void setVerifyEndTime(String verifyEndTime) {
		this.verifyEndTime = verifyEndTime;
	}
}