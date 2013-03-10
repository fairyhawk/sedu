package com.shangde.edu.exam.condition;

import java.io.Serializable;
import java.util.Date;

import com.shangde.common.vo.PageResult;

/**
 * 查询试题条件
 * @author chenshuai
 *
 */
public class QueryQstCondition extends PageResult implements Serializable{
    /**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 试题id
	 */
	private int qstId;
	/**
	 * 试卷Id
	 */
    private int epId;
    /**
     * 试题类型
     */
    private int qstType;
    /**
     * 父题的id
     */
    private int qstindexId;
    /**
     * 关键字
     */
    private String searchKey;
    /**
     * 条件
     */
    private int tiaojian;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 专业id
     */
    private int subject_id;
    /**
     * 课程ID；
     */
    private int sortId;  
    
  

	public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}

	public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId;
    }

	public int getQstType() {
		return qstType;
	}

	public void setQstType(int qstType) {
		this.qstType = qstType;
	}

	public int getQstindexId() {
		return qstindexId;
	}

	public void setQstindexId(int qstindexId) {
		this.qstindexId = qstindexId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getTiaojian() {
		return tiaojian;
	}

	public void setTiaojian(int tiaojian) {
		this.tiaojian = tiaojian;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}
}