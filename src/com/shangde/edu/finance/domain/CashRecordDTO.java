package com.shangde.edu.finance.domain;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.util.StringUtil;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;
import com.shangde.edu.cus.domain.Customer;

public class CashRecordDTO implements Serializable{
	
	/** 注册用户id   */
	private int cusId;
	/** 流水id   */
	private int id;
	 /** 流水类型   */
	private int type; //1前台购买　2后台赠送  3后台修复
	 /** 流水金额   */
    private java.lang.Object price; 
    /** 流水说明   */
    private String crInfo;
    /** 创建时间   */
    private java.util.Date createTime;
    /** 订单号   */
    private String contractId;
	 /** 课程id   */
	private int courseId;
	 /** 打包id   */
    private int packId;
    /** 订单id   */
    private int ctId;
    /** 流水状态 */
    private int status;  //0无效 1有效
    
    /**商品名**/
    private String packName;
    /** 是否保过 **/
    private Boolean isProtocal;
   /**商品简单介绍**/
    
    private String remark;
    
    /**Yangning add 2012 03 14 教师姓名**/
    private String teacherNames;
    
    /**保过协议内容**/
    private String protocalContent;
    
    // 用于页面显示.一个教师姓名独占一行.使用<p>标签将每个教师姓名包裹起来.
    private String teacherNamesHTML;
    
    private int payType;  //支付类型
    
    private int subjectId;
    
    private Course course;
    
    private Customer customer;
    
   private int lessonNumber;
   private java.lang.Object sellPrice;
   
   /**流水下所有课程列表**/
   private List<UserCenterCourseDTO> courseList;
   
   private java.lang.Object oriSellPrice;
    
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type; 
    }
        
    public java.lang.Object getPrice(){
        return price;
    }

    public void setPrice(java.lang.Object price){
        this.price = price; 
    }
        
    public String getCrInfo(){
        return crInfo;
    }

    public void setCrInfo(String crInfo){
        this.crInfo = crInfo; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public String getContractId(){
        return contractId;
    }

    public void setContractId(String contractId){
        this.contractId = contractId; 
    }

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getPackId() {
		return packId;
	}

	public void setPackId(int packId) {
		this.packId = packId;
	}

	public int getCtId() {
		return ctId;
	}

	public void setCtId(int ctId) {
		this.ctId = ctId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getTeacherNames() {
		return teacherNames;
	}

	public void setTeacherNames(String teacherNames) {
		this.teacherNames = teacherNames;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public java.lang.Object getOriSellPrice() {
		return oriSellPrice;
	}

	public void setOriSellPrice(java.lang.Object oriSellPrice) {
		this.oriSellPrice = oriSellPrice;
	}

	public java.lang.Object getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(java.lang.Object sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public Boolean getIsProtocal() {
		return isProtocal;
	}

	public void setIsProtocal(Boolean isProtocal) {
		this.isProtocal = isProtocal;
	}

	public String getTeacherNamesHTML() {
		StringBuffer sb = new StringBuffer();
		if (!StringUtil.isNullString(teacherNames)) {
			String[] names = teacherNames.trim().split("\\s+");
			for(int i = 0; i < names.length; i++) {
				sb.append("<p>");
				sb.append(names[i]);
				sb.append("</p>");
			}
		}
		return sb.toString();
	}

	public void setTeacherNamesHTML(String teacherNamesHTML) {
		this.teacherNamesHTML = teacherNamesHTML;
	}

	public String getProtocalContent() {
		return protocalContent;
	}

	public void setProtocalContent(String protocalContent) {
		this.protocalContent = protocalContent;
	}

	public List<UserCenterCourseDTO> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<UserCenterCourseDTO> courseList) {
		this.courseList = courseList;
	}
}
