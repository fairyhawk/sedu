package com.shangde.edu.cou.condition;

public class QueryCourseBeforeTodayCondition {
	
	private int cus_id;
	private String before_create_time;
	private String today_time;
	
	public String getBefore_create_time() {
		return before_create_time;
	}
	public void setBefore_create_time(String before_create_time) {
		this.before_create_time = before_create_time;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public String getToday_time() {
		return today_time;
	}
	public void setToday_time(String today_time) {
		this.today_time = today_time;
	}
	
   
}