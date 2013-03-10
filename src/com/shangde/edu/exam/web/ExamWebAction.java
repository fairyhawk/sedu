package com.shangde.edu.exam.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FunctionUtil;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.exam.condition.GetQSTEXAMKB;
import com.shangde.edu.exam.condition.QueryExampaperCondition;
import com.shangde.edu.exam.condition.QueryExampaperRecordCondition;
import com.shangde.edu.exam.condition.QueryOptRecordCondition;
import com.shangde.edu.exam.condition.QueryFavoritesCondition;
import com.shangde.edu.exam.condition.QueryQstCondition;
import com.shangde.edu.exam.condition.QueryQstConditionBFQ;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.ExampaperRecord;
import com.shangde.edu.exam.domain.FavoritesPaper;
import com.shangde.edu.exam.domain.FavoritesQuestion;
import com.shangde.edu.exam.domain.OptRecord;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.domain.Reviews;
import com.shangde.edu.exam.dto.ExamAnalysisDTO;
import com.shangde.edu.exam.dto.ExampaperCountLastDay;
import com.shangde.edu.exam.dto.ExampaperQstDTO;
import com.shangde.edu.exam.dto.ExampaperSubjectId;
import com.shangde.edu.exam.dto.QstRightPercent;
import com.shangde.edu.exam.dto.SubjectExampaperNumDTO;
import com.shangde.edu.exam.dto.UserExamPaperDTO;
import com.shangde.edu.exam.dto.UserQst;
import com.shangde.edu.exam.service.IExampaper;
import com.shangde.edu.exam.service.IExampaperRecord;
import com.shangde.edu.exam.service.IOptRecord;
import com.shangde.edu.exam.service.IOptions;
import com.shangde.edu.exam.service.IQst;
import com.shangde.edu.exam.service.IQstKb;
import com.shangde.edu.exam.service.IQstPic;
import com.shangde.edu.exam.service.IReviews;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.kb.domain.Knowledge;
import com.shangde.edu.kb.service.IKnowledge;
import com.shangde.edu.kno.condition.QuerySysNodeCondition;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.service.ISysNode;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.res.domain.VedioCount;
import com.shangde.edu.res.service.IVedioCount;
import com.shangde.edu.stu.service.IPlanclock;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.IGrade;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.service.ITaskCus;

/**
 * 考试WebAction
 * 
 * @author chenshuai wanglei
 */

public class ExamWebAction extends CommonAction implements Serializable {
	
	private static final Logger logger = Logger.getLogger(ExamWebAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private int favoritesPaperCount;
	
	private int favoritesQstCount;
	
	private int myPaperCount;
	
	private int wrongQstCount;
	
	private int myQstCount;
	
	public int getMyPaperCount() {
		return myPaperCount;
	}
	public void setMyPaperCount(int myPaperCount) {
		this.myPaperCount = myPaperCount;
	}
	public int getWrongQstCount() {
		return wrongQstCount;
	}
	public void setWrongQstCount(int wrongQstCount) {
		this.wrongQstCount = wrongQstCount;
	}
	public int getFavoritesPaperCount() {
		return favoritesPaperCount;
	}
	public void setFavoritesPaperCount(int favoritesPaperCount) {
		this.favoritesPaperCount = favoritesPaperCount;
	}
	public int getFavoritesQstCount() {
		return favoritesQstCount;
	}
	public void setFavoritesQstCount(int favoritesQstCount) {
		this.favoritesQstCount = favoritesQstCount;
	}
	/*
	 * 考试中心-查询知识点
	 */
	private ISysNode sysNodeService;
	private QuerySysNodeCondition querySysNodeCondition;
	private List<SysNode> sysNodeList;
	private QueryOptRecordCondition queryOptRecordCondition;
	private String lastQstId;
	private String resultCur;
	private List<OptRecord> errorQstTop3;
	private List<FavoritesPaper> favoritesPapers;
	public List<FavoritesPaper> getFavoritesPapers() {
		return favoritesPapers;
	}
	public void setFavoritesPapers(List<FavoritesPaper> favoritesPapers) {
		this.favoritesPapers = favoritesPapers;
	}
	/**
	 * UserMsg实体类
	 */
	private UserMsg userMsg;
	
	/**
	 * 统计试卷数量
	 */
	private  int counts;
	/**
	 * 计划提醒Service层
	 */
	private IPlanclock planclockService;
	
	/**
	 * Message实体类
	 */
	private Message message;
	/**
	 * 知识库服务
	 */
	private IKnowledge knowledgeService;
	 
	/**
	 * 试卷最大分
	 */
	private float maxfen;
	/**
	 * 试卷最小分
	 */
	private float minfen;
	/**
	 * 试卷平均分
	 */
	private float avgfen;
	/**
	 * 试卷是否已做
	 */
	private int isDone;
	/**
	 * author:hhq
	 * 知识库和试题服务
	 */
	private IQstKb qstKbService;
	
	/**
	 * author:hhq
	 * 根据考题id查询当前试卷中集合的知识点
	 */
	private int shuliang;
	
	private List<Knowledge> qblist=new ArrayList<Knowledge>();

	/**
	 * 正确率
	 */
	private float lv;
	/**
	 * 考试��ӿ�
	 */
	private Exampaper exam;
	private Course course;
	/**
	 * 考试服务
	 */
	private IExampaper exampaperService;

	private IExampaperRecord exampaperRecordService;

	private ExamAnalysisDTO examAnalysisDTO;

	private QueryExampaperRecordCondition queryExampaperRecordCondition;

	/**
	 * 总评服务
	 */
	private IReviews reviewsService;

	/**
	 * 试题服务
	 */
	private IQst qstService;

	/**
	 * 选项服务
	 */
	private IOptions optionsService;
	
	/**
	 * 流水服务
	 */
	private ICashRecord cashRecordService;

	/**
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;

	/**
	 * �考试查询条件
	 */
	private QueryExampaperCondition queryExampaperCondition;
	
	/**
	 * 收藏试卷或试题条件
	 */
	private QueryFavoritesCondition queryFavoritesCondition;

	/**
	 * 试卷集合
	 */
	private List<Exampaper> exampaperList = new ArrayList<Exampaper>();

	/**
	 * 年级服务
	 */
	private IGrade gradeService;

	/**
	 * 学科服务
	 */
	private ISubject subjectService;

	/**
	 * 试题图片服务
	 */
	private IQstPic qstPicService;

	/**
	 * 年份集合
	 */
	private List<Grade> gradeList = new ArrayList<Grade>();

	/**
	 * 学科集合
	 */
	private List<Subject> subjectList = new ArrayList<Subject>();
	private List<SubjectExampaperNumDTO> subjectExampaperNum=new ArrayList<SubjectExampaperNumDTO>();

	/**
	 * 考试难度系数
	 */
	private Map<Integer, String> examlevel = new HashMap<Integer, String>();

	/**
	 * 查询条件
	 */
	private QueryQstCondition queryQstCondition;
	
	private ISellWay sellWayService;
	
	private String flag;
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	private int epid;
	private int subjectIdweb;
	private List<Qst> qstPaper;

	private String subRlt;// 获取前台过来的答案

	private float totalScroe = 0;// 总得分

	private String scoreJudgment;

	private List<Reviews> reviewsList;

	
	private List<GetQSTEXAMKB> getlist =new ArrayList<GetQSTEXAMKB>();
	
	private Map studyTypePram;
	
	private int subjectId;
	private String usetime;// 考试用时
	private int kpoint;
	/**
	 * 试题IDS
	 */
	private String qstIds;

	private String zongping1;// 总评
	private String zongping2;
	private String zongping3;
	private String zongping4;
	private String zongping5;
	private String zongping6;
	private String zongping7;
	private String zongping8;
	private String zongping9;
	private int zongping1Id;
	private int zongping2Id;
	private int zongping3Id;
	private int zongping4Id;
	private int zongping5Id;
	private int zongping6Id;
	private int zongping7Id;
	private int zongping8Id;
	private int zongping9Id;
	private int epids;
	private int weida;//未回答的试题个数
	private int result_type;
	/**
	 * 查询是否有没有试卷
	 * 提示没有试卷信息
	 */
	
	private String errorinfo;
	/**
	 * �课程分类集合����б�
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 用户考试集合
	 */
	private List<ExampaperRecord> exampaperRecordList;

	private List<Course> courseList;
	
	private List<Coursesort> coursesortList;
 
	/**
	 * 试卷IDs 冻结时用到
	 */
	private int[] exampaperIds;

	/**
	 * 试题IDs
	 */
	private int[] qst;
	private IOptRecord optRecordService;// 答案记录
	
	/**
	 * 用户试卷记录
	 */
	private UserExamPaperDTO userExamPaper;
	
	private int userEpId;
	
	private int subjectweb;
	
	/**
	 * 节点
	 */
	private IKpoint kpointService;
	private Kpoint kPonint;
	
	/**
	 * �课程分类服务���Service
	 */
	private ICoursesort coursesortService; 
	
	private List<CourseSortListDTO> courseSortListDTOList;
	
	private ICustomer customerService;
	
	private float accuracy1;
	
	public List<QueryQstConditionBFQ> qstlistall =new  ArrayList<QueryQstConditionBFQ>();
	
	public int renshu;
	
	private ITaskCus taskCusService;
	
	/**
	 * 积分的服务
	 */
	private ITotolsScore totolsScoreService;
	/**
	 * 积分记录的服务
	 */
	private ITsRecord tsRecordService;
	
	/**
	 *用list存储试题 
	 * @author 何海强
	 */
	private List<Qst> qstlist=new ArrayList<Qst>();
	
	
	/**
	 * 课程id
	 */
	private int cusid;
	
	/**
	 * 专业类型
	 */
	private int eptype;
	/**
	 * 前一天更新试卷类
	 */
	private List<ExampaperCountLastDay> exampaperCountLastDay;
	

	
	/**
	 * 获取次数试卷的前10人
	 */
	private List<ExampaperRecord> examrecord;
	
	/**
	 * 获取做过此试卷的人还做过其他试卷
	 * @return
	 */
	private List<Exampaper> examrecordqita;
	
	/**
	 * 用户积分
	 */
	private int jifen;
	/**
	 *查询是否可以考试
	 */
	private int mb;
	
	/**
	 * 流水表条件
	 */
	private CashRecord cashrecord;
	
   private QueryCourseCondition queryCourseCondition;
   
   /**
    * 课程条件
    */
   private QueryCoursesortCondition queryCoursesortCondition;
   private ExampaperRecord exampaperRecord;
   
   /**
    * 考试排名
    */
    private int paiming;
    
    private int userzongjifen;
    
    private QstRightPercent qstRightPercent;
    /*
     * 随机考试
     */
    
	private Qst qstPage;
	private List<Options> options;
	private String pageMessages; 
	private Integer correctNum;
	private Integer correctRate;
	private String startTime;
	private Integer examLength;
	
    
    /** 王超  开始  */
    private List<QstRightPercent> percentList=new ArrayList<QstRightPercent>();
    private List<Qst> errorQstList=new ArrayList<Qst>();
    private List<ExampaperQstDTO> examQstList;
    
    private List<UserQst> qstOptlist;
    
    private int qstId;
    /** 王超  结束  */
   
	//谢添加开始
	private IVedioCount vediocountService; 
	
    private int videoCountStatus;//播放器功能使用统计类型状态
        
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
	/**
     * 我要提问播放器功能统计
     */
	public void vedioCount(){
		try{
		Integer userId= this.getLoginUserId();
	    VedioCount vedioCount=new VedioCount();
		vedioCount.setCountTime(new Date());
		vedioCount.setCusId(userId!=null?userId:0);
		vedioCount.setStatus(videoCountStatus);
		vediocountService.addCount(vedioCount);
		}catch(Exception e){
			logger.error(e.toString());
		}
	}
	//谢添加结束
	/**
	 * @author 何海强
	 * 前台考试中心，发布试卷功能
	 */
	public String FabuExam(){
		String retruns="fabuexam";
		try {
			int userId=getLoginUserId();
			//积分表进行记录  
			TotolsScore totolsScore=this.totolsScoreService.getTotolsScore(userId);
			if(totolsScore!=null){
				int examjifen = exampaperService.getUserjifen(epid);
				int jifen=totolsScore.getTsCurrent()-examjifen;
				if(jifen>=0){
				userzongjifen=totolsScore.getTsCurrent();
				totolsScore.setTsCurrent(jifen);
				this.totolsScoreService.updateTotolsScore(totolsScore);
				retruns="fabuexam";
				}else{
					retruns=getExamPaperInfo();
				}
			}else{
				retruns="fabuexam";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("ExamWebAction.FabuExam",e);
			return ERROR;
		}
		
		return retruns;
	}
	
	/**
	 * @author 何海强
	 * 前台考试中心，发布试卷功能
	 * 未购买
	 
	public String NoFabuExam(){
		
		try {
			exam=exampaperService.getExampaperById(epid);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
		return "nofabuexam";
	}
	*/
	/**
	 * 跳转到开始做试卷页面
	 * @return
	 */
	public String toTestPaper(){
		try{
			//获得当前登录用户ID
			int userId = getLoginUserId();
			this.getQueryExampaperCondition().setUserId(userId);
			this.getQueryExampaperCondition().setEpId(epid);
			exam=exampaperService.getExampaperPaperResult(this.getQueryExampaperCondition());
			examQstList=new ArrayList<ExampaperQstDTO>();
			if(exam==null||exam.getLevel()!=2){
				return	getExamPaperAllList()  ;
			}
			List<ExampaperQstDTO> tempList=new ArrayList<ExampaperQstDTO>();
			//获取试卷所有相关试题和选项，用于循环处理数据至正确的形式
			
			tempList=exampaperService.getExamQstList(this.getQueryExampaperCondition());
			counts=0;//试卷试题数量
			if(tempList!=null&&tempList.size()>0){
				//获取第一个用于比较的试题id和第一个对象
				int qstId=tempList.get(0).getQstId();
				List<ExampaperQstDTO> list=new ArrayList<ExampaperQstDTO>();
				ExampaperQstDTO tempExamQst=tempList.get(0);
				for(int i=0;i<tempList.size();i++){
					
					//试题id相同，表示这是选项信息，添加进入临时选项列表
					//过滤html标签
					tempList.get(i).setOptContent(FunctionUtil.Html2Text(tempList.get(i).getOptContent()));
					if(tempList.get(i).getQstId()==qstId) list.add(tempList.get(i));
					else{
						
						tempExamQst.setQstOptionList(list);//试题id不同，选项已经添加完毕，set进试题里面
						examQstList.add(tempExamQst);//将试题放入最终显示的试题列表
						//统计试卷的试题数量
						if(tempExamQst.getQstType()!=4)counts++;
						else if(tempExamQst.getZiQstType()==4)counts++;
						
						qstId=tempList.get(i).getQstId();//赋值新得到的试题id
						tempExamQst=tempList.get(i);//存入新得到的对象
						list=new ArrayList<ExampaperQstDTO>();//list清空
						list.add(tempExamQst);
						
					}
					if(i==tempList.size()-1){
						tempExamQst.setQstOptionList(list);//试题id不同，选项已经添加完毕，set进试题里面
						examQstList.add(tempExamQst);//将试题放入最终显示的试题列表
						//统计试卷的试题数量
						if(tempExamQst.getQstType()!=4)counts++;
						else if(tempExamQst.getZiQstType()==4)counts++;
					}
				}
			/*	List<QstRightPercent> rplist=this.qstService.getRightPercentByEpId(epid);
				for(int i=0;i<rplist.size();i++){
					if(examQstList.get(i).getQstType()!=4&&examQstList.get(i).getQstType()!=6)
					examQstList.get(i).setPercent(rplist.get(i).getPercent());
				}*/
			}
		}catch(Exception e){
			logger.error("ExamWebAction.toTestPaper",e);
			return ERROR;
		}
		return "toTestPaper";
	}
	
	
	/**
	 * @author 何海强
	 * 是否可以考试
	 * @return 
	 */
	public String iSkaoshi(){
		try{
			int userId=getLoginUserId();
			
			exam=exampaperService.getExampaperById(epid);
			this.getQueryExampaperCondition().setCusId(userId);
			this.getQueryExampaperCondition().setCourseId(exam.getCourseId());
			mb=exampaperService.getiskaoshi(this.getQueryExampaperCondition());
			if(mb>0){
				this.setResult(new Result(false,"1",epid+"",null));
			}else{
				this.setResult(new Result(true,"0",epid+"",null));
			}
		}catch(Exception e){
			logger.error("ExamWebAction.iSkaoshi",e);
			return ERROR;
		}
		return "json";
	}
	
	
	/**
	 * 获取所有试卷
	 * @author 何海强
	 * @return string
	 */

	 public String getExamPaperAllList(){
		
		int userId = getLoginUserId(); //获取用户id
		this.getQueryExampaperCondition().setUserId(userId);
		//查询专业
		subjectExampaperNum=exampaperService.getSubjectExampaperNum(userId);
		if(subjectExampaperNum==null||subjectExampaperNum.size()==0)return "examNone";
		
			try {
				//设置页面专业，如果页面subjectIdweb=空，走用户第一次购买的专业，否则走选中的专业
				if(subjectIdweb==0)
				{
					List<ExampaperSubjectId> esList = this.exampaperService.getSubjectIdListForUnAss(userId);
					int esSize= esList.size();
					if(esSize>0){
						int subid=esList.get(0).getSubjectId();
						subjectIdweb=subid;
					} 
				}				
				this.getQueryExampaperCondition().setSubjectId(subjectIdweb);
				//通过专业查询知识结点点列表
				
				Sys sys=this.sysNodeService.getSysBySubjectId(subjectIdweb);
				if(sys!=null){
					SysNode rootNode=this.sysNodeService.getSysNodeBySysId(sys.getKsId());
					this.getQuerySysNodeCondition().setParentId(rootNode.getKsnId());
					this.sysNodeList=this.sysNodeService.getSysNodeListByCondition(querySysNodeCondition);
									
				}
				
				//专业设置结束
				//getQueryExampaperCondition().setLevel(2);
				getQueryExampaperCondition().setPageSize(7);
				getQueryExampaperCondition().setUserId(userId);
				setPage(exampaperService.getExampaperPage(queryExampaperCondition));
				//courseList=this.exampaperService.getCourseListBySubjectId(queryExampaperCondition);
				myQstCount=this.qstService.getRightPercent(userId);
				//统计我的收藏信息 add by yanghaibo 2012-07-30 17:38
				this.getQueryFavoritesCondition().setType(0);
				this.getQueryFavoritesCondition().setCoeffcient(0);
				this.getQueryFavoritesCondition().setUserId(userId);
				favoritesPaperCount = exampaperRecordService.getMyFavoritesPaperCount(this.getQueryFavoritesCondition());
				favoritesQstCount = exampaperRecordService.getMyFavoritesQuestionCount(this.getQueryFavoritesCondition());
				wrongQstCount = optRecordService.getErrorQstCountBycusId(userId);
				this.getQueryExampaperRecordCondition().setCusId(userId);
				this.getQueryExampaperRecordCondition().setSubject_id(subjectIdweb);
				myPaperCount = exampaperRecordService.getMyPapaerCount(getQueryExampaperRecordCondition());
				//错题记录
				qstPaper = this.qstService.getCurrentErrorQst(subjectIdweb);
				Iterator it = qstPaper.iterator();
				while(it.hasNext()){
					Qst obj = (Qst) it.next();
					obj.setQstContent(FunctionUtil.Html2Text(obj.getQstContent()).replaceAll("&nbsp;", ""));
					if(obj.getQstContent().length() > 32){
						obj.setQstContent(obj.getQstContent().substring(0, 32)+"......");
					}
					errorQstList.add(obj);
				}
				this.getPage().setPageSize(7);
				setPageUrlParms();
				    
				    
			} catch (RuntimeException e) {
				logger.error("ExamWebAction.getExamPaperAllList",e);
				return ERROR;
			}
			
			
		return "getExamPaperAllList";
	} 
	 
	 /**
	  * 添加或移除收藏试卷
	  * @author 杨海波
	  * @return string
	  */
	 
	 public String changeFavoritesStatus(){
		 
		 
		 try{
			 //获得试卷ID
			 int epId = exam.getEpId();
			 //获得当前登录用户ID
			 int userId = getLoginUserId();
			 //添加收藏
			 if(flag != null && flag.equals("1")){
				 //获得被收藏试卷的基本信息
				 exam=exampaperService.getFavoritesExampaperInfo(epId);
				 FavoritesPaper temp = new FavoritesPaper();
				 temp.setCreateDate(new Date());
				 temp.setFlag(flag);
				 temp.setLevel(exam.getLevel());
				 temp.setType(exam.getType());
				 temp.setPaperId(epId);
				 temp.setSubjectName(exam.getCoursesortName());
				 temp.setUserId(userId);
				 temp.setPaperName(exam.getEpName());
				 temp.setJifen(exam.getJifen());
				 temp.setCoeffcient(exam.getCoeffcient());
				 exampaperService.addFavoritesPaper(temp);
			 }
			 //移除收藏
			 if(flag != null && flag.equals("0")){
				 this.getQueryFavoritesCondition().setUserId(userId);
				 this.getQueryFavoritesCondition().setEpId(epId);
				 exampaperService.delFavoritesPaper(this.getQueryFavoritesCondition());
			 }
			 
			this.setResult(new Result(true,"success",null,null));
			
			   
			
			}catch(Exception e){
				logger.error("ExamWebAction.changeFavoritesStatus",e);
				return ERROR;
			}
			return "favoritesjson";
	 } 
	 /**
	  * 添加或移除收藏试题
	  * @author 杨海波
	  * @return string
	  */
	 
	 public String changeFavoritesQstStatus(){
		 
		 
		 try{
			 //获得被收藏的试题ID
			 int questionId = this.getQueryFavoritesCondition().getQuestionID();
			 //获得当前用户ID
			 int userId = getLoginUserId();
			 //添加收藏
			 if(flag != null && flag.equals("1")){
				 FavoritesQuestion temp = new FavoritesQuestion();
				 temp.setCreateDate(new Date());
				 temp.setFlag(flag);
				 temp.setEpId(epid);
				 temp.setUserId(userId);
				 temp.setQuestionId(questionId);
				 exampaperService.addFavoritesQuestion(temp);
				 //更新试题收藏数量+1
				 exampaperService.addCollectNum(questionId);
			 }
			 //移除收藏
			 if(flag != null && flag.equals("0")){
				 this.getQueryFavoritesCondition().setUserId(userId);
				 this.getQueryFavoritesCondition().setQuestionID(questionId);
				 this.getQueryFavoritesCondition().setEpId(epid);
				 exampaperService.delFavoritesQuestion(this.getQueryFavoritesCondition());
				 //更新试题收藏数量-1
				 exampaperService.subCollectNum(questionId);
			 }
			 
			 this.setResult(new Result(true,"success",null,null));
			 
		 }catch(Exception e){
			 logger.error("ExamWebAction.changeFavoritesQstStatus",e);
			 return ERROR;
		 }
		 return "favoritesjson";
	 } 
	
	/*public String getExamPaperAllList(){
		
		return "getExamPaperAllList";
	}*/
	
	/**
	 * 我的试卷查询
	 */
	public String getExamHistroy(){
		try{
			
			int userId = getLoginUserId();
			subjectExampaperNum=exampaperService.getSubjectExampaperNum(userId);
			if(subjectExampaperNum==null||subjectExampaperNum.size()==0)return "examNone";
			this.getQueryExampaperRecordCondition().setCusId(userId);
			int usersubjectId=this.exampaperService.getcustomerSubjectId(userId);
			//设置页面专业，如果页面subjectIdweb=空，走用户第一次购买的专业，否则走选中的专业
			if(subjectIdweb==0)
			{
				List<ExampaperSubjectId> esList = this.exampaperService.getSubjectIdListForUnAss(userId);
				int esSize= esList.size();
				if(esSize>0){
					int subid=esList.get(0).getSubjectId();
					this.getQueryExampaperCondition().setSubjectId(subid);
					subjectIdweb=subid;
					
				}else
				{
					this.getQueryExampaperCondition().setSubjectId(usersubjectId);
					subjectIdweb=usersubjectId;
				}
				//通过专业查询课程列表
			}else
			{
				this.getQueryExampaperRecordCondition().setSubject_id(subjectIdweb);
				//通过专业查询课程列表
			}
			//coursesortList=exampaperService.getcourseotByPid(subjectIdweb);
			Sys sys=this.sysNodeService.getSysBySubjectId(subjectIdweb);
			if(sys!=null){
				SysNode rootNode=this.sysNodeService.getSysNodeBySysId(sys.getKsId());
				this.getQuerySysNodeCondition().setParentId(rootNode.getKsnId());
				this.sysNodeList=this.sysNodeService.getSysNodeListByCondition(querySysNodeCondition);
								
			}
			
			//统计我的收藏信息 add by yanghaibo 2012-07-30 17:38
			this.getQueryFavoritesCondition().setType(0);
			this.getQueryFavoritesCondition().setCoeffcient(0);
			this.getQueryFavoritesCondition().setUserId(userId);
			favoritesPaperCount = exampaperRecordService.getMyFavoritesPaperCount(this.getQueryFavoritesCondition());
			favoritesQstCount = exampaperRecordService.getMyFavoritesQuestionCount(this.getQueryFavoritesCondition());
			
			wrongQstCount = optRecordService.getErrorQstCountBycusId(userId);
			
			myPaperCount = exampaperRecordService.getMyPapaerCount(getQueryExampaperRecordCondition());
			
			myQstCount=this.qstService.getRightPercent(userId);
			
			//错题记录
			qstPaper = this.qstService.getCurrentErrorQst(subjectIdweb);
			Iterator it = qstPaper.iterator();
			while(it.hasNext()){
				Qst obj = (Qst) it.next();
				obj.setQstContent(FunctionUtil.Html2Text(obj.getQstContent()).replaceAll("&nbsp;", ""));
				if(obj.getQstContent().length() > 32){
					obj.setQstContent(obj.getQstContent().substring(0, 32)+"......");
				}
				errorQstList.add(obj);
			}
			
//			errorQstList=this.qstService.getCurrentErrorQst();
			this.getQueryExampaperRecordCondition().setPageSize(7);
			setPage(exampaperRecordService.getExamHistory(queryExampaperRecordCondition));
			this.getPage().setPageSize(7);
			this.setPageUrlParms();
			/*this.getQueryOptRecordCondition().setCusId(userId);
			 this.errorQstTop3=this.optRecordService.getErrorQstTop3(queryOptRecordCondition);
			    for(OptRecord re:errorQstTop3){
			    	if(re.getEpType()==1){re.setEpName("真题测试");}
			    	if(re.getEpType()==2){re.setEpName("仿真测试");}
			    	if(re.getEpType()==3){re.setEpName("单元测试");}
			    	if(re.getEpType()==4){re.setEpName("专题测试");}
			    	re.setQstContext(re.getQstContext().replaceAll("style", "styl"));
			    	re.setQstContext(re.getQstContext().replaceAll("<br>", ""));
			    	re.setQstContext(re.getQstContext().replaceAll("<br/>", ""));
			    	re.setQstContext(re.getQstContext().replaceAll("span", "font"));
			    
			    }*/
		}catch(Exception e){
			logger.error("ExamWebAction.getExamHistroy",e);
			return ERROR;
		}
		
		return "getExamHistroy";
	}
	
	/**
	 * 查看我收藏的试卷 add by yanghaibo 2012-07-10 16:37
	 */
	public String getMyFavoritesPaper(){
		try{
			//获得当前登录用户ID
			int userId = getLoginUserId();
			this.getQueryFavoritesCondition().setUserId(userId);
			this.getQueryFavoritesCondition().setPageSize(9);
			setPage(exampaperRecordService.getMyFavoritesPaper(queryFavoritesCondition));
			
			//统计我的收藏信息 add by yanghaibo 2012-07-30 17:38
			this.getQueryFavoritesCondition().setType(0);
			this.getQueryFavoritesCondition().setCoeffcient(0);
			this.getQueryFavoritesCondition().setUserId(userId);
			favoritesPaperCount = exampaperRecordService.getMyFavoritesPaperCount(this.getQueryFavoritesCondition());
			favoritesQstCount = exampaperRecordService.getMyFavoritesQuestionCount(this.getQueryFavoritesCondition());
			wrongQstCount = optRecordService.getErrorQstCountBycusId(userId);
			this.getQueryExampaperRecordCondition().setCusId(userId);
			this.getQueryExampaperRecordCondition().setSubject_id(subjectIdweb);
			myPaperCount = exampaperRecordService.getMyPapaerCount(getQueryExampaperRecordCondition());
			
			//错题记录
			qstPaper = this.qstService.getCurrentErrorQst(subjectIdweb);
			Iterator it = qstPaper.iterator();
			while(it.hasNext()){
				Qst obj = (Qst) it.next();
				obj.setQstContent(FunctionUtil.Html2Text(obj.getQstContent()).replaceAll("&nbsp;", ""));
				if(obj.getQstContent().length() > 32){
					obj.setQstContent(obj.getQstContent().substring(0, 32)+"......");
				}
				errorQstList.add(obj);
			}
			
			//最近被收藏的试卷
			//this.favoritesPapers = exampaperRecordService.getNewFavoritesPaper(queryFavoritesCondition);
			//获得统计结果
			myQstCount=this.qstService.getRightPercent(userId);
			this.getPage().setPageSize(9);
			this.setPageUrlParms();
		}catch(Exception e){
			logger.error("ExamWebAction.getMyFavoritesPaper",e);
			return ERROR;
		}
		
		return "getMyFavoritesPaper";
	}
	/**
	 * 查看我收藏的试题 add by yanghaibo 2012-07-11 15:25
	 */
	public String getMyFavoritesQuestion(){
		try{
			//获得当前登录用户ID
			int userId = getLoginUserId();
			this.getQueryFavoritesCondition().setUserId(userId);
			this.getQueryFavoritesCondition().setPageSize(9);
			setPage(exampaperRecordService.getMyFavoritesQuestion(queryFavoritesCondition));
			
			//统计我的收藏信息 add by yanghaibo 2012-07-30 17:38
			this.getQueryFavoritesCondition().setType(0);
			this.getQueryFavoritesCondition().setCoeffcient(0);
			this.getQueryFavoritesCondition().setUserId(userId);
			favoritesPaperCount = exampaperRecordService.getMyFavoritesPaperCount(this.getQueryFavoritesCondition());
			favoritesQstCount = exampaperRecordService.getMyFavoritesQuestionCount(this.getQueryFavoritesCondition());
			wrongQstCount = optRecordService.getErrorQstCountBycusId(userId);
			
			this.getQueryExampaperRecordCondition().setCusId(userId);
			this.getQueryExampaperRecordCondition().setSubject_id(subjectIdweb);
			myPaperCount = exampaperRecordService.getMyPapaerCount(getQueryExampaperRecordCondition());
			List resuList =this.getPage().getPageResult();
			List returnList = new ArrayList<FavoritesQuestion>();
			Iterator it = resuList.iterator();
			while(it.hasNext()){
				FavoritesQuestion obj = (FavoritesQuestion) it.next();
				obj.setQstContent(FunctionUtil.Html2Text(obj.getQstContent()).replaceAll("&nbsp;", ""));
				if(obj.getQstContent().length() > 32){
					obj.setQstContent(obj.getQstContent().substring(0, 32)+"......");
				}
				returnList.add(obj);
			}
			
			//错题记录
			qstPaper = this.qstService.getCurrentErrorQst(subjectIdweb);
			Iterator itt = qstPaper.iterator();
			while(itt.hasNext()){
				Qst obj = (Qst) itt.next();
				obj.setQstContent(FunctionUtil.Html2Text(obj.getQstContent()).replaceAll("&nbsp;", ""));
				if(obj.getQstContent().length() > 32){
					obj.setQstContent(obj.getQstContent().substring(0, 32)+"......");
				}
				errorQstList.add(obj);
			}
			
			this.getPage().setPageResult(returnList);
			//获得统计结果
			myQstCount=this.qstService.getRightPercent(userId);
			this.getPage().setPageSize(9);
			this.setPageUrlParms();
		}catch(Exception e){
			logger.error("ExamWebAction.getMyFavoritesQuestion",e);
			return ERROR;
		}
		
		return "getMyFavoritesQst";
	}
	
	/**
	 * 试卷信息
	 */
	public String getExamPaperInfo(){
		
		try {
			exam=exampaperService.getExampaperInfoByEpId(epid);
			if(exam==null||exam.getLevel()!=2){
				return getExamPaperAllList()  ;
			}
			int userId = getLoginUserId();
			TotolsScore tss = this.totolsScoreService.getTotolsScore(userId);
			if(tss!=null){
				jifen=tss.getTsCurrent();
			}else{
				TotolsScore totlescore =new TotolsScore();
				totlescore.setCusId(userId);
				totlescore.setTsAction(0);
				totlescore.setTsCurrent(0);
				totolsScoreService.addTotolsScore(totlescore);
				jifen=0;
			}
			if(exam.getAvgScore()!=0)
			exam.setAvgScore(Float.parseFloat(String.valueOf(exam.getAvgScore()).substring(0, String.valueOf(exam.getAvgScore()).indexOf(".")+2)));
			examrecord=exampaperRecordService.getExamRecordTop10(epid);
			examrecordqita=exampaperService.getExampaperTop8(exam.getSubjectId());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("ExamWebAction.getExamPaperInfo",e);
			return ERROR;
		}
		
		return "getExamPaper";
	}

	
	/**
	 * 播放器要的东西
	 * 参数 kpoint
	 */
	
	public List<QueryQstConditionBFQ> getExamPaperkpointid(int kPoint)
	{
		List<QueryQstConditionBFQ> qstlistall =new  ArrayList<QueryQstConditionBFQ>();
		try {
			int index = 0;
			exampaperList = exampaperService.getExampaperByKpointList(kPoint);
			for(int a=0;a<exampaperList.size();a++)
			{
				exam=exampaperList.get(a);
				List<Qst> qstlist = qstService.getQstByIdList(exam.getEpId());
				for(int i=0; i<qstlist.size();i++)
				{
					
					QueryQstConditionBFQ sst=new QueryQstConditionBFQ();
					sst.setQstId(qstlist.get(i).getQstId());
					sst.setEpId(qstlist.get(i).getEpId());
					sst.setIsAsr(qstlist.get(i).getIsAsr());
					sst.setWrongJude(qstlist.get(i).getWrongJude());
					sst.setQstContent(qstlist.get(i).getQstContent());
					sst.setAoptOrder(qstlist.get(i).getOptions().get(0).getOptOrder());
					sst.setBoptOrder(qstlist.get(i).getOptions().get(1).getOptOrder());
					sst.setCoptOrder(qstlist.get(i).getOptions().get(2).getOptOrder());
					sst.setDoptOrder(qstlist.get(i).getOptions().get(3).getOptOrder());
					index ++;
					sst.setQstIndex(index);
					if(qstlist.get(i).getOptions().get(0).getOptContent()==null&&qstlist.get(i).getOptions().get(0).getOptContent()=="")
					{
						sst.setAoptContent("");
					}else
					{
						sst.setAoptContent(qstlist.get(i).getOptions().get(0).getOptContent());
					}
					if(qstlist.get(i).getOptions().get(1).getOptContent()==null&&qstlist.get(i).getOptions().get(1).getOptContent()=="")
					{
						sst.setBoptContent("");
					}else
					{
						sst.setBoptContent(qstlist.get(i).getOptions().get(1).getOptContent());
					}
					if(qstlist.get(i).getOptions().get(2).getOptContent()==null&&qstlist.get(i).getOptions().get(2).getOptContent()=="")
					{
						sst.setCoptContent("");
					}else
					{
						sst.setCoptContent(qstlist.get(i).getOptions().get(2).getOptContent());
					}
					if(qstlist.get(i).getOptions().get(3).getOptContent()==null&&qstlist.get(i).getOptions().get(3).getOptContent()=="")
					{
						sst.setDoptContent("");
					}else
					{
						sst.setDoptContent(qstlist.get(i).getOptions().get(3).getOptContent());
					}
					qstlistall.add(sst);
					
				}
			}
		} catch (Exception e) {
			logger.error("ExamWebAction.getExamPaperkpointid",e);
			return null;
		}
		return qstlistall;
	}
	

	/**
	 * 设置学员中心左侧工具栏
	 * 为我的课程 和 用户试听课程
	 * @param userId
	 */
	protected void setCourseSortListDTOList(int userId){
		Customer customer = customerService.getCustomerById(userId);
		
		if(customer.getIsComplete() != 1 ||(customer.getIsComplete() == 1 && customerService.isComplete(userId) >= 0)){
			courseSortListDTOList = coursesortService.getCourseSortListDTOList();
		}
		
		if(userId != 0){
			courseList = cusCouKpointService.getCusCouKpointListByCusId(userId);
		}
	}
	
	
	/**
	 * 新主观题的效果
	 * 通过epId查询试卷试题
	 * @author 何海强
	 * @return 
	*/
	
	public String NewToExamzhuguan(){
		try{
			int userId = getLoginUserId();
			//this.setCourseSortListDTOList(userId);
			exam=exampaperService.getExampaperByIdqian(epid);
		
			qstOptlist=new ArrayList<UserQst>();
			String answer[] = this.subRlt.split(",");
			List<Qst> qstList=new ArrayList<Qst>();
			List<Qst> qstOptList=new ArrayList<Qst>();
			//获取试卷所有相关试题和选项，用于循环处理数据至正确的形式
			qstList=this.getQstService().getExamQstOptList(epid);
			counts=0;
			if(qstList!=null&&qstList.size()>0){
				//获取第一个用于比较的试题id和第一个对象
				int qstId=qstList.get(0).getQstId();
				List<Qst> list=new ArrayList<Qst>();
				Qst qst=qstList.get(0);
				for(int i=0;i<qstList.size();i++){
					//试题id相同，表示这是选项信息，添加进入临时选项列表
					if(qstList.get(i).getQstId()==qstId)list.add(qstList.get(i));
					else{
						
						qst.setQstziti(list);//试题id不同，选项已经添加完毕，set进试题里面
						qstOptList.add(qst);//将试题放入最终显示的试题列表
						
						qstId=qstList.get(i).getQstId();//赋值新得到的试题id
						qst=qstList.get(i);//存入新得到的对象
						list=new ArrayList<Qst>();//list清空
						list.add(qst);
					}
					if(i==qstList.size()-1){
						qst.setQstziti(list);//试题id不同，选项已经添加完毕，set进试题里面
						qstOptList.add(qst);//将试题放入最终显示的试题列表
					}
				}
			}
			for(int i=0;i<qstOptList.size();i++)
			{
				Qst qstset=qstOptList.get(i);
				
				//统计试卷的试题数量
				if(qstset.getQstType()!=4)counts++;
				else if(qstset.getQsttype()==4)counts++;
				
				if(i>=answer.length){
				qstset.setWrongAsr("");
				}else{
				if(answer[i].trim().indexOf("isnull")>=0){
					String asr=answer[i].trim().substring(0,answer[i].trim().indexOf("isnull"));	
					qstset.setWrongAsr(asr);
				}else{
				qstset.setWrongAsr(answer[i]);
				}
				}
				qstlist.add(qstset);
			}
			
			if ("0".equals(usetime.trim())) {
				usetime = "1";
			}
		}catch(Exception e){
			logger.error("ExamWebAction.NewToExamzhuguan",e);
			return ERROR;
		}
		
		return "newtoexamzhuguan";
	}
	 
	
	/**
	 * 新开始页面
	 * 学员考试结束后展现考试结果以及评价
	 * 结果又评价、得分、错误解析
	 * @return
	 */
	public String NewjudgmentPaper() {
		try {
			int userId = getLoginUserId();
			//this.setCourseSortListDTOList(userId);
		
			List<Qst>qstlist=this.qstService.getExamQstList(epid);
			exam=exampaperService.getExampaperByIdqian(epid);
			// 计算分数
			String answer[] = this.subRlt.split(",");
			// 基础问题个数，做对个数
			int simplePro = 0;
			int rightSp = 0;
			float pctSp = 0;
			// 中档问题个数，做对个数
			int generPro = 0;
			int rightGp = 0;
			float pctGp = 0;
			// 难题个数，做对个数
			int hardPor = 0;
			int rightHp = 0;
			float pctHp = 0;
			//实际做题总数，不算材料分析题主题，每个子题算一道题
			int examqstCount=0;
			int qstCount=0;//做题数，每个材料分析题包括子题算做一道题
			totalScroe=new Float(servletRequest.getParameter("zipingfen")).floatValue();
			StringBuffer zongpingIds = new StringBuffer();
			for (int i = 0; i < qstlist.size(); i++) {
				Qst qst = qstlist.get(i);
				String editAsr="";
				String asr="";
				if(qst.getIsAsr().indexOf(",")!=-1){
					String isAsr[]=qst.getIsAsr().split(",");
					for(int k=0;k<isAsr.length;k++){
						editAsr+=isAsr[k].trim();
					}
				}else editAsr=qst.getIsAsr();
				qst.setIsAsr(editAsr);
				if(i<answer.length){
				if(answer[i].trim().indexOf("isnull")>=0){
					 asr=answer[i].trim().substring(0,answer[i].trim().indexOf("isnull"));
				}else {
					asr=answer[i].trim();
				}
				}else{
					asr="";
				}
				if (qst.getQstType() == 1) {
					if (qst.getIsAsr().equals(asr)) {
						totalScroe += qst.getScore();
						rightSp++;
					}  
					simplePro++;
					examqstCount++;
					qstCount++;
				} else if (qst.getQstType() == 2) {
					
					if (qst.getIsAsr().equals(asr)) {
						totalScroe += qst.getScore();
						rightGp++;
					} 
					generPro++;
					examqstCount++;
					qstCount++;
				} else if (qst.getQstType() == 3) {
					if (qst.getIsAsr().equals(asr)) {
						totalScroe += qst.getScore();
						rightHp++;
					} 
					hardPor++;
					examqstCount++;
					qstCount++;
				}
				else if (qst.getQstType() == 4) {
					if(qst.getQsttype()!=4){
					if (qst.getIsAsr().equals(asr)) {
						totalScroe += qst.getScore();
						rightHp++;
					} 
					hardPor++;
					examqstCount++;
					}else qstCount++;
					
				}
				
				else if (qst.getQstType() == 5) {
					if (qst.getIsAsr().equals(asr)) {
						totalScroe += qst.getScore();
						rightHp++;
					} 
					hardPor++;
					examqstCount++;
					qstCount++;
				}
				else if (qst.getQstType() == 6) {
					if(i<answer.length){
						if (qst.getIsAsr().equals(asr)) {
							totalScroe += qst.getScore();
							rightHp++;
						} 
					} 
					hardPor++;
					examqstCount++;
					qstCount++;
				}
			}

			if (simplePro != 0) {
				pctSp = (float) rightSp / simplePro;
			}
			if (generPro != 0) {
				pctGp = (float) rightGp / generPro;
			}
			if (hardPor != 0) {
				pctHp = (float) rightHp / hardPor;
			}

			// 定义评语
			for (int i = 0; i < exam.getReviewsList().size(); i++) {
				Reviews reviews = exam.getReviewsList().get(i);

				if (reviews.getEvaType() == 1) {
					zongping1 = reviews.getRvInfo();
					zongping1Id = reviews.getRvId();
				} else if (reviews.getEvaType() == 2) {
					zongping2 = reviews.getRvInfo();
					zongping2Id = reviews.getRvId();
				} else if (reviews.getEvaType() == 3) {
					zongping3 = reviews.getRvInfo();
					zongping3Id = reviews.getRvId();
				} else if (reviews.getEvaType() == 4) {
					zongping4 = reviews.getRvInfo();
					zongping4Id = reviews.getRvId();
				} else if (reviews.getEvaType() == 5) {
					zongping5 = reviews.getRvInfo();
					zongping5Id = reviews.getRvId();
				} else if (reviews.getEvaType() == 6) {
					zongping6 = reviews.getRvInfo();
					zongping6Id = reviews.getRvId();
				} else if (reviews.getEvaType() == 7) {
					zongping7 = reviews.getRvInfo();
					zongping7Id = reviews.getRvId();
				} else if (reviews.getEvaType() == 8) {
					zongping8 = reviews.getRvInfo();
					zongping8Id = reviews.getRvId();
				} else if (reviews.getEvaType() == 9) {
					zongping9 = reviews.getRvInfo();
					zongping9Id = reviews.getRvId();
				}
			}
			StringBuffer sb = new StringBuffer();
			if (simplePro != 0) {
				if (pctSp <= 0.5) {
					sb.append(zongping1);
					zongpingIds.append(zongping1Id);
					zongpingIds.append(",");
				} else if (pctSp < 1 && pctSp > 0.5) {
					sb.append(zongping2);
					zongpingIds.append(zongping2Id);
					zongpingIds.append(",");
				} else if (pctSp == 1) {
					sb.append(zongping3);
					zongpingIds.append(zongping3Id);
					zongpingIds.append(",");
				}
			}
			if (generPro != 0) {
				if (pctGp <= 0.5) {
					sb.append(zongping4);
					zongpingIds.append(zongping4Id);
					zongpingIds.append(",");
				} else if (pctGp < 1 && pctGp > 0.5) {
					sb.append(zongping5);
					zongpingIds.append(zongping5Id);
					zongpingIds.append(",");
				} else if (pctGp == 1) {
					sb.append(zongping6);
					zongpingIds.append(zongping6Id);
					zongpingIds.append(",");
				}
			}
			if (hardPor != 0) {
				if (pctHp <= 0.5) {
					sb.append(zongping7);
					zongpingIds.append(zongping7Id);
				} else if (pctHp < 1 && pctHp > 0.5) {
					sb.append(zongping8);
					zongpingIds.append(zongping8Id);
				} else if (pctHp == 1) {
					sb.append(zongping9);
					zongpingIds.append(zongping9Id);
				}
			}
			//---------------- 插入考试记录
			ExampaperRecord exampaperRecord = new ExampaperRecord();
			exampaperRecord.setCusId(userId);
			exampaperRecord.setAddtime(DateUtil.getNow());
			exampaperRecord.setEpId(exam.getEpId());
			exampaperRecord.setExampaper(exam);
			exampaperRecord.setUserScore(totalScroe);
			exampaperRecord.setReviewsIds(zongpingIds.toString());
			exampaperRecord.setResId(0);
			exampaperRecord.setRightCount(rightHp+rightSp+rightGp);
			exampaperRecord.setQstCount(qstCount);
			exampaperRecord.setRightPercent(exampaperRecord.getRightCount()*100/examqstCount);
			if ("0".equals(usetime.trim())) {
				usetime = "1";
			}
			float utime = Float.parseFloat(usetime);
			exampaperRecord.setTestTime(utime);
			// 计算正确率
			int totalPro = simplePro + generPro + hardPor;
			int totalRPro = rightSp + rightGp + rightHp;
			if (totalPro == 0) {
				totalPro = 1;// 防止除数为0
			}
			float accuracy = (float) totalScroe / exam.getEpTotelScore();
			
			accuracy = ((float) (Math.round(accuracy * 1000)))/ 1000;
			lv=((float) (Math.round(accuracy * 1000)))/ 10;
			exampaperRecord.setAccuracy(accuracy);
			userEpId = exampaperRecordService
					.addExampaperRecord(exampaperRecord);// 返回userEpId为插入错题表做准备
			this.scoreJudgment = sb.toString();//当前的试卷的评语
			//----------------  插入答案记录
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<OptRecord> optrecordlist=new ArrayList<OptRecord>();
			for (int i = 0; i < qstlist.size(); i++) {
				Qst qst = qstlist.get(i);
				
				OptRecord optRecord = new OptRecord();
				optRecord.setQstId(qst.getQstId());
				optRecord.setAddtime(DateUtil.getNow());
				if(i<answer.length){
					if(answer[i].trim().indexOf("isnull")>=0){
					optRecord.setUserAnswer(answer[i].trim().substring(0,answer[i].trim().indexOf("isnull")));
					}else
					{
						optRecord.setUserAnswer(answer[i].trim());
					}
				}else{
					optRecord.setUserAnswer("");
				} 
				optRecord.setCusId(this.getLoginUserId());
				optRecord.setUserEpId(userEpId);
				optRecord.setQstType(qstlist.get(i).getQstType());
				if(StringUtils.equalsIgnoreCase(qst.getIsAsr(),optRecord.getUserAnswer())){
					optRecord.setIsRight(1);//答题正确
				}else{
					optRecord.setIsRight(0);//答题错误
				}
				optrecordlist.add(optRecord);
//				optRecordService.addOptRecord(optRecord);
			}
			if(optrecordlist!=null&&optrecordlist.size()>0){
			this.optRecordService.addManyOptRecord(optrecordlist);
			this.qstService.updateQstDoneNum(optrecordlist);
			}
			exam.setJoinNum(exam.getJoinNum()+1);
			exampaperService.updateExampaper(exam);
					//第一次评论课程任务
			QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
			queryTaskCusCondition.setCusId(userId);
			queryTaskCusCondition.setKeyWord(Task.TASK_KEY_TEST);
			
			TaskCus tc = taskCusService.getTaskCusByKey(queryTaskCusCondition);
			
			if(tc != null && tc.getIsComplete() == 0){//若果未完成则设置完成
				tc.setIsComplete(1);
				taskCusService.updateTaskCus(tc);
			}
			
			//积分表进行记录  
			TotolsScore totolsScore=new TotolsScore();
			totolsScore=this.totolsScoreService.getTotolsScore(userId);
			if(totolsScore!=null){
			//成长积分 +5
			int tsA=totolsScore.getTsAction();
			tsA=tsA+5;
			totolsScore.setTsAction(tsA);
			//兑换积分
			int tsT=totolsScore.getTsCurrent();
			if(accuracy>0.9&&accuracy<1)
			{
			tsT=tsT+10;
			this.setinfo(userId, exam.getJifen(), 10);
			}
			if(accuracy>0.7&&accuracy<0.9)
			{
			tsT=tsT+9;
			this.setinfo(userId, exam.getJifen(), 9);
			}
			if(accuracy>0.6&&accuracy<0.7)
			{
			tsT=tsT+6;
			this.setinfo(userId, exam.getJifen(), 6);
			}
			if(accuracy<0.6)
			{
			tsT=tsT+4;
			this.setinfo(userId, exam.getJifen(), 4);
			}
			totolsScore.setTsCurrent(tsT);
			
			this.totolsScoreService.updateTotolsScore(totolsScore);
			
			//积分记录表进行记录
			TsRecord tsRecord=new TsRecord();
			tsRecord.setCusId(userId);
			tsRecord.setTrType(TsRecord.TRTYPE_ACTION);
			tsRecord.setAccessType(TsRecord.ACCESSTYPE_FOR_EXAM);
			tsRecord.setAccessTime(new Date());
			tsRecord.setTsId(totolsScore.getTsId());
			tsRecord.setTrNum(5);
			this.tsRecordService.addTsRecord(tsRecord);
			
			//积分记录表进行记录
			TsRecord tsR=new TsRecord();
			tsR.setCusId(userId);
			tsR.setTrType(TsRecord.TRTYPE_FOR);
			tsR.setAccessType(TsRecord.ACCESSTYPE_FOR_NEXAM);
			tsR.setAccessTime(new Date());
			tsR.setTsId(totolsScore.getTsId());
			tsR.setTrNum(5);
			this.tsRecordService.addTsRecord(tsR);
			}

			//第一次评论课程任务结束
			return "newshowokok";
		} catch (Exception ex) {
			logger.error("ExamWebAction.NewToExamzhuguan",ex);
			return ERROR;
		}

	}

	
	public void setinfo(int userId,int jinfen,int jiafen){
		//添加信息提醒；
		String msgContent="嗨学网提示您:您在考试中心,您消耗了"+jinfen+",通过您完成考试奖励积分为"+jiafen; 
		message=new Message();
		message.setMsgContent(msgContent);
		message.setKeyWord("考试中心提醒");
		message.setMsgType(5);
		message.setCreateTime(new Date());
		message.setMsgSort(1);
		message.setSenderType(2);
		message.setSenderId(1);
		int msgId=planclockService.addTSSO(getMessage());
		userMsg=new UserMsg();
		userMsg.setMsgId(msgId);
		userMsg.setSenderId(1);
		userMsg.setSenderType(2);
		userMsg.setReceiverType(1);
		userMsg.setReceiverId(userId);
		userMsg.setSendTime(new Date());
		planclockService.addtSSOuserInfo(getUserMsg());
	}
	
	/**
	 * 学员中心考试分析
	 * 按学员的课程进行查询
	 * 查询结果有最高正确率、最低正确率、平均正确率、平均得分、所有该课程考试记录、考试次数
	 * @return
	*/
	public String getExampaperAnalysisDTO() {
		try {
			int userId = getLoginUserId();
			
			Customer cus = customerService.getCustomerById(userId);
			subjectId=cus.getSubjectId();
			if(cus.getStudyType() != null && cus.getStudyType() != "") {
				prepareStudyTypeParm(cus);
			}
			
			if(userId != 0){
				courseList = cusCouKpointService.getCusCouKpointListByCusId(userId);
			}
			
			if((course == null || course.getCourseId() == 0) && courseList != null && courseList.size() != 0){
				course = courseList.get(0);
			}

		} catch (Exception ex) {
			logger.error("ExamWebAction.getExampaperAnalysisDTO",ex);
			return ERROR;
		}
		return "showPaperAnalysis";
	}
	private void prepareStudyTypeParm(Customer cus) {
		String studyTypeStr = cus.getStudyType();
		
		String[] str = studyTypeStr.split(",");
		int index = Integer.parseInt(str[0]);
		int totalCount = Integer.parseInt(str[5]);
		int[] answers = new int[4];
		int[] results = new int[4];
		
		for(int i=1; i<5; i++) {
			answers[i-1] = Integer.parseInt(str[i]);
		}
		
		results[0] = answers[0]*100/totalCount;
		results[1] = answers[1]*100/totalCount;
		results[2] = answers[2]*100/totalCount;
		
		for(int i=0; i<3; i++) {
			results[i] = results[i] < 1 ? 1 : results[i];
			results[i] = results[i] > 97 ? 97 : results[i];
		}
		
		results[3] = 100 - results[0] - results[1] - results[2];
		
		studyTypePram = new HashMap();
		studyTypePram.put("studyType", index);
		studyTypePram.put("results", results);
	}
	/**
	 * 根据用户考试记录ID获取考过的试卷
	 * 查看考过的试卷
	 * 查看结果为：错题解析、试卷全部内容、用户答案、得分
	 * @return
	*/
	public String getUserExamPaperByUserEpIdnew() {
		try {
			if(userEpId != 0){
				userExamPaper = exampaperRecordService.getUserExamPaperByUserEpId(userEpId);
				if(userExamPaper==null){
					return getExamHistroy();
				}
				String reviewsIds = userExamPaper.getReviewsIds();
				qstOptlist=new ArrayList<UserQst>();
				List<UserQst> qstOptionlist=this.optRecordService.getUserQstOptionList(userEpId);
				if(qstOptionlist!=null&&qstOptionlist.size()>0){
					//获取第一个用于比较的试题id和第一个对象
					int qstId=qstOptionlist.get(0).getQstId();
					List<UserQst> list=new ArrayList<UserQst>();
					UserQst userQst=qstOptionlist.get(0);
					for(int i=0;i<qstOptionlist.size();i++){
						//试题id相同，表示这是选项信息，添加进入临时选项列表
						if(qstOptionlist.get(i).getQstId()==qstId)list.add(qstOptionlist.get(i));
						else{
							
							userQst.setQstOptList(list);//试题id不同，选项已经添加完毕，set进试题里面
							qstOptlist.add(userQst);//将试题放入最终显示的试题列表
							
							qstId=qstOptionlist.get(i).getQstId();//赋值新得到的试题id
							userQst=qstOptionlist.get(i);//存入新得到的对象
							list=new ArrayList<UserQst>();;//list清空
							list.add(userQst);
						}
						if(i==qstOptionlist.size()-1){
							userQst.setQstOptList(list);//试题id不同，选项已经添加完毕，set进试题里面
							qstOptlist.add(userQst);//将试题放入最终显示的试题列表
						}
					}
				}
				weida=0;
				counts=0;
				int qstId=0;
				boolean isCount=false;
				if(userEpId != 0){
					for(int i=0;i<qstOptlist.size();i++){
						String asr=qstOptlist.get(i).getUserAsr();
						//统计试卷的试题数量
						if(qstOptlist.get(i).getOptqstType()!=4){
							if(asr.equals("")){
								weida+=1;
							}
							counts++;
						}
						else if(qstOptlist.get(i).getQstType()==4){
							qstId=qstOptlist.get(i).getQstId();
							isCount=false;
							counts++;
						}
						else{
							if(asr.equals("")&&!isCount){
								weida+=1;
								isCount=true;
							}
						}
					}
				}
				
				if(reviewsIds != null&&reviewsIds!=""&&(!reviewsIds.equals(""))){
					String[] idsTemp = reviewsIds.split(",");
					StringBuffer buf = new StringBuffer();
					for(int i = 0; i < idsTemp.length; i ++){
						if(!idsTemp[i].trim().equals("0")){
							buf.append(reviewsService.getReviewsById(Integer.parseInt(idsTemp[i])).getRvInfo());
						}
					}
					String temp = buf.toString();
					userExamPaper.setZongping(temp);
				}
				
				//
				
				exam=exampaperService.getExampaperByIdUserEp(userExamPaper.getEpId());

				this.getExampaperRecord().setAccuracy(userExamPaper.getAccuracy()/10);
				this.getExampaperRecord().setEpId(userExamPaper.getEpId());
				paiming=exampaperRecordService.getExamPaperRecordqianpan(getExampaperRecord());
				
				if(paiming==0){
					paiming=paiming+1;
				}		
			}else{
				return	getExamPaperAllList();
			}
		} catch (Exception ex) {
			logger.error("ExamWebAction.getUserExamPaperByUserEpIdnew",ex);
			return ERROR;
		}
		return "getUserExamPaperByUserEpId";
	}
	 
	/**
	 * 新历史查询效果
	*/
	public String getUserExamPaperByUserEpId() {
		try {
			weida=0;
			if(userEpId != 0){
				userExamPaper = exampaperRecordService.getUserExamPaperByUserEpId(userEpId);
				if(userExamPaper==null){
					return getExamHistroy();
				}
				List<UserQst> qstlist= userExamPaper.getQst();
				for(int i=0;i<qstlist.size();i++){
					String asr=qstlist.get(i).getUserAsr();
					if(asr.equals("")){
						weida+=1;
					}
					
				}
				
				String reviewsIds = userExamPaper.getReviewsIds();
				
				if(reviewsIds != null&&reviewsIds!=""&&(!reviewsIds.equals(""))){
					String[] idsTemp = reviewsIds.split(",");
					StringBuffer buf = new StringBuffer();
					for(int i = 0; i < idsTemp.length; i ++){
						if(!idsTemp[i].trim().equals("0")){
							buf.append(reviewsService.getReviewsById(Integer.parseInt(idsTemp[i])).getRvInfo());
						}
					}
					String temp = buf.toString();
					userExamPaper.setZongping(temp);
				}
				
				//
				
				exam=exampaperService.getExampaperByIdUserEp(userExamPaper.getEpId());
				
				this.getExampaperRecord().setAccuracy(userExamPaper.getAccuracy()/10);
				this.getExampaperRecord().setEpId(userExamPaper.getEpId());
				paiming=exampaperRecordService.getExamPaperRecordqianpan(getExampaperRecord());
				if(paiming==0){
					paiming=paiming+1;
				}
			}else{
				return	getExamPaperAllList();
			}
		} catch (Exception ex) {
			logger.error("ExamWebAction.getUserExamPaperByUserEpId",ex);
			return ERROR;
		}
		return "getUserExamPaperByUserEpIdnew";
	}
	
	
	/*
	 * 到随即考试页面                                                                                                           
	 */
	public String toRandomExam() {
		

		//scopeMessage="如果没有做过题，不能选择做没做过的题";
		int userId = getLoginUserId();
		this.getServletRequest().getSession().removeAttribute("resultMap");
		this.getServletRequest().getSession().removeAttribute("randomQstId");
		subjectList=this.exampaperService.getcustomerbuySubject(userId);
		if(subjectList==null || subjectList.size()==0){
			subjectList.add(exampaperService.getcustomerSubject(userId));
			
		}
		return "toRandomExam";
	}

	/*
	 * 查找试卷，开始考试
	 */
	public String startRandomExam() {
     try{
    	 int userId = getLoginUserId();
    	 this.getQueryExampaperCondition().setCusId(userId);
  		 int usersubjectId=0;
  		 HashMap<Integer,String> resultList=(HashMap<Integer,String>)this.getServletRequest().getSession().getAttribute("resultMap");
  		 if(resultList==null){
  			 resultList=new HashMap<Integer,String>();
  		 }
  		 if(StringUtils.isNotEmpty(lastQstId) && StringUtils.isNotEmpty(resultCur)){
  			 Integer lastQstIdi=Integer.parseInt(lastQstId.trim());
  			 resultList.put(lastQstIdi,resultCur);
  			 this.getServletRequest().getSession().setAttribute("resultMap",resultList);
  		 }
  		 if(StringUtils.isBlank(startTime)){
  			 this.startTime= new Date().getTime()+"";
  		 }
  		  		
  		StringBuffer sb_type=new StringBuffer();
  		String[] str=this.getServletRequest().getParameterValues("qst_type");
  		if(str!=null && str.length>0){
  			for(int i=0;i<str.length;i++){
  				sb_type.append(str[i]).append(",");
  			}
  			sb_type.deleteCharAt(sb_type.length()-1);
  		}
  		
  		queryExampaperCondition.setSubjectId(this.subjectIdweb);
  		List<Qst> qstId_list=(ArrayList<Qst>)this.getServletRequest().getSession().getAttribute("randomQstId");
  		if(qstId_list==null || qstId_list.size()==0){
  			if(queryExampaperCondition==null){
  	  			 queryExampaperCondition=this.getQueryExampaperCondition();
  	  		}
  	  			 queryExampaperCondition.setQstTypeSql(sb_type.toString());
  	  			 String level= this.getServletRequest().getParameter("difficult_set");
  	  			 if(StringUtils.isBlank(level) || StringUtils.isEmpty(level)){
  	  				 level="1";
  	  			 }
  	  			 String scope= this.getServletRequest().getParameter("scope");
  	  			 if(StringUtils.isBlank(scope) || StringUtils.isEmpty(scope)){
  	  				 scope="1";
  	  			 }
  	  			 queryExampaperCondition.setDifficult_set(Integer.parseInt(level));
  	  			 queryExampaperCondition.setResult_type(result_type);
  	  			 queryExampaperCondition.setScope(Integer.parseInt(scope));
  	  		   	qstId_list=this.exampaperService.getQstIdList(queryExampaperCondition);
  	  		   
  		}
  		
  		if(qstId_list==null || qstId_list.size()==0){
  		    pageMessages="没有找到试题，请重新选择！";
  		    return "toRandomPaper";
  		}
  		Random random=new Random();
  		if(qstPaper==null){
  			qstPaper=new ArrayList<Qst>();
  		}
  		qstPaper.clear();
  		
  		int n=random.nextInt(qstId_list.size());
	  
  		int qst_id=0;
  		if(n<=qstId_list.size()){
  			qst_id=qstId_list.get(n).getQstId();
  		}else{
  			n=random.nextInt(qstId_list.size());
  			qst_id=qstId_list.get(n).getQstId();
  		}
  		 		
  		//qstId_list.remove(n);
  		this.getServletRequest().getSession().setAttribute("randomQstId",qstId_list);
  		qstPage=this.qstService.getQstById(qst_id);
  		if(qstPage.getQstType()==4){
  		this.queryQstCondition=this.getQueryQstCondition();
  		queryQstCondition.setQstindexId(qst_id);
  		this.qstPaper=this.qstService.getQstList(queryQstCondition);
  			for(Qst qst:qstPaper){
  				qst.setOptions(this.optionsService.getOptionsListByQstId(qst.getQstId()));	
  			}
  		}else{
  			this.options=this.optionsService.getOptionsListByQstId(qst_id);
  			qstPage.setOptions(options);
  		}
  		  		  		  		
     }catch(Exception e){
    	 logger.info("ExamWebAction.getExamHistroy", e);
 		return ERROR;
     }
    
		return "toRandomPaper";
		
	}

	
	public String getPageMessages() {
		return pageMessages;
	}
	public void setPageMessages(String pageMessages) {
		this.pageMessages = pageMessages;
	}
	/*
	 * 结束随即考试，并计算答题对错，返回答题结果。
	 */
	public String endrandomExam(){
		
		 HashMap<Integer,String> resultList=(HashMap<Integer,String>)this.getServletRequest().getSession().getAttribute("resultMap");
		
  		 if(resultList==null){
  			 resultList=new HashMap<Integer,String>();
  		 }
  		 if((StringUtils.isNotBlank(resultCur)) && lastQstId!=null ){
  			
  	  		 Integer lastqstId=Integer.parseInt(this.lastQstId);
  	  		
  			 resultList.put(lastqstId, resultCur);
  		 }
  		 if(resultList.size()==0){
  			pageMessages="您没有做一道题，本次随机考试零分。";
  			return "warning";
  		 }
  		 if(StringUtils.isNotBlank(startTime)){
  			
  			this.examLength=Integer.parseInt(((new Date().getTime()-Long.parseLong(startTime))/60000)+"");
  			if(examLength==0){
  				examLength=1;
  			}
  		 }
  		 this.correctNum=0;
  		 this.qstlist=new ArrayList<Qst>();
  		 Set<Integer> key = resultList.keySet();
        
  		 for (Iterator it = key.iterator(); it.hasNext();) {
             Integer qstId = (Integer) it.next();
             qstPage=this.qstService.getQstById(qstId);
            
             
             //单选、双选、判断题的结果
             if(qstPage.getQstType()==1 || qstPage.getQstType()==2 || qstPage.getQstType()==3){
            	 qstPage.setOptions(this.optionsService.getOptionsListByQstId(qstId));
            	 String usrStr=resultList.get(qstId).replaceAll(",", "");
            	 qstPage.setNote(usrStr);//记录用户答案
            	  if(StringUtils.equalsIgnoreCase(qstPage.getIsAsr().trim(), qstPage.getNote().trim())){
             	  	 this.correctNum++;
                  }
             }else if(qstPage.getQstType()==6){//问答题的结果
            	  List<Options> list=this.optionsService.getOptionsListByQstId(qstId);
            	  if(list.size()!=0){
            		 qstPage.setOptContent(list.get(0).getOptContent());
            		 qstPage.setNote(resultList.get(qstId));//记录用户答案
            		 if(StringUtils.equalsIgnoreCase(qstPage.getOptContent().trim(), qstPage.getNote().trim())){
                 	  	 this.correctNum++;
                     }
            	  }
              }else if(qstPage.getQstType()==4){
           		this.queryQstCondition=this.getQueryQstCondition();
           		queryQstCondition.setQstindexId(qstId);
           		String[] ristr=resultList.get(qstId).split(",");//记录用户答案
           		this.qstPaper=this.qstService.getQstList(queryQstCondition);
           			for(int i=0;i<qstPaper.size();i++){
           				Qst qst=qstPaper.get(i);
           				if(ristr!=null && ristr.length>0){
           					qst.setNote(ristr[i]);
           				}
           				qst.setOptions(this.optionsService.getOptionsListByQstId(qst.getQstId()));	
           			}
           		 this.qstPage.setQstziti(qstPaper);
           		}
             
             qstlist.add(qstPage);
  		 }
        
		this.correctRate=correctNum*100/qstlist.size();
		return "randomexamResult";
	}

	public String  updateQuesCount(){
		try {
			this.qstService.updateQuesCount(qstId);
			this.setResult(new Result(true,null,null,null));
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			 logger.info("ExamWebAction.updateQuesCount", e);
			this.setResult(new Result(false,null,null,null));
		}
		return "json";
	}
	
	/**
	 * 考试中心-错误试题查询
	 */
	public String getErrorExam(){
		try{
			
			int userId = getLoginUserId();
			this.getQueryOptRecordCondition().setCusId(userId);
			if(this.queryOptRecordCondition.getDoTime()==null){
				this.queryOptRecordCondition.setDoTime(0);
			}
			if(this.queryOptRecordCondition.getQstType()==null){
				this.queryOptRecordCondition.setQstType(0);
			}
			if(this.queryOptRecordCondition.getLevel()==null){
				this.queryOptRecordCondition.setLevel(0);
			}
			if(this.queryOptRecordCondition.getDoTime()==null){
				this.queryOptRecordCondition.setDoTime(0);
				
			}
			if(this.queryOptRecordCondition.getDoTime()==4){
				Date cur=new Date();
				Long de=cur.getTime()-90*24*3600000;
				cur.setTime(de);
				this.queryOptRecordCondition.setAddTime(cur.toString());
			}
			setPage(this.optRecordService.getErrorQstList(queryOptRecordCondition));
			
			List list=this.getPage().getPageResult();
			if(list!=null){
				SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
				for(int i=0;i<list.size();i++){
					OptRecord or=(OptRecord)list.get(i);
					String context=FunctionUtil.Html2Text(or.getQstContext().replaceAll("&nbsp;", ""));
					String asr=FunctionUtil.Html2Text(or.getIsAsr());
					String wro=FunctionUtil.Html2Text(or.getWorngJude());
					String optRe=FunctionUtil.Html2Text(or.getOptContent());
					or.setOptContent(optRe);
					if(wro!=null && wro.length()>32){
						wro=wro.substring(0, 32);
					}
					if(asr!=null && asr.length()>32){
						asr=asr.substring(0,32);
					}
					if(context!=null && context.length()>32){
						context=context.substring(0, 32);
					}
					or.setWorngJude(wro);
					or.setQstContext(context);
					or.setIsAsr(asr);
					
					or.setExamTime(dateformat2.format(or.getAddtime()));
									
				}
			}
			
			//统计我的收藏信息 add by yanghaibo 2012-07-30 17:38
			this.getQueryFavoritesCondition().setType(0);
			this.getQueryFavoritesCondition().setCoeffcient(0);
			this.getQueryFavoritesCondition().setUserId(userId);
			favoritesPaperCount = exampaperRecordService.getMyFavoritesPaperCount(this.getQueryFavoritesCondition());
			favoritesQstCount = exampaperRecordService.getMyFavoritesQuestionCount(this.getQueryFavoritesCondition());
			wrongQstCount = optRecordService.getErrorQstCountBycusId(userId);
			this.getQueryExampaperRecordCondition().setCusId(userId);
			this.getQueryExampaperRecordCondition().setSubject_id(subjectIdweb);
			myPaperCount = exampaperRecordService.getMyPapaerCount(getQueryExampaperRecordCondition());
			myQstCount=this.qstService.getRightPercent(userId);
			
			//错题记录
			qstPaper = this.qstService.getCurrentErrorQst(subjectIdweb);
			Iterator it = qstPaper.iterator();
			while(it.hasNext()){
				Qst obj = (Qst) it.next();
				obj.setQstContent(FunctionUtil.Html2Text(obj.getQstContent()).replaceAll("&nbsp;", ""));
				if(obj.getQstContent().length() > 32){
					obj.setQstContent(obj.getQstContent().substring(0, 32)+"......");
				}
				errorQstList.add(obj);
			}
			
			
		   /* this.errorQstTop3=this.optRecordService.getErrorQstTop3(queryOptRecordCondition);
		    for(OptRecord re:errorQstTop3){
		    	if(re.getEpType()==1){re.setEpName("真题测试");}
		    	if(re.getEpType()==2){re.setEpName("仿真测试");}
		    	if(re.getEpType()==3){re.setEpName("单元测试");}
		    	if(re.getEpType()==4){re.setEpName("专题测试");}
		    	re.setQstContext(re.getQstContext().replaceAll("style", "styl"));
		    	re.setQstContext(re.getQstContext().replaceAll("<br>", ""));
		    	re.setQstContext(re.getQstContext().replaceAll("<br/>", ""));
		    	re.setQstContext(re.getQstContext().replaceAll("span", "font"));
		    	re.setQstContext(re.getQstContext().replaceAll("   ","  "));
		    }*/
		   
			this.getQueryExampaperRecordCondition().setPageSize(7);
			this.getPage().setPageSize(7);
			this.setPageUrlParms();
			}catch(Exception e){
			logger.error("ExamWebAction.getExamHistroy",e);
			//return ERROR;
		}
		
		return "getMyError";
	}
	
	public int getMyQstCount() {
		return myQstCount;
	}
	public void setMyQstCount(int myQstCount) {
		this.myQstCount = myQstCount;
	}
	public String getErrorQst(){
		
		if(StringUtils.isNotBlank(lastQstId)){
			qstPage=this.qstService.getQstById(Integer.parseInt(lastQstId));
			qstPage.setQstContent((FunctionUtil.Html2Text(qstPage.getQstContent())));
			qstPage.setWrongJude(FunctionUtil.Html2Text(qstPage.getWrongJude()));
			
			this.options=this.optionsService.getOptionsListByQstId(qstPage.getQstId());
			exam=this.exampaperService.getExampaperById(qstPage.getEpId());
			if(options!=null){
				for(Options op:options){
					op.setOptContent(FunctionUtil.Html2Text(op.getOptContent()));
				}
				qstPage.setOptions(options);
			}
			
		}
		return "getErrorQst";
	}
	
	

	public Exampaper getExam() {
		return exam;
	}
	
	public void setExam(Exampaper exam) {
		this.exam = exam;
	}

	public IExampaper getExampaperService() {
		return exampaperService;
	}

	public void setExampaperService(IExampaper exampaperService) {
		this.exampaperService = exampaperService;
	}

	public QueryExampaperCondition getQueryExampaperCondition() {
		if (queryExampaperCondition == null) {
			queryExampaperCondition = new QueryExampaperCondition();
		}
		return queryExampaperCondition;
	}

	public void setQueryExampaperCondition(
			QueryExampaperCondition queryExampaperCondition) {
		this.queryExampaperCondition = queryExampaperCondition;
	}

	public List<Exampaper> getExampaperList() {
		return exampaperList;
	}

	public void setExampaperList(List<Exampaper> exampaperList) {
		this.exampaperList = exampaperList;
	}

	public IGrade getGradeService() {
		return gradeService;
	}

	public void setGradeService(IGrade gradeService) {
		this.gradeService = gradeService;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public String getScoreJudgment() {
		return scoreJudgment;
	}

	public void setScoreJudgment(String scoreJudgment) {
		this.scoreJudgment = scoreJudgment;
	}

	public Map<Integer, String> getExamlevel() {
		return examlevel;
	}

	public void setExamlevel(Map<Integer, String> examlevel) {
		this.examlevel = examlevel;
	}

	public String getQstIds() {
		return qstIds;
	}

	public void setQstIds(String qstIds) {
		this.qstIds = qstIds;
	}

	public String getZongping1() {
		return zongping1;
	}

	public void setZongping1(String zongping1) {
		this.zongping1 = zongping1;
	}

	public String getZongping2() {
		return zongping2;
	}

	public void setZongping2(String zongping2) {
		this.zongping2 = zongping2;
	}

	public String getZongping3() {
		return zongping3;
	}

	public void setZongping3(String zongping3) {
		this.zongping3 = zongping3;
	}

	public String getZongping4() {
		return zongping4;
	}

	public void setZongping4(String zongping4) {
		this.zongping4 = zongping4;
	}

	public String getZongping5() {
		return zongping5;
	}

	public void setZongping5(String zongping5) {
		this.zongping5 = zongping5;
	}

	public String getZongping6() {
		return zongping6;
	}

	public void setZongping6(String zongping6) {
		this.zongping6 = zongping6;
	}

	public String getZongping7() {
		return zongping7;
	}

	public void setZongping7(String zongping7) {
		this.zongping7 = zongping7;
	}

	public String getZongping8() {
		return zongping8;
	}

	public void setZongping8(String zongping8) {
		this.zongping8 = zongping8;
	}

	public String getZongping9() {
		return zongping9;
	}

	public void setZongping9(String zongping9) {
		this.zongping9 = zongping9;
	}

	public IReviews getReviewsService() {
		return reviewsService;
	}

	public void setReviewsService(IReviews reviewsService) {
		this.reviewsService = reviewsService;
	}

	public IQst getQstService() {
		return qstService;
	}

	public void setQstService(IQst qstService) {
		this.qstService = qstService;
	}

	public IOptions getOptionsService() {
		return optionsService;
	}

	public void setOptionsService(IOptions optionsService) {
		this.optionsService = optionsService;
	}

	public IQstPic getQstPicService() {
		return qstPicService;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public void setQstPicService(IQstPic qstPicService) {
		this.qstPicService = qstPicService;
	}

	public QueryQstCondition getQueryQstCondition() {

		if (queryQstCondition == null) {
			queryQstCondition = new QueryQstCondition();
		}
		return queryQstCondition;
	}

	public void setQueryQstCondition(QueryQstCondition queryQstCondition) {
		this.queryQstCondition = queryQstCondition;
	}


	public List<Qst> getQstPaper() {
		return qstPaper;
	}

	public void setQstPaper(List<Qst> qstPaper) {
		this.qstPaper = qstPaper;
	}

	public String getSubRlt() {
		return subRlt;
	}

	public void setSubRlt(String subRlt) {
		this.subRlt = subRlt;
	}

	public float getTotalScroe() {
		return totalScroe;
	}

	public void setTotalScroe(float totalScroe) {
		this.totalScroe = totalScroe;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public int[] getExampaperIds() {
		return exampaperIds;
	}

	public void setExampaperIds(int[] exampaperIds) {
		this.exampaperIds = exampaperIds;
	}

	public List<Reviews> getReviewsList() {
		return reviewsList;
	}

	public void setReviewsList(List<Reviews> reviewsList) {
		this.reviewsList = reviewsList;
	}

	public int[] getQst() {
		return qst;
	}

	public void setQst(int[] qst) {
		this.qst = qst;
	}


	public IExampaperRecord getExampaperRecordService() {
		return exampaperRecordService;
	}

	public void setExampaperRecordService(
			IExampaperRecord exampaperRecordService) {
		this.exampaperRecordService = exampaperRecordService;
	}

	public ExamAnalysisDTO getExamAnalysisDTO() {
		return examAnalysisDTO;
	}

	public void setExamAnalysisDTO(ExamAnalysisDTO examAnalysisDTO) {
		this.examAnalysisDTO = examAnalysisDTO;
	}

	public List<ExampaperRecord> getExampaperRecordList() {
		return exampaperRecordList;
	}

	public void setExampaperRecordList(List<ExampaperRecord> exampaperRecordList) {
		this.exampaperRecordList = exampaperRecordList;
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

	public IOptRecord getOptRecordService() {
		return optRecordService;
	}

	public void setOptRecordService(IOptRecord optRecordService) {
		this.optRecordService = optRecordService;
	}

	public String getUsetime() {
		return usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
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

	public QueryExampaperRecordCondition getQueryExampaperRecordCondition() {
		if (queryExampaperRecordCondition == null) {
			queryExampaperRecordCondition = new QueryExampaperRecordCondition();
		}
		return queryExampaperRecordCondition;
	}
	public void setQueryExampaperRecordCondition(
			QueryExampaperRecordCondition queryExampaperRecordCondition) {
		this.queryExampaperRecordCondition = queryExampaperRecordCondition;
	}

	public UserExamPaperDTO getUserExamPaper() {
		return userExamPaper;
	}

	public void setUserExamPaper(UserExamPaperDTO userExamPaper) {
		this.userExamPaper = userExamPaper;
	}

	public int getUserEpId() {
		return userEpId;
	}

	public void setUserEpId(String userEpId) {
		try {
			this.userEpId = Integer.parseInt(userEpId.trim());
		} catch (NumberFormatException e) {
			this.userEpId = 0;
		}
	}

	public List<CourseSortListDTO> getCourseSortListDTOList() {
		return courseSortListDTOList;
	}

	public void setCourseSortListDTOList(
			List<CourseSortListDTO> courseSortListDTOList) {
		this.courseSortListDTOList = courseSortListDTOList;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public int getKpoint() {
		return kpoint;
	}

	public void setKpoint(int kpoint) {
		this.kpoint = kpoint;
	}

	public int getEpids() {
		return epids;
	}

	public void setEpids(int epids) {
		this.epids = epids;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public Kpoint getKPonint() {
		return kPonint;
	}

	public void setKPonint(Kpoint ponint) {
		kPonint = ponint;
	}

	public float getLv() {
		return lv;
	}

	public void setLv(float lv) {
		this.lv = lv;
	}

	public float getAccuracy1() {
		return accuracy1;
	}

	public void setAccuracy1(float accuracy1) {
		this.accuracy1 = accuracy1;
	}

	public int getRenshu() {
		return renshu;
	}

	public void setRenshu(int renshu) {
		this.renshu = renshu;
	}
	
		public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}


	public IQstKb getQstKbService() {
		return qstKbService;
	}

	public void setQstKbService(IQstKb qstKbService) {
		this.qstKbService = qstKbService;
	}

	public List<Knowledge> getQblist() {
		return qblist;
	}

	public void setQblist(List<Knowledge> qblist) {
		this.qblist = qblist;
	}

	public int getShuliang() {
		return shuliang;
	}

	public void setShuliang(int shuliang) {
		this.shuliang = shuliang;
	}

	public IKnowledge getKnowledgeService() {
		return knowledgeService;
	}

	public void setKnowledgeService(IKnowledge knowledgeService) {
		this.knowledgeService = knowledgeService;
	}

	public List<GetQSTEXAMKB> getGetlist() {
		return getlist;
	}

	public void setGetlist(List<GetQSTEXAMKB> getlist) {
		this.getlist = getlist;
	}

	public String getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}

	public Map getStudyTypePram() {
		return studyTypePram;
	}

	public void setStudyTypePram(Map studyTypePram) {
		this.studyTypePram = studyTypePram;
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

	public List<Qst> getQstlist() {
		return qstlist;
	}

	public void setQstlist(List<Qst> qstlist) {
		this.qstlist = qstlist;
	}

	public int getCusid() {
		return cusid;
	}

	public void setCusid(int cusid) {
		this.cusid = cusid;
	}



	public int getJifen() {
		return jifen;
	}


	public void setJifen(int jifen) {
		this.jifen = jifen;
	}


	public List<ExampaperRecord> getExamrecord() {
		return examrecord;
	}


	public void setExamrecord(List<ExampaperRecord> examrecord) {
		this.examrecord = examrecord;
	}


	public List<Exampaper> getExamrecordqita() {
		return examrecordqita;
	}


	public void setExamrecordqita(List<Exampaper> examrecordqita) {
		this.examrecordqita = examrecordqita;
	}


	public int getMb() {
		return mb;
	}


	public void setMb(int mb) {
		this.mb = mb;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public CashRecord getCashrecord() {
		if(cashrecord==null){
			cashrecord=new CashRecord();
		}
		return cashrecord;
	}

	public void setCashrecord(CashRecord cashrecord) {
		this.cashrecord = cashrecord;
	}

	public QueryCourseCondition getQueryCourseCondition() {
		if(queryCourseCondition==null){
			queryCourseCondition=new QueryCourseCondition();
		}
		return queryCourseCondition;
	}

	public void setQueryCourseCondition(QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}

	public List<Coursesort> getCoursesortList() {
		return coursesortList;
	}

	public void setCoursesortList(List<Coursesort> coursesortList) {
		this.coursesortList = coursesortList;
	}

	public QueryCoursesortCondition getQueryCoursesortCondition() {
		if(queryCoursesortCondition==null){
			queryCoursesortCondition=new QueryCoursesortCondition();
		}
		return queryCoursesortCondition;
	}

	public void setQueryCoursesortCondition(
			QueryCoursesortCondition queryCoursesortCondition) {
		this.queryCoursesortCondition = queryCoursesortCondition;
	}

	public int getWeida() {
		return weida;
	}

	public void setWeida(int weida) {
		this.weida = weida;
	}

	public ExampaperRecord getExampaperRecord() {
		if(exampaperRecord==null){
			exampaperRecord=new ExampaperRecord();
		}
		return exampaperRecord;
	}

	public void setExampaperRecord(ExampaperRecord exampaperRecord) {
		this.exampaperRecord = exampaperRecord;
	}

	public int getPaiming() {
		return paiming;
	}

	public void setPaiming(int paiming) {
		this.paiming = paiming;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public IPlanclock getPlanclockService() {
		return planclockService;
	}

	public void setPlanclockService(IPlanclock planclockService) {
		this.planclockService = planclockService;
	}

	public UserMsg getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(UserMsg userMsg) {
		this.userMsg = userMsg;
	}

	public List<ExampaperCountLastDay> getExampaperCountLastDay() {
		return exampaperCountLastDay;
	}

	public void setExampaperCountLastDay(
			List<ExampaperCountLastDay> exampaperCountLastDay) {
		this.exampaperCountLastDay = exampaperCountLastDay;
	}

	public int getUserzongjifen() {
		return userzongjifen;
	}

	public void setUserzongjifen(int userzongjifen) {
		this.userzongjifen = userzongjifen;
	}

	public int getSubjectweb() {
		return subjectweb;
	}

	public void setSubjectweb(int subjectweb) {
		this.subjectweb = subjectweb;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public float getMaxfen() {
		return maxfen;
	}

	public void setMaxfen(float maxfen) {
		this.maxfen = maxfen;
	}

	public float getMinfen() {
		return minfen;
	}

	public void setMinfen(float minfen) {
		this.minfen = minfen;
	}

	public float getAvgfen() {
		return avgfen;
	}

	public void setAvgfen(float avgfen) {
		this.avgfen = avgfen;
	}

	public int getIsDone() {
		return isDone;
	}

	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}

	public List<SubjectExampaperNumDTO> getSubjectExampaperNum() {
		return subjectExampaperNum;
	}

	public void setSubjectExampaperNum(
			List<SubjectExampaperNumDTO> subjectExampaperNum) {
		this.subjectExampaperNum = subjectExampaperNum;
	}

	public int getEpid() {
		return epid;
	}

	public void setEpid(String epid) {
		try {
			this.epid = Integer.parseInt(epid.trim());
		} catch (NumberFormatException e) {
			this.epid = 0;
		}
	}
	
	public int getSubjectIdweb() {
		return subjectIdweb;
	}

	public void setSubjectIdweb(String subjectIdweb) {
		try {
			this.subjectIdweb = Integer.parseInt(subjectIdweb.trim());
		} catch (NumberFormatException e) {
			this.subjectIdweb = 0;
		}
	}
	public ISellWay getSellWayService() {
		return sellWayService;
	}
	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}
	public QstRightPercent getQstRightPercent() {
		return qstRightPercent;
	}
	public void setQstRightPercent(QstRightPercent qstRightPercent) {
		this.qstRightPercent = qstRightPercent;
	}
	public List<Qst> getErrorQstList() {
		return errorQstList;
	}
	public void setErrorQstList(List<Qst> errorQstList) {
		this.errorQstList = errorQstList;
	}
	public List<ExampaperQstDTO> getExamQstList() {
		return examQstList;
	}
	public void setExamQstList(List<ExampaperQstDTO> examQstList) {
		this.examQstList = examQstList;
	}
	public List<UserQst> getQstOptlist() {
		return qstOptlist;
	}
	public void setQstOptlist(List<UserQst> qstOptlist) {
		this.qstOptlist = qstOptlist;
	}
	public Integer getCorrectNum() {
		return correctNum;
	}
	public void setCorrectNum(Integer correctNum) {
		this.correctNum = correctNum;
	}
	public Integer getCorrectRate() {
		return correctRate;
	}
	public void setCorrectRate(Integer correctRate) {
		this.correctRate = correctRate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Integer getExamLength() {
		return examLength;
	}
	public void setExamLength(Integer examLength) {
		this.examLength = examLength;
	}
	public Qst getQstPage() {
		return qstPage;
	}
	public void setQstPage(Qst qstPage) {
		this.qstPage = qstPage;
	}
	public List<Options> getOptions() {
		return options;
	}
	public void setOptions(List<Options> options) {
		this.options = options;
	}
	public ISysNode getSysNodeService() {
		return sysNodeService;
	}
	public void setSysNodeService(ISysNode sysNodeService) {
		this.sysNodeService = sysNodeService;
	}

	public QuerySysNodeCondition getQuerySysNodeCondition() {
		if(querySysNodeCondition==null){
			querySysNodeCondition=new QuerySysNodeCondition();
		}
		return querySysNodeCondition;
	}
	public void setQuerySysNodeCondition(QuerySysNodeCondition querySysNodeCondition) {
		this.querySysNodeCondition = querySysNodeCondition;
	}
	public List<SysNode> getSysNodeList() {
		return sysNodeList;
	}
	public void setSysNodeList(List<SysNode> sysNodeList) {
		this.sysNodeList = sysNodeList;
	}

	public QueryOptRecordCondition getQueryOptRecordCondition() {
		if(queryOptRecordCondition==null){
			queryOptRecordCondition=new QueryOptRecordCondition();
		}
		return queryOptRecordCondition;
	}
	public void setQueryOptRecordCondition(
			QueryOptRecordCondition queryOptRecordCondition) {
		this.queryOptRecordCondition = queryOptRecordCondition;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public QueryFavoritesCondition getQueryFavoritesCondition() {
		if(queryFavoritesCondition == null){
			queryFavoritesCondition = new QueryFavoritesCondition();
		}
		return queryFavoritesCondition;
	}
	public void setQueryFavoritesCondition(
			QueryFavoritesCondition queryFavoritesCondition) {
		this.queryFavoritesCondition = queryFavoritesCondition;
	}
	public int getResult_type() {
		return result_type;
	}
	public void setResult_type(int resultType) {
		result_type = resultType;
	}
	public String getLastQstId() {
		return lastQstId;
	}
	public void setLastQstId(String lastQstId) {
		this.lastQstId = lastQstId;
	}
	public String getResultCur() {
		return resultCur;
	}
	public void setResultCur(String resultCur) {
		this.resultCur = resultCur;
	}
	

	public int getQstId() {
		return qstId;
	}
	public void setQstId(int qstId) {
		this.qstId = qstId;
	}
	public List<OptRecord> getErrorQstTop3() {
		return errorQstTop3;
	}
	public void setErrorQstTop3(List<OptRecord> errorQstTop3) {
		this.errorQstTop3 = errorQstTop3;
	}
	

}
