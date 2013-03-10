package com.shangde.edu.cou.webdto;

import java.io.Serializable;
/**
 * <br>
 * <b>功能：课程DTO</b><br>
 * <b>作者：</b>李志强 Kobe.Lee<br>
 * <b>日期：</b> 2012.06.19 <br>
 * 
 * @return
 */
public class CourseBeforeTodayDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4733928870206504246L;
	
	//父id
	private int pId;
	//课程明细id
	private int pointId;
	//课程名称
	private String title;
	//章节名称
	private String name;
	//视频名称
	private String voName;
	//课程ID
	private int courseId;
	//专业ID
	private int subjectId;
	//商品ID
	private int sellId;
	
	public int getPId() {
		return pId;
	}
	public void setPId(int id) {
		pId = id;
	}
	public int getPointId() {
		return pointId;
	}
	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVoName() {
		return voName;
	}
	public void setVoName(String voName) {
		this.voName = voName;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getSellId() {
		return sellId;
	}
	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
}
