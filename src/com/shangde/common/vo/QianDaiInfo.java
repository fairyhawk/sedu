package com.shangde.common.vo;

/**
 * 钱袋包参数类 备注;参数为String类型的请进行编码。
 * 
 * @author liuqg
 * 
 */
public class QianDaiInfo {
    /**
     * 钱袋提供的 md5key不可更改
     */
    private String qiandaimd5key ="d9oOUBLawSDsnDKcja1q";
    /*
     * 版本号，string 类型，默认为1.0。
     */
    private String interfaceVer = "1.0";
    /*
     * 订单金额，decimal 类型，必须大于0，包含2 位小数。
     */
    private String tradeMoney;
    /*
     * 商户订单号，最多32 位，商户需保证其唯一性，且不能为空。
     */
    private String partnerTradeId;
    /*
     * 商户号，由钱袋支付提供给签约商户，请向商务合作人员索取。set方法已经置空，防止修改
     */
    private  String   partnerNo="10000010205";
    /*
     * 子项目编号.
     */
    private String childItem="childItem";
    /*
     * 回调地址,直接跳转。
     */
    private String returnUrl;
    /*
     * 通知后台
     */
    private String notifyUrl;
    /*
     * 商品名称。
     */
    private String productName = "嗨学网课程";
    /*
     * 商品Url。
     */
    private String productUrl;
    /*
     * ;备注，非必须。
     */
    private String remark;
    /**
     * 银行编码，指定的支付银行，参见银行列表。 webIcbcQiandai 中国工商银行 qdboc 中国银行 webBocom 交通银行
     * WebqdCsii 北京农村商业银行 webCeb 中国光大银行 webCbhb 渤海银行 webUpop 中国银联 webCitic 中信银行
     * webHxb 华夏银行 qdabc 中国农业银行 webCcbQd 中国建设银行 webCmbQdPay 招商银行 hzbank 杭州银行
     * webPsbc 中国邮政储蓄银行 webBcCb 北京银行 webSpdb 浦发银行
     * 我们为无卡支付，必须设置为webUpop 中国银联
     */
    private String bankCode="webUpop";
    /*
     * ;加密串，把上述所有字段按顺序直接连接，随后使用加密方式签名。
     */
    private String sign;
    /*
     * 比较加密串
     */
    private String compareSign;

    /*
     * 签名方式, 1.Md5
     */
    private String signType="1";
    /*
     * 订单生成者ip。
     */
    private String userIp;
    /*
     * 格式是整形，在这个时间后为失效。默认为30后失效，分钟为单位
     */
    private String tradeFailureTime=30*24*60+"";
    /*
     * 消费场所, 供用户查看(生活驿站等).
     */
    private String place="嗨学网";
    /*
     * 订单生成时间
     */
    private String orderTime;
    /*
     * 连接方式1直连0非直连 *无卡支付必须为直连 1直连；跳转银行 0非直连；跳转钱袋网
     */
    private String isDirect="1";
    
    //钱袋返回信息,注释部分为传递给钱袋，钱袋原封返回的值
    //private String partnerTradeId;
    private String state;
   // private String signType;
   // private String tradeMoney;
    private String successMoney;
    private String tradeId;
    //private String bankCode;
    private String tradeSuccessTime;
    //private String productName;
    //private String productUrl;
    //private String remark;
   // private String sign;
    
    public String getCompareSign() {
        return compareSign;
    }

    public void setCompareSign(String compareSign) {
        this.compareSign = compareSign;
    }

    public String getInterfaceVer() {
        return interfaceVer;
    }

    public void setInterfaceVer(String interfaceVer) {
        this.interfaceVer = interfaceVer;
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getPartnerTradeId() {
        return partnerTradeId;
    }

    public void setPartnerTradeId(String partnerTradeId) {
        this.partnerTradeId = partnerTradeId;
    }

    public String getPartnerNo() {
        return partnerNo;
    }

    public void setPartnerNo(String partnerNo) {
        this.partnerNo = partnerNo;
    }

    public String getChildItem() {
        return childItem;
    }

    public void setChildItem(String childItem) {
        this.childItem = childItem;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
  

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getTradeFailureTime() {
        return tradeFailureTime;
    }

    public void setTradeFailureTime(String tradeFailureTime) {
        this.tradeFailureTime = tradeFailureTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getIsDirect() {
        return isDirect;
    }

    public void setIsDirect(String isDirect) {
        this.isDirect = isDirect;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuccessMoney() {
        return successMoney;
    }

    public void setSuccessMoney(String successMoney) {
        this.successMoney = successMoney;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeSuccessTime() {
        return tradeSuccessTime;
    }

    public void setTradeSuccessTime(String tradeSuccessTime) {
        this.tradeSuccessTime = tradeSuccessTime;
    }

    public String getQiandaimd5key() {
        return qiandaimd5key;
    }

    public void setQiandaimd5key(String qiandaimd5key) {
        this.qiandaimd5key = qiandaimd5key;
    }

}
