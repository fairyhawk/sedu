package com.shangde.edu.crm.condition;

public class QueryChanceCondition {
    private int id;
        
    /**
     * 机会创建开始时间
     */
    private String startTime;
    
    /**
     * 机会创建结束时间
     */
    private String endTime;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    
    private int drawStatus;

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

	public int getDrawStatus() {
		return drawStatus;
	}

	public void setDrawStatus(int drawStatus) {
		this.drawStatus = drawStatus;
	}
    
    
}