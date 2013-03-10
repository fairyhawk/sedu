package com.shangde.edu.cus.web;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.core.task.TaskExecutor;
import org.xml.sax.InputSource;

import com.danga.MemCached.MemCachedClient;
import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.action.RandomCodeAction;
import com.shangde.common.intercepter.LimitIntercepterForWeb;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.DESCoder;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileUtils;
import com.shangde.common.util.ImageHelper;
import com.shangde.common.util.MD5;
import com.shangde.common.util.PreventInfusion;
import com.shangde.common.util.Result;
import com.shangde.common.util.StringUtil;
import com.shangde.common.util.json.JsonUtil;
import com.shangde.edu.cms.condition.QueryColumnsCondition;
import com.shangde.edu.cms.condition.QueryCommentCondition;
import com.shangde.edu.cms.condition.QueryTemplateCondition;
import com.shangde.edu.cms.domain.Announcement;
import com.shangde.edu.cms.domain.Columns;
import com.shangde.edu.cms.domain.Comment;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.service.IAnnouncement;
import com.shangde.edu.cms.service.IColumns;
import com.shangde.edu.cms.service.IComment;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.domain.Coupon;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.CpCus;
import com.shangde.edu.cou.domain.Gmrecord;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.domain.SellCou;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.domain.Uncoupon;
import com.shangde.edu.cou.dto.UserKpointDTO;
import com.shangde.edu.cou.service.ICoupon;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cou.service.IGmrecord;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellCou;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cou.service.IUncoupon;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;
import com.shangde.edu.cou.webdto.UserCenterSubjectCourseDTO;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryTotolsScoreCondition;
import com.shangde.edu.cus.domain.CusRankInfo;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.LoginRecord;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.domain.PublicResult;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ILoginRecord;
import com.shangde.edu.cus.service.IProblem;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.cus.task.SendRegisterInfo2Crm;
import com.shangde.edu.cus.util.CusUtil;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;
import com.shangde.edu.cusmgr.dto.LearnBuyInfoDTO;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.ExampaperRecord;
import com.shangde.edu.exam.service.IExampaper;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.freshnews.condition.QueryActionRecordCondition;
import com.shangde.edu.freshnews.domain.ActionRecord;
import com.shangde.edu.freshnews.service.IActionRecord;
import com.shangde.edu.mail.service.IMail;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.stu.condition.QueryVideoStatisticsCondition;
import com.shangde.edu.stu.domain.VideoStatistics;
import com.shangde.edu.stu.service.IVideoStatistics;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.tk.condition.QueryTaskCondition;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.service.ITask;
import com.shangde.edu.tk.service.ITaskCus;
import com.shangde.edu.web.domain.CusBuyInfo;
import com.shangde.edu.web.domain.CusNotBuyInfo;

/**
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CustomerWebAction extends CommonAction {

	private static final Logger logger = Logger
			.getLogger(CustomerWebAction.class);

	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;

	/**
	 * 课程公告
	 */
	private IAnnouncement acementService;
	/**
	 * 活动服务对象
	 */
	private ICoupon couponService;

	private ICourse courseService;

	private IKpoint kpointService;

	private String randomCode;

	private List<Problem> plemList = new ArrayList<Problem>();
	private List<Announcement> acmList = new ArrayList<Announcement>();
	private int cId;
	private Log log = LogFactory.getLog(CustomerWebAction.class);

	private ISellCou sellCouService;
	/**
	 * 购买记录服务
	 */
	private IGmrecord gmrecordService;

	/**
	 * 栏目服务对象
	 */
	private IColumns columnsService;

	/**
	 * 登陆信息服务对象
	 */
	private ILoginRecord loginRecordService;

	/**
	 * 优惠券用户关系服务对象
	 */
	private ICpCus cpCusService;

	private ConfigService configurator;
	/**
	 * 
	 *留言管理对象
	 */
	private IComment commentService;

	/**
	 * 专业服务对象
	 */
	private ISubject subjectService;

	/**
	 * 邮件服务对象
	 */
	private IMail mailService;

	/**
	 * 短信服务对象
	 */
	private IsmsService smsService;

	/**
	 * 优惠券服务对象
	 */
	private IUncoupon uncouponService;

	/**
	 * 专业实体 2012年5月29日16:04:14
	 * */

	private Subject subject;

	/**
	 * 用户实体
	 */
	private Customer customer = new Customer();

	/**
	 * 活动
	 */
	private Coupon coupon;
	/**
	 * 评论查询条件
	 */
	private QueryCommentCondition queryCommentCondition;

	/**
	 * 用户查询条件
	 */
	private QueryCustomerCondition queryCustomerCondition;

	/**
	 * 新密码
	 */
	private String newPwd;

	/**
	 * 验证码
	 */
	private String confirmationCode;

	/**
	 * 是否需要返回
	 */
	private String back;

	/**
	 * 返回地址
	 */
	private String backURL;

	/**
	 * 是否是注册
	 */
	private boolean isRegister = false;

	/**
	 * email是否被占用
	 */
	private boolean emailInUse = false;

	/**
	 * 修改是否成功
	 */
	private boolean updateSuccess = false;

	/**
	 * id
	 */
	private int id;

	/**
	 * email
	 */
	private String email;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 初始化个人信息
	 */
	private String initMessage = "cusInfo";
	/**
	 * 积分记录的服务
	 */
	private ITsRecord tsRecordService;

	/**
	 * 积分的服务
	 */
	private ITotolsScore totolsScoreService;

	private int couponCount = 0;

	private TotolsScore totolsScore;

	private IContract contractService;

	private int contractCount;

	private String serialNumber;

	private QueryColumnsCondition queryColumnsCondition;

	private String userInfoState = "";

	private String updateMessage = "";

	private String updateError = "";

	private String subjectIds = "";

	private List<Course> courseList;// 课程分类
	private ICoursesort coursesortService;// 课程分类服务
	private List<UserCenterCourseDTO> userCourseList;// 用户课程集合
	private List<UserCenterSubjectCourseDTO> subjectCourseList;// 专业课程集合
	private List<SellWay> butSellWayList;// 购买的包
	private Course course;// 最后查看课程
	private Kpoint kpoint;// 最后观看知识点
	private TaskCus taskCus;// 学员任务
	private IUserMsg userMsgService;// 用户消息服务
	private IMessage messageService;// 消息服务
	private ITask taskService;// 任务服务
	private ITaskCus taskCusService;// 用户任务服务
	private ICusCouKpoint cusCouKpointService;// 课程知识点记录
	private UserMsg userMsg;
	private IExampaper exampaperService;// 试卷服务

	private int[] answers;
	private int firstAnswer;

	private int courseStatus;// 是否已经读过
	private List<UserCenterSubjectCourseDTO> sellWayCourseList;// 购买的包集合
	private List<UserCenterSubjectCourseDTO> sellWayCourseListMore;// 购买的包集合
	private CusBuyInfo buyInfo;// 用户已购买信息
	private CusNotBuyInfo notBuyInfo;// 用户未购买信息
	private String content;// 签名内容

	private String sellIds;

	/**
	 * add by shixf 用户观看记录service
	 */
	private IVideoStatistics videoStatisticsService;

	/**
	 * 上传照片
	 */
	private List<File> newPhoto = new ArrayList<File>();

	private List<String> newPhotoFileName = new ArrayList<String>();

	private Map<String, String> photoPrams = new HashMap<String, String>();
	// 高分list
	private List<Problem> hPbList = new ArrayList<Problem>();
	// 最新问题list
	private List<Problem> nPbList = new ArrayList<Problem>();
	// 已解决问题list
	private List<Problem> sPbList = new ArrayList<Problem>();
	/**
	 * 评论list
	 */
	private List<Comment> commentList = new ArrayList<Comment>();
	/**
	 * 问题的服务
	 */
	private IProblem problemService;
	/**
	 * 查询条件
	 */
	private QueryProblemCondition queryProblemCondition;

	private Map studyTypePram;

	private int minute = 5;
	
	private int maxnumber = 0;//最大人数

	private IExampaperRecord exampaperRecordService;

	private ExampaperRecord exampaperRecord;

	private HttpSession session;

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
	 * 体验（临时）学员的验证码
	 */
	private String tempCusRandomCode;

	private ICashRecord cashRecordService;

	private boolean ishavebuy = false;

	private ISellWay sellWayService;

	/** 线程池对象,用户线程操作,提高效率,防止任务过多服务器压力过大 **/
	private TaskExecutor taskExecutor;

	/**
	 * memcachedClient服务
	 */
	private MemCachedClient memcachedClient;
	
	private List<UserKpointDTO> kpoints;

	/**
	 * 登录
	 * 
	 * @return
	 */
	public String login() {
		try {
			if (PreventInfusion.sql_inj(customer.getEmail())) {
				return "loginDangerWord";
			}
			// 回去可试听课程列表
			// 验证输入数据
			if (!validateLogin(customer)) {
				return "loginFail";
			}

			customer.setEmail(PreventInfusion.xssEncode(customer.getEmail()));

			// 查询用户
			if (!isRegister) {
				customer.setCusPwd(MD5.getMD5(customer.getCusPwd()));
			}
			Integer userId = customerService.getUIDByLogin(customer);
			if (userId == null) {
				return "loginFail";
			}

			// 将ukey等信息加入cookie
			doLogin(userId);
			if (back != null && back.equals("true")) {
				return "loginBack";
			}

			// 如果是注册，那么转向注册成功页面，否则根据登陆来源进行返回
			if (isRegister) {
				return "registerSuccess";
			} else {
				// 如果back等于true那么说明是需要返回，否则转向用户中心
				return "userCenter";
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.login", e);
			return ERROR;
		}
	}

	/**
	 * 登录 专为注册用
	 * 
	 * @return
	 */
	public String login_reg(int userId) {
		try {
			if (PreventInfusion.sql_inj(customer.getEmail())) {
				return "loginDangerWord";
			}
			// 回去可试听课程列表
			// 验证输入数据
			if (!validateLogin(customer)) {
				return "loginFail";
			}

			if (customer == null) {
				return "loginFail";
			}
			// 将ukey等信息加入cookie **********************

			String curTime = getCurTime();
			String uKey = DESCoder.md5(DESCoder.encrypt(userId + curTime))
					+ DESCoder.md5(DESCoder.encrypt(DESCoder.SECONDKEY));
			String uid = DESCoder.md5(DESCoder.encrypt(userId + curTime));

			// 准备cookie参数
			Hashtable<String, String> hashTable = new Hashtable<String, String>();
			String userName = "";
			// 准备用户名
			if (customer.getCusName() != null
					&& !"".equals(customer.getCusName().trim())) {
				userName = customer.getCusName();
			} else {
				userName = customer.getEmail();
			}
			// 准备ukey
			hashTable.put(LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY, userId
					+ ","
					+ uid
					+ ","
					+ uKey
					+ ","
					+ URLEncoder.encode(userName, "UTF-8")
					+ ","
					+ URLEncoder.encode(customer.getPhoto() == null ? ""
							: customer.getPhoto(), "UTF-8") + ","
					+ customer.getEmail());

			// 登陆将subject写入cookie
			QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
			queryCusCouKpointCondition.setCusId(userId);
			Integer subjectId;
			int subjectIdTemp = customer.getSubjectId();
			hashTable.put("subjectIdTemp", "" + subjectIdTemp);
			if (subjectIdTemp <= 0) {
				subjectId = 3;
			} else {
				subjectId = subjectIdTemp;
			}

			hashTable.put("subjectId", "" + subjectId.intValue());
			hashTable.put("cusType", customer.getCusType() + "");
			if (customer.getPhoto() == null) {
				hashTable.put("myphoto", "");
			} else {
				hashTable.put("myphoto", customer.getPhoto());
			}
			if (configurator.getProjectURL().indexOf("highso.cn") > -1) {
				CookieHandler.createCookieDomain(getServletResponse(),
						hashTable, 1, "highso.cn");
			} else if (configurator.getProjectURL().indexOf("haixue.com") > -1) {
				CookieHandler.createCookieDomain(getServletResponse(),
						hashTable, 1, "haixue.com");
			} else {
				CookieHandler.createCookie(getServletResponse(), hashTable, 1);
			}

			// 将ukey等信息加入cookie **********************

			if (back != null && back.equals("true")) {
				return "loginBack";
			}

			return "registerSuccess";
		} catch (Exception e) {
			logger.error("CustomerWebAction.login_reg", e);
			return ERROR;
		}
	}

	/**
	 * 快速登录，为flex提供
	 * 
	 * @param cus
	 * @return
	 */
	public Map<String, Object> quickLogin(Customer cus) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (!validateLogin(cus)) {
				return result;
			}
			cus.setCusPwd(MD5.getMD5(cus.getCusPwd()));
			Integer userId = customerService.getUIDByLogin(cus);
			if (userId == null) {
				return result;
			}
			cus = doLogin(userId);
			result.put("customer", cus);
		} catch (Exception e) {
			logger.error("CustomerWebAction.quickLogin", e);
		}
		return result;
	}

	/**
	 * 快速登录，为ajax提供
	 * 
	 * @return
	 */
	public String quickLogin() {
		try {
			// 验证输入数据合法性
			if (PreventInfusion.sql_inj(customer.getEmail())
					|| !validateLogin(customer)) {
				setResult(new Result<String>(false, "false", null, null));
				return "json";
			}

			customer.setEmail(PreventInfusion.xssEncode(customer.getEmail()));
			customer.setCusPwd(MD5.getMD5(customer.getCusPwd()));
			/*
			 * //验证码if(randomCode==null||!randomCode.equals(this.getSession(
			 * "back_rand_code"))){ setResult(new Result<String>(false,
			 * "err.randCode", null, null)); return "json"; }
			 */
			// 通过数据库，验证用户名密码
			Integer userId = customerService.getUIDByLogin(customer);
			if (userId == null) {
				setResult(new Result<String>(false, "false", null, null));
				return "json";
			}
			// 通过数据库，验证该用户是否被冻结
			Integer freezeStatus = customerService.checkFreezeStatus(userId);
			if (freezeStatus != null && freezeStatus == 0) {
				setResult(new Result<String>(false, "freezed", null, null));
				return "json";
			}
			// 执行登陆操作
			doLogin(userId);
			// 准备返回的用户名
			String userName = "";
			if (customer.getCusName() != null
					&& !"".equals(customer.getCusName().trim())) {
				userName = customer.getCusName();
			} else {
				userName = customer.getEmail();
			}

			int loginUseId = getLoginUserId();
			List list = cusCouKpointService
					.getCusCouKpointListByCusId(loginUseId);

			// 返回出异常，故用map装载数据，未查出原因
			Map<String, String> map = new HashMap<String, String>();
			map.put("userName", userName);
			map.put("courseCount", (list == null ? 0 : list.size()) + "");

			setResult(new Result<Map<String, String>>(false, "success", null,
					map));

			/**
			 * 向分销商系统发送最后一次登录时间，未启用
			 */
			/**
			 * try{ AgentClient ac = new AgentClient(); Student student = new
			 * Student(); student.setStudentLogin(customer.getEmail());
			 * student.setLastLoginTime(new Date());
			 * ac.updateUserLastLoginTime(student); }catch(Exception e){
			 * log.info("登录时向分销商系统更新最后一次登录时间！"); }
			 **/

			return "json";
		} catch (Exception e) {
			logger.error("CustomerWebAction.quickLogin", e);
			return ERROR;
		}
	}

	public String iPhoneQuickLogin() {
		try {
			// 验证输入数据合法性
			if (PreventInfusion.sql_inj(customer.getEmail())
					|| !validateLogin(customer)) {
				setResult(new Result<String>(false, "validator", null, null));
				return "json";
			}

			customer.setEmail(PreventInfusion.xssEncode(customer.getEmail()));
			customer.setCusPwd(MD5.getMD5(customer.getCusPwd()));
			/*
			 * //验证码if(randomCode==null||!randomCode.equals(this.getSession(
			 * "back_rand_code"))){ setResult(new Result<String>(false,
			 * "err.randCode", null, null)); return "json"; }
			 */
			// 通过数据库，验证用户名密码
			Integer userId = customerService.getUIDByLogin(customer);
			if (userId == null) {
				setResult(new Result<String>(false, "faile", null, null));
				return "json";
			}
			// 执行登陆操作
			doLogin(userId);
			setResult(new Result<String>(false, "success", null, null));
			return "json";
			// return "reList";
		} catch (Exception e) {
			logger.error("CustomerWebAction.iPhoneQuickLogin", e);
			return ERROR;
		}
	}

	/**
	 * 验证是否完善信息
	 * 
	 * @return
	 */
	public String checkComplete() {
		try {
			customer = customerService.getCustomerById(getLoginUserId());
			setResult(new Result<Integer>(false, "success", null, customer
					.getIsComplete()));
			return "json";
		} catch (Exception e) {
			logger.error("CustomerWebAction.checkComplete", e);
			return ERROR;
		}
	}

	/**
	 * 获取栏目id
	 * 
	 * @return
	 */
	public String getColumnId() {
		try {
			List<Columns> list = columnsService
					.getColumnsListForTag(queryColumnsCondition);
			if (list != null && list.size() > 0) {
				setResult(new Result<Integer>(false, "success", null, list.get(
						0).getColumnId()));
			} else {
				setResult(new Result<Integer>(false, "faillure", null, null));
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.getColumnId", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * 更新新手引导变量
	 * 
	 * @return
	 */
	public String updatenewerflag() {
		Customer customerner = new Customer();
		try {
			customerner.setCusId(getLoginUserId());
			customerner.setNewerflag(customer.getNewerflag());
			customerService.updateCustomerNewerflag(customerner);
			setResult(new Result<String>(false, "success", null, customerner
					.getNewerflag()));
		} catch (Exception e) {
			setResult(new Result<String>(false, "error", null, customerner
					.getNewerflag()));
			log.error("更新新手引导flag错误！", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * 创建临时学员
	 * 
	 * @return
	 */
	public String createTempCustomer() {
		try {
			if (tempCusRandomCode == null
					|| getSession(RandomCodeAction.RAND_CODE_NAME_TEMP_CUS) == null
					|| !tempCusRandomCode.equals(getSession(
							RandomCodeAction.RAND_CODE_NAME_TEMP_CUS)
							.toString())) {
				setResult(new Result(false, "randoCodeError", null, null));
				return "json";
			}

			getServletRequest().getSession().removeAttribute(
					RandomCodeAction.RAND_CODE_NAME_TEMP_CUS);
			// 获得域名的后缀
			String projectURL = configurator.getProjectURL();
			String projectURLtmp = "";
			if (null == projectURL || "".endsWith(projectURL)) {
				projectURLtmp = "highso.org.cn";// 默认
				projectURL = "http://highso.org.cn";
			} else {
				projectURLtmp = projectURL.toLowerCase().substring(
						projectURL.indexOf("http://") + 7);
			}
			customer.setEmail(customer.getMobile() + "@" + projectURLtmp);

			if (!customerService.checkEmail(customer.getEmail())) {
				setResult(new Result<String>(false, "emailInUsed", null, null));
				return "json";
			}

			String randomPwd = getRandomNum(6);
			// 设置基础参数，登陆次数，性别，完善状态
			customer.setCusFromUrl(servletRequest.getServerName());
			customer.setLoginTimes(0);
			customer.setSex("1");
			customer.setCusPwd(MD5.getMD5(randomPwd));
			customer.setIsComplete(Customer.CUS_COMPLETE_FALSE);
			customer.setCusType(Customer.CUS_CUS_TYPE_TEMP);

			int cusId = customerService.addCustomer(customer);

			// 注册时赠送100积分
			addTsScore(cusId);

			// 初始化所有任务
			QueryTaskCondition queryTaskCondition = new QueryTaskCondition();
			List<Task> taskList = taskService.getTaskList(queryTaskCondition);

			if (taskList != null) {
				TaskCus taskCus = null;
				for (int i = 0; i < taskList.size(); i++) {
					taskCus = new TaskCus();
					taskCus.setCusId(customer.getCusId());
					taskCus.setTaskId(taskList.get(i).getTaskId());
					taskCusService.addTaskCus(taskCus);
				}
			}
			setResult(new Result(false, "success", null, null));
			sendSms("您好，账号成功开通。账号：" + customer.getEmail() + "，密码：" + randomPwd
					+ ".登陆" + projectURL + ",体验全套课程，5天有效。");
		} catch (Exception e) {
			logger.error("CustomerWebAction.createTempCustomer", e);
			setResult(new Result(false, "fail", null, null));
		}
		return "json";
	}

	/**
	 * 随机生成密码
	 * 
	 * @return String
	 * @throws Exception
	 */
	private String getRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 9;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	/**
	 * 完善个人信息
	 * 
	 * @return
	 */
	public String completeInfo() {
		try {

			// 获取提交数据
			String cusName = customer.getCusName();
			String mobile = customer.getMobile();

			// 从cookie中取得验证码
			String cCode = CookieHandler.getCookieValueByName(
					getServletRequest(), "confirmationCode");

			if (cCode != null && !cCode.trim().equals("")
					&& cCode.equals(confirmationCode.trim())) {
				// 修改用户信息
				customer = customerService.getCustomerById(getLoginUserId());
				customer.setCusName(cusName);
				customer.setMobile(mobile);
				customer.setIsComplete(Customer.CUS_COMPLETE_TRUE);
				customer.setCompleteTime(new Date());
				customerService.updateCustomer(customer);

				// 修改cookie中存放的用户名称
				String ukey = CookieHandler.getCookieValueByName(
						getServletRequest(),
						LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);
				String newKey = ukey.substring(0, ukey.lastIndexOf(",") + 1)
						+ cusName;
				CookieHandler.setCookieValueByName(getServletRequest(),
						LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY, newKey);
				CookieHandler.deleteCookieByName(getServletRequest(), this
						.getServletResponse(), "confirmationCode");
				setResult(new Result<String>(false, "success", null, null));
			} else {
				setResult(new Result<String>(false, "false", null, null));
			}
			return "json";
		} catch (Exception e) {
			logger.error("CustomerWebAction.completeInfo", e);
			return ERROR;
		}
	}

	/**
	 * 转向个人学习计划
	 * 
	 * @return
	 */
	public String initStudyPlan() {
		try {
			customer = customerService.getCustomerById(getLoginUserId());
		} catch (Exception e) {
			logger.error("CustomerWebAction.initStudyPlan", e);
			return ERROR;
		}
		return "initStudyPlan";
	}

	/**
	 * 开启学习计划
	 * 
	 * @return
	 */
	public String startStudyPlan() {
		try {
			customer = customerService.getCustomerById(getLoginUserId());
			customer.setIsComplete(Customer.CUS_COMPLETE_TRUE);
			customerService.updateCustomer(customer);
		} catch (Exception e) {
			logger.error("CustomerWebAction.startStudyPlan", e);
			return ERROR;
		}
		return "initStudyPlan";
	}

	/**
	 * 检查登陆参数是否合法
	 * 
	 * @return
	 */
	private boolean validateLogin(Customer cus) {
		if (cus.getEmail() == null || cus.getEmail().trim().equals("")
				|| cus.getCusPwd() == null || cus.getCusPwd().trim().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 执行登陆操作
	 * 
	 * @param userId
	 * @throws Exception
	 */
	private Customer doLogin(int userId) throws Exception {
		customer = customerService.getCustomerById(userId);

		// 对ukey进行加密
		String curTime = getCurTime();
		String uKey = DESCoder.md5(DESCoder.encrypt(userId + curTime))
				+ DESCoder.md5(DESCoder.encrypt(DESCoder.SECONDKEY));
		String uid = DESCoder.md5(DESCoder.encrypt(userId + curTime));

		// 准备cookie参数
		Hashtable<String, String> hashTable = new Hashtable<String, String>();
		String userName = "";
		// 准备用户名
		if (customer.getCusName() != null
				&& !"".equals(customer.getCusName().trim())) {
			userName = customer.getCusName();
		} else {
			userName = customer.getEmail();
		}
		// 准备ukey
		hashTable.put(LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY, userId
				+ ","
				+ uid
				+ ","
				+ uKey
				+ ","
				+ URLEncoder.encode(userName, "UTF-8")
				+ ","
				+ URLEncoder.encode(customer.getPhoto() == null ? "" : customer
						.getPhoto(), "UTF-8") + "," + customer.getEmail());

		try {
			memcachedClient.set(String.valueOf(userId), uKey, new Date(System
					.currentTimeMillis() + 21600000));
		} catch (Exception e) {
			logger.error("CustomerWebAction.doLogin.setMemcached", e);
		}
		// 登陆将subject写入cookie
		QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
		queryCusCouKpointCondition.setCusId(userId);
		Integer subjectId = cusCouKpointService
				.getEarliestSubjectIdByCusId(queryCusCouKpointCondition);
		hashTable.put("subjectIdTemp", "" + customer.getSubjectId());
		if (subjectId == null) {
			subjectId = customer.getSubjectId();
			if (subjectId <= 0) {
				subjectId = 3;
			}
		}

		hashTable.put("subjectId", "" + subjectId.intValue());
		hashTable.put("cusType", customer.getCusType() + "");
		if (customer.getPhoto() == null) {
			hashTable.put("myphoto", "");
		} else {
			hashTable.put("myphoto", customer.getPhoto());
		}
		if (configurator.getProjectURL().indexOf("highso.cn") > -1) {
			CookieHandler.createCookieDomain(getServletResponse(), hashTable,
					1, "highso.cn");
		} else if (configurator.getProjectURL().indexOf("haixue.com") > -1) {
			CookieHandler.createCookieDomain(getServletResponse(), hashTable,
					1, "haixue.com");
		} else {
			CookieHandler.createCookie(getServletResponse(), hashTable, 1);
		}
		// 登陆次数加1，登陆ip变更
		customer.setLoginTimes(customer.getLoginTimes() + 1);
		customer.setLastloginIP(getIpAddr(getServletRequest()));
		// 记录登陆信息
		LoginRecord loginRecord = new LoginRecord();
		loginRecord.setCusId(customer.getCusId());
		loginRecord.setLoginIp(getIpAddr(getServletRequest()));
		loginRecord.setLoginTime(new Date());
		loginRecordService.addLoginRecord(loginRecord);

		// 上一次登录的日期，和这次登录的日期进行比较看是否是当天的第一次登录

		Calendar beforeDate = Calendar.getInstance();
		Date date = customer.getLastloginTime();
		beforeDate.setTime(date);

		customer.setLastloginTime(new Date());
		customerService.updateCustomer(customer);

		return customer;
	}

	/**
	 * 初始化体验学员的购买的包课程，按专业分
	 * 
	 * @param courseId
	 * @param cusId
	 */
	private void initTempCustmoerSellWay(int sellId, int cusId) {
		try {
			Contract contract = new Contract();

			Date date = new Date();
			Long cId = date.getTime();
			// 添加订单表
			contract.setContractId(cusId + cId.toString());
			contract.setCusId(cusId);
			contract.setCreateTime(date);
			contract.setContractFrom("后台用户");
			contract.setPayType(0);
			contract.setStatus(4);// 4代表修复或赠送

			contract.setContractCutSumMoney(0.0);// 减去优惠券应付的价格，折后总金额。
			contract.setContractSumMoney(0.0);// 应付总价，总金额。
			contract.setCouponMoney(0.0);
			contract.setUseCoupon(0);// 0代表没有使用 1代表使用
			int ctId = this.contractService.addContract(contract);
			// 记录流水
			QuerySellCouCondition querySellCouCondition = new QuerySellCouCondition();
			querySellCouCondition.setSellId(sellId);
			List<SellCou> sellCouList = new ArrayList<SellCou>();
			sellCouList = this.getSellCouService().getSellCouList(
					querySellCouCondition);
			for (int i = 0; sellCouList != null && sellCouList.size() != 0
					&& i < sellCouList.size(); i++) {
				CashRecord cashRecord = new CashRecord();
				Gmrecord gmercord = new Gmrecord();

				course = this.courseService.getCourseById(sellCouList.get(i)
						.getCourseId());
				cashRecord.setCrInfo("购买《" + course.getTitle() + "》");
				cashRecord.setPrice(sellCouList.get(i).getSellCouPrice());
				cashRecord.setContractId(cusId + cId.toString());
				cashRecord.setCreateTime(date);
				cashRecord.setCusId(cusId);
				cashRecord.setCourseId(course.getCourseId());
				cashRecord.setType(3);// 2代表后台赠送 3代表后台修复
				cashRecord.setCtId(ctId);// 把订单id记录到流水表中
				cashRecord.setStatus(1);
				cashRecord.setPackId(sellId);
				this.cashRecordService.addCashRecord(cashRecord);

				// 把每一条添加到购买记录表中
				gmercord.setUserId(cusId);
				gmercord.setCourseId(course.getCourseId());
				gmercord.setAddTime(date);
				this.gmrecordService.addGmrecord(gmercord);
			}

		} catch (Exception e) {
			log.error("初始化体验用户课程错误：", e);
		}

	}

	/**
	 * ajax判断登陆学员是否填写手机号
	 * 
	 * @return
	 */
	public String hasMobile() {
		try {
			customer = customerService.getCustomerById(getLoginUserId());
			setResult(new Result(false, "no", null, null));
			if (customer != null && customer.getMobile() != null
					&& !customer.getMobile().equals("")) {
				setResult(new Result(false, "has", null, null));
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.hasMobile", e);
		}
		return "json";
	}

	/**
	 * 通过ajax添加学员手机号信息，并赠送优惠券序列码，发送到其手机
	 * 
	 * @return
	 */
	public String addMobile() {
		try {
			Customer cus = customerService.getCustomerById(getLoginUserId());
			cus.setMobile(customer.getMobile());
			customerService.updateCustomer(cus);
			String serialNumber = addCP(cus);
			setResult(new Result<String>(false, "true", null, serialNumber));
		} catch (Exception e) {
			logger.error("CustomerWebAction.addMobile", e);
		}
		return "json";
	}

	public String sendSNAgain() {
		try {
			smsService.sendEx("感谢您注册嗨学网会员，注册即送优惠券，优惠券序列号为:" + serialNumber
					+ "。", customer.getMobile(), "", "", "");
			setResult(new Result<String>(false, "success", null, null));
		} catch (Throwable t) {
			logger.error("CustomerWebAction.addMobile", t);
			setResult(new Result<String>(false, "failure", null, null));
		}
		return "json";
	}

	/**
	 * 获取客户IP
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	public String exit() {
		try {
			doExit();
		} catch (Exception e) {
			logger.error("CustomerWebAction.exit", e);
			return ERROR;
		}
		return "homePage";
	}

	/**
	 * 注销
	 * 
	 * @return
	 */
	public String ajaxExit() {
		try {
			doExit();
			setResult(new Result<String>(false, "success", null, null));
		} catch (Exception e) {
			logger.error("CustomerWebAction.ajaxExit", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * 用户个性签名保存
	 * 
	 * @return
	 */
	public String ajaxContent() {
		try {
			if (StringUtils.isNotEmpty(content)) {
				if (!PreventInfusion.sql_inj(content)) {
					Thread.sleep(1000);
					int userId = getLoginUserId();
					if (userId == 0) {
						userId = customer.getCusId();
					}
					customer = customerService.getCustomerById(userId);
					if (StringUtils.equals(customer.getSignature(), content)) {
						setResult(new Result<String>(false, "success", content,
								null));
						return "json";
					}
					customerService.updateCusSignature(this.getLoginUserId(),
							content);
					setResult(new Result<String>(false, "success", content,
							null));
				}
			}
			setResult(new Result<String>(false, "success", content, null));
		} catch (Exception e) {
			logger.error("CustomerWebAction.ajaxContent", e);
			setResult(new Result<String>(false, "failure", null, null));
		}
		return "json";
	}

	/**
	 * 获取他们在嗨学数据
	 * 
	 * @return json
	 */
	public String ajaxOtherCusInfo() {
		try {
			if (id != 0) {
				PublicResult result = new PublicResult();
				List<String> sellResult = courseService
						.getUserSellWayByCusId(id);
				result.setSellResult(sellResult);
				result.setSellSize(sellResult.size());
				List<String> courseResult = courseService
						.getUserCourseByCusId(id);
				result.setCourseResult(courseResult);
				result.setCourseSize(courseResult.size());
				setResult(new Result<String>(true, "success", null, JsonUtil
						.getJsonString4JavaPOJO(result)));
				return "json";
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.ajaxOtherCusInfo", e);
			setResult(new Result<String>(false, "failure", null, null));
		}
		return "json";
	}

	/**
	 * 用户观看记录
	 * 
	 * @return
	 */
	public String ajaxVideoRecord() {
		String result = null;
		try {
			int cusId = getLoginUserId();
			result = CusUtil.getCusInfoById(cusId);
			if (null != result) {
				setResult(new Result<String>(false, "success", result, null));
				return "json";
			}
			setResult(new Result<String>(false, "success", "", null));
		} catch (Exception e) {
			logger.error("CustomerWebAction.ajaxVideoRecord", e);
			setResult(new Result<String>(false, "failure", null, null));
		}
		return "json";
	}

	/**
	 * 获取用户公告
	 * 
	 * @return
	 */
	public String ajaxAcment() {
		try {
			if (StringUtils.isNotEmpty(subjectIds)) {
				acmList = acementService.getTop10(subjectIds); // 获取课程公告
				if (!acmList.isEmpty()) {
					setResult(new Result<String>(true, "success", JSONArray
							.fromObject(acmList).toString(), null));
					return "json";
				}
			} else {
				acmList = acementService.getTop10(customerService
						.getCustomerById(getLoginUserId()).getSubjectId()
						+ "");
				if (!acmList.isEmpty()) {
					setResult(new Result<String>(true, "success", JSONArray
							.fromObject(acmList).toString(), null));
					return "json";
				}
			}
			setResult(new Result<String>(false, "failure", "", null));
		} catch (Exception e) {
			logger.error("CustomerWebAction.ajaxAcment", e);
			setResult(new Result<String>(false, "failure", null, null));
		}
		return "json";
	}

	private void doExit() {
		memcachedClient.delete(String.valueOf(getLoginUserId()));
		memcachedClient.delete(String.valueOf(CusUtil
				.getCusVideoKey(getLoginUserId())));
	}

	private boolean isshiting = false;

	public boolean isIsshiting() {
		return isshiting;
	}

	public void setIsshiting(boolean isshiting) {
		this.isshiting = isshiting;
	}
	
	/*
     * 输出课程
     */
	public String toCourseShu(){
    	
    	try{
    		int userId = getLoginUserId();
    		if(userId == 0){
    			userId = customer.getCusId();
    		}
    		
    		//鏍规嵁鐢ㄦ埛id鍒ゆ柇鐢ㄦ埛鏈夋病鏈変拱璇剧▼
    		
    		QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
    		queryCusCouKpointCondition.setCusId(userId);
    		queryCusCouKpointCondition.setCourseId(course.getCourseId());
    		
    		//List<UserCenterKpointDTO> mylist1 = cusCouKpointService.getUserCenterKpointDTOByCusIdAndCourseId(queryCusCouKpointCondition);
    		List<UserKpointDTO> mylist = cusCouKpointService.getKpointListByCusIdAndCourseId(queryCusCouKpointCondition);
    		
    		if(mylist != null&& mylist.size()>0){
    			JSONArray jslist = JSONArray.fromObject(mylist);
    			this.setResult(new Result(false,jslist.toString(),null,mylist));
    			
    		}else{
    			this.setResult(new Result<String>(false,"error",null,null));
    			
    		}
    	}catch(Exception e){
    		logger.error("CustomerWebAction.toCourseShu", e);
    		this.setResult(new Result<String>(false,"error",null,null));
    	}
    
    	return "json";
    }

	/**
	 * 用户查看知识点
	 * 
	 * @return
	 */
	public String toKpoint() {
		try {
			// 获得用户ID
			int userId = getLoginUserId();
			
			//查出该课程下所有小节
			QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
			queryCusCouKpointCondition.setCusId(userId);
			queryCusCouKpointCondition.setCourseId(course.getCourseId());
			
			kpoints = cusCouKpointService.getKpointListByCusIdAndCourseId(queryCusCouKpointCondition);

			//add by shixf 获取某个课程下视频的观看人数
			Map<Integer, Map<Integer, Integer>> keyMap = getKpointCount(course.getCourseId());
			
			 //判断用户是否看过的判断
			QueryVideoStatisticsCondition queryVideoStatisticsCondition = new QueryVideoStatisticsCondition();
			queryVideoStatisticsCondition.setCourseId(course.getCourseId());
			queryVideoStatisticsCondition.setCusId(userId);
			List<VideoStatistics> videoStatisList = videoStatisticsService.getVideoStatisticsListByCouseId(queryVideoStatisticsCondition);

			//查询该课程
			course = courseService.getCourseById(course.getCourseId());
			
			for(UserKpointDTO kpdto : kpoints){
				Map<Integer, Integer> vedioMap = keyMap.get(course.getCourseId());
				  if(vedioMap.containsKey(kpdto.getId())){
					 kpdto.setHasRdCount(vedioMap.get(kpdto.getId()));
					  if(kpdto.getHasRdCount() > maxnumber){maxnumber = kpdto.getHasRdCount();}
					    if(!videoStatisList.isEmpty()){
						  for(VideoStatistics var : videoStatisList){
						    if(StringUtils.contains(var.getViewCode(),String.valueOf(kpdto.getId()))){
								  kpdto.setRdState(1);
							}
						 }
					}
				}
			}
			return "courseDetaile";
		} catch (Exception e) {
			logger.error("CustomerWebAction.toCourseShu", e);
		}
		return "courseDetaile";
	}
	
	

	/**
	 * 根据课程ID统计该课程下节点观看总数
	 * 
	 * @param courseId
	 *            课程ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Map<Integer, Integer>> getKpointCount(int courseId) {
		Map<Integer, Map<Integer, Integer>> resultMap = 
			(Map<Integer, Map<Integer, Integer>>) memcachedClient.get(CusUtil.getKpointKey(courseId));
		if (resultMap != null) {
			return resultMap;
		} else {
			resultMap = CusUtil.getKpointCount(courseId);
			memcachedClient.set(CusUtil.getKpointKey(courseId), resultMap,
					new Date(System.currentTimeMillis() + 21600000));
			return resultMap;
		}
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	public String toUserCenter2() {
		int userId = getLoginUserId();
		if (userId == 0) {
			userId = customer.getCusId();
		}
		// 最新按授卖方式获得start
		if (userId != 0) {
			try {
				QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
				queryCourseCondition.setCusId(this.getLoginUserId());
				// 取得购买成功的包
				butSellWayList = courseService
						.getUserCenterSellWayListByCusId(queryCourseCondition);
				UserCenterSubjectCourseDTO sellwayCourseTemp = null;// 存放到最后存储的包的list
				getSubjectCourseList();
				getSellWayCourseList();
				QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
				queryCusCouKpointCondition.setCusId(userId);
				if (null != butSellWayList && butSellWayList.size() > 3) {
					/* 存储subjectID 用来查询公告（2011.09.23） */
					Set<Integer> set = new HashSet<Integer>();
					for (int i = 3; i < butSellWayList.size(); i++) {
						set.add(butSellWayList.get(i).getSubjectId()); // 存储subjectID
						SellWay sellWay = butSellWayList.get(i);
						// 取得包下的课程
						sellwayCourseTemp = new UserCenterSubjectCourseDTO();
						sellwayCourseTemp.setSellWay(sellWay);// 设置包
						// 获得一个包下所有课程的list
						List<UserCenterCourseDTO> templist = new ArrayList<UserCenterCourseDTO>();
						templist = cusCouKpointService
								.getCouserListBySellId(sellWay.getSellId() + "");
						if (null != templist) {
							// 查找课程的知识点和上传视频数量
							for (int j = 0; j < templist.size(); j++) {
								UserCenterCourseDTO temp = templist.get(j);
								queryCusCouKpointCondition.setCourseId(temp
										.getCourseId());
								temp
										.setKpointList(cusCouKpointService
												.getUserCenterKpointDTOByCusIdAndCourseId(queryCusCouKpointCondition));
								temp.setUploadedSize(cusCouKpointService
										.getCourseVedioSizeByCourseId(temp
												.getCourseId()));// 上传的课程数量
								temp.setVedioSize(temp.getKpointList().size());// 课程叶子节点数量
								sellwayCourseTemp.getCourseList().add(temp);// 设置包下的课程课程
							}
						}

						this.getSellWayCourseListMore().add(sellwayCourseTemp);// 添加到包的list中
					}
					if (sellWayCourseListMore != null) {
						setResult(new Result<List<UserCenterSubjectCourseDTO>>(
								false, "success", null, sellWayCourseListMore));
					} else {
						setResult(new Result<String>(false, "failure", null,
								null));
					}
				}
			} catch (Exception e) {
				logger.error("个人中心跳转错误,toUserCenter2！", e);
				this.setResult(new Result<String>(false, "error", null, null));
			}
		}
		// 最新按授卖方式获得end
		return "json";
	}

	public String toUserCenter3() {
		try {
			SellWay sellWay = sellWayService.getSellWayById(id);
			UserCenterSubjectCourseDTO sellwayCourseTemp;
			QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
			if (sellWay != null) {
				sellwayCourseTemp = new UserCenterSubjectCourseDTO(); // 显示类以专业来显示专业下面的课程集合
				sellwayCourseTemp.setSellWay(sellWay);
				List<UserCenterCourseDTO> templist = new ArrayList<UserCenterCourseDTO>();
				templist = cusCouKpointService.getCouserListBySellId(sellWay
						.getSellId()
						+ "");
				if (null != templist) {
					for (int j = 0; j < templist.size(); j++) {

						UserCenterCourseDTO temp = templist.get(j);
						queryCusCouKpointCondition.setCourseId(temp
								.getCourseId());
						temp
								.setKpointList(cusCouKpointService
										.getUserCenterKpointDTOByCusIdAndCourseId(queryCusCouKpointCondition));
						sellwayCourseTemp.getCourseList().add(temp);// 设置包下的课程课程
					}
				}
				if (sellwayCourseTemp.getCourseList() != null
						&& sellwayCourseTemp.getCourseList().size() > 0) {
					getSellWayCourseListMore().add(sellwayCourseTemp);// 添加到包的list中
				}

			}
		} catch (Exception e) {
			logger.error("个人中心跳转错误！", e);
		}
		// 通过商品id获取数据
		return "toUserCenter3";
	}

	private IActionRecord actionRecordService;
	private List<ActionRecord> actionRecords;
	private QueryActionRecordCondition queryActionRecordCondition;
	private List freshlist;
	private List<ActionRecord> myfreshlist;

	/**
	 * 转向个人中心
	 * 
	 * @return
	 */
	public String toUserCenter() {
		int userId = getLoginUserId();
		if (userId == 0) {
			userId = customer.getCusId();
		}
		customer = customerService.getCustomerById(userId);
		// 最新按授卖方式获得start
		/* 存储subjectID 用来查询公告（2011.09.23） */
		Set<Integer> set = new HashSet<Integer>();
		if (userId != 0) {
			try {
				QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
				queryCourseCondition.setCusId(this.getLoginUserId());

				// 取得购买成功的包
				butSellWayList = courseService
						.getUserCenterSellWayListByCusId(queryCourseCondition);
				UserCenterSubjectCourseDTO sellwayCourseTemp = null;// 存放到最后存储的包的list
				getSubjectCourseList();
				getSellWayCourseList();
				QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
				queryCusCouKpointCondition.setCusId(userId);

				if (null != butSellWayList && butSellWayList.size() > 0) {
					/* 已经购买过课程add by liuqg 20110610 */
					ishavebuy = true;
					sellIds = new String();
					for (int i = 0; i < butSellWayList.size(); i++) {
						set.add(butSellWayList.get(i).getSubjectId()); // 存储subjectID
						SellWay sellWay = butSellWayList.get(i);
						sellIds += sellWay.getSellId() + ",";
					}
					for (Integer integer : set) {
						subjectIds = subjectIds + integer + ",";
					}
					subjectIds = subjectIds.substring(0,
							subjectIds.length() - 1); // 去掉最后一个逗号
				} else {/* add by liuqg 免费试听部分 */
					// Date dateishavebuy = new Date();
					// if(dateishavebuy.getTime()-customer.getRegTime().getTime()<86400000*2){}
					ishavebuy = false;
					isshiting = true;
					isshitingdown = 1;
					subject = subjectService.getSubjectById(customer
							.getSubjectId());
					// 将试听课程编号从数据库中取出数据
					String freeClasses[] = subject.getFreeClasses().split(",");
					for (int i = 0; i < freeClasses.length; i++) {
						// 根据配置的课程编号查出售卖方式的课程编号
						SellWay sellWay = sellWayService.getSellWayById(Integer
								.valueOf(freeClasses[i]));
						if (sellWay != null) {
							sellwayCourseTemp = new UserCenterSubjectCourseDTO(); // 显示类以专业来显示专业下面的课程集合
							sellwayCourseTemp.setSellWay(sellWay);
							List<UserCenterCourseDTO> templist = new ArrayList<UserCenterCourseDTO>();
							templist = cusCouKpointService
									.getCouserListBySellId(sellWay.getSellId()
											+ "");
							if (null != templist) {
								for (int j = 0; j < templist.size(); j++) {
									UserCenterCourseDTO temp = templist.get(j);
									queryCusCouKpointCondition.setCourseId(temp
											.getCourseId());
									temp
											.setKpointList(cusCouKpointService
													.getUserCenterKpointDTOByCusIdAndCourseId(queryCusCouKpointCondition));
									sellwayCourseTemp.getCourseList().add(temp);// 设置包下的课程课程
								}
							}
							if (sellwayCourseTemp.getCourseList() != null
									&& sellwayCourseTemp.getCourseList().size() > 0) {
								getSellWayCourseListMore().add(
										sellwayCourseTemp);// 添加到包的list中
							}
						}
					}
				}/* add by liuqg 免费试听部分 */
			} catch (Exception e) {
				logger.error("个人中心跳转错误！", e);
			}
		}
		// 最新按授卖方式获得end
		// 从cookie中取出subjectId,做为查询条件，属于哪个专业下面的
		String subject = CookieHandler.getCookieValueByName(servletRequest,
				"subjectId");
		if (!ishavebuy) {
			getUserStatistics(customer, subject);// 如果用户未购买统计该专业下信息
		} else {
			getUserVideoInfo(customer);// 如果用户已购买课程查询用户观看记录
		}
		return "userCenter";
	}

	/**
	 * 获取用户相关统计信息 HQL
	 */
	private void getUserVideoInfo(Customer customer) {
		try {
			Object obj = memcachedClient.get(CusUtil.getCusVideoKey(customer
					.getCusId()));
			if (null != obj) {
				buyInfo = (CusBuyInfo) obj;
				return;
			}
			List<CusRankInfo> resultList = customerService
					.getCusRandByCusId(customer.getCusId());// 获取用户统计数据
			buyInfo = CusUtil.getCusBuyInfo(customer.getCusId(), resultList);
			processCusInfo(buyInfo, customer);
			memcachedClient.set(CusUtil.getCusVideoKey(customer.getCusId()),
					buyInfo, new Date(System.currentTimeMillis() + 21600000));
		} catch (Exception e) {
			buyInfo = new CusBuyInfo();
			logger.info("CustomerWebAction.getUserVideoInfo", e);
		}
	}

	/**
	 * 如果用户数据统计不在5000以内单次统计
	 * 
	 * @param buyInfo
	 */
	private void processCusInfo(CusBuyInfo buyInfo, Customer customer) {
		try {
			if (buyInfo.getLoginCount() == 0) { // 登录次数
				buyInfo.setLoginCount(loginRecordService
						.getLoginCountByCusId(customer.getCusId()));
			}
			if (buyInfo.getWatchVideoTime() == 0) {// 观看节数
				buyInfo.setWatchVideoTime(CusUtil.getCusVideoTotal(customer
						.getCusId()));
			}
			if (buyInfo.getWorkQuestionCount() == 0) {// 做多少试题
				buyInfo.setWorkQuestionCount(exampaperService
						.getExampaperTotal(customer.getCusId()));
			}
		} catch (Exception e) {
			logger.info("CustomerWebAction.processCusInfo", e);
		}
	}

	/**
	 * 获取用户未购买信息
	 * 
	 * @param customer
	 */
	private void getUserStatistics(Customer customer, String subjectId) {
		try {
			Object obj = memcachedClient.get(CusUtil.getCusVideoKey(subjectId));
			if (null != obj) {
				notBuyInfo = (CusNotBuyInfo) obj;
				return;
			}
			int classmate = customerService.getCustomerClassmate(customer
					.getSubjectId());// 同班同学
			int avgVideo = CusUtil.getCusVideoCount(customer.getSubjectId());// 观看视频时长
			int avgExampaper = exampaperService.getCusExampaperCount(customer
					.getSubjectId());// 做了多少试题
			int avgProblem = problemService.getCusProblemCount(customer
					.getSubjectId());// 提了多少问题
			int avgReplyProblem = problemService
					.getCusProblemReplyCount(customer.getSubjectId());// 回复了多少问题
			notBuyInfo = CusUtil.getCusNotBuyInfo(classmate, avgVideo,
					avgExampaper, avgProblem, avgReplyProblem, customer
							.getSubjectId());
			memcachedClient.set(CusUtil.getCusVideoKey(subjectId), notBuyInfo,
					new Date(System.currentTimeMillis() + 21600000));
		} catch (Exception e) {
			notBuyInfo = new CusNotBuyInfo();
			logger.info("CustomerWebAction.getUserStatistics", e);
		}
	}

	/**
	 * 前台flash播放页面，间隔请求此地址...用与判断此用户是否需要重新登录
	 * 
	 * @return
	 */
	public String isUserLogined() {
		try {
			String cookieKey = CookieHandler.getCookieValueByName(
					servletRequest, "sedu.cookie.ukey");
			String uid = "";
			String uKey = "";
			if (!StringUtil.isNullString(cookieKey)) {
				String str[] = cookieKey.split(",");
				uid = str[0];
				uKey = str[2];
				if (uid != null && uid.trim().length() > 0 && uKey != null
						&& uKey.trim().length() > 0) {
					String memUkey = (String) memcachedClient.get(uid);
					// ukey存在，cache中不存在允许通过,并写入memcached(cache服务器重启动)
					if (memUkey == null) {
						// memcachedClient.set(uid, uKey,60);
						setResult(new Result<String>(false, "success", null,
								null));
						return "json";
						// cache中存在,与cookie中的uKey相同,允许通过
					} else if (memUkey.equals(uKey)) {
						setResult(new Result<String>(false, "success", null,
								null));
						return "json";
					}
					// cookie中与cache中不同，返回失败
					setResult(new Result<String>(false, "failed", null, null));
					return "json";
				}
			}
		} catch (Exception e) {
			logger.info("CustomerWebAction.isUserLogined", e);
			setResult(new Result<String>(false, "success", null, null));
		}
		return "json";
	}

	private void prepareStudyTypeParm(Customer cus) {
		String studyTypeStr = cus.getStudyType();

		String[] str = studyTypeStr.split(",");
		int index = Integer.parseInt(str[0]);
		int totalCount = Integer.parseInt(str[5]);
		int[] answers = new int[4];
		int[] results = new int[4];

		for (int i = 1; i < 5; i++) {
			answers[i - 1] = Integer.parseInt(str[i]);
		}

		results[0] = answers[0] * 100 / totalCount;
		results[1] = answers[1] * 100 / totalCount;
		results[2] = answers[2] * 100 / totalCount;

		for (int i = 0; i < 3; i++) {
			results[i] = results[i] < 1 ? 1 : results[i];
			results[i] = results[i] > 97 ? 97 : results[i];
		}

		results[3] = 100 - results[0] - results[1] - results[2];

		studyTypePram = new HashMap();
		studyTypePram.put("studyType", index);
		studyTypePram.put("results", results);
	}

	private String getCurTime() {
		return "sedu" + new Date().getTime();
	}

	/**
	 * 注册
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String register() {
		try {

			// 验证emial是否可用
			if (customer == null
					|| !customerService.checkEmail(customer.getEmail())
					|| customer.getEmail() == null
					|| customer.getEmail().trim().equals("")
					|| customer.getCusPwd() == null
					|| customer.getCusPwd().trim().equals("")
					|| customer.getCusName() == null
					|| customer.getCusName().trim().equals("")) {
				return "emailInUsed";
			}
			if (PreventInfusion.sql_inj(customer.getEmail())
					|| PreventInfusion.sql_inj(customer.getCusName())) {
				return "regDangerWord";
			}

			customer.setEmail(PreventInfusion.xssEncode(customer.getEmail()));
			customer.setCusName(PreventInfusion
					.xssEncode(customer.getCusName()));
			// 注册来源
			String cusFromUrl = servletRequest.getServerName();
			// 设置基础参数，登陆次数，性别，完善状态
			customer.setCusFromUrl(cusFromUrl);
			customer.setLoginTimes(0);
			customer.setSex("1");
			customer.setIsComplete(Customer.CUS_COMPLETE_FALSE);
			customer.setCusPwd(MD5.getMD5(customer.getCusPwd()));
			// Yaning 加入for Crm
			customer.setRegTime(new Date());
			// 添加注册来源
			String webFrom = CookieHandler.getCookieValueByName(servletRequest,
					"webFrom");
			String webAgent = CookieHandler.getCookieValueByName(
					servletRequest, "webAgent");
			if (webFrom != null && !webFrom.trim().equals("")
					&& !webFrom.trim().equals("null")) {
				customer.setCusWebFrom(webFrom);
			}
			if (webAgent != null && !webAgent.trim().equals("")
					&& !webAgent.trim().equals("null")) {
				customer.setCusWebAgent(webAgent);
			}
			int cusId = customerService.addCustomer(customer);

			// 处理优惠券
			// processUncoupon();

			// 注册时赠送100积分
			addTsScore(cusId);

			// 发送email通知
			customerService.sendRegEmail(customer);
			// 给Crm发送信息
			try {
				if (!configurator.getProjectURL().contains("haixue.com")) {
					taskExecutor.execute(new SendRegisterInfo2Crm(customer,
							configurator));
				}
			} catch (Exception e) {
				logger.error("CustomerWebAction.register.send2Crm", e);
			}
			setRegister(true);
			return login();
		} catch (Exception e) {
			logger.error("CustomerWebAction.register", e);
			return ERROR;
		}
	}

	/**
	 * 注册（ajax用）
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String register4ajax() {
		try {
			logger.info("register4ajax");
			// 验证emial是否可用
			if (customer == null
					|| !customerService.checkEmail(customer.getEmail())
					|| customer.getEmail() == null
					|| customer.getEmail().trim().equals("")
					|| customer.getCusPwd() == null
					|| customer.getCusPwd().trim().equals("")) {
				setResult(new Result<String>(false, "emailInUsed", null, null));
				return "json";
			}
			if (PreventInfusion.sql_inj(customer.getEmail())
					|| PreventInfusion.sql_inj(customer.getCusName())) {
				setResult(new Result<String>(false, "regDangerWord", null, null));
				return "json";
			}

			/**
			 * 杨宁加入服务器验证手机号不可为空
			 */
			if (customer.getMobile() == null
					|| customer.getMobile().trim().length() <= 0) {
				setResult(new Result<String>(false, "regMobileNone", null, null));
				return "json";
			}

			/**
			 * 验证码 if(randomCode==null||!randomCode.equals(this.getSession(
			 * "back_rand_code"))){ setResult(new Result<String>(false,
			 * "err.randCode", null, null)); return "json"; }
			 */
			String protoPwd = customer.getCusPwd();
			getServletRequest().getSession().setAttribute("protoPwd", protoPwd);
			customer.setEmail(PreventInfusion.xssEncode(customer.getEmail()));
			customer.setCusName(PreventInfusion
					.xssEncode(customer.getCusName()));

			// 注册来源
			String cusFromUrl = servletRequest.getServerName();
			// 设置基础参数，登陆次数，性别，完善状态
			customer.setCusFromUrl(cusFromUrl);
			customer.setLoginTimes(0);
			customer.setSex("1");
			customer.setIsComplete(Customer.CUS_COMPLETE_FALSE);
			customer.setCusPwd(MD5.getMD5(customer.getCusPwd()));
			// Yaningning加入for Crm
			customer.setRegTime(new Date());
			// 添加注册来源
			String webFrom = CookieHandler.getCookieValueByName(servletRequest,
					"webFrom");
			String webAgent = CookieHandler.getCookieValueByName(
					servletRequest, "webAgent");
			if (webFrom != null && !webFrom.trim().equals("")
					&& !webFrom.trim().equals("null")) {
				customer.setCusWebFrom(webFrom);
			}
			if (webAgent != null && !webAgent.trim().equals("")
					&& !webAgent.trim().equals("null")) {
				customer.setCusWebAgent(webAgent);
			}

			// 注册用户
			int cusId = customerService.addCustomer(customer);

			// 给Crm发送信息
			try {
				if (!configurator.getProjectURL().contains("haixue.com")) {
					taskExecutor.execute(new SendRegisterInfo2Crm(customer,
							configurator));
				}
			} catch (Exception e) {
				logger.error("CustomerWebAction.register4ajax.send2Crm", e);
			}
			setRegister(true);
			login_reg(cusId);// 尊敬的用户您好，您已经成功注册嗨学网，您现在登陆即可免费体验嗨学网[相关项目专业]课程24小时
			setResult(new Result<String>(false, "success", null, customer
					.getEmail()));
			return "json";
		} catch (Exception e) {
			logger.error("CustomerWebAction.register4ajax", e);
			return ERROR;
		}
	}

	// 注册后执行的方法,把注册时可后续做的操作，放在此处进行,提高注册速度
	public String doAfterReg() {
		try {
			logger.info("doAfterReg");
			int cusId = getLoginUserId();
			// 查询积分避免重复操作***
			QueryTotolsScoreCondition queryTotolsScoreCondition = new QueryTotolsScoreCondition();
			queryTotolsScoreCondition.setCusId(cusId);
			List<TotolsScore> custols = totolsScoreService
					.getTotolsScoreList(queryTotolsScoreCondition);
			if (custols == null || custols.size() == 0) {
			} else {
				setResult(new Result<String>(false, "success", null, null));
				log.info("注册后续操作重复操作!");
				return "json";
			}
			// 查询积分避免重复操作***
			customer = customerService.getCustomerById(cusId);
			// 注册时赠送100积分
			addTsScore(cusId);
			// 发送email通知
			// customerService.sendRegEmail(customer);
			// 注册发送消息
			Message msg = messageService.getMessageByKey("register");
			if (msg != null && msg.getMsgId() > 0) {
				User sender = new User();
				sender.setUserId(1);
				sender.setUserName("超级管理员");
				userMsgService.adminerSendMsgToCutomer(sender, msg.getMsgId(),
						customer);
			}

			// 初始化所有任务
			QueryTaskCondition queryTaskCondition = new QueryTaskCondition();
			List<Task> taskList = taskService.getTaskList(queryTaskCondition);

			if (taskList != null) {
				TaskCus taskCus = null;
				for (int i = 0; i < taskList.size(); i++) {
					taskCus = new TaskCus();
					taskCus.setCusId(customer.getCusId());
					taskCus.setTaskId(taskList.get(i).getTaskId());
					taskCusService.addTaskCus(taskCus);
				}
			}

			String protoPwd = (String) getServletRequest().getSession()
					.getAttribute("protoPwd");

			// 登陆次数加1，登陆ip变更
			customer.setLoginTimes(customer.getLoginTimes() + 1);
			customer.setLastloginIP(getIpAddr(getServletRequest()));

			// 记录登陆信息
			LoginRecord loginRecord = new LoginRecord();
			loginRecord.setCusId(customer.getCusId());
			loginRecord.setLoginIp(getIpAddr(getServletRequest()));
			loginRecord.setLoginTime(new Date());
			loginRecordService.addLoginRecord(loginRecord);
			// 上一次登录的日期，和这次登录的日期进行比较看是否是当天的第一次登录

			Calendar beforeDate = Calendar.getInstance();
			Date date = customer.getLastloginTime();
			beforeDate.setTime(date);

			customer.setLastloginTime(new Date());
			customerService.updateCustomer(customer);

			String sendStr = this.yanSendStr(customer, "reg");
			sendSms(sendStr);
			/*
			 * }else{ sendSms("恭喜您注册成功，您的帐号：" + customer.getEmail() + ",密码:" +
			 * protoPwd + "。使用帐号密码登录我的highso,即可免费试听嗨学网课程[24小时内有效]"); }
			 */
			// 春节加代码结束
			setResult(new Result<String>(false, "success", null, null));
			getServletRequest().getSession().removeAttribute("protoPwd");
		} catch (Exception e) {
			setResult(new Result<String>(false, "error", null, null));
			logger.error("注册后续操作错误!", e);
		}
		return "json";
	}

	/**
	 * 根据用户所学专业查找与之相关联试卷信息 Author:HQL CreateDate:2012-06-20
	 */
	public String SendMsgByCus() {
		int cus_id = this.getLoginUserId();
		Customer Customer = customerService.getCustomerById(cus_id);
		// 判断用户短信表里是否有当天发送的短信息
		if (userMsgService.WhetherHaveCustomerMsg(Customer.getCusId(),
				UserMsg.SENDER_MSG_TYPE)) {
			this.setResult(new Result<Object>(true, "success", null, null));
			return "json";// 如果有就不用重复发送
		}
		// 根据用户所学专业和今天之前2天时间查询试卷信息
		List<Exampaper> examList = exampaperService.getExampaperTop10(Customer
				.getSubjectId());
		if (!examList.isEmpty()) {
			// 发送站内信
			StringBuffer content = new StringBuffer();
			content.append("您好，HighSo【最新上传试卷】");
			for (Exampaper var : examList) {
				content.append("<br/>");
				content.append("&nbsp;&nbsp;&nbsp;<a target='_blank' href='"
						+ getServletRequest().getContextPath()
						+ "/exam/qstManager!getExamPaperInfo.action?epid="
						+ var.getEpId() + "'>" + var.getEpName() + "</a>");
			}
			content.append("<br/>");
			Message msg = new Message();
			msg.setMsgType(UserMsg.SENDER_MSG_TYPE);// 消息类型
			msg.setCreateTime(new Date());// 创建时间
			msg.setMsgContent(content.toString());// 消息内容
			messageService.addMessage(msg);

			User sender = new User();
			sender.setUserId(1);
			sender.setUserName("超级管理员");
			userMsgService.adminerSendMsgToCutomer(sender, msg.getMsgId(),
					Customer);
		}
		this.setResult(new Result<Object>(true, "success", null, null));
		return "json";
	}

	/**
	 * 功能:手机注册发送验证码(生成随机码后，将随机码与用户手机一起md5写入cookie中)
	 * 
	 * @return Author:Yangning CreateDate:2012-3-9
	 */
	public String getMobileValidataCode() {
		Integer randCode = 0;
		try {
			if (customer.getMobile() != null) {
				Random rand = new Random();
				randCode = rand.nextInt(8999) + 1000;
				String randCodeHash = MD5.getMD5(randCode.toString()
						+ customer.getMobile() + "Sunland");
				String content = "您的注册验证码为：" + randCode
						+ ",请在网站输入完成验证【嗨学网—远程培训领跑者】";
				smsService.sendEx(content, customer.getMobile(), "", "", "");
				setResult(new Result<String>(true, "success", null,
						randCodeHash));
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.getMobileValidataCode", e);
			setResult(new Result<String>(false, "error", null, randCode
					.toString()));
		}
		return "json";
	}

	/**
	 * 验证手机与验证码(将前台传递md5值与生成md5值比较)
	 * 
	 * @return Author:Yangning CreateDate:2012-3-10
	 */
	public String valMobile() {
		try {
			String mobileHashCode = CookieHandler.getCookieValueByName(
					getServletRequest(), "valMoblieCode");
			if (mobileHashCode != null && mobileHashCode.trim().length() > 0
					&& customer != null && customer.getMobile() != null
					&& customer.getMobile().trim().length() > 0
					&& randomCode != null && randomCode.trim().length() > 0) {
				String clientMd5 = MD5.getMD5(randomCode + customer.getMobile()
						+ "Sunland");
				if (clientMd5.equals(mobileHashCode)) {
					sendMessage("true");
					return null;
				}
			}
			sendMessage("false");
		} catch (Exception e) {
			try {
				sendMessage("false");
			} catch (IOException e1) {
				logger.error("CustomerWebAction.valMobile", e);
			}
			logger.error("CustomerWebAction.valMobile", e);
		}
		return null;
	}

	/**
	 * 根据项目获取要发送的短信内容
	 * 
	 * @param customer
	 * @return
	 */
	private String yanSendStr(Customer customer, String type) {
		try {
			String protoPwd = (String) getServletRequest().getSession()
					.getAttribute("protoPwd");
			String sendStr = "";
			String contr = "";
			String start = "";
			String end = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if (type.equals("reg")) {
				contr = "恭喜您注册成功，登录帐号：" + customer.getEmail() + ",密码:"
						+ protoPwd;
			}
			if (type.equals("login")) {
				contr = "欢迎" + customer.getEmail() + "登录嗨学网试听学习";
			}
			String biaozhun = "恭喜您注册成功，wgv帐号：" + customer.getEmail() + ",密码:"
					+ protoPwd + "。使用帐号密码登录我的highso,即可免费试听嗨学网课程[24小时内有效]";
			if (true) {
				sendStr = "恭喜您注册成功，您的帐号：" + customer.getEmail() + ",密码:"
						+ protoPwd + "。使用帐号密码登录我的highso,即可免费试听嗨学网课程[24小时内有效]";
			}
			getServletRequest().getSession().removeAttribute("protoPwd");
			return sendStr;
		} catch (Exception e) {
			logger.error("注册发送短信错误筛选发送内容时" + e.toString());
			return null;
		}
	}

	private String addCP(Customer cus) {
		Coupon coupon = couponService.getCouponByPrice(10);
		String sn = DateUtil.getCouponTime() + cus.getCusId();

		Uncoupon uncoupon = new Uncoupon();
		uncoupon.setSerialNumber(sn);
		uncoupon.setId(coupon.getId());
		uncoupon.setStatus(Uncoupon.UNCP_STATUS_USABLE);

		CpCus cpCus = new CpCus();
		cpCus.setId(coupon.getId());
		cpCus.setCusId(cus.getCusId());
		cpCus.setSerialNumber(sn);
		cpCus.setStatus(CpCus.CPCUS_STATUS_USABLE);

		uncouponService.addUncoupon(uncoupon);
		cpCusService.addCpCus(cpCus);

		try {
			smsService.sendEx("感谢您注册嗨学网会员，注册即送优惠券，优惠券序列号为:" + sn + "。",
					customer.getMobile(), "", "", "");
		} catch (Throwable t) {
			logger.error("CustomerWebAction.addCP", t);
		}
		return sn;
	}

	/**
	 * 为注册成功的用户赠送积分
	 * 
	 * @throws IOException
	 * @throws IOException
	 */
	private void addTsScore(int cusId) {
		Date date = new Date();
		// 积分表进行记录
		TotolsScore totolsScore = new TotolsScore();
		totolsScore.setCusId(cusId);
		// 兑换积分 +50
		int tsA = totolsScore.getTsCurrent();
		tsA = tsA + 55;
		totolsScore.setTsCurrent(tsA);
		// 成长积分（经验值） +50
		int tsC = totolsScore.getTsAction();
		tsC = tsC + 55;
		totolsScore.setTsAction(tsC);
		int tsId = this.totolsScoreService.addTotolsScore(totolsScore);

		// 积分记录表进行记录

		// 兑换积分记录
		TsRecord tsRecord = new TsRecord();
		tsRecord.setCusId(cusId);
		tsRecord.setTrType(TsRecord.TRTYPE_FOR);
		tsRecord.setAccessType(TsRecord.ACCESSTYPE_FOR_REGISTE);
		tsRecord.setAccessTime(date);
		// tsRecord.setUseType(00);
		// tsRecord.setUseTime(date);
		tsRecord.setTsId(tsId);
		tsRecord.setTrNum(55);
		this.tsRecordService.addTsRecord(tsRecord);

		// 成长积分兑换记录
		TsRecord tsR = new TsRecord();
		tsR.setCusId(cusId);
		tsR.setTrType(TsRecord.TRTYPE_ACTION);
		tsR.setAccessType(TsRecord.ACCESSTYPE_FOR_REG);
		tsR.setAccessTime(date);
		// tsRecord.setUseType(00);
		// tsRecord.setUseTime(date);
		tsR.setTsId(tsId);
		tsR.setTrNum(55);
		this.tsRecordService.addTsRecord(tsR);

	}

	/**
	 * 初始化注册页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String initRegAndLogin() {
		try {
			if (backURL != null && !backURL.trim().equals("")) {
				// 将返回地址存入cookie
				CookieHandler.createOrUpdateCookie(getServletRequest(),
						getServletResponse(), "backURL", backURL, 1);
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.initRegAndLogin", e);
			return ERROR;
		}
		return "initRegAndLogin";
	}

	/**
	 * 检查邮箱是否可用
	 * 
	 * @return
	 */
	public String checkEmail() {
		try {
			String email = customer.getEmail();
			if (email != null) {
				if (customerService.checkEmail(email.trim())) {
					sendMessage("true");
					return null;
				}
			}
			sendMessage("false");
		} catch (Exception e) {
			logger.error("CustomerWebAction.checkEmail", e);
			return ERROR;
		}
		return null;
	}

	/**
	 * 检查当前用户密码是否正确
	 * 
	 * @return
	 */
	public String checkCustomer() {
		Integer byLogin = 0;
		try {
			if (customer != null) {
				if (!customer.getCusPwd().equals("")) {
					customer.setCusPwd(MD5.getMD5(customer.getCusPwd()));
				}
				byLogin = customerService.getUIDByLogin(customer);
			}

			if (byLogin != null) {
				sendMessage("true");
				return null;
			}
			sendMessage("false");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("CustomerWebAction.checkCustomer", e);
			return ERROR;
		}
		return null;
	}

	public String checkmoblie() {
		try {
			String mobileString = customer.getMobile();
			if (mobileString != null) {
				if (customerService.checkMoblie(mobileString.trim())) {
					sendMessage("true");
					return null;
				}
			}
			sendMessage("false");
		} catch (Exception e) {
			logger.error("验证手机号错误！", e);
			return ERROR;
		}
		return null;
	}

	/* 注册时用验证码 */
	public String checkRegRandomCode() {
		try {
			// 验证码
			if (randomCode == null
					|| !randomCode.equals(this.getSession("back_rand_code"))) {
				setResult(new Result<String>(false, "err.randCode", null, null));

			} else {
				setResult(new Result<String>(false, "success", null, null));
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.checkRegRandomCode", e);
		}
		return "json";
	}

	public String checkRandomCode() {
		try {
			if (randomCode == null
					|| !randomCode.equals(this.getSession("back_rand_code"))) {
				sendMessage("false");
				return null;
			}
			sendMessage("true");
		} catch (Exception e) {
			logger.error("CustomerWebAction.checkRandomCode", e);
			return ERROR;
		}
		return null;
	}

	/**
	 * 找回密码，发送新密码到邮箱
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String getPwdByEmail() {
		try {
			if (customer == null || customer.getEmail() == null
					|| customer.getEmail().trim().equals("")) {
				setResult(new Result<String>(false, "failEmail", null, null));
				return "json";
			} else {
				// 验证数据
				customer = customerService.getCustomerByEmail(customer
						.getEmail());
				if (customer == null) {
					setResult(new Result<String>(false, "forgotFailMail", null,
							null));
					return "json";
				}
				if (randomCode == null
						|| !randomCode
								.equals(this.getSession("back_rand_code"))) {
					setResult(new Result<String>(false, "err.randCode", null,
							null));
					return "json";
				}
				// 随即生成由数字组成的新密码
				String ranPwd = customerService.genericRandomPwd();
				// 修改用户密码
				customer.setCusPwd(MD5.getMD5(ranPwd.toString()));
				customerService.updateCustomer(customer);
				// 发送邮件通知用户，设置加密前的密码，以便发送
				customer.setCusPwd(ranPwd);
				customerService.sendForgotPwdEmail(customer);
				setResult(new Result<String>(false, "forgotSuccessMail", null,
						null));
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.getPwdByEmail", e);
			return ERROR;
		}
		return "json";
	}

	private List<Customer> customerlist;

	private String email2;

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getPwdByMobile() {
		try {
			if (customer == null || customer.getMobile() == null
					|| customer.getMobile().trim().equals("")) {
				setResult(new Result<String>(false, "failMobile", null, null));
				return "json";
			}
			if (randomCode == null
					|| !randomCode.equals(this.getSession("back_rand_code"))) {
				setResult(new Result<String>(false, "err.randCode", null, null));

			} else if (customer != null && customer.getMobile() != null
					&& randomCode.equals(this.getSession("back_rand_code"))
					&& email2 != null && (!"undefined".equals(email2))) {
				String mobileno = customer.getMobile();
				Pattern p = Pattern
						.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
				Matcher m = p.matcher(mobileno);
				if (m.matches() == true) {
					customer = customerService.getCustomerByEmail(email2);
					// 随即生成由数字组成的新密码
					String ranPwd = customerService.genericRandomPwd();
					// 修改用户密码
					customer.setCusPwd(MD5.getMD5(ranPwd.toString()));
					customerService.updateCustomer(customer);
					smsService.sendEx("您的帐号" + customer.getEmail() + "的新密码为："
							+ ranPwd, customer.getMobile(), "", "", "");
					setResult(new Result<String>(false, "forgotSuccessMobile",
							null, null));

				}
			} else {
				String mobileno = customer.getMobile();
				Pattern p = Pattern
						.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
				Matcher m = p.matcher(mobileno);
				if (m.matches() == true) {
					queryCustomerCondition = getQueryCustomerCondition();
					queryCustomerCondition.setMobile(mobileno);
					customerlist = customerService
							.getCustomerListByMobile(queryCustomerCondition);
					if (customerlist == null || customerlist.size() == 0) {
						setResult(new Result<String>(false, "forgotFailMobile",
								null, null));
						return "json";
					} else if (customerlist != null && customerlist.size() > 1) {

						List<String> emailList = new ArrayList<String>();
						for (int i = 0; i < customerlist.size(); i++) {
							Customer cus = customerlist.get(i);
							if (cus != null) {
								emailList.add(cus.getEmail());
							}
						}

						setResult(new Result<List<String>>(false,
								"forgotManyMobile", null, emailList));

						// setResult(new Result<String>(false,
						// "forgotManyMobile", null, null));
						return "json";

					} else if (customerlist.size() == 1) {
						customer = customerlist.get(0);
						// 随即生成由数字组成的新密码
						String ranPwd = customerService.genericRandomPwd();
						// 修改用户密码
						customer.setCusPwd(MD5.getMD5(ranPwd.toString()));
						customerService.updateCustomer(customer);

						smsService.sendEx("您的帐号" + customer.getEmail()
								+ "的新密码为：" + ranPwd, customer.getMobile(), "",
								"", "");
						setResult(new Result<String>(false,
								"forgotSuccessMobile", null, null));
					}
				} else {
					setResult(new Result<String>(false, "forgotMobile", null,
							null));
					return "json";
				}
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.getPwdByMobile", e);
			return ERROR;
		}
		return "json";

	}

	/**
	 * 发送短信到用户手机
	 * 
	 * @throws Exception
	 */
	public void sendSms(String content) throws Exception {
		try {
			// 发送短信到用户手机
			smsService.sendEx(content, customer.getMobile(), "", "", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除验证码
	 * 
	 * @return
	 */
	public String deleteConfirmationCode() {
		try {
			CookieHandler.deleteCookieByName(getServletRequest(),
					getServletResponse(), "confirmationCode");
			setResult(new Result());
		} catch (Exception e) {
			logger.error("CustomerWebAction.deleteConfirmationCode", e);
		}
		return "json";
	}

	/**
	 * 修改学员
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String updateCustomer() {
		try {
			int userId = getLoginUserId();

			// 验证输入数据的合法性
			if (checkSqlInj(customer)) {
				updateError = "请不要输入非法关键字。";
				return initCusInfo();
			}

			// 准备数据
			Customer cus = this.customerService.getCustomerById(userId);
			// 处理完善信息操作
			processComplete(cus, customer);

			// 准备修改数据
			// KeyWordFilter keyFilter = new KeyWordFilter() ;
			// String filterName = keyFilter.doFilter(customer.getCusName()) ;
			// if(!filterName.equals(cus.getCusName())){
			// updateError = "请不要输入敏感词汇。";
			// return initCusInfo() ;
			// }

			cus.setCusName(PreventInfusion.xssEncode(customer.getCusName()));
			cus.setMobile(customer.getMobile());
			cus.setPhoto(customer.getPhoto());
			cus.setQq(customer.getQq());
			cus.setSex(customer.getSex());
			cus.setAddress(PreventInfusion.xssEncode(customer.getAddress()));
			cus.setMSN(PreventInfusion.xssEncode(customer.getMSN()));
			cus.setBirthday(getBirthdayForCusUpdate());

			customerService.updateCustomer(cus);

			// 修改cookie中用户信息
			String ukey = CookieHandler.getCookieValueByName(
					getServletRequest(),
					LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);
			String newUkey = ukey.substring(0, ukey.lastIndexOf(",") + 1)
					+ URLEncoder.encode(customer.getCusName(), "UTF-8");
			CookieHandler.setCookieValueByName(getServletRequest(),
					LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY, newUkey);

			if (customer.getMobile() != null
					&& !customer.getMobile().trim().equals("")) {// 完善手机任务完成
				QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
				queryTaskCusCondition.setCusId(userId);
				queryTaskCusCondition.setKeyWord(Task.TASK_KEY_TELEPHONE);

				TaskCus tc = taskCusService
						.getTaskCusByKey(queryTaskCusCondition);

				if (tc != null && tc.getIsComplete() == 0) {// 若果未完成则设置完成
					tc.setIsComplete(1);
					taskCusService.updateTaskCus(tc);
				}
			}

			updateMessage = "修改成功。";

			// 返回信息：修改成功
			return initCusInfo();
		} catch (Exception e) {
			logger.error("CustomerWebAction.updateCustomer", e);
			return ERROR;
		}
	}

	/*
	 * 范昕--2011-08-03
	 * 
	 * 修改学员昵称
	 * 
	 * @return String
	 * 
	 * @thorows Exception
	 */
	public String updateCustomerName() {
		try {
			int userId = getLoginUserId();
			// 准备数据
			Customer cus = new Customer();
			cus.setCusId(userId);
			cus.setCusName(customer.getCusName());
			customerService.updateCustomerName(cus);

		} catch (Exception e) {
			logger.error("CustomerWebAction.updateCustomerName", e);
			setResult(new Result<String>(false, "failure", null, null));
		}
		setResult(new Result<String>(false, "success", null, null));

		return "json";
	}

	/**
	 * 修改个人资料准备生日 日期对象
	 * 
	 * @return
	 * @throws ParseException
	 */
	private Date getBirthdayForCusUpdate() throws ParseException {
		Date date = null;
		int year = getQueryCustomerCondition().getYear();
		int month = getQueryCustomerCondition().getMonth();
		int day = getQueryCustomerCondition().getDay();
		if (year > 0 && month > 0 && day > 0) {
			date = new SimpleDateFormat("yyyy-MM-dd")
					.parse(getQueryCustomerCondition().getYear() + "-"
							+ getQueryCustomerCondition().getMonth() + "-"
							+ getQueryCustomerCondition().getDay());
		}
		return date;
	}

	/**
	 * 验证数据合法性
	 * 
	 * @param cus
	 * @return
	 */
	private boolean checkSqlInj(Customer cus) {
		if (PreventInfusion.sql_inj(customer.getCusName())
				|| PreventInfusion.sql_inj(customer.getAddress())
				|| PreventInfusion.sql_inj(customer.getMSN())) {
			return true;
		}
		return false;
	}

	/**
	 * 处理是否完善信息
	 * 
	 * @param oldCus
	 * @param newCus
	 */
	private void processComplete(Customer oldCus, Customer newCus) {
		if (oldCus.getCompleteTime() != null) {
			return;
		}
		if (newCus.getMobile() == null) {
			return;
		}
		oldCus.setCompleteTime(new Date());
	}

	/**
	 * 上传照片
	 * 
	 * @return
	 */
	public String uploadPhoto() throws Exception {
		userInfoState = "updatePhoto";
		if (newPhotoFileName == null
				|| newPhotoFileName.get(0).indexOf(".") < 0
				|| !(newPhotoFileName.get(0).substring(
						newPhotoFileName.get(0).lastIndexOf("."))
						.equalsIgnoreCase(".jpg")
						|| newPhotoFileName.get(0).substring(
								newPhotoFileName.get(0).lastIndexOf("."))
								.equalsIgnoreCase(".png") || newPhotoFileName
						.get(0).substring(
								newPhotoFileName.get(0).lastIndexOf("."))
						.equalsIgnoreCase(".bmp"))) {
			updateError = "请上传一个图片。";
			setNewPhotoFileName(null);
			customer = customerService.getCustomerById(getLoginUserId());
			return "initCusInfo";
		}
		List<String> fileNameList = new ArrayList<String>();
		fileNameList.add(getLoginUserId()
				+ "-"
				+ System.currentTimeMillis()
				+ newPhotoFileName.get(0).substring(
						newPhotoFileName.get(0).lastIndexOf(".")));
		uploadForWeb(fileNameList, newPhoto);
		newPhotoFileName = fileNameList;
		customer = customerService.getCustomerById(getLoginUserId());
		return "initCusInfo";
	}

	/**
	 * 保存头像
	 * 
	 * @return
	 */
	public void updatePhoto() {
		userInfoState = "updatePhoto";
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ctx
				.get(ServletActionContext.HTTP_RESPONSE);
		try {
			String photoName = savePhoto(request);
			customer = customerService.getCustomerById(getLoginUserId());
			try {
				FileUtils
						.deleteFile(ServletActionContext.getServletContext()
								.getRealPath(
										"/upload/customer/photo/"
												+ customer.getPhoto()));
			} catch (Exception e) {
			}
			customer.setPhoto(photoName);
			customerService.updateCustomer(customer);
			String ukey = CookieHandler.getCookieValueByName(
					getServletRequest(),
					LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);
			String newUkey = ukey.substring(0, ukey.lastIndexOf(",") + 1)
					+ photoName;
			CookieHandler.createOrUpdateCookie(getServletRequest(),
					getServletResponse(),
					LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY, newUkey, 1);
			updateMessage = "修改成功。";
			Map<String, String> map = new HashMap<String, String>();
			map.put("src", photoName);
			JSONObject jsonObject = JSONObject.fromObject(map);

			PrintWriter out = response.getWriter();
			String callname = request.getParameter("callback");
			out.print(callname + "(" + jsonObject + ")");
			out.flush();
			out.close();
		} catch (Exception e) {
			updateError = "修改失败，请重新上传图片。";
			customer = customerService.getCustomerById(getLoginUserId());
			logger.error("CustomerWebAction.updatePhoto", e);
		}

	}

	private String savePhoto(HttpServletRequest request) throws IOException {
		String photoPath = request.getParameter("photoPath");

		int imageWidth = Integer.parseInt(request.getParameter("txt_width"));
		int imageHeight = Integer.parseInt(request.getParameter("txt_height"));
		int cutTop = Integer.parseInt(request.getParameter("txt_top"));
		int cutLeft = Integer.parseInt(request.getParameter("txt_left"));
		// 截取长宽，前台传过来的，暂时未用，后台长宽由ImageHelper静态属性所定
		// int dropWidth = Integer.parseInt(photoPrams.get("txt_DropWidth"));
		// int dropHeight = Integer.parseInt(photoPrams.get("txt_DropHeight"));

		Rectangle rec = createPhotoCutRec(imageWidth, imageHeight, cutLeft,
				cutTop);

		String photoName = getLoginUserId() + "-" + System.currentTimeMillis()
				+ photoPath.substring(photoPath.lastIndexOf("."));
		File file = new File(ServletActionContext.getServletContext()
				.getRealPath("/upload/customer/photo/" + photoName));
		File tempPic = new File(ServletActionContext.getServletContext()
				.getRealPath(photoPath));

		// 数字数据用int数组传入，长度为4，分别为，图片宽度，图片高度，截取位置高，截取位置左
		saveSubImage(tempPic, file, rec, new int[] { imageWidth, imageHeight,
				cutLeft, cutTop });

		tempPic.delete();
		return photoName;
	}

	/**
	 * 更新图片路径到数据库
	 * 
	 * @return
	 */
	public String HeadPhotoDB() {
		try {
			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ctx
					.get(ServletActionContext.HTTP_REQUEST);
			int userId = Integer.parseInt(request.getParameter("userId"));
			String photoName = request.getParameter("photoName");
			customer = customerService.getCustomerById(userId);
			customer.setPhoto(photoName);
			customerService.updateCustomer(customer);

			CookieHandler.createOrUpdateCookie(getServletRequest(),
					getServletResponse(), "myphoto", photoName, 1);

			return "";
		} catch (Exception e) {
			logger.error("CustomerWebAction.HeadPhotoDB", e);
			return "";
		}

	}

	private Rectangle createPhotoCutRec(int imageWidth, int imageHeight,
			int cutLeft, int cutTop) {
		int recX = cutLeft > 0 ? cutLeft : 0;
		int recY = cutTop > 0 ? cutTop : 0;
		int recWidth = ImageHelper.CUS_PHOTO_WIDTH;
		int recHieght = ImageHelper.CUS_PHOTO_HEIGHT;
		if (cutLeft < 0) {
			// 注意curLeft 是负数
			if (imageWidth - cutLeft > ImageHelper.CUS_PHOTO_WIDTH) {
				recWidth = ImageHelper.CUS_PHOTO_WIDTH + cutLeft;
			} else {
				recWidth = imageWidth;
			}
		} else {
			if (imageWidth - cutLeft < ImageHelper.CUS_PHOTO_WIDTH) {
				recWidth = imageWidth - cutLeft;
			}
		}

		if (cutTop < 0) {
			// 注意curLeft 是负数
			if (imageHeight - cutTop > ImageHelper.CUS_PHOTO_HEIGHT) {
				recHieght = ImageHelper.CUS_PHOTO_HEIGHT + cutTop;
			} else {
				recHieght = imageHeight;
			}
		} else {
			if (imageHeight - cutTop < ImageHelper.CUS_PHOTO_HEIGHT) {
				recHieght = imageHeight - cutTop;
			}
		}
		return new Rectangle(recX, recY, recWidth, recHieght);
	}

	private static void saveSubImage(File srcImageFile, File descDir,
			Rectangle rect, int[] intParms) throws IOException {
		ImageHelper.cut(srcImageFile, descDir, rect, intParms);
	}

	/**
	 * 初始化个人信息页面
	 * 
	 * @return
	 */
	public String initCusInfo() {
		try {
			int userId = getLoginUserId();
			customer = customerService.getCustomerById(userId);
		} catch (Exception e) {
			logger.error("CustomerWebAction.initCusInfo", e);
			return ERROR;
		}
		return "initCusInfo";
	}

	/**
	 * 初始化个人信息页面
	 * 
	 * @return
	 */
	public String initUpdatePwd() {
		return "initUpdatePwd";
	}

	/**
	 * 根据id获取学员
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String getCustomerByUID() {
		try {
			customer = customerService.getCustomerById(getLoginUserId());
			setResult(new Result<Customer>(true, "返回成功!", null, customer));
		} catch (Exception e) {
			logger.error("CustomerWebAction.getCustomerByUID", e);
			return ERROR;
		}
		return "json";
	}

	public String getRtById() {
		try {
			Date regTime = customerService
					.getCustomerRegTimeById(getLoginUserId());
			if (regTime != null) {
				String regTimeStr = DateUtil.formatDate(regTime,
						"yyyy-MM-dd-HH-mm-ss");
				logger.info("getRtById______\t" + regTimeStr);
				setResult(new Result<String>(true, "返回成功!", null, regTimeStr));
			} else {
				setResult(new Result<Customer>(false, "failed", null, null));
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.getRtById", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * 检查邮箱是否可以修改（暂时未用，暂定不能修改email）
	 * 
	 * @return
	 */
	public String checkEmailForUpdate() {
		try {
			String email = customer.getEmail();
			customer = customerService.getCustomerById(getLoginUserId());
			if (email != null) {

				// 如果email未改动，返回true
				if (customer.getEmail().equals(email.trim())) {
					sendMessage("true");
					return null;
				}
				// 如果此email未注册过，则返回true
				if (customerService.checkEmail(email.trim())) {
					sendMessage("true");
					return null;
				}
			}

			// 最后返回false
			sendMessage("false");
		} catch (Exception e) {
			logger.error("CustomerWebAction.checkEmailForUpdate", e);
			return ERROR;
		}
		return null;
	}

	/**
	 * 检查邮箱是否可用(修改个人信息验证)
	 * 
	 * @return
	 */
	private boolean checkEmailForUpdate(String email) {
		Customer cus = customerService.getCustomerById(getLoginUserId());
		if (cus.getEmail().equals(email)) {
			return true;
		}
		if (email != null) {
			if (customerService.checkEmail(email.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 修改密码
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String updatePwd() {
		try {
			int userId = getLoginUserId();
			customer.setCusId(userId);

			// 检查原密码
			customer.setCusPwd(MD5.getMD5(customer.getCusPwd()));
			userInfoState = "updatePwd";
			if (customerService.checkOldPwd(customer)) {
				customer = customerService.getCustomerById(customer.getCusId());
				customer.setCusPwd(MD5.getMD5(newPwd));
				customerService.updateCustomer(customer);

				updateMessage = "修改成功";
			} else {
				customer = customerService.getCustomerById(getLoginUserId());
				updateError = "当前密码不正确";
			}
			return "initCusInfo";
		} catch (Exception e) {
			logger.error("CustomerWebAction.updatePwd", e);
			return ERROR;
		}
	}

	/**
	 * 根据id获取学员
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String getCustomerById() {
		try {
			customer = customerService.getCustomerById(customer.getCusId());
			setResult(new Result<Customer>(true, "返回成功!", null, customer));
		} catch (Exception e) {
			logger.error("CustomerWebAction.getCustomerById", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * 根据id获取学员
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String getLoginedCustomer() {
		try {
			if (getLoginUserId() == 0) {
				setResult(new Result<Map<String, String>>(true, "false", null,
						null));
				return "json";
			}
			customer = customerService.getCustomerById(getLoginUserId());
			if (customer == null) {
				setResult(new Result<Map<String, String>>(true, "false", null,
						null));
				return "json";
			}
			String userName = "";
			if (customer.getCusName() != null
					&& !"".equals(customer.getCusName())) {
				userName = customer.getCusName();
			} else {
				userName = customer.getEmail();
			}

			List list = cusCouKpointService
					.getCusCouKpointListByCusId(getLoginUserId());

			// 返回出异常，故用map装载数据，未查出原因
			Map<String, String> map = new HashMap<String, String>();
			map.put("userName", userName);
			map.put("loginTimes", customer.getLoginTimes() + "");
			map.put("photo", customer.getPhoto());
			map.put("isComplete", customer.getIsComplete() + "");
			map.put("courseCount", (list == null ? 0 : list.size()) + "");
			setResult(new Result<Map<String, String>>(true, "success", null,
					map));
		} catch (Exception e) {
			logger.error("CustomerWebAction.getLoginedCustomer", e);
			return ERROR;
		}
		return "json";
	}

	public Map<String, Object> testStudyType(Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (data != null) {
			answers = new int[4];
			firstAnswer = Integer.parseInt(data.get("firstAnswer") + "");

			Object[] objs = (Object[]) data.get("answers");
			for (int i = 0; i < objs.length; i++) {
				answers[i] = (Integer) objs[i];
			}

			int max = 0;
			int count = 0;
			int index = -1;
			int totalCount = 0;
			int[] results = new int[4];

			for (int i = 0; i < answers.length; i++) {
				totalCount += answers[i];
				if (answers[i] > max) {
					max = answers[i];
					count = 1;
					index = i + 1;
				} else if (answers[i] == max) {
					count += 1;
				}
			}
			if (count > 1) {
				index = firstAnswer;
			}

			customer = customerService.getCustomerById(getLoginUserId());
			customer.setStudyType(index + "," + answers[0] + "," + answers[1]
					+ "," + answers[2] + "," + answers[3] + "," + totalCount);
			customerService.updateCustomer(customer);

			results[0] = answers[0] * 100 / totalCount;
			results[1] = answers[1] * 100 / totalCount;
			results[2] = answers[2] * 100 / totalCount;

			for (int i = 0; i < 3; i++) {
				results[i] = results[i] < 1 ? 1 : results[i];
				results[i] = results[i] > 97 ? 97 : results[i];
			}
			results[3] = 100 - results[0] - results[1] - results[2];

			map.put("studyType", index);
			map.put("results", results);
		}
		return map;
	}

	public String testStudyType() {
		try {
			int max = 0;
			int count = 0;
			int index = -1;
			int totalCount = 0;
			int[] results = new int[4];

			for (int i = 0; i < answers.length; i++) {
				totalCount += answers[i];
				if (answers[i] > max) {
					max = answers[i];
					count = 1;
					index = i + 1;
				} else if (answers[i] == max) {
					count += 1;
				}
			}
			if (count > 1) {
				index = firstAnswer;
			}

			customer = customerService.getCustomerById(getLoginUserId());
			customer.setStudyType(index + "," + answers[0] + "," + answers[1]
					+ "," + answers[2] + "," + answers[3] + "," + totalCount);
			customerService.updateCustomer(customer);

			results[0] = answers[0] * 100 / totalCount;
			results[1] = answers[1] * 100 / totalCount;
			results[2] = answers[2] * 100 / totalCount;

			for (int i = 0; i < 3; i++) {
				results[i] = results[i] < 1 ? 1 : results[i];
				results[i] = results[i] > 97 ? 97 : results[i];
			}

			results[3] = 100 - results[0] - results[1] - results[2];

			Map map = new HashMap();
			map.put("studyType", index);
			map.put("results", results);
			setResult(new Result(false, "success", null, map));
		} catch (Exception e) {
			logger.error("CustomerWebAction.getLoginedCustomer", e);
		}
		return "json";
	}

	/**
	 * 转到咨询中心
	 * 
	 * @return
	 */
	public String toInfCenter() {
		this.getHighSoProblem();
		return "toInfCenter";
	}

	/**
	 * 转到咨询中心
	 * 
	 * @return
	 */
	public String toInfList() {

		this.getHighSoProblem();
		return "toInfList";
	}

	@SuppressWarnings("deprecation")
	public void getHighSoProblem() {

		try {
			// 查出最新的十条热门问题
			int linSubjectId = 0;
			if (customer.getSubjectId() > 0) {
				linSubjectId = customer.getSubjectId();
			}
			customer = customerService.getCustomerById(getLoginUserId());
			if (linSubjectId > 0) {
				customer.setSubjectId(linSubjectId);
			}
			if (customer.getStudyType() != null
					&& customer.getStudyType() != "") {
				prepareStudyTypeParm(customer);
			}

			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			queryCourseCondition.setCusId(getLoginUserId());
			String subject = CookieHandler.getCookieValueByName(servletRequest,
					"subjectId");
			int subjectId = new Integer(subject);
			if (subjectId != 0) {
				this.getQueryProblemCondition().setSubjectId(subjectId);
			}
			plemList = this.problemService
					.getProblemByHost(getQueryProblemCondition());
			int userId = this.getLoginUserId();
			cId = userId;
			totolsScore = this.totolsScoreService.getTotolsScore(userId);

			queryCourseCondition.setCusId(getLoginUserId());

			userCourseList = courseService
					.getUserCenterCourseListByCusId(queryCourseCondition);

			for (int i = 0; i < plemList.size(); i++) {
				int str = plemList.get(i).getCreateTime().getSeconds(); // 处理热门问题发布时间秒数为0的问题
				if (str == 0) {
					plemList.get(i).getCreateTime().setSeconds(1); // 将时间秒数为0的时间
																	// 改成01秒
				}
			}

		} catch (Exception ex) {
			logger.error("CustomerWebAction.getHighSoProblem", ex);
		}
	}

	public String getLearnBuyInfos() {
		try {
			// minute改为subjectId用
			QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();
			queryCusCouKpointCondition.setSubjectId(minute);
			List<LearnBuyInfoDTO> learnBuyInfos = cusCouKpointService
					.getLearningInfoInTime(queryCusCouKpointCondition);
			setResult(new Result<List<LearnBuyInfoDTO>>(false, "success", null,
					learnBuyInfos));
		} catch (Exception e) {
			logger.error("CustomerWebAction.getLearnBuyInfos", e);
		}
		return "json";
	}

	/**
	 * 转到咨询中心
	 * 
	 * @return
	 */
	public String toStudyPlan() {
		try {
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			queryCourseCondition.setCusId(getLoginUserId());

			userCourseList = courseService
					.getUserCenterCourseListByCusId(queryCourseCondition);
		} catch (Exception e) {
			logger.error("CustomerWebAction.toStudyPlan", e);
			return ERROR;
		}
		return "toStudyPlan";
	}

	/**
	 * 转到咨询中心
	 * 
	 * @return
	 */
	public String toDiscuss() {
		try {
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			queryCourseCondition.setCusId(getLoginUserId());

			userCourseList = courseService
					.getUserCenterCourseListByCusId(queryCourseCondition);
		} catch (Exception e) {
			logger.error("CustomerWebAction.toDiscuss", e);
			return ERROR;
		}
		return "toDiscuss";
	}

	/**
	 * 转到咨询中心
	 * 
	 * @return
	 */
	public String toComment() {
		try {
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			queryCourseCondition.setCusId(getLoginUserId());

			userCourseList = courseService
					.getUserCenterCourseListByCusId(queryCourseCondition);
		} catch (Exception e) {
			logger.error("CustomerWebAction.toComment", e);
			return ERROR;
		}
		return "toComment";
	}

	/**
	 * 转到评价中心
	 * 
	 * @return
	 */
	public String toAssess() {
		return "toAssess";
	}

	/**
	 * 转向会计证专题
	 * 
	 * @return
	 */
	public String toKjz() {
		return "toKjz";
	}

	/**
	 * 转向建造师专题
	 */
	public String toJz() {
		return "toJz";
	}

	/**
	 * 转向高级会计师专题
	 */
	public String toGk() {
		return "toGk";
	}

	/**
	 * 转向公务员专题
	 */
	public String toGwy() {
		return "toGwy";
	}

	/**
	 * 转向人力专题
	 * 
	 * @return
	 */
	public String toRl() {
		return "toRl";
	}

	/**
	 * 转向司法专题
	 * 
	 * @return
	 */
	public String toSf() {
		return "toSf";
	}

	/**
	 * 转向旧版cpa专题
	 * 
	 * @return
	 */
	public String toCpa() {
		return "toCpa";
	}

	/**
	 * 转向新版cpa1专题
	 * 
	 * @return
	 */
	public String toCpa1() {
		return "toCpa1";
	}

	/**
	 * 转向新版cpa2专题
	 * 
	 * @return
	 */
	public String toCpa2() {
		return "toCpa2";
	}

	/**
	 * 转向新版sf2专题
	 * 
	 * @return
	 */
	public String toSf2() {
		return "toSf2";
	}

	/**
	 * 转向证券专题
	 * 
	 * @return
	 */
	public String toZq() {
		return "toZq";
	}

	/**
	 * 转向心理咨询师专题
	 * 
	 * @return
	 */
	public String toXL() {
		return "toXL";
	}

	public String toPayOk() {
		try {
			servletRequest.setAttribute("zfbReturnMessage", "success");
			processTempContent();
		} catch (Exception e) {
			logger.error("CustomerWebAction.toPayOk", e);
			return ERROR;
		}
		return "toPayOk";
	}

	/**
	 * 更新学员的所在项目
	 * 
	 * @return
	 */
	public String updateCustomerSubject() {
		try {
			int cusId = getLoginUserId();
			if (cusId != 0 && customer.getSubjectId() > 0) {
				customer.setCusId(cusId);
				boolean f = customerService.updateCustomerSubject(customer);
				if (f) {
					CookieHandler.deleteCookieByName(getServletRequest(), this
							.getServletResponse(), "confirmationCode");
					setResult(new Result<String>(false, "success", null, null));
				} else {
					setResult(new Result<String>(false, "false", null, null));
				}
			} else {
				setResult(new Result<String>(false, "false", null, null));
			}
		} catch (Exception e) {
			setResult(new Result<String>(false, "false", null, null));
		}
		return "json";
	};

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
			logger.error("CustomerWebAction.processTempContent", e);
		}
	}

	// 选择标签
	public String SelectBQ() {
		try {
			setUserInfoState("");
			int userId = getLoginUserId();
			if (userId == 0) {
				userId = customer.getCusId();
			}
			customer = customerService.getCustomerById(userId);
			if (customer.getPhoto() == null) {
				CookieHandler.createOrUpdateCookie(getServletRequest(),
						getServletResponse(), "myphoto", "", 1);
			} else {
				CookieHandler
						.createOrUpdateCookie(getServletRequest(),
								getServletResponse(), "myphoto", customer
										.getPhoto(), 1);
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.SelectBQ", e);
		}
		return "initCusInfo";
	}

	// 获取学员服务人员联系方式及姓名
	public String getCustomerServiceMsg() {
		try {
			// 获取用户id
			int cusId = this.getLoginUserId();
			// 根据登陆用户的id，获取该项目的服务人员信息
			subject = this.customerService.getSubjectByCusId(cusId);
			setResult(new Result<Subject>(false, "success", null, subject));
		} catch (Exception e) {
			logger.error("SubjectAction.getcustomerService", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * <br>
	 * <b>功能：获取积分</b><br>
	 * <b>作者：</b>李志强 Kobe.Lee<br>
	 * <b>日期：</b> 2012.06.27 <br>
	 */
	public String getScore() {
		try {
			// 获取用户id
			int cusId = this.getLoginUserId();
			// 根据登陆用户的id获取当前用户的积分
			TotolsScore totolsScore = totolsScoreService.getTotolsScore(cusId);
			int score = 0;
			if (null != totolsScore)
				score = totolsScore.getTsCurrent();
			// 判断当天是否已经打卡过。
			String scoreAndFlag;
			if (null != memcachedClient.get(cusId + "SignInFlag")) {
				String date = memcachedClient.get(cusId + "SignInFlag")
						.toString();
				if (date.equals(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()))) {
					scoreAndFlag = score + ",1";
				} else {
					scoreAndFlag = score + ",0";
				}
			} else {
				scoreAndFlag = score + ",0";
			}
			setResult(new Result<String>(false, "success", null, scoreAndFlag));
		} catch (Exception e) {
			logger.error("SubjectAction.getScore", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * <br>
	 * <b>功能：签到积分</b><br>
	 * <b>作者：</b>李志强 Kobe.Lee<br>
	 * <b>日期：</b> 2012.06.27 <br>
	 */
	public String signin() {
		try {
			// 获取用户id
			int cusId = this.getLoginUserId();
			// 判断用户当天是否已经送过积分
			if (!totolsScoreService.checkScore(cusId)) {
				// 赠送积分
				TotolsScore ts = totolsScoreService.addScore(cusId, 5,
						TsRecord.ACCESSTYPE_FOR_SIGNIN);
				// 将打卡信息放入memcached
				memcachedClient.set(cusId + "SignInFlag", new SimpleDateFormat(
						"yyyy-MM-dd").format(new Date()), new Date(System
						.currentTimeMillis() + 86400000));
				setResult(new Result<Integer>(false, "first", null, ts
						.getTsCurrent()));
			} else {
				setResult(new Result<String>(false, "notfirst", null,
						"当天已经赠送过积分"));
			}
		} catch (Exception e) {
			logger.error("CustomerWebAction.signin", e);
			return ERROR;
		}
		return "json";
	}

	private String vId;

	public String getVId() {
		return vId;
	}

	public void setVId(String id) {
		vId = id;
	}

	/**
	 * 功能：将一个Map按照Key字母升序构成一个QueryString. 并且加入时间混淆的hash串。
	 * 
	 * @param queryMap
	 *            query内容
	 * @param time
	 *            加密时候，为当前时间；解密时，为从querystring得到的时间；
	 * @param salt
	 *            加密salt
	 * @return
	 */
	private String createHashedQueryString(Map<String, String> queryMap,
			long time, String salt) {

		Map<String, String> map = new TreeMap<String, String>(queryMap);
		String qs = createQueryString(map);
		time = time / 1000;
		String hash = MD5.getMD5(String.format("%s&time=%d&salt=%s", qs, time,
				salt));
		String htqs = String.format("%s&time=%d&hash=%s", qs, time, hash);
		return htqs;
	}

	/**
	 * 功能：用一个Map生成一个QueryString，参数的顺序不可预知。
	 * 
	 * @param queryString
	 * @return
	 */
	private String createQueryString(Map<String, String> queryMap) {

		if (queryMap == null) {
			return null;
		}

		try {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : queryMap.entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				String key = entry.getKey().trim();
				String value = URLEncoder.encode(entry.getValue().trim(),
						"utf-8");
				sb.append(String.format("%s=%s&", key, value));
			}
			return sb.substring(0, sb.length() - 1);
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void sendMessage(String message) throws IOException {
		this.getServletResponse().setCharacterEncoding("utf-8");
		this.getServletResponse().getWriter().write(message);
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

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public void setMailService(IMail mailService) {
		this.mailService = mailService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public String getBackURL() {
		return backURL;
	}

	public void setBackURL(String backURL) {
		this.backURL = backURL;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public boolean isRegister() {
		return isRegister;
	}

	public void setRegister(boolean isRegister) {
		this.isRegister = isRegister;
	}

	public void setCouponService(ICoupon couponService) {
		this.couponService = couponService;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public void setCpCusService(ICpCus cpCusService) {
		this.cpCusService = cpCusService;
	}

	public boolean isEmailInUse() {
		return emailInUse;
	}

	public void setEmailInUse(boolean emailInUse) {
		this.emailInUse = emailInUse;
	}

	public boolean isUpdateSuccess() {
		return updateSuccess;
	}

	public void setUpdateSuccess(boolean updateSuccess) {
		this.updateSuccess = updateSuccess;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public void setUncouponService(IUncoupon uncouponService) {
		this.uncouponService = uncouponService;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public String getInitMessage() {
		return initMessage;
	}

	public void setInitMessage(String initMessage) {
		this.initMessage = initMessage;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public void setColumnsService(IColumns columnsService) {
		this.columnsService = columnsService;
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

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	public ICoupon getCouponService() {
		return couponService;
	}

	public IColumns getColumnsService() {
		return columnsService;
	}

	public ICpCus getCpCusService() {
		return cpCusService;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public IMail getMailService() {
		return mailService;
	}

	public IsmsService getSmsService() {
		return smsService;
	}

	public IUncoupon getUncouponService() {
		return uncouponService;
	}

	public TotolsScore getTotolsScore() {
		return totolsScore;
	}

	public void setTotolsScore(TotolsScore totolsScore) {
		this.totolsScore = totolsScore;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public int getContractCount() {
		return contractCount;
	}

	public void setContractCount(int contractCount) {
		this.contractCount = contractCount;
	}

	public ILoginRecord getLoginRecordService() {
		return loginRecordService;
	}

	public void setLoginRecordService(ILoginRecord loginRecordService) {
		this.loginRecordService = loginRecordService;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public QueryColumnsCondition getQueryColumnsCondition() {
		return queryColumnsCondition;
	}

	public void setQueryColumnsCondition(
			QueryColumnsCondition queryColumnsCondition) {
		this.queryColumnsCondition = queryColumnsCondition;
	}

	public String getUserInfoState() {
		return userInfoState;
	}

	public void setUserInfoState(String userInfoState) {
		this.userInfoState = userInfoState;
	}

	public String getUpdateMessage() {
		return updateMessage;
	}

	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public List<UserCenterCourseDTO> getUserCourseList() {
		return userCourseList;
	}

	public void setUserCourseList(List<UserCenterCourseDTO> userCourseList) {
		this.userCourseList = userCourseList;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public List<File> getNewPhoto() {
		return newPhoto;
	}

	public void setNewPhoto(List<File> newPhoto) {
		this.newPhoto = newPhoto;
	}

	public List<String> getNewPhotoFileName() {
		return newPhotoFileName;
	}

	public void setNewPhotoFileName(List<String> newPhotoFileName) {
		this.newPhotoFileName = newPhotoFileName;
	}

	public Map<String, String> getPhotoPrams() {
		return photoPrams;
	}

	public void setPhotoPrams(Map<String, String> photoPrams) {
		this.photoPrams = photoPrams;
	}

	public TaskCus getTaskCus() {
		return taskCus;
	}

	public void setTaskCus(TaskCus taskCus) {
		this.taskCus = taskCus;
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

	public ITask getTaskService() {
		return taskService;
	}

	public void setTaskService(ITask taskService) {
		this.taskService = taskService;
	}

	public UserMsg getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(UserMsg userMsg) {
		this.userMsg = userMsg;
	}

	public String getUpdateError() {
		return updateError;
	}

	public void setUpdateError(String updateError) {
		this.updateError = updateError;
	}

	public List<Problem> getHPbList() {
		return hPbList;
	}

	public void setHPbList(List<Problem> pbList) {
		hPbList = pbList;
	}

	public List<Problem> getNPbList() {
		return nPbList;
	}

	public void setNPbList(List<Problem> pbList) {
		nPbList = pbList;
	}

	public List<Problem> getSPbList() {
		return sPbList;
	}

	public void setSPbList(List<Problem> pbList) {
		sPbList = pbList;
	}

	public IProblem getProblemService() {
		return problemService;
	}

	public void setProblemService(IProblem problemService) {
		this.problemService = problemService;
	}

	public QueryProblemCondition getQueryProblemCondition() {
		if (queryProblemCondition == null) {
			queryProblemCondition = new QueryProblemCondition();
		}

		return queryProblemCondition;
	}

	public void setQueryProblemCondition(
			QueryProblemCondition queryProblemCondition) {
		this.queryProblemCondition = queryProblemCondition;
	}

	public int[] getAnswers() {
		return answers;
	}

	public void setAnswers(int[] answers) {
		this.answers = answers;
	}

	public int getFirstAnswer() {
		return firstAnswer;
	}

	public void setFirstAnswer(int firstAnswer) {
		this.firstAnswer = firstAnswer;
	}

	public int getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Map getStudyTypePram() {
		return studyTypePram;
	}

	public void setStudyTypePram(Map studyTypePram) {
		this.studyTypePram = studyTypePram;
	}

	public List<Problem> getPlemList() {
		return plemList;
	}

	public void setPlemList(List<Problem> plemList) {
		this.plemList = plemList;
	}

	public int getCId() {
		return cId;
	}

	public void setCId(int id) {
		cId = id;
	}

	public List<UserCenterSubjectCourseDTO> getSubjectCourseList() {
		if (subjectCourseList == null) {
			subjectCourseList = new ArrayList<UserCenterSubjectCourseDTO>();
		}
		return subjectCourseList;
	}

	public void setSubjectCourseList(
			List<UserCenterSubjectCourseDTO> subjectCourseList) {
		this.subjectCourseList = subjectCourseList;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public IExampaperRecord getExampaperRecordService() {
		return exampaperRecordService;
	}

	public void setExampaperRecordService(
			IExampaperRecord exampaperRecordService) {
		this.exampaperRecordService = exampaperRecordService;
	}

	public ExampaperRecord getExampaperRecord() {
		return exampaperRecord;
	}

	public void setExampaperRecord(ExampaperRecord exampaperRecord) {
		this.exampaperRecord = exampaperRecord;
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

	public String getTempCusRandomCode() {
		return tempCusRandomCode;
	}

	public void setTempCusRandomCode(String tempCusRandomCode) {
		this.tempCusRandomCode = tempCusRandomCode;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	public List<SellWay> getButSellWayList() {
		return butSellWayList;
	}

	public void setButSellWayList(List<SellWay> butSellWayList) {
		this.butSellWayList = butSellWayList;
	}

	public List<UserCenterSubjectCourseDTO> getSellWayCourseList() {
		if (sellWayCourseList == null) {
			sellWayCourseList = new ArrayList<UserCenterSubjectCourseDTO>();
		}
		return sellWayCourseList;
	}

	public void setSellWayCourseList(
			List<UserCenterSubjectCourseDTO> sellWayCourseList) {
		this.sellWayCourseList = sellWayCourseList;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public ISellCou getSellCouService() {
		return sellCouService;
	}

	public void setSellCouService(ISellCou sellCouService) {
		this.sellCouService = sellCouService;
	}

	public IGmrecord getGmrecordService() {
		return gmrecordService;
	}

	public void setGmrecordService(IGmrecord gmrecordService) {
		this.gmrecordService = gmrecordService;
	}

	public boolean isIshavebuy() {
		return ishavebuy;
	}

	public void setIshavebuy(boolean ishavebuy) {
		this.ishavebuy = ishavebuy;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	private int isshitingdown = 0;

	public int getIsshitingdown() {
		return isshitingdown;
	}

	public void setIsshitingdown(int isshitingdown) {
		this.isshitingdown = isshitingdown;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public List<Customer> getCustomerlist() {
		return customerlist;
	}

	public void setCustomerlist(List<Customer> customerlist) {
		this.customerlist = customerlist;
	}

	public IAnnouncement getAcementService() {
		return acementService;
	}

	public void setAcementService(IAnnouncement acementService) {
		this.acementService = acementService;
	}

	public List<Announcement> getAcmList() {
		return acmList;
	}

	public void setAcmList(List<Announcement> acmList) {
		this.acmList = acmList;
	}

	public String getSubjectIds() {
		return subjectIds;
	}

	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}

	/**
	 * @return the sellWayCourseListMore
	 */
	public List<UserCenterSubjectCourseDTO> getSellWayCourseListMore() {
		if (sellWayCourseListMore == null) {
			sellWayCourseListMore = new ArrayList<UserCenterSubjectCourseDTO>();
		}
		return sellWayCourseListMore;
	}

	/**
	 * @param sellWayCourseListMore
	 *            the sellWayCourseListMore to set
	 */
	public void setSellWayCourseListMore(
			List<UserCenterSubjectCourseDTO> sellWayCourseListMore) {
		this.sellWayCourseListMore = sellWayCourseListMore;
	}

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public MemCachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemCachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public QueryCommentCondition getQueryCommentCondition() {
		if (queryCommentCondition == null) {
			queryCommentCondition = new QueryCommentCondition();
		}
		return queryCommentCondition;
	}

	public void setQueryCommentCondition(
			QueryCommentCondition queryCommentCondition) {
		this.queryCommentCondition = queryCommentCondition;
	}

	/**
	 * 
	 *反馈信息
	 */
	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public IComment getCommentService() {
		return commentService;
	}

	public void setCommentService(IComment commentService) {
		this.commentService = commentService;
	}

	public IExampaper getExampaperService() {
		return exampaperService;
	}

	public void setExampaperService(IExampaper exampaperService) {
		this.exampaperService = exampaperService;
	}

	public CusBuyInfo getBuyInfo() {
		return buyInfo;
	}

	public void setBuyInfo(CusBuyInfo buyInfo) {
		this.buyInfo = buyInfo;
	}

	public CusNotBuyInfo getNotBuyInfo() {
		return notBuyInfo;
	}

	public void setNotBuyInfo(CusNotBuyInfo notBuyInfo) {
		this.notBuyInfo = notBuyInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public IActionRecord getActionRecordService() {
		return actionRecordService;
	}

	public void setActionRecordService(IActionRecord actionRecordService) {
		this.actionRecordService = actionRecordService;
	}

	public List<ActionRecord> getActionRecords() {
		return actionRecords;
	}

	public void setActionRecords(List<ActionRecord> actionRecords) {
		this.actionRecords = actionRecords;
	}

	public QueryActionRecordCondition getQueryActionRecordCondition() {
		if (queryActionRecordCondition == null) {
			queryActionRecordCondition = new QueryActionRecordCondition();
		}
		return queryActionRecordCondition;
	}

	public void setQueryActionRecordCondition(
			QueryActionRecordCondition queryActionRecordCondition) {
		this.queryActionRecordCondition = queryActionRecordCondition;
	}

	public List getFreshlist() {
		return freshlist;
	}

	public void setFreshlist(List freshlist) {
		this.freshlist = freshlist;
	}

	public List<ActionRecord> getMyfreshlist() {
		return myfreshlist;
	}

	public void setMyfreshlist(List<ActionRecord> myfreshlist) {
		this.myfreshlist = myfreshlist;
	}

	public String getSellIds() {
		return sellIds;
	}

	public void setSellIds(String sellIds) {
		this.sellIds = sellIds;
	}

	public IVideoStatistics getVideoStatisticsService() {
		return videoStatisticsService;
	}

	public void setVideoStatisticsService(IVideoStatistics videoStatisticsService) {
		this.videoStatisticsService = videoStatisticsService;
	}

	public List<UserKpointDTO> getKpoints() {
		return kpoints;
	}

	public void setKpoints(List<UserKpointDTO> kpoints) {
		this.kpoints = kpoints;
	}

	public int getMaxnumber() {
		return maxnumber;
	}

	public void setMaxnumber(int maxnumber) {
		this.maxnumber = maxnumber;
	}
}
