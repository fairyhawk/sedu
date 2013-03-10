package com.shangde.edu.exam.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 用户考试分析DTO类
 * @author chenshuai
 *
 */
public class ExampaperQstDTO implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 试题id
	 */
	private int qstId;
	/**
	 * 试题内容
	 */
	private String qstContent;
	/**
	 * 试题分数
	 */
	private int score;
	/**
	 * 试题类型
	 */
	private int qstType;
	/**
	 * 选项序号
	 */
	private String optOrder;
	/**
	 * 选项内容
	 */
	private String optContent;
	/**
	 * 存放试题的选项信息
	 */
	private List<ExampaperQstDTO> qstOptionList;
	
	private int ziQstType;
	/**
	 * 正确率
	 */
	private float percent;
	
	/**
	 * 试题是否被收藏
	 */
	private String flag;
	
	/**
	 * 试题正确答案
	 */
	private String isAsr;
	
	/**
	 * 试题解析
	 */
	private String wrongJude;
	
	public String getIsAsr() {
		return isAsr;
	}
	public void setIsAsr(String isAsr) {
		this.isAsr = isAsr;
	}
	public String getWrongJude() {
		return wrongJude;
	}
	public void setWrongJude(String wrongJude) {
		this.wrongJude = wrongJude;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	public List<ExampaperQstDTO> getQstOptionList() {
		return qstOptionList;
	}
	public void setQstOptionList(List<ExampaperQstDTO> qstOptionList) {
		this.qstOptionList = qstOptionList;
	}
	public String getQstContent() {
		return qstContent;
	}
	public void setQstContent(String qstContent) {
		this.qstContent = qstContent;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getOptOrder() {
		return optOrder;
	}
	public void setOptOrder(String optOrder) {
		this.optOrder = optOrder;
	}
	public String getOptContent() {
		return optContent;
	}
	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}
	public int getQstId() {
		return qstId;
	}
	public void setQstId(int qstId) {
		this.qstId = qstId;
	}
	public int getQstType() {
		return qstType;
	}
	public void setQstType(int qstType) {
		this.qstType = qstType;
	}
	public int getZiQstType() {
		return ziQstType;
	}
	public void setZiQstType(int ziQstType) {
		this.ziQstType = ziQstType;
	}
	  
	  
}
