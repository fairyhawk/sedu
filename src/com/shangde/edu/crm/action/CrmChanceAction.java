package com.shangde.edu.crm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.crm.domain.Chance;
import com.shangde.edu.crm.domain.ChanceUser;
import com.shangde.edu.crm.service.IChance;
import com.shangde.edu.crm.service.IUser;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.web.CustomerWebAction;

public class CrmChanceAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(CustomerWebAction.class);
	/**
	 * 用户实体
	 */
	private IUser chanceUserService;
	private IChance chanceService;
	/**
	 * 声名logger
	 */
	private Logger logger = LoggerFactory.getLogger(CrmChanceAction.class);
	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;

	private String type;// 判断是否立即执行批量

	private String begin; // 开始时间

	private String end;// 结束时间
	
	private String phoneNumber; //手机号码
	private String realName;//真实姓名
	private int subid;
	private String baselocation;

	public String getBaselocation() {
		return baselocation;
	}


	public void setBaselocation(String baselocation) {
		this.baselocation = baselocation;
	}


	public int getSubid() {
		return subid;
	}


	public void setSubid(int subid) {
		this.subid = subid;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	 
	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public ChanceUser getChcUser() {
		return chcUser;
	}


	public void setChcUser(ChanceUser chcUser) {
		this.chcUser = chcUser;
	}


	public Chance getChc() {
		return chc;
	}


	public void setChc(Chance chc) {
		this.chc = chc;
	}
	private ChanceUser chcUser;
	private Chance chc;


	public synchronized String doAfterReg() {
		try {
			/*
			 * int cusId = getLoginUserId(); customer =
			 * customerService.getCustomerById(cusId); // 谢添加 关于指派开始 int[]
			 * chanceUserVal = chanceUserService.addUser(customer);// 谢添加//
			 * 添加到用户库// 销售机会// [0]为用户库id// [1]为是否有指派记录// :1为无记录2为有记录 if
			 * (chanceUserVal[1] == 1) {//新用户从未用手机号注册过的 int xiaoshouId =
			 * chanceUserService.getUserByGuiZe(customer .getSubjectId());//
			 * 获取符合条件的销售人员 if (chanceUserVal[2] != 1)// 如果是乐语用户不用添加 销售机会 {
			 * if(xiaoshouId==0){
			 * xiaoshouId=chanceUserService.getBuQi(customer.getSubjectId()); }
			 * chanceService.addChance(customer, chanceUserVal[0],
			 * xiaoshouId);// 用户库id ,坐席id//如果无指派记录直接按规则添加 } } if
			 * (chanceUserVal[1] == 2) { int XSId =
			 * chanceService.getXSIdByChancePhone(customer .getMobile()); if
			 * (chanceUserVal[2] != 1) { if(XSId==0){
			 * XSId=chanceUserService.getBuQi(customer.getSubjectId()); }
			 * chanceService.addChance(customer, chanceUserVal[0], XSId);//
			 * 用户库id // ,坐席id//如果有记录直接指派给原人 } } // 谢添加 关于指派结束
			 */setResult(new Result<String>(false, "success", null, null));

		} catch (Exception e) {
			setResult(new Result<String>(false, "error", null, null));
			log.error("注册后续操作机会分配错误!", e);
		}
		return "json";
	}


	/**
	 * 定时处理新注册用户
	 */
	public synchronized  void CusDispose(int cusId) {
		try {
			
			Customer customer = customerService.getCustomerById(cusId);
			// 谢添加 关于指派开始
			int[] chanceUserVal = chanceUserService.addUser(customer);// 谢添加//
																		// 添加到用户库//
																		// 销售机会//
																		// [0]为用户库id//
																		// [1]为是否有指派记录//
			if (chanceUserVal[0] != 0) //索引为1 :1为无记录2为有记录
			{
				if (chanceUserVal[1] == 1) {// 新用户从未用手机号注册过的
					logger.info("进入从未注册的");
					int xiaoshouId = chanceUserService.getUserByGuiZe(customer
							.getSubjectId());// 获取符合条件的销售人员
					if (chanceUserVal[2] != 1)// 如果是乐语用户不用添加 销售机会
					{
						if (xiaoshouId == 0) {
							xiaoshouId = chanceUserService.getBuQi(customer
									.getSubjectId());
						}
						chanceService.addChance(customer, chanceUserVal[0],
								xiaoshouId,type);// 用户库id ,坐席id//如果无指派记录直接按规则添加
						
					}
				}
				if (chanceUserVal[1] == 2) {
					int XSId = chanceService.getXSIdByChancePhone(customer
							.getMobile());
					if (chanceUserVal[2] != 1) {
						logger.info("指派给原人");
						if (XSId == 0) {
							XSId = chanceUserService.getBuQi(customer
									.getSubjectId());
						}
						chanceService.addChance(customer, chanceUserVal[0],
								XSId,type);// 用户库id// //// ,坐席id//如果有记录直接指派给原人
						
					}
				}
			}
			else{
				logger.info("user_type为0自然跃过或email记录存在");
			}
			// 谢添加 关于指派结束
			setResult(new Result<String>(false, "success", null, null));

		} catch (Exception e) {
			setResult(new Result<String>(false, "error", null, null));
			log.error("注册后续操作机会分配错误!", e);
		}
	}

	

	/**
	 * 定时执行用户入库，指派销售机会
	 */
	public synchronized void runTimeingChance() {
		logger.info("进入指派机会定时-----------------");
		try {
			HashMap map = chanceUserService.getTime();
			int time = Integer.parseInt(map.get("timing_num").toString());
			Date updateTime = (Date) map.get("update_time");
				List<Integer> list = chanceUserService.getCusTime(type, begin,
						end);
				for (Integer cusId : list) {
					try {
						this.CusDispose(cusId);// 执行指派操作
					//	chanceUserService.updateCusState(cusId);// 更新用户状态
						Thread.sleep(1000);
					} catch (Exception e) {
						logger.error("定时指派程序问题子循环问题" + e.toString());
					}
				}
			//	if ((updateTime.getTime() + time* 60 * 1000) <= System
						//.currentTimeMillis()) {
				    chanceUserService.updateCrmTime();
					List<Chance> chances=chanceService.getChanceNoAssign(type,begin,end);
					// 更新定时时间
					for (Chance chance : chances) {
						if ((chance.getChanceStime().getTime() + time * 60 * 1000) <= System
								.currentTimeMillis()) {
							logger.info("正式进入更新循环");
						try {
							chance.setChanceUtime(new Date());
							chance.setUserId(chance.getBackCusId());
							chanceService.updateChance(chance);
							chanceService.addRecord(chance.getBackCusId(), chance.getId());
							//this.CusDispose(cusId);// 执行指派操作
						//	chanceUserService.updateCusState(cusId);// 更新用户状态
						} catch (Exception e) {
							logger.error("定时指派程序问题子循环问题" + e.toString());
						}
					}
					//}
				}	
		} catch (Exception e) {
			logger.error("定时指派程序问题" + e.toString());			
		}
	}
	
	
	public synchronized  void runTimeingChanceAll() {
		logger.info("进入指派机会定时指定即时运行-----------------");
		try {
			if (type != null && type.equals("all")) {
				List<Integer> list = chanceUserService.getCusTime(type, begin,
						end);
				//chanceUserService.updateCrmTime();// 更新定时时间
				for (Integer cusId : list) {
					try {
						this.CusDispose(cusId);// 执行指派操作
					//	chanceUserService.updateCusState(cusId);// 更新用户状态
					} catch (Exception e) {
						logger.error("定时指派程序问题子循环问题" + e.toString());
					}
				}
			}
			// 如果是执行全部未分配的话
			if (type != null && type.equals("all")) {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();
				out.write("指派执行成功");
			}
		} catch (Exception e) {
			logger.error("定时指派程序问题" + e.toString());
			if (type != null && type.equals("all")) {
				try {
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setCharacterEncoding("gbk");
					PrintWriter out = response.getWriter();
					out.write("指派执行异常");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	
	public void addMessageUser(){
		
	}

	public IUser getChanceUserService() {
		return chanceUserService;
	}

	public void setChanceUserService(IUser chanceUserService) {
		this.chanceUserService = chanceUserService;
	}

	public IChance getChanceService() {
		return chanceService;
	}

	public void setChanceService(IChance chanceService) {
		this.chanceService = chanceService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void verifyMessage() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
			if(phoneNumber!=null){
				List<ChanceUser> userList=chanceUserService.getUserByPhone(phoneNumber);
				if(userList!=null&&userList.size()>0){
					boolean v=false;
					String mes="1";
					Map<String,String> Params=new HashMap<String,String>();
					Params.put("mes", mes);
					Params.put("mobile", phoneNumber);
					for(ChanceUser l:userList){
						if(l.getMesStatus()==1){
							v=true;
						}
					}
					if(v){
						chanceUserService.updateMes(Params);
						out.print("alreadySuccess");				
					}else{
						chanceUserService.updateMes(Params);
						out.print("Success");
					}
				}else{
					chcUser=new ChanceUser();
					chcUser.setMobile(phoneNumber);
					chcUser.setUserType(4);
					chcUser.setSubjectId(subid);
					chcUser.setRegTime(new Date());
					chcUser.setCusId(0);//新用户
					chcUser.setRealName(realName);
					chcUser.setMesStatus(1);					
					Integer crmUserId=chanceUserService.addUser(chcUser);
					chc=new Chance();	
					chc.setCrmUserId(crmUserId);
					chc.setOrigin(4);
					chc.setSubjectId(subid);//
					chc.setFollowStatus(2);
					chc.setUserId(0);
					chc.setBackCusId(0);
					chc.setChanceStime(new Date());
					chc.setWebName(baselocation);
					chanceService.addChc(chc);
					out.print("Success");
				}
				out.flush();
				out.close();
			}
		}catch(Exception e){
			logger.error("程序问题" + e.toString());		
		}
	}
}
