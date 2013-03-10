package com.shangde.edu.finance.condition;

import com.shangde.common.vo.PageQuery;

public class QueryCashRecordCondition extends PageQuery {
    private int id;
    private String searchKey;
    private int userId;
    private int packId ;
    private String startTime;
    private String endTime;
    private String contractId;
    private int type;
    private int ctId;
    private String email;
    private int courseId;
    private int status;
    
    
    //流水金额
    private java.lang.Object cashRecordPrice;
    //减免价格
    private java.lang.Object reliefPrice;
    //优惠金额
    private java.lang.Object couponMoney;
    //商品状态
    private int shopStatus;  // 商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
    //支付类型
    private int shopPayType;   //0:赠送（内部开通）/1:网上支付（支付宝）/2:货到付款/3:网银在线/4:快钱/5:代理商开通
    //支付时间
    private java.util.Date shopPayTime; 
    //退款时间
    private java.util.Date refundTime;
    //到期时间
    private java.util.Date validityTime;
    //停止服务时间
    private java.util.Date stopTime;
    //延期时间
    private java.util.Date delayTime;
    //商品类型
    private int shopType;   //1 代表课程，2 代表书籍   
    //所属项目Id
    private int cRSubjectId;
    
    

	public int getCRSubjectId() {
		return cRSubjectId;
	}

	public void setCRSubjectId(int subjectId) {
		cRSubjectId = subjectId;
	}
 
	public java.lang.Object getCashRecordPrice() {
		return cashRecordPrice;
	}

	public void setCashRecordPrice(java.lang.Object cashRecordPrice) {
		this.cashRecordPrice = cashRecordPrice;
	}
	
	public java.lang.Object getReliefPrice() {
		return reliefPrice;
	}

	public void setReliefPrice(java.lang.Object reliefPrice) {
		this.reliefPrice = reliefPrice;
	}

	public java.lang.Object getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(java.lang.Object couponMoney) {
		this.couponMoney = couponMoney;
	}

	public int getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(int shopStatus) {
		this.shopStatus = shopStatus;
	}

	public int getShopPayType() {
		return shopPayType;
	}

	public void setShopPayType(int shopPayType) {
		this.shopPayType = shopPayType;
	}

	public java.util.Date getShopPayTime() {
		return shopPayTime;
	}

	public void setShopPayTime(java.util.Date shopPayTime) {
		this.shopPayTime = shopPayTime;
	}

	public java.util.Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(java.util.Date refundTime) {
		this.refundTime = refundTime;
	}

	public java.util.Date getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(java.util.Date validityTime) {
		this.validityTime = validityTime;
	}

	public java.util.Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(java.util.Date stopTime) {
		this.stopTime = stopTime;
	}

	public java.util.Date getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(java.util.Date delayTime) {
		this.delayTime = delayTime;
	}

	public int getShopType() {
		return shopType;
	}

	public void setShopType(int shopType) {
		this.shopType = shopType;
	}
	
    public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCtId() {
		return ctId;
	}

	public void setCtId(int ctId) {
		this.ctId = ctId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPackId() {
		return packId;
	}

	public void setPackId(int packId) {
		this.packId = packId;
	}
	
}