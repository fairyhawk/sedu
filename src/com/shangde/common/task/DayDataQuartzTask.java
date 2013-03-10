package com.shangde.common.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.shangde.common.service.ConfigService;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

/**
 * 定时任务
 * @author wangchao
 *
 */
public class DayDataQuartzTask {
	
	private IsmsService smsService;
	private ICustomer customerService;
	private IContract contractService;
	private ConfigService configurator;
	private ISubject subjectService;
	private List <Subject> subjects=new  ArrayList<Subject>();
	private StringBuffer s;
	private int a=0;
	private int b=0;
	private int c=0;
	
	/**
	 * 
	 * @author 王超
	 * 功能：定时通知网站管理人员网站信息
	 * @param args
	 */
	protected void infoToManager(){
		try {
			 List subName=new ArrayList();
			 List subZhuCe=new ArrayList();
			 List subDingDan=new ArrayList();
			 List subZhiFu=new ArrayList();
			 boolean status=true;
			//设置时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat mdSdf = new SimpleDateFormat("MM月dd日");
			//设置时间
			Calendar cal = Calendar.getInstance();
			
			cal.add(Calendar.DAY_OF_MONTH, -1);

			
			//设置查询条件
			QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
			queryCustomerCondition.setStartCountTime(sdf.format(cal.getTime()));
			queryCustomerCondition.setEndCountTime(sdf.format(new Date()));
			queryCustomerCondition.setCusType("0");
			
			QueryContractCondition queryContractCondition = new QueryContractCondition();
			queryContractCondition.setStartCountTime(sdf.format(cal.getTime()));
			queryContractCondition.setEndCountTime(sdf.format(new Date()));
			
			subjects=subjectService.getAllSubject();
				


			//得到定时短信的配置信息并拆分
			Map<String,String> telephones = configurator.getTelephoneMap();
			
			Iterator<Entry<String, String>> keyValueIterator = (Iterator<Entry<String, String>>) telephones.entrySet().iterator();
			while(keyValueIterator.hasNext()){
			  Entry<String,String> entry =  keyValueIterator.next();
			  String name = (String)entry.getKey();
			  String telephone = (String)entry.getValue();
			  //拆分得出手机号和专业简称
			  String[] tel = telephone.split(",");
				String newTel = tel[0];
				String subject = tel[1];
				s=new StringBuffer();
				//通过专业名称拼接成短信并发送
				if (subject != null && !"".equals(subject)) {
						queryContractCondition.setPayType(-1);
						String sub[] = subject.split(";");
						for (int j = 0; j < sub.length; j++) {
							
							for(int i=0;i<subjects.size();i++){
								if(status){
									queryCustomerCondition.setSubjectId(subjects.get(i).getSubjectId());
									queryContractCondition.setSubjectId(subjects.get(i).getSubjectId());
									subName.add(i,subjects.get(i).getSubjectName());
									subZhuCe.add(i,customerService.getRegistNumber(queryCustomerCondition));
									queryContractCondition.setConStr(1);
									subDingDan.add(i,contractService.getContractNumberBySubject(queryContractCondition));
									queryContractCondition.setConStr(2);
									subZhiFu.add(i,contractService.getContractNumberBySubject(queryContractCondition));
								
									if(i==(subjects.size()-1)){
										status=false;
									}
								}
								if(sub[j].equals(subjects.get(i).getSubjectName())){
									
									s.append(subName.get(i));
									s.append(":");
									s.append(subZhuCe.get(i));
									s.append(":");
									a+=Integer.parseInt(subZhuCe.get(i).toString());
									s.append(subDingDan.get(i));
									s.append(":");
									b+=Integer.parseInt(subDingDan.get(i).toString());
									
									s.append(subZhiFu.get(i));
									s.append("\n");
									c+=Integer.parseInt(subZhiFu.get(i).toString());
								}
							}												
						}
//						smsService.sendEx(name +"您好,过去24小时内,注册、订单、支付比:" +"\n" + s + "\nHighSo-技术中心",newTel, "", "", "");
						smsService.sendEx(name +"您好,过去24小时内,注册、订单、支付比:"+"\n"+"all"+":"+a+":"+b+":" +c  +"\n" + s + "\nHighSo-技术中心",newTel, "", "", "");
						a=0;
						b=0;
						c=0;
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IsmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
}
