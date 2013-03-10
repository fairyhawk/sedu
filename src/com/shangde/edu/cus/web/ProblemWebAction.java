package com.shangde.edu.cus.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.Result;
import com.shangde.common.util.messageRemind.MessageRemindBean;
import com.shangde.common.util.messageRemind.MessageRemindUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.domain.Comment;
import com.shangde.edu.cms.service.CommentImpl;
import com.shangde.edu.cms.service.IComment;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryReProblemCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.dto.OfficialCustomerDTO;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.IOfficialCustomer;
import com.shangde.edu.cus.service.IProblem;
import com.shangde.edu.cus.service.IReProblem;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.dis.service.IDisWord;
import com.shangde.edu.dis.web.interceptor.KeyWordFilter;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.res.domain.VedioCount;
import com.shangde.edu.res.service.IVedioCount;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.IUser;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.service.ITaskCus;

/**
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class ProblemWebAction extends CommonAction {

	private static final Logger logger = Logger
			.getLogger(ProblemWebAction.class);

	private IOfficialCustomer officialCustomer;

	private List<OfficialCustomerDTO> officialCustomerDTOList;

	private boolean checkOfficialCustomer;

	/**
	 * 问题实体对象
	 */
	private Problem problem = new Problem();
	/**
	 * 问题的服务
	 */
	private IProblem problemService;
	/**
	 * 专业服务
	 */
	private ISubject subjectService;
	/**
	 * 查询条件
	 */
	private QueryProblemCondition queryProblemCondition;
	/**
	 * 课程实体
	 */
	private Course course = new Course();
	/**
	 * 课程集合
	 */
	private List<Course> courseList;
	/**
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;
	/**
	 * 问题list
	 */
	private List<Problem> problemList = new ArrayList<Problem>();
	/**
	 * 回复list
	 */
	private List<ReProblem> reProblemList;

	/**
	 * 回复问题实体
	 */
	private ReProblem reProblem = new ReProblem();
	/**
	 * 回复问题的服务
	 */
	private IReProblem reProblemService;
	private QueryReProblemCondition queryReProblemCondition;
	/**
	 * 学员信息
	 */
	private QueryCustomerCondition queryCustomerCondition;
	private Customer customer;
	/**
	 * 后台用户
	 */
	private User user;
	private IUser userService;
	private List<Problem> plemList = new ArrayList<Problem>();
	private ICustomer customerService;
	private int pblId;
	/**
	 * 课程分类服务Service
	 */
	private ICoursesort coursesortService;

	private List<CourseSortListDTO> courseSortListDTOList;
	/**
	 * 验证码
	 */
	private String randomCode;
	/**
	 * 积分的服务
	 */
	private ITotolsScore totolsScoreService;
	/**
	 * 积分的实体对象
	 */
	private TotolsScore totolsScore;
	// 敏感词service
	private IDisWord disWordService;
	
	private int proTypeOneCount=0;//存放各个类型的问题数量
	private int proTypeTwoCount=0;//存放各个类型的问题数量
	private int proTypeThrCount=0;//存放各个类型的问题数量
	private int proTypeFouCount=0;//存放各个类型的问题数量

	/**
	 * 变量
	 */
	private int score = 0;
	private String content = "";
	private int reId;
	private String pro_content;
	private int subject_id;
	private int problem_type;
	/**
	 * tab切换参数
	 */
	private int highProblem;
	/**
	 * 采纳为答案的list
	 */
	private List<ReProblem> takeResultList = new ArrayList<ReProblem>();
	/**
	 * 没有采纳为答案的list
	 */
	private List<ReProblem> resultList = new ArrayList<ReProblem>();

	/**
	 * 已购买的专业list
	 */
	List<Subject> subList = new ArrayList<Subject>();

	private int cusId;

	/**
	 * 用户任务服务
	 */
	private ITaskCus taskCusService;
	/**
	 * 切换参数
	 */
	private int location = 1;

	private int sw;

	private String sta;

	private IMessage messageService;// 消息服务

	private IUserMsg userMsgService;// 用户信息服务

	private String addError;
	/**
	 * 回复的参数判断用
	 */
	private String manw = "";
	/**
	 * 积分记录的服务
	 */
	private ITsRecord tsRecordService;
	private int cId;
	private int strBug = 0;
	private int strRes = 0;

	// private List<UserCenterCourseDTO> userCourseList;// 用户课程集合

	// private ICourse courseService;

	// 以下為處理管理員回復的內容對象
	private IComment commentService = new CommentImpl();

	private Comment comment = new Comment();

	// private Comment reComment=new Comment();

	private List<Comment> cList = new ArrayList<Comment>();

	// 谢添加开始
	private IVedioCount vediocountService;

	private int videoCountStatus;// 播放器功能使用统计类型状态

	/**
	 * 我要提问播放器功能统计
	 */
	public void vedioCount() {
		try {
			Integer userId = this.getLoginUserId();
			VedioCount vedioCount = new VedioCount();
			vedioCount.setCountTime(new Date());
			vedioCount.setCusId(userId != null ? userId : 0);
			vedioCount.setStatus(videoCountStatus);
			vediocountService.addCount(vedioCount);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	// 谢添加结束

	/**
	 * highso问答首页面
	 * 
	 * @return
	 */
	public String getHighSoProblem() {

		try {
			// 查出最新的十条热门问题
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

			// 查询已购买的专业
			subList = subjectService.getSubjectListForUnAss(userId);
			officialCustomerDTOList = officialCustomer
					.queryOfficialCustomerDTOBySubjectIds(subList);
			checkOfficialCustomer = officialCustomer
					.checkOfficialCustomer(userId);
			vedioCount();// 统计播放器功能使用次数
		} catch (Exception ex) {
			logger.error("ProblemWebAction.getHighSoProblem", ex);
			return ERROR;
		}
		return "wenda";

	}

	/**
	 * 回复问题前查询问题状态
	 * 
	 * @return
	 */
	public String addProblemallback() {
		try {
			if (this.pblId != 0) {

				Problem pbl = this.problemService.getProblemById(pblId);
				if (pbl != null) {
					JSONArray jsPbl = JSONArray.fromObject(pbl);
					this.setResult(new Result<Object>(true, jsPbl.toString(),
							null, null));
				}
			}
		} catch (RuntimeException e) {
			logger.error("ProblemWebAction.addProblemallback", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * 得我提出的建議及管理員的回復
	 */
	public String getMyAdvice() {
		try {
			// 得到用戶發出的意見
			comment = commentService.getCommentById(comment.getCmtId());
			// 得到管理員回復的內容
			HttpServletRequest request = ServletActionContext.getRequest();
			int rid = Integer.parseInt(request.getParameter("rid"));
			Map<String, Integer> param = new HashMap<String, Integer>();
			param.put("id", comment.getCmtId());
			param.put("rid", rid);
			cList = commentService.getReplyById(param);
		} catch (Exception e) {
			logger.error("ProblemWebAction.getMyAdvice", e);
			return ERROR;
		}
		return "myAdvice";
	}

	/**
	 * 删除建议
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String deleteAdvice() {
		try {
			// 先删除建议的回复，再删除建议
			commentService.delAdviceReply(comment.getCmtId());
			commentService.delCommentById(comment.getCmtId());

			// 刪除提示消息
			MessageRemindUtil.delMessageRemindForTopic(1, comment.getCmtId());
		} catch (Exception e) {
			logger.error("ProblemWebAction.deleteAdvice", e);
			return ERROR;
		}
		return "reshowAdviceMsgList";
	}

	/**
	 * 大家的问题
	 * 
	 * @return
	 */
	public String getEveryOneProblem() {

		try {
			int userId = this.getLoginUserId();
			// 查询当前登录用户购买的项目
			List<Subject> subjectList = subjectService
					.getSubjectListForUnAss(userId);
			StringBuffer buf = new StringBuffer();
			if (subjectList != null && subjectList.size() > 0) {
				for (int i = 0; i < subjectList.size(); i++) {
					if (i == (subjectList.size() - 1)) {
						buf.append(subjectList.get(i).getSubjectId());
					} else {
						buf.append(subjectList.get(i).getSubjectId() + ",");
					}
				}
			}
			this.getQueryProblemCondition().setSubjectIds(buf.toString());
			if (course != null && course.getCourseId() != 0) {
				this.getQueryProblemCondition().setCourseId(
						course.getCourseId());
			}
			this.getQueryProblemCondition().setCusId(userId);
			// 根据分类来查询
			if (problem.getPblType() != 0) {
				this.getQueryProblemCondition()
						.setPblType(problem.getPblType());
			}
			// 查询高分问题
			if (highProblem != 0) {
				this.getQueryProblemCondition().setHighProblem(highProblem);
			}
			// 查询已解决和待解决
			if (problem.getPblSolv() != 0) {
				this.getQueryProblemCondition()
						.setPblSolv(problem.getPblSolv());
			}
			this.getQueryProblemCondition().setPageSize(20);
			setPage(this.problemService
					.getPageProblemListById(getQueryProblemCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
		} catch (Exception ex) {
			logger.error("ProblemWebAction.getEveryOneProblem", ex);
			return ERROR;
		}
		return "wenda_jihe";

	}

	/**
	 * 
	 * @Title: getEveryOneContent 
	 * @Description: TODO(新版highso，大家问题的详细) 
	 * @param @return    设定文件 
	 * @return String    返回类型
	 * @author shixiaofeng@sunland.org.cn 
	 * @throws
	 */
	public String getEveryOneContent() {
		try {
			// 根据问题id查出这个问题的全部信息
			int userId = this.getLoginUserId();
			problem = this.problemService.getProblemById(problem.getPblId());
			List<ReProblem> repList = problem.getReProblemList();
			List<ReProblem> newrepList = new ArrayList<ReProblem>();
			for (int i = 0; i < repList.size(); i++) {
				if (repList.get(i).getReManId() != userId
						&& repList.get(i).getReStatus() == 1) {
				} else {
					newrepList.add(repList.get(i));
				}
			}
			problem.setReProblemList(newrepList);
			// 根据问题id查出这个问题下面的全部回答的信息
		} catch (Exception ex) {
			logger.error("ProblemWebAction.getEveryOneContent", ex);
			return ERROR;
		}
		return "wenda_detail";
	}

	/**
	 * 大家的的问题回复
	 * 
	 * @return
	 */

	public String problemContent() {
		String result = "";
		int reid = 0;
		try {
			Date date = new Date();
			// 通过cookie获得userId
			int userId = this.getLoginUserId();
			if (userId > 0 && problem != null) {
				problem = problemService.getProblemById(problem.getPblId());
				if (problem != null) {
					if (reProblem != null) {
						reProblem.setPblId(problem.getPblId());
						// 过滤问题回答中的敏感词
						KeyWordFilter kw = KeyWordFilter
								.getInstance(disWordService);
						reProblem.setReInfo(kw.doFilter(reProblem.getReInfo()));
						reProblem.setReTime(date);
						reProblem.setReManId(userId);
						reProblem.setReManType(ReProblem.REPROBLEM_COSTOMER);
						reid = this.reProblemService.addReProblem(reProblem);
					}

					// 回答问题任务
					QueryReProblemCondition queryReProblemCondition = new QueryReProblemCondition();
					queryReProblemCondition.setReManId(userId);
					int reProblemSize = reProblemService
							.getCountByCusId(queryReProblemCondition);

					// 给提问者发消息
					Customer cusSender = customerService
							.getCustomerById(userId);
					Customer cus = customerService.getCustomerById(problem
							.getCusId());
					if (cusSender != null && cus != null) {

						User sender = new User();
						sender.setUserId(1);
						sender.setUserName("超级管理员");
						MessageRemindBean mrb = new MessageRemindBean();
						mrb.setReceiver(cus);
						mrb.setRid(reid);
						mrb.setSender(sender);
						mrb.setText(problem.getPblTitle());
						mrb.setTid(problem.getPblId());
						mrb.setType(2);
						mrb
								.setUrl("有人解答了您的问题[<a href=\"../cus/pblimit!getMyProblemContent.action?problem.pblId="
										+ problem.getPblId() + "\">");
						MessageRemindUtil.sendMessageRemind(mrb);
					}
					// 给提问者发消息

					if (reProblemSize >= 1 && reProblemSize < 5) {
						completeTask(Task.TASK_KEY_ANSWER_1);
					} else if (reProblemSize >= 5 && reProblemSize < 10) {
						completeTask(Task.TASK_KEY_ANSWER_1);
						completeTask(Task.TASK_KEY_ANSWER_5);
					} else if (reProblemSize >= 10 && reProblemSize < 20) {
						completeTask(Task.TASK_KEY_ANSWER_1);
						completeTask(Task.TASK_KEY_ANSWER_5);
						completeTask(Task.TASK_KEY_ANSWER_10);

					} else if (reProblemSize >= 20 && reProblemSize < 50) {
						completeTask(Task.TASK_KEY_ANSWER_1);
						completeTask(Task.TASK_KEY_ANSWER_5);
						completeTask(Task.TASK_KEY_ANSWER_10);
						completeTask(Task.TASK_KEY_ANSWER_20);

					} else if (reProblemSize >= 50 && reProblemSize < 100) {
						completeTask(Task.TASK_KEY_ANSWER_1);
						completeTask(Task.TASK_KEY_ANSWER_5);
						completeTask(Task.TASK_KEY_ANSWER_10);
						completeTask(Task.TASK_KEY_ANSWER_20);
						completeTask(Task.TASK_KEY_ANSWER_50);

					} else if (reProblemSize >= 100) {
						completeTask(Task.TASK_KEY_ANSWER_1);
						completeTask(Task.TASK_KEY_ANSWER_5);
						completeTask(Task.TASK_KEY_ANSWER_10);
						completeTask(Task.TASK_KEY_ANSWER_20);
						completeTask(Task.TASK_KEY_ANSWER_50);
						completeTask(Task.TASK_KEY_ANSWER_100);
					}
				}
				// 回答一次问题赠送5积分 成长积分（经验值）
				// 积分表进行记录
				TotolsScore totolsScore = new TotolsScore();
				totolsScore = this.totolsScoreService.getTotolsScore(userId);
				if (totolsScore != null) {
					// 赠送成长积分5
					int tsA = totolsScore.getTsAction();
					tsA = tsA + 5;
					totolsScore.setTsAction(tsA);
					// 赠送兑换积分5
					int tsT = totolsScore.getTsCurrent();
					tsT = tsT + 5;
					totolsScore.setTsCurrent(tsT);
					this.totolsScoreService.updateTotolsScore(totolsScore);

					// 积分记录表进行记录
					TsRecord tsRecord = new TsRecord();
					tsRecord.setCusId(userId);
					tsRecord.setTrType(TsRecord.TRTYPE_ACTION);
					tsRecord.setAccessType(TsRecord.ACCESSTYPE_FOR_ANSWERQ);
					tsRecord.setAccessTime(new Date());
					// tsRecord.setUseType(00);
					// tsRecord.setUseTime(useTime);
					tsRecord.setTsId(totolsScore.getTsId());
					tsRecord.setTrNum(5);
					this.tsRecordService.addTsRecord(tsRecord);

					// 积分记录表进行记录
					TsRecord tsR = new TsRecord();
					tsR.setCusId(userId);
					tsR.setTrType(TsRecord.TRTYPE_FOR);
					tsR.setAccessType(TsRecord.ACCESSTYPE_FOR_NANSWERQ);
					tsR.setAccessTime(new Date());
					// tsRecord.setUseType(00);
					// tsRecord.setUseTime(useTime);
					tsR.setTsId(totolsScore.getTsId());
					tsR.setTrNum(5);
					this.tsRecordService.addTsRecord(tsR);
				}
			}
			// 如果是官方认证达人做出的回答，则直接设置最佳答案为此答案。
			// if(officialCustomer.checkOfficialCustomer(userId)) {//add by
			// shixiaofeng 取消达人自动设为最佳答案的功能
			// if (this.problem == null) {
			// this.problem = new Problem();
			// }
			// if (this.reProblem == null) {
			// this.reProblem = new ReProblem();
			// }
			// this.problem.setPblId(problem.getPblId());
			// this.reProblem.setReId(reid);
			// this.myProblemIsResult();
			// }
			if (manw != null && !"".equals(manw)) {

				result = "myansw";
			} else {
				result = "everyonewenda";
			}
			// 回答问题任务
		} catch (Exception e) {
			logger.error("ProblemWebAction.problemContent", e);
			return ERROR;
		}
		return result;
	}

	/**
	 * @author cxs 功能：完成任务
	 * @param args
	 * @param keyWord
	 */
	private void completeTask(String keyWord) {
		int userId = this.getLoginUserId();
		QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
		TaskCus tc = null;
		queryTaskCusCondition.setCusId(userId);
		queryTaskCusCondition.setKeyWord(keyWord);

		tc = taskCusService.getTaskCusByKey(queryTaskCusCondition);

		if (tc != null && tc.getIsComplete() == 0) {// 若果未完成则设置完成
			tc.setIsComplete(1);
			taskCusService.updateTaskCus(tc);
		}
	}

	/**
	 * ********************** add by shixiaofeng 20120707 新版highso问答
	 * start*************************************
	 */

	/**
	 * 
	 * @Title: getEveryOneProblemList
	 * @Description: TODO(新版highso问答列表页面)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String getEveryOneProblemList() {

		try {
			int userId = this.getLoginUserId();
			// 查询当前登录用户购买的项目
			subList = subjectService.getSubjectListForUnAss(userId);
			if(subList==null || subList.size()==0){
				return "onsubject";
			}
			StringBuffer buf = new StringBuffer();
			if (subList != null && subList.size() > 0) {
				for (int i = 0; i < subList.size(); i++) {
					if (i == (subList.size() - 1)) {
						buf.append(subList.get(i).getSubjectId());
					} else {
						buf.append(subList.get(i).getSubjectId() + ",");
					}
				}
			}
			this.getQueryProblemCondition().setSubjectIds(buf.toString());
			// 查询已解决和待解决
			if (problem.getPblSolv() != 0) {
				this.getQueryProblemCondition()
						.setPblSolv(problem.getPblSolv());
			}
			this.getQueryProblemCondition().setPblType(problem.getPblType());//添加查询问题的类型
			this.getQueryProblemCondition().setPageSize(10);
			setPage(this.problemService
					.getPageProblemListById(getQueryProblemCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}

			/**
			 * 查询各个类型问题数量
			 */
			List<Problem> proCountList = this.problemService.getProblemCountBySubjectId(buf.toString());
			if(proCountList!=null && proCountList.size()>0){
				for(Problem pbl:proCountList){
					if(pbl.getPblType()==1){
						this.proTypeOneCount=pbl.getPblCountByType();
					}else if(pbl.getPblType()==2){
						this.proTypeTwoCount=pbl.getPblCountByType();
					}else if(pbl.getPblType()==3){
						this.proTypeThrCount=pbl.getPblCountByType();
					}
					else if(pbl.getPblType()==4){
						this.proTypeFouCount=pbl.getPblCountByType();
					}
				}
			}
			// 查询认证达人信息
			officialCustomerDTOList = officialCustomer
					.queryOfficialCustomerDTOBySubjectIds(subList);
//			checkOfficialCustomer = officialCustomer
//					.checkOfficialCustomer(userId);
		} catch (Exception ex) {
			logger.error("ProblemWebAction.getEveryOneProblem", ex);
			return ERROR;
		}
		return "wenda_list";

	}

	/**
	 * 
	 * @Title: getMyProblemList
	 * @Description: TODO(新版highso问答，我的问题列表)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String getMyProblemList() {
		try {

			int userId = this.getLoginUserId();
			this.getQueryProblemCondition().setCusId(userId);
			this.getQueryProblemCondition().setPageSize(20);

			// 视频类别
//			if (problem.getPblType() != 0) {
//				this.getQueryProblemCondition()
//						.setPblType(problem.getPblType());
//			}
			// 视频是否解决
			if (problem.getPblSolv() != 0) {
				this.getQueryProblemCondition()
						.setPblSolv(problem.getPblSolv());
			}
			// 视频是否热门
			this.getQueryProblemCondition().setPblHot(2);
			// 分页
			PageResult pr = this.problemService
					.getPageProblemList(getQueryProblemCondition());
			List<Problem> problemList = pr.getPageResult();

			List<Problem> newproblemList = new ArrayList<Problem>();
			for (Problem p : problemList) {
				List<ReProblem> rpList = p.getReProblemList();
				List<ReProblem> newRpList = new ArrayList<ReProblem>();
				for (ReProblem rp : rpList) {
					if (rp.getReManId() != userId && rp.getReStatus() == 1) {// 非自己回答并且是隐藏状态的答案
					} else {
						newRpList.add(rp);
					}
				}
				p.setReProblemList(newRpList);
				newproblemList.add(p);
			}
			pr.setPageResult(newproblemList);
			setPage(pr);
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
			/**
			 * 查询各个类型问题数量
			 */
			// 查询当前登录用户购买的项目
			subList = subjectService.getSubjectListForUnAss(userId);
			if(subList==null || subList.size()==0){
				return "onsubject";
			}
			StringBuffer buf = new StringBuffer();
			if (subList != null && subList.size() > 0) {
				for (int i = 0; i < subList.size(); i++) {
					if (i == (subList.size() - 1)) {
						buf.append(subList.get(i).getSubjectId());
					} else {
						buf.append(subList.get(i).getSubjectId() + ",");
					}
				}
			}
			List<Problem> proCountList = this.problemService.getProblemCountBySubjectId(buf.toString());
			if(proCountList!=null && proCountList.size()>0){
				for(Problem pbl:proCountList){
					if(pbl.getPblType()==1){
						this.proTypeOneCount=pbl.getPblCountByType();
					}else if(pbl.getPblType()==2){
						this.proTypeTwoCount=pbl.getPblCountByType();
					}else if(pbl.getPblType()==3){
						this.proTypeThrCount=pbl.getPblCountByType();
					}
					else if(pbl.getPblType()==4){
						this.proTypeFouCount=pbl.getPblCountByType();
					}
				}
			}
			// 查询已购买的专业
			officialCustomerDTOList = officialCustomer
					.queryOfficialCustomerDTOBySubjectIds(subList);
			checkOfficialCustomer = officialCustomer
					.checkOfficialCustomer(userId);
		} catch (Exception ex) {
			logger.error("ProblemWebAction.getMyProblem", ex);
			return ERROR;
		}
		return "wenda_myproblem";
	}

	/**
	 * 
	 * @Title: myAnswerProblemList
	 * @Description: TODO(新版highso，我的回答列表)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String myAnswerProblemList() {
		try {
			int userId = this.getLoginUserId();
			this.getQueryProblemCondition().setCusId(userId);
			this.getQueryProblemCondition().setPageSize(20);
			if (problem.getPblType() != 0) {
				this.getQueryProblemCondition()
						.setPblType(problem.getPblType());
			}

			PageResult pr = this.problemService
					.getReProblemByCusId(getQueryProblemCondition());
			List<Problem> problemList = pr.getPageResult();

			List<Problem> newproblemList = new ArrayList<Problem>();
			for (Problem p : problemList) {
				List<ReProblem> rpList = p.getReProblemList();
				List<ReProblem> newRpList = new ArrayList<ReProblem>();
				for (ReProblem rp : rpList) {
					if (rp.getReManId() != userId && rp.getReStatus() == 1) {// 非自己回答并且是隐藏状态的答案
					} else {
						newRpList.add(rp);
					}
				}
				p.setReProblemList(newRpList);
				newproblemList.add(p);
			}
			pr.setPageResult(newproblemList);
			setPage(pr);
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
			
			/**
			 * 查询各个类型问题数量
			 */
			// 查询当前登录用户购买的项目
			subList = subjectService.getSubjectListForUnAss(userId);
			if(subList==null || subList.size()==0){
				return "onsubject";
			}
			StringBuffer buf = new StringBuffer();
			if (subList != null && subList.size() > 0) {
				for (int i = 0; i < subList.size(); i++) {
					if (i == (subList.size() - 1)) {
						buf.append(subList.get(i).getSubjectId());
					} else {
						buf.append(subList.get(i).getSubjectId() + ",");
					}
				}
			}
			List<Problem> proCountList = this.problemService.getProblemCountBySubjectId(buf.toString());
			if(proCountList!=null && proCountList.size()>0){
				for(Problem pbl:proCountList){
					if(pbl.getPblType()==1){
						this.proTypeOneCount=pbl.getPblCountByType();
					}else if(pbl.getPblType()==2){
						this.proTypeTwoCount=pbl.getPblCountByType();
					}else if(pbl.getPblType()==3){
						this.proTypeThrCount=pbl.getPblCountByType();
					}
					else if(pbl.getPblType()==4){
						this.proTypeFouCount=pbl.getPblCountByType();
					}
				}
			}
			// 查询已购买的专业
			officialCustomerDTOList = officialCustomer
					.queryOfficialCustomerDTOBySubjectIds(subList);
			checkOfficialCustomer = officialCustomer
					.checkOfficialCustomer(userId);
		} catch (Exception e) {
			logger.error("ProblemWebAction.myAnswerProblem", e);
			return ERROR;
		}
		return "wenda_myanswer";
	}

	/**
	 * 
	 * @Title: addProblemNewList
	 * @Description: TODO(新版highso问答，提交新的问题)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String addProblemNewList() {
		try {

			if ("".equals(this.getPro_content())
					|| this.getPro_content() == null) {
				addError = "请输入问题内容!";
				this.setResult(new Result<Object>(false, addError, null, null));
			} else {
				int userId = this.getLoginUserId();
				Date date = new Date();
				// 过滤问题标题和内容中的敏感词
				KeyWordFilter kw = KeyWordFilter.getInstance(disWordService);
				problem.setPblTitle(kw
						.doFilter(this.getPro_content().length() > 20 ? this
								.getPro_content().substring(0, 19) : this
								.getPro_content()));
				problem.setPblContent(kw.doFilter(this.getPro_content()));
				problem.setCreateTime(date);
				problem.setCourseId(15);
				problem.setCusId(userId);
				problem.setSubjectId(this.subject_id);
				// 待解决的问题，新添加的问题
				problem.setPblSolv(2);
				problem.setPblStatus(0);// 是否隐藏
				problem.setPblType(this.getProblem_type());
				this.problemService.addProblem(problem);
				this.setResult(new Result<Object>(true, "问题添加成功", null, null));
			}
		} catch (Exception e) {
			logger.error("ProblemWebAction.addProblemNewList", e);
			this.setResult(new Result<Object>(false, "问题添加失败", null, null));
			return "json";
		}

		return "json";
	}
	

	/**
	 * ********************** add by shixiaofeng 20120707 新版highso问答
	 * end*************************************
	 */

	/**
	 * 我的问题
	 * 
	 * @return
	 */
	public String getMyProblem() {
		try {

			int userId = this.getLoginUserId();
			this.getQueryProblemCondition().setCusId(userId);
			this.getQueryProblemCondition().setPageSize(20);

			if (problem.getPblType() != 0) {
				this.getQueryProblemCondition()
						.setPblType(problem.getPblType());
			}
			if (problem.getPblSolv() != 0) {
				this.getQueryProblemCondition()
						.setPblSolv(problem.getPblSolv());
			}
			// 分页
			PageResult pr = this.problemService
					.getPageProblemList(getQueryProblemCondition());
			List<Problem> problemList = pr.getPageResult();

			List<Problem> newproblemList = new ArrayList<Problem>();
			for (Problem p : problemList) {
				List<ReProblem> rpList = p.getReProblemList();
				List<ReProblem> newRpList = new ArrayList<ReProblem>();
				for (ReProblem rp : rpList) {
					if (rp.getReManId() != userId && rp.getReStatus() == 1) {// 非自己回答并且是隐藏状态的答案
					} else {
						newRpList.add(rp);
					}
				}
				p.setReProblemList(newRpList);
				newproblemList.add(p);
			}
			pr.setPageResult(newproblemList);
			setPage(pr);
			// setPage(this.problemService
			// .getPageProblemList(getQueryProblemCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}

		} catch (Exception ex) {
			logger.error("ProblemWebAction.getMyProblem", ex);
			return ERROR;
		}
		return "wenda_weiti";
	}

	/**
	 * 
	 * @Title: getMyProblemContent
	 * @Description: TODO(新版highso问答，我的问题详细)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String getMyProblemContent() {
		try {
			int userId = this.getLoginUserId();
			problem = this.problemService.getProblemById(problem.getPblId());
			if (problem != null) {
				for (int i = 0; i < problem.getReProblemList().size(); i++) {
					if (problem.getReProblemList().get(i).getIsResult() == 1) {
						takeResultList.add(problem.getReProblemList().get(i));
					} else {
						if (problem.getReProblemList().get(i).getReManId() != userId
								&& problem.getReProblemList().get(i)
										.getReStatus() == 1) {

						} else {
							resultList.add(problem.getReProblemList().get(i));
						}

					}
				}
				// int userId = this.getLoginUserId();
				totolsScore = this.totolsScoreService.getTotolsScore(userId);
			}
		} catch (Exception ex) {
			logger.error("ProblemWebAction.getMyProblemContent", ex);
			return ERROR;
		}
		return "wenda_myque";
	}

	/**
	 * 采纳为答案
	 * 
	 * @return
	 */
	public String myProblemIsResult() {
		try {
			// 把问题表更新上有答案了，为已解决问题
			problem = this.problemService.getProblemById(problem.getPblId());
			problem.setPblSolv(1);
			this.problemService.updateProblem(problem);
			// 把答案也更新上，哪个问题为最佳答案
			reProblem = this.reProblemService.getReProblemById(reProblem
					.getReId());
			reProblem.setIsResult(1);
			this.reProblemService.updateReProblem(reProblem);
			// 把问题积分给相应回答的用户

			//Date date = new Date();
			// 积分表进行记录
//			TotolsScore totolsScore = this.totolsScoreService
//					.getTotolsScore(reProblem.getReManId());
//			if (totolsScore != null) {
//
//				int tsA = totolsScore.getTsCurrent();
//				tsA = tsA + problem.getPblTotols();
//				// 赠送5兑换积分
//				tsA = tsA + 5;
//				totolsScore.setTsCurrent(tsA);
//
//				// 采纳为答案，赠送，10，成长积分
//				int tsC = totolsScore.getTsAction();
//				tsC = tsC + 10;
//				totolsScore.setTsAction(tsC);
//
//				this.totolsScoreService.updateTotolsScore(totolsScore);
//				// 积分记录表进行记录
//				TsRecord tsRecord = new TsRecord();
//				tsRecord.setCusId(reProblem.getReManId());
//				tsRecord.setTrType(TsRecord.TRTYPE_FOR);
//				tsRecord.setAccessType(TsRecord.ACCESSTYPE_FOR_ANSWER);
//				tsRecord.setAccessTime(date);
//				tsRecord.setTsId(totolsScore.getTsId());
//				tsRecord.setTrNum(problem.getPblTotols() + 5);
//				this.tsRecordService.addTsRecord(tsRecord);
//				// 记录成长积分的赠送
//				TsRecord tsR = new TsRecord();
//				tsR.setCusId(reProblem.getReManId());
//				tsR.setTrType(TsRecord.TRTYPE_ACTION);
//				tsR.setAccessType(TsRecord.ACCESSTYPE_FOR_ANSWERQE);
//				tsR.setAccessTime(date);
//				tsR.setTsId(totolsScore.getTsId());
//				tsR.setTrNum(10);
//				this.tsRecordService.addTsRecord(tsR);
//			}

			// //记录兑换积分的赠送 5
			// TsRecord tsRe=new TsRecord();
			// tsRe.setCusId(reProblem.getReManId());
			// tsRe.setTrType(TsRecord.TRTYPE_FOR);
			// tsRe.setAccessType(TsRecord.ACCESSTYPE_FOR_NANSWERQE);
			// tsRe.setAccessTime(date);
			// tsRe.setTsId(totolsScore.getTsId());
			// tsRe.setTrNum(5);
			// this.tsRecordService.addTsRecord(tsRe);

			// 给提问者发消息
			// Customer cusSender =
			// customerService.getCustomerById(this.getLoginUserId());
			Customer cus = customerService.getCustomerById(reProblem
					.getReManId());
			if (cus != null) {
				Message msg = new Message();
				msg
						.setMsgContent("您的回答被采纳为最佳答案，问题为[<a href=\"../cus/pblimit!getEveryOneContent.action?problem.pblId="
								+ problem.getPblId() + "\">"
								+ problem.getPblTitle() + "</a>]");
				int msgId = messageService.addMessage(msg);
				User sender = new User();
				sender.setUserId(1);
				sender.setUserName("超级管理员");
				userMsgService.adminerSendMsgToCutomer(sender, msgId, cus);
			}
			// 给提问者发消息

		} catch (Exception ex) {
			logger.error("ProblemWebAction.myProblemIsResult", ex);
			return ERROR;
		}
		return "mywendaIsResult";
	}

	/**
	 * 回复问题
	 * 
	 * @return
	 */

	public String addProblem() {
		try {
			Date date = new Date();
			// 通过cookie获得userId
			int userId = this.getLoginUserId();
			reProblem.setPblId(problem.getPblId());
			reProblem.setReInfo(problem.getPblTitle());
			reProblem.setReTime(date);
			reProblem.setReManId(userId);
			reProblem.setReManType(ReProblem.REPROBLEM_COSTOMER);
			this.reProblemService.addReProblem(reProblem);

		} catch (Exception e) {
			logger.error("ProblemWebAction.addProblem", e);
			return ERROR;
		}
		return "everyonewenda";
	}

	/**
	 * 提交新问题
	 * 
	 * @return
	 */
	public String addProblemNew() {
		String ts = "";
		try {
			if (problem.getPblTotols() == 0) {
				addError = "积分不能为0!";
				return getHighSoProblem();
			} else if (problem.getSubjectId() == 0) {
				addError = "请选择提问专业!";
				return getHighSoProblem();
			} else if ("".equals(problem.getPblContent())
					|| problem.getPblContent() == null) {
				addError = "请输入问题内容!";
				return getHighSoProblem();
			} else if ("".equals(problem.getPblTitle())
					|| problem.getPblTitle() == null) {
				addError = "请输入问题标题!";
				return getHighSoProblem();
			} else if (problem.getPblContent().indexOf("file:") != -1) {
				addError = "请删除外部图片！";
				return getHighSoProblem();
			} else if (problem.getPblContent().indexOf("<a") != -1) {
				addError = "请删除外部链接！";
				return getHighSoProblem();
			} else {
				int userId = this.getLoginUserId();
				totolsScore = this.totolsScoreService.getTotolsScore(userId);
				if (totolsScore.getTsCurrent() < problem.getPblTotols()) {
					strBug = 1;
					ts = "mwenda";
				} else {
					Date date = new Date();
					// 过滤问题标题和内容中的敏感词
					KeyWordFilter kw = KeyWordFilter
							.getInstance(disWordService);
					problem.setPblTitle(kw.doFilter(problem.getPblTitle()));
					problem.setPblContent(kw.doFilter(problem.getPblContent()));
					problem.setCreateTime(date);
					problem.setCourseId(15);
					problem.setCusId(userId);
					// 待解决的问题，新添加的问题
					problem.setPblSolv(2);
					problem.setPblStatus(0);// 是否隐藏
					// 查出当前用户的积分，减去问题给的积分数 +5兑换积分

					totolsScore.setTsCurrent(totolsScore.getTsCurrent()
							- problem.getPblTotols() + 5);
					// 增加经验5
					int tsA = totolsScore.getTsAction();
					tsA = tsA + 5;
					totolsScore.setTsAction(tsA);
					// 增加兑换积分5
					this.totolsScoreService.updateTotolsScore(totolsScore);

					this.problemService.addProblem(problem);
					// 积分记录表进行记录
					TsRecord tsRecord = new TsRecord();
					tsRecord.setCusId(userId);
					tsRecord.setTrType(TsRecord.TRTYPE_ACTION);
					tsRecord.setAccessType(TsRecord.ACCESSTYPE_FOR_ONEANSWER);
					tsRecord.setAccessTime(new Date());
					tsRecord.setTsId(totolsScore.getTsId());
					tsRecord.setTrNum(5);
					this.tsRecordService.addTsRecord(tsRecord);

					// 消耗积分记录下来。。
					TsRecord tsR = new TsRecord();
					tsR.setCusId(userId);
					tsR.setTrType(TsRecord.TRTYPE_FOR);
					tsR.setUseType(TsRecord.USETYPE_ANSWERQ);
					tsR.setUseTime(new Date());
					tsR.setTsId(totolsScore.getTsId());
					tsR.setTrNum(problem.getPblTotols());
					this.tsRecordService.addTsRecord(tsR);

					// 兑换分记录下来。。
					TsRecord tsRe = new TsRecord();
					tsRe.setCusId(userId);
					tsRe.setTrType(TsRecord.TRTYPE_FOR);
					tsRe.setAccessType(TsRecord.ACCESSTYPE_FOR_NONEANSWER);
					tsRe.setAccessTime(new Date());
					tsRe.setTsId(totolsScore.getTsId());
					tsRe.setTrNum(5);
					this.tsRecordService.addTsRecord(tsRe);

					ts = "mywenda";
				}
			}
		} catch (Exception e) {
			logger.error("ProblemWebAction.addProblemNew", e);
			return ERROR;
		}

		return ts;
	}

	/**
	 * 自己提的问题，可以自己删除
	 * 
	 * @return
	 */
	public String deleteProblem() {
		try {
			// 先删除子表在删除父表
			// 删除问题对应的答案
			if (problem.getPblId() != 0) {
				this.getQueryReProblemCondition().setPblId(problem.getPblId());
			}
			reProblemList = this.reProblemService
					.getReProblemList(getQueryReProblemCondition());
			for (int i = 0; reProblemList != null && i < reProblemList.size(); i++) {
				this.reProblemService.delReProblemById(reProblemList.get(i)
						.getReId());
			}
			// 删除问题
			this.problemService.delProblemById(problem.getPblId());

		} catch (Exception e) {
			logger.error("ProblemWebAction.deleteProblem", e);
			return ERROR;
		}
		return "deletemywenda";
	}

	// 问题补充和追加分数
	public String addMyProblemContent() {

		String str = "";
		try {
			int ts = 0;
			String ct = "";
			int userId = this.getLoginUserId();
			totolsScore = this.totolsScoreService.getTotolsScore(userId);
			if (totolsScore.getTsCurrent() < score) {
				strRes = 1;
				str = "mwendaIsResult";
			} else {
				if (problem.getPblId() != 0) {
					problem = this.problemService.getProblemById(problem
							.getPblId());

					// 过滤补充问题中的敏感词
					KeyWordFilter kw = KeyWordFilter
							.getInstance(disWordService);

					ts = problem.getPblTotols() + score;
					ct = problem.getPblContent() + "<br/>"
							+ kw.doFilter(content);
					problem.setPblTotols(ts);
					problem.setPblContent(ct);
					this.problemService.updateProblem(problem);
					// 查出当前用户的积分，减去问题给的积分数
					totolsScore
							.setTsCurrent(totolsScore.getTsCurrent() - score);
					this.totolsScoreService.updateTotolsScore(totolsScore);
				}
				// 消耗积分记录下来。。
				TsRecord tsR = new TsRecord();
				tsR.setCusId(userId);
				tsR.setTrType(TsRecord.TRTYPE_FOR);
				// tsR.setAccessType(TsRecord.ACCESSTYPE_FOR_ONEANSWER);
				// tsR.setAccessTime(new Date());
				tsR.setUseType(TsRecord.USETYPE_ANSSCORE);
				tsR.setUseTime(new Date());
				tsR.setTsId(totolsScore.getTsId());
				tsR.setTrNum(score);
				this.tsRecordService.addTsRecord(tsR);
				str = "mywendaIsResult";
			}

		} catch (Exception e) {
			logger.error("ProblemWebAction.addMyProblemContent", e);
			return ERROR;
		}
		return str;
	}

	// 我的回答
	public String myAnswerProblem() {
		try {
			int userId = this.getLoginUserId();
			this.getQueryProblemCondition().setCusId(userId);
			this.getQueryProblemCondition().setPageSize(20);
			if (problem.getPblType() != 0) {
				this.getQueryProblemCondition()
						.setPblType(problem.getPblType());
			}

			PageResult pr = this.problemService
					.getReProblemByCusId(getQueryProblemCondition());
			List<Problem> problemList = pr.getPageResult();

			List<Problem> newproblemList = new ArrayList<Problem>();
			for (Problem p : problemList) {
				List<ReProblem> rpList = p.getReProblemList();
				List<ReProblem> newRpList = new ArrayList<ReProblem>();
				for (ReProblem rp : rpList) {
					if (rp.getReManId() != userId && rp.getReStatus() == 1) {// 非自己回答并且是隐藏状态的答案
					} else {
						newRpList.add(rp);
					}
				}
				p.setReProblemList(newRpList);
				newproblemList.add(p);
			}
			pr.setPageResult(newproblemList);
			setPage(pr);

			// setPage(this.problemService
			// .getReProblemByCusId(getQueryProblemCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
		} catch (Exception e) {
			logger.error("ProblemWebAction.myAnswerProblem", e);
			return ERROR;
		}
		return "myanswer";
	}

	/**
	 * 
	 * @Title: getMyAnswerContent 
	 * @Description: TODO(新版highso问答，我的回答详情) 
	 * @param @return    设定文件 
	 * @return String    返回类型
	 * @author shixiaofeng@sunland.org.cn 
	 * @throws
	 */
	public String getMyAnswerContent() {
		try {
			int userId = this.getLoginUserId();
			this.cusId = userId;
			problem = this.problemService.getProblemById(problem.getPblId());
			List<ReProblem> repList = problem.getReProblemList();
			List<ReProblem> newrepList = new ArrayList<ReProblem>();
			for (int i = 0; i < repList.size(); i++) {
				if (repList.get(i).getReManId() != userId
						&& repList.get(i).getReStatus() == 1) {
				} else {
					newrepList.add(repList.get(i));
				}
			}
			problem.setReProblemList(newrepList);
		} catch (Exception e) {
			logger.error("ProblemWebAction.getMyAnswerContent", e);
			return ERROR;
		}
		return "myanswercontent";
	}

	// 删除我回答的答案
	public String deleteMyAnswer() {
		try {
			if (reProblem.getReId() != 0) {
				this.reProblemService.delReProblemById(reProblem.getReId());
			}

		} catch (Exception e) {
			logger.error("ProblemWebAction.deleteMyAnswer", e);
			return ERROR;
		}
		return "myansw";
	}

	// 修改我回答的问题
	public String toUpdateMyAnswer() {
		try {
			if (reId != 0) {
				reProblem = this.reProblemService.getReProblemById(reId);

			}
		} catch (Exception e) {
			logger.error("ProblemWebAction.toUpdateMyAnswer", e);
			return ERROR;
		}
		return "toupdatemyans";
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public IProblem getProblemService() {
		return problemService;
	}

	public void setProblemService(IProblem problemService) {
		this.problemService = problemService;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public QueryProblemCondition getQueryProblemCondition() {
		if (this.queryProblemCondition == null) {
			this.queryProblemCondition = new QueryProblemCondition();
		}
		return queryProblemCondition;
	}

	public void setQueryProblemCondition(
			QueryProblemCondition queryProblemCondition) {
		this.queryProblemCondition = queryProblemCondition;
	}

	public List<Problem> getProblemList() {
		return problemList;
	}

	public void setProblemList(List<Problem> problemList) {
		this.problemList = problemList;
	}

	public List<ReProblem> getReProblemList() {
		return reProblemList;
	}

	public void setReProblemList(List<ReProblem> reProblemList) {
		this.reProblemList = reProblemList;
	}

	public ReProblem getReProblem() {
		return reProblem;
	}

	public void setReProblem(ReProblem reProblem) {
		this.reProblem = reProblem;
	}

	public IReProblem getReProblemService() {
		return reProblemService;
	}

	public void setReProblemService(IReProblem reProblemService) {
		this.reProblemService = reProblemService;
	}

	public QueryReProblemCondition getQueryReProblemCondition() {
		if (this.queryReProblemCondition == null) {
			this.queryReProblemCondition = new QueryReProblemCondition();
		}
		return queryReProblemCondition;
	}

	public void setQueryReProblemCondition(
			QueryReProblemCondition queryReProblemCondition) {
		this.queryReProblemCondition = queryReProblemCondition;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public List<Problem> getPlemList() {
		return plemList;
	}

	public void setPlemList(List<Problem> plemList) {
		this.plemList = plemList;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<CourseSortListDTO> getCourseSortListDTOList() {
		return courseSortListDTOList;
	}

	public void setCourseSortListDTOList(
			List<CourseSortListDTO> courseSortListDTOList) {
		this.courseSortListDTOList = courseSortListDTOList;
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}

	public TotolsScore getTotolsScore() {
		return totolsScore;
	}

	public void setTotolsScore(TotolsScore totolsScore) {
		this.totolsScore = totolsScore;
	}

	public int getHighProblem() {
		return highProblem;
	}

	public void setHighProblem(int highProblem) {
		this.highProblem = highProblem;
	}

	public List<ReProblem> getTakeResultList() {
		return takeResultList;
	}

	public void setTakeResultList(List<ReProblem> takeResultList) {
		this.takeResultList = takeResultList;
	}

	public List<ReProblem> getResultList() {
		return resultList;
	}

	public void setResultList(List<ReProblem> resultList) {
		this.resultList = resultList;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCusId() {
		return cusId;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
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

	public void setDisWordService(IDisWord disWordService) {
		this.disWordService = disWordService;
	}

	public int getReId() {
		return reId;
	}

	public void setReId(int reId) {
		this.reId = reId;
	}

	public int getSw() {
		return sw;
	}

	public void setSw(int sw) {
		this.sw = sw;
	}

	public String getManw() {
		return manw;
	}

	public void setManw(String manw) {
		this.manw = manw;
	}

	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}

	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}

	public int getCId() {
		return cId;
	}

	public void setCId(int id) {
		cId = id;
	}

	public int getStrBug() {
		return strBug;
	}

	public void setStrBug(int strBug) {
		this.strBug = strBug;
	}

	public int getStrRes() {
		return strRes;
	}

	public void setStrRes(int strRes) {
		this.strRes = strRes;
	}

	public String getSta() {
		return sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	/*
	 * public List<UserCenterCourseDTO> getUserCourseList() { return
	 * userCourseList; }
	 * 
	 * public void setUserCourseList(List<UserCenterCourseDTO> userCourseList) {
	 * this.userCourseList = userCourseList; }
	 */

	/*
	 * public ICourse getCourseService() { return courseService; }
	 * 
	 * public void setCourseService(ICourse courseService) { this.courseService =
	 * courseService; }
	 */

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getPblId() {
		return pblId;
	}

	public void setPblId(int pblId) {
		this.pblId = pblId;
	}

	public String getAddError() {
		return addError;
	}

	public void setAddError(String addError) {
		this.addError = addError;
	}

	public IComment getCommentService() {
		return commentService;
	}

	public void setCommentService(IComment commentService) {
		this.commentService = commentService;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<Comment> getCList() {
		return cList;
	}

	public void setCList(List<Comment> list) {
		cList = list;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Subject> getSubList() {
		return subList;
	}

	public void setSubList(List<Subject> subList) {
		this.subList = subList;
	}

	public IVedioCount getVediocountService() {
		return vediocountService;
	}

	public void setVediocountService(IVedioCount vediocountService) {
		this.vediocountService = vediocountService;
	}

	public int getVideoCountStatus() {
		return videoCountStatus;
	}

	public void setVideoCountStatus(int videoCountStatus) {
		this.videoCountStatus = videoCountStatus;
	}

	public List<OfficialCustomerDTO> getOfficialCustomerDTOList() {
		return officialCustomerDTOList;
	}

	public void setOfficialCustomer(IOfficialCustomer officialCustomer) {
		this.officialCustomer = officialCustomer;
	}

	public boolean isCheckOfficialCustomer() {
		return checkOfficialCustomer;
	}

	public void setCheckOfficialCustomer(boolean checkOfficialCustomer) {
		this.checkOfficialCustomer = checkOfficialCustomer;
	}

	public String getPro_content() {
		return pro_content;
	}

	public void setPro_content(String pro_content) {
		this.pro_content = pro_content;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public int getProblem_type() {
		return problem_type;
	}

	public void setProblem_type(int problem_type) {
		this.problem_type = problem_type;
	}

	public int getProTypeOneCount() {
		return proTypeOneCount;
	}

	public void setProTypeOneCount(int proTypeOneCount) {
		this.proTypeOneCount = proTypeOneCount;
	}

	public int getProTypeTwoCount() {
		return proTypeTwoCount;
	}

	public void setProTypeTwoCount(int proTypeTwoCount) {
		this.proTypeTwoCount = proTypeTwoCount;
	}

	public int getProTypeThrCount() {
		return proTypeThrCount;
	}

	public void setProTypeThrCount(int proTypeThrCount) {
		this.proTypeThrCount = proTypeThrCount;
	}

	public int getProTypeFouCount() {
		return proTypeFouCount;
	}

	public void setProTypeFouCount(int proTypeFouCount) {
		this.proTypeFouCount = proTypeFouCount;
	}

	/*
	 * private int isshitingdown=0;
	 * 
	 * public int getIsshitingdown() { return isshitingdown; }
	 * 
	 * public void setIsshitingdown(int isshitingdown) { this.isshitingdown =
	 * isshitingdown; }
	 */
}
