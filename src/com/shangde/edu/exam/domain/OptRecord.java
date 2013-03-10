package com.shangde.edu.exam.domain;

import java.io.Serializable;

import com.shangde.edu.cus.domain.Customer;

/**
 * 用户答题记录
 * @author chenshuai
 *
 */
public class OptRecord implements Serializable{
	
	/**
	 * 用户试卷ID
	 */
	private int userEpId;
	
	/**
	 * 问题ID
	 */
    private int qstId;
    
    /**
     * 答案Id
     */
    private int asrId;
    
    /**
     * 用户Id
     */
    private int cusId;
    
    /**
     * 答案内容，
     */
    private String answerContent;
    
    /**
     * 用户答案
     */
    private String userAnswer;
    
    /**
     * 添加时间
     */
    private java.util.Date addtime;
    
    private int reviewsId;
   
    private String epName;
    private String qstContext;
    private String worngJude;
    private String isAsr;
    private String nodeName;
    private String epnodeName;
    private String examTime;
    private Integer collectNum;//收藏数
    private Integer quesNum;//提问数
    private Integer doneNum;//做题数
    private Integer rightPercent;//正确率
    private Integer epType;
    private Integer isRight;//答题是否正确0-错误  1正确
    /**
     * 主观题正确答案
     */
    private String optContent;
    private Integer subjectId;
    private Integer coeffcient;
    
    
    
    
   

	public Integer getCoeffcient() {
		return coeffcient;
	}

	public void setCoeffcient(Integer coeffcient) {
		this.coeffcient = coeffcient;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getIsRight() {
		return isRight;
	}

	public void setIsRight(Integer isRight) {
		this.isRight = isRight;
	}

	public Integer getEpType() {
		return epType;
	}

	public void setEpType(Integer epType) {
		this.epType = epType;
	}

	public Integer getRightPercent() {
		return rightPercent;
	}

	public void setRightPercent(Integer rightPercent) {
		this.rightPercent = rightPercent;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Integer getQuesNum() {
		return quesNum;
	}

	public void setQuesNum(Integer quesNum) {
		this.quesNum = quesNum;
	}

	public Integer getDoneNum() {
		return doneNum;
	}

	public void setDoneNum(Integer doneNum) {
		this.doneNum = doneNum;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getEpnodeName() {
		return epnodeName;
	}

	public void setEpnodeName(String epnodeName) {
		this.epnodeName = epnodeName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getIsAsr() {
		return isAsr;
	}

	public void setIsAsr(String isAsr) {
		this.isAsr = isAsr;
	}

	public String getWorngJude() {
		return worngJude;
	}

	public void setWorngJude(String worngJude) {
		this.worngJude = worngJude;
	}

	public String getQstContext() {
		return qstContext;
	}

	public void setQstContext(String qstContext) {
		this.qstContext = qstContext;
	}


	public String getEpName() {
		return epName;
	}

	public void setEpName(String epName) {
		this.epName = epName;
	}

	/**
     * 试题类型，以Qstmiddle为准
     */
    private int qstType;
    
        
    public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId; 
    }
        
    public int getAsrId(){
        return asrId;
    }

    public void setAsrId(int asrId){
        this.asrId = asrId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public java.util.Date getAddtime(){
        return addtime;
    }

    public void setAddtime(java.util.Date addtime){
        this.addtime = addtime; 
    }

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public int getUserEpId() {
		return userEpId;
	}

	public void setUserEpId(int userEpId) {
		this.userEpId = userEpId;
	}

	public int getReviewsId() {
		return reviewsId;
	}

	public void setReviewsId(int reviewsId) {
		this.reviewsId = reviewsId;
	}

	public int getQstType() {
		return qstType;
	}

	public void setQstType(int qstType) {
		this.qstType = qstType;
	}

	public String getOptContent() {
		return optContent;
	}

	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}


}
