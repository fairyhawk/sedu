package com.shangde.edu.iphone.dto;

import java.io.Serializable;
import java.sql.Date;

public class IphoneModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4624657017484684031L;
	
	private int courseNum;
	
	private String teachers;
	
	private String imageURL;
	
	private String videoName;
	
	private int playTimes;
	
	private String radioURL;
	
	private int videoId;
	
	private String videoURL;
	
	private Date updateDate;
	
	private Date playedDate;
	
	private String subjectName;
	
	private String subjectUrl;
	
	private int subjectId;
	
	private int teacherId;
	
	private int leaf;
	
	

	public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectUrl() {
		return subjectUrl;
	}

	public void setSubjectUrl(String subjectUrl) {
		this.subjectUrl = subjectUrl;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public int getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}

	public String getRadioURL() {
		return radioURL;
	}

	public void setRadioURL(String radioURL) {
		this.radioURL = radioURL;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getPlayedDate() {
		return playedDate;
	}

	public void setPlayedDate(Date playedDate) {
		this.playedDate = playedDate;
	}

	
	

}
