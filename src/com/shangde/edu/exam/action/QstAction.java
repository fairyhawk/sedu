/**
 * @author 何海强
 * @time 2011-05-12
 */
package com.shangde.edu.exam.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.Result;
import com.shangde.common.util.StringUtil;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.exam.condition.QueryQstCondition;
import com.shangde.edu.exam.condition.QueryQstmiddleCondition;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.domain.QstKb;
import com.shangde.edu.exam.domain.Qstmiddle;
import com.shangde.edu.exam.service.IOptions;
import com.shangde.edu.exam.service.IQst;
import com.shangde.edu.exam.service.IQstKb;
import com.shangde.edu.exam.service.IQstmiddle;
import com.shangde.edu.kb.condition.QueryProfessionalCondition;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.service.IProfessional;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;

public class QstAction extends CommonAction {
	
	/**
	 * 试题实体
	 */
	private Qst qst;

	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	
	/**
	 * 学科集合
	 */
	private List<Subject> subjectList = new ArrayList<Subject>();
	
	/**
	 * 考试难度系数
	 */
	private Map<Integer,String> examlevel = new HashMap<Integer, String>();
	
	/**
	 * 配置
	 */
	private ConfigService configurator;
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * �课程分类集合����б�
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 声名知识结构服务
	 */
	private IProfessional professionalService;
	
	/**
	 * 知识库专业表
	 */
	private List<Professional> professionalList=new ArrayList<Professional>();

	/**
	 * 试题服务
	 * @return
	 */
	private IQst qstService ;
	
	/**
	 * author:hhq
	 * 知识库和试题服务
	 */
	private IQstKb qstKbService;
	/**
	 * 选项服务
	 */
	private IOptions optionsService;
	
	/**
	 *查询 条件
	 */
	private QueryQstCondition queryQstCondition;
	/**
	 * 试题id
	 */
	private int qstId;
	
	/**
	 * 试题类型
	 */
	private int type;
	/**
	 * 试题中间表service
	 */
	private IQstmiddle qstmiddleService;
	/**
	 * Qstmiddle对象列表
	 */
	private List<Qstmiddle> qstmiddleList=new ArrayList<Qstmiddle>();;
	/**
	 * 试题中间表查询条件
	 */
	private QueryQstmiddleCondition queryQstmiddleCondition;
	/**
	 * 关键字
	 */
	private String searchKey;
	/**
	 * 查询条件
	 */
	private int tiaojian;
	/**
	 * 试题类型
	 */
	private int qstType;
	
	private Date startTime;
    private Date endTime;
    
    private int isNuber;
	
	private List chkIdList=new ArrayList();
	private List orderList=new ArrayList();
 /*方法*/
	
	
	/**
	 * 材料分析，通过id查询子题
	 */
	public String ziqst(){
		try {
			this.getQueryQstCondition().setQstindexId(qstId);
			List<Qst> qstlist=qstService.getQstList(this.getQueryQstCondition());
			StringBuffer buf = new StringBuffer();
			for(int i=0;i<qstlist.size();i++){
				buf.append(qstlist.get(i).getQstId()+",");
			}
			String qstid=buf.toString();
			this.setResult(new Result<String>(true,qstid+"",null,null));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "qstjson";
	}
	/**
	 * 查询试题id的内容
	 * @return json
	 */
	public String getQstContent(){
		try {
			if(qstService.getQstById(qstId)!=null){
			this.setResult(new Result(true,qstService.getQstById(qstId).getQstContent(),String.valueOf(qstService.getQstById(qstId).getQstType()),null));
			}else{
				this.setResult(new Result(true,"false",null,null));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 考试题删除方法
	 */
	public String Qstdel(){
		
		
		
		try {
			optionsService.delOptionsById(qstId);
			qstService.delQstById(qstId);
			this.setResult(new Result(true,"1",null,null));
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.setResult(new Result(true,"0",null,null));
			return ERROR;
		}
		return "json";
	}
	
	
	/**
	 *转向材料分析试题添加页面
	 */	
	public String toQstcailiaoAdd(){
		
		try {
			qst=qstService.getQstById(qstId);
			
			//过滤HTML标签  截取题干长度30
			for (Qst ziti : qst.getQstziti()) {
				String qstContent = StringUtil.filterHTML(ziti.getQstContent());
				qstContent = StringUtil.chop(qstContent, 30, "...");
				ziti.setQstContent(qstContent);				
			}
			
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			examlevel = configurator.getExamlevel();
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
			professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ERROR;
		}
		return "toQstcailiaoAdd";
	}
	
	/**
	  * 
	  * 材料分析试题添加页面
	  * @return
	  */
	 public String QstcailiaoAdd(){
		 
		 
		 try {
			 
			 int qst_Id =qstaddMethodsziti(qst.getQstType());
			 if(qst_Id!=-1)
			 {
				 optionMethods(qst.getQstType(),qst_Id);
				String zhishidianid=servletRequest.getParameter("zkId");
				
				if(!zhishidianid.endsWith(""))
				{
					String zhishiId [] =zhishidianid.split(",");
					for(int a=0;a<zhishiId.length; a++)
					{
						QstKb qb=new QstKb();
						qb.setKId(new Integer(zhishiId[a]).intValue());
						qb.setQstId(qst_Id);
						qstKbService.addQstKb(qb);
					}
				
				}
				qst=qstService.getQstById(qst.getQstId());
				subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
				examlevel = configurator.getExamlevel();
				courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
				professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
				 return "toQstcailiaoAdd";
			 }
			 
			 return "ERROR";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
	 }
	 
	 

	 /**
	  * 往试题表中添加数据
	  * @param qst_type
	  */
	 public  int qstaddMethodsziti(int qst_type)
	 {
	 	int qstId=0;
		try {
			Qst question = new Qst();
			question.setEpId(37);//添加试题--------------------------------------------
			question.setScore(0);
			question.setQstindexId(qst.getQstId());
			question.setQstContent(qst.getQstContent().replaceAll("\r\n", "<br>"));
			question.setWrongJude(qst.getWrongJude());
			question.setQstType(qst.getQstType());
			question.setQsttype(qst.getQsttype());
			question.setKorcouId(qst.getKorcouId());
			question.setCourseId(qst.getCourseId());
			question.setNote(qst.getNote());
			 if(qst_type == 1){//客观题时，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}else if(qst_type == 2){//多选题，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}
				else if(qst_type == 4){//多选题，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}
				else if(qst_type == 5){//多选题，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}
				else if(qst_type == 3){//判断，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}else if(qst_type == 6){
					question.setIsAsr("main");
				}
				question.setQstType(qst_type);//试题的类型（单选，主观）
				
				question.setCtPerson("A");
				question.setLevel(qst.getLevel());
				User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
				question.setAuthor(users.getLoginName());
				question.setLastEditTime(new Date());
				 qstId = qstService.addQst(question);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return -1;
		}
			 
			 return qstId;
		 
	 }
	 
	
	/**
	 * 转向更新页面
	 */
	public String toQstUpdate(){
		try {
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			examlevel = configurator.getExamlevel();
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
			professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
			qst=qstService.getQstById(qstId);
			this.getQueryQstmiddleCondition().setQstId(qstId);
			qstmiddleList=qstmiddleService.getQstmiddleList(this.getQueryQstmiddleCondition());
			chkIdList.add("Aasr");
			chkIdList.add("Basr");
			chkIdList.add("Casr");
			chkIdList.add("Dasr");
			chkIdList.add("Easr");
			chkIdList.add("Fasr");
			chkIdList.add("Gasr");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "toQstUpdate";
	}
	
 /**
  * 试题的更新
  */
	public String QstUpdate(){
		try {
			String al[]=servletRequest.getParameterValues("asr");
			Qst quest = qstService.getQstById(qst.getQstId());
			quest.setCourseId(qst.getCourseId());
			quest.setKorcouId(qst.getKorcouId());
			String aasr[]=qst.getIsAsr().split(",");
			StringBuffer buf = new StringBuffer();
			for(int i=0;i<aasr.length;i++){
				buf.append(aasr[i].trim());
			}
			quest.setIsAsr(buf.toString());
			quest.setLevel(qst.getLevel());
			quest.setNote(qst.getNote());
			quest.setQstContent(qst.getQstContent());
			quest.setQsttype(qst.getQsttype());
			quest.setQstType(qst.getQstType());
			quest.setWrongJude(qst.getWrongJude());
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			quest.setAuthor(users.getLoginName());
			quest.setLastEditTime(new Date());
			qstService.updateQst(quest);
			List<Options> options = quest.getOptions();
			for(int i=0;i<options.size();i++){
				Options option = options.get(i);
				option.setOptContent(al[i].replaceAll("\r\n", "<br>"));
				optionsService.updateOptions(option);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ERROR;
		}
		return "QstUpdate";
	}
	
/**
 * 试题全部列表
 */

 public String  QstList(){
	 
	 
		try {
			String res=this.getQueryQstCondition().getSearchKey();
			
			if(res!=null){
			this.getQueryQstCondition().setSearchKey(URLDecoder.decode(getQueryQstCondition().getSearchKey().trim(), "UTF-8"));
			}
			this.getQueryQstCondition().setPageSize(20);
			setPage(qstService.getQstListPageAllqst(this.getQueryQstCondition()));
			this.getPage().setPageSize(20);
			setPageUrlParms();
			
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	 
	 return "Qstlist";
 }
 
 /**
  * 试题全部列表弹窗
  */

 /**
  * 转向添加试题页面
 * @throws UnsupportedEncodingException 
  */
 public String  QstListtanchuan() {
	 
	 
		try {
			this.getQueryQstCondition().setQstType(type);
			this.getQueryQstCondition().setSearchKey(searchKey);
			//查询所有的课程；
			courseSortList = qstService.getCourseList();
			this.getQueryQstCondition().setPageSize(10);
			setPage(qstService.getQstListPageAll(this.getQueryQstCondition()));
			this.getPage().setPageSize(10);
			setPageUrlParms();
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	 
	 return "Qstlisttanchuan";
}
 
 public String toQstadd(){
	 
	 	try {
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			examlevel = configurator.getExamlevel();
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
			professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
		
			 if(ActionContext.getContext().getSession().get("qstIdd")!=null){
				 isNuber=1;
				 int q=Integer.parseInt(ActionContext.getContext().getSession().get("qstIdd").toString());
				 qst=qstService.getQstById(q);
			 }else{
				 isNuber=0;
			 }
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ERROR;
		}
	 return "toqstadd";
 }

 /**
  * 
  * 试题的添加方法
  * @return
  */
 public String QstAdd(){
	 
	 
	 try {
		 
		 int qstId =qstaddMethods(qst.getQstType());
		 ActionContext.getContext().getSession().remove("qstIdd");
		 ActionContext.getContext().getSession().put("qstIdd", qstId);
		 if(qstId!=-1)
		 {
			 optionMethods(qst.getQstType(),qstId);
			String zhishidianid=servletRequest.getParameter("zkId");
			
			if(!zhishidianid.endsWith(""))
			{
				String zhishiId [] =zhishidianid.split(",");
				for(int a=0;a<zhishiId.length; a++)
				{
					QstKb qb=new QstKb();
					qb.setKId(new Integer(zhishiId[a]).intValue());
					qb.setQstId(qstId);
					qstKbService.addQstKb(qb);
				}
			
			}
			 return "QstAdd";
		 }
		 
		 return "ERROR";
	} catch (RuntimeException e) {
		e.printStackTrace();
		return ERROR;
	}
	
 }
 
 /**
  * 往试题表中添加数据
  * @param qst_type
  */
 public  int qstaddMethods(int qst_type)
 {
 	int qstId=0;
	try {
		Qst question = new Qst();
		question.setEpId(37);//添加试题--------------------------------------------
		question.setScore(0);
		question.setQstContent(qst.getQstContent().replaceAll("\r\n", "<br>"));
		question.setWrongJude(qst.getWrongJude());
		question.setQstType(qst.getQstType());
		question.setQsttype(qst.getQsttype());
		question.setKorcouId(qst.getKorcouId());
		question.setCourseId(qst.getCourseId());
		question.setSortId(qst.getSortId());
		question.setNote(qst.getNote());
		 if(qst_type == 1){//客观题时，答案存储于选项表中
				question.setIsAsr(qst.getIsAsr());
			}else if(qst_type == 2){//多选题，答案存储于选项表中

				String aasr[]=qst.getIsAsr().split(",");
				StringBuffer buf = new StringBuffer();
				for(int i=0;i<aasr.length;i++){
					buf.append(aasr[i].trim());
				}
				String a = buf.toString();
				question.setIsAsr(a);
			}
			else if(qst_type == 4){//多选题，答案存储于选项表中

				question.setIsAsr(qst.getIsAsr());
			}
			else if(qst_type == 5){//多选题，答案存储于选项表中

				String aasr[]=qst.getIsAsr().split(",");
				StringBuffer buf = new StringBuffer();
				for(int i=0;i<aasr.length;i++){
					buf.append(aasr[i].trim());
				}
				String a=buf.toString();
				question.setIsAsr(a);
			}
			else if(qst_type == 3){//判断，答案存储于选项表中
				question.setIsAsr(qst.getIsAsr());
			}else if(qst_type == 6){
				question.setIsAsr("main");
			}
			question.setQstType(qst_type);//试题的类型（单选，主观）
			
			question.setCtPerson("A");
			question.setLevel(qst.getLevel());
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			question.setAuthor(users.getLoginName());
			question.setLastEditTime(new Date());
			 qstId = qstService.addQst(question);
	} catch (RuntimeException e) {
		e.printStackTrace();
		return -1;
	}
		 return qstId;
 }
 
 /**
  * 选项表录入数据
  * @param qst_type
  * @param qstId
  */
 public void optionMethods(int qst_type ,int qstId)
 { 
	 orderList.add("A");
	 orderList.add("B");
	 orderList.add("C");
	 orderList.add("D");
	 orderList.add("E");
	 orderList.add("F");
	 orderList.add("G");
	 try {
		Options option = null;//选项
		 String tempid="asr";
		 if(qst_type == 1){//单选题
			 
			 String[] al=servletRequest.getParameterValues("asr");
				
				for(int i=0;i<4;i++){
					option = new Options();//添加选项---------------------------------
					option.setQstId(qstId);
					option.setOptContent(al[i].replaceAll("\r\n", "<br>"));
					option.setOptOrder(orderList.get(i).toString());
					optionsService.addOptions(option);
				}
				
			}else if(qst_type == 2){//多选题
				String j=servletRequest.getParameter("duoxuanNumber");
				String[] al=servletRequest.getParameterValues("asr");
					
					for(int i=0;i<Integer.parseInt(j);i++){
						option = new Options();//添加选项---------------------------------
						option.setQstId(qstId);
						option.setOptContent(al[i].replaceAll("\r\n", "<br>"));
						option.setOptOrder(orderList.get(i).toString());
						optionsService.addOptions(option);
					}
				
				
			}else if(qst_type == 4){//材料分析题
				option = new Options();//添加选项---------------------------------
				option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
				option.setQstId(qstId);
				option.setOptOrder("A");
				optionsService.addOptions(option);
				
			}
			else if(qst_type == 5){//图表题
				String j=servletRequest.getParameter("tubiaoNumber");
				String[] al=servletRequest.getParameterValues("asr");
					
					for(int i=0;i<Integer.parseInt(j);i++){
						option = new Options();//添加选项---------------------------------
						option.setQstId(qstId);
						option.setOptContent(al[i].replaceAll("\r\n", "<br>"));
						option.setOptOrder(orderList.get(i).toString());
						optionsService.addOptions(option);
					}
				
			}else if(qst_type == 3){//判断题
				String[] al=servletRequest.getParameterValues("asr");
				option = new Options();//添加选项---------------------------------
				option.setOptContent(al[0].replaceAll("\r\n", "<br>"));
				option.setQstId(qstId);
				option.setOptOrder("A");
				optionsService.addOptions(option);
				
				option = new Options();
				option.setOptContent(al[1].replaceAll("\r\n", "<br>"));
				option.setQstId(qstId);
				option.setOptOrder("B");
				optionsService.addOptions(option);
				
				
			}else if(qst_type == 6){//主观题
				option = new Options();
				option.setOptContent(servletRequest.getParameter("zhuguanContent").replaceAll("\r\n", "<br>"));
				option.setQstId(qstId);
				option.setOptOrder("main");
				optionsService.addOptions(option);
			}
	} catch (RuntimeException e) {
		e.printStackTrace();
		
	} 
	 
 }
 
public Qst getQst() {
	return qst;
}

public void setQst(Qst qst) {
	this.qst = qst;
}

public ISubject getSubjectService() {
	return subjectService;
}

public void setSubjectService(ISubject subjectService) {
	this.subjectService = subjectService;
}

public List<Subject> getSubjectList() {
	return subjectList;
}

public void setSubjectList(List<Subject> subjectList) {
	this.subjectList = subjectList;
}

public Map<Integer, String> getExamlevel() {
	return examlevel;
}

public void setExamlevel(Map<Integer, String> examlevel) {
	this.examlevel = examlevel;
}

public ConfigService getConfigurator() {
	return configurator;
}

public void setConfigurator(ConfigService configurator) {
	this.configurator = configurator;
}

public ICoursesort getCoursesortService() {
	return coursesortService;
}

public void setCoursesortService(ICoursesort coursesortService) {
	this.coursesortService = coursesortService;
}

public List<Coursesort> getCourseSortList() {
	return courseSortList;
}

public void setCourseSortList(List<Coursesort> courseSortList) {
	this.courseSortList = courseSortList;
}

public IProfessional getProfessionalService() {
	return professionalService;
}

public void setProfessionalService(IProfessional professionalService) {
	this.professionalService = professionalService;
}

public List<Professional> getProfessionalList() {
	return professionalList;
}

public void setProfessionalList(List<Professional> professionalList) {
	this.professionalList = professionalList;
}


public IQst getQstService() {
	return qstService;
}


public void setQstService(IQst qstService) {
	this.qstService = qstService;
}


public IQstKb getQstKbService() {
	return qstKbService;
}


public void setQstKbService(IQstKb qstKbService) {
	this.qstKbService = qstKbService;
}

public IOptions getOptionsService() {
	return optionsService;
}

public void setOptionsService(IOptions optionsService) {
	this.optionsService = optionsService;
}

public QueryQstCondition getQueryQstCondition() {
	if(queryQstCondition==null){
		queryQstCondition = new QueryQstCondition();
	}
	return queryQstCondition;
}

public void setQueryQstCondition(QueryQstCondition queryQstCondition) {
	this.queryQstCondition = queryQstCondition;
}

public int getQstId() {
	return qstId;
}

public void setQstId(int qstId) {
	this.qstId = qstId;
}

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}


public IQstmiddle getQstmiddleService() {
	return qstmiddleService;
}


public void setQstmiddleService(IQstmiddle qstmiddleService) {
	this.qstmiddleService = qstmiddleService;
}


public List<Qstmiddle> getQstmiddleList() {
	if(qstmiddleList==null){
		qstmiddleList=new ArrayList<Qstmiddle>();
	}
	return qstmiddleList;
}


public void setQstmiddleList(List<Qstmiddle> qstmiddleList) {
	this.qstmiddleList = qstmiddleList;
}


public QueryQstmiddleCondition getQueryQstmiddleCondition() {
	if(queryQstmiddleCondition==null){
		queryQstmiddleCondition=new QueryQstmiddleCondition();
	}
	return queryQstmiddleCondition;
}


public void setQueryQstmiddleCondition(
		QueryQstmiddleCondition queryQstmiddleCondition) {
	this.queryQstmiddleCondition = queryQstmiddleCondition;
}
public String getSearchKey() {
	return searchKey;
}
public void setSearchKey(String searchKey) {
	this.searchKey = searchKey;
}
public List getChkIdList() {
	return chkIdList;
}
public void setChkIdList(List chkIdList) {
	this.chkIdList = chkIdList;
}
public int getTiaojian() {
	return tiaojian;
}
public void setTiaojian(int tiaojian) {
	this.tiaojian = tiaojian;
}
public int getQstType() {
	return qstType;
}
public void setQstType(int qstType) {
	this.qstType = qstType;
}
public Date getStartTime() {
	return startTime;
}
public void setStartTime(Date startTime) {
	this.startTime = startTime;
}
public Date getEndTime() {
	return endTime;
}
public void setEndTime(Date endTime) {
	this.endTime = endTime;
}
public List getOrderList() {
	return orderList;
}
public void setOrderList(List orderList) {
	this.orderList = orderList;
}
public int getIsNuber() {
	return isNuber;
}
public void setIsNuber(int isNuber) {
	this.isNuber = isNuber;
}
}
