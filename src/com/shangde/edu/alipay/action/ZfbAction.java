package com.shangde.edu.alipay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.alipay.config.AlipayConfig;
import com.alipay.util.CheckURL;
import com.alipay.util.Payment;
import com.alipay.util.UtilDate;
import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.GetHttpMessage;
import com.shangde.common.util.Result;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryTemplateCondition;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cou.service.IGmrecord;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICusProtocalCash;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.domain.Coupon;
import com.shangde.edu.finance.domain.Coupontype;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.finance.task.CrmSendMessageTask;
import com.shangde.edu.finance.task.ProtocalContractTask;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sys.domain.User;

/**
 * @author liuqinggang
 * @version 1.0
 */
public class ZfbAction extends CommonAction {

	
    private static final long serialVersionUID = 1L;
    /*
     * 支付宝跳转路径
     */
    private String zfbUrl;
    /*
     * 支付原价总价
     */
	private String get_total_fee;
	/*
	 * 购买项目名称
	 */
	private String get_subject;
	/*
	 * 订单说明
	 */
	private String get_body;
	/*
	 * 实际支付价格
	 */
	private String totalPrice;
	/*
	 * 购物车信息，存在cookie中
	 */
	private String courseOrder;
	/*
	 * 咱未用（未知）
	 */
	private String isUse;
	/*
     * 咱未用（未知）
     */
	private String cPrice;
	/*
     * 咱未用（未知）
     */
	private String cpId;
	/*
     * 咱未用（未知）
     */
	private String serialNumber;

	/**
	 * 支付类型
	 */
	private int payType;

	/**
	 * 应付价格
	 */
	private String paymoney;
	/**
	 * 订单号
	 */
	private String makeoders;
	/**
	 * 声名订单的PO对象
	 */
	private Contract contract = new Contract();
	/**
	 * 声明订单服务
	 */
	private IContract contractService;

	/**
	 * 声明流水服务
	 */
	private ICashRecord cashRecordService;
	/**
	 * 亿起发返回订单服务
	 */
	private GetHttpMessage getMsg;
	/**
	 * 课程
	 */
	private Course course;

	/**
	 * 课程服务
	 */
	private ICourse courseService;

	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;

	/**
	 * 购买记录服务
	 */
	private IGmrecord gmrecordService;
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	/**
	 * 服务（已经废弃）
	 */
	private ICusCouKpoint cusCouKpointService;
	/**
	 * 积分服务
	 */
	private ITotolsScore totolsScoreService;
	/**
	 * 服务
	 */
	private ITsRecord tsRecordService;
	/**
	 * 服务
	 */
	private ICpCus cpCusService;
	/**
	 * 声名logger
	 */
	private Logger logger = Logger.getLogger(ZfbAction.class);
	/**
	 * 订单号
	 */
	private String get_order;
	/**
	 * 总金额
	 */
	private String total_fee;
	/**
	 * 默认银行
	 * 
	 */
	private String defaultbank_1;
	/**
	 * 默认银行
	 */
	private String paymethod_1;
	/**
	 * 消息
	 */
	private IUserMsg userMsgService;
	/**
	 * 消息
	 */
	private IMessage messageService;
    /**
     * 用户服务
     */
	private ICustomer customerService;
	/**
	 *发送短信接口 
	 */
	private IsmsService smsService;

	/**
	 * 头部模板内容
	 */
	private String headerHTML = "";

	/**
	 * 尾部模板内容
	 */
	private String footerHTML = "";

	/**
	 * 模板服务对象
	 */
	private ITemplate templateService;
	/**
	 * 售卖方式关联课程服务
	 */
	private ISellCou sellCouService;
	/**
	 * 售卖方式查询类
	 */
	private QuerySellCouCondition querySellCouCondition;
	/**
	 * 售卖方式服务
	 */
	private ISellWay sellWayService;
	/**
	 * 流水条件查询
	 */
	private QueryCashRecordCondition queryCashRecordCondition;

	/**
	 * 售卖方式实体
	 */
	private SellWay sWay;
	/**
	 * 流水实体
	 */
	private CashRecord cashRecord;

	// 优惠编码
	private String couponCode;

	// 项目id
	private String subjectId;

	// 售卖方式id
	private String sellId;

	// 优惠券id
	private String couponId;
	// 售卖方式 列表id
	private String sellIds = "";
	/**
	 * 配置文件
	 */
	private ConfigService configurator;
	
	/**保过协议售卖方式Id**/
	private String sellids;
	
	/**用户填写保过协议ID**/
	private int cusDetialId;
	
	/**用户保过协议流水关联**/
	private ICusProtocalCash  cusProtocalCashService;
	
	/**线程池对象,用户线程操作,提高效率,防止任务过多服务器压力过大**/
	private TaskExecutor taskExecutor;
	
	/**流水ID与授卖方式关联容器,为方便保过协议写入**/
	private List<Map> cashSellMap = new ArrayList<Map>();

	
	/**
	 * 跳转到支付页面
	 * @return
	 */
	public String toIndex() {
		AlipayConfig ac = new AlipayConfig();
		return "toIndex";
	}

	/**
	 * 付款后点返回支付网站，初始化知识点 同步
	 * 
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	public String returnUrl() throws UnsupportedEncodingException {
	    logger.info("支付宝同步回调returnUrl");
	    try {
    		int callSum = 1;
    		String partner = AlipayConfig.partnerID; // 支付宝合作伙伴id (账户内提取)
    		String privateKey = AlipayConfig.key; // 支付宝安全校验码(账户内提取)
    		// **********************************************************************************
    		// 如果您服务器不支持https交互，可以使用http的验证查询地址
    		/*
    		 * 注意下面的注释，如果在测试的时候导致response等于空值的情况，请将下面一个注释，打开上面一个验证连接，另外检查本地端口，
    		 * 请挡开80或者443端口
    		 */
    		// String alipayNotifyURL =
    		// "https://www.alipay.com/cooperate/gateway.do?service=notify_verify"
    		String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
    				+ "partner="
    				+ partner
    				+ "&notify_id="
    				+ servletRequest.getParameter("notify_id");
    		// **********************************************************************************
    		// 获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
    		String responseTxt = CheckURL.check(alipayNotifyURL);
    		Map params = new HashMap();
    		// 获得POST 过来参数设置到新的params中
    		Map requestParams = servletRequest.getParameterMap();
    		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
    			String name = (String) iter.next();
    			String[] values = (String[]) requestParams.get(name);
    			String valueStr = "";
    			for (int i = 0; i < values.length; i++) {
    				valueStr = (i == values.length - 1) ? valueStr + values[i]
    						: valueStr + values[i] + ",";
    			}
    			/*
    			 * 乱码解决，这段代码在出现乱码时使用,但是不一定能解决所有的问题，所以建议写过滤器实现编码控制。
    			 * 如果mysign和sign不相等也可以使用这段代码转化
    			 */
    			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8"); // 乱码解决
    			params.put(name, valueStr);
    		}
    		String mysign = com.alipay.util.SignatureHelper
    				.sign(params, privateKey);
    		// 最好是在异步做日志动作，可以记录mysign、sign、resposenTXT和其他值，方便以后查询错误。
    		if (mysign.equals(servletRequest.getParameter("sign"))
    				&& responseTxt.equals("true")) {
    			/* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
    			// 以下输出测试时候用，可以删除
    			Date date = new Date();
    			// 订单编号
    			get_order = servletRequest.getParameter("out_trade_no");
    			// 总价
    			get_total_fee = servletRequest.getParameter("total_fee");
    			// 订单名称
    			get_subject = new String(servletRequest.getParameter("subject")
    					.getBytes("ISO-8859-1"), "UTF-8");
    			// 订单说明
    			get_body = new String(servletRequest.getParameter("body"));
    			get_body = new String(servletRequest.getParameter("body").getBytes(
    					"ISO-8859-1"), "UTF-8");
    			if (servletRequest.getParameter("trade_status").equals(
    					"WAIT_BUYER_PAY")) {
    				// 等待买家付款（暂未用）
    			} else if (servletRequest.getParameter("trade_status").equals(
    					"TRADE_FINISHED")
    					|| servletRequest.getParameter("trade_status").equals(
    							"TRADE_SUCCESS")) {
    				int cusId = 0;
    				// 在这里可以写入数据处理,
    				// 通过订单号查找订单记录
    				QueryContractCondition qcc = new QueryContractCondition();
    				qcc.setContractId(get_order);
    
    				String subjectStr = get_subject;
    				if (subjectStr != null && !subjectStr.trim().equals("")
    						&& subjectStr.split(",").length > 1) {
    					try {
    						qcc.setUserId(Integer.valueOf(subjectStr.split(",")[1]));
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
    				}
    				PageResult ctList = this.contractService.getContractList(qcc);
    				Contract ct = new Contract();
    				// 说明正常在系统内查到订单
    				if (ctList != null) {
    					ct = (Contract) ctList.getPageResult().get(0);
    
    					// 如果是临时学员或内部体验账号，那么删除他的临时信息，并置状态为注册学员
    					Customer tempCus = this.customerService.getCustomerById(ct
    							.getCusId());
    					if (tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP
    							|| tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP
    							|| tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP_MONTH) {
    						customerService.recoverTempCustomer(ct.getCusId(),
    								ct.getId());
    						tempCus.setCusType(Customer.CUS_CUS_TYPE_REGISTER);
    						customerService.updateCustomer(tempCus);
    					}
    
    					// 得到cusID
    					cusId = ct.getCusId();
    					// 增加访问次数
    					int newCallSum = ct.getCallSum();
    					callSum = callSum + newCallSum;// 访问次数
    					ct.setCallSum(callSum);
    					this.contractService.updateContract(ct);
    					// 改变订单状态
    					if (ct.getStatus() == 0) {
    						ct.setStatus(1);// 已付款
    						ct.setPayTime(date);// 记录支付时间
    						ct.setRemark("同步");// 记录同步
    						ct.setPayType(Contract.CONTRACT_PAY_TYPE_ZFB);
    						this.contractService.updateContract(ct);
    						// 通过订单id查询流水记录
    //						QueryCashRecordCondition qcrc = new QueryCashRecordCondition();
    //						// qcrc.setContractId(ct.getContractId());
    //						qcrc.setCtId(ct.getId());
    //						List<CashRecord> crList = this.cashRecordService
    //								.getCashRecordByList(qcrc);// 流水记录
    //						// 修改课程体系后，改变流水状态，与订单同步
    						CashRecord upashRecord = new CashRecord();
                            upashRecord.setCusId(cusId);
                            upashRecord.setCtId(ct.getId());
                            upashRecord.setStatus(1);
                            upashRecord.setShopStatus(1);
                            //----Yangning 修改 2011/12/21 支付成功后更新流水 paytype 类型   开始------//
                            upashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_ZFB);
    						this.getCashRecordService().upateCashRecordForOnlinePay(upashRecord);
    						 //----Yangning 修改 2011/12/21 支付成功后更新流水 paytype 类型   结束------//
    						logger.info("流水回调时信息CusId："+upashRecord.getCusId()+" CtId:"+upashRecord.getCtId());
    //						this.getCashRecordService().updateCashReocrdStatus(
    //								crList);
    						// 如果订单是升级课程的订单则把以前的订单流水状态改为无效
    						upOld(ct);
    						// 发送购买成功消息
    						Message msg = messageService.getMessageByKey("buy");
    						if (msg != null && msg.getMsgId() > 0) {
    							User sender = new User();
    							sender.setUserId(1);
    							sender.setUserName("超级管理员");
    							int userId = this.getLoginUserId();
    							Customer cus = customerService
    									.getCustomerById(userId);
    							if (cus != null) {
    								userMsgService.adminerSendMsgToCutomer(sender,
    										msg.getMsgId(), cus);
    							}
    						}
    						// 支付成功发送短信给客户
    						Customer cusTemp = this.customerService
    								.getCustomerById(new Integer(cusId));
    						if (cusTemp.getMobile() != null
    								&& !"".equals(cusTemp.getMobile().trim())) {
    							try {
    								this.smsService
    										.sendEx("【嗨学网】您已支付成功，进入[我的highso]，点击我的课程，开始学习吧！",
    												cusTemp.getMobile(), "", "", "");
    							} catch (Exception e) {
    								logger.error("发送支付成功手机短信错误！",e);
    							}
    						}
    						
    						/**
    						 * 
    						 * 杨宁 2012/04/25 加入此处，向CRM推送消息
    						 * 
    						 * **/
    						try{
    							taskExecutor.execute(new CrmSendMessageTask(ct));
    						}catch(Exception e){
    							logger.error("ZFBAction.taskExecutor.CrmSendMessageTask",e);
    						}
    					}
    					// 谢添加修改支付状态
    					if (ct.getUseCoupon() == Contract.CONTRACT_USE_COUPON_YES
    							&& ct.getStatus() == 1) {
    						try {
    							String couponId = sellWayService
    									.getContractForCouponIdById(String
    											.valueOf(ct.getId()));
    							if (couponId != null && !couponId.equals("")) {
    								int couponTypeId = sellWayService
    										.GetParentIdBycouponId(couponId);
    								sellWayService.updateCouponForPayState(
    										couponId, couponTypeId);
    							}
    						} catch (Exception e) {
    							logger.error("ZfbAction.returnUrl",e);
    						}
    					}
    					// 谢添加结束
    				}
    				getOut().println("success");// 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
    				// System.out.println("return " +mysign + "--------------------"
    				// + servletRequest.getParameter("sign") + "<br>");
    			}
    		} else {
    			getOut().println("fail");
    			// 打印，收到消息比对sign的计算结果和传递来的sign是否匹配
    			// System.out.println("return " +mysign + "--------------------"
    			// + servletRequest.getParameter("sign") + "<br>");
    		}
    		processTempContent();
	    } catch (Exception e) {    
           logger.error("支付宝同步回调returnUrl错误",e);
        }
		return "zfbReturn";
	}

	/**
	 * 获取前台静态模板
	 */
	private void processTempContent() {
		try {
			QueryTemplateCondition queryTemplateCondition = new QueryTemplateCondition();
			queryTemplateCondition.setTmpName("web_header_org");
			List<Template> tempList = templateService
					.getTemplateList(queryTemplateCondition);
			if (tempList != null && tempList.size() > 0) {
				headerHTML = templateService.processTag(tempList.get(0)
						.getTmpContent(), null);
			}

			queryTemplateCondition.setTmpName("web_footer_org");
			tempList = templateService.getTemplateList(queryTemplateCondition);
			if (tempList != null && tempList.size() > 0) {
				footerHTML = templateService.processTag(tempList.get(0)
						.getTmpContent(), null);
			}
		} catch (Exception e) {
			logger.error("ZfbAction.processTempContent",e);
		}
	}

	/**
	 * 异步，初始化课程
	 * 
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */

	public String notifyUrl() throws UnsupportedEncodingException {
	    logger.info("支付宝异步回调notifyUrl");
	    try {
            
        
		String partner = AlipayConfig.partnerID; // 支付宝合作伙伴id (账户内提取)
		String privateKey = AlipayConfig.key; // 支付宝安全校验码(账户内提取)
		// **********************************************************************************
		// 如果您服务器不支持https交互，可以使用http的验证查询地址
		/*
		 * 注意下面的注释，如果在测试的时候导致response等于空值的情况，请将下面一个注释，打开上面一个验证连接，另外检查本地端口，
		 * 请挡开80或者443端口
		 */
		// String alipayNotifyURL =
		// "https://www.alipay.com/cooperate/gateway.do?service=notify_verify"
		String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
				+ "partner="
				+ partner
				+ "&notify_id="
				+ servletRequest.getParameter("notify_id");
		// **********************************************************************************
		// 获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
		String responseTxt = CheckURL.check(alipayNotifyURL);
		Map params = new HashMap();
		// 获得POST 过来参数设置到新的params中
		Map requestParams = servletRequest.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			/*
			 * 乱码解决，这段代码在出现乱码时使用,但是不一定能解决所有的问题，所以建议写过滤器实现编码控制。
			 * 如果mysign和sign不相等也可以使用这段代码转化
			 */
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8"); // 乱码解决
			params.put(name, valueStr);
		}
		String mysign = com.alipay.util.SignatureHelper
				.sign(params, privateKey);
		// 最好是在异步做日志动作，可以记录mysign、sign、resposenTXT和其他值，方便以后查询错误。
		if (mysign.equals(servletRequest.getParameter("sign"))
				&& responseTxt.equals("true")) {
			/* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
			Date date = new Date();
			// 订单编号
			get_order = servletRequest.getParameter("out_trade_no");
			// 总价
			get_total_fee = servletRequest.getParameter("total_fee");
			// 订单名称
			get_subject = new String(servletRequest.getParameter("subject")
					.getBytes("ISO-8859-1"), "UTF-8");
			// 订单说明
			get_body = new String(servletRequest.getParameter("body"));
			get_body = new String(servletRequest.getParameter("body").getBytes(
					"ISO-8859-1"), "UTF-8");

			if (servletRequest.getParameter("trade_status").equals(
					"WAIT_BUYER_PAY")) {
				// 等待买家付款（暂未用）
				getOut().println("success"); // 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
			} else if (servletRequest.getParameter("trade_status").equals(
					"TRADE_FINISHED")
					|| servletRequest.getParameter("trade_status").equals(
							"TRADE_SUCCESS")) {
				// 在这里可以写入数据处理,
				// 通过订单号查找订单记录
				QueryContractCondition qcc = new QueryContractCondition();
				qcc.setContractId(get_order);

				String subjectStr = get_subject;
				if (subjectStr != null && !subjectStr.trim().equals("")
						&& subjectStr.split(",").length > 1) {
					try {
						qcc.setUserId(Integer.valueOf(subjectStr.split(",")[1]));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				PageResult ctList = contractService.getContractList(qcc);
				Contract ct = new Contract();
				// 说明正常在系统内查到订单
				if (ctList != null) {
					ct = (Contract) ctList.getPageResult().get(0);
					// 得到cusID
					int cusId = ct.getCusId();

					// 如果是临时学员或内部体验账号，那么删除他的临时信息，并置状态为注册学员
					Customer tempCus = this.customerService.getCustomerById(ct
							.getCusId());
					if (tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP
							|| tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP
							|| tempCus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP_MONTH) {
						customerService.recoverTempCustomer(ct.getCusId(),
								ct.getId());
						tempCus.setCusType(Customer.CUS_CUS_TYPE_REGISTER);
						customerService.updateCustomer(tempCus);
					}

					// 增加访问次数
					ct.setCallSum(ct.getCallSum() + 1);
					contractService.updateContract(ct);
					// 改变订单状态
					if (ct.getStatus() == 0) {
						ct.setStatus(1);// 已付款
						ct.setPayTime(date);// 记录支付时间
						ct.setRemark("异步");// 记录异步
						ct.setPayType(Contract.CONTRACT_PAY_TYPE_ZFB);
						contractService.updateContract(ct);
						// 通过订单id查询流水记录
						QueryCashRecordCondition qcrc = new QueryCashRecordCondition();
						// qcrc.setContractId(ct.getContractId());
						qcrc.setCtId(ct.getId());
						List<CashRecord> crList = this.cashRecordService
								.getCashRecordByList(qcrc);
						// 在支付成功后修改流水状态,与订单同步
						CashRecord upcashRecord = new CashRecord();
						upcashRecord.setCusId(cusId);
						upcashRecord.setCtId(ct.getId());
						upcashRecord.setStatus(1);
						upcashRecord.setShopStatus(1);
						
						 //----Yangning 修改 2011/12/21 支付成功后更新流水 paytype 类型   开始------//
						upcashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_ZFB);
						this.getCashRecordService().upateCashRecordForOnlinePay(upcashRecord);
						//----Yangning 修改 2011/12/21 支付成功后更新流水 paytype 类型   结束------//
						
//						this.getCashRecordService().updateCashReocrdStatus(
//								crList);
						// 如果订单是升级课程的订单则把以前的订单流水状态改为无效
						upOld(ct);
						// 发送购买成功消息
						Message msg = messageService.getMessageByKey("buy");
						if (msg != null && msg.getMsgId() > 0) {
							User sender = new User();
							sender.setUserId(1);
							sender.setUserName("超级管理员");
							int userId = this.getLoginUserId();
							Customer cus = customerService
									.getCustomerById(userId);
							if (cus != null) {
								userMsgService.adminerSendMsgToCutomer(sender,
										msg.getMsgId(), cus);
							}
						}
						// 支付成功发送短信给客户
						Customer cusTemp = this.customerService
								.getCustomerById(new Integer(cusId));
						if (cusTemp.getMobile() != null
								&& !"".equals(cusTemp.getMobile().trim())) {
							try {
								this.smsService
										.sendEx("【嗨学网】您已支付成功，进入[我的highso]，点击我的课程，开始学习吧！",
												cusTemp.getMobile(), "", "", "");
							} catch (Exception e) {
								logger.error("ZfbAction.notifyUrl",e);
							}
						}
						getOut().println("success");// 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
						
						/**
						 * 
						 * 杨宁 2012/04/25 加入此处，向CRM推送消息
						 * 
						 * **/
						try{
							taskExecutor.execute(new CrmSendMessageTask(ct));
						}catch(Exception e){
							logger.error("ZFBAction.taskExecutor.CrmSendMessageTask",e);
						}
					}
					// 谢添加修改支付状态
					if (ct.getUseCoupon() == Contract.CONTRACT_USE_COUPON_YES
							&& ct.getStatus() != 1) {
						String couponId = sellWayService
								.getContractForCouponIdById(String.valueOf(ct
										.getId()));
						if (couponId != null && !couponId.equals("")) {
							int couponTypeId = sellWayService
									.GetParentIdBycouponId(couponId);
							sellWayService.updateCouponForPayState(couponId,
									couponTypeId);
						}
					}
					// 谢添加结束

				}
			}
		} else {
			getOut().println("fail");
		}
		processTempContent();
	    } catch (Exception e) {
            logger.error("支付宝异步回调错误！：",e);
        }
		return null;
	}
	/**
	 * 跳转到支付宝前处理
	 * @return
	 * @throws java.io.IOException
	 */
	public String goToZFB() {
		try{
			// String courseOrder =
			// CookieHandler.getCookieValueByName(servletRequest, "courseOrder");
			// courseOrder =java.net.URLDecoder.decode(courseOrder,"utf-8").trim();
			// String goodBody = String.valueOf(courseOrder);
			// total_fee = String.valueOf(0.01); //订单总价
			String paygateway = "https://www.alipay.com/cooperate/gateway.do?"; // 支付接口（不可以修改）
			String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
			String sign_type = "MD5";// 文件加密机制（不可以修改）
			String out_trade_no = get_order.trim();// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
			String input_charset = AlipayConfig.CharSet; // （不可以修改）
			// partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
			String partner = AlipayConfig.partnerID; // 支付宝合作伙伴id (账户内提取)
			String key = AlipayConfig.key; // 支付宝安全校验码(账户内提取)
			String body = "shangdeEdu".trim();// 商品描述，推荐格式：商品名称（订单编号：订单编号）
			//contractService.getContract(new QueryContractCondition().setCid(get_order))
			total_fee = getFeeByContracIdAndCusId(String.valueOf(out_trade_no)); // 订单总价
			//total_fee = String.valueOf(total_fee); // 订单总价
			String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
			String seller_email = AlipayConfig.sellerEmail; // 卖家支付宝帐户,例如：gwl25@126.com
			String subject = "highso".trim() + out_trade_no + ","
					+ getLoginUserId(); // 商品名称
			String show_url = configurator.getProjectURL() + "/".trim(); // 根据集成的网站而定
			// 例如：http://wow.alipay.com
			String path = configurator.getProjectURL() + "/";// "http://190.10.2.33:7001/";
			// String show_url = "http://localhost:8080/sedu/".trim(); //根据集成的网站而定
			// 例如：http://wow.alipay.com
			// String path ="http://localhost:8080/sedu/";//
			// "http://190.10.2.33:7001/";
			String notify_url = path + "alipay/zfb!notifyUrl.action"; // 通知接收URL(本地测试时，服务器返回无法测试)
			String return_url = path + "alipay/zfb!returnUrl.action"; // 支付完成后跳转返回的网址URL
			// 注意以上两个地址 要用 http://格式的完整路径
			/* 以下两个参数paymethod和defaultbank可以选择不使用，如果不使用需要注销，并在Payment类的方法中也要注销 */
			String paymethod = "";
			if ("directPay".equals(defaultbank_1)) {
				paymethod = "directPay".trim();
			} else {
				paymethod = "bankPay".trim();// 赋值:bankPay(网银);cartoon(卡通);
												// directPay(余额)
			}
			if (StringUtil.isNullString(defaultbank_1)) {
				defaultbank_1 = "CMB";
			}
			String defaultbank = defaultbank_1.trim();
			// ICBCB2C 中国工商银行
			// CMB 招商银行
			// CCB 中国建设银行
			// ABC 中国农业银行
			// SPDB 上海浦东发展银行
			// SPDBB2B 上海浦东发展银行(B2B)
			// CIB 兴业银行
			// GDB 广东发展银行
			// SDB 深圳发展银行
			// CMBC 中国民生银行
			// COMM 交通银行
			// POSTGC 邮政储蓄银行
			// CITIC 中信银行
			// CCBVISA 建行VISA
			// VISA VISA
			zfbUrl = Payment.CreateUrl(paygateway, service, sign_type,
					out_trade_no, input_charset, partner, key, show_url, body,
					total_fee, payment_type, seller_email, subject, notify_url,
					return_url, paymethod, defaultbank);
		}catch(Exception e){
			logger.error("ZfbAction.goToZFB", e);
			return ERROR;
		}
		return "goToZFB";
	}
	
	/**
	 * 通过订单查找应付总金额
	 * @param cid
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-21
	 */
	private String getFeeByContracIdAndCusId(String cid){
		Map map = new HashMap();
		map.put("cusId", getLoginUserId());
		map.put("cid", cid);
		Contract contract = contractService.getContractByCidAndCusId(map);
		if(contract == null){
			throw new RuntimeException("订单不可为空");	
		}
		return contract.getContractCutSumMoney().toString();
	}
	
	/**
	 * 生成订单
	 * @return String
	 */
	@SuppressWarnings("static-access")
	public String makeContract() {

		if (isMakeContract()) {
			try {
				Date date = new Date();
				get_order = getLoginUserId() + new UtilDate().getOrderNum(); // 调取支付宝工具类生成订单号
				courseOrder = URLDecoder.decode(
						CookieHandler.getCookieValueByName(servletRequest,
								"courseOrder"), "utf-8").trim();// 存值形式[0]课程id,[1]每一本书的价格,[2]包id
				float youhuijuan = 0;
				String src = null;
				String wi = null;
				String strCid = null;
				Integer cid = null;
				if (!StringUtils.isEmpty(CookieHandler.getCookieValueByName(
						servletRequest, "SRC"))) {
					src = CookieHandler.getCookieValueByName(servletRequest,
							"SRC");
				}
				if (!StringUtils.isEmpty(CookieHandler.getCookieValueByName(
						servletRequest, "WI"))) {
					wi = CookieHandler.getCookieValueByName(servletRequest,
							"WI");
				}
				if (!StringUtils.isEmpty(CookieHandler.getCookieValueByName(
						servletRequest, "CID"))) {
					strCid = CookieHandler.getCookieValueByName(servletRequest,
							"CID");
				}

				if (courseOrder != null && !courseOrder.trim().equals("")) {
					// 记录到订单表中
					Contract newcontract = new Contract();

					newcontract.setContractFromUrl(servletRequest
							.getServerName());
					newcontract.setContractId(get_order);
					newcontract.setCusId(getLoginUserId());
					newcontract.setCreateTime(date);
					newcontract.setContractFrom(Contract.CONTRACT_FROM);

					if (payType == Contract.CONTRACT_PAY_TYPE_ZFB) {
						newcontract.setPayType(Contract.CONTRACT_PAY_TYPE_ZFB);// 支付宝
					} else if (payType == Contract.CONTRACT_PAY_TYPE_CB) {
						newcontract.setPayType(Contract.CONTRACT_PAY_TYPE_CB);// 网银在线
					}else if (payType == Contract.CONTRACT_PAY_TYPE_TRUEU){
					    newcontract.setPayType(Contract.CONTRACT_PAY_TYPE_TRUEU);// 真友信用
					}else if(payType == Contract.CONTRACT_PAY_TYPE_YL){
						newcontract.setPayType(Contract.CONTRACT_PAY_TYPE_YL);//银联在线
					}else if(payType == Contract.CONTRACT_PAY_TYPE_YL_VOICE){
						newcontract.setPayType(Contract.CONTRACT_PAY_TYPE_YL_VOICE);//易联语音
					}
					else{
						newcontract.setPayType(Contract.CONTRACT_PAY_TYPE_KQ);// 快钱支付
					}
					newcontract.setStatus(Contract.CONTRACT_STATUS_NOT_PAY);// 0代表未付款
																			// 1代表已付款
																			// 2后台免费赠送
					newcontract.setFreight(0);// 运费网上支付为0
					newcontract.setRelief(0);// 特殊减免默认为0
					int ctId = contractService.addContract(newcontract);
					float totalPrice = saveCashRecords(ctId);
					float totalPriceBefore=totalPrice;
					DecimalFormat df = new DecimalFormat("#.00 ");
					Coupon coupon=null;
					newcontract.setUseCoupon(Contract.CONTRACT_USE_COUPON_NO);
					if (couponCode != null && !couponCode.equals("0") && !couponCode.equals(""))// 优惠卷面值// 谢修改些句
					{
						try{
						 coupon = sellCouService.GetCouponByCode(couponCode);
						Coupontype couponType = sellCouService.getCouponTypeByCouponId(coupon.getId());
						float zhe = Float.parseFloat(couponType.getPreferentialPrice());
						if (couponType.getCType() == 1) { //如果是打折
							totalPrice = totalPrice * (zhe * 10 / 100);
							youhuijuan=totalPriceBefore-totalPrice;
						}
						if (couponType.getCType() == 2) {//如果是立减
							totalPrice = totalPrice - zhe;
							youhuijuan=zhe;
						}
						if( totalPrice==0)
						{
							totalPrice=totalPriceBefore;
							SimpleDateFormat matter = new SimpleDateFormat(
					                "现在时间:yyyy年MM月dd日E HH时mm分ss秒");
							logger.info(matter.format(new Date())+" 优惠券id是: "+couponCode+"  订单id是："+ctId+" 总金额是"+totalPriceBefore);
						}
						totalPrice = Float.parseFloat(df.format(totalPrice));
						youhuijuan = Float.parseFloat(df.format(youhuijuan));
						int couponTypeId = sellCouService
								.GetParentIdBycouponId(String.valueOf(coupon.getId()));// 谢添加
						sellCouService.updateCouponForState(String.valueOf(coupon.getId()),
								couponTypeId);// 谢添加更改优惠券的状态
						newcontract
						.setUseCoupon(Contract.CONTRACT_USE_COUPON_YES);
						newcontract.setCouponId(coupon.getId());
						}catch(Exception e)
						{
							SimpleDateFormat matter = new SimpleDateFormat(
					                "现在时间:yyyy年MM月dd日E HH时mm分ss秒");
							logger.error(matter.format(new Date())+" 订单id是："+ctId+" 总金额是"+totalPriceBefore);
						}
					}
					newcontract.setContractCutSumMoney(totalPrice);// 优惠后总金额
					newcontract.setContractSumMoney(totalPriceBefore);// 优惠前总金额
					newcontract.setCouponMoney(youhuijuan);// 优惠额

					String webFrom = CookieHandler.getCookieValueByName(
							servletRequest, "webFrom");
					String webAgent = CookieHandler.getCookieValueByName(
							servletRequest, "webAgent");
					if (webFrom != null && !webFrom.trim().equals("")
							&& !webFrom.trim().equals("null")) {
						newcontract.setWebFrom(webFrom);
					}

					if (webAgent != null && !webAgent.trim().equals("")
							&& !webAgent.trim().equals("null")) {
						newcontract.setWebAgent(webAgent);
					}
					
					
					/**
					 * 查询Cookies是否有亿起发的信息，有则添加到订单中
					 */
					if (null != src && !"".equals(src)) {
						newcontract.setSrc(src);
						if (null != wi && !"".equals(wi)) {
							newcontract.setWi(wi);
						}
						if (null != strCid && !"".equals(strCid.trim())) {
							cid = Integer.parseInt(strCid);
							newcontract.setCid(cid);
						}
						// 给亿起发返订单数据
						SimpleDateFormat dateFm = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String dateTime = dateFm.format(date);
						String url = "cid=" + cid + "&wi=" + wi + "&on="
								+ get_order + "&ta=1&pp=" + totalPrice + "&sd="
								+ dateTime + "&ct=0&pn="+get_order+"&encoding=utf-8";
						String flag = this.getGetMsg().getHttpMessageFromGet(
								url);
						if ("0".equals(flag)) {
							logger.info("成功发送亿起发订单数据");
						} else if ("1".equals(flag)) {
							logger.info("缺少必要参数url:"+url);
						} else if ("2".equals(flag)) {
							logger.info("参数格式错误url:"+url);
						}
					}

					/**
					 * 为第三方网站进行订单推送 add by caowei 2011-07-18
					 */
					try {
						String sendData = "";
						int siteCode = 0;
						if (webFrom != null && !webFrom.trim().equals("")
								&& !webFrom.trim().equals("null")) {
							if (webFrom.trim().equals("51fanli")) {
								siteCode = 1;
							}
						}
						if (webAgent != null && !webAgent.trim().equals("")
								&& !webAgent.trim().equals("null")
								&& siteCode != 0) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							String sendDate = sdf.format(date);
							HashMap detailMap = (HashMap) this
									.getContractDetail(courseOrder);
							String pks = (String) detailMap.get("pks");// 售卖方式的字符串
							String prices = (String) detailMap.get("prices");// 售卖方式价格的字符串
							String pksNum = (String) detailMap.get("pksNum");// 售卖方式的个数
							String comm = (String) detailMap.get("comm");// 佣金
							if (siteCode == 1) {// 返利网
								String m_id = "highso";// 由返利网提供
								String k = "123456";// 由返利网提供
								sendData = "otime=" + sendDate + "&o_cd="
										+ get_order + "&m_id=" + m_id + "&k="
										+ k + "&u_id=" + webAgent + "&p_cd="
										+ pks + "&c_cd=" + comm + "&it_cnt="
										+ pksNum + "&price=" + prices
										+ "&comm=" + comm + "&username=abcd";
								logger.info(sendData);
							}
							String res = this.getGetMsg()
									.sendContractToOtherSiteFromGet(siteCode,
											sendData.trim());
							if ("0".equals(res)) {
								logger.info("成功发送数据到第三方站点");
							} else if ("1".equals(res)) {
								logger.info("缺少必要参数");
							} else if ("2".equals(res)) {
								logger.info("参数格式错误");
							}
						}
					} catch (Exception e) {
						logger.info("推送订单异常！"+e.toString());
					}

					// 对应网盟过来的订单
					String wmFlag = "wm";
					if (null != webAgent && !webAgent.trim().equals("")
							&& !webAgent.trim().equals("null")
							&& webAgent.trim().equals(wmFlag)) {
						newcontract.setWebAgent(webAgent);
						if (webFrom != null && !webFrom.trim().equals("")
								&& !webFrom.trim().equals("null")) {
							newcontract.setWebFrom(webFrom);
						}
					}
					
					/**
					 * Yaning  查询是否为中视网盟订单如是则把cookie写入库中
					 * 
					 */
					try{
					if (webFrom != null && !webFrom.trim().equals("")
							&& !webFrom.trim().equals("null")) {
						if(webFrom.equals("zswm")){
							String zswmInfo = CookieHandler.getCookieValueByName(servletRequest,"ZSWMINFO");
							if(zswmInfo != null && zswmInfo.trim().length() > 0 && !zswmInfo.equals("null")){
								//cookie 编码|替换
								String codeZswmInfo = zswmInfo.replaceAll("%7C", "|");
								newcontract.setSrc(codeZswmInfo);
							}
						}
					}
					}catch(Exception e){
						logger.error("makeContract zswm",e);
						newcontract.setSrc("");
					}
					// 谢添加
					
						
					// 谢添加
					contractService.updateContract(newcontract);
					clearCookieContractInfo();
					// 存json中，订单号，和，应付金额
					setResult(new Result(true, get_order + ","+ totalPrice, null, null));
					logger.info("前台/用户中心/我的订单/" + "用户：" + getLoginUserId()
							+ "订单号：" + get_order + "订单来源:前台用户　下单时间：" + date
							+ "订单状态：等待付款/成功");
					// 在优惠券中添加订单 谢添加开始
					if (newcontract.getUseCoupon() == Contract.CONTRACT_USE_COUPON_YES && coupon!=null) {
						Map map = new HashMap();
						map.put("contarctId", newcontract.getId());
						couponId = coupon.getId().toString();
						map.put("couponId", couponId);
						sellCouService.updateCouponForContractId(map);
						sellCouService.updateCouponForPayState(couponId, coupon.getParentId());
					}
					
					/**
					 * 
					 * 杨宁 2012/04/25 加入此处，新起一个线程进行写入保过协议内容与同时新crm推送信息 --->保过协议与流水1对1关联,此操作将保过协议文本信息写入cus_cash_protocal_tbl关联表中
					 * 原因为:产品保过协议功能更改后台保过协议可各更改与crm业务需要
					 * 
					 * **/
					try{
						if(cusDetialId > 0 && sellids != null && sellids.trim().length() > 0){
							taskExecutor.execute(new ProtocalContractTask(cashSellMap,sellids,cusDetialId,sellWayService,cusProtocalCashService));
						}
						taskExecutor.execute(new CrmSendMessageTask(newcontract));
					}catch(Exception e){
						logger.error("ZFBAction.taskExecutor.execute",e);
					}

					// 谢添加结束
					
					// 下单成功发送短信给客户
//					try {
//					    Customer customer = customerService
//                        .getCustomerById(getLoginUserId());
//                        if (customer.getMobile() != null
//                                && !"".equals(customer.getMobile().trim())) {
//                            smsService.sendEx("【嗨学网】您的订单已成功提交，您的订单编号为" + get_order
//                                    + "，请使用网银完成支付。", customer.getMobile(), "", "",
//                                    "");
//                        }
//                    } catch (Exception e) {
//                        logger.error("订单下单时发送短信异常",e);
//                    }
				}

			} catch (Exception e) {
				SimpleDateFormat matter = new SimpleDateFormat(
		                "现在时间:yyyy年MM月dd日E HH时mm分ss秒");
				logger.error(matter.format(new Date())+"订单下单异常",e);
			}
		}
		return "yesoder";
	}

	/**
	 * 是否包含可升级课程的包，判断是否要下订单
	 * 
	 * @return
	 */
	private boolean isMakeContract() {
		boolean upFlag = false;
		try {
			courseOrder = URLDecoder.decode(
					CookieHandler.getCookieValueByName(servletRequest,
							"courseOrder"), "utf-8").trim();
			String[] body = courseOrder.split("#");
			Set settmp = new HashSet<String>();
			for (int j = 1; j < body.length; j++) {
				String[] crInfo = body[j].split(",");
				if (settmp.contains(crInfo[2])) {
					continue;
				}
				settmp.add(crInfo[2]);
				int packId = new Integer(crInfo[2]);
				upFlag = isUpSell(packId);
				if (upFlag == false) {
					return false;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return upFlag;
	}

	/**
	 * 判断是否能下定单
	 * 
	 * @param packId
	 * @return
	 */
	public boolean isUpSell(int packId) {
		try {
			boolean upFlag = false;
			if (!StringUtils.isEmpty(sellWayService.getSellWayById(packId)
					.getSellMark())) {

				getQueryCashRecordCondition().setUserId(getLoginUserId());
				getQueryCashRecordCondition().setStatus(1);
				getQueryCashRecordCondition().setPackId(
						Integer.parseInt(sellWayService.getSellWayById(packId)
								.getSellMark()));

				List<CashRecord> cList = cashRecordService
						.getCashRecordByList(getQueryCashRecordCondition());
				if (cList != null && cList.size() != 0) {
					upFlag = true;
				} else {
					upFlag = false;
				}
			} else {
				upFlag = true;
			}
			return upFlag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
		List<CashRecord> cdList = cashRecordService.getAllCash(ct
				.getContractId());
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
	 * 通过参数获取订单
	 * 
	 * @return
	 */
	private Contract getContractByParms(int cusId) {
		QueryContractCondition qcc = new QueryContractCondition();
		qcc.setContractId(getGet_order());
		qcc.setUserId(cusId);
		PageResult ctList = contractService.getContractList(qcc);
		if (ctList != null && ctList.getPageResult() != null
				&& ctList.getPageResult().size() > 0) {
			return (Contract) ctList.getPageResult().get(0);
		}
		return null;
	}

	/**
	 * 记录流水
	 * 
	 * @param ctId
	 * @return
	 */
	private float saveCashRecords(int ctId) {
		// 记录到流水表中
		CashRecord cashRecord = null;
		Date date = new Date();
		Set<String> stmpset = new HashSet<String>();
		float youhuijuan = 0;//优惠卷
		float priceSum = 0;//订单总价
		String[] body = courseOrder.split("#");
		
		List<SellWay> sellList = new ArrayList<SellWay>();
		for (int j = 1; j < body.length; j++) {
			String[] crInfo = body[j].split(",");
			if (stmpset.contains(crInfo[2])) {
				continue;
			} else {
				stmpset.add(crInfo[2]);
			}
			int packId = new Integer(crInfo[2]);
			SellWay sellWay = sellWayService.getSellWayById(packId);
			priceSum += sellWay.getSellPrice();
			sellList.add(sellWay);
		}
		float totalPrice = priceSum;
		
		Coupon coupon = null;  //优惠券
		if (couponCode != null && !couponCode.equals("0") && !couponCode.equals(""))// 优惠券编码是否为空
		{
			try{
				coupon = sellCouService.GetCouponByCode(couponCode); //根据优惠编码得到优惠券对象
				if(coupon!=null){
					Coupontype couponType = sellCouService.getCouponTypeByCouponId(coupon.getId());  //得到优惠券类型
					float rebate = Float.parseFloat(couponType.getPreferentialPrice());
					if (couponType.getCType() == 1) { //如果是打折
						float zheTotalPrice = totalPrice * (rebate * 10 / 100);
						
						//做四舍五入操作，保留到小数点之后2位
		    			BigDecimal bd = new BigDecimal(zheTotalPrice);   
		    			float zTalPrice = bd.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
						youhuijuan = priceSum - zTalPrice;
					}
					if (couponType.getCType() == 2) {//如果是立减
						youhuijuan = rebate;
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("生成订单，写入流水出错");
			}
		}
		
		float privilegeSum = 0; //流水优惠金额  累计变量
		
		for (int j = 0; j < sellList.size(); j++) {
			SellWay sellWay = sellList.get(j);
            cashRecord = new CashRecord();
//            totalPrice += sellWay.getSellPrice(); // 总价累计
            cashRecord.setCusId(getLoginUserId()); // 用户ID
            cashRecord.setPrice(sellWay.getSellPrice()); // 课程价格
            cashRecord.setContractId(get_order); // 订单号
            cashRecord.setType(1); // 购买类型 1前台购买，2后台赠送 3后台修复
            cashRecord.setStatus(0);
            cashRecord.setCourseId(0);
            // 加入一个状态 status
            cashRecord.setCRSubjectId(sellWay.getSubjectId());
            cashRecord.setCtId(ctId); // 定单号
            cashRecord.setPackId(sellList.get(j).getSellId()); // 包ID
            cashRecord.setCrInfo("购买《" + sellWay.getSellName() + "》"); // 该流水的课程名
            cashRecord.setCreateTime(new Date()); // 生成流水的时间
            //--------开始-----------设置流水的有效期和服务终止时间----郑强-------2012/05/16---------
            cashRecord.setStopTime(sellWay.getStopServiceTime()); // 停止服务时间
            if (sellWay.getValidityNum() != null && sellWay.getValidityNum() > 0) {
            	Calendar now = Calendar.getInstance();
            	now.add(Calendar.DAY_OF_MONTH, sellWay.getValidityNum());
            	cashRecord.setValidityTime(now.getTime());
            } else {
            	cashRecord.setValidityTime(sellWay.getValidityEndTime());
            }
            //--------结束-----------设置流水的有效期和服务终止时间----郑强-------2012/05/16---------
            
            //-------------流水升级新增数据-------王郑 start-------
            
			if (payType == Contract.CONTRACT_PAY_TYPE_ZFB) {
				cashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_ZFB);// 支付宝
			} else if (payType == Contract.CONTRACT_PAY_TYPE_CB) {
				cashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_CB);// 网银在线
			}else if (payType == Contract.CONTRACT_PAY_TYPE_TRUEU){
			    cashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_TRUEU);// 真友信用
            }else if (payType == Contract.CONTRACT_PAY_TYPE_YL){
			    cashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_YL);// 银联支付
            }else{
				cashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_KQ);// 快钱支付
			}
			cashRecord.setShopStatus(0); //设置流水商品状态 0:未激活/1:已激活/2:已延期/3:已关闭
			
			cashRecord.setShopType(1);   //设置商品的类型  1为课程 2为书籍
			
			cashRecord.setReliefPrice(0); //特别减免，暂时没用到，设置初始值0
			
			float sellPrice = sellWay.getSellPrice();
	            //未使用时 优惠价格为0 ，流水成交价格 为 sellPrice
	            cashRecord.setCashRecordPrice(sellPrice);//流水成交价格
	            if (youhuijuan == 0 ) {
	            	cashRecord.setCouponMoney(0);  
	    		} else { //已使用优惠卷 
	    			if(j != (sellList.size()-1)){    //前n个流水的 优惠金额 = 单个商品金额 / 订单总价 * 优惠劵金额
	        			float couponMoney = sellPrice/totalPrice * youhuijuan;
	        			//做四舍五入操作，保留到小数点之后2位
	        			BigDecimal bd = new BigDecimal(couponMoney);   
	        			float cMoney = bd.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
	        			cashRecord.setCouponMoney(cMoney);  //优惠价格
	        			cashRecord.setCashRecordPrice(sellPrice - cMoney);//流水成交价格
	        			privilegeSum += cMoney;
	    			}else{    //最后一个流水的优惠金额 = 有汇总金额 - 前N个流水的优惠金额
	    				cashRecord.setCouponMoney(youhuijuan - privilegeSum);  //优惠价格
	    				cashRecord.setCashRecordPrice(sellPrice - (youhuijuan - privilegeSum));//流水成交价格
	    			}
	    		}
			
			/*if(j!=sellList.size()){ //如果当前对象不是数组的最后一个 则执行
				//privilege 优惠单价
				//privilegeSum优惠总价
				float privilege= sellWay.getSellPrice()/priceSum;
				BigDecimal bd= new BigDecimal(privilege);
				privilege = bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()* youhuijuan;
				cashRecord.setCouponMoney(privilege);
				cashRecord.setCashRecordPrice(sellWay.getSellPrice()-privilege);
				privilegeSum+=privilege; //累加以上流水优惠金额
			}else {//为了保证多条流水的优惠金额总和  等于优惠金额，最后一条流水优惠金额=总优惠金额-前面所有流水优惠金额。
				cashRecord.setCouponMoney(youhuijuan-privilegeSum);
			}*/
			
			
            //-------------流水升级新增数据-------王郑 end-------
			
			
			
            int cashId = cashRecordService.addCashRecord(cashRecord); // 添加到流水表中
            
            /**Yangning add time 2012/03/26 存入用户保过协议关联信息**/
            try{
                /**-------杨宁  2012/04/25 加入保过协议,生成如下MapList<sellId,cashId> 为方便起线程写入**/
                if(cusDetialId > 0 && sellids !=null && sellids.trim().length() > 0){
           		 Map sellMap = new HashMap<Integer,String>();
                    sellMap.put(cashRecord.getPackId(),cashId);
                    this.cashSellMap.add(sellMap);
                }
            }catch(Exception e){
            	logger.error("ZFBaction.saveCashRecords____1616",e);
            }
            logger.info("前台/个人中心/消费记录/" + "用户:" + getLoginUserId() + "购买:"
                    + sellWay.getSellName() + "金额：" +1 + "订单号为:"
                    + get_order + "创建时间" + date + "/成功");
		}
		// ******订单优化 每个售卖方式生成一条流水

		return priceSum;
	}
	

	/**
	 * 清除生成订单所用的cookie中的数据
	 */
	private void clearCookieContractInfo() {
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(),
				"courses");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(),
				"totalPrice");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(),
				"courseOrder");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(),
				"payprice");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(),
				"totalPrice1");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(),
				"zongjia");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(),
				"coursesdan");
		CookieHandler.deleteCookieByName(servletRequest, getServletResponse(),
				"coursesbao");
	}

	/**
	 * 获取订单的明细，包含售卖方式、单价
	 * 
	 * @param courseOrder
	 * @return
	 */
	public Map<String, String> getContractDetail(String courseOrder) {

		Map<String, String> detailMap = new HashMap<String, String>();
		ArrayList packList = new ArrayList();
		String[] courses = courseOrder.split("#");
		for (int j = 1; j < courses.length; j++) {
			String[] crInfo = courses[j].split(",");
			int packId = new Integer(crInfo[2]);
			this.getQuerySellCouCondition().setSellId(packId);
			List<SellCou> sellCouList = sellCouService.getSellCouList(this
					.getQuerySellCouCondition());
			for (int i = 0; i < sellCouList.size(); i++) {
				packList.add(sellCouList.get(i).getSellId());
			}
		}
		packList = (ArrayList) this.filterList(packList);
		String pks = "";
		String prices = "";
		String pksNum = "";
		String comm = "";
		for (int i = 0; i < packList.size(); i++) {
			pks += packList.get(i) + "|_|";
			SellWay sw = sellWayService.getSellWayById((Integer) packList
					.get(i));
			prices += sw.getSellPrice() + "|_|";
			pksNum += "1|_|";
			comm += "0|_|";
			// sellWayService.getSellWayById(38);
			// prices += sellWayService.getSellWayById(Integer.parseInt((String)
			// packList.get(i))).getSellPrice() + "|_|";
		}
		detailMap.put("pks", pks);
		detailMap.put("prices", prices);
		detailMap.put("pksNum", pksNum);
		detailMap.put("comm", comm);
		return detailMap;
	}

	/**
	 * 工具方法-用于对List中的元素进行剔重
	 * 
	 * @param list
	 * @return
	 */
	public List filterList(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}

	// 谢添加开始
	/**
	 * 获取验证码对应项目 谢添加方法 若二期对优惠券加功能此方法需改
	 * 
	 * @return
	 */
	public String getCouponByCode() {
		try {
			String resultStr = "此优惠券不存在";
			Coupon coupon = sellCouService.GetCouponByCode(couponCode);
			if (coupon != null) {
				Date useDate = coupon.getCouponType().getUseTime();
				Date stopDate = coupon.getCouponType().getStopTime();
				// 判断日期还有状态及父类状态
				if (useDate.getTime() > (new Date()).getTime()
						|| stopDate.getTime() < (new Date()).getTime()
						|| stopDate.getTime() < useDate.getTime()) {
					coupon = null;
					resultStr = "此优惠券不在使用期限内";
				} else if (coupon.getState() == 3) {
					coupon = null;
					resultStr = "此优惠券已过期";
				} else if (coupon.getState() == 4) {
					coupon = null;
					resultStr = "此优惠券已作废";
				} else if (coupon.getCouponType().getState() == 2) {
					coupon = null;
					resultStr = "此优惠券已过期";
				} else if (coupon.getCouponType().getState() == 3) {
					coupon = null;
					resultStr = "此优惠券已冻结";
				} else if (coupon.getCouponType().getState() == 4) {
					coupon = null;
					resultStr = "此优惠券已作废";
				}
			}
			boolean zj = true;
			List dbsellId = new ArrayList();
			String[] list = sellIds.split(",");
			int mysellId = 0;
			if (list.length != 0 && list[0] != null && (!list[0].equals(""))) {
				mysellId = Integer.parseInt(list[0]);
			}
			int SubjectId = sellCouService.getSubjectIdBySellId(mysellId);
			dbsellId.add(SubjectId);
			for (int i = 0; i < list.length; i++) {

				if (list[i] != null && (!list[i].equals(""))) {
					mysellId = Integer.parseInt(list[i]);
				}
				SubjectId = sellCouService.getSubjectIdBySellId(mysellId);

				if (!dbsellId.contains(SubjectId)) {
					zj = false;
					break;
				} else {
					dbsellId.add(SubjectId);
				}
			}
			setResult(new Result<Coupon>(zj, resultStr,
					String.valueOf(SubjectId), coupon));
			return "yesoder";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}
	/**
	 * 判断优惠卷是否存在
	 * @return
	 */
	public String GetSellWayByCode() {
		try {
			boolean val = sellCouService.GetSellWayByCode(subjectId, sellId);
			int Id = sellCouService.getCouponState(couponId);
			if(Id==2){//此判断只为春节期不判断重复的状态  年后需撤掉 2012-1-6 谢金龙（此后长期开放2012-2-2）
				Id=1;
			}
			if (Id != 1) {
				val = false;
			}
			setResult(new Result(true, "返回成功!", null, val));
			return "yesoder";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}

	/**
	 * 判断优惠券状态
	 */
	public String GetSellWayByCodeForId() {
		try {
			boolean val = true;
			int Id = sellCouService.getCouponState(couponId);
			if(Id==2){//此判断只为春节期不判断重复的状态  年后需撤掉 2012-1-6（此后长期开放 2012-2-2）
				Id=1;
			}
			if (Id != 1) {
				val = false;
			}
			setResult(new Result(true, "返回成功!", null, val));
			return "yesoder";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}

	/**
	 * 更新使用后优惠券的状态
	 */
	public void updateCouponForState() {
		int couponTypeId = sellCouService.GetParentIdBycouponId(couponId);
		sellCouService.updateCouponForState(couponId, couponTypeId);

	}

	/**
	 * 更新使用后优惠券的支付状态
	 */
	public void updatePayState() {
		int couponTypeId = sellCouService.GetParentIdBycouponId(couponId);
		sellCouService.updateCouponForPayState(couponId, couponTypeId);
	}

	/**
	 * 更新重置后优惠券的状态
	 */
	public void updateCouponForStateReset() {
		int couponTypeId = sellCouService.GetParentIdBycouponId(couponId);
		sellCouService.updateCouponForStateReset(couponId, couponTypeId);

	}
	
	/**
	 * 功能:下单完成后发送短信至用户,用户前端异步调用
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-22
	 */
	public String sendMsgToUser(){
		try {
		    Customer customer = customerService.getCustomerById(getLoginUserId());
            if (customer.getMobile() != null && !"".equals(customer.getMobile().trim())) {
               smsService.sendEx("【嗨学网】您的订单已成功提交，您的订单编号为" + get_order + "，请使用网银完成支付。", customer.getMobile(), "", "","");
           }
            setResult(new Result(true, "success", null, null));
        } catch (Exception e) {
          logger.error("订单下单时发送短信异常",e);
          setResult(new Result(true, "error", null, null));
      }
		return "json";
	}
	
	// 谢添加结束
	public String getZfbUrl() {
		return zfbUrl;
	}

	public void setZfbUrl(String zfbUrl) {
		this.zfbUrl = zfbUrl;
	}

	public String getGet_total_fee() {
		return get_total_fee;
	}

	public void setGet_total_fee(String get_total_fee) {
		this.get_total_fee = get_total_fee;
	}

	public String getGet_subject() {
		return get_subject;
	}

	public void setGet_subject(String get_subject) {
		this.get_subject = get_subject;
	}

	public String getGet_body() {
		return get_body;
	}

	public void setGet_body(String get_body) {
		this.get_body = get_body;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCourseOrder() {
		return courseOrder;
	}

	public void setCourseOrder(String courseOrder) {
		this.courseOrder = courseOrder;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getCPrice() {
		return cPrice;
	}

	public void setCPrice(String price) {
		cPrice = price;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public IGmrecord getGmrecordService() {
		return gmrecordService;
	}

	public void setGmrecordService(IGmrecord gmrecordService) {
		this.gmrecordService = gmrecordService;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public QueryKpointCondition getQueryKpointCondition() {
		if (queryKpointCondition == null) {
			queryKpointCondition = new QueryKpointCondition();
		}
		return queryKpointCondition;
	}

	public void setQueryKpointCondition(
			QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPaymoney() {
		return paymoney;
	}

	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}

	public String getMakeoders() {
		return makeoders;
	}

	public void setMakeoders(String makeoders) {
		this.makeoders = makeoders;
	}

	public ICpCus getCpCusService() {
		return cpCusService;
	}

	public void setCpCusService(ICpCus cpCusService) {
		this.cpCusService = cpCusService;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getDefaultbank_1() {
		return defaultbank_1;
	}

	public void setDefaultbank_1(String defaultbank_1) {
		this.defaultbank_1 = defaultbank_1;
	}

	public String getGet_order() {
		return get_order;
	}

	public void setGet_order(String get_order) {
		this.get_order = get_order;
	}

	public String getPaymethod_1() {
		return paymethod_1;
	}

	public void setPaymethod_1(String paymethod_1) {
		this.paymethod_1 = paymethod_1;
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}

	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}

	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}

	public IUserMsg getUserMsgService() {
		return userMsgService;
	}

	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}

	public IMessage getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
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

	public ITemplate getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public ISellCou getSellCouService() {
		return sellCouService;
	}

	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}

	public QuerySellCouCondition getQuerySellCouCondition() {
		if (querySellCouCondition == null) {
			querySellCouCondition = new QuerySellCouCondition();
		}
		return querySellCouCondition;
	}

	public void setQuerySellCouCondition(
			QuerySellCouCondition querySellCouCondition) {
		this.querySellCouCondition = querySellCouCondition;
	}

	public GetHttpMessage getGetMsg() {
		return getMsg;
	}

	public void setGetMsg(GetHttpMessage getMsg) {
		this.getMsg = getMsg;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	public QueryCashRecordCondition getQueryCashRecordCondition() {
		if (queryCashRecordCondition == null) {
			queryCashRecordCondition = new QueryCashRecordCondition();
		}
		return queryCashRecordCondition;
	}

	public void setQueryCashRecordCondition(
			QueryCashRecordCondition queryCashRecordCondition) {
		this.queryCashRecordCondition = queryCashRecordCondition;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSellId() {
		return sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getcPrice() {
		return cPrice;
	}

	public void setcPrice(String cPrice) {
		this.cPrice = cPrice;
	}

	public SellWay getsWay() {
		return sWay;
	}

	public void setsWay(SellWay sWay) {
		this.sWay = sWay;
	}

	public CashRecord getCashRecord() {
		return cashRecord;
	}

	public void setCashRecord(CashRecord cashRecord) {
		this.cashRecord = cashRecord;
	}

	public String getSellIds() {
		return sellIds;
	}

	public void setSellIds(String sellIds) {
		this.sellIds = sellIds;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public int getCusDetialId() {
		return cusDetialId;
	}

	public void setCusDetialId(int cusDetialId) {
		this.cusDetialId = cusDetialId;
	}

	public String getSellids() {
		return sellids;
	}

	public void setSellids(String sellids) {
		this.sellids = sellids;
	}

	public ICusProtocalCash getCusProtocalCashService() {
		return cusProtocalCashService;
	}

	public void setCusProtocalCashService(ICusProtocalCash cusProtocalCashService) {
		this.cusProtocalCashService = cusProtocalCashService;
	}
	
	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

}
