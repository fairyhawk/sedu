package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.List;

public class PublicResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> sellResult;
	
	private int sellSize;
	
	private List<String> courseResult;
	
	private int courseSize;

	public List<String> getSellResult() {
		return sellResult;
	}

	public void setSellResult(List<String> sellResult) {
		this.sellResult = sellResult;
	}

	public List<String> getCourseResult() {
		return courseResult;
	}

	public void setCourseResult(List<String> courseResult) {
		this.courseResult = courseResult;
	}

	public int getSellSize() {
		return sellSize;
	}

	public void setSellSize(int sellSize) {
		this.sellSize = sellSize;
	}

	public int getCourseSize() {
		return courseSize;
	}

	public void setCourseSize(int courseSize) {
		this.courseSize = courseSize;
	}
}
