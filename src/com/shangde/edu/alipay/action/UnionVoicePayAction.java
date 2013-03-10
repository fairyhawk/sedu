/**
 * UnionVoicePayAction.java
 * com.shangde.edu.alipay.action
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2012-7-9 		Yangning
 *
 * Copyright (c) 2012, TNT All Rights Reserved.
*/

package com.shangde.edu.alipay.action;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.common.vo.UnionVoicePayConfig;
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

import dnapay.service.thirdpart.IOrderServerWS;
import dnapay.service.thirdpart.OrderServerWSServiceClient;
import dnapay.service.thirdpart.PosMessage;

/**
 * ClassName:UnionVoicePayAction
 * Function: TODO ADD FUNCTION
 * Desc:	 TODO ADD Description
 *
 * @author   Yangning
 * @version  
 * @since    Ver 1.1
 * @Date	 2012-7-9		下午1:18:05
 *
 * @see 	 
 */
public class UnionVoicePayAction extends CommonAction{

	private static final long serialVersionUID = 1331236562351084183L;
	
	private static final Logger logger = Logger.getLogger(UnionVoicePayAction.class);
	
	
	private String contractNo;
	
	
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
	
	/**
	 * 银行卡号 
	 */
	private String cardNumber;
	/**
	 * 支付类型
	 */
	private String cardType;
	
	/**
	 * 手机号
	 */
	private String cardMobile;
	//持有者姓名
	private String ownerName;
	//身份证号码
	private String ownerCardId;
	//持有者开户地
	private String ownerAddress;
	//第一次查询结果
	private String transData;
	
	
	/**线程池对象,用户线程操作,提高效率,防止任务过多服务器压力过大**/
	private TaskExecutor taskExecutor;
	
	/**
	 * 
	 * chkCard:(检测当前用用户输入卡号是否可用,参见易联5.2身份验证接口)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String chkCard(){
		try{
			if(isConnectionAlive() && cardNumber != null && cardNumber.trim().length() > 0 && cardType != null && cardMobile != null && cardMobile.trim().length() > 0){
				PosMessage posMessage = new PosMessage();
				//报文类型	n4	0100	0110	由商户发起
				posMessage.setProcCode("0100");
				//-	位元表	b64 	M 	M 
				posMessage.setBitMap(null);
				String accountNumber = null;
				//判断支付类型,拼接支付提交参数
				if(!cardType.equals("14") && !cardType.equals("21")){
					this.setResult(new Result<String>(true, "failed", null, null));
					return "json";
				}
				accountNumber = cardType + cardMobile + "|" + cardNumber;
				//用户账号	ans(LLVAR) 	M	C 
				posMessage.setAccountNum(accountNumber);
				//处理码	n6 	M	M	“300002”：查询银行卡信息
				posMessage.setProcessCode("300002");
				//系统跟踪号	n6 
				posMessage.setAcqSsn(getRandNumber().toString());
				//本地交易时间	n6(HHmmss) 	M 	M 
				posMessage.setLtime(getTimeByFormate("HHmmss"));
				//本地交易日期	n4(MMDD) 	M 	M 
				posMessage.setLdate(getTimeByFormate("MMdd"));
				//终端号	ans8 	M 	M 
				posMessage.setTerminalNo(UnionVoicePayConfig.terminalNo);
				//商户号	ans17 	M	M 
				posMessage.setMerchantNo("02"+UnionVoicePayConfig.merchantNo);
				//MAC 校验码	ans32
				posMessage.setMac(createSign(posMessage));
                OrderServerWSServiceClient orderServerWS = new OrderServerWSServiceClient();
				IOrderServerWS iorderServer= orderServerWS.getOrderServerWSPort();
				posMessage  = iorderServer.transact(posMessage);
				//返回前端去除验证码，与商户号
				posMessage.setMac(null);
				posMessage.setMerchantNo(null);
				posMessage.setTerminalNo(null);
				this.setResult(new Result<PosMessage>(true, "success", null, posMessage));
			}else{
				this.setResult(new Result<String>(true, "failed", null, null));
			}
		}catch(Exception e){
			this.setResult(new Result<String>(true, "error", null, null));
			logger.error("UnionVoicePayAction.chkCard",e);
		}
		return "json";
	}

    public static void main(String [] args){
        UnionVoicePayAction union = new UnionVoicePayAction();
        //union.setCardMobile("18210611961");
        union.cardMobile = "18210611961";
        union.cardNumber = "6225880134695312";
        union.cardType = "14";
        union.chkCard();
        //union.set
    }
	/**
	 * 
	 * goToUnionPay:(下单操作)
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String goToUnionPay(){
		try{
			if(cardNumber != null && cardNumber.trim().length() > 0 && cardMobile != null && cardMobile.trim().length() > 0 && transData != null && transData.trim().length() > 0){
				DecimalFormat df = new DecimalFormat("#.00");
				String money=df.format(Float.parseFloat(getContractByParms(getLoginUserId(),contractNo).getContractCutSumMoney().toString()));
				logger.info("Contract money" + money);
				StringBuffer sb = new StringBuffer();
				for(int i = 0;i< 12 - money.length();i++ ){
						sb.append("0");
				}
				sb.append(money);
				PosMessage posMessage = new PosMessage();
				//交易金额,银联语音支付金额amount 必须12位。例如：000000000.01 代表1分钱
				posMessage.setAmount(sb.toString());
				logger.info("post money" + sb.toString());
				//位元表默认为空
				posMessage.setBitMap(null);
				//处理码,0200为提交订单
				posMessage.setProcCode("0200");
				
				String accountNumber = null;
				//判断支付类型,拼接支付提交参数
				if(!cardType.equals("14") && !cardType.equals("21")){
					this.setResult(new Result<String>(true, "failed", null, null));
					return "json";
				}
				accountNumber = cardType + cardMobile + "|" + cardNumber;
				
				String messageArray [] = transData.split("\\|");
				
				messageArray[0] = ownerName;
				messageArray[1] = ownerCardId;
				StringBuffer sbInfo = new StringBuffer();
				//借记卡需要提供用户开户地信息
                //借记卡需要提供用户开户地信息
                if(cardType.equals("14")){
                    if(ownerAddress.contains("北京")){
                        messageArray[2] = "北京市";
                    }else if(ownerAddress.contains("上海")){
                        messageArray[2] = "上海市";
                    }else if(ownerAddress.contains("天津")){
                        messageArray[2] = "天津市";
                    }else if(ownerAddress.contains("重庆")){
                        messageArray[2] = "重庆市";
                    }else{
                        messageArray[2] = ownerAddress;
                    }
                }
				//logger.info();
				for (int i = 0; i < messageArray.length; i++) {
					sbInfo.append(messageArray[i]);
					if(i != messageArray.length -1){
						sbInfo.append("|");
					}
				}
                logger.info("address + " + sbInfo.toString() );
				posMessage.setTransData(sbInfo.toString());
				System.out.println(sbInfo.toString());
				posMessage.setReturnAddress("02" + configurator.getProjectURL() + "/alipay/unionVoice!noticeUrl.action");
				posMessage.setReference(String.valueOf(getLoginUserId()));
				//用户账号
				posMessage.setAccountNum(accountNumber);
				//处理码,190000为即使支付
				posMessage.setProcessCode("190000");
				//交易传输时间
				posMessage.setTransDatetime(getTimeByFormate("MMddHHmmss"));
				//系统跟踪号
				posMessage.setAcqSsn(String.valueOf(getRandNumber()));
				//本地交易日期
				posMessage.setLdate(getTimeByFormate("MMdd"));
				//本地交易时间
				posMessage.setLtime(getTimeByFormate("HHmmss"));
				//终端号
				posMessage.setTerminalNo(UnionVoicePayConfig.terminalNo);
				//商户编号
				posMessage.setMerchantNo("02"+UnionVoicePayConfig.merchantNo);
				//订单号
				posMessage.setOrderNo("12"+ this.getContractNo());
				//即时支付
				posMessage.setOrderType("00");
				//订单描述
				posMessage.setDescription("嗨学网课");
				
				posMessage.setMac(createSign(posMessage));

                OrderServerWSServiceClient orderServerWS = new OrderServerWSServiceClient();
				IOrderServerWS iorderServer= orderServerWS.getOrderServerWSPort();
				posMessage  = iorderServer.transact(posMessage);
				//返回前端去除验证码，与商户号
				posMessage.setMac(null);
				posMessage.setMerchantNo(null);
				posMessage.setTerminalNo(null);
				this.setResult(new Result<PosMessage>(true, "success", null, posMessage));
			}else{
				this.setResult(new Result<String>(true, "failed", null, null));
			}
		}catch(Exception e){
			this.setResult(new Result<String>(true, "error", null, null));
			logger.error("UnionVoicePayAction.goToUnionPay",e);
		}
		return "json";
	}
	
	/**
	 * 
	 * noticeUrl:(支付成功后回调)
	 *
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String noticeUrl(){
		try{
			logger.info("noticeUrl__invoke");
			HttpServletRequest request = this.getServletRequest();
			PosMessage message = getPostMessage(request);
			//提交过来的签名
			String recvSign = message.getMac().toUpperCase();
			//本地生成的签名
			String genSign = createSign(message).toUpperCase();
			
			logger.info("recvSign | " +recvSign + "genSign" + genSign +"\t");
			logger.info("respCode" + message.getRespCode());
			if(genSign != null && recvSign != null && recvSign.equals(genSign) && message.getRespCode().trim().equals("0000")){
				//签名正确..业务操作，更新订单
				logger.info("update contract");
				String postMoney = message.getAmount();
				String orderNo = message.getOrderNo();
				orderNo = orderNo.substring(2);
				orderNo = orderNo.split("\\|")[0];
				logger.info("posMoney | " + postMoney + "orderNo" + orderNo);
				proccessContractForPayOk(postMoney,orderNo);
				processTempContent();
			}
		}catch(Exception e){
			logger.error("UnionVoicePayAction.noticeUrl",e);
		}finally{
			
		}
		return null;
	}
	
	
	/**
	 * 支付成功，处理订单数据
	 * @param returnType
	 */
	private void proccessContractForPayOk(String postMoney,String orderNo) {
		logger.info("proccessContractForPayOk begin");
		// 支付成功，在这里写入数据处理,通过订单号及用户id查找订单记录
		Contract ct = getContractByParms((getLoginUserId()),orderNo);
		
		if(ct != null ){
			DecimalFormat df = new DecimalFormat("#.00");
			String money=df.format(Float.parseFloat(ct.getContractCutSumMoney().toString()));
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i< 12 - money.length();i++ ){
					sb.append("0");
			}
			sb.append(money);
			logger.info("local_money | " + money);
			
			//比较post过来金额
			if(!postMoney.equals(sb.toString())){
				logger.info("contractId" + ct.getContractId() + "postMoney not right");
				return ;
			}
		}
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
				ct.setRemark("易联语音");//记录异步
				ct.setPayType(Contract.CONTRACT_PAY_TYPE_YL_VOICE);//易联语音支付
				
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
	    upcashRecord.setShopPayType(Contract.CONTRACT_PAY_TYPE_YL_VOICE);
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
	
	/**
	 * 
	 * getPostMessage:(银联返回时接收参数，并转为javabean)
	 *
	 * @param  @param request
	 * @param  @return    设定文件
	 * @return PosMessage    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	private PosMessage getPostMessage(HttpServletRequest request){
		PosMessage message = new PosMessage();
		message.setAccountNum(request.getParameter("AccountNum"));
		message.setAcqSsn(request.getParameter("AcqSsn"));
		message.setAmount(request.getParameter("Amount"));
		//message.setBitMap(request.getParameter("bitMap").getBytes());
		message.setCurCode(request.getParameter("CurCode"));
		message.setDescription(request.getParameter("Description"));
		message.setLdate(request.getParameter("Ldate"));
		message.setLoginPin(request.getParameter("LoginPin"));
		message.setMac(request.getParameter("Mac"));
		message.setMerchantNo(request.getParameter("MerchantNo"));
		message.setOrderNo(request.getParameter("OrderNo"));
		message.setOrderState(request.getParameter("OrderState"));
		message.setOrderType(request.getParameter("OrderType"));
		message.setPin(request.getParameter("Pin"));
		message.setProcCode(request.getParameter("ProcCode"));
		message.setProcessCode(request.getParameter("ProcessCode"));
		message.setReference(request.getParameter("Reference"));
		message.setRemark(request.getParameter("Remark"));
		message.setRespCode(request.getParameter("RespCode"));
		message.setReturnAddress(request.getParameter("ReturnAddress"));
		message.setSettleDate(request.getParameter("SettleDate"));
		message.setTerminalNo(request.getParameter("TerminalNo"));
		message.setTransData(request.getParameter("TransData"));
		message.setTransDatetime(request.getParameter("TransDatetime"));
		message.setTsNo(request.getParameter("TsNo"));
		message.setUpsNo(request.getParameter("UpsNo"));
		message.setValidTime(request.getParameter("ValidTime"));
		return message;
	}
	
	/**
	 * 通过参数获取订单
	 * @return
	 */
	private Contract getContractByParms(int cusId,String contractNo) {
		QueryContractCondition qcc = new QueryContractCondition();
		qcc.setContractId(contractNo);
		PageResult  ctList = contractService.getContractList(qcc);
		if(ctList != null && ctList.getPageResult() != null && ctList.getPageResult().size() > 0) {
			return (Contract)ctList.getPageResult().get(0);
		}
		return null;
	}
	
	
	
	/**
	 * isConnectionAlive:(测试易联支付服务是否启用,连接是否可用)
	 * @param  @return    设定文件
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	private boolean isConnectionAlive(){
		PosMessage posMessage = new PosMessage(); 
		posMessage.setProcCode("0800");
		posMessage.setAcqSsn(getRandNumber().toString());
		posMessage.setLtime(getTimeByFormate("HHmmss"));
		posMessage.setLdate(getTimeByFormate("MMdd"));

        OrderServerWSServiceClient orderServerWS = new OrderServerWSServiceClient();
		IOrderServerWS iorderServer= orderServerWS.getOrderServerWSPort();
		posMessage = iorderServer.transact(posMessage);
		
		if(posMessage.getRespCode().equals("0000")){
			return true;
		}
		return false;
	}
	
	

	
	/**
	 * 功能:加密方法
	 * @param str
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-23
	 */
	private static String md5(String str) {
		if (str == null) {
			return null;
		}
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			return str;
		} catch (UnsupportedEncodingException e) {
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
	 * 
	 * createSign:(生成支付签名)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * TODO(这里描述这个方法的执行流程 – 可选)
	 * TODO(这里描述这个方法的使用方法 – 可选)
	 * TODO(这里描述这个方法的注意事项 – 可选)
	 *
	 * @param  @param posMessage
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public String createSign(PosMessage posMessage){
		 StringBuffer sb = new StringBuffer();
		 //消息类型
		 if(posMessage.getProcCode() != null){
			 sb.append(posMessage.getProcCode().trim().toUpperCase());
			 sb.append(" ");
		 }
		//用户账号（类型+内容）
		 if(posMessage.getAccountNum() != null){
			 sb.append(posMessage.getAccountNum().trim().toUpperCase());
			 sb.append(" ");
		 }
		 //交易处理码
		if(posMessage.getProcessCode() != null){
			 sb.append(posMessage.getProcessCode().trim().toUpperCase());
			 sb.append(" ");
		}
		//交易金额
		if(posMessage.getAmount() != null){
			 sb.append(posMessage.getAmount().trim().toUpperCase());
			 sb.append(" ");
		}
		 //交易传输时间
		if(posMessage.getTransDatetime() != null){
			 sb.append(posMessage.getTransDatetime().toUpperCase());
			 sb.append(" ");
		}
		//系统跟踪号
		if(posMessage.getAcqSsn()!= null){
			 sb.append(posMessage.getAcqSsn().trim().toUpperCase());
			 sb.append(" ");
		}
		 //银联流水号
		if(posMessage.getUpsNo()!= null){
			 sb.append(posMessage.getUpsNo().trim().toUpperCase());
			 sb.append(" ");
		}
		//终端流水号
		if(posMessage.getTsNo() != null){
			 sb.append(posMessage.getTsNo().trim().toUpperCase());
			 sb.append(" ");
		}
		 //交易参考
		if(posMessage.getReference() != null){
			 sb.append(posMessage.getReference().trim().toUpperCase());
			 sb.append(" ");
		}
		//应答码
		if(posMessage.getRespCode() != null){
			 sb.append(posMessage.getRespCode().trim().toUpperCase());
			 sb.append(" ");
		}
		//终端编号
		if(posMessage.getTerminalNo() != null){
			 sb.append(posMessage.getTerminalNo().trim().toUpperCase());
			 sb.append(" ");
		}
		 //商户编号（类型+内容）
		if(posMessage.getMerchantNo() != null){
			 sb.append(posMessage.getMerchantNo().trim().toUpperCase());
			 sb.append(" ");
		}
		//订单号（类型+内容）
		if(posMessage.getOrderNo() != null){
			 sb.append(posMessage.getOrderNo().trim().toUpperCase());
			 sb.append(" ");
		}
		//订单状态
		if(posMessage.getOrderState() != null){
			 sb.append(posMessage.getOrderState().trim().toUpperCase());
			 sb.append(" ");
		}
		 //私钥
		 sb.append(UnionVoicePayConfig.privateKey);
		 System.out.println(sb.toString());
		 System.out.println(md5(sb.toString()));
		 return md5(sb.toString());
	}
	
	/**
	 * 得到8位随机数
	 * @return
	 */
	public static Integer getRandNumber(){
		Random rand = new Random();
		return rand.nextInt(899999) + 100000;
	}
	
	/**
	 * 
	 * getTimeByFormate:(生成服务器日期)
	 *
	 * @param  @param fromate
	 * @param  @return    设定文件
	 * @return String   
	 * @since  CodingExample　Ver 1.1
	 */
	public static String getTimeByFormate(String fromate){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(fromate);
		return sdf.format(date);
	}

	public String getContractNo() {
		return contractNo;
	}


	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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


	public String getUnionPayUrl() {
		return unionPayUrl;
	}


	public void setUnionPayUrl(String unionPayUrl) {
		this.unionPayUrl = unionPayUrl;
	}


	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}


	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardMobile() {
		return cardMobile;
	}

	public void setCardMobile(String cardMobile) {
		this.cardMobile = cardMobile;
	}
	
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getOwnerCardId() {
		return ownerCardId;
	}


	public void setOwnerCardId(String ownerCardId) {
		this.ownerCardId = ownerCardId;
	}


	public String getOwnerAddress() {
		return ownerAddress;
	}


	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}


	public String getTransData() {
		return transData;
	}


	public void setTransData(String transData) {
		this.transData = transData;
	}
}

