package com.shangde.edu.cus.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.sf.json.JSONArray;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryReProblemCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.IProblem;
import com.shangde.edu.cus.service.IReProblem;
import com.shangde.edu.sys.action.BackLoginAction;
import com.shangde.edu.sys.condition.QueryUserCondition;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IUser;

/**
 * 
 * @author miaoshusen
 * @version 1.0
 */

public class ReProblemAction extends CommonAction {
	/**
	 * 问题实体对象
	 */
	private Problem problem;
	/**
	 * 问题的服务
	 */
	private IProblem problemService;
	/**
	 * 专业
	 */
	private ISubject subjectService;
	/**
	 * 查询条件
	 */
	private QueryProblemCondition queryProblemCondition;
	private QuerySubjectCondition querySubjectCondition;
	/**
	 * 问题list
	 */
	private List<Problem> problemList;
	/**
	 * 前台学员
	 */
	private ICustomer customerService;
	private List<Customer> customerList = new ArrayList<Customer>();
	private Customer customer;
	private QueryCustomerCondition queryCustomerCondition;
	private List<Customer> scustomerList = new ArrayList<Customer>();
	/**
	 * 课程实体
	 */
	private Course course;
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	/**
	 * 课程list
	 */
	private List<Course> courseList = new ArrayList<Course>();
	private Set<Course> courseSet = new HashSet<Course>();
	private Set<Customer> customerSet = new HashSet<Customer>();
	private List<Course> scourseList = new ArrayList<Course>();
	/**
	 * 课程查询条件
	 */
	private QueryCourseCondition queryCourseCondition;
	/**
	 * 回答问题实体对象
	 */
	private ReProblem reproblem;
	/**
	 * 回答问题服务
	 */
	private IReProblem reProblemService;
	private QueryReProblemCondition queryReProblemCondition;
	private List<ReProblem> reProblemList = new ArrayList<ReProblem>();
	/**
	 * 后台用户
	 */
	private User user;
	private IUser userService;
	private QueryUserCondition queryUserCondition;
	private List<User> userList = new ArrayList<User>();
	private int subjectId;
	private int pblHot;
	private int pblId;
	private List<Subject> subjectList=new ArrayList<Subject>();

	/**
	 * 获得问题列表的页面
	 * 
	 * @return
	 */
	public String getProblemList() {
		try {
			// 查询条件
			// if(problem!=null&&problem.getCusId()!=0){
			// this.getQueryProblemCondition().setCusId(problem.getCusId());
			// }
			// if(problem!=null&&problem.getCourseId()!=0){
			// this.getQueryProblemCondition().setCourseId(problem.getCourseId());
			// }
			if (problem != null && problem.getPblTitle() != null
					&& !"".equals(problem.getPblTitle().trim())) {
				this.getQueryProblemCondition().setPblTitle(
						problem.getPblTitle().trim());
			}
			//设置是否热门问题
				
				this.getQueryProblemCondition().setPblHot(pblHot);

			if (subjectId != 0) {

				this.getQueryProblemCondition().setSubjectId(subjectId);
			}
			this.getQueryProblemCondition().setPageSize(30);
			// 查询问题列表
			setPage(this.problemService
					.getPageProblemList(getQueryProblemCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			//查询专业列表
			subjectList=subjectService.getSubjectList(querySubjectCondition);
			// //查询出用户的名称，和课程名称
			// problemList=this.problemService.getProblemList(getQueryProblemCondition());
			// for(int i=0;problemList!=null&&i<problemList.size();i++){
			// customer=new Customer();
			// course=new Course();
			//				
			//				
			// customer=this.customerService.getCustomerById(problemList.get(i).getCusId());
			// course=this.courseService.getCourseById(problemList.get(i).getCourseId());
			//				
			// courseSet.add(course);
			// customerSet.add(customer);
			//				
			// //处理前台显示的标题
			// String title=problemList.get(i).getPblTitle();
			// if(title.length()>10){
			// title=title.substring(0,10);
			// problemList.get(i).setPblTitle(title+"...");
			// }
			// customerList.add(customer);
			// courseList.add(course);
			// }
			// scourseList.addAll(courseSet);
			// scustomerList.addAll(customerSet);
			// customerList=this.customerService.getCustomerList(getQueryCustomerCondition());
			// courseList=this.courseService.getCourseList(getQueryCourseCondition());

		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "getProblemList";
	}

	/**
	 * 打开回复问题页面
	 * 
	 * @return
	 */
	public String toReProblem() {
		try {
			if (problem.getPblId() != 0) {
				problem = this.problemService
						.getProblemById(problem.getPblId());
				customer = this.customerService.getCustomerById(problem
						.getCusId());
				this.getQueryReProblemCondition().setPblId(problem.getPblId());
				reProblemList = this.reProblemService
						.getReProblemList(getQueryReProblemCondition());
			}
			// 得出所有后台用户的集合
			setPage(this.userService.getUserList(getQueryUserCondition()));
			userList = userService.getUsetByList(getQueryUserCondition());
			// 哪个老师回答的，显示出后台用户名
			User user = this.getSession(BackLoginAction.SYS_USER_SESSION_NAME);
			this.user = this.userService.getUserById(user.getUserId());
			// 得出所有前台用户的集合
			customerList = this.customerService
					.getCustomerList(getQueryCustomerCondition());
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "toReProblem";
	}

	/**
	 * 回复问题继续提问题
	 * 
	 * @return
	 */
	public String addReProblem() {
		try {
			if (user == null) {
				user = this.getLoginedUser();
			}
			Date date = new Date();
			reproblem.setReTime(date);
			reproblem.setReManId(user.getUserId());
			reproblem.setReManType(ReProblem.REPROBLEM_USER);
			reproblem.setPblId(problem.getPblId());
			this.reProblemService.addReProblem(reproblem);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}

		return "changeSuccess";
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
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}

	/**
	 * 删除问题
	 * 
	 * @return
	 */
	public String deleteProblem() {
		// 先删除子表在删除父表
		// 删除问题对应的答案
		try {
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
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}

		return "deleteProblemSuccess";
	}

	// 置为热门问题
	public String toHotProblem() {
		try {
			if (problem.getPblId() != 0) {
				problem = this.problemService
						.getProblemById(problem.getPblId());
				problem.setPblHot(1);
				this.problemService.updateProblem(problem);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}

	// 显示问题内容
	public String toProblemView() {
		try {
			if (problem.getPblId() != 0) {
				problem = this.problemService
						.getProblemById(problem.getPblId());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "problemview";
	}

	public String toEditProblem() {
		try {
			if (problem.getPblId() != 0) {
				problem = this.problemService
						.getProblemById(problem.getPblId());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "toeditproblem";
	}

	public String editProblem() {
		try {
			if (problem.getPblId() != 0) {
				problem = this.problemService
						.getProblemById(problem.getPblId());
				problem.setSubjectId(subjectId);
				this.problemService.updateProblem(problem);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "deleteProblemSuccess";
	}

	public String deleteReProblem() {
		try {
			if (reproblem.getReId() != 0) {
				this.reProblemService.delReProblemById(reproblem.getReId());
			}
			if (problem.getPblId() != 0) {
				problem = this.problemService
						.getProblemById(problem.getPblId());

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "problemview";
	}

	public IProblem getProblemService() {
		return problemService;
	}

	public void setProblemService(IProblem problemService) {
		this.problemService = problemService;
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

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public void setProblemList(List<Problem> problemList) {
		this.problemList = problemList;
	}

	public Set<Course> getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(Set<Course> courseSet) {
		this.courseSet = courseSet;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		if (problem == null) {
			problem = new Problem();
		}
		this.problem = problem;
	}

	public Set<Customer> getCustomerSet() {
		return customerSet;
	}

	public void setCustomerSet(Set<Customer> customerSet) {
		this.customerSet = customerSet;
	}

	public ReProblem getReproblem() {
		return reproblem;
	}

	public void setReproblem(ReProblem reproblem) {
		this.reproblem = reproblem;
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

	public List<ReProblem> getReProblemList() {
		return reProblemList;
	}

	public void setReProblemList(List<ReProblem> reProblemList) {
		this.reProblemList = reProblemList;
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

	public QueryUserCondition getQueryUserCondition() {
		if (queryUserCondition == null) {
			queryUserCondition = new QueryUserCondition();
			queryUserCondition.setUserType(-1);
		}
		return queryUserCondition;
	}

	public void setQueryUserCondition(QueryUserCondition queryUserCondition) {
		this.queryUserCondition = queryUserCondition;
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

	public QueryCourseCondition getQueryCourseCondition() {

		if (this.queryCourseCondition == null) {
			this.queryCourseCondition = new QueryCourseCondition();
		}
		return queryCourseCondition;
	}

	public void setQueryCourseCondition(
			QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}

	public List<Customer> getScustomerList() {
		return scustomerList;
	}

	public void setScustomerList(List<Customer> scustomerList) {
		this.scustomerList = scustomerList;
	}

	public List<Course> getScourseList() {
		return scourseList;
	}

	public void setScourseList(List<Course> scourseList) {
		this.scourseList = scourseList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getPblId() {
		return pblId;
	}

	public void setPblId(int pblId) {
		this.pblId = pblId;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public QuerySubjectCondition getQuerySubjectCondition() {
		return querySubjectCondition;
	}

	public void setQuerySubjectCondition(QuerySubjectCondition querySubjectCondition) {
		this.querySubjectCondition = querySubjectCondition;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public int getPblHot() {
		return pblHot;
	}

	public void setPblHot(int pblHot) {
		this.pblHot = pblHot;
	}

}
