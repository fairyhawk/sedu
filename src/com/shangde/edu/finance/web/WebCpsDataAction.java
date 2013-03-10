package com.shangde.edu.finance.web;

import java.io.IOException;
import java.net.CookiePolicy;
import java.text.SimpleDateFormat;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.GetHttpMessage;
import com.shangde.common.util.MD5;
import com.shangde.common.util.Result;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.dto.ContractCpsDTO;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.finance.service.ICpsDataService;

public class WebCpsDataAction extends CommonAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4734412263801061731L;
	private static final Logger logger = Logger.getLogger(WebCpsDataAction.class);
	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	/**
	 * 发送订单信息服务
	 */
	private GetHttpMessage getMsg;

	public String webFrom;

	public String contractId;

	private ICpsDataService cpsDataService;
	
	private final String sendFlag = "51bi|weiyi|";
	/**
	 * 给网盟发数据，根据网盟和订单号发送
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String sendCpsData() {
		logger.debug("send cps data");
		try {
			if(webFrom==null || "".equals(webFrom)|| contractId==null || "".equals(contractId)){
				setResult(new Result<String>(true, "", null, null));
				return "json";
			}
			if(sendFlag.indexOf(webFrom)!=-1){
			// 查询订单
			Contract queryContractCondition = new Contract();
			int cusId=getLoginUserId();
			if (cusId == 0) {
				setResult(new Result<String>(true, "", null, null));
				return "json";
			}
			queryContractCondition.setCusId(cusId);
			queryContractCondition.setContractId(contractId);
			ContractCpsDTO ccd = cpsDataService.getUserOderAndWebCpsURLByid(queryContractCondition);
			String url = ccd.getUrl();
			if (null!=url) {
				// 将参数替换
				url = url.replaceAll("#cusId#", ccd.getCusId() + "");
				url = url.replaceAll("#id#", ccd.getId() + "");
				url = url.replaceAll("#contractId#", ccd.getContractId());
				if (ccd.getContractFrom() != null) {
					url = url.replaceAll("#contractFrom#", ccd.getContractFrom());
				} else {
					url = url.replaceAll("#contractFrom#", "");
				}
				String formatPattern = "yyyy-MM-dd HH:mm:ss";
				if(null!=ccd.getCreateTimeFormat()){
					formatPattern = ccd.getCreateTimeFormat();
				}
				SimpleDateFormat dateFm = new SimpleDateFormat(formatPattern);
				url = url.replaceAll("#createTime#", dateFm.format(ccd.getCreateTime()));
				url = url.replaceAll("#status#", ccd.getStatus() + "");
				url = url.replaceAll("#payType#", ccd.getPayType() + "");
				if (ccd.getRemark() != null) {
					url = url.replaceAll("#remark#", ccd.getRemark() + "");
				} else {
					url = url.replaceAll("#remark#", "");
				}
				url = url.replaceAll("#price#", ccd.getContractSumMoney() + "");
				url = url.replaceAll("#contractCutSumMoney#", ccd.getContractCutSumMoney() + "");
				url = url.replaceAll("#useCoupon#", ccd.getUseCoupon() + "");
				url = url.replaceAll("#couponMoney#", ccd.getCouponMoney() + "");
				if (ccd.getPayTime() != null) {
					if(null!=ccd.getPayTimeFormat()){
						formatPattern = ccd.getPayTimeFormat();
					}
					SimpleDateFormat dateFm2 = new SimpleDateFormat(formatPattern);
					url = url.replaceAll("#payTime#", dateFm2.format(ccd.getPayTime()));
				} else {
					url = url.replaceAll("#payTime#", "");
				}
				if (ccd.getContractCDkey() != null) {
					url = url.replaceAll("#contractCDkey#", ccd.getContractCDkey());
				} else {
					url = url.replaceAll("#contractCDkey#", "");
				}
				if (ccd.getWebFrom() != null) {
					url = url.replaceAll("#webFrom#", ccd.getWebFrom());
				} else {
					url = url.replaceAll("#webFrom#", "");
				}
				if (ccd.getWebAgent() != null) {
					url = url.replaceAll("#webAgent#", ccd.getWebAgent());
				} else {
					url = url.replaceAll("#webAgent#", "");
				}
				if (ccd.getContractFromUrl() != null) {
					url = url.replaceAll("#contractFromUrl#", ccd.getContractFromUrl());
				} else {
					url = url.replaceAll("#contractFromUrl#", "");
				}
				if (ccd.getSrc() != null) {
					url = url.replaceAll("#src#", ccd.getSrc());
				} else {
					url = url.replaceAll("#src#", "");
				}
				if (ccd.getCid() != null) {
					url = url.replaceAll("#cid#", ccd.getCid() + "");
				} else {
					url = url.replaceAll("#cid#", "");
				}
				if (ccd.getWi() != null) {
					url = url.replaceAll("#wi#", ccd.getWi() + "");
				} else {
					url = url.replaceAll("#wi#", "");
				}
				url = url.replaceAll("#couponId#", ccd.getCouponId() + "");
				
				//判断如果是51比购需要进行加密
				if(ccd.getWebFrom().equals("51bi")){
					String mcode = ccd.getWi()+ ccd.getContractId();
					mcode = MD5.getMD5(mcode);
					url = url.replaceAll("#mcode#", mcode);
				}
				logger.debug(url);
				//this.getGetMsg().getHttpMessageFromGet(9,url,"");
				String returnMsg = sendData(url);
				logger.debug(returnMsg);
			} else {
				setResult(new Result<String>(true, "", null, null));
				return "json";
			}
			}
		} catch (Exception e) {
			logger.error("发送网盟数据错误！", e);
		}
		setResult(new Result<String>(true, "success", null, null));
		return "json";
	}
	
	private String sendData(String url){
		String msg = "";
		GetMethod method = new GetMethod(url);
		HttpClient client = new HttpClient();
		try {
			client.executeMethod(method);
			//method.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
			msg = method.getResponseBodyAsString();
		} catch (HttpException e) {
			logger.error("WebCpsDataAction.sendData",e);
		} catch (IOException e) {
			logger.error("WebCpsDataAction.sendData",e);
		}finally{
			method.releaseConnection();
		}
		return msg;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public GetHttpMessage getGetMsg() {
		return getMsg;
	}

	public void setGetMsg(GetHttpMessage getMsg) {
		this.getMsg = getMsg;
	}

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public ICpsDataService getCpsDataService() {
		return cpsDataService;
	}

	public void setCpsDataService(ICpsDataService cpsDataService) {
		this.cpsDataService = cpsDataService;
	}

//	public static void main(String[] args) {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/spring/applicationContext.xml");
//		WebCpsDataAction action = new WebCpsDataAction();
//		action.setCpsDataService((ICpsDataService) applicationContext.getBean("cpsDataService"));
//		action.setContractService((IContract) applicationContext.getBean("contractService"));
//		action.webFrom = "51bi";
//		action.contractId = "4730301343355769747";
//		action.sendCpsData();
//	}
}
