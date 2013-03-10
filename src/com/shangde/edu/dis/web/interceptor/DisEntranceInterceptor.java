package com.shangde.edu.dis.web.interceptor;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.shangde.common.intercepter.LimitIntercepterForWeb;
import com.shangde.common.util.CookieHandler;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.dis.domain.CusUserDis;
import com.shangde.edu.dis.domain.Discussion;
import com.shangde.edu.dis.dto.SubjectDTO;
import com.shangde.edu.dis.service.ICusUserDis;
import com.shangde.edu.dis.service.IDiscussion;

/**
 * 小组入口初始化拦截
 * 
 * 
 * 拦截：1、进入我的HighSo，*2、购买课程后*，3、从消息模块中点击查看帖子请求过滤
 * 
 * @author Libg
 *
 */
public class DisEntranceInterceptor extends MethodFilterInterceptor {

	// 用户服务
	private ICustomer customerService;
	//用户实体
	private Customer customer;
	// 组关系对象
	private CusUserDis cusUserDis = null;
	
	/**
	 * 获取已加入的小组列表
	 */
	private List<CusUserDis> cusUserDisList = new ArrayList<CusUserDis>();
	
	// 区域映射业务接口
	private ICusUserDis cusUserDisService;
	// if session 存有已购买专业的信息ID
	private List<SubjectDTO> buySubjects = new ArrayList<SubjectDTO>();
	// 课程
	private SubjectDTO subjectDTO;
	// 讨论组业务
	private IDiscussion discussionService;
	// 所有小组列表
	//private List<Discussion> disAll = new ArrayList<Discussion>();
	private List<Discussion> disAll = null;
	// 小组列表
	//private List<Discussion> disList = new ArrayList<Discussion>();
	private List<Discussion> disList = null;
	
	//必选项，监控到该函数名称的请求，立即加载
	private String []filters = {"toUserCenter","getContractList","toPayOk"};
	
	/**
	 * 实现进入小组首页时，数据初始化
	 * 
	 * 从进入小组首页方法中，独立出来，避免每次点进入小组首页时,执行判断
	 * 
	 */
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		
		Object disInfoObject = actionContext.getSession().get("dis_info");//进入小组默认显示小组对象
		Object sessionAuthObject = actionContext.getSession().get("session_auth");//用户处于当前小组的权限(两种：1、只读，2、增删改)
		Object sessionDisIdObject = actionContext.getSession().get("session_disId");//进入小组默认显示小组id
		
		System.out.println("-------------------------------------------进入拦截器");
		//校验是否在重要请求范围内
		boolean flag = false;
		String requestURI = request.getRequestURI();
		for(String s : filters){
			if(requestURI.indexOf(s) != -1){
				flag = true;
				break;
			}
		}
		
		/*********************************************这里测试，故意将下面的模块抛出异常，并是否继续网下执行..........***/
		
		//TODO 这里测试，故意将下面的模块抛出异常，并是否继续网下执行;kkkkkkkkkkkkkkkk
		try{
			//如果在必须重新载入的范围内，则直接载入，否则校验session范围值是否有效在加载
			if(flag){
				disInit(invocation);
			}else{
				//条件成立，将重新载入小组首页数据
				if(disInfoObject == null || sessionAuthObject == null || sessionDisIdObject == null){
					disInit(invocation);
				}
			}
		}catch (Exception e) {
			log.error("讨论小组-拦截器-异常-（原因较多）" + e.getMessage(), e);
		}
		
		System.out.println("-------------------------------------------拦截器结束");
		return invocation.invoke();
	}
	

	/**
	 * 小组重新初始化
	 * @param invocation
	 */
	private void disInit(ActionInvocation invocation) throws Exception {

		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);

		int userId = 0;
		// 取登陆用户ID
		try{
			userId = getLoginUserId(request);
		}catch (Exception e) {
			log.error("讨论小组拦截器-disInit->获取userIdCookie出错", e);
		}

		// 初始化用户
		if (actionContext.getSession().get("customer") == null) {
			customer = customerService.getCustomerById(userId);
			actionContext.getSession().put("customer", customer);
		}
		
		log.info("dis****************************************************************start-------------->userId--->" + userId);

		//获取课程id
		//这里增加判断，如果专业subjectId无法获取，则不继续执行*****************************************************
		Integer subjectId = null;
		Integer disId = null;//小组id
		try{
			String subId = CookieHandler.getCookieValueByName(request, "subjectId");
			if(subId != null || !subId.equals("")){
				subjectId = Integer.parseInt(subId);
			}else{
				throw new Exception("小组拦截器--取cookie之subjectid时，置为空异常");
			}
		}catch (Exception e) {
			log.error("小组拦截器--取cookie之subjectid时，置为空", e);
			throw e;
		}

		// this.getSession("cus_user_dis_list");
		// cusUserDisList = new ArrayList<CusUserDis>(); //Libg 2011-10-18 update
		//用来保存用户已经存在的群组,过滤掉重复存在的群组
		Map<Integer,CusUserDis> userDisMap = new HashMap<Integer, CusUserDis>();
		try{
			//获取已加入的小组列表
			cusUserDisList = this.cusUserDisService.findCusUserDisByCusId(userId);
			if(cusUserDisList != null && cusUserDisList.size()>0){
				for(CusUserDis cd : cusUserDisList){//过滤
					userDisMap.put(cd.getDisId(), cd);
				}
			}
			
		
			log.info("-------------------------userId--->" + userId);
			log.info("cusUserDisList-----size----------->" + cusUserDisList.size());
		}catch (Exception e) {
			log.error("***查询用户已加入小组-异常-拦截器***", e);
			throw e;
		}
		// buySubjects = (List<SubjectDTO>) this.getSession("buy_subjects");
		
		
		
		// ~ 用户是否购买过专业
		try{
			buySubjects = this.getCusUserDisService().getMyBuySubject(userId);//查询当前用户所购买的专业
			log.info("-------------------------userId--->" + userId);
			log.info("buySubjects---------------->" + buySubjects.size());
		}catch (Exception e) {
			log.error("***查询用户已购买课程-异常-拦截器***", e);
			throw e;
		}
		
		// 需要操作的关系映射
		List<CusUserDis> himCudList = new ArrayList<CusUserDis>();
		// 根据购买的专业需要加入的所有小组
		List<Discussion> himDis = new ArrayList<Discussion>();

		// 小组信息
		try {

			// ~ 已购买专业
			if (buySubjects != null && buySubjects.size() > 0) {
				log.info("in--buySubjects");
				
				try{
					this.setSubjectDTO(buySubjects.get(0));// 首页
					// 第一个专业ID
					subjectId = buySubjects.get(0).getSubjectId();
				}catch (Exception e) {
					log.error("取得已购买专业，取得默认第一个专业项出错-索引", e);
					throw e;
				}

				// 查询所有小组信息
				disAll = this.getDiscussionService().getAllDiscussion();
				log.info("查询所有小组信息");
				
				// 取到用户需要加入的所有小组
				for (SubjectDTO dto : buySubjects) {
					for (Discussion dis : disAll) {
						if (dto.getSubjectId().intValue() == dis.getSubjectId()) {
							himDis.add(dis);//当前用户所拥有的群组
						}
					} 	
				}
				
				
				// 第一个购买的权限组
				try{
					if(himDis != null && himDis.size() > 0){
						disId = himDis.get(0).getId();	
					}
				}catch (Exception e) {
					log.error("获取购买权限组排在第一个购买组-取值出错", e);
					throw e;
				}
				
				// log.info("one buy dis" + this.disId);

				// 取现在用户的小组映射关系表
				// cusUserDisList =
				// this.cusUserDisService.findCusUserDisByCusId(userId);
				log.info("get cus UserDisList " + cusUserDisList.size());
				if (userDisMap != null && userDisMap.size() > 0) {

					// 用户应该加入的小组，关系映射
					List<CusUserDis> tempCusList = new ArrayList<CusUserDis>();
					log.info("in to temp_cus_list");
					// 根据用户购买的专业判断：
					// 1.用户是否已加入该专业下的小组
					// 2.用户是否已有权限
					// 3.将没有权限的加入待更新
					for (Discussion dis : himDis) {
						CusUserDis cTemp = new CusUserDis();
						cTemp.setAuth(0);
						cTemp.setCusId(userId);
						cTemp.setDisId(dis.getId());
						tempCusList.add(cTemp);
					}

					
					//cusUserDisList已经存在的群组
					
					// 判断现有映射的权限
					for (CusUserDis c : tempCusList) {
						if(!userDisMap.containsKey(c.getDisId())) {
								//添加
								c.setCusId(c.getCusId());
								c.setAuth(1);
								c.setDisId(c.getDisId());
								himCudList.add(c);// 待添加更新的关系List
						}else{//更新
							if (c.getAuth().equals(0)) {
								c.setId(userDisMap.get(c.getDisId()).getId());
								c.setAuth(1);
								himCudList.add(c);// 待添加更新的关系List
							}
						}
						
//						for (CusUserDis  cNow : cusUserDisList) {
//							if (c.getDisId().equals(cNow.getDisId())) {
//								if (c.getAuth().equals(0)) {
//									c.setId(cNow.getId());
//									c.setCusId(cNow.getCusId());
//									c.setAuth(1);
//									c.setDisId(cNow.getDisId());
//								}
//							}
//						}
					}

					// 初始或更新小组查询条件
					for (CusUserDis himCud : himCudList) {
						this.cusUserDisService.saveOrUpdate(himCud);
						cusUserDisList.add(himCud);
					}

					actionContext.getSession().put("cus_user_dis_list",cusUserDisList);

					// this.getDiscussion().setIntroduction(StringUtil.htmlspecialchars(this.getDiscussion().getIntroduction()));
					// 当前组信息列表（用于界面展示）后期修改
					// disList = new ArrayList<Discussion>();
					// disList.add(this.getDiscussion());
					// this.setDisList(disList);
					// 将组信息设置到Session中
					// this.setSession("dis_list", disList);
					actionContext.getSession().put("session_auth", 1);
				} else {// ~将用户与小组的映射添加到数据库中

					// TODO: 为了前期减少不必要的查询，此处为临时解决方案
					if (buySubjects.size() > 1) {
						log.info("购买多个专业业务区域");
						log.info(buySubjects.size() + "--------------------初始化用户列表----循环----------userId->" + userId);
						// 设置小组查询条件
						for (SubjectDTO dto : buySubjects) {
							disId = initCusUserDis(actionContext, dto.getSubjectId(), 1, userId);
						}
					} else {
						log.info("购买一个专业业务区域");
						log.info(buySubjects.size() + "--------------------初始化用户列表----单个----------userId->" + userId);
						disId = initCusUserDis(actionContext, subjectId, 1,userId);
					}
				}
			} else {/* 用户未购买课程，使用默认专业 */
				
				/**
				 * 这里临时注释，目前业务是：购买过课程进入讨论小组，否则进入 尚未购买课程提示页面
				 */
				/**
				if (cusUserDisList.size() == 0) {
					disId = initCusUserDis(actionContext, subjectId, 0, userId);
				} else {
					disId = cusUserDisList.get(0).getDisId();
					actionContext.getSession().put("cus_user_dis_list", cusUserDisList);
					//本业务属于未购买课程范围，所以设置为0
					actionContext.getSession().put("session_auth", 0);
				}
				*/
			}

			// 取到当前专业下的组列表
			disList = discussionService.getDisBySubjectId(subjectId);
			// log.info("get dis list ...");
			
			actionContext.getSession().put("dis_list", disList);
			
			// 根据小组ID初始化首页数据信息
			if(disId != null){
				initInfo(actionContext,disId);
				// 放入Session中，当点击进入小组首页时
				actionContext.getSession().put("session_disId", String.valueOf(disId));
			}else{
				log.info("小组拦截器，小组disId取值为空，这里会导致无法初始化小组基本信息");
			}
			
		} catch (Exception e) {
			log.error("跳转到讨论小组错误", e);
			
			throw e;
		}

		// 当前用户权限值
		// int auth = Utils.topicRoleVerify(userId,(List<CusUserDis>)getSession("cus_user_dis_list"));
		// setSession("session_auth", auth);
		log.info("dis****************************************************************end");
		log.info("小组首页，拦截器执行完毕，进入下一流程!");
	}
	
	/**
	 * 初始化小组对象
	 * @param actionContext
	 * @param disId
	 */
	private void initInfo(ActionContext actionContext,int disId) throws Exception {
		try{
			actionContext.getSession().put("dis_info", this.discussionService.getDiscussionById(disId));
		}catch (Exception e) {
			log.error("在小组拦截器-初始化小组对象-错误", e);
			throw e;
		}
	}

	/**
	 * 初始化 CusUserDis数据
	 */
	private Integer initCusUserDis(ActionContext actionContext, int subjectId,
			int auth, int userId) {
		int disId = -1;
		
		actionContext.getSession().put("session_auth", auth);
		// 根据专业查询组
		// this.getQueryDiscussionCondition().setSubjectid(subjectId);
		// 取到当前专业下的组列表
		List<Discussion> dList = discussionService.getDisBySubjectId(subjectId);

		// 遍历当前专业组，将用户关系添加
		for (Discussion d : dList) {
			// 如果d.getStatus()的值为1（被禁止） 为0（开启）
			if(d.getStatus() == 0){
				// 将用户加入到话题组中
				cusUserDis = new CusUserDis();
				cusUserDis.setCusId(userId);
				cusUserDis.setDisId(d.getId());
				cusUserDis.setAuth(auth);

				this.cusUserDisService.addCusUserDis(cusUserDis);

				// 更新小组用户数据
				d.setMember(d.getMember() + 1);
				this.discussionService.updateDiscussion(d);
				cusUserDisList.add(cusUserDis);
			}
			
		}
		
		actionContext.getSession().put("cus_user_dis_list", cusUserDisList);
		if(cusUserDisList != null && cusUserDisList.size() > 0){
			disId = cusUserDisList.get(0).getDisId();
		}
		return disId;
	}

	/**
	 * 获取登陆用户的id
	 * 
	 * @return
	 */
	protected int getLoginUserId(HttpServletRequest request) {
		String ukey = CookieHandler.getCookieValueByName(request,
				LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);
		if (ukey == null || ukey.trim().equals("")) {
			return 0;
		} else {
			return Integer.valueOf(ukey.split(",")[0]);
		}
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

	public CusUserDis getCusUserDis() {
		return cusUserDis;
	}

	public void setCusUserDis(CusUserDis cusUserDis) {
		this.cusUserDis = cusUserDis;
	}

	public List<CusUserDis> getCusUserDisList() {
		return cusUserDisList;
	}

	public void setCusUserDisList(List<CusUserDis> cusUserDisList) {
		this.cusUserDisList = cusUserDisList;
	}

	public ICusUserDis getCusUserDisService() {
		return cusUserDisService;
	}

	public void setCusUserDisService(ICusUserDis cusUserDisService) {
		this.cusUserDisService = cusUserDisService;
	}

	public List<SubjectDTO> getBuySubjects() {
		return buySubjects;
	}

	public void setBuySubjects(List<SubjectDTO> buySubjects) {
		this.buySubjects = buySubjects;
	}

	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}
	public List<Discussion> getDisList() {
		return disList;
	}

	public void setDisList(List<Discussion> disList) {
		this.disList = disList;
	}

	public List<Discussion> getDisAll() {
		return disAll;
	}

	public void setDisAll(List<Discussion> disAll) {
		this.disAll = disAll;
	}

	public IDiscussion getDiscussionService() {
		return discussionService;
	}

	public void setDiscussionService(IDiscussion discussionService) {
		this.discussionService = discussionService;
	}
}
