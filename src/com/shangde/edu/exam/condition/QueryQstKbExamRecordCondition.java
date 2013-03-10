package com.shangde.edu.exam.condition;

public class QueryQstKbExamRecordCondition {

   private int cusid;//用户id
   private int kid;//知识库id
   private int userepid;//考试试卷历史记录id
   private String isnull; //答案为空的时候
	public int getCusid() {
		return cusid;
	}
	public void setCusid(int cusid) {
		this.cusid = cusid;
	}
	public int getKid() {
		return kid;
	}
	public void setKid(int kid) {
		this.kid = kid;
	}
	public int getUserepid() {
		return userepid;
	}
	public void setUserepid(int userepid) {
		this.userepid = userepid;
	}
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	   
	
}