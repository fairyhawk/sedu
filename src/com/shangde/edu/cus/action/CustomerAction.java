package com.shangde.edu.cus.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.IPUtil;
import com.shangde.common.util.MD5;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Gmrecord;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cou.service.IGmrecord;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryReProblemCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.CustomerDTO;
import com.shangde.edu.cus.domain.CustomerWithConSizeDTO;
import com.shangde.edu.cus.domain.LoginRecord;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.cus.dto.AddressDTO;
import com.shangde.edu.cus.dto.CustomerCountDTO;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ILoginRecord;
import com.shangde.edu.cus.service.IProblem;
import com.shangde.edu.cus.service.IReProblem;
import com.shangde.edu.cus.service.IStudyPlan;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.exam.service.IOptRecord;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.mail.service.IMail;
import com.shangde.edu.res.service.INotes;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sms.webService.SmsServiceStub.SendExResp;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.dto.SubjectCountsDTO;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.IUser;
import com.shangde.edu.tk.service.ITaskCus;


/**
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CustomerAction extends CommonAction {
	/**
	 * 查询条件
	 */
	private String startTime;
	private String endTime;
	private String sendLinks;
	private String sendInfo;
	private String startHH = " 00:00:00";
	private String endHH = " 23:59:59";
	int zsSum = 0; // 声明支付方式变量 赠送数
	int zfbSum = 0; // 支付宝下单总数
	int hdfkSum = 0; // 货到付款下单总数
	int wyzxSum = 0; // 网银在线
	int kqSum = 0; // 快钱
	int yfZsSum = 0; // 已付赠送
	int yfZfbSum = 0; // 已付支付宝
	int yfHdfkSum = 0; // 已付货到付款
	int yfWyzxSum = 0; // 已付网银在线
	int yfKqSum = 0; // 已付快钱
	int SUM = 0;
	int YFSUM = 0;
	int cusAll = 0;
	int cusWbAll = 0;
	int TFSUM = 0;	

	/**
	 * 用户服务对象
	 */
	private IsmsService smsService;

	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;

	/**
	 * 邮件服务对象
	 */
	private IMail mailService;

	/**
	 * 用户实体
	 */
	private Customer customer = new Customer();

	/**
	 * 用户查询条件
	 */
	private QueryCustomerCondition queryCustomerCondition;

	/**
	 * 用户列表
	 */
	private List<Customer> customerList = new ArrayList<Customer>();
	private List<Customer> newCusList = new ArrayList<Customer>();
	private List<Integer> cusList = new ArrayList<Integer>();
	private List<CashRecordDTO> cashList = new ArrayList<CashRecordDTO>();
	private List<CashRecordDTO> YFcashList = new ArrayList<CashRecordDTO>();

	/**
	 * id数组
	 */
	private int[] ids;

	/**
	 * 新密码
	 */
	private String newPwd;
	private String newMD5Pwd;

	/**
	 * 声明订单服务
	 */
	private IContract contractService;
	/**
	 * DTO
	 */
	private CustomerCountDTO customerCountDTO;
	private CustomerCountDTO customerCountSum;
	private List<CustomerCountDTO> customerCountDTOList = new ArrayList<CustomerCountDTO>();
	private CustomerDTO customerDTO = new CustomerDTO();
	private CustomerDTO hdfkCustomerDTO = new CustomerDTO();
	private CustomerDTO zfbCustomerDTO = new CustomerDTO();
	/**
	 * 查询条件
	 */
	private QueryContractCondition queryContractCondition;
	private QueryCashRecordCondition queryCashRecordCondition;
	private QueryContractCondition zfbPayedQueryContractCondition;
	private QueryContractCondition zfbQueryContractCondition;
	private QueryContractCondition hdfkPayedQueryContractCondition;
	private QueryContractCondition hdfkQueryContractCondition;
	private QueryContractCondition wyzxQueryContractCondition;
	private QueryContractCondition wyzxPayedQueryContractCondition;

	/**
	 * 月List
	 */
	private List<Customer> monthList = new ArrayList<Customer>();
	/**
	 * 月参数
	 */
	private String monthDay;
	/**
	 * 日参数
	 */
	private String dateDay;

	/**
	 * 支付类型
	 */
	private int payType = -1;
	/**
	 * List
	 */
	private List<Customer> monthDayList = new ArrayList<Customer>();
	/**
	 * 每天List
	 */
	private List<Customer> DayList = new ArrayList<Customer>();
	/**
	 * 后台用户
	 */
	private User user;
	/**
	 * 用户服务
	 */
	private IUser userService;
	/**
	 * 赠送方式
	 */
	private String giveAway;
	/**
	 * 课程实体
	 */
	private Course course;

	/**
	 * 课程服务
	 */
	private ICourse courseService;
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	/**
	 * 服务
	 */
	private ICusCouKpoint cusCouKpointService;
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	/**
	 * 声明流水服务
	 */
	private ICashRecord cashRecordService;
	/**
	 * 购买记录服务
	 */
	private IGmrecord gmrecordService;
	/**
	 * 已存在的customerList
	 */
	private List<Customer> existCusList = new ArrayList<Customer>();
	private int newCusId = 0;
	private String[] cus = null;
	private int cusId;
	private int raBatch;

	/**
	 * 笔记服务对象
	 */
	private INotes notesService;
	/**
	 * 活动服务对象
	 */
	private ICpCus cpCusService;
	/**
	 * 学习计划服务对象
	 */
	private IStudyPlan studyPlanService;
	/**
	 * 考试服务
	 */
	private IOptRecord optRecordService;
	/**
	 * 考试服务
	 */
	private IExampaperRecord exampaperRecordService;
	/**
	 * 积分的服务
	 */
	private ITotolsScore totolsScoreService;
	/**
	 * 积分记录的服务
	 */
	private ITsRecord tsRecordService;
	/**
	 * 登录信息记录
	 */
	private ILoginRecord loginRecordService;
	/**
	 * 删除成功
	 */
	private String delSucess;
	/**
	 * 学科集合
	 */
	private List<Subject> subjectList;
	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	/**
	 * 切换参数
	 */
	private int location = 0;
	private int dateLocation = -1;

	private List<SubjectCountsDTO> subjectCountsDTOList = new ArrayList<SubjectCountsDTO>();
	/**
	 * 问题的服务
	 */
	private IProblem problemService;
	/**
	 * 回复问题的服务
	 */
	private IReProblem reProblemService;
	/**
	 * 任务
	 */
	private ITaskCus taskCusService;
	/**
	 * 售卖方式关系服务
	 */
	private ISellCou sellCouService;
	private QuerySellCouCondition querySellCouCondition;
	/**
	 * 修改学员
	 * 
	 * @return String
	 * @thorows Exception
	 */
	private int fromURLType;

	/**
	 * 专业服务
	 * 
	 * @return
	 */
	private QuerySubjectCondition querySubjectCondition;

	private InputStream excelFile;

	List<CustomerWithConSizeDTO> cwcsList = new ArrayList<CustomerWithConSizeDTO>();
	
	List<AddressDTO> addressList=new ArrayList<AddressDTO>();

	public List<AddressDTO> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<AddressDTO> addressList) {
		this.addressList = addressList;
	}

	public String updateCustomer() {
		try {
			customerService.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 跳转到免费赠送课程页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String freeCourse() {
		String Result = "";
		try {
			if (customer.getCusType() == 1) {
				// 内部学员
				Result = "freeCourse1";
			} else {
				// 注册学员
				Result = "freeCourse";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return Result;
	}

	/**
	 * 修改密码
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String updatePwd() {
		try {
			customer = customerService.getCustomerById(customer.getCusId());
			if (customer != null) {
				customer.setCusPwd(MD5.getMD5(newPwd));
			}
			customerService.updateCustomer(customer);
			return "changeSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 修改MD5密码
	 * 
	 * @return
	 */
	public String updateMD5Pwd() {
		try {
			customer = customerService.getCustomerById(customer.getCusId());
			if (customer != null) {
				customer.setCusPwd(newMD5Pwd);
			}
			customerService.updateCustomer(customer);
			return "changeSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 根据ids删除学员
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String deleteCustomer() {
		try {
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					customerService.delCustomerById(ids[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "relist";
	}

	/**
	 * 
	 * 分页查询 查询学员列表
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String showCustomerList() {
		try {
			subjectList = subjectService.getAllSubject();
			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			String cusType = getQueryCustomerCondition().getCusType();
			int subjectId = getQueryCustomerCondition().getSubjectId();
			int startNumber = getQueryCustomerCondition().getStartNumber();
			int endNumber = getQueryCustomerCondition().getEndNumber();
			String cusName=customer.getCusName();
			if (customer.getCusFromUrl() != null
					&& !"".equals(customer.getCusFromUrl().trim())) {
				if (customer.getCusFromUrl().equals("1")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.org.cn");
				} else if (customer.getCusFromUrl().equals("2")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.cn");
				} else if (customer.getCusFromUrl().equals("3")) {
					this.getQueryCustomerCondition()
							.setCusFromUrl("highso.org");
				} else if (customer.getCusFromUrl().equals("4")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.com.cn");
				} else if (customer.getCusFromUrl().equals("5")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.net.cn");
				}
			}
			if (startNumber != 0) {
				this.getQueryCustomerCondition().setStartNumber(startNumber);
			}
			if (endNumber != 0) {
				this.getQueryCustomerCondition().setEndNumber(endNumber);
			}

			if (endNumber == 0) {
				this.getQueryCustomerCondition().setEndNumber(999999);
			}
			if (subjectId == 0) {
				this.getQueryCustomerCondition().setSubjectId(-1);
			}
			if (dateDay != null && !"".equals(dateDay)) {
				getQueryCustomerCondition().setDateDay(dateDay);
			}
			if (email != null) {
				getQueryCustomerCondition().setEmail(email.trim());
			}
			if (mobile != null) {
				getQueryCustomerCondition().setMobile(mobile.trim());
			}
			if (startTime != null && !"".equals(startTime) && startHH != null
					&& !startHH.equals("")) {

				startTime = startTime + startHH;
				this.getQueryCustomerCondition().setStartTime(startTime);

			}
			if (endTime != null && !"".equals(endTime) && endHH != null
					&& !endHH.equals("")) {

				endTime = endTime + endHH;
				this.getQueryCustomerCondition().setEndTime(endTime);
			}
			if (cusType != null && !"".equals(cusType)) {
				//6为未支付类型，但数据库没有对应字段描述。重设为0，即注册学员，再通过查询筛选出未支付学员
				if(cusType.trim().equals("6")){
					cusType="0";
				}
				getQueryCustomerCondition().setCusType(cusType.trim());
			}
			else{
				//如果是查询注册学员的操作
				if(getQueryCustomerCondition().getVisitType()==1||getQueryCustomerCondition().getVisitType()==2){
					getQueryCustomerCondition().setCusType("0");
				}
			}
			if(cusName!=null&&cusName!=""){
				getQueryCustomerCondition().setCusName(cusName);
			}
			this.getQueryCustomerCondition().setPageSize(30);
			setPage(customerService
					.getCustomerListByCondition(getQueryCustomerCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			if (endHH != null && !endHH.equals("") && endTime != null
					&& !"".equals(endTime)) {

				endTime = endTime.substring(0, endTime.indexOf(endHH));

			}
			if (startHH != null && !startHH.equals("") && startTime != null
					&& !"".equals(startTime)) {

				startTime = startTime.substring(0, startTime.indexOf(startHH));

			}
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "list";
	}
	
	/**
	 * 查询学员列表，为外呼人员提供，查询学员的统计信息。
	 */
	public String showCustomerStatsList(){
		try {
			subjectList = subjectService.getAllSubject();
			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			
			if (customer.getCusFromUrl() != null
					&& !"".equals(customer.getCusFromUrl().trim())) {
				if (customer.getCusFromUrl().equals("1")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.org.cn");
				} else if (customer.getCusFromUrl().equals("2")) {
					this.getQueryCustomerCondition().setCusFromUrl("highso.cn");
				} else if (customer.getCusFromUrl().equals("3")) {
					this.getQueryCustomerCondition()
							.setCusFromUrl("highso.org");
				} else if (customer.getCusFromUrl().equals("4")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.com.cn");
				} else if (customer.getCusFromUrl().equals("5")) {
					this.getQueryCustomerCondition().setCusFromUrl(
							"highso.net.cn");
				}
			}
			
			if (dateDay != null && !"".equals(dateDay)) {
				getQueryCustomerCondition().setDateDay(dateDay);
			}
			if (email != null) {
				getQueryCustomerCondition().setEmail(email.trim());
			}
			if (mobile != null) {
				getQueryCustomerCondition().setMobile(mobile.trim());
			}			
			
			this.getQueryCustomerCondition().setSubjectId(-1);
			this.getQueryCustomerCondition().setEndNumber(999999);
			
			this.getQueryCustomerCondition().setPageSize(30);
			setPage(customerService
					.getCustomerListByCondition(getQueryCustomerCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			if (endHH != null && !endHH.equals("") && endTime != null
					&& !"".equals(endTime)) {

				endTime = endTime.substring(0, endTime.indexOf(endHH));

			}
			if (startHH != null && !startHH.equals("") && startTime != null
					&& !"".equals(startTime)) {

				startTime = startTime.substring(0, startTime.indexOf(startHH));

			}
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return "showCustomerStatsList";
	}
	

	/**
	 * 导出订单到excel
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public String exportExcelFile() {
		// 查询条件
		subjectList = subjectService.getAllSubject();
		String email = getQueryCustomerCondition().getEmail();
		String mobile = getQueryCustomerCondition().getMobile();
		String cusType = getQueryCustomerCondition().getCusType();
		int subjectId = getQueryCustomerCondition().getSubjectId();
		int startNumber = getQueryCustomerCondition().getStartNumber();
		int endNumber = getQueryCustomerCondition().getEndNumber();
		String cusName=customer.getCusName();
		if (customer.getCusFromUrl() != null
				&& !"".equals(customer.getCusFromUrl().trim())) {
			if (customer.getCusFromUrl().equals("1")) {
				this.getQueryCustomerCondition().setCusFromUrl("highso.org.cn");
			} else if (customer.getCusFromUrl().equals("2")) {
				this.getQueryCustomerCondition().setCusFromUrl("highso.cn");
			} else if (customer.getCusFromUrl().equals("3")) {
				this.getQueryCustomerCondition().setCusFromUrl("highso.org");
			} else if (customer.getCusFromUrl().equals("4")) {
				this.getQueryCustomerCondition().setCusFromUrl("highso.com.cn");
			} else if (customer.getCusFromUrl().equals("5")) {
				this.getQueryCustomerCondition().setCusFromUrl("highso.net.cn");
			}
		}
		if (startNumber != 0) {
			this.getQueryCustomerCondition().setStartNumber(startNumber);
		}
		if (endNumber != 0) {
			this.getQueryCustomerCondition().setEndNumber(endNumber);
		}

		if (endNumber == 0) {
			this.getQueryCustomerCondition().setEndNumber(999999);
		}
		if (subjectId == 0) {
			this.getQueryCustomerCondition().setSubjectId(-1);
		}
		if (dateDay != null && !"".equals(dateDay)) {
			getQueryCustomerCondition().setDateDay(dateDay);
		}
		if (email != null) {
			getQueryCustomerCondition().setEmail(email.trim());
		}
		if (mobile != null) {
			getQueryCustomerCondition().setMobile(mobile.trim());
		}
		if (startTime != null && !"".equals(startTime) && startHH != null
				&& !startHH.equals("")) {

			startTime = startTime + startHH;
			this.getQueryCustomerCondition().setStartTime(startTime);

		}
		if (endTime != null && !"".equals(endTime) && endHH != null
				&& !endHH.equals("")) {

			endTime = endTime + endHH;
			this.getQueryCustomerCondition().setEndTime(endTime);
		}
		if (cusType != null && !"".equals(cusType)) {
			//6为未支付类型，但数据库没有对应字段描述。重设为0，即注册学员，再通过查询筛选出未支付学员
			if(cusType.trim().equals("6")){
				cusType="0";
			}
			getQueryCustomerCondition().setCusType(cusType.trim());
		}else{
			//如果是查询注册学员的操作
			if(getQueryCustomerCondition().getVisitType()==1||getQueryCustomerCondition().getVisitType()==2){
				getQueryCustomerCondition().setCusType("0");
			}
		}
		getQueryCustomerCondition().setQueryFlag(1);
		if(cusName!=null&&cusName!=""){
			getQueryCustomerCondition().setCusName(cusName);
		}
		//this.getQueryCustomerCondition().setPageSize(5000);
		cwcsList = customerService.getCustomerWithConSizeDTOList(queryCustomerCondition);
		if (endHH != null && !endHH.equals("") && endTime != null
				&& !"".equals(endTime)) {

			endTime = endTime.substring(0, endTime.indexOf(endHH));

		}
		if (startHH != null && !startHH.equals("") && startTime != null
				&& !"".equals(startTime)) {

			startTime = startTime.substring(0, startTime.indexOf(startHH));

		}
		subjectList = subjectService
				.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
		// 初始化一个集合用来存放查询出的订单列表
		// 根据条件查询订单列表
		createExcelFile();
		// 初始化时间条件
		return "exportExcelCus";
	}

	/**
	 * 学员列表excel
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public void createExcelFile() {
		try {
			// 格式化时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			// 下次修改内容
			String[] headName = { "昵称","电子邮箱", "注册项目", "注册域名", "登录地区", "手机", "登陆次数",
					"注册时间", "支付数/订单数" };
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			// 创建表头
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			for (int i = 0; i < headName.length; i++) {
				cell = row.createCell((short) i);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(headName[i]);
			}
			for (int i = 0; i < cwcsList.size(); i++) {
				row = sheet.createRow(i + 1);
				//昵称
				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getCusName());
				// 电子邮箱
				cell = row.createCell((short) 1);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getEmail());
				// //注册项目
				cell = row.createCell((short) 2);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (null != cwcsList.get(i).getCusSubject()) {
					cell.setCellValue(cwcsList.get(i).getCusSubject()
							.getSubjectName());
				} else {
					cell.setCellValue("--");
				}
				// 注册域名
				cell = row.createCell((short) 3);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getCusFromUrl());
				// 登录地区
				cell = row.createCell((short) 4);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (cwcsList.get(i).getLoginRecordList().size() > 0) {
					cell.setCellValue(cwcsList.get(i).getLoginRecordList().get(
							0).getAddress());
				} else {
					cell.setCellValue("--");
				}
				// 手机
				cell = row.createCell((short) 5);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getMobile());
				// 登陆次数
				cell = row.createCell((short) 6);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getLoginTimes());
				// 注册时间
				cell = row.createCell((short) 7);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(sdf.format(cwcsList.get(i).getRegTime()));
				// 支付数/订单数
				cell = row.createCell((short) 8);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(cwcsList.get(i).getContractStatus() + "|"
						+ cwcsList.get(i).getContractCount());
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFile(bais);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页查询Json查询手机号
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String showCustomerListJson() {
		try {

			String email = getQueryCustomerCondition().getEmail();
			String mobile = getQueryCustomerCondition().getMobile();
			String cusType = getQueryCustomerCondition().getCusType();
			int subjectId = getQueryCustomerCondition().getSubjectId();
			int startNumber = getQueryCustomerCondition().getStartNumber();
			int endNumber = getQueryCustomerCondition().getEndNumber();

			if (startNumber != 0) {
				this.getQueryCustomerCondition().setStartNumber(startNumber);
			}
			if (endNumber != 0) {
				this.getQueryCustomerCondition().setEndNumber(endNumber);
			}

			if (endNumber == 0) {
				this.getQueryCustomerCondition().setEndNumber(999999);
			}
			if (subjectId == 0) {
				this.getQueryCustomerCondition().setSubjectId(-1);
			}
			if (dateDay != null && !"".equals(dateDay)) {
				getQueryCustomerCondition().setDateDay(dateDay);
			}
			if (email != null) {
				getQueryCustomerCondition().setEmail(email.trim());
			}
			if (mobile != null) {
				getQueryCustomerCondition().setMobile(mobile.trim());
			}
			if (cusType != null && !"".equals(cusType)) {
				getQueryCustomerCondition().setCusType(cusType.trim());
			}
			if (startTime != null && !"".equals(startTime)) {
				startTime = startTime + startHH;
				this.getQueryCustomerCondition().setStartTime(startTime);
			}
			if (endTime != null && !"".equals(endTime)) {
				endTime = endTime + endHH;
				this.getQueryCustomerCondition().setEndTime(endTime);
			}
			this.getQueryCustomerCondition().setPageSize(30);
			setPage(customerService
					.getCustomerListByCondition(getQueryCustomerCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}

			List<Map> dataList = new ArrayList<Map>();
			for (int a = 0; a < getPage().getPageResult().size(); a++) {
				Map map = new HashMap();
				Customer cus = (Customer) getPage().getPageResult().get(a);
				map.put("email", cus.getEmail());
				map.put("mobile", cus.getMobile());
				dataList.add(map);
			}

			this.setResult(new Result(true, new Integer(getPage()
					.getTotalRecord()).toString(), new Integer(getPage()
					.getTotalPage()).toString(), dataList));

			return "json";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	/**
	 * 根据专业ID查找当前条件下的注册用户数量
	 * 
	 * @param subjectId
	 *            cusType 0 注册学员 1 内部学员 3 全部
	 */
	private void selRegCusNumbers(int subjectId) {

		for (int cusType = 0; cusType <= 4; cusType++) { // 根据当前专业 查询专业下 内部数量
			// 外部数量 总数量

			if (cusType == 0) {
				this.getCustomerInfo(subjectId, cusType);
			} else if (cusType == 1) {
				this.getCustomerInfo(subjectId, cusType);
				cusType++;
			} else if (cusType == 3) {
				this.getCustomerInfo(subjectId, cusType);
			} else if (cusType == 4) {
				this.getCustomerInfo(subjectId, cusType);
				this.getCustomerInfo(subjectId, 100);
			}
		}
	}

	/**
	 * @author wangzheng 学员统计附加查询条件
	 */
	private void selectCondition() {
		getSelectTime1();
		getSelectTime(); // 调用时间附加条件
	}

	private void getSelectTime1() {

		if (startTime != null) { // 如果初次查询时间不为空 则添加查询条件 即 当天时间
			if (startTime.length() != 0) {
				this.getQueryCustomerCondition().setStartCountTime(
						startTime + startHH);
				this.getQueryContractCondition().setStartCountTime(
						startTime + startHH);
			}
		}
		if (endTime != null) {
			if (endTime.length() != 0) {
				this.getQueryCustomerCondition().setEndCountTime(
						endTime + endHH);
				this.getQueryContractCondition().setEndCountTime(
						endTime + endHH);
			}
		}
	}

	/**
	 * @author wangzheng 学员统计 附加时间查询条件 当天 一周 当月 三月
	 */
	private void getSelectTime() {
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
		Calendar todayDate = Calendar.getInstance(); // 当天时间对象
		Calendar weekDate = Calendar.getInstance(); // 周时间对象
		Calendar monthDate = Calendar.getInstance(); // 当月时间对象
		Calendar threeMonthDate = Calendar.getInstance(); // 三个月时间对象

		Date today = todayDate.getTime(); // 获取时间
		String todayStrartTime = dateFm.format(today); // 格式化当天开始时间
		String todayEndTime = dateFm.format(today); // 格式化当天结束时间
		todayStrartTime = todayStrartTime + startHH; // 完整开始时间
		todayEndTime = todayEndTime + endHH; // 完整结束时间

		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		weekDate.add(Calendar.DAY_OF_MONTH, -7); // 一周时间处理
		Date week = weekDate.getTime();
		String weekStartTime = dateFmt.format(week);

		monthDate.add(Calendar.MONTH, -1); // 一月时间处理
		Date month = monthDate.getTime();
		String monthStartTime = dateFmt.format(month);

		threeMonthDate.add(Calendar.MONTH, -3); // 三月时间处理
		Date threeMonth = threeMonthDate.getTime();
		String threeMonthStartTime = dateFmt.format(threeMonth);
		if (location == 1) { // 当天查询条件
			this.getQueryCustomerCondition().setStartCountTime(todayStrartTime);
			this.getQueryCustomerCondition().setEndCountTime(todayEndTime);

			this.getQueryContractCondition().setStartCountTime(todayStrartTime);
			this.getQueryContractCondition().setEndCountTime(todayEndTime);
		} else if (location == 2) { // 一周查询条件
			this.getQueryCustomerCondition().setStartCountTime(weekStartTime);
			this.getQueryCustomerCondition().setEndCountTime(todayStrartTime);

			this.getQueryContractCondition().setStartCountTime(weekStartTime);
			this.getQueryContractCondition().setEndCountTime(todayStrartTime);

		} else if (location == 3) {
			this.getQueryCustomerCondition().setStartCountTime(monthStartTime);
			this.getQueryCustomerCondition().setEndCountTime(todayStrartTime);

			this.getQueryContractCondition().setStartCountTime(monthStartTime);
			this.getQueryContractCondition().setEndCountTime(todayStrartTime);
		} else if (location == 4) {
			this.getQueryCustomerCondition().setStartCountTime(
					threeMonthStartTime);
			this.getQueryCustomerCondition().setEndCountTime(todayStrartTime);

			this.getQueryContractCondition().setStartCountTime(
					threeMonthStartTime);
			this.getQueryContractCondition().setEndCountTime(todayStrartTime);
		} else if (location == 0) {
			this.getQueryCustomerCondition().setStartCountTime("");
			this.getQueryCustomerCondition().setEndCountTime("");

			this.getQueryContractCondition().setStartCountTime("");
			this.getQueryContractCondition().setEndCountTime("");
			this.getQueryContractCondition().setPayType(-1);
		}
	}

	/**
	 * @author wangzheng 学员统计数量
	 * @param subjectId
	 * @param cusType
	 */
	private void getCustomerInfo(int subjectId, int cusType) {
		Subject subject = this.subjectService.getSubjectById(subjectId); // 查询统计当前的专业
		this.getQueryCustomerCondition().setSubjectId(subjectId); // 查询当前专业下学员，添加专业ID
		this.getQueryCustomerCondition().setCusType("" + cusType); // 添加查询学员类型
		this.customerCountDTO.setSubjectName(subject.getSubjectName()); // 添加专业名字

		if (cusType == 0) { // 注册
			this.selectCondition(); // 调用查询附加条件方法

			this.customerCountDTO.setWbRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition())); // 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getWbRegCustomer();
			cusWbAll += this.customerCountDTO.getWbRegCustomer();

		} else if (cusType == 1) { // 面授
			this.selectCondition();
			this.customerCountDTO.setNbRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition())); // 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getNbRegCustomer();
		} else if (cusType == 3) { // 内部体验
			this.selectCondition();
			this.customerCountDTO.setLsRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition()));// 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getLsRegCustomer();
		} else if (cusType == 4) { // 4内部体验(三十天)
			this.selectCondition();
			this.customerCountDTO.setLsCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition()));// 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getLsCustomer();
		} else if (cusType == 5) {
			this.selectCondition(); // 调用查询附加条件方法
			this.customerCountDTO.setAllRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition()));// 调用查询方法，添加查询结果
			cusAll += this.customerCountDTO.getAllRegCustomer();
		} else {
			this.selectCondition(); // 调用查询附加条件方法
			this.customerCountDTO.setAllRegCustomer(this.customerService
					.getCustomerCount(this.getQueryCustomerCondition()));// 调用查询方法，添加查询结果
		}
	}

	/**
	 * 得到全部订单方法
	 */
	private void getContractAll() {
		cashList = this.contractService.getContractSum(this
				.getQueryContractCondition());
		YFcashList = this.contractService.getYfContractSum(this
				.getQueryContractCondition());
	}
	
	private void getContractYFSum(int subjectId){   //查询各个专业 各个类型已付订单数
		int YFZFB = 0;
		int YFPOST = 0;
		int YFCB = 0;
		int YFKQ = 0;
		int YfContractSum = 0;
		
		int TFZFB = 0;
		for (int i = 0; i < YFcashList.size(); i++) {
			if (YFcashList.get(i).getPayType() == 1
					&& YFcashList.get(i).getSubjectId() == subjectId) { // 支付宝订单处理

					if (YFcashList.get(i).getStatus() == 3) {
						TFZFB++;
						TFSUM++;
					}else {
						YFZFB++;
						yfZfbSum++;
						YfContractSum++; // 当前专业已付款总数
						YFSUM++;
					}
			} else if (YFcashList.get(i).getPayType() == 2
					&& YFcashList.get(i).getSubjectId() == subjectId) { // 货到付款订单处理

					if (YFcashList.get(i).getStatus() == 3) {
						TFZFB++;
						TFSUM++;
					}else {
						YFPOST++;
						yfHdfkSum++;
						YfContractSum++; // 当前专业已付款总数
						YFSUM++;
					}
			} else if (YFcashList.get(i).getPayType() == 3
					&& YFcashList.get(i).getSubjectId() == subjectId) { // 网银在线订单处理

					if (YFcashList.get(i).getStatus() == 3) {
						TFZFB++;
						TFSUM++;
					}else {
						YFCB++;
						yfWyzxSum++;
						YfContractSum++; // 当前专业已付款总数
						YFSUM++;
					}
			} else if (YFcashList.get(i).getPayType() == 4
					&& YFcashList.get(i).getSubjectId() == subjectId) { // 快钱订单处理

					if (YFcashList.get(i).getStatus() == 3) {
						TFZFB++;
						TFSUM++;
					}else {
						YFKQ++;
						yfKqSum++;
						YfContractSum++; // 当前专业已付款总数
						YFSUM++;
					}
			}
		}
		this.customerCountDTO.setYfZfbSum(YFZFB); // 当前 已付专业支付宝总数
		this.customerCountDTO.setYfWyzxSum(YFCB); // 当前专业 已付网银在线总数
		this.customerCountDTO.setYfKqSum(YFKQ); // 当前专业 已付快钱总数
		this.customerCountDTO.setYfHdfkSum(YFPOST); // 当前专业货到付款 总数
		this.customerCountDTO.setYfSubjectContractSum(YfContractSum); // 当前专业
		this.customerCountSum.setYfWyzxSum(yfWyzxSum); // 所有专业已付网银在线总计
		this.customerCountSum.setYfKqSum(yfKqSum); // 所有专业已付快钱总计
		this.customerCountSum.setYfHdfkSum(yfHdfkSum); // 已付货到付款总计
		this.customerCountSum.setYfZfbSum(yfZfbSum); // 已付支付宝总计
		this.customerCountSum.setYfContractSum(YFSUM); // 所有专业已付订单总计
		this.customerCountDTO.setTfZfb(TFZFB);
		this.customerCountSum.setTfSum(TFSUM);
	}

	/**
	 * 得到专业下 各种支付方式 订单信息
	 * 
	 * 优化过
	 */
	public void getContractSum(int subjectId) {
		int GIVE = 0;
		int ZFB = 0;
		int POST = 0;
		int CB = 0;
		int KQ = 0;


		int ContractSum = 0;

		// payType付款方式0 为赠送 1支付宝 2货到付款 3 网银在线 4 块钱
		// stutas 付款状态 //赠送的包括 2赠送 4修复 0未付 1已付 3退费; 货到付款的包括 0未激活 1已激活 3已取消 4退费
		int a = 0;
		for (int i = 0; i < cashList.size(); i++) {
			if (cashList.get(i).getPayType() == 0
					&& cashList.get(i).getSubjectId() == subjectId) { // 赠送订单处理
				SUM++;
				GIVE++; // 当前专业赠送总数
				zsSum++; // 所有专业赠送总数
				ContractSum++;
			} else if (cashList.get(i).getPayType() == 1
					&& cashList.get(i).getSubjectId() == subjectId) { // 支付宝订单处理
				SUM++;
				ZFB++;
				zfbSum++;
				ContractSum++;

			} else if (cashList.get(i).getPayType() == 2
					&& cashList.get(i).getSubjectId() == subjectId) { // 货到付款订单处理
				SUM++;
				POST++;
				hdfkSum++;
				ContractSum++;

			} else if (cashList.get(i).getPayType() == 3
					&& cashList.get(i).getSubjectId() == subjectId) { // 网银在线订单处理
				SUM++;
				CB++;
				wyzxSum++;
				ContractSum++;

			} else if (cashList.get(i).getPayType() == 4
					&& cashList.get(i).getSubjectId() == subjectId) { // 快钱订单处理
				SUM++;
				KQ++;
				kqSum++;
				ContractSum++;

			}
		}
		this.customerCountDTO.setZsSum(GIVE); // 当前专业赠送总数
		this.customerCountDTO.setZfbSum(ZFB); // 当前专业支付宝总数
		this.customerCountDTO.setWyzxSum(CB); // 当前专业网银在线总数
		this.customerCountDTO.setKqSum(KQ); // 当前专业会计总数
		this.customerCountDTO.setHdfkSum(POST); // 当前专业货到付款总数
		this.customerCountDTO.setSubjectContractSum(ContractSum); // 当前专业 订单总数
		this.customerCountSum.setZfbSum(zfbSum); // 所有专业支付宝总计
		this.customerCountSum.setContractSum(SUM); // 所有专业 订单总计
		this.customerCountSum.setKqSum(kqSum); // 所有专业快钱总计
		this.customerCountSum.setWyzxSum(wyzxSum); // 所有专业网银在线总计
		this.customerCountSum.setZsSum(zsSum); // 赠送总计
		this.customerCountSum.setZfbSum(zfbSum); // 支付宝总计
		this.customerCountSum.setHdfkSum(hdfkSum); // 货到付款总计
		
	}

	/**
	 * @author wangzheng 学员订单统计主方法，调用各个模块字方法
	 */
	public String customerDisplayInfo() { // 学员统计主方法
		// 统计DTO

		subjectList = this.subjectService
				.getSubjectList(this.querySubjectCondition); // 得到所有专业信息
		selectCondition(); // 添加时间查询条件
		getContractAll(); // 得到所有订单方法
		for (int i = 0; i < subjectList.size(); i++) { // 遍历所有专业

			this.customerCountDTO = new CustomerCountDTO(); // 这个DTO 是处理当前专业信息
			this.customerCountSum = new CustomerCountDTO(); // 这个DTO
															// 是处理所有专业总计的数据
			this.selRegCusNumbers(subjectList.get(i).getSubjectId()); // 调用学员统计，各个专业学员信息都在此方法下处理

			getContractSum(subjectList.get(i).getSubjectId()); // 掉用订单处理方法，各个专业下订单信息
			getContractYFSum(subjectList.get(i).getSubjectId());
																// 都是在这里做处理

			this.customerCountDTOList.add(this.customerCountDTO); // 经过前两个方法的处理
																	// 在将处理过后的
																	// DTO
																	// 添加到集合中
		}
		this.getCustomerSum(); // 调用学员总计处理方法，也就是处理customerCountDTOList里所有的专业数据相加

		this.getQueryCustomerCondition().setEndNumber(999999);
		// 分析数据，按月份显示
		int monthContract = 0;
		int monthPayContract = 0;
		float percent;
		String monthS;
		monthList = this.customerService.getMonthList();
		for (int i = 0; monthList != null && i < monthList.size(); i++) {
			Date date = monthList.get(i).getRegTime();
			SimpleDateFormat dateFmm = new SimpleDateFormat("yyyy-MM");
			monthS = dateFmm.format(date);
			monthContract = this.contractService.getMonthContractNumber(monthS);
			monthPayContract = this.contractService
					.getMonthPayContractNumber(monthS);
			monthList.get(i).setMonthRigist(
					this.customerService.getMonthRegistNumber(monthS));
			monthList.get(i).setMonthContract(monthContract);
			monthList.get(i).setMonthPayContract(monthPayContract);
			percent = Math
					.round(((float) monthPayContract / monthContract) * 1000) / 10;
			monthList.get(i).setPercent(percent);
		}

		return "customerCountDTOList";
	}

	/**
	 * 统计 学员所有专业下 总计数量 即 所有专业人数相加
	 */
	public void getCustomerSum() {
		int wbSum = 0; // 声明外部 内部 临时总和变量
		int nbSum = 0;
		int allSum = 0;
		int lsSum = 0;
		int lsSum1 = 0;
		for (int i = 0; i < customerCountDTOList.size(); i++) { // 遍历所有专业
			allSum += customerCountDTOList.get(i).getAllRegCustomer(); // 经过处理
																		// 所有专业DTO里边的人数已经添加完毕
			wbSum += customerCountDTOList.get(i).getWbRegCustomer(); // 现在将所有专业人数相加
																		// 得到总计
			nbSum += customerCountDTOList.get(i).getNbRegCustomer();
			lsSum += customerCountDTOList.get(i).getLsRegCustomer();
			lsSum1 += customerCountDTOList.get(i).getLsCustomer();
		}
		customerCountSum.setAllSum(allSum);
		customerCountSum.setNbSum(nbSum);
		customerCountSum.setWbSum(wbSum);
		customerCountSum.setLsSum((lsSum + lsSum1));
		customerCountSum.setAllSum(cusAll);
		customerCountSum.setWbSum(cusWbAll);

	}

	/**
	 * 学员统计后台处理
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String openCusCountList() {

		try {
			SubjectCountsDTO subjectCountsDTOTemp = null;
			Subject subjectTemp = null;
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			for (int i = 0; i < subjectList.size(); i++) {
				subjectCountsDTOTemp = new SubjectCountsDTO();
				subjectTemp = subjectList.get(i);
				subjectCountsDTOTemp.setSubjectId(subjectTemp.getSubjectId());
				subjectCountsDTOTemp.setSubjectName(subjectTemp
						.getSubjectName());
				subjectCountsDTOTemp.setCreateTime(subjectTemp.getCreateTime());
				subjectCountsDTOTemp.setStatus(subjectTemp.getStatus());
				subjectCountsDTOTemp.setUpdateTime(subjectTemp.getUpdateTime());
				subjectCountsDTOTemp.setTestTime(subjectTemp.getTestTime());
				subjectCountsDTOList.add(subjectCountsDTOTemp);
			}
			if (startTime != null && !"".equals(startTime)) {
				location = 5;
			}
			this.getQueryContractCondition().setPayType(-1);
			// int subject =getQueryCustomerCondition().getSubjectId(); //获取专业id
			String cType = getQueryCustomerCondition().getCusType(); // 获取注册类型

			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
			Calendar todayDate = Calendar.getInstance();
			Calendar weekDate = Calendar.getInstance();
			Calendar monthDate = Calendar.getInstance();
			Calendar threeMonthDate = Calendar.getInstance();

			Date today = todayDate.getTime();
			String todayStrartTime = dateFm.format(today);
			String todayEndTime = dateFm.format(today);
			todayStrartTime = todayStrartTime + startHH;
			todayEndTime = todayEndTime + endHH;

			SimpleDateFormat dateFmt = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			weekDate.add(Calendar.DAY_OF_MONTH, -7);
			Date week = weekDate.getTime();
			String weekStartTime = dateFmt.format(week);

			monthDate.add(Calendar.MONTH, -1);
			Date month = monthDate.getTime();
			String monthStartTime = dateFmt.format(month);

			threeMonthDate.add(Calendar.MONTH, -3);
			Date threeMonth = threeMonthDate.getTime();
			String threeMonthStartTime = dateFmt.format(threeMonth);

			if (location == 5) {
				String timeBeginPart = null;
				timeBeginPart = this.startTime + this.startHH;
				String timeEndPart = null;
				if (!"".equals(endTime) && endTime != null) {
					timeEndPart = this.endTime + this.endHH;
				}
				// 按时间段显示
				// 注册时间参数
				this.getQueryCustomerCondition().setStartCountTime(
						timeBeginPart);
				this.getQueryCustomerCondition().setEndCountTime(timeEndPart);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						timeBeginPart);
				this.getQueryContractCondition().setEndCountTime(timeEndPart);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						timeBeginPart);
				this.getHdfkQueryContractCondition().setStartCountTime(
						timeBeginPart);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						timeBeginPart);
				this.getZfbQueryContractCondition().setStartCountTime(
						timeBeginPart);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(
						timeEndPart);
				this.getHdfkQueryContractCondition().setEndCountTime(
						timeEndPart);
				this.getZfbPayedQueryContractCondition().setEndCountTime(
						timeEndPart);
				this.getZfbQueryContractCondition()
						.setEndCountTime(timeEndPart);

			} else if (location == 0) {
				// 全部统计

			} else if (location == 1) {
				// 今日

				// 注册时间参数
				this.getQueryCustomerCondition().setStartCountTime(
						todayStrartTime);
				this.getQueryCustomerCondition().setEndCountTime(todayEndTime);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						todayStrartTime);
				this.getQueryContractCondition().setEndCountTime(todayEndTime);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						todayStrartTime);
				this.getHdfkQueryContractCondition().setStartCountTime(
						todayStrartTime);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						todayStrartTime);
				this.getZfbQueryContractCondition().setStartCountTime(
						todayStrartTime);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(
						todayEndTime);
				this.getHdfkQueryContractCondition().setEndCountTime(
						todayEndTime);
				this.getZfbPayedQueryContractCondition().setEndCountTime(
						todayEndTime);
				this.getZfbQueryContractCondition().setEndCountTime(
						todayEndTime);

			} else if (location == 2) {
				// 一周内

				// 注册时间参数
				this.getQueryCustomerCondition().setCusType(null);
				this.getQueryCustomerCondition().setStartCountTime(
						weekStartTime);
				this.getQueryCustomerCondition().setEndCountTime(null);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						weekStartTime);
				this.getQueryContractCondition().setEndCountTime(null);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						weekStartTime);
				this.getHdfkQueryContractCondition().setStartCountTime(
						weekStartTime);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						weekStartTime);
				this.getZfbQueryContractCondition().setStartCountTime(
						weekStartTime);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(null);
				this.getHdfkQueryContractCondition().setEndCountTime(null);
				this.getZfbPayedQueryContractCondition().setEndCountTime(null);
				this.getZfbQueryContractCondition().setEndCountTime(null);

			}

			else if (location == 3) {
				// 一个月内

				// 注册时间参数
				this.getQueryCustomerCondition().setCusType(null);
				this.getQueryCustomerCondition().setStartCountTime(
						monthStartTime);
				this.getQueryCustomerCondition().setEndCountTime(null);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						monthStartTime);
				this.getQueryContractCondition().setEndCountTime(null);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						monthStartTime);
				this.getHdfkQueryContractCondition().setStartCountTime(
						monthStartTime);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						monthStartTime);
				this.getZfbQueryContractCondition().setStartCountTime(
						monthStartTime);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(null);
				this.getHdfkQueryContractCondition().setEndCountTime(null);
				this.getZfbPayedQueryContractCondition().setEndCountTime(null);
				this.getZfbQueryContractCondition().setEndCountTime(null);

			} else if (location == 4) {

				// 三个月内

				// 注册时间参数
				this.getQueryCustomerCondition().setCusType(null);
				this.getQueryCustomerCondition().setStartCountTime(
						threeMonthStartTime);
				this.getQueryCustomerCondition().setEndCountTime(null);

				// 订单时间参数
				this.getQueryContractCondition().setStartCountTime(
						threeMonthStartTime);
				this.getQueryContractCondition().setEndCountTime(null);

				this.getHdfkPayedQueryContractCondition().setStartCountTime(
						threeMonthStartTime);
				this.getHdfkQueryContractCondition().setStartCountTime(
						threeMonthStartTime);
				this.getZfbPayedQueryContractCondition().setStartCountTime(
						threeMonthStartTime);
				this.getZfbQueryContractCondition().setStartCountTime(
						threeMonthStartTime);

				this.getHdfkPayedQueryContractCondition().setEndCountTime(null);
				this.getHdfkQueryContractCondition().setEndCountTime(null);
				this.getZfbPayedQueryContractCondition().setEndCountTime(null);
				this.getZfbQueryContractCondition().setEndCountTime(null);
			}
			// 调用公共的方法
			this.getCountRegistAndContract();

			this.getQueryCustomerCondition().setCusType("");

			// 调用查询月份和注册数据列表
			this.getMonthAndRegistAndContract(cType); // 调用查询方法 因为参数传过去
			// 已经被上边的公共方法改了，所以先把参数保存下来，在将参数传进方法
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "openCusCountList";
	}

	public void getCountRegistAndContract() {
		this.getQueryCustomerCondition().setCusType("0");
		// 会计证 ID 3
		this.getQueryCustomerCondition().setSubjectId(3);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 3);
		// 人力 ID 1
		this.getQueryCustomerCondition().setSubjectId(1);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 1);
		// 司法 ID 5
		this.getQueryCustomerCondition().setSubjectId(5);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 5);
		// 证券 ID 8
		this.getQueryCustomerCondition().setSubjectId(8);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 8);
		// 注会 ID 7
		this.getQueryCustomerCondition().setSubjectId(7);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 7);
		// 一级建造师
		this.getQueryCustomerCondition().setSubjectId(9);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 9);
		// 高级会计师
		this.getQueryCustomerCondition().setSubjectId(10);
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 10);
		// 国家公务员
		this.getQueryContractCondition().setSubjectId(11);
		this.getQuerySubjectContract(getQueryContractCondition(), 11);
		// 全部 0
		this.getQueryCustomerCondition().setSubjectId(0);
		this.getQueryCustomerCondition().setCusType("3");
		this.getQuerySubjectRegist(getQueryCustomerCondition(), 0);
		// １为查看订单数 ２为已付订单数
		this.getQueryContractCondition().setConStr(1);

		// 会计证
		this.getQueryContractCondition().setSubjectId(3);
		this.getQuerySubjectContract(getQueryContractCondition(), 3);
		// 人力
		this.getQueryContractCondition().setSubjectId(1);
		this.getQuerySubjectContract(getQueryContractCondition(), 1);
		// 司法
		this.getQueryContractCondition().setSubjectId(5);
		this.getQuerySubjectContract(getQueryContractCondition(), 5);
		// 证券
		this.getQueryContractCondition().setSubjectId(8);
		this.getQuerySubjectContract(getQueryContractCondition(), 8);
		// 注会
		this.getQueryContractCondition().setSubjectId(7);
		this.getQuerySubjectContract(getQueryContractCondition(), 7);
		// 一级建造师
		this.getQueryContractCondition().setSubjectId(9);
		this.getQuerySubjectContract(getQueryContractCondition(), 9);
		// 高级会计师
		this.getQueryContractCondition().setSubjectId(10);
		this.getQuerySubjectContract(getQueryContractCondition(), 100);
		// 国家公务员
		this.getQueryContractCondition().setSubjectId(11);
		this.getQuerySubjectContract(getQueryContractCondition(), 110);
		// 全部
		this.getQueryContractCondition().setSubjectId(0);
		this.getQuerySubjectContract(getQueryContractCondition(), 0);

		// １为查看订单数 ２为已付订单数

		this.getQueryContractCondition().setConStr(2);
		// 会计证
		this.getQueryContractCondition().setSubjectId(3);
		this.getQuerySubjectContract(getQueryContractCondition(), 13);
		// 人力
		this.getQueryContractCondition().setSubjectId(1);
		this.getQuerySubjectContract(getQueryContractCondition(), 11);
		// 司法
		this.getQueryContractCondition().setSubjectId(5);
		this.getQuerySubjectContract(getQueryContractCondition(), 15);
		// 证券
		this.getQueryContractCondition().setSubjectId(8);
		this.getQuerySubjectContract(getQueryContractCondition(), 18);
		// 注会
		this.getQueryContractCondition().setSubjectId(7);
		this.getQuerySubjectContract(getQueryContractCondition(), 17);
		// 一级建造师
		this.getQueryContractCondition().setSubjectId(9);
		this.getQuerySubjectContract(getQueryContractCondition(), 19);
		// 高级会计师
		this.getQueryContractCondition().setSubjectId(10);
		this.getQuerySubjectContract(getQueryContractCondition(), 200);
		// 国家公务员
		this.getQueryContractCondition().setSubjectId(11);
		this.getQuerySubjectContract(getQueryContractCondition(), 210);
		// 全部
		this.getQueryContractCondition().setSubjectId(0);
		this.getQuerySubjectContract(getQueryContractCondition(), 10);

	}

	public void getQuerySubjectRegist(
			QueryCustomerCondition queryCustomerCondition, int subjectId) {

		if (subjectId == 3) {
			customerDTO.setKjRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 1) {
			customerDTO.setRlRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 5) {
			customerDTO.setSfRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 8) {
			customerDTO.setZqRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 7) {
			customerDTO.setZkRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 9) {
			customerDTO.setJzRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 10) {
			customerDTO.setGkRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 11) {
			customerDTO.setGwyRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
		} else if (subjectId == 0) {
			customerDTO.setQbRegist(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
			// ////////1为内部学员 0为注册学员 区分内部学员和外部学员
			this.getQueryCustomerCondition().setCusType("1");
			customerDTO.setQbRegistNb(this.customerService
					.getRegistNumber(getQueryCustomerCondition()));
			customerDTO.setQbRegistWb(customerDTO.getQbRegist()
					- customerDTO.getQbRegistNb());
		}

	}

	public void getQuerySubjectContract(
			QueryContractCondition queryContractCondition, int subjectId) {
		// 支付宝支付的已付订单
		this.getZfbPayedQueryContractCondition().setConStr(2);
		this.getZfbPayedQueryContractCondition().setPayType(1);
		// 支付宝所有的订单
		this.getZfbQueryContractCondition().setConStr(1);
		this.getZfbQueryContractCondition().setPayType(1);
		// 货到付款的已激活的订单
		this.getHdfkPayedQueryContractCondition().setConStr(2);
		this.getHdfkPayedQueryContractCondition().setPayType(2);
		// 货到付款的所有的订单
		this.getHdfkQueryContractCondition().setConStr(1);
		this.getHdfkQueryContractCondition().setPayType(2);
		// 网银支付订单

		// 网银支付已付订单

		// 订单数
		if (subjectId == 3) {
			customerDTO.setKjContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));

			this.getZfbQueryContractCondition().setSubjectId(3);
			this.getHdfkQueryContractCondition().setSubjectId(3);
			// 支付宝订单
			zfbCustomerDTO.setKjContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付活订单
			hdfkCustomerDTO.setKjContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 1) {
			customerDTO.setRlContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(1);
			this.getHdfkQueryContractCondition().setSubjectId(1);
			// 支付宝订单
			zfbCustomerDTO.setRlContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付活订单
			hdfkCustomerDTO.setRlContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 5) {
			customerDTO.setSfContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(5);
			this.getHdfkQueryContractCondition().setSubjectId(5);
			// 支付宝订单
			zfbCustomerDTO.setSfContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setSfContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 8) {
			customerDTO.setZqContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(8);
			this.getHdfkQueryContractCondition().setSubjectId(8);
			// 支付宝订单
			zfbCustomerDTO.setZqContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setZqContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 7) {
			customerDTO.setZkContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(7);
			this.getHdfkQueryContractCondition().setSubjectId(7);
			// 支付宝订单
			zfbCustomerDTO.setZkContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setZkContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));

		} else if (subjectId == 9) {
			customerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(9);
			this.getHdfkQueryContractCondition().setSubjectId(9);
			// 支付宝订单
			zfbCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 100) {
			customerDTO.setGkContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(10);
			this.getHdfkQueryContractCondition().setSubjectId(10);
			// 支付宝订单
			zfbCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 110) {
			customerDTO.setGwyContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(11);
			this.getHdfkQueryContractCondition().setSubjectId(11);
			// 支付宝订单
			zfbCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setJzContract(this.contractService
					.getContractNumberBySubject(hdfkQueryContractCondition));
		} else if (subjectId == 0) {
			customerDTO.setQbContract(this.contractService
					.getContractNumber(getQueryContractCondition()));
			this.getZfbQueryContractCondition().setSubjectId(0);
			this.getHdfkQueryContractCondition().setSubjectId(0);
			// 支付宝订单
			zfbCustomerDTO.setQbContract(this.contractService
					.getContractNumber(zfbQueryContractCondition));
			// 货到付款订单
			hdfkCustomerDTO.setQbContract(this.contractService
					.getContractNumber(hdfkQueryContractCondition));
			customerDTO.setWyzxContract(customerDTO.getQbContract()
					- zfbCustomerDTO.getQbContract()
					- hdfkCustomerDTO.getQbContract());
		}

		// 已付订单数
		else if (subjectId == 13) {
			customerDTO.setKjPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(3);
			this.getHdfkPayedQueryContractCondition().setSubjectId(3);
			// 支付宝已付
			zfbCustomerDTO
					.setKjPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setKjPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 11) {
			customerDTO.setRlPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(1);
			this.getHdfkPayedQueryContractCondition().setSubjectId(1);
			// 支付宝已付
			zfbCustomerDTO
					.setRlPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setRlPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 15) {
			customerDTO.setSfPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(5);
			this.getHdfkPayedQueryContractCondition().setSubjectId(5);
			// 支付宝已付
			zfbCustomerDTO
					.setSfPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setSfPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 18) {
			customerDTO.setZqPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(8);
			this.getHdfkPayedQueryContractCondition().setSubjectId(8);
			// 支付宝已付
			zfbCustomerDTO
					.setZqPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setZqPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 17) {
			customerDTO.setZkPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(7);
			this.getHdfkPayedQueryContractCondition().setSubjectId(7);
			// 支付宝已付
			zfbCustomerDTO
					.setZkPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setZkPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 19) {
			customerDTO.setJzPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(9);
			this.getHdfkPayedQueryContractCondition().setSubjectId(9);
			// 支付宝已付
			zfbCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 200) {
			customerDTO.setJzPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(10);
			this.getHdfkPayedQueryContractCondition().setSubjectId(10);
			// 支付宝已付
			zfbCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 210) {
			customerDTO.setJzPayContract(this.contractService
					.getContractNumberBySubject(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(11);
			this.getHdfkPayedQueryContractCondition().setSubjectId(11);
			// 支付宝已付
			zfbCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO
					.setJzPayContract(this.contractService
							.getContractNumberBySubject(hdfkPayedQueryContractCondition));
		} else if (subjectId == 10) {
			customerDTO.setQbPayContract(this.contractService
					.getContractNumber(getQueryContractCondition()));
			this.getZfbPayedQueryContractCondition().setSubjectId(0);
			this.getHdfkPayedQueryContractCondition().setSubjectId(0);
			// 支付宝已付
			zfbCustomerDTO.setQbPayContract(this.contractService
					.getContractNumber(zfbPayedQueryContractCondition));
			// 货到付款已激活订单
			hdfkCustomerDTO.setQbPayContract(this.contractService
					.getContractNumber(hdfkPayedQueryContractCondition));
			customerDTO.setWyzxPayContract(customerDTO.getQbPayContract()
					- zfbCustomerDTO.getQbPayContract()
					- hdfkCustomerDTO.getQbPayContract());
		}

		setCounts();

	}

	/**
	 * 设置各个学科的注册和订单的数量
	 */
	public void setCounts() {
		for (int i = 0; i < subjectCountsDTOList.size(); i++) {
			if (subjectCountsDTOList.get(i).getSubjectId() == 1) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getRlRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getRlContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getRlPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getRlContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getRlPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getRlContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getRlPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getRlPayContract()
								- zfbCustomerDTO.getRlPayContract()
								- hdfkCustomerDTO.getRlPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getRlContract()
								- zfbCustomerDTO.getRlContract()
								- hdfkCustomerDTO.getRlContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 3) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getKjRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getKjContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getKjPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getKjContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getKjPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getKjContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getKjPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getKjPayContract()
								- zfbCustomerDTO.getKjPayContract()
								- hdfkCustomerDTO.getKjPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getKjContract()
								- zfbCustomerDTO.getKjContract()
								- hdfkCustomerDTO.getKjContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 5) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getSfRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getSfContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getSfPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getSfContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getSfPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getSfContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getSfPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getSfPayContract()
								- zfbCustomerDTO.getSfPayContract()
								- hdfkCustomerDTO.getSfPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getSfContract()
								- zfbCustomerDTO.getSfContract()
								- hdfkCustomerDTO.getSfContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 7) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getZkRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getZkContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getZkPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getZkContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getZkPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getZkContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getZkPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getZkPayContract()
								- zfbCustomerDTO.getZkPayContract()
								- hdfkCustomerDTO.getZkPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getZkContract()
								- zfbCustomerDTO.getZkContract()
								- hdfkCustomerDTO.getZkContract());

			} else if (subjectCountsDTOList.get(i).getSubjectId() == 8) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getZqRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getZqContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getZqPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getZqContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getZqPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getZqContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getZqPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getZqPayContract()
								- zfbCustomerDTO.getZqPayContract()
								- hdfkCustomerDTO.getZqPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getZqContract()
								- zfbCustomerDTO.getZqContract()
								- hdfkCustomerDTO.getZqContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 9) {
				// 一级建造师
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getJzRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getJzContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getJzPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getJzContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getJzPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getJzContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getJzPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getJzPayContract()
								- zfbCustomerDTO.getJzPayContract()
								- hdfkCustomerDTO.getJzPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getJzContract()
								- zfbCustomerDTO.getJzContract()
								- hdfkCustomerDTO.getJzContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 10) {
				// 一级建造师
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getGkRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getGkContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getGkPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getGkContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getGkPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getGkContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getGkPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getGkPayContract()
								- zfbCustomerDTO.getGkPayContract()
								- hdfkCustomerDTO.getGkPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getGkContract()
								- zfbCustomerDTO.getGkContract()
								- hdfkCustomerDTO.getGkContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 11) {
				// 一级建造师
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getGwyRegist());
				subjectCountsDTOList.get(i).setContracts(
						customerDTO.getGwyContract());
				subjectCountsDTOList.get(i).setPayedContracts(
						customerDTO.getGwyPayContract());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getGwyContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getGwyPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getGwyContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getGwyPayContract());
				subjectCountsDTOList.get(i).setWyzcPayedContracts(
						customerDTO.getGwyPayContract()
								- zfbCustomerDTO.getGwyPayContract()
								- hdfkCustomerDTO.getGwyPayContract());
				subjectCountsDTOList.get(i).setWyzxContracts(
						customerDTO.getGwyContract()
								- zfbCustomerDTO.getGwyContract()
								- hdfkCustomerDTO.getGwyContract());
			} else if (subjectCountsDTOList.get(i).getSubjectId() == 0) {
				subjectCountsDTOList.get(i).setQbRegist(
						customerDTO.getQbRegist());
				subjectCountsDTOList.get(i).setQbRegistNb(
						customerDTO.getQbRegistNb());
				subjectCountsDTOList.get(i).setQbRegistWb(
						customerDTO.getQbRegistWb());
				subjectCountsDTOList.get(i).setZfbContracts(
						zfbCustomerDTO.getQbContract());
				subjectCountsDTOList.get(i).setZfbPayedContracts(
						zfbCustomerDTO.getQbPayContract());
				subjectCountsDTOList.get(i).setHdfkContracts(
						hdfkCustomerDTO.getQbContract());
				subjectCountsDTOList.get(i).setHdfkPayedContracts(
						hdfkCustomerDTO.getQbPayContract());
			}

		}

	}

	public void getMonthAndRegistAndContract(String cType) {
		// 根据条件查询
		// getQueryCustomerCondition().setSubjectId(subId);
		getQueryCustomerCondition().setCusType(cType);
		String email = getQueryCustomerCondition().getEmail();
		String mobile = getQueryCustomerCondition().getMobile();
		String cusType = getQueryCustomerCondition().getCusType();
		int subjectId = customer.getSubjectId();
		int startNumber = getQueryCustomerCondition().getStartNumber();
		int endNumber = getQueryCustomerCondition().getEndNumber();

		if (email != null && !"".equals(email) || cusType != null
				&& !"".equals(cusType) || startTime != null
				&& !"".equals(startTime) || endTime != null
				&& !"".equals(endTime) || subjectId != 0 || startNumber != 0
				|| endNumber != 0 || customer.getCusFromUrl() != null
				&& !"".equals(customer.getCusFromUrl().trim())) {
			if (dateLocation == -1 || dateLocation == 0) {
				if (customer.getCusFromUrl() != null
						&& !"".equals(customer.getCusFromUrl().trim())) {
					if (customer.getCusFromUrl().equals("1")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.org.cn");
					} else if (customer.getCusFromUrl().equals("2")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.cn");
					} else if (customer.getCusFromUrl().equals("3")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.org");
					} else if (customer.getCusFromUrl().equals("4")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.net.cn");
					} else if (customer.getCusFromUrl().equals("5")) {
						this.getQueryCustomerCondition().setCusFromUrl(
								"highso.com.cn");
					}
				}
				if (startNumber != 0) {
					this.getQueryCustomerCondition()
							.setStartNumber(startNumber);
				}
				if (endNumber != 0) {
					this.getQueryCustomerCondition().setEndNumber(endNumber);
				}

				if (endNumber == 0) {
					this.getQueryCustomerCondition().setEndNumber(999999);
				}

				if (subjectId == 0) {
					this.getQueryCustomerCondition().setSubjectId(-1);
				} else {
					this.getQueryCustomerCondition().setSubjectId(subjectId);
				}
				if (email != null && !"".equals(email)) {
					getQueryCustomerCondition().setEmail(email.trim());
				}
				if (mobile != null && !"".equals(mobile)) {
					getQueryCustomerCondition().setMobile(mobile.trim());
				}
				if (cusType != null && !"".equals(cusType)) {
					getQueryCustomerCondition().setCusType(cusType.trim());
				}
				if (startTime != null && !"".equals(startTime)) {
					startTime = startTime + startHH;
					this.getQueryCustomerCondition().setStartTime(startTime);
				}
				if (endTime != null && !"".equals(endTime)) {
					endTime = endTime + endHH;
					this.getQueryCustomerCondition().setEndTime(endTime);
				}
				// 查询的用户信息
				this.getQueryCustomerCondition().setPageSize(30);
				setPage(customerService
						.getCustomerListByCondition(getQueryCustomerCondition()));
				setPageUrlParms();
				if (getPage() != null) {
					getPage().setPageSize(30);
				}
				if (startTime != null && !"".equals(startTime)) {
					startTime = startTime.substring(0, startTime
							.indexOf(startHH));
				}
				if (endTime != null && !"".equals(endTime)) {
					endTime = endTime.substring(0, endTime.indexOf(endHH));
				}
			} else {
				if (dateLocation == 1) {
					if (startTime != null && !"".equals(startTime)) {
						startTime = startTime + startHH;
						this.getQueryContractCondition()
								.setStartTime(startTime);
					}
					if (endTime != null && !"".equals(endTime)) {
						endTime = endTime + endHH;
						this.getQueryContractCondition().setEndTime(endTime);
					}
					this.getQueryContractCondition().setConStr(1);
				}
				if (dateLocation == 2) {
					if (startTime != null && !"".equals(startTime)) {
						startTime = startTime + startHH;
						this.getQueryContractCondition().setPayStartTime(
								startTime);
					}
					if (endTime != null && !"".equals(endTime)) {
						endTime = endTime + endHH;
						this.getQueryContractCondition().setPayEndTime(endTime);
					}
					this.getQueryContractCondition().setConStr(2);
					// this.getQueryContractCondition().setNewStatus("1");

				}
				// customerList=this.customerService.getCustomerList(getQueryCustomerCondition());
				// 查询订单的信息
				if (subjectId == 0) {
					this.getQueryContractCondition().setSubjectId(-1);
				} else {
					this.getQueryContractCondition().setSubjectId(subjectId);
				}
				if (customer.getCusFromUrl() != null
						&& !"".equals(customer.getCusFromUrl().trim())) {
					if (customer.getCusFromUrl().equals("1")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.org.cn");
					} else if (customer.getCusFromUrl().equals("2")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.cn");
					} else if (customer.getCusFromUrl().equals("3")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.org");
					} else if (customer.getCusFromUrl().equals("4")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.net.cn");
					} else if (customer.getCusFromUrl().equals("5")) {
						this.getQueryContractCondition().setContractFromUrl(
								"highso.com.cn");
					}
				}

				this.getQueryContractCondition().setPageSize(30);
				setPage(this.contractService
						.getContractNumberBySubjectList(getQueryContractCondition()));
				setPageUrlParms();
				if (getPage() != null) {
					getPage().setPageSize(30);
				}
				if (startTime != null && !"".equals(startTime)) {
					startTime = startTime.substring(0, startTime
							.indexOf(startHH));
				}
				if (endTime != null && !"".equals(endTime)) {
					endTime = endTime.substring(0, endTime.indexOf(endHH));
				}
			}

		} else {
			this.getQueryCustomerCondition().setEndNumber(999999);
			// 分析数据，按月份显示
			int monthContract = 0;
			int monthPayContract = 0;
			float percent;
			String monthS;
			monthList = this.customerService.getMonthList();
			for (int i = 0; monthList != null && i < monthList.size(); i++) {
				Date date = monthList.get(i).getRegTime();
				SimpleDateFormat dateFmm = new SimpleDateFormat("yyyy-MM");
				monthS = dateFmm.format(date);
				monthContract = this.contractService
						.getMonthContractNumber(monthS);
				monthPayContract = this.contractService
						.getMonthPayContractNumber(monthS);
				monthList.get(i).setMonthRigist(
						this.customerService.getMonthRegistNumber(monthS));
				monthList.get(i).setMonthContract(monthContract);
				monthList.get(i).setMonthPayContract(monthPayContract);
				percent = Math
						.round(((float) monthPayContract / monthContract) * 1000) / 10;
				monthList.get(i).setPercent(percent);
			}

		}

	}

	public String getMonthDay() {

		try {
			int dayContract = 0;
			int dayPayContract = 0;
			float percent;
			String monthS;
			if (monthDay != null && !"".equals(monthDay)) {
				monthDayList = this.customerService.getMonthDay(monthDay);
				for (int i = 0; monthDayList != null && i < monthDayList.size(); i++) {
					Date date = monthDayList.get(i).getRegTime();
					SimpleDateFormat dateFmm = new SimpleDateFormat(
							"yyyy-MM-dd");
					monthS = dateFmm.format(date);
					dayContract = this.contractService
							.getMonthContractNumber(monthS);
					dayPayContract = this.contractService
							.getMonthPayContractNumber(monthS);
					monthDayList.get(i).setMonthRigist(
							this.customerService.getMonthRegistNumber(monthS));
					monthDayList.get(i).setMonthContract(dayContract);
					monthDayList.get(i).setMonthPayContract(dayPayContract);
					percent = Math
							.round(((float) dayPayContract / dayContract) * 1000) / 10;
					monthDayList.get(i).setPercent(percent);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "getMonthDay";
	}

	public String getDay() {

		try {
			if (dateDay != null && !"".equals(dateDay)) {
				DayList = this.customerService.getDay(dateDay);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "getDay";
	}

	/**
	 * 检查用户名是否可用
	 * 
	 * @return
	 */
	public String checkCusName() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}

	/**
	 * 初始化添加页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String initAddCustomer() {
		return "addPage";
	}

	/**
	 * 初始化修改页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String initUpdateCustomer() {
		try {
			if (customer != null) {
				customer = customerService.getCustomerById(customer.getCusId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "updatePage";
	}

	/**
	 * open内部学员批量导入页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String importCustomer() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "importCustomer";
	}

	/**
	 * 内部学员批量导入处理
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String importProcess() {
		try {
			// 用时间来生成订单号，加上用户Id
			// Long cId=date.getTime();
			// String contractId=cId.toString();

			String customerContent = "";
			String customerEmail = "";
			// raBatch，默认的是批量添加，0，批量禁止，1，批量删除，2
			if (raBatch == 2) {
				customerEmail = this.servletRequest
						.getParameter("customerContent");
				this.delBatchCustomer(customerEmail);
				this.delSucess = "删除成功!";
			} else {
				customerContent = this.servletRequest
						.getParameter("customerContent");
			}
			String[] sellId = null;
			if (customerContent.trim() != null
					&& !"".equals(customerContent.trim())) {

				// 处理字符串判断是已存在用户还是新用户，存到相应的list中
				this.getCustomerContent(customerContent.trim());

				// 对这个list进行整体添加,返回整型的list 存放的cusId
				if (customerList.size() != 0) {
					cusList = this.customerService
							.addBathCustomer(customerList);
					for (int b = 0; cusList != null && b < cusList.size(); b++) {
						// 返回前台成功的提示list
						Customer newCus = this.customerService
								.getCustomerById(cusList.get(b));
						newCusList.add(newCus);
					}
				}
				// 把已存在的customer，取出id，添加到cusList中。
				if (existCusList.size() != 0) {
					for (int a = 0; a < existCusList.size(); a++) {
						cusList.add(existCusList.get(a).getCusId());
					}
				}
				for (int m = 0; cusList != null && m < cusList.size(); m++) {
					Date date = new Date();
					cusId = cusList.get(m);
					int ctId = this.getContract(date, customer.getCusType());
					// 课程ID列表
					sellId = cus[3].split(",");
					for (int j = 0; j < sellId.length; j++) {
						this.getCashRecord(new Integer(sellId[j]), date,
								customer.getCusType(), ctId); // 增加一个packID
						// 售卖方式ID
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "importCustomer";
	}

	/**
	 * @param customerContent
	 */
	public void getCustomerContent(String customerContent) {
		String email;
		String cusPwd;
		String realName;
		String[] cusCon = null;
		// 售卖方式ID
		cusCon = customerContent.split("\r\n");
		Customer newCustomer = null;
		for (int i = 0; i < cusCon.length; i++) {
			newCustomer = new Customer();
			cus = cusCon[i].split("\t");
			email = cus[0];
			cusPwd = cus[1];
			realName = cus[2];
			Customer cusCustomer = this.customerService
					.getCustomerByEmail(email);
			if (cusCustomer == null) {
				newCustomer.setEmail(email);
				newCustomer.setCusPwd(MD5.getMD5(cusPwd));
				newCustomer.setRealName(realName);
				newCustomer.setCusName(realName);
				newCustomer.setCusType(customer.getCusType());
				// 设置基础参数，登陆次数，性别，完善状态
				newCustomer.setLoginTimes(1);
				newCustomer.setSex("1");
				newCustomer.setIsComplete(Customer.CUS_COMPLETE_FALSE);
				// 置状态，subjectId
				newCustomer.setSubjectId(3);
				// cusId=customerService.addCustomer(newCustomer);
				// 把对象都添加到list中
				customerList.add(newCustomer);
			} else {
				existCusList.add(cusCustomer);
			}
		}
	}

	/**
	 * 生成流水
	 * 
	 * @param courseId
	 * @param date
	 * @param cusType
	 * @param ctId
	 */
	public void getCashRecord(int sellId, Date date, int cusType, int ctId) {
		// 添加到流水表中
		CashRecord cashRecord = null;
		Gmrecord gmercord = null;

		Long cId = date.getTime();
		this.getQuerySellCouCondition().setSellId(sellId);
		List<SellCou> sellCouList = this.getSellCouService().getSellCouList(
				this.getQuerySellCouCondition());
		//		
		for (int i = 0; i < sellCouList.size(); i++) {
			course = this.courseService.getCourseById(sellCouList.get(i)
					.getCourseId());
			cashRecord = new CashRecord();
			cashRecord.setPackId(sellId);
			cashRecord.setStatus(1);
			cashRecord.setCrInfo("购买《" + course.getTitle() + "》");
			cashRecord.setPrice(course.getPrice());
			cashRecord.setContractId(cusId + cId.toString());
			cashRecord.setCreateTime(date);
			cashRecord.setCusId(cusId);
			cashRecord.setCourseId(course.getCourseId());
			if (cusType == 1 || cusType == 3) {
				cashRecord.setType(2);// 2代表后台赠送
			} else {
				cashRecord.setType(3);// 1前台购买 2 后台赠送 3 后台修复
			}
			cashRecord.setCtId(ctId);// 把订单的id存到流水中
			this.cashRecordService.addCashRecord(cashRecord);

			// 把每一条添加到购买记录表中
			gmercord = new Gmrecord();
			gmercord.setUserId(cusId);
			gmercord.setCourseId(course.getCourseId());
			gmercord.setAddTime(date);
			this.gmrecordService.addGmrecord(gmercord);
		}
	}

	/**
	 * 生成订单
	 * 
	 * @param date
	 * @param cusType
	 * @return
	 */
	public int getContract(Date date, int cusType) {
		// 添加到订单表中
		Long cId = date.getTime();
		Contract contract = new Contract();
		contract.setContractId(cusId + cId.toString());
		contract.setCusId(cusId);
		contract.setCreateTime(date);
		contract.setContractFrom("后台用户");
		contract.setPayType(0);
		if (cusType == 1 || cusType == 3) {
			contract.setStatus(2);// 2代表赠送
		} else {
			contract.setStatus(4);// 0未付 1已付 2内部赠送 3外部退费 4外部修复 5外部过期 6内部过期
		}
		contract.setContractCutSumMoney(0.0);// 减去优惠券应付的价格，折后总金额。
		contract.setContractSumMoney(0.0);// 应付总价，总金额。
		contract.setCouponMoney(0.0);
		contract.setUseCoupon(0);// 0代表没有使用 1代表使用
		int ctId = this.contractService.addContract(contract);
		return ctId;
	}

	public void delBatchCustomer(String email) {
		String[] newEmail = null;
		if (email.trim() != null && !"".equals(email.trim())) {
			newEmail = email.split("\r\n");
			for (int i = 0; i < newEmail.length; i++) {
				Customer customer = this.customerService
						.getCustomerByEmail(newEmail[i]);
				if (customer != null) {
					int cusId = customer.getCusId();
					// 删除笔记
					this.notesService.delNotesByCusId(cusId);
					// 删除流水
					this.cashRecordService.delCashRecordByCusId(cusId);
					// 删除订单
					this.contractService.delContractByCusId(cusId);
					// 删除优惠券
					this.cpCusService.delCpCusByCusId(cusId);
					// 删除学习计划
					this.studyPlanService.delStudyPlanByCusId(cusId);
					// 删除考试
					this.exampaperRecordService.delERecordByCusId(cusId);
					// 删除考试关联
					this.optRecordService.delOptRecordByCusId(cusId);
					// 删除知识树的表
					// this.cusCouKpointService.delCusCouKpointByCusId(cusId);
					// 删除积分记录
					this.tsRecordService.delTsRecordByCusId(cusId);
					// 删除积分
					this.totolsScoreService.delTotolsScoreByCusId(cusId);
					// 删除学员登录信息
					this.loginRecordService.delLoginRecordByCusId(cusId);
					// 删除任务
					this.taskCusService.deleteTaskCusByCusId(cusId);
					// 删除问题及问题的回复
					QueryProblemCondition queryProblemCondition = new QueryProblemCondition();
					queryProblemCondition.setCusId(cusId);
					List<Problem> problemList = this.problemService
							.getProblemList(queryProblemCondition);
					for (int j = 0; problemList != null
							&& j < problemList.size(); j++) {
						for (int n = 0; problemList.get(j).getReProblemList() != null
								&& n < problemList.get(j).getReProblemList()
										.size(); n++) {
							// 删除回复
							this.reProblemService
									.delReProblemById(problemList.get(j)
											.getReProblemList().get(n)
											.getReId());
						}
						// 删除问题
						this.problemService.delProblemById(problemList.get(j)
								.getPblId());
					}

					// 删除回复
					QueryReProblemCondition queryReProblemCondition = new QueryReProblemCondition();
					queryReProblemCondition.setReManId(cusId);
					List<ReProblem> reProblemList = this.reProblemService
							.getReProblemList(queryReProblemCondition);
					for (int k = 0; reProblemList != null
							&& k < reProblemList.size(); k++) {
						this.reProblemService.delReProblemById(reProblemList
								.get(k).getReId());
					}

					// 删除学员
					this.customerService.delCustomerById(cusId);
				}
			}
		}
	}

	/**
	 * 查看用户信息
	 * 
	 * @return
	 */
	public String viewCus() {
		customer = customerService.getCustomerByView(customer.getCusId());
	    addressList=customerService.GetAddrByCusId(customer.getCusId());
		List<LoginRecord> loR = customer.getLoginRecordList();
//		for (LoginRecord l : loR) {
//			if (l.getAddress() == null || "".equals(l.getAddress())) {
//				IPUtil ip = new IPUtil();
//				String newAddress = ip.getAddressByIP(l.getLoginIp());//有问题(longjl)
//				if (newAddress == null || "".equals(newAddress)) {
//					l.setAddress("未知地区");
//				} else {
//					l.setAddress(newAddress);
//				}
//				loginRecordService.updateLoginRecord(l);
//			}
//		}
		for(LoginRecord ld : loR){
			if(ld.getAddress() != null && !"".equals(ld.getAddress())){
				ld.setAddress(ld.getAddress());
			}else{
				ld.setAddress("未知地区");
			}
			loginRecordService.updateLoginRecord(ld);
		}
		return "viewCus";
	}

	/**
	 * 初始化修改密码页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String initUpdatePwd() {
		customer = customerService.getCustomerById(customer.getCusId());
		return "updatePwd";
	}

	public void sendMessage(String message) throws IOException {
		this.getServletResponse().setCharacterEncoding("utf-8");
		this.getServletResponse().getWriter().write(message);
	}

	/**
	 * 给制定用户通过账户手机号 发送指定短信内容sendinfo
	 * 
	 * @return
	 */
	public String sendSMSForCus() {
		try {
			String aa = sendLinks.replaceAll("\r\n", "");
			String[] str = aa.split(",");// 定义一个数组
			List list = new ArrayList();// new一个arralist
			Set set = new HashSet();// new 一个hashset
			set.addAll(java.util.Arrays.asList(str));// 将数组转为list并存入set中，就可以去掉重复项了
			for (java.util.Iterator it = set.iterator(); it.hasNext();) {
				list.add(it.next());// 遍历set 将所有元素键入list中
			}
			java.util.Collections.sort(list); // 对list进行快速排序

			String wuchongfu = list.toString();
			wuchongfu = wuchongfu.replace("[", "");
			wuchongfu = wuchongfu.replace("]", "");
			wuchongfu = wuchongfu.trim();
			String sl[] = wuchongfu.split(",");

			StringBuffer rltMsg = new StringBuffer();
			for (int i = 0; i < sl.length; i++) {
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNum = pattern.matcher(sl[i].toString().trim());
				if (!isNum.matches()) {
					rltMsg
							.append("<font color='red'>"
									+ sl[i].replaceAll("\r\n", "")
									+ "发送失败!</font><br>");
				} else {

					if (sl[i].trim().length() == 11) {
						Thread.sleep(100);
						SendExResp sp = smsService.sendEx(sendInfo.trim(),
								sl[i].toString().trim(), "", "", "");
						rltMsg.append("<font color='green'>" + sl[i]
								+ "发送成功!</font><br>");
					} else {
						rltMsg.append("<font color='red'>"
								+ sl[i].replaceAll("\r\n", "")
								+ "发送失败!</font><br>");
					}

				}

			}
			subjectList = subjectService
					.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			this.addActionMessage(rltMsg.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toSendSMSForCus";
	}

	public String toSendSMSForCus() {

		subjectList = subjectService
				.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
		return "toSendSMSForCus";
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public QueryCustomerCondition getQueryCustomerCondition() {
		if (queryCustomerCondition == null) {
			queryCustomerCondition = new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}

	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public void setMailService(IMail mailService) {
		this.mailService = mailService;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	public QueryContractCondition getQueryContractCondition() {
		if (queryContractCondition == null) {
			queryContractCondition = new QueryContractCondition();
		}
		return queryContractCondition;
	}

	public void setQueryContractCondition(
			QueryContractCondition queryContractCondition) {
		this.queryContractCondition = queryContractCondition;
	}

	public void setMonthDay(String monthDay) {
		this.monthDay = monthDay;
	}

	public List<Customer> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Customer> monthList) {
		this.monthList = monthList;
	}

	public List<Customer> getMonthDayList() {
		return monthDayList;
	}

	public void setMonthDayList(List<Customer> monthDayList) {
		this.monthDayList = monthDayList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IUser getUserService() {
		return userService;
	}

	public void setUserService(IUser userService) {
		this.userService = userService;
	}

	public String getGiveAway() {
		return giveAway;
	}

	public void setGiveAway(String giveAway) {
		this.giveAway = giveAway;
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

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public IGmrecord getGmrecordService() {
		return gmrecordService;
	}

	public void setGmrecordService(IGmrecord gmrecordService) {
		this.gmrecordService = gmrecordService;
	}

	public List<Customer> getNewCusList() {
		return newCusList;
	}

	public void setNewCusList(List<Customer> newCusList) {
		this.newCusList = newCusList;
	}

	public List<Customer> getExistCusList() {
		return existCusList;
	}

	public void setExistCusList(List<Customer> existCusList) {
		this.existCusList = existCusList;
	}

	public List<Integer> getCusList() {
		return cusList;
	}

	public void setCusList(List<Integer> cusList) {
		this.cusList = cusList;
	}

	public int getNewCusId() {
		return newCusId;
	}

	public void setNewCusId(int newCusId) {
		this.newCusId = newCusId;
	}

	public String[] getCus() {
		return cus;
	}

	public void setCus(String[] cus) {
		this.cus = cus;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getRaBatch() {
		return raBatch;
	}

	public void setRaBatch(int raBatch) {
		this.raBatch = raBatch;
	}

	public INotes getNotesService() {
		return notesService;
	}

	public void setNotesService(INotes notesService) {
		this.notesService = notesService;
	}

	public IStudyPlan getStudyPlanService() {
		return studyPlanService;
	}

	public void setStudyPlanService(IStudyPlan studyPlanService) {
		this.studyPlanService = studyPlanService;
	}

	public IOptRecord getOptRecordService() {
		return optRecordService;
	}

	public void setOptRecordService(IOptRecord optRecordService) {
		this.optRecordService = optRecordService;
	}

	public IExampaperRecord getExampaperRecordService() {
		return exampaperRecordService;
	}

	public void setExampaperRecordService(
			IExampaperRecord exampaperRecordService) {
		this.exampaperRecordService = exampaperRecordService;
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

	public IMail getMailService() {
		return mailService;
	}

	public ICpCus getCpCusService() {
		return cpCusService;
	}

	public void setCpCusService(ICpCus cpCusService) {
		this.cpCusService = cpCusService;
	}

	public ILoginRecord getLoginRecordService() {
		return loginRecordService;
	}

	public void setLoginRecordService(ILoginRecord loginRecordService) {
		this.loginRecordService = loginRecordService;
	}

	public String getDelSucess() {
		return delSucess;
	}

	public void setDelSucess(String delSucess) {
		this.delSucess = delSucess;
	}

	public String getDateDay() {
		return dateDay;
	}

	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}

	public List<Customer> getDayList() {
		return DayList;
	}

	public void setDayList(List<Customer> dayList) {
		DayList = dayList;
	}

	public String getSendLinks() {
		return sendLinks;
	}

	public void setSendLinks(String sendLinks) {
		this.sendLinks = sendLinks;
	}

	public String getSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(String sendInfo) {
		this.sendInfo = sendInfo;
	}

	public IsmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getDateLocation() {
		return dateLocation;
	}

	public void setDateLocation(int dateLocation) {
		this.dateLocation = dateLocation;
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndHH() {
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public CustomerDTO getHdfkCustomerDTO() {
		return hdfkCustomerDTO;
	}

	public void setHdfkCustomerDTO(CustomerDTO hdfkCustomerDTO) {
		this.hdfkCustomerDTO = hdfkCustomerDTO;
	}

	public CustomerDTO getZfbCustomerDTO() {
		return zfbCustomerDTO;
	}

	public void setZfbCustomerDTO(CustomerDTO zfbCustomerDTO) {
		this.zfbCustomerDTO = zfbCustomerDTO;
	}

	public QueryContractCondition getZfbPayedQueryContractCondition() {
		if (zfbPayedQueryContractCondition == null) {
			zfbPayedQueryContractCondition = new QueryContractCondition();
		}
		return zfbPayedQueryContractCondition;
	}

	public void setZfbPayedQueryContractCondition(
			QueryContractCondition zfbPayedQueryContractCondition) {
		this.zfbPayedQueryContractCondition = zfbPayedQueryContractCondition;
	}

	public QueryContractCondition getZfbQueryContractCondition() {
		if (zfbQueryContractCondition == null) {
			zfbQueryContractCondition = new QueryContractCondition();
		}
		return zfbQueryContractCondition;
	}

	public void setZfbQueryContractCondition(
			QueryContractCondition zfbQueryContractCondition) {
		this.zfbQueryContractCondition = zfbQueryContractCondition;
	}

	public QueryContractCondition getHdfkPayedQueryContractCondition() {
		if (hdfkPayedQueryContractCondition == null) {
			hdfkPayedQueryContractCondition = new QueryContractCondition();
		}
		return hdfkPayedQueryContractCondition;
	}

	public void setHdfkPayedQueryContractCondition(
			QueryContractCondition hdfkPayedQueryContractCondition) {
		this.hdfkPayedQueryContractCondition = hdfkPayedQueryContractCondition;
	}

	public QueryContractCondition getHdfkQueryContractCondition() {
		if (hdfkQueryContractCondition == null) {
			hdfkQueryContractCondition = new QueryContractCondition();
		}
		return hdfkQueryContractCondition;
	}

	public void setHdfkQueryContractCondition(
			QueryContractCondition hdfkQueryContractCondition) {
		this.hdfkQueryContractCondition = hdfkQueryContractCondition;
	}

	public List<SubjectCountsDTO> getSubjectCountsDTOList() {
		return subjectCountsDTOList;
	}

	public void setSubjectCountsDTOList(
			List<SubjectCountsDTO> subjectCountsDTOList) {
		this.subjectCountsDTOList = subjectCountsDTOList;
	}

	public IProblem getProblemService() {
		return problemService;
	}

	public void setProblemService(IProblem problemService) {
		this.problemService = problemService;
	}

	public IReProblem getReProblemService() {
		return reProblemService;
	}

	public void setReProblemService(IReProblem reProblemService) {
		this.reProblemService = reProblemService;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}

	public QueryContractCondition getWyzxQueryContractCondition() {
		return wyzxQueryContractCondition;
	}

	public void setWyzxQueryContractCondition(
			QueryContractCondition wyzxQueryContractCondition) {
		this.wyzxQueryContractCondition = wyzxQueryContractCondition;
	}

	public QueryContractCondition getWyzxPayedQueryContractCondition() {
		return wyzxPayedQueryContractCondition;
	}

	public void setWyzxPayedQueryContractCondition(
			QueryContractCondition wyzxPayedQueryContractCondition) {
		this.wyzxPayedQueryContractCondition = wyzxPayedQueryContractCondition;
	}

	public int getFromURLType() {
		return fromURLType;
	}

	public void setFromURLType(int fromURLType) {
		this.fromURLType = fromURLType;
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

	public String getNewMD5Pwd() {
		return newMD5Pwd;
	}

	public void setNewMD5Pwd(String newMD5Pwd) {
		this.newMD5Pwd = newMD5Pwd;
	}

	public List<CustomerCountDTO> getCustomerCountDTOList() {
		return customerCountDTOList;
	}

	public void setCustomerCountDTOList(
			List<CustomerCountDTO> customerCountDTOList) {
		this.customerCountDTOList = customerCountDTOList;
	}

	public CustomerCountDTO getCustomerCountDTO() {
		if (this.customerCountDTO == null) {
			customerCountDTO = new CustomerCountDTO();
		}
		return customerCountDTO;
	}

	public void setCustomerCountDTO(CustomerCountDTO customerCountDTO) {
		this.customerCountDTO = customerCountDTO;
	}

	public QuerySubjectCondition getQuerySubjectCondition() {
		return querySubjectCondition;
	}

	public void setQuerySubjectCondition(
			QuerySubjectCondition querySubjectCondition) {
		this.querySubjectCondition = querySubjectCondition;
	}

	public QueryCashRecordCondition getQueryCashRecordCondition() {
		return queryCashRecordCondition;
	}

	public void setQueryCashRecordCondition(
			QueryCashRecordCondition queryCashRecordCondition) {
		this.queryCashRecordCondition = queryCashRecordCondition;
	}

	public CustomerCountDTO getCustomerCountSum() {
		if (this.customerCountDTO == null) {
			customerCountSum = new CustomerCountDTO();
		}
		return customerCountSum;
	}

	public void setCustomerCountSum(CustomerCountDTO customerCountSum) {
		this.customerCountSum = customerCountSum;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public List<CustomerWithConSizeDTO> getCwcsList() {
		return cwcsList;
	}

	public void setCwcsList(List<CustomerWithConSizeDTO> cwcsList) {
		this.cwcsList = cwcsList;
	}

	public List<CashRecordDTO> getYFcashList() {
		return YFcashList;
	}

	public void setYFcashList(List<CashRecordDTO> fcashList) {
		YFcashList = fcashList;
	}

}
