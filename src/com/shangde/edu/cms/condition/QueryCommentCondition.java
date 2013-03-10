package com.shangde.edu.cms.condition;

import com.shangde.common.vo.PageResult;

public class QueryCommentCondition extends PageResult{
    private int cmtId;
    private int sourceId;
    private int cfId;
    private int checkState = -1;
    private String cmtInfo;
    private int isTop;
    private int subjectId;
    private int isfreeze;
    private int isLimit;
    private int cusId;
    private int userId;
    
    
    
        
    public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getCfId() {
		return cfId;
	}

	public void setCfId(int cfId) {
		this.cfId = cfId;
	}

	public int getCmtId(){
        return cmtId;
    }

    public void setCmtId(int cmtId){
        this.cmtId = cmtId;
    }

	public String getCmtInfo() {
		return cmtInfo;
	}

	public void setCmtInfo(String cmtInfo) {
		this.cmtInfo = cmtInfo;
	}

	public int getCheckState() {
		return checkState;
	}

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getIsfreeze() {
		return isfreeze;
	}

	public void setIsfreeze(int isfreeze) {
		this.isfreeze = isfreeze;
	}

	public int getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(int isLimit) {
		this.isLimit = isLimit;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}