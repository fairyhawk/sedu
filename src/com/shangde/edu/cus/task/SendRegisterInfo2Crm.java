package com.shangde.edu.cus.task;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.highso.client.util.WebserviceUtil;
import cn.highso.core.protocol.TaskInfo;

import com.shangde.common.service.ConfigService;
import com.shangde.edu.cus.domain.Customer;
/**
 * 多线程类，给Crm发送注册信息
 * @author Yangning
 *
 */
public class SendRegisterInfo2Crm implements Runnable {
		private static final Logger logger = Logger.getLogger(SendRegisterInfo2Crm.class);
		private Customer customerInfo;
		
		private ConfigService configurator;
		
		public SendRegisterInfo2Crm(Customer customer,ConfigService configurator){
			this.customerInfo = customer;
			this.configurator = configurator;
		}
		
		@Override
		public void run(){
				try {
					sendMsg2Crm(customerInfo);
				} catch (Exception e) {
					logger.error("SendRegisterInfo2Crm.run",e);
				}
		}
		
		/**
		 * webService接口，发送用户信息至crm
		 * @param customer
		 * @throws Exception
		 * Author:Yangning
		 * CreateDate:2012-3-27
		 */
		private void sendMsg2Crm(Customer customer) throws Exception {
			TaskInfo taskInfo = new TaskInfo();
			Map crmUserMap = new Hashtable();
		    crmUserMap.put("cusId", customer.getCusId());
		    crmUserMap.put("email",customer.getEmail());
		    crmUserMap.put("mobile", customer.getMobile());
		    crmUserMap.put("subjectId", customer.getSubjectId());
		    crmUserMap.put("regTime", customer.getRegTime());
		    Map map = new Hashtable();
		    map.put("webname", getDomainString());
		    map.put("crmUser", crmUserMap);
	        taskInfo.setType(2);
	        taskInfo.setData(map);
	        
	        WebserviceUtil.instance.getExecuteService("http://crm.highso.org:8021/remoting/executeService").setTask(taskInfo);
	        logger.info("sendMessage2Crm___success"+ customer.getEmail());
		}
		
		public  String getDomainString(){
			if(configurator.getProjectURL()!=null){
				if(configurator.getProjectURL().contains("highso.cn")){
					return "highso.cn";
				}else if(configurator.getProjectURL().contains("highso.org")){
					return "highso.org";
				}else if(configurator.getProjectURL().contains("haixue.com")){
					return "haixue.com";
				}else if(configurator.getProjectURL().contains("highso.net.cn")){
					return "highso.net.cn";
				}
			}
			return "";
		}
}
