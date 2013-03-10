package com.shangde.edu.alipay.action;

import java.io.IOException;
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
import com.shangde.common.vo.TrueUInfo;
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
import com.sun.org.apache.bcel.internal.generic.NEW;

public class TrueUAction extends CommonAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 声名logger
     */
    private Logger logger = LoggerFactory.getLogger(TrueUAction.class);
    /** 真友参数信息类 */
    private TrueUInfo trueUInfo = new TrueUInfo();

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
    
    private int productNum=0;

    /**
     * 跳转到真友页面
     * 
     * @return
     */
    public String gotoTrueU() {
        try {
            DecimalFormat df = new DecimalFormat("#.00 ");
            String money = df.format(Float
                    .parseFloat(getContractByParms(getLoginUserId())
                            .getContractCutSumMoney().toString()));
            money = String.valueOf((Float.parseFloat(money) * 100));// 真友100相当于1元
            if (money.contains(".")) {
                money = money.substring(0, money.indexOf("."));
            }

            /*
             * 设置真友请求参数信息 注释的部分为含有默认值或者固定的参数无需设置
             */
            getTrueUInfo().setPageUrl(
                    configurator.getProjectURL() + "/alipay/trueu!pageUrl.action");
            getTrueUInfo().setNoticeUrl(
                    configurator.getProjectURL() + "/alipay/trueu!noticeUrl.action");
            // 订单号,从页面传来
            // getTrueUInfo().setOrderId(orderId);
            getTrueUInfo().setOrderAmount(money);// 商户订单金额
            // 以分为单位。比方1元，提交时金额应为100
            //getTrueUInfo().setOrderAmount("100");// 商户订单金额
            // 以分为单位。比方1元，提交时金额应为100
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateTime = dateFm.format(new Date());
            getTrueUInfo().setOrderTime(dateTime);// 订单生成时间
            getTrueUInfo().setExt1(getLoginUserId() + "");// 扩展字段，会返回传入用户的CUSID
            Customer cus = customerService.getCustomerById(getLoginUserId());
            List<CashRecord> cashRecordList = cashRecordService.getCashRecordByCtIdForFanli(getTrueUInfo().getOrderId());
            String sellName="";
            if(cashRecordList!=null&& cashRecordList.size()!=0){
            	SellWay sellWay = sellWayService.getSellWayById(cashRecordList.get(0).getPackId());
            	if(sellWay!=null){
            		sellName=sellName+sellWay.getSellName();
            	}
            }
            if(cashRecordList.size()==1){
            	getTrueUInfo().setProductName(sellName);
            	getTrueUInfo().setProductNum("1");
            }else if(cashRecordList.size()>1) {
            	getTrueUInfo().setProductName(sellName+"...");
            	getTrueUInfo().setProductNum(cashRecordList.size()+"");
			}
            getTrueUInfo().setProductDesc(sellName);
            if (cus!=null){
                getTrueUInfo().setPayerContact(cus.getMobile());
                getTrueUInfo().setPayerEmail(cus.getEmail());
            }
            // 加密串生成
            getTrueUInfo().setSign(createSign());// 加密串，把加密字段按顺序直接连接，随后使用加密方式签名
            // getTrueUInfo().setIsDirect("0");//1直连；跳转银行0非直连；跳转真友 默认为0
            // 设置真友请求参数信息
        } catch (Exception e) {
            logger.error("跳转到真友页面错误！", e);
        }
        return "gototrueu";
    }

    /**
     * 传递给真友时的加密串，顺序不可以变， 按照参数的字母顺序a-z
     * 
     * @return
     */
    public String createSign() {
        String result = new String();
        result = appendParam(result, "ext1", getTrueUInfo().getExt1());
        result = appendParam(result, "inputCharset", getTrueUInfo().getInputCharset());
        result = appendParam(result, "merchantID", getTrueUInfo().getMerchantID());
        result = appendParam(result, "method", getTrueUInfo().getMethod());
        result = appendParam(result, "noticeUrl", getTrueUInfo().getNoticeUrl());
        result = appendParam(result, "orderAmount", getTrueUInfo().getOrderAmount());
        result = appendParam(result, "orderId", getTrueUInfo().getOrderId());
        result = appendParam(result, "orderTime", getTrueUInfo().getOrderTime());
        result = appendParam(result, "pageUrl", getTrueUInfo().getPageUrl());
        result = appendParam(result, "payType", getTrueUInfo().getPayType());
        result = appendParam(result, "payerContact", getTrueUInfo().getPayerContact());
        result = appendParam(result, "payerEmail", getTrueUInfo().getPayerEmail());
        result = appendParam(result, "productDesc", getTrueUInfo().getProductDesc());
        result = appendParam(result, "productName", getTrueUInfo().getProductName());
        result = appendParam(result, "productNum", getTrueUInfo().getProductNum());
        result = appendParam(result, "signType", getTrueUInfo().getSignType());
        result = appendParam(result, "version", getTrueUInfo().getVersion());
        result = appendParam(result, "key", getTrueUInfo().getKey());
        logger.info("加密前："+result);
        try {
            result = getTrueUMD5(result.getBytes("utf-8")).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            result = MD5.getMD5(result).toUpperCase();
            logger.error("真友编码异常", e);
        }
        logger.info("加密后："+result);
        return result;
    }

    /**
     * 获得真友返回时的加密串，顺序不可以变，按照参数的字母顺序
     * 
     * @return
     */
    public String createCompaerSign() {
        String result = new String();
        result = appendParam(result, "dealId", getTrueUInfo().getDealId());
        result = appendParam(result, "dealTime", getTrueUInfo().getDealTime());
        result = appendParam(result, "errMsg", getTrueUInfo().getErrMsg());
        result = appendParam(result, "ext1", getTrueUInfo().getExt1());
        result = appendParam(result, "ext2", getTrueUInfo().getExt2());
        result = appendParam(result, "fee", getTrueUInfo().getFee());
        result = appendParam(result, "loanAmount", getTrueUInfo().getLoanAmount());
        result = appendParam(result, "merchantID", getTrueUInfo().getMerchantID());
        result = appendParam(result, "orderAmount", getTrueUInfo().getOrderAmount());
        result = appendParam(result, "orderId", getTrueUInfo().getOrderId());
        result = appendParam(result, "orderTime", getTrueUInfo().getOrderTime());
        result = appendParam(result, "payResult", getTrueUInfo().getPayResult());
        result = appendParam(result, "signType", getTrueUInfo().getSignType());
        result = appendParam(result, "version", getTrueUInfo().getVersion());
        result = appendParam(result, "key", getTrueUInfo().getKey());
        logger.info("createCompaerSign:加密前："+result);
        try {
            result = getTrueUMD5(result.getBytes("utf-8")).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            result = MD5.getMD5(result).toUpperCase();
            logger.error("真友返回编码异常", e);
        }
        logger.info("createCompaerSign:加密后："+result);
        return result;
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
        qcc.setContractId(getTrueUInfo().getOrderId());
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
     * 真友回调函数同步
     * 
     * @return
     */
    public String pageUrl() {
        logger.info("真友回调pageUrl");
        try {
            getTrueUReturnInfo();// 获得真友返回的信息
            // 生成签名sign加密方式按真友报顺序不能更改
            // getTrueUInfo().setCompareSign(createCompareSignMsg());
            // 商家进行数据处理，并跳转会商家显示支付结果的页面
            // /首先进行签名字符串验证,验证成功进行取值判断是否支付成功
            getTrueUInfo().setCompareSign(createCompaerSign());
            if (getTrueUInfo().getCompareSign() != null
                    && getTrueUInfo().getSign() != null
                    && getTrueUInfo().getSign().toUpperCase().equals(
                            getTrueUInfo().getCompareSign().toUpperCase())) {
                    // /接着进行支付结果判断
                    if (Integer.parseInt(getTrueUInfo().getPayResult()) == 10) {
                        // 报告给真友处理结果，并提供将要重定向的地址。
                        proccessContractForPayOk(0);
                        getServletResponse().setCharacterEncoding("UTF-8");
                        //getServletResponse().getOutputStream().print("OK");
                        getServletResponse().getWriter().println("OK");
                        return "trueuReturn";
                    } else {
                       // getServletResponse().getWriter().println("ERROR");
                        return null;
                    }
                    // 以下报告给真友处理结果
             } else {
                 //getServletResponse().getWriter().println("ERROR");
                 logger.info("比较加密串错误！");
                 return null;
             }
        } catch (Exception e) {
            logger.error("真友回调pageUrlerror", e);
        }
        return "null";
    }

    /**
     * 真友回调函数 异步
     * 
     * @return
     */
    public String noticeUrl() {
        logger.info("真友回调noticeUrl");
        try {
            getTrueUReturnInfo();// 获得真友返回的信息
            // 生成签名sign加密方式按真友报顺序不能更改
            // getTrueUInfo().setCompareSign(createCompareSignMsg());
            // 商家进行数据处理，并跳转会商家显示支付结果的页面
            // /首先进行签名字符串验证,验证成功进行取值判断是否支付成功
            getTrueUInfo().setCompareSign(createCompaerSign());
            if (getTrueUInfo().getCompareSign() != null
                    && getTrueUInfo().getSign() != null
                    && getTrueUInfo().getSign().toUpperCase().equals(
                            getTrueUInfo().getCompareSign().toUpperCase())) {
                    // /接着进行支付结果判断
                    if (Integer.parseInt(getTrueUInfo().getPayResult()) == 10) {
                        // 报告给真友处理结果，并提供将要重定向的地址。
                        proccessContractForPayOk(1);
                        getServletResponse().setCharacterEncoding("UTF-8");
                        //getServletResponse().getOutputStream().print("OK");
                        getServletResponse().getWriter().println("OK");
                        return null;
                    } else {
                       // getServletResponse().getWriter().println("ERROR");
                        return null;
                    }
                    // 以下报告给真友处理结果
             } else {
                 //getServletResponse().getWriter().println("ERROR");
                 logger.info("比较加密串错误！");
                 return null;
             }
        } catch (Exception e) {
            logger.error("真友回调noticeUrlerror", e);
        }
        return "null";
    }

    /**
     * 获得真友返回的参数信息
     */
    private void getTrueUReturnInfo() {
        getTrueUInfo().setMerchantID(
                (String) getServletRequest().getParameter("merchantID").trim());// 合作商户IDString（20）合作商户ID
        // 网关版本String（5）固定值：1.0
        getTrueUInfo().setVersion(
                (String) getServletRequest().getParameter("version").trim());
        // 签名类型Number（2）固定值：1，代表MD5
        getTrueUInfo().setSignType(
                (String) getServletRequest().getParameter("signType").trim());
        // 商户订单号String（50）与提交订单时的商户订单号保持一致
        getTrueUInfo().setOrderId(
                (String) getServletRequest().getParameter("orderId").trim());
        // 商户订单金额Number（10）整型数字，以分为单位
        getTrueUInfo().setOrderAmount(
                (String) getServletRequest().getParameter("orderAmount").trim());
        // 商户订单时间String（14）与提交订单时的商户订单提交时间保持一致
        getTrueUInfo().setOrderTime(
                (String) getServletRequest().getParameter("orderTime").trim());
        // 真友交易号String（18）数字串，该交易在真友系统中对应的交易号
        getTrueUInfo().setDealId(
                (String) getServletRequest().getParameter("dealId").trim());
        // 真友交易时间String（14）位数字串
        getTrueUInfo().setDealTime(
                (String) getServletRequest().getParameter("dealTime").trim());
        // 贷款金额Number（10）用户贷款金额，单位分
        getTrueUInfo().setLoanAmount(
                (String) getServletRequest().getParameter("loanAmount").trim());
        // 真友手续费Number（10）收取商户的手续费，单位为分
        getTrueUInfo().setFee((String) getServletRequest().getParameter("fee").trim());
        // 扩展字段1
        getTrueUInfo().setExt1((String) getServletRequest().getParameter("ext1").trim());
        // 扩展字段2
        getTrueUInfo().setExt2((String) getServletRequest().getParameter("ext2").trim());
        // 处理结果支付成功；其他编号为支付失败错误代码
        getTrueUInfo().setPayResult(
                (String) getServletRequest().getParameter("payResult").trim());
        // 错误提示String（250）失败时返回的错误提示
        if (getServletRequest().getParameter("errMsg") != null) {
            getTrueUInfo().setErrMsg(
                    (String) getServletRequest().getParameter("errMsg").trim());
        }
        // 数字签名String（32）本列表所有参数，根据签
        getTrueUInfo().setSign((String) getServletRequest().getParameter("sign").trim());
    }

    /**
     * 支付成功，处理订单数据
     * 
     * @param callType
     *            0同步 1异步
     */
    private void proccessContractForPayOk(int callType) {
        // 支付成功，在这里写入数据处理,通过订单号及用户id查找订单记录
        Contract ct = getContractByParms(getCusIdByParm());// 根据用户ID和订单号查询
        // 说明正常在系统内查到订单
        if (ct != null) {
            Customer cus = customerService.getCustomerById(ct.getCusId());
            // 检查学员类型
            checkCusType(cus, ct);

            // 改变订单状态
            if (ct.getStatus() == 0) {
                ct.setStatus(Contract.CONTRACT_STATUS_PAIED);// 已付款
                ct.setPayTime(new Date());// 记录支付时间
                if (callType == 0) {
                    ct.setRemark("同步");//
                } else {
                    // 增加访问次数
                    ct.setCallSum(ct.getCallSum() + 1);
                    ct.setRemark("异步");//
                }
                ct.setPayType(Contract.CONTRACT_PAY_TYPE_TRUEU);// 真友支付
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
     * 系统目前是通过售卖方式来出售课程的。如：售卖方式A含1,2,3课程，售卖方式B含1,2,3,4,5,6课程，
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
               logger.error("TrueUAction.sendBuySuccMsgToMobile",e);
            }
        }
    }

    private String appendParam(String returnStr, String paramId, String paramValue) {
        if (returnStr != null && !returnStr.equals("")) {
            if (paramValue != null && !paramValue.equals("")) {
                returnStr = returnStr + "&" + paramId + "=" + paramValue;
            }
        } else {
            if (paramValue != null && !paramValue.equals("")) {
                returnStr = paramId + "=" + paramValue;
            }
        }
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
        //---Yangning 2011/12/21  支付成功时同时更新流水支付类型--- begin // 
        upcashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_TRUEU);
        this.getCashRecordService().upateCashRecordForOnlinePay(upcashRecord);
      //---Yangning 2011/12/21  支付成功时同时更新流水支付类型--- end //
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
        if (getTrueUInfo().getExt1() != null
                && !("").equals(getTrueUInfo().getExt1().trim())) {
            try {
                return Integer.valueOf(getTrueUInfo().getExt1());
            } catch (Exception e) {
                logger.error("获得真友返回用户ID错误！", e);
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

    public TrueUInfo getTrueUInfo() {
        return trueUInfo;
    }

    public void setTrueUInfo(TrueUInfo TrueUInfo) {
        this.trueUInfo = TrueUInfo;
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
    	if(queryCashRecordCondition ==null){
    		queryCashRecordCondition = new QueryCashRecordCondition();
    	}
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

    public String getTrueUMD5(byte[] strTemp) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
                'c', 'd', 'e', 'f' };
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
