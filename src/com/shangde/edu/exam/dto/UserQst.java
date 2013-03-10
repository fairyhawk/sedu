package com.shangde.edu.exam.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.domain.QstPic;

/**
 * 用户答题DTO记录
 * @author chenshuai
 *
 */
public class UserQst implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	private int cusId;

	/**
	 * 试题Id
	 */
    private int qstId;
    
    /**
     * 试题内容
     */
    private String qstContent;
    
    /**
     * 正确答案
     */
    private String isAsr;
    
    /**
     * 用户答案
     */
    private String userAsr;
    
    /**
     * 试题类型
     */
    private int qstType;
    
    /**
     * 试题分数
     */
    private int score;
    
    /**
     * 简易难度
     */
    private int level;
    
    /**
     * 选项集合
     */
    private List<Options> options;
    
    /**
     * 试题图片
     */
    private QstPic qstPic;
    
    /**
     * 历史记录表的试题类型
     */
    private int optqstType;
    /**
     * 错误评语
     */
    private String wrongJude;
    /**
     * 选项序号
     */
    private String optOrder;
    /**
     * 选项内容
     */
    private String optContent;
    
    private List<UserQst> qstOptList=new ArrayList<UserQst>();           

	public List<UserQst> getQstOptList() {
		return qstOptList;
	}

	public void setQstOptList(List<UserQst> qstOptList) {
		this.qstOptList = qstOptList;
	}

	public int getQstId() {
		return qstId;
	}

	public void setQstId(int qstId) {
		this.qstId = qstId;
	}

	public String getQstContent() {
		return qstContent;
	}

	public void setQstContent(String qstContent) {
		this.qstContent = qstContent;
	}

	public String getIsAsr() {
		return isAsr;
	}

	public void setIsAsr(String isAsr) {
		this.isAsr = isAsr;
	}

	public String getUserAsr() {
		return userAsr;
	}

	public void setUserAsr(String userAsr) {
		this.userAsr = userAsr;
	}

	public int getQstType() {
		return qstType;
	}

	public void setQstType(int qstType) {
		this.qstType = qstType;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public QstPic getQstPic() {
		return qstPic;
	}

	public void setQstPic(QstPic qstPic) {
		this.qstPic = qstPic;
	}

	public String getWrongJude() {
		return wrongJude;
	}

	public void setWrongJude(String wrongJude) {
		this.wrongJude = wrongJude;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getOptqstType() {
		return optqstType;
	}

	public void setOptqstType(int optqstType) {
		this.optqstType = optqstType;
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
    
}
