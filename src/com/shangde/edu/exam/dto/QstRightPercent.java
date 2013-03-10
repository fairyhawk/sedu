package com.shangde.edu.exam.dto;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.domain.QstPic;

/**
 * 用户答题DTO记录
 * @author chenshuai
 *
 */
public class QstRightPercent implements Serializable{
	
	/**
	 * 所有做题的数量
	 */
	private int totalCount;
	/**
	 * 所有做错题的数量
	 */
	private int wrongTotalCount;
	/**
	 * 一周内做题的总数
	 */
	private int weekTotalCount;
	/**
	 * 一周内做题的正确数
	 */
	private int weekRightCount;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getWrongTotalCount() {
		return wrongTotalCount;
	}
	public void setWrongTotalCount(int wrongTotalCount) {
		this.wrongTotalCount = wrongTotalCount;
	}
	public int getWeekTotalCount() {
		return weekTotalCount;
	}
	public void setWeekTotalCount(int weekTotalCount) {
		this.weekTotalCount = weekTotalCount;
	}
	public int getWeekRightCount() {
		return weekRightCount;
	}
	public void setWeekRightCount(int weekRightCount) {
		this.weekRightCount = weekRightCount;
	}
}
