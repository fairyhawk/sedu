package com.shangde.edu.alipay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.DateUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.common.vo.UnionPayInfo;
import com.shangde.edu.cms.condition.QueryTemplateCondition;
import com.shangde.edu.cms.domain.Template;
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
import com.shangde.edu.finance.task.CrmSendMessageTask;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sys.domain.User;


public class UnionPayAction extends CommonAction {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 声名logger
	 */
	private static final Logger logger = Logger.getLogger(UnionPayAction.class);
	
	/**银联参数信息类*/
	private UnionPayInfo upInfo = new UnionPayInfo(); 
	
	/**模板服务对象*/
	private ITemplate templateService;
	
	/**订单服务对象*/
	private IContract contractService;
	
	/**学员服务对象*/
	private ICustomer customerService;

	/**流水服务对象*/
	private ICashRecord cashRecordService;
	
	/**消息接口服务对象*/
	private IMessage messageService;
	
	/**用户小心服务对象*/
	private IUserMsg userMsgService;

	/**短信服务对象*/
	private IsmsService smsService;
	
	/**头部模板内容*/
	private String headerHTML = "";
	
	/**尾部模板内容*/
	private String footerHTML = "";
	
	/**
	 * 售卖方式服务
	 */
	private ISellWay sellWayService ;
	/**
	 * 售卖方式实体
	 */
	private SellWay sWay ;
	/**
	 * 流水实体
	 */
	private CashRecord cashRecord ; 
	/**
	 * 流水条件查询
	 */
	private QueryCashRecordCondition queryCashRecordCondition ;
	/**
	 * 配置文件服务
	 */
	private ConfigService configurator;
	
	private String unionPayUrl;
	
	/**线程池对象,用户线程操作,提高效率,防止任务过多服务器压力过大**/
	private TaskExecutor taskExecutor;
	

	/**
	 * 转向银联
	 * @return
	 * @throws IOException
	 */
	public String goToUnionPay()  {
		try{
				DecimalFormat df = new DecimalFormat("#.00 ");
				String money=df.format(Float.parseFloat(getContractByParms(getLoginUserId()).getContractCutSumMoney().toString()));
				money=String.valueOf((Float.parseFloat(money)*100));//银联2相当于0.02
				if(money.contains("."))
				{
					money=money.substring(0,money.indexOf("."));
				}
				//支付金额
				upInfo.setOrderAmount(money);
				//upInfo.setOrderAmount("1");
				//回调地址
				upInfo.setFrontEndUrl(configurator.getProjectURL() + "/alipay/unionpay!pageUrl.action");
				upInfo.setBackEndUrl(configurator.getProjectURL() + "/alipay/unionpay!noticeUrl.action");
				//交易时间
				upInfo.setOrderTime(DateUtil.formatDate(new Date(),"yyyyMMddHHmmss"));
				upInfo.setCommodityName("HighSoCourse");
				upInfo.setCommodityQuantity("1");
				/*
				try{
					//商品名称
					List<CashRecord> cashRecordList = cashRecordService.getCashRecordByCtIdForFanli(getUpInfo().getOrderNumber());
					String sellName="";
		            if(cashRecordList!=null&& cashRecordList.size()!=0){
		            	SellWay sellWay = sellWayService.getSellWayById(cashRecordList.get(0).getPackId());
		            	if(sellWay!=null){
		            		sellName=sellName+sellWay.getSellName();
		            		upInfo.setCommodityName(sellName);
		            		upInfo.setCommodityQuantity("1");
		            	}else{
		            		upInfo.setCommodityName("HighSoCourse");
		            		upInfo.setCommodityQuantity("1");
		            	}
		            }
				}catch(Exception e){
					upInfo.setCommodityName("HighSoCourse");
					upInfo.setCommodityQuantity("1");
				}
				*/
				//客户ip
				upInfo.setCustomerIp(getIpAddr(this.getServletRequest()) == null ? "127.0.0.1" : getIpAddr(this.getServletRequest()));
				//签名
				upInfo.setSignature(createSignMsg());
		}catch(Exception e){
			logger.error("UnionPayAction.goToUnionPay",e);
			return ERROR;
		}
		return "gotoUnionPay";
	}
	
	/**
	 * 银联回调函数同步
	 * @return
	 */
	public String pageUrl() {
	    logger.info("银联回调returnURL");
	    String forward = "invalid";
			try {
				initReturnParms();
				getUpInfo().setReturnSign(createReturnSignMsg());
				boolean flag = false;
				if(getUpInfo().getSignature() != null && getUpInfo().getReturnSign() != null && getUpInfo().getSignature().toUpperCase().equals(getUpInfo().getReturnSign().toUpperCase())){
					//银联返回00为支付成功
					if(getUpInfo().getRespCode() != null && getUpInfo().getRespCode().equals("00")){
						flag = true;
					}
				}
				//支付成功，业务操作
	            if(flag){
						proccessContractForPayOk();
						processTempContent();
						forward = "success";
					} 
			} catch (Exception e) {
			    logger.error("UnionPayAction.pageUrl",e);
			    return ERROR;
			} finally{
				//通知银联已收到信息
				getServletResponse().setStatus(HttpServletResponse.SC_OK);
			}
		return forward;
	}
	
	/**
	 * 银联回调函数后台异步
	 * @return
	 */
	public String noticeUrl() {
	    logger.info("银联回调noticeUrl");
			try {
				initReturnParms();
				getUpInfo().setReturnSign(createReturnSignMsg());
				boolean flag = false;
				if(getUpInfo().getSignature() != null && getUpInfo().getReturnSign() != null && getUpInfo().getSignature().toUpperCase().equals(getUpInfo().getReturnSign().toUpperCase())){
					//银联返回00为支付成功
					if(getUpInfo().getRespCode() != null && getUpInfo().getRespCode().equals("00")){
						logger.info("------notify签名通过----更新订单--------\n\r");
						flag = true;
					}
				}
				//支付成功，业务操作
	            if(flag){
						proccessContractForPayOk();
						processTempContent();
	            }
			} catch (Exception e) {
			    logger.error("UnionPayAction.noticeUrl",e);
			} finally{
				//通知银联已收到信息
				getServletResponse().setStatus(HttpServletResponse.SC_OK);
			}
		return null;
	}
	
	/**
	 * 支付成功，处理订单数据
	 * @param returnType
	 */
	private void proccessContractForPayOk() {
		// 支付成功，在这里写入数据处理,通过订单号及用户id查找订单记录
		Contract ct = getContractByParms((getLoginUserId()));
		//说明正常在系统内查到订单
		if(ct != null){
			Customer cus = customerService.getCustomerById(ct.getCusId());
			//检查学员类型
			checkCusType(cus, ct);
			
			//增加访问次数
			ct.setCallSum(ct.getCallSum() + 1);
			
			//改变订单状态
			if(ct.getStatus() == Contract.CONTRACT_STATUS_NOT_PAY){
				logger.info("---------更新订单为已支付--------");
				ct.setStatus(Contract.CONTRACT_STATUS_PAIED);//已付款
				ct.setPayTime(new Date());//记录支付时间
				ct.setRemark("银联");//记录异步
				ct.setPayType(Contract.CONTRACT_PAY_TYPE_YL);//银联支付
				
				//初始化知识树
				initKpointTreeByContract(ct);
				
				//发送购买成功消息
				sendBuySuccMsgToUser(cus);
				
				//支付成功发送短信给客户
				sendBuySuccMsgToMobile(cus);
				
				try{
					taskExecutor.execute(new CrmSendMessageTask(ct));
				}catch(Exception e){
					logger.error("UnionPay.taskExecutor.CrmSendMessageTask",e);
				}
			}
			contractService.updateContract(ct);
			//谢添加修改支付状态
			try{
			if(ct.getUseCoupon()==Contract.CONTRACT_USE_COUPON_YES&&ct.getStatus() == Contract.CONTRACT_STATUS_PAIED)
			{
			String couponId=sellWayService.getContractForCouponIdById(String.valueOf(ct.getId()));
			if(couponId!=null&&!couponId.equals(""))
			{
			int couponTypeId=sellWayService.GetParentIdBycouponId(couponId);
				sellWayService.updateCouponForPayState(couponId, couponTypeId);
			}
			}
			}
			catch(Exception e){
				logger.error("支付成功，处理订单数据错误！",e);
			}
			//谢添加结束
			//如果订单是升级课程的订单则把以前的订单流水状态改为无效
			upOld(ct) ;
		}
	}
	/**
	 * 判断订单是否是升级课程的订单，是，就把升级课程前的订单流水状态改为无效
	 * 系统目前是通过售卖方式来出售课程的。如：售卖方式A包含1,2,3课程，售卖方式B包含1,2,3,4,5,6课程，
	 * 当用户已经购买售卖方式A进行学习的时候，需要在学习结束之后学习4,5,6课程，而4,5,6课程在系统上没有
	 * 单独的售卖方式出售，他必须通过购买售卖方式B来学习剩下的课程，由于以前的课程是不能退的，充分考虑
	 * 用户的权益，当他购买售卖方式B的时候，减去售卖方式A的价值。当然他以前的订单也随之作废，将他以前的
	 * 订单的流水的状态改为无效。
	 */
	public void upOld(Contract ct) {
		//查询订单下的所有流水
		List<CashRecord> cdList = cashRecordService.getAllCash(ct.getContractId());
		Set settmp = new HashSet<String>(); 
		for (CashRecord cd : cdList) {
			//查询流水的售卖方式
			sWay = sellWayService.getSellWayById(cd.getPackId());
			if (settmp.contains(cd.getPackId())){
				continue;
			}
			settmp.add(cd.getPackId());
			//判断流水的售卖方式是否是可升级的
			if (!StringUtils.isEmpty(sWay.getSellMark())) {
				getQueryCashRecordCondition().setUserId(ct.getCusId());
				getQueryCashRecordCondition().setStatus(1);
				getQueryCashRecordCondition().setPackId(
						Integer.parseInt(sWay.getSellMark()));
				//查询出满足条件的流水
				List<CashRecord> cList = cashRecordService
						.getCashRecordByList(getQueryCashRecordCondition());
				if (cList != null && cList.size() != 0) {
					//改变流水的状态
					for(CashRecord cdClear: cList){
						cdClear.setStatus(0) ;
						cashRecordService.updateCashRecord(cdClear);
					}
				}
			} 
		}
	}
	
	/**
	 * 发送短信通知购买成功
	 * @param cusId
	 */
	private void sendBuySuccMsgToMobile(Customer cus) {
		if(cus.getMobile() != null && !"".equals(cus.getMobile().trim())){
			try {
				smsService.sendEx("【嗨学网】您已支付成功，进入[我的highso]，点击我的课程，开始学习吧！", cus.getMobile(), "", "", "");
			} catch (Exception e) {
				logger.error("UnionPayAction.sendBuySuccMsgToMobile",e);
			}
		}
	}

	/**
	 * 发送购买成功消息
	 */
	private void sendBuySuccMsgToUser(Customer cus) {
		try{
			Message msg = messageService.getMessageByKey("buy");
			if(msg != null && msg.getMsgId() > 0){
				User sender = new User();
				sender.setUserId(1);
				sender.setUserName("超级管理员");
				if(cus != null){
					userMsgService.adminerSendMsgToCutomer(sender, msg.getMsgId(), cus);
				}
			}
		}catch(Exception e){
			logger.error("UnionPayAction.sendBuySuccMsgToUser",e);
		}
	}

	/**
	 * 根据订单初始化学员的知识树
	 */
	private void initKpointTreeByContract(Contract ct) {
	    CashRecord upcashRecord = new CashRecord();
	    upcashRecord.setCusId(ct.getCusId());
	    upcashRecord.setCtId(ct.getId());
	    upcashRecord.setStatus(1);
	    upcashRecord.setShopStatus(1);
	    //---Yangning 2011/12/21  支付成功时同时更新流水支付类型--- begin // 
	    upcashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_YL);
        this.getCashRecordService().upateCashRecordForOnlinePay(upcashRecord);
      //---Yangning 2011/12/21  支付成功时同时更新流水支付类型--- end //
	}

	/**
	 * 如果是临时学员或内部体验账号，那么删除他的临时信息（除了刚刚购支付的订单所对应的数据），并置状态为注册学员
	 * @param cus
	 * @param ct
	 */
	private void checkCusType(Customer cus, Contract ct) {
		if(cus.getCusType() == Customer.CUS_CUS_TYPE_TEMP || cus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP || cus.getCusType() == Customer.CUS_CUS_TYPE_TEMP_EXP_MONTH) {
			customerService.recoverTempCustomer(ct.getCusId(), ct.getId());
			cus.setCusType(Customer.CUS_CUS_TYPE_REGISTER);
			customerService.updateCustomer(cus);
		}
	}

	/**
	 * 通过参数获取订单
	 * @return
	 */
	private Contract getContractByParms(int cusId) {
		QueryContractCondition qcc = new QueryContractCondition();
		qcc.setContractId(getUpInfo().getOrderNumber());
		PageResult  ctList = contractService.getContractList(qcc);
		if(ctList != null && ctList.getPageResult() != null && ctList.getPageResult().size() > 0) {
			return (Contract)ctList.getPageResult().get(0);
		}
		return null;
	}
	
	/**
	 * 功能:签名生成
	 * @return
	 * @throws UnsupportedEncodingException
	 * Author:Yangning
	 * CreateDate:2011-12-23
	 */
	private String createSignMsg() {
		String singMsgVal = "";
		singMsgVal = appendParam(singMsgVal,"acqCode",upInfo.getAcqCode());
		singMsgVal = appendParam(singMsgVal,"backEndUrl",upInfo.getBackEndUrl());
		singMsgVal = appendParam(singMsgVal,"charset",upInfo.getCharset());
		singMsgVal = appendParam(singMsgVal,"commodityDiscount",upInfo.getCommodityDiscount());
		singMsgVal = appendParam(singMsgVal,"commodityName",upInfo.getCommodityName());
		singMsgVal = appendParam(singMsgVal,"commodityQuantity",upInfo.getCommodityQuantity());
		singMsgVal = appendParam(singMsgVal,"commodityUnitPrice",upInfo.getCommodityUnitPrice());
		singMsgVal = appendParam(singMsgVal,"commodityUrl",upInfo.getCommodityUrl());
		singMsgVal = appendParam(singMsgVal,"customerIp",upInfo.getCustomerIp());
		singMsgVal = appendParam(singMsgVal,"customerName",upInfo.getCustomerName());
		singMsgVal = appendParam(singMsgVal,"defaultBankNumber",upInfo.getDefaultBankNumber());
		singMsgVal = appendParam(singMsgVal,"defaultPayType",upInfo.getDefaultPayType());
		singMsgVal = appendParam(singMsgVal,"frontEndUrl",upInfo.getFrontEndUrl());
		singMsgVal = appendParam(singMsgVal,"merAbbr",upInfo.getMerAbbr());
		singMsgVal = appendParam(singMsgVal,"merCode",upInfo.getMerCode());
		singMsgVal = appendParam(singMsgVal,"merId",upInfo.getMerId());
		singMsgVal = appendParam(singMsgVal,"merReserved",upInfo.getMerReserved());
		singMsgVal = appendParam(singMsgVal,"orderAmount",upInfo.getOrderAmount());
		singMsgVal = appendParam(singMsgVal,"orderCurrency",upInfo.getOrderCurrency());
		singMsgVal = appendParam(singMsgVal,"orderNumber",upInfo.getOrderNumber());
		singMsgVal = appendParam(singMsgVal,"orderTime",upInfo.getOrderTime());
		singMsgVal = appendParam(singMsgVal,"origQid",upInfo.getOrigQid());
		singMsgVal = appendParam(singMsgVal,"transTimeout",upInfo.getTransTimeout());
		singMsgVal = appendParam(singMsgVal,"transType",upInfo.getTransType());
		singMsgVal = appendParam(singMsgVal,"transferFee",upInfo.getTransferFee());
		singMsgVal = appendParam(singMsgVal,"version",upInfo.getVersion());
		singMsgVal = singMsgVal + "&" + md5(upInfo.getSecurityKey());
		return md5(singMsgVal);
	}
	
	/**
	 * 功能:加密方法
	 * @param str
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-23
	 */
	private String md5(String str) {
		if (str == null) {
			return null;
		}
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(upInfo.getSignMethod());
			messageDigest.reset();
			messageDigest.update(str.getBytes(upInfo.getCharset()));
		} catch (NoSuchAlgorithmException e) {
			logger.error("UnionPayAction.md5",e);
			return str;
		} catch (UnsupportedEncodingException e) {
			logger.error("UnionPayAction.md5",e);
			return str;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}
	
	/**
	 * 获取银联返回参数
	 */
	private void initReturnParms() {
		this.getUpInfo().setCharset((String)getServletRequest().getParameter("charset"));
		this.getUpInfo().setExchangeDate((String)getServletRequest().getParameter("exchangeDate"));
		this.getUpInfo().setExchangeRate((String)getServletRequest().getParameter("exchangeRate"));
		this.getUpInfo().setMerAbbr((String)getServletRequest().getParameter("merAbbr"));
		this.getUpInfo().setMerId((String)getServletRequest().getParameter("merId"));
		this.getUpInfo().setOrderAmount((String)getServletRequest().getParameter("orderAmount"));
		this.getUpInfo().setOrderCurrency((String)getServletRequest().getParameter("orderCurrency"));
		this.getUpInfo().setOrderNumber((String)getServletRequest().getParameter("orderNumber"));
		this.getUpInfo().setQid((String)getServletRequest().getParameter("qid"));
		this.getUpInfo().setRespCode((String)getServletRequest().getParameter("respCode"));
		this.getUpInfo().setRespMsg((String)getServletRequest().getParameter("respMsg"));
		this.getUpInfo().setRespTime((String)getServletRequest().getParameter("respTime"));
		this.getUpInfo().setSettleAmount((String)getServletRequest().getParameter("settleAmount"));
		this.getUpInfo().setSettleCurrency((String)getServletRequest().getParameter("settleCurrency"));
		this.getUpInfo().setSettleDate((String)getServletRequest().getParameter("settleDate"));
		this.getUpInfo().setTraceNumber((String)getServletRequest().getParameter("traceNumber"));
		this.getUpInfo().setTraceTime((String)getServletRequest().getParameter("traceTime"));
		this.getUpInfo().setTransType((String)getServletRequest().getParameter("transType"));
		this.getUpInfo().setCupReserved((String)getServletRequest().getParameter("cupReserved"));
		this.getUpInfo().setVersion((String)getServletRequest().getParameter("version"));
		this.getUpInfo().setSignature((String)getServletRequest().getParameter("signature"));
		this.getUpInfo().setSignMethod((String)getServletRequest().getParameter("signMethod"));
	}
	
	/**
	 * 生成返回加密串，顺序不可变
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String createReturnSignMsg() throws UnsupportedEncodingException {
		String returnMsgVal = "";
		returnMsgVal=appendParam(returnMsgVal,"charset", getUpInfo().getCharset());
		returnMsgVal=appendParam(returnMsgVal,"cupReserved",getUpInfo().getCupReserved());
		returnMsgVal=appendParam(returnMsgVal,"exchangeDate", getUpInfo().getExchangeDate());
		returnMsgVal=appendParam(returnMsgVal,"exchangeRate", getUpInfo().getExchangeRate());
		returnMsgVal=appendParam(returnMsgVal,"merAbbr", getUpInfo().getMerAbbr());
		returnMsgVal=appendParam(returnMsgVal,"merId", getUpInfo().getMerId());
		returnMsgVal=appendParam(returnMsgVal,"orderAmount", getUpInfo().getOrderAmount());
		returnMsgVal=appendParam(returnMsgVal,"orderCurrency", getUpInfo().getOrderCurrency());
		returnMsgVal=appendParam(returnMsgVal,"orderNumber", getUpInfo().getOrderNumber());
		returnMsgVal=appendParam(returnMsgVal,"qid", getUpInfo().getQid());
		returnMsgVal=appendParam(returnMsgVal,"respCode", getUpInfo().getRespCode());
		returnMsgVal=appendParam(returnMsgVal,"respMsg", getUpInfo().getRespMsg());
		returnMsgVal=appendParam(returnMsgVal,"respTime", getUpInfo().getRespTime());
		returnMsgVal=appendParam(returnMsgVal,"settleAmount", getUpInfo().getSettleAmount());
		returnMsgVal=appendParam(returnMsgVal,"settleCurrency", getUpInfo().getSettleCurrency());
		returnMsgVal=appendParam(returnMsgVal,"settleDate", getUpInfo().getSettleDate());
		returnMsgVal=appendParam(returnMsgVal,"traceNumber", getUpInfo().getTraceNumber());
		returnMsgVal=appendParam(returnMsgVal,"traceTime", getUpInfo().getTraceTime());
		returnMsgVal=appendParam(returnMsgVal,"transType", getUpInfo().getTransType());
		returnMsgVal=appendParam(returnMsgVal,"version", getUpInfo().getVersion());
		returnMsgVal = returnMsgVal + "&" + md5(getUpInfo().getSecurityKey());
		return md5(returnMsgVal);
	}
	
	
	
	/**
	 * 功能:拼签名串
	 * @param returnStr
	 * @param paramId
	 * @param paramValue
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-26
	 */
	private String appendParam(String returnStr,String paramId,String paramValue) {
		if(returnStr != null && !returnStr.equals("")) {
			if(paramValue != null && !paramValue.equals("")) {
				returnStr = returnStr + "&" + paramId + "=" + paramValue.trim();
			}else{
				returnStr = returnStr + "&" + paramId + "=";
			}
		} else {
			if(paramId != null && !paramId.equals("")) {
				if(paramValue != null && !paramValue.equals("")){
					returnStr = paramId + "=" + paramValue.trim();
				}else{
					returnStr = paramId + "=";
				}
			}
		}	
		return returnStr;
	}

	/**
	 * 获取前台静态模板
	 */
	private void processTempContent() {
		try {
			QueryTemplateCondition queryTemplateCondition = new QueryTemplateCondition();
			queryTemplateCondition.setTmpName("web_header_org");
			List<Template> tempList = templateService.getTemplateList(queryTemplateCondition);
			if(tempList != null  &&  tempList.size() > 0) {
				headerHTML = templateService.processTag(tempList.get(0).getTmpContent(), null);
			}

			queryTemplateCondition.setTmpName("web_footer_org");
			tempList = templateService.getTemplateList(queryTemplateCondition);
			if(tempList != null  &&  tempList.size() > 0) {
				footerHTML = templateService.processTag(tempList.get(0).getTmpContent(), null);
			}
		} catch (Exception e) {
			logger.error("UnionPayAction.processTempContent");
		}
	}

	public ITemplate getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public CashRecord getCashRecord() {
		return cashRecord;
	}

	public void setCashRecord(CashRecord cashRecord) {
		this.cashRecord = cashRecord;
	}

	public SellWay getsWay() {
		return sWay;
	}

	public void setsWay(SellWay sWay) {
		this.sWay = sWay;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}
	
    /**
     * 功能:得到用户ip地址
     * @param request
     * @return
     * Author:Yangning
     * CreateDate:2011-12-23
     */
    public static String getIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
    
    public UnionPayInfo getUpInfo() {
		return upInfo;
	}

	public void setUpInfo(UnionPayInfo upInfo) {
		this.upInfo = upInfo;
	}
	
	public String getUnionPayUrl() {
		return unionPayUrl;
	}

	public void setUnionPayUrl(String unionPayUrl) {
		this.unionPayUrl = unionPayUrl;
	}
	
	public static void main(String [] args){
		UnionPayAction ac = new UnionPayAction();
		String str = "backEndUrl=http://highso.cn/alipay/unionPay!notifyURL.action&charset=UTF-8&customerIp=127.0.0.1&frontEndUrl=http://highso.cn/alipay/unionPay!returnURL.action&merAbbr=abcde&merId=100000000000404&orderAmount=59900&orderCurrency=156&orderNumber=27160120111226113212&orderTime=20111226113213&transType=01&version=1.0.0&8ddcff3a80f4189ca1c9d4d902c3c909";
		System.out.println(ac.md5(str));
	}
	
	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
}
