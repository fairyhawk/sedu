package com.shangde.edu.cou.domain;

import java.io.Serializable;
import java.util.Date;

public class SellWay implements Serializable{
	/**
	 *  ID
	 **/
    private int sellId;
    /**
	 *   专业ID
	 **/
    private int subjectId;
    /**
	 *  售卖方式名
	 **/
    private String sellName;
    /**
	 *  售卖方式状态
	 **/
    private int status;
    /**
	 *  添加时间
	 **/
    private java.util.Date addTime;
    /**
	 *  售卖方式价格
	 **/
    private float sellPrice;
    
    /**
     * 原始价格
     */
    private Float oriPrice;
    
    /**
	 *  。。。。。
	 **/
    private String sellMark;
    /**
     * 讲师名称
     */
    private String teacher;
    /**
     * 专业关键字
     */
    private String subjectKey;
    /**
     * 课时数
     */
    private int lessonNumber;
    /**
     * 课程开始上传时间
     * 
     */
    private String startTime;
    
    /**
     *说明
     */
    private String remark;
    
    /**
     * 课程有效期
     */
    private String validity;
    /**
     * 下架时间
     */
    private java.util.Date  dropTime;
    /**
     * 上下架状态
     */
    private int shopState;
    /**
     * 
     * 时间版本
     */
    private String timeVersion;
    
    /**
     * 创建时间
     */
    private Date upTime;
    
    /**
     * 显示属性
     */
    private String disProperty;
    
    /**
     * 显示位置 
     */
    private String disPosition;
    
    /**
     * 过期时间
     */
    private Date validityTime;
    
    /**
     * 升级说明
     */
    private String upgradeShow;
    
    /**
     * 商品图片
     */
    private String imagesUrl;
    
    /**
     * 商品视频
     */
    private String vedioUrl;
    
    /**杨宁  2012/03/24  是否存在保过协议**/
    private Boolean isProtocal;
    /**杨宁  2012/03/24  保过协议内容**/
    private String protocalContent;
    
    /** 郑强 2012/05/16 有效期（天数） */
    private Integer validityNum;
    
    /** 郑强 2012/05/16 停止服务时间 */
    private Date stopServiceTime;
    
    /** 郑强 2012/05/16 有效期开始 */
    private Date validityBeginTime;
    
    /** 郑强 2012/05/16 有效期结束 */
    private Date validityEndTime;

	public Date getValidityBeginTime() {
		return validityBeginTime;
	}

	public void setValidityBeginTime(Date validityBeginTime) {
		this.validityBeginTime = validityBeginTime;
	}

	public Date getValidityEndTime() {
		return validityEndTime;
	}

	public void setValidityEndTime(Date validityEndTime) {
		this.validityEndTime = validityEndTime;
	}

	public Date getStopServiceTime() {
		return stopServiceTime;
	}

	public void setStopServiceTime(Date stopServiceTime) {
		this.stopServiceTime = stopServiceTime;
	}

	public Integer getValidityNum() {
		return validityNum;
	}

	public void setValidityNum(Integer validityNum) {
		this.validityNum = validityNum;
	}

	public Boolean getIsProtocal() {
		return isProtocal;
	}

	public void setIsProtocal(Boolean isProtocal) {
		this.isProtocal = isProtocal;
	}

	public String getProtocalContent() {
		return protocalContent;
	}

	public void setProtocalContent(String protocalContent) {
		this.protocalContent = protocalContent;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public String getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}

	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getDisProperty() {
		return disProperty;
	}

	public void setDisProperty(String disProperty) {
		this.disProperty = disProperty;
	}

	public String getDisPosition() {
		return disPosition;
	}

	public void setDisPosition(String disPosition) {
		this.disPosition = disPosition;
	}

	public Date getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(Date validityTime) {
		this.validityTime = validityTime;
	}

	public String getUpgradeShow() {
		return upgradeShow;
	}

	public void setUpgradeShow(String upgradeShow) {
		this.upgradeShow = upgradeShow;
	}

	public java.util.Date getDropTime() {
		return dropTime;
	}

	public void setDropTime(java.util.Date dropTime) {
		this.dropTime = dropTime;
	}

	public int getShopState() {
		return shopState;
	}

	public void setShopState(int shopState) {
		this.shopState = shopState;
	}



	public String getTimeVersion() {
		return timeVersion;
	}

	public void setTimeVersion(String timeVersion) {
		this.timeVersion = timeVersion;
	}

	public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public java.util.Date getAddTime(){
        return addTime;
    }

    public void setAddTime(java.util.Date addTime){
        this.addTime = addTime; 
    }

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}


	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getSellMark() {
		return sellMark;
	}

	public void setSellMark(String sellMark) {
		this.sellMark = sellMark;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getSubjectKey() {
		return subjectKey;
	}

	public void setSubjectKey(String subjectKey) {
		this.subjectKey = subjectKey;
	}

	public int getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public Float getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(Float oriPrice) {
		this.oriPrice = oriPrice;
	}
	
	
	/**李志强  2012/06/13  添加商品详情**/
	public String detail;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
