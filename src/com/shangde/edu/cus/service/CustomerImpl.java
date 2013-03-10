package com.shangde.edu.cus.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.CusRankInfo;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.CustomerWithConSizeDTO;
import com.shangde.edu.cus.domain.SubjectCustomerDTO;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.dto.AddressDTO;
import com.shangde.edu.cus.dto.SimpleCustomerDTO;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.exam.service.IOptRecord;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.mail.service.IMail;
import com.shangde.edu.res.service.INotes;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.tk.service.ITaskCus;

/**
 * Customer对象操作实现类
 * User: guoqiang.liu
 * Date: 2010-07-26
 */
@SuppressWarnings("unchecked")
public class CustomerImpl extends BaseService implements ICustomer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 邮件服务对象
	 */
	private IMail mailService;
	
	/**
	 * 流水服务对象
	 */
	private ICashRecord  cashRecordService;
	
	/**
	 * 笔记服务对象
	 */
	private INotes notesService;
	
	/**
	 * 优惠券服务对象
	 */
	private ICpCus cpCusService;
	
	/**
	 * 学习计划服务对象
	 */
	private IStudyPlan studyPlanService;
	
	/**
	 * 试卷服务对象
	 */
	private IExampaperRecord exampaperRecordService;
	
	/**
	 * 考试结果服务对象
	 */
	private IOptRecord optRecordService;
	
	/**
	 * 积分记录服务对象
	 */
	private ITsRecord tsRecordService;
	
	/**
	 * 积分服务对象
	 */
	private ITotolsScore totolsScoreService;
	
	/**
	 * 任务服务对象
	 */
	private ITaskCus taskCusService;
	
	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	
	private ICusCouKpoint cusCouKpointService;
	
	private ICustomer customerService;

	public java.lang.Integer addCustomer(Customer customer) {
		return simpleDao.createEntity("Customer_NS.createCustomer",customer);
    }

    public void delCustomerById(int cusId){
    	simpleDao.deleteEntity("CusCouKpoint_NS.deleteCusCouKpointByCusId",cusId);
        simpleDao.deleteEntity("Customer_NS.deleteCustomerById",cusId);
    }

    public void updateCustomer(Customer customer) {
        simpleDao.updateEntity("Customer_NS.updateCustomer",customer);
    }

	/**
	 * 范昕 2011-08-03
     * 修改Customer 的昵称
     * @param customer 要修改的Customer
     */
	public void updateCustomerName(Customer customer){
		simpleDao.updateEntity("Customer_NS.updateCustomerName", customer);
	}
	
    public Customer getCustomerById(int cusId) {
        return simpleDao.getEntity("Customer_NS.getCustomerById",cusId);
    }

    public List<Customer> getCustomerList(QueryCustomerCondition queryCustomerCondition) {
        return simpleDao.getForList("Customer_NS.getCustomerList",queryCustomerCondition);
    }

	public PageResult getCustomerListByCondition(
			PageQuery queryCustomerCondition) {
		return simpleDao.getPageResult("Customer_NS.getCustomerListByCondition", 
									   "Customer_NS.getCustomerCountByCondition", 
									   queryCustomerCondition);
	}

	public boolean checkOldPwd(Customer customer) {
		if((Integer)simpleDao.getEntity("Customer_NS.checkOldPwd", customer) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Customer getCustomerByEmail(String email) {
		 return simpleDao.getEntity("Customer_NS.getCustomerByEmail",email);
	}
	
	public List<Customer> getCustomerListByMobile(QueryCustomerCondition queryCustomerCondition){
		return simpleDao.getForList("Customer_NS.getCustomerListByMobile", queryCustomerCondition);
	}
	
	public Integer getUIDByLogin(Customer customer) {
		return simpleDao.getEntity("Customer_NS.getUIDByLogin", customer);
	}

	public boolean checkEmail(String email) {
		if((Integer)simpleDao.getEntity("Customer_NS.checkEmail", email) < 1) {
			return true;
		} else {
			return false;
		}
	}
	
   public boolean checkMoblie(String moblie) {
        if((Integer)simpleDao.getEntity("Customer_NS.checkMobile", moblie) < 1) {
            return true;
        } else {
            return false;
        }
    }

	public List<Subject> getSubjectListByCus(int userId) {
		return simpleDao.getForList("Customer_NS.getSubjectListByCus",userId);
	}
	
	public int isComplete(int userId) {
		return simpleDao.getEntity("Customer_NS.isComplete", userId);
	}

	public void sendRegEmail(Customer customer) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", customer.getEmail());
		map.put("date", format.format(new Date()));
		map.put("cusName", customer.getCusName());
		mailService.getMail(IMail.CUS_REG_MAIL, map);
	}
	
    public void setMailService(IMail mailService) {
		this.mailService = mailService;
	}

	public String genericRandomPwd() {
		StringBuffer ranPwd = new StringBuffer(""); 
		Random ran = new Random();
		int location = 0;
		for(int i=0; i<9; i++) {
			location = ran.nextInt(10);
			ranPwd.append(location);
		}
		return ranPwd.toString();
	}

	public void sendForgotPwdEmail(Customer customer) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("cusName", customer.getCusName());
		map.put("email", customer.getEmail());
		map.put("newPwd", customer.getCusPwd());
		map.put("date", format.format(new Date()));
		
		mailService.getMail(IMail.FORGOT_PWD_MAIL, map);
	}

	public String genericConfirmCode() {
		StringBuffer newConfirmationCode = new StringBuffer(""); 
		Random ran = new Random();
		for(int i=0; i<6; i++) {
			newConfirmationCode.append(ran.nextInt(10));
		}
		return newConfirmationCode.toString();
	}

	public int getRegistNumber(QueryCustomerCondition queryCustomerCondition) {
	int number=simpleDao.getEntity("Customer_NS.getRegistNumber", queryCustomerCondition);
	return number;
	}
	public List<SubjectCustomerDTO> getRegistNumberList(QueryCustomerCondition queryCustomerCondition){
		return simpleDao.getForList("Customer_NS.getRegistNumberList", queryCustomerCondition);
	}
	public List getMonthList(){
		
		return simpleDao.getForList("Customer_NS.getMonthList", "");
	}
	public int getMonthRegistNumber(String month){
		return simpleDao.getEntity("Customer_NS.getMonthRegist", month);
	}
	public List getMonthDay(String month){
		return simpleDao.getForList("Customer_NS.getMonthDay", month);
	}
	
	public List getDay(String dateDay){
		
		return simpleDao.getForList("Customer_NS.getDay", dateDay);
	}
	
	public List addBathCustomer(List cusList) throws SQLException{
		return simpleDao.createBatchEntity("Customer_NS.createCustomer", cusList);
	}

    public Customer getCustomerByView(int cusId) {
        return simpleDao.getEntity("Customer_NS.getCustomerByView",cusId);
    }
    
    public List<SimpleCustomerDTO> getSimpleCustomerDTOListByCondition(QueryCustomerCondition queryCustomerCondition){
    	return simpleDao.getForList("Customer_NS.getSimpleCustomerDTOListByCondition", queryCustomerCondition);
    }
    
    //修改---用户
    public void fixFinance(){
    	//修改用户
    	QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
    	List<Customer> cusList =  simpleDao.getForList("Customer_NS.getTestCustomerList", queryCustomerCondition);
    	
    	//开始循环遍历
    	if(cusList != null && cusList.size() > 0){
    		Customer cus  = null;
    		
    		List<Course> userCourseList = null;
    		
    		List<Contract> ctList = null;
    		//---订单
    		List<Contract> ctQList = null;
    		Contract ctTemp = null;
    		
    		QueryContractCondition queryContractCondition = new QueryContractCondition();
    		
    		for(int i = 0; i < cusList.size(); i ++){
    			cus = cusList.get(i);//取出用户
    			
    			//查出该用户的课程集合
    			userCourseList = cusCouKpointService.getCusCouKpointListByCusId(cus.getCusId());//课程集合
    			
    			//查出已付订单集合
    			queryContractCondition.setUserId(cus.getCusId());
    			ctList = simpleDao.getForList("Customer_NS.getPayContractListByCusId", queryContractCondition);
    			
    			//查询所有---订单（第一个保留，剩下的删除）根据用户ID
				ctQList = simpleDao.getForList("Customer_NS.getQuestionContractListByCusId", queryContractCondition);
				
    			if(ctList != null && ctList.size() > 0){//存在已付订单
    				List<Course> couTempList = null;
    				
    				for(int j = 0; j < ctList.size(); j ++){//去掉课程集合中，已购买的课程，剩下的都是赠送的课程
    					ctTemp = ctList.get(j);
    					
    					queryContractCondition.setContractId(ctTemp.getContractId());
    					couTempList = simpleDao.getForList("Customer_NS.getCourseListByContractId", queryContractCondition);//查出购买课程
    					
    					for(int k = 0; k < couTempList.size(); k ++){//去掉相等的课程
    						if(userCourseList.contains(couTempList.get(k))){//去掉
    							userCourseList.remove(couTempList.get(k));
    						}
    					}
    				}
    				
    				if(userCourseList.size() > 0){//当存在赠送课程
        				Date date=new Date();
        				Long cId=date.getTime();
    					for(int j = 0; j < ctQList.size(); j ++){
        					ctTemp = ctQList.get(j);
        					if(j == 0){//修改
        						//修改订单号 
        						ctTemp.setContractId(cus.getCusId()+cId.toString());
        						contractService.updateContract(ctTemp);
        						//生成流水---------------------------------------------------------------
        						CashRecord cashRecord = null;
        						Course course = null;
        						for(int k = 0; k < userCourseList.size(); k ++ ){
        								cashRecord=new CashRecord();
        								course=userCourseList.get(k);
        								cashRecord.setCrInfo("购买《"+course.getTitle()+"》");
        								cashRecord.setPrice(course.getPrice());
        								cashRecord.setContractId(ctTemp.getContractId());
        								cashRecord.setCreateTime(ctTemp.getCreateTime());
        								cashRecord.setCusId(cus.getCusId());
        								cashRecord.setCtId(ctTemp.getId());
        								cashRecord.setCourseId(course.getCourseId());
        								cashRecord.setType(2);//2代表后台赠送
        								this.cashRecordService.addCashRecord(cashRecord);
        							}
        					}else{//删除
        						contractService.delContractById(ctTemp.getId());
        					}
        				}
    				}else{//如果只是修复，删掉---订单，保留以前的
    					//删掉---的订单
        				for(int j = 0; j < ctQList.size(); j ++){
        					ctTemp = ctQList.get(j);
        					contractService.delContractById(ctTemp.getId());
        				}
    				}
    				
    			}else{
    				Date date=new Date();
    				Long cId=date.getTime();
    				for(int j = 0; j < ctQList.size(); j ++){
    					ctTemp = ctQList.get(j);
    					if(j == 0){//修改
    						//修改订单号 
    						ctTemp.setContractId(cus.getCusId()+cId.toString());
    						contractService.updateContract(ctTemp);
    						//生成流水---------------------------------------------------------------
    						CashRecord cashRecord = null;
    						Course course = null;
    						for(int k = 0; k < userCourseList.size(); k ++ ){
    								cashRecord=new CashRecord();
    								course=userCourseList.get(k);
    								cashRecord.setCrInfo("购买《"+course.getTitle()+"》");
    								cashRecord.setPrice(course.getPrice());
    								cashRecord.setContractId(ctTemp.getContractId());
    								cashRecord.setCreateTime(ctTemp.getCreateTime());
    								cashRecord.setCusId(cus.getCusId());
    								cashRecord.setCtId(ctTemp.getId());
    								cashRecord.setCourseId(course.getCourseId());
    								cashRecord.setType(2);//2代表后台赠送
    								this.cashRecordService.addCashRecord(cashRecord);
    						}
    					}else{//删除
    						contractService.delContractById(ctTemp.getId());
    					}
    				}
    			}
    		}
    	}
    }
	   public void cusTypeFix(){
		 //查询出
    	QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
    	List<Customer> cusList =  simpleDao.getForList("Customer_NS.getCusTypeCustomerList", queryCustomerCondition);
    	if(cusList != null && cusList.size() > 0){
    		Customer cus  = null;
    		List<Course> userCourseList = null;	
    		Contract contract=null;
    		CashRecord cashRecord=null;
    		Course course=null;
    		for(int i = 0; i < cusList.size(); i ++){
    			Date date=new Date();
    			Long ctId=date.getTime();
    			cus=cusList.get(i);
    			//添加到订单表中
    			contract=new Contract();
    			contract.setContractId(cus.getCusId()+ctId.toString());
    			contract.setCusId(cus.getCusId());
    			contract.setCreateTime(date);           
    			contract.setContractFrom("后台用户");
    			contract.setPayType(0);
    			contract.setStatus(2);//2代表赠送
    			contract.setContractCutSumMoney(0.0);//减去优惠券应付的价格，折后总金额。
    			contract.setContractSumMoney(0.0);//应付总价，总金额。
    			contract.setCouponMoney(0.0);
    			contract.setUseCoupon(0);//0代表没有使用 1代表使用
    			int cId=this.contractService.addContract(contract);
    			//查出该用户的课程集合
    			userCourseList = cusCouKpointService.getCusCouKpointListByCusId(cus.getCusId());//课程集合
    			for(int j = 0; userCourseList != null && j < userCourseList.size(); j ++){
    				//添加到流水表中
    				cashRecord=new CashRecord();
    				course=userCourseList.get(j);
    				cashRecord.setCrInfo("购买《"+course.getTitle()+"》");
    				cashRecord.setPrice(course.getPrice());
    				cashRecord.setContractId(cus.getCusId()+ctId.toString());
    				cashRecord.setCreateTime(date);
    				cashRecord.setCusId(cus.getCusId());
    				cashRecord.setCourseId(course.getCourseId());
    				cashRecord.setType(2);//2代表后台赠送
    				cashRecord.setCtId(cId);//把订单的id存到流水中
    				this.cashRecordService.addCashRecord(cashRecord);
    			}
    		}
    	}
	}
		
	/**
	 * 删除体验（临时）学员的体验（临时）数据
	 * @param cusId
	 */
	public void recoverTempCustomer(int cusId) {
		recoverTempCustomer(cusId, 0);
	}

	/**
	 * 删除体验（临时）学员除了传入订单的的其他体验（临时）数据
	 * @param cusId
	 * @param ctId
	 */
	public void recoverTempCustomer(int cusId, int ctId) {
		//删除笔记
		notesService.delNotesByCusId(cusId);
		//删除优惠券
		cpCusService.delCpCusByCusId(cusId);
		//删除学习计划
		studyPlanService.delStudyPlanByCusId(cusId);
		//删除考试
		exampaperRecordService.delERecordByCusId(cusId);
		//删除考试关联
		optRecordService.delOptRecordByCusId(cusId);
		//删除知识树的表
		cusCouKpointService.delCusCouKpointByCusId(cusId);
		//删除积分记录
		tsRecordService.delTsRecordByCusId(cusId);
		//积分清零		
//		TotolsScore totolsScore = totolsScoreService.getTotolsScore(cusId);
//		totolsScore.setTsAction(0);
//		totolsScore.setTsCurrent(0);		
//		totolsScoreService.updateTotolsScore(totolsScore);
//		//删除积分
//		totolsScoreService.delTotolsScoreByCusId(cusId);
		//删除学员登录信息
//		loginRecordService.delLoginRecordByCusId(cusId);
		//删除task_cus表
//		taskCusService.deleteTaskCusByCusId(cusId);
		
		if(ctId <= 0) {
			//删除流水
			cashRecordService.delCashRecordByCusId(cusId);
			//删除订单
			contractService.delContractByCusId(cusId);
		} else {
			QueryCashRecordCondition queryCashRecordCondition = new QueryCashRecordCondition();
			queryCashRecordCondition.setUserId(cusId);
			queryCashRecordCondition.setCtId(ctId);
			cashRecordService.delCashRecordExceptCtId(queryCashRecordCondition);
			
			QueryContractCondition queryContractCondition = new QueryContractCondition();
			queryContractCondition.setUserId(cusId);
			queryContractCondition.setId(ctId);
			contractService.delContractExceptCtId(queryContractCondition);
		}
	}
	/**
	 * 更新学员的所在项目
	 */
	public boolean updateCustomerSubject(Customer customer){
		int cnt= simpleDao.update("Customer_NS.updateCustomerSubject", customer);
		if(cnt>0){
			return true;
		}else{
			return true;
		}
	}
	
	/**
	 * 学员统计
	 * @return  customerCount
	 */

	public Integer getCustomerCount(QueryCustomerCondition queryCustomerCondition) {
		// TODO Auto-generated method stub
			int cusCount=0;
		try {
			cusCount = this.simpleDao.getEntity("Customer_NS.getCustomerCount",
					queryCustomerCondition);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cusCount;
		
	}
	/**
     * 修改Customer 的newerflag 新手引导用
     * @param customer 要修改的Customer
     */
    public void updateCustomerNewerflag(Customer customer){
        simpleDao.updateEntity("Customer_NS.updateCustomerNewerFlag",customer);
    }
    

    //获取用户地址列表 
    public List<AddressDTO> GetAddrByCusId(int cusId)
    {
    	List<AddressDTO> list=simpleDao.getForList("Customer_NS.getAddrByUserId", cusId);
    	return list;
    }
	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public INotes getNotesService() {
		return notesService;
	}

	public void setNotesService(INotes notesService) {
		this.notesService = notesService;
	}

	public ICpCus getCpCusService() {
		return cpCusService;
	}

	public void setCpCusService(ICpCus cpCusService) {
		this.cpCusService = cpCusService;
	}

	public IStudyPlan getStudyPlanService() {
		return studyPlanService;
	}

	public void setStudyPlanService(IStudyPlan studyPlanService) {
		this.studyPlanService = studyPlanService;
	}

	public IExampaperRecord getExampaperRecordService() {
		return exampaperRecordService;
	}

	public void setExampaperRecordService(IExampaperRecord exampaperRecordService) {
		this.exampaperRecordService = exampaperRecordService;
	}

	public IOptRecord getOptRecordService() {
		return optRecordService;
	}

	public void setOptRecordService(IOptRecord optRecordService) {
		this.optRecordService = optRecordService;
	}

	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}

	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}

	public IMail getMailService() {
		return mailService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	
    /**
     *   范昕
     * 统计通过某个推广网站或站长注册人数
     * Date : 2011-8-10 16:49:16
     */
    public int getCustomerRegNum(QueryCustomerCondition queryCustomerCondition){
    	return simpleDao.getEntity("Customer_NS.getCustomerRegNum",queryCustomerCondition );
    }

	public List<CustomerWithConSizeDTO> getCustomerWithConSizeDTOList(
			QueryCustomerCondition queryCustomerCondition) {
		return simpleDao.getForList("Customer_NS.getCustomerListByCondition", queryCustomerCondition);
	}

	@Override
	public Date getCustomerRegTimeById(int cusId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Customer_NS.getCustomerRegTimeById", cusId);
	}
	
	public Integer checkFreezeStatus(int cusId) {
		return simpleDao.getEntity("Customer_NS.checkFreezeStatus", cusId);
	}
	
	/**
	 * 根CustomerId查询subject信息
	 */
	public Subject getSubjectByCusId(int cusId){
		return simpleDao.getEntity("Subject_NS.getSubjectByCusId", cusId);
	}

	@Override
	public int getCustomerClassmate(int subjectId) {
		return simpleDao.getEntity("Customer_NS.getCustomerClassmate", subjectId);
	}
	@Override
	public List<CusRankInfo> getCusRandByCusId(int cusId) {
		return simpleDao.getForList("Customer_NS.getCusVideoRankList", cusId);
	}

	@Override
	public void updateCusSignature(int cusId, String content) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cusId", cusId);
		params.put("signature", content);
		simpleDao.update("Customer_NS.updateCusSignature", params);
	}
}
