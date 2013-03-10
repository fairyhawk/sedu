package com.shangde.edu.freshnews.condition;

import com.shangde.common.vo.PageQuery;


public class QueryActionRecordCondition extends PageQuery{
        
	private int subjectId;
	private int cusId;
	
	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	
}