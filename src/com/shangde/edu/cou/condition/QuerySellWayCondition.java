package com.shangde.edu.cou.condition;

public class QuerySellWayCondition {
    private int saleId;
    public int subjectId;
    
    private int customerId;
        
    public int getCustomerId() {
		return customerId;
	}

    /**
     * 上下架状态
     */
    private int shopState;
    
    /**
     * (显示属性1为推荐2为试听3为特价4为精品5为抢购)
     */
    private String disProperty;
	
    /**
     * (1-4为区域1到4 ，5为网站首页推荐区6为网站首页试听区7为购物车8为个人中心首页推荐区9注册完成引导页推荐区)
     */
    private String disPosition;

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

	public int getShopState() {
		return shopState;
	}

	public void setShopState(int shopState) {
		this.shopState = shopState;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getSaleId(){
        return saleId;
    }

    public void setSaleId(int saleId){
        this.saleId = saleId;
    }
   
}