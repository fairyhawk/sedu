package com.shangde.edu.finance.task;

import org.apache.log4j.Logger;


import cn.highso.client.util.WebserviceUtil;
import cn.highso.core.protocol.TaskInfo;
import cn.highso.core.webservice.hessian.IExecuteService;

import com.shangde.edu.finance.domain.Contract;

/**
 * 下单后与支付成功后给新crm发送消息
 * @author Yangning
 *
 */
public class CrmSendMessageTask implements Runnable{
	private static final Logger logger = Logger.getLogger(CrmSendMessageTask.class);
	private Contract contract;
	
	public CrmSendMessageTask(Contract contract){
		this.contract = contract;
	}
	
	@Override
	public void run() {
		try{
			logger.info("sendTo other platForm");
			TaskInfo ts = new TaskInfo();
			ts.setType(6);
			//ts.setMark(String.valueOf(330228));
			ts.setMark(String.valueOf(contract.getId()));
			//测试环境地址
			//IExecuteService executeService =  WebserviceUtil.instance.getExecuteService("http://sys.highso.com.cn:8022/remoting/executeService");
			//生产环境地址
			//IExecuteService executeService =  WebserviceUtil.instance.getExecuteService("http://sys.highso.org:8022/remoting/executeService");
			//int i = executeService.putTask(ts);
			logger.info("send pay Success to customer contractId="+ contract.getId());
		}catch(Exception e){
			logger.error("CrmSendMessageTask.sendMessage2Crm",e);
		}
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
