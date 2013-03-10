package com.shangde.edu.exam.condition;

import java.io.Serializable;
import java.util.Date;

import com.shangde.common.vo.PageResult;

/**
 * 查询试卷条件
 * @author chenshuai
 *
 */
public class QueryExampaperCondition extends PageResult implements Serializable{
	
    /**
	 * 系列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 试卷id
	 */
	private int epId;
    /**
     * 关节点类型
     */
    private int type;
    
    /**
     * 课程或知识点Id
     */
    private int kOrCouId;
    
    /**
     * 专业id
     */
    
    private int subjectId;
    
    /**
     * 课程id
     * 
     */
    private int cid;
    
    /**
     * 测试类型
     */
    private Integer eptype;
    
    /**
     * 考试的测试类型关键字
     */
    private String eptypekeyword;
    
 
    /**
     * 查询条件
     */
    private String searchKey;
    /**
     * 课程id
     * @return
     */
    
    private int courseId;
    
    /**
     * 试题类型
     */
    private int level;
    /**
     * 用户id
     * @return
     */
    private int userId;
    
    /**
     * 前台页面排序条件
     */
    private int paixu;
    
    /**
     * 课程id
     */
    private int sortId;
    /**
     * 查询条件
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
     * 试卷状态
     */
    private String epState;
    
    /**
     * 用户id
     */
    private int cusId;
    
    /**
     * 试题ID
     */
    private int qstId;
    
    public int getQstId() {
		return qstId;
	}

	public void setQstId(int qstId) {
		this.qstId = qstId;
	}

	/**
     * 排序语句
     */
    private String sql;
    private int scope;
    private int difficult_set;
    private int result_type;
    private String[] qstType;
    private String qstTypeSql;
    private int year;
    
    
    public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	private int ksnId;
    
    
    
    public int getKsnId() {
		return ksnId;
	}

	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}

	public String getQstTypeSql() {
		return qstTypeSql;
	}

	public void setQstTypeSql(String qstTypeSql) {
		this.qstTypeSql = qstTypeSql;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public int getDifficult_set() {
		return difficult_set;
	}

	public void setDifficult_set(int difficult_set) {
		this.difficult_set = difficult_set;
	}

	public int getResult_type() {
		return result_type;
	}

	public void setResult_type(int result_type) {
		this.result_type = result_type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getKOrCouId() {
		return kOrCouId;
	}

	public void setKOrCouId(int orCouId) {
		kOrCouId = orCouId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getEpId(){
        return epId;
    }

    public void setEpId(int epId){
        this.epId = epId;
    }



	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getEptype() {
		return eptype;
	}

	public void setEptype(Integer eptype) {
		this.eptype = eptype;
	}

	public String getEptypekeyword() {
		return eptypekeyword;
	}

	public void setEptypekeyword(String eptypekeyword) {
		this.eptypekeyword = eptypekeyword;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPaixu() {
		return paixu;
	}

	public void setPaixu(int paixu) {
		this.paixu = paixu;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
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
		if(endTime!=null){
			endTime.setHours(23);
			endTime.setMinutes(59);
			endTime.setSeconds(59);
		}
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String[] getQstType() {
		
		return qstType;
	}

	public void setQstType(String[] qstType) {
		this.qstType = qstType;
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<qstType.length;i++){
			sb.append(qstType[i]);
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		this.setQstTypeSql(sb.toString());
	}

	public String getEpState() {
		return epState;
	}

	public void setEpState(String epState) {
		if("".equals(epState)){
			this.epState=epState="-1";
		}else{
			this.epState = epState;
		}
		
	}

}