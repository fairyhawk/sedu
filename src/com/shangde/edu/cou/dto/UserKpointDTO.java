package com.shangde.edu.cou.dto;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.res.domain.Notes;

/**
 * 用户知识点DTO
 * 用于正式听课及试听课程，获取课程的所有知识点相关信息
 * 
 * @author chenshuai
 */
public class UserKpointDTO implements Serializable, Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4733928870206504246L;

	/**
	 * 父ID
	 */
	private int pId;
	
	/**
	 * ID
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 视频地址
	 */
	private String vedioUrl;
	/**
	 *音频的地址
	 */
	private String mpUrl;

	/**
	 * 主讲老师
	 */
	private String vedioTeacher;
	/**
	 * 排序值
	 */
	private int sortNum;
	
	/**
	 * 是否能试听(0：不能, 1:能)
	 */
	private int isAudition;
	
	/**
	 * 用户课程知识点ID
	 */
	private int cusCouKpointId;
	
	/**
	 * 是否能试听
	 */
	private int rdState;
	
	/**
	 * 一共有多少人听过
	 */
	private int hasRdCount;
	
	/**
	 * 是购买后用，还是试听时用的标示
	 */
	private String type;
	
	private List<String> urls;
	
	/**
	 * 讲义
	 */
	private String lecture;
	
	/**
	 * 级别
	 */
	private int level;
	
	/**
	 * 叶子节点
	 */
	private int leaf;
	
	/**
	 * 视频ID
	 */
	private int voId;
	/**
	 * 判断是否已观看视频
	 */
	private String isWatch="0";
	
	/**
	 * @author  ningxiao
	 * @version 统计课程下面的子节点
	 * @Create Date: 2011-8-11
	 */
	private int childnodes;
	/**
	 *视频笔记
	 */
	private List<Notes> notelist;
	/**
	 * 当前视频笔记总数
	 */
	private int notsesum;
	
	/**
	 * 整合name
	 * @return
	 */
	private String allName;
	
	private String ccUrl;// ccURL
	private Integer playType;//播放类型 0为原来播放方式，1为CC播放方式
	
	

	public Integer getPlayType() {
		return playType;
	}

	public void setPlayType(Integer playType) {
		this.playType = playType;
	}

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	public int getNotsesum() {
		return notsesum;
	}

	public void setNotsesum(int notsesum) {
		this.notsesum = notsesum;
	}

	public List<Notes> getNotelist() {
		return notelist;
	}

	public void setNotelist(List<Notes> notelist) {
		this.notelist = notelist;
	}

	public int getChildnodes() {
		return childnodes;
	}

	public void setChildnodes(int childnodes) {
		this.childnodes = childnodes;
	}

	public String getIsWatch() {
        return isWatch;
    }

    public void setIsWatch(String isWatch) {
        this.isWatch = isWatch;
    }

    public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public int compareTo(Object arg0) {
		UserKpointDTO kpointtemp = (UserKpointDTO)arg0;
		return new Integer(sortNum).compareTo(new Integer(kpointtemp.getSortNum()));
	}
	public boolean equals(Object o){
		if(o instanceof UserKpointDTO){
			UserKpointDTO ukd=(UserKpointDTO)o;
			if(this.id==ukd.id)return true;
		}
		return false;
	}
	public int hashCode(){
		return this.id;
	}

	public int getPId() {
		return pId;
	}

	public void setPId(int id) {
		pId = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public int getIsAudition() {
		return isAudition;
	}

	public void setIsAudition(int isAudition) {
		this.isAudition = isAudition;
	}

	public int getCusCouKpointId() {
		return cusCouKpointId;
	}

	public void setCusCouKpointId(int cusCouKpointId) {
		this.cusCouKpointId = cusCouKpointId;
	}

	public int getRdState() {
		return rdState;
	}

	public void setRdState(int rdState) {
		this.rdState = rdState;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public int getVoId() {
		return voId;
	}

	public void setVoId(int voId) {
		this.voId = voId;
	}
	public String getVedioTeacher() {
		return vedioTeacher;
	}

	public void setVedioTeacher(String vedioTeacher) {
		this.vedioTeacher = vedioTeacher;
	}
	public String getMpUrl() {
		return mpUrl;
	}

	public void setMpUrl(String mpUrl) {
		this.mpUrl = mpUrl;
	}

	public String getCcUrl() {
		return ccUrl;
	}

	public void setCcUrl(String ccUrl) {
		this.ccUrl = ccUrl;
	}

	public int getHasRdCount() {
		return hasRdCount;
	}

	public void setHasRdCount(int hasRdCount) {
		this.hasRdCount = hasRdCount;
	}
}
