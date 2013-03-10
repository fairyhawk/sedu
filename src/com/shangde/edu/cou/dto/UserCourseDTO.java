package com.shangde.edu.cou.dto;

import java.io.Serializable;
import java.util.Map;

public class UserCourseDTO<E> implements Serializable{
	private String type;
	private Map<String,E> treeArray;
	private Map<String,E> courseList;
	/**
	 * 是否完成状态
	 */
	private int isCompleted;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, E> getTreeArray() {
		return treeArray;
	}
	public void setTreeArray(Map<String, E> treeArray) {
		this.treeArray = treeArray;
	}
	public int getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(int isCompleted) {
		this.isCompleted = isCompleted;
	}
	public Map<String, E> getCourseList() {
		return courseList;
	}
	public void setCourseList(Map<String, E> courseList) {
		this.courseList = courseList;
	}
}
