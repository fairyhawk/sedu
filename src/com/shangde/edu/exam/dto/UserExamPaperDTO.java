package com.shangde.edu.exam.dto;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.Reviews;
import com.shangde.edu.cus.domain.Customer;
/**
 * 用户试卷DTO类
 * @author chenshuai
 *
 */
public class UserExamPaperDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int epId;
	
	private float userScore;
	
	/**
	 * 用时
	 */
	private int testTime;
	
	private int cusId;
	
	private Customer customer;
	
	private int userEpId;
    
    private List<UserQst> qst;
    
    private Exampaper examPaper;
    
    private String zongping;
    
    private float accuracy;
    
    /**
     * 总评
     */
    private String reviewsIds;
    

	public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}

	public Exampaper getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(Exampaper examPaper) {
		this.examPaper = examPaper;
	}

	public int getUserEpId() {
		return userEpId;
	}

	public void setUserEpId(int userEpId) {
		this.userEpId = userEpId;
	}

	public List<UserQst> getQst() {
		return qst;
	}

	public void setQst(List<UserQst> qst) {
		this.qst = qst;
	}

	public float getUserScore() {
		return userScore;
	}

	public void setUserScore(float userScore) {
		this.userScore = userScore;
	}

	public int getTestTime() {
		return testTime;
	}

	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}

	public String getReviewsIds() {
		return reviewsIds;
	}

	public void setReviewsIds(String reviewsIds) {
		this.reviewsIds = reviewsIds;
	}

	public String getZongping() {
		return zongping;
	}

	public void setZongping(String zongping) {
		this.zongping = zongping;
	}

	public float getAccuracy() {
		accuracy = (float) (Math.round(accuracy * 1000)) / 100;
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
