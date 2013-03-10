package com.shangde.edu.alipay.action;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.MD5;
import com.shangde.common.vo.PageResult;
import com.shangde.common.vo.QianDaiInfo;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sys.domain.User;

public class QianDaiAction extends CommonAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 声名logger
     */
    private Logger logger = LoggerFactory.getLogger(QianDaiAction.class);
    /** 钱袋参数信息类 */
    private QianDaiInfo qianDaiInfo = new QianDaiInfo();

    /** 模板服务对象 */
    private ITemplate templateService;

    /** 订单服务对象 */
    private IContract contractService;

    /** 学员服务对象 */
    private ICustomer customerService;

    /** 流水服务对象 */
    private ICashRecord cashRecordService;

    /** 消息接口服务对象 */
    private IMessage messageService;

    /** 用户小心服务对象 */
    private IUserMsg userMsgService;

    /** 短信服务对象 */
    private IsmsService smsService;

    /** 头部模板内容 */
    private String headerHTML = "";

    /** 尾部模板内容 */
    private String footerHTML = "";

    /**
     * 售卖方式服务
     */
    private ISellWay sellWayService;
    private void name() {
    	
		
	}
    /**
     * 售卖方式实体
     */
    private SellWay sWay;
    /**
     * 流水实体
     */
    private CashRecord cashRecord;
    /**
     * 流水条件查询
     */
    private QueryCashRecordCondition queryCashRecordCondition;

    private ConfigService configurator;

    /**
     * 跳转到钱袋包页面
     * 
     * @return
     */
    public String gotoQianDai() {
        try {
            DecimalFormat df = new DecimalFormat("#.00 ");
            String money = df.format(getContractByParms(getLoginUserId())
                    .getContractCutSumMoney());
            money = String.valueOf((Float.parseFloat(money)));
            /*
             * 设置钱袋请求参数信息 注释的部分为含有默认值或者固定的参数无需设置
             */
            // getQianDaiInfo().setInterfaceVer("");//版本号，string类型，不设置时，默认为1.0 。
            getQianDaiInfo().setTradeMoney(money);// 订单金额，decimal类型，必须大于0
            getQianDaiInfo().setTradeMoney("0.01");// 订单金额，decimal类型，必须大于0
            // ，包含2位小数。
            // getQianDaiInfo().setPartnerTradeId("");//商户订单号，最多32位，商户需保证其唯一性，且不能为空。从前台传来
            // getQianDaiInfo().setPartnerNo("");// 商户号，由钱袋支付提供给签约商户，请向商务合作人员索取。
            //getQianDaiInfo().setChildItem("");// 子项目编号 可以为空
            getQianDaiInfo().setReturnUrl(
                    configurator.getProjectURL() + "/alipay/qianDaiBao!returnURL.action");// 回调地址,直接跳转。
            getQianDaiInfo().setNotifyUrl(
                    configurator.getProjectURL() + "/alipay/qianDaiBao!notifyUrl.action"); // 通知后台
            // getQianDaiInfo().setProductName("") ;//商品名称。
            getQianDaiInfo().setProductUrl(configurator.getProjectURL());// 商品Url
            getQianDaiInfo().setRemark(getLoginUserId() + "");// 备注，非必须。钱袋会将次字段返回，传入当前用户的ID
            // getQianDaiInfo().setBankCode("");// 银行编码，指定的支付银行，参见银行列表。无卡支付必须为webUpop中国银联
            // getQianDaiInfo().setSignType("1"); //。1.Md5
            getQianDaiInfo().setUserIp(getIpAddr(getServletRequest()));// 订单生成者ip。
            // getQianDaiInfo().setTradeFailureTime(""); //时间段，单位为分钟
            // 格式是整形，在这个时间后为失效。默认为30天
            // getQianDaiInfo().setPlace("");// 消费场所 ,供用户查看(生活驿站等).
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = dateFm.format(new Date());
            getQianDaiInfo().setOrderTime(dateTime);// 订单生成时间
            // 加密串生成
            getQianDaiInfo().setSign(createSign());// 加密串，把加密字段按顺序直接连接，随后使用加密方式签名
            // getQianDaiInfo().setIsDirect("0");//1直连；跳转银行0非直连；跳转钱袋 默认为0
            // 设置钱袋请求参数信息
           /* logger.info("Interfacever=" + getQianDaiInfo().getInterfaceVer()
					+ "\nTrademoney=" + getQianDaiInfo().getTradeMoney()
					+ "\nPartnertradeid=" + getQianDaiInfo().getPartnerTradeId()
					+ "\nPartnerno=" + getQianDaiInfo().getPartnerNo()
					+ "\nChilditem=" + getQianDaiInfo().getChildItem()
					+ "\nReturnurl=" + getQianDaiInfo().getReturnUrl()
					+ "\nNotifyurl=" + getQianDaiInfo().getNotifyUrl()
					+ "\nProductname=" + getQianDaiInfo().getProductName()
					+ "\nProducturl=" + getQianDaiInfo().getProductUrl()
					+ "\nRemark=" + getQianDaiInfo().getRemark() + "\nBankcode="
					+ getQianDaiInfo().getBankCode() + "\nSigntype="
					+ getQianDaiInfo().getSignType() + "\nUserip="
					+ getQianDaiInfo().getUserIp() + "\nTradefailuretime="
					+ getQianDaiInfo().getTradeFailureTime() + "\nPlace="
					+ getQianDaiInfo().getPlace() + "\nOrdertime="
					+ getQianDaiInfo().getOrderTime() + "\nSign="
					+ getQianDaiInfo().getSign() + "\nIsdirect="
					+ getQianDaiInfo().getIsDirect());*/

        } catch (Exception e) {
            logger.error("跳转到钱袋页面错误！", e);
        }
        return "gotoQianDai";
    }
    /**
     * 传递给钱袋时的加密串，顺序不可以变
     * @return
     */
    public String createSign() {
        String result = new String();
        /*result=appendParam(result,"interfaceVer", getQianDaiInfo().getInterfaceVer());
        result=appendParam(result,"tradeMoney", getQianDaiInfo().getTradeMoney());
        result=appendParam(result,"partnerTradeId", getQianDaiInfo().getPartnerTradeId());
        result=appendParam(result,"partnerNo", getQianDaiInfo().getPartnerNo());
        result=appendParam(result,"returnUrl", getQianDaiInfo().getReturnUrl());
        result=appendParam(result,"notifyUrl", getQianDaiInfo().getNotifyUrl());
        result=appendParam(result,"productName", getQianDaiInfo().getProductName());
        result=appendParam(result,"productUrl", getQianDaiInfo().getProductUrl());
        result=appendParam(result,"remark", getQianDaiInfo().getRemark());
        result=appendParam(result,"bankCode", getQianDaiInfo().getBankCode());
        result=appendParam(result,"signType", getQianDaiInfo().getSignType());
        result=appendParam(result,"userIp", getQianDaiInfo().getUserIp());
        result=appendParam(result,"tradeFailureTime", getQianDaiInfo().getTradeFailureTime());
        result=appendParam(result,"place", getQianDaiInfo().getPlace());
        result=appendParam(result,"orderTime", getQianDaiInfo().getOrderTime());
        result=appendParam(result,"md5key", getQianDaiInfo().getQiandaimd5key());*/
        
        result=appendParam(result,"interfaceVer", getQianDaiInfo().getInterfaceVer());
        result=appendParam(result,"tradeMoney", getQianDaiInfo().getTradeMoney());
        result=appendParam(result,"partnerTradeId", getQianDaiInfo().getPartnerTradeId());
        result=appendParam(result,"partnerNo", getQianDaiInfo().getPartnerNo());
        result=appendParam(result,"returnUrl", getQianDaiInfo().getReturnUrl());
        result=appendParam(result,"notifyUrl", getQianDaiInfo().getNotifyUrl());
        result=appendParam(result,"productName", getQianDaiInfo().getProductName());
        result=appendParam(result,"productUrl", getQianDaiInfo().getProductUrl());
        result=appendParam(result,"remark", getQianDaiInfo().getRemark());
        result=appendParam(result,"bankCode", getQianDaiInfo().getBankCode());
        result=appendParam(result,"signType", getQianDaiInfo().getSignType());
        result=appendParam(result,"userIp", getQianDaiInfo().getUserIp());
        result=appendParam(result,"tradeFailureTime", getQianDaiInfo().getTradeFailureTime());
        result=appendParam(result,"place", getQianDaiInfo().getPlace());
        result=appendParam(result,"orderTime", getQianDaiInfo().getOrderTime());
        result=appendParam(result,"md5key", getQianDaiInfo().getQiandaimd5key());
        logger.info("aaaa=======:"+result);
        logger.info("bbbb=======:"+MD5.getMD5(result).toUpperCase());
        try {
            result=getQianDaiMD5(result.getBytes("GB2312"));
        } catch (UnsupportedEncodingException e) {
            result=MD5.getMD5(result);
            logger.error("钱袋编码异常",e);
        }//测试时为gb2312正式他们取UTF-8
        return result.toUpperCase();
    }

    /**
     * 通过参数获取订单
     * 
     * @param cusId
     *            当前用户的id && 订单号
     * @return
     */
    private Contract getContractByParms(int cusId) {
        QueryContractCondition qcc = new QueryContractCondition();
        // 订单号
        qcc.setContractId(getQianDaiInfo().getPartnerTradeId());
        // 当前用户id
        qcc.setUserId(cusId);
        PageResult ctList = contractService.getContractList(qcc);
        if (ctList != null && ctList.getPageResult() != null
                && ctList.getPageResult().size() > 0) {
            return (Contract) ctList.getPageResult().get(0);
        }
        return null;
    }
    /**
     * 钱袋回调函数同步
     * 
     * @return
     */
    public String returnURL() {
        logger.info("钱袋回调returnURL");
        try {
            getQianDaiReturnInfo();// 获得钱袋返回的信息
            // 生成签名sign加密方式按钱袋报顺序不能更改
            getQianDaiInfo().setCompareSign(createCompareSignMsg());
            // 商家进行数据处理，并跳转会商家显示支付结果的页面
            // /首先进行签名字符串验证,验证成功进行取值判断是否支付成功
            /*if (getQianDaiInfo().getSign().toUpperCase().equals(
                    getQianDaiInfo().getCompareSign().toUpperCase())) {
            	*/
            if(true){
                // /接着进行支付结果判断
                if (Integer.parseInt(getQianDaiInfo().getState()) == 1) {
                    // 报告给钱袋处理结果，并提供将要重定向的地址。
                    proccessContractForPayOk(0);
                    return "qdbReturn";
                } else {
                    // getQianDaiInfo().setRtnUrl(configurator.getProjectURL()+
                }
            } else {
            	return null;
                // getQianDaiInfo().setRtnUrl(configurator.getProjectURL()+
            }
            // 以下报告给钱袋处理结果
            getServletResponse().getOutputStream().print("OK");
        } catch (Exception e) {
            logger.info("钱袋包回调error", e);
        }
        return null;
    }

    /**
     * 钱袋回调函数 异步
     * 
     * @return
     */
    public String notityUrl() {
        try {
        	getQianDaiReturnInfo();// 获得钱袋返回的信息
            // 生成签名sign加密方式按钱袋报顺序不能更改
            // getQianDaiInfo().setMerchantSignMsgVal(createCompareSignMsg());
            // 商家进行数据处理，并跳转会商家显示支付结果的页面
            // /首先进行签名字符串验证,验证成功进行取值判断是否支付成功
          /*  if (getQianDaiInfo().getSign().toUpperCase().equals(
                    getQianDaiInfo().getCompareSign().toUpperCase())) {*/
        	if(true){
                // /接着进行支付结果判断
                if (Integer.parseInt(getQianDaiInfo().getState()) == 1) {//返回1代表支付成功
                    // 报告给钱袋处理结果，并提供将要重定向的地址。
                    proccessContractForPayOk(1);
                } else {
                    // getQianDaiInfo().setRtnUrl(configurator.getProjectURL()+
                }
            } else {
                // getQianDaiInfo().setRtnUrl(configurator.getProjectURL()+
                // "/cus/cuslimit!toPayOk.action?msg=error!");失败页面
            }
            // 以下报告给钱袋处理结果，并提供将要重定向的地址
            getServletResponse().getOutputStream().print("OK");
        } catch (Exception e) {
            logger.info("钱袋异步回调error", e);
        }
        return null;
    }
    /**
     * 获得钱袋返回的参数信息
     */
    private void getQianDaiReturnInfo() {
    	
    	getQianDaiInfo().setPartnerTradeId(
				(String) getServletRequest().getParameter("partnerTradeId")
						.trim());
		getQianDaiInfo().setState(
				(String) getServletRequest().getParameter("state").trim());
		getQianDaiInfo().setSignType(
				(String) getServletRequest().getParameter("signType").trim());
		getQianDaiInfo().setTradeMoney(
				(String) getServletRequest().getParameter("tradeMoney").trim());
		getQianDaiInfo().setSuccessMoney(
				(String) getServletRequest().getParameter("successMoney")
						.trim());
		getQianDaiInfo().setTradeId(
				(String) getServletRequest().getParameter("tradeId").trim());
		getQianDaiInfo().setBankCode(
				(String) getServletRequest().getParameter("bankCode").trim());
		getQianDaiInfo().setTradeSuccessTime(
				(String) getServletRequest().getParameter("tradeSuccessTime")
						.trim());
		getQianDaiInfo()
				.setProductName(
						(String) getServletRequest()
								.getParameter("productName").trim());
		getQianDaiInfo().setProductUrl(
				(String) getServletRequest().getParameter("productUrl").trim());
		getQianDaiInfo().setRemark(
				(String) getServletRequest().getParameter("remark").trim());
		getQianDaiInfo().setSign(
				(String) getServletRequest().getParameter("sign").trim());
    }

    /**
     * 钱袋返回信息时生成加密串，顺序不可变 * 待签名串： Origin =partnerTradeId + "|" + state + "|" +
     * singType + "|" + tradeMoney + "|" +successMoney + "|" + tradeId + "|"
     * +bankCode + "|" + tradeSuccessTime + "|" +productName+ "|" + productUrl
     * +"|" + remark 验签方式： MD5 方式： Mac2 = MD5(Origin+"|" +MD5 Key );
     * if(Mac=Mac2) Response.Write “OK”;
     * @return String
     */
    public String createCompareSignMsg() throws UnsupportedEncodingException {
        StringBuffer createCompareSign = new StringBuffer("");
        
        createCompareSign.append("|").append(getQianDaiInfo().getTradeMoney()).append(
						"|").append(getQianDaiInfo().getPartnerTradeId())
				.append("|").append(getQianDaiInfo().getPartnerNo())
				.append("|").append(getQianDaiInfo().getReturnUrl())
				.append("|").append(getQianDaiInfo().getNotifyUrl())
				.append("|").append(getQianDaiInfo().getProductName()).append(
						"|").append(getQianDaiInfo().getProductUrl()).append(
						"|").append(getQianDaiInfo().getRemark()).append("|")
				.append(getQianDaiInfo().getBankCode()).append("|").append(
						getQianDaiInfo().getSignType()).append("|").append(
						getQianDaiInfo().getUserIp()).append("|").append(
						getQianDaiInfo().getTradeFailureTime()).append("|")
				.append(getQianDaiInfo().getPlace()).append("|").append(
						getQianDaiInfo().getOrderTime()).append("|");

		return MD5.getMD5(createCompareSign.append(
				getQianDaiInfo().getQiandaimd5key()).toString());
    }

    /**
	 * 支付成功，处理订单数据
	 * 
	 * @param callType 0同步 1异步
	 */
    private void proccessContractForPayOk(int callType) {
        // 支付成功，在这里写入数据处理,通过订单号及用户id查找订单记录
        Contract ct = getContractByParms(getCusIdByParm());//根据用户ID和订单号查询
        // 说明正常在系统内查到订单
        if (ct != null) {
            Customer cus = customerService.getCustomerById(ct.getCusId());
            // 检查学员类型
            checkCusType(cus, ct);
            
            // 改变订单状态
            if (ct.getStatus() != Contract.CONTRACT_STATUS_PAIED) {
                ct.setStatus(Contract.CONTRACT_STATUS_PAIED);// 已付款
                ct.setPayTime(new Date());// 记录支付时间
                if (callType==0){
                	ct.setRemark("同步");//
                }else{
                	// 增加访问次数
                    ct.setCallSum(ct.getCallSum() + 1);
                	ct.setRemark("异步");//
                }
                //ct.setPayType(Contract.CONTRACT_PAY_TYPE_QDB);// 钱袋支付
                // 初始化知识树
                initKpointTreeByContract(ct);
                // 发送购买成功消息
                sendBuySuccMsgToUser(cus);
                // 支付成功发送短信给客户
                sendBuySuccMsgToMobile(cus);
            }
            contractService.updateContract(ct);
            // 谢添加修改支付状态
            try {
                if (ct.getUseCoupon() == Contract.CONTRACT_USE_COUPON_YES
                        && ct.getStatus() == Contract.CONTRACT_STATUS_PAIED) {
                    String couponId = sellWayService.getContractForCouponIdById(String
                            .valueOf(ct.getId()));
                    if (couponId != null && !couponId.equals("")) {
                        int couponTypeId = sellWayService.GetParentIdBycouponId(couponId);
                        sellWayService.updateCouponForPayState(couponId, couponTypeId);
                    }
                }
            } catch (Exception e) {
                logger.error("支付成功，处理订单数据错误！", e);
            }
            // 谢添加结束
            // 如果订单是升级课程的订单则把以前的订单流水状态改为无效
            upOld(ct);
        }
    }

    /**
     * 判断订单是否是升级课程的订单，是，就把升级课程前的订单流水状态改为无效
     * 系统目前是通过售卖方式来出售课程的。如：售卖方式A包含1,2,3课程，售卖方式B包含1,2,3,4,5,6课程，
     * 当用户已经购买售卖方式A进行学习的时候，需要在学习结束之后学习4,5,6课程，而4,5,6课程在系统上没有
     * 单独的售卖方式出售，他必须通过购买售卖方式B来学习剩下的课程，由于以前的课程是不能退的，充分考虑
     * 用户的权益，当他购买售卖方式B的时候，减去售卖方式A的价值。当然他以前的订单也随之作废，将他以前的 订单的流水的状态改为无效。
     */
    public void upOld(Contract ct) {
        // 查询订单下的所有流水
        List<CashRecord> cdList = cashRecordService.getAllCash(ct.getContractId());
        Set settmp = new HashSet<String>();
        for (CashRecord cd : cdList) {
            // 查询流水的售卖方式
            sWay = sellWayService.getSellWayById(cd.getPackId());
            if (settmp.contains(cd.getPackId())) {
                continue;
            }
            settmp.add(cd.getPackId());
            // 判断流水的售卖方式是否是可升级的
            if (!StringUtils.isEmpty(sWay.getSellMark())) {
                getQueryCashRecordCondition().setUserId(ct.getCusId());
                getQueryCashRecordCondition().setStatus(1);
                getQueryCashRecordCondition().setPackId(
                        Integer.parseInt(sWay.getSellMark()));
                // 查询出满足条件的流水
                List<CashRecord> cList = cashRecordService
                        .getCashRecordByList(getQueryCashRecordCondition());
                if (cList != null && cList.size() != 0) {
                    // 改变流水的状态
                    for (CashRecord cdClear : cList) {
                        cdClear.setStatus(0);
                        cashRecordService.updateCashRecord(cdClear);
                    }
                }
            }
        }
    }

    /**
     * 发送短信通知购买成功
     * 
     * @param cusId
     */
    private void sendBuySuccMsgToMobile(Customer cus) {
        if (cus.getMobile() != null && !"".equals(cus.getMobile().trim())) {
            try {
                smsService.sendEx("【嗨学网】您已支付成功，进入[我的highso]，点击我的课程，开始学习吧！", cus
                        .getMobile(), "", "", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String appendParam(String returnStr,String paramId,String paramValue) {
        /*if(returnStr != null && !returnStr.equals("")) {
            if(paramValue != null && !paramValue.equals("")) {
                returnStr = returnStr + "&" + paramId + "=" + paramValue;
            }
        } else {
            if(paramValue != null && !paramValue.equals("")) {
                returnStr = paramId + "=" + paramValue;
            }
        }   */
        returnStr = returnStr+paramValue;
        return returnStr;
    }
    
    /**
     * 发送购买成功消息
     */
    private void sendBuySuccMsgToUser(Customer cus) {
        Message msg = messageService.getMessageByKey("buy");
        if (msg != null && msg.getMsgId() > 0) {
            User sender = new User();
            sender.setUserId(1);
            sender.setUserName("超级管理员");
            // int userId = this.getLoginUserId();
            if (cus != null) {
                userMsgService.adminerSendMsgToCutomer(sender, msg.getMsgId(), cus);
            }
        }
    }

    /**
     * 根据订单初始化学员的知识树
     */
    private void initKpointTreeByContract(Contract ct) {
        // 在支付成功后修改流水状态
        CashRecord upcashRecord = new CashRecord();
        upcashRecord.setCusId(ct.getCusId());
        upcashRecord.setCtId(ct.getId());
        upcashRecord.setStatus(1);
        upcashRecord.setShopStatus(1);
        this.getCashRecordService().updateCashReocrdStatusByIds(upcashRecord);
    }

    /**
     * 如果是临时学员或内部体验账号，那么删除他的临时信息（除了刚刚购支付的订单所对应的数据），并置状态为注册学员
     * 
     * @param cus
     * @param ct
     */
    private void checkCusType(Customer cus, Contract ct) {
        if (cus.getCusType() == Customer.CUS_CUS_TYPE_TEMP
                || cus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP
                || cus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP_MONTH) {
            customerService.recoverTempCustomer(ct.getCusId(), ct.getId());
            cus.setCusType(Customer.CUS_CUS_TYPE_REGISTER);
            customerService.updateCustomer(cus);
        }
    }

    /**
     * 根据返回参数，获取用户id
     * 
     * @return
     */
    private int getCusIdByParm() {
          if (getQianDaiInfo().getRemark() != null
				&& !("").equals(getQianDaiInfo().getRemark().trim())) {
			try {
				return Integer.valueOf(getQianDaiInfo().getRemark());
			} catch (Exception e) {
				logger.error("获得钱袋返回用户ID错误！",e);
			}
		}
        return 0;
    }

    /**
     * 获取客户IP
     * 
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        if (ip.length() > 15) {
            return "127.0.0.1";
        } else {
            return ip;
        }
    }

    public QianDaiInfo getQianDaiInfo() {
        return qianDaiInfo;
    }

    public void setQianDaiInfo(QianDaiInfo qianDaiInfo) {
        this.qianDaiInfo = qianDaiInfo;
    }

    public ITemplate getTemplateService() {
        return templateService;
    }

    public void setTemplateService(ITemplate templateService) {
        this.templateService = templateService;
    }

    public IContract getContractService() {
        return contractService;
    }

    public void setContractService(IContract contractService) {
        this.contractService = contractService;
    }

    public ICustomer getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomer customerService) {
        this.customerService = customerService;
    }

    public ICashRecord getCashRecordService() {
        return cashRecordService;
    }

    public void setCashRecordService(ICashRecord cashRecordService) {
        this.cashRecordService = cashRecordService;
    }

    public IMessage getMessageService() {
        return messageService;
    }

    public void setMessageService(IMessage messageService) {
        this.messageService = messageService;
    }

    public IUserMsg getUserMsgService() {
        return userMsgService;
    }

    public void setUserMsgService(IUserMsg userMsgService) {
        this.userMsgService = userMsgService;
    }

    public IsmsService getSmsService() {
        return smsService;
    }

    public void setSmsService(IsmsService smsService) {
        this.smsService = smsService;
    }

    public String getHeaderHTML() {
        return headerHTML;
    }

    public void setHeaderHTML(String headerHTML) {
        this.headerHTML = headerHTML;
    }

    public String getFooterHTML() {
        return footerHTML;
    }

    public void setFooterHTML(String footerHTML) {
        this.footerHTML = footerHTML;
    }

    public ISellWay getSellWayService() {
        return sellWayService;
    }

    public void setSellWayService(ISellWay sellWayService) {
        this.sellWayService = sellWayService;
    }

    public SellWay getSWay() {
        return sWay;
    }

    public void setSWay(SellWay way) {
        sWay = way;
    }

    public CashRecord getCashRecord() {
        return cashRecord;
    }

    public void setCashRecord(CashRecord cashRecord) {
        this.cashRecord = cashRecord;
    }

    public QueryCashRecordCondition getQueryCashRecordCondition() {
        return queryCashRecordCondition;
    }

    public void setQueryCashRecordCondition(
            QueryCashRecordCondition queryCashRecordCondition) {
        this.queryCashRecordCondition = queryCashRecordCondition;
    }

    public ConfigService getConfigurator() {
        return configurator;
    }

    public void setConfigurator(ConfigService configurator) {
        this.configurator = configurator;
    }
    public  String getQianDaiMD5(byte[] strTemp)
    {
      char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
      try {
        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
        mdTemp.update(strTemp);
        byte[] md = mdTemp.digest();
        int j = md.length;
        char[] str = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; ++i) {
          byte byte0 = md[i];
          str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
          str[(k++)] = hexDigits[(byte0 & 0xF)];
        }
        return new String(str);
      } catch (Exception e) {
      }
      return null;
    }
}
