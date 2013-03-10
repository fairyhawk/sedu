package com.shangde.edu.web.domain;

import java.io.Serializable;

/**
 * 主体功能:用户登录信息统计
 *
 * @author		HQL
 * @date		2012-6-27
 */
public class CusNotBuyInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int classmate;//同班同学(与该用户项目相同的总和)
	
	private int avgVideo;//平均观看视频数
	
	private int avgExercises;//平均做多少练习题
	
	private int avgQuestions;//平均问多少问题
	
	private int answerQuestions;//平均回答多少问题
	
	public int getClassmate() {
		return classmate;
	}

	public void setClassmate(int classmate) {
		this.classmate = classmate;
	}

	public int getAvgVideo() {
		return avgVideo;
	}

	public void setAvgVideo(int avgVideo) {
		this.avgVideo = avgVideo;
	}

	public int getAvgExercises() {
		return avgExercises;
	}

	public void setAvgExercises(int avgExercises) {
		this.avgExercises = avgExercises;
	}

	public int getAvgQuestions() {
		return avgQuestions;
	}

	public void setAvgQuestions(int avgQuestions) {
		this.avgQuestions = avgQuestions;
	}

	public int getAnswerQuestions() {
		return answerQuestions;
	}

	public void setAnswerQuestions(int answerQuestions) {
		this.answerQuestions = answerQuestions;
	}
}
