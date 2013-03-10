package com.shangde.edu.exam.condition;

import com.shangde.common.vo.PageResult;

/**
 * 查询试卷条件
 * @author chenshuai
 *
 */
public class GetQSTEXAMKB extends PageResult{
	/**
	 * 知识库名字
	 */
    private String Kname;
    /**
     * 正确率
     */
    private int rightCount;
    /**
     * 错误率
     */
    private int falseCount;
    /**
     * 为空率
     */
    private int noCount;
    /**
     * 建议学习的内容
     */
    private String Conter;
    /**
     * 课程名
     */
    private String kename;
    /**
     * 类型
     */
    private String type; 
    /**
     * 试卷名称
     */
    private String Epname;
    /**
     * 试卷id
     */
    private int epid;
	public String getKname() {
		return Kname;
	}
	public void setKname(String kname) {
		Kname = kname;
	}
	public int getRightCount() {
		return rightCount;
	}
	public void setRightCount(int rightCount) {
		this.rightCount = rightCount;
	}
	public int getFalseCount() {
		return falseCount;
	}
	public void setFalseCount(int falseCount) {
		this.falseCount = falseCount;
	}
	public int getNoCount() {
		return noCount;
	}
	public void setNoCount(int noCount) {
		this.noCount = noCount;
	}
	public String getConter() {
		return Conter;
	}
	public void setConter(String conter) {
		Conter = conter;
	}
	public String getKename() {
		return kename;
	}
	public void setKename(String kename) {
		this.kename = kename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEpname() {
		return Epname;
	}
	public void setEpname(String epname) {
		Epname = epname;
	}
	public int getEpid() {
		return epid;
	}
	public void setEpid(int epid) {
		this.epid = epid;
	}
}