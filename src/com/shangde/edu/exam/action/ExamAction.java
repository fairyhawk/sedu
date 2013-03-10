package com.shangde.edu.exam.action;

import java.io.File;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.exam.condition.QueryExampaperCondition;
import com.shangde.edu.exam.condition.QueryQstCondition;
import com.shangde.edu.exam.condition.QueryQstmiddleCondition;
import com.shangde.edu.exam.condition.QueryReviewsCondition;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.domain.QstKb;
import com.shangde.edu.exam.domain.Qstmiddle;
import com.shangde.edu.exam.domain.Reviews;
import com.shangde.edu.exam.service.IExampaper;
import com.shangde.edu.exam.service.IOptions;
import com.shangde.edu.exam.service.IQst;
import com.shangde.edu.exam.service.IQstKb;
import com.shangde.edu.exam.service.IQstPic;
import com.shangde.edu.exam.service.IQstmiddle;
import com.shangde.edu.exam.service.IReviews;
import com.shangde.edu.kb.condition.QueryProfessionalCondition;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.service.IProfessional;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IGrade;
import com.shangde.edu.sys.service.ISubject;

/**
 * 考试Action
 * @author chenshuai wanglei
 */
public class ExamAction extends CommonAction implements Serializable {
	
	private static final int BUFFER_SIZE=16*1024;
	/**
	 * 序列号id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 考试��ӿ�
	 */
	private Exampaper exam;
	
	/**
	 * 考试和试题中表条件
	 */
	private QueryQstmiddleCondition queryQstmiddleCondition;
	
	/**
	 * 试题id
	 */
	private int qstId;
	/**
	 * 试题
	 */
	private Qst qqst;
	
	/**
	 * 考试服务
	 */
	private IExampaper exampaperService;
	
	/**
	 * 总评服务
	 */
	private IReviews  reviewsService;
	
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	/**
	 * 试题服务
	 */
	private IQst qstService;
	
	/**
	 * 选项服务
	 */
	private IOptions optionsService;
	
	/**
	 * 声名知识结构服务
	 */
	private IProfessional professionalService;
	
	
	/**
	 * 试卷和试题中间表服务
	 */
	private IQstmiddle qstmiddleService;
	/**
	 * 配置 ������
	 */
	private ConfigService configurator;
	
	/**
	 * �考试查询条件
	 */
	private QueryExampaperCondition queryExampaperCondition;
	
	/**
	 * 查询条件
	 */
	private String searchKey;
	
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
	 * author:hhq
	 * 知识库和试题服务
	 */
	private IQstKb qstKbService;
	
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
	
	/**
	 * 考试难度系数
	 */
	private Map<Integer,String> examlevel = new HashMap<Integer, String>();
	
	/**
	 * 上传试题图片集合
	 */
	private List<File> shitiPic = new ArrayList<File>();
	
	/**
	 * 试题图片名称集合
	 */
	private List<String> shitiPicFileName = new ArrayList<String>();
	
	/**
	 * 查询条件
	 */
	private QueryQstCondition queryQstCondition;
	/**
	 * 试卷id
	 */
	private int epid;
	
	private List<Qst> qstPaper;
	private List<Qst> qstlisttb;
	/**
	 * 获取前台过来的答案
	 */
	private String subRlt;
	/**
	 * 总得分
	 */
	private float totalScroe=0f;
	
	private String scoreJudgment;
	
	private List<Reviews> reviewsList ;
	
	private List<Qst> wrongQsts=new ArrayList<Qst>();
	

	/**
	 * 专业id
	 */
	private int subjectId;
	
	/**
	 * 知识库专业表
	 */
	private List<Professional> professionalList=new ArrayList<Professional>();
	
	/**
	 * 试题IDS
	 */
	private String qstIds;
	/**
	 * 总评1
	 */
	private String zongping1;
	/**
	 * 总评2
	 */
	private String zongping2;
	/**
	 * 总评3
	 */
	private String zongping3;
	/**
	 * 总评4
	 */
	private String zongping4;
	/**
	 * 总评5
	 */
	private String zongping5;
	/**
	 * 总评6
	 */
	private String zongping6;
	/**
	 * 总评7
	 */
	private String zongping7;
	/**
	 * 总评8
	 */
	private String zongping8;
	/**
	 * 总评9
	 */
	private String zongping9;
	
	/**
	 * �课程分类集合����б�
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 试卷IDs 冻结时用到
	 */
	private int[] exampaperIds;
	
	/**
	 * 试题IDs
	 */
	private int[] qst;
	
	/**
	 * 通过试卷id和试题类型查询单选题列表
	 */
	private List<Qstmiddle> danxuan;
	/**
	 * 通过试卷id和试题类型查询多选题列表
	 */
	private List<Qstmiddle> duoxuan;
	/**
	 * 通过试卷id和试题类型查询判断题列表
	 */
	private List<Qstmiddle> panduan;
	/**
	 * 通过试卷id和试题类型查询材料分析题列表
	 */
	private List<Qstmiddle> cailiao;
	/**
	 * 通过试卷id和试题类型查询图表题列表
	 */
	private List<Qstmiddle> tubiao;
	/**
	 * 通过试卷id和试题类型查询主观题列表
	 */
	private List<Qstmiddle> zhuguan;
	
	/**
	 * 试卷名称
	 */
	String testname1;
	/**
	 * 测试类型
	 */
	String testeptype1;
	
	String course1 ;
	
	
	/**
	 * 转向试题更新
	 * @author 何海强
	 * @return
	 */
	
	public String toUpdateQst(){
		try {
			qqst=qstService.getQstById(qstId);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
		return "toupdateqst";
	}
	
	/**
	 * 试题更新
	 * @author 何海强
	 */
	public String QstUpdate(){
		 try {
			Qst question =null;//问题
			Options option = null;//选项	
			question = qstService.getQstById(qqst.getQstId());
			
			//修改试题--------------------------------------------
			question.setScore(qqst.getScore());
			question.setLevel(qqst.getLevel());
			question.setQstContent(qqst.getQstContent());
			question.setWrongJude((String)servletRequest.getParameter("pingyu" + question.getQstId()));
			
//		if(question.getQstType() == 0){
//			question.setIsAsr((String)servletRequest.getParameter("right" + question.getQstId()));
//		}else if(question.getQstType() == 1){//主观题时，答案存储于选项表中
//			question.setIsAsr("main");
//		}
			
			if(question.getQstType() == 0){//客观题时，答案存储于选项表中
				question.setIsAsr((String)servletRequest.getParameter("right" +  question.getQstId()));
			}else if(question.getQstType() == 2){//多选题，答案存储于选项表中
				String [] isAsr=servletRequest.getParameterValues("right" +  question.getQstId());
				StringBuffer buf = new StringBuffer();
				for(int i1=0;i1<isAsr.length;i1++)
				{
					buf.append(isAsr[i1]);
				}
				question.setIsAsr(buf.toString());
			}else if(question.getQstType() == 4){// 材料分析题，答案存储于选项表中
				String [] isAsr=servletRequest.getParameterValues("right" +  question.getQstId());
				StringBuffer buf = new StringBuffer();
				for(int i1=0;i1<isAsr.length;i1++)
				{
					buf.append(isAsr[i1]);
				}
				question.setIsAsr(buf.toString());
			}else if(question.getQstType() == 5){//图表题，答案存储于选项表中
				String [] isAsr=servletRequest.getParameterValues("right" +  question.getQstId());
				StringBuffer buf = new StringBuffer();
				for(int i1=0;i1<isAsr.length;i1++)
				{
					buf.append(isAsr[i1]);
				}
				question.setIsAsr(buf.toString());
			}else if(question.getQstType() == 3){//判断，答案存储于选项表中
				question.setIsAsr((String)servletRequest.getParameter("right" +  question.getQstId()));
			}else if(question.getQstType() == 6){
				question.setIsAsr("main");
			}
			 
			question.setCtPerson("A");
			
			qstService.updateQst(question);//修改试题完成
			
			if(question.getQstType() == 0){//客观题
				List<Options> options = question.getOptions();
				for(int j = 0; j < options.size(); j ++){
					option = options.get(j);
					option.setOptContent(servletRequest.getParameter("optioncontent" + option.getOptId()).replaceAll("\r\n", "<br>"));
					optionsService.updateOptions(option);
				}
				
			}else if(question.getQstType() == 2){//多选题
				List<Options> options = question.getOptions();
				for(int j = 0; j < options.size(); j ++){
					option = options.get(j);
					option.setOptContent(servletRequest.getParameter("optioncontent" + option.getOptId()).replaceAll("\r\n", "<br>"));
					optionsService.updateOptions(option);
				}
				
			} else if(question.getQstType() == 3){//判断题
				List<Options> options = question.getOptions();
				for(int j = 0; j < options.size(); j ++){
					option = options.get(j);
					option.setOptContent(servletRequest.getParameter("optioncontent" + option.getOptId()).replaceAll("\r\n", "<br>"));
					optionsService.updateOptions(option);
				}
				
			} else if(question.getQstType() == 4){//材料解析题
				List<Options> options = question.getOptions();
				for(int j = 0; j < options.size(); j ++){
					option = options.get(j);
					option.setOptContent(servletRequest.getParameter("optioncontent" + option.getOptId()).replaceAll("\r\n", "<br>"));
					optionsService.updateOptions(option);
				}
				
			}else if(question.getQstType() == 5){//图表题
				List<Options> options = question.getOptions();
				for(int j = 0; j < options.size(); j ++){
					option = options.get(j);
					option.setOptContent(servletRequest.getParameter("optioncontent" + option.getOptId()).replaceAll("\r\n", "<br>"));
					optionsService.updateOptions(option);
				}
				
			}else if(question.getQstType() == 6){//主观题
				List<Options> options = question.getOptions();
				option = options.get(0);
				option.setOptContent(servletRequest.getParameter("optioncontent"+ option.getOptId()));
				
				optionsService.updateOptions(option);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
		return "addExam";
	}
	
	/**
	 * 试题列表
	 * @author 何海强
	 */
	public String QstListTB(){
		
		try {
			qstlisttb=qstService.getQstByIdList(epid);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
		return "qstlisttb";
	}
	
	/**
	 * 转向添加试题页面
	 * @author 何海强
	 * @return
	 */
	
	public String toaddQst(){
//		subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
//		examlevel = configurator.getExamlevel();
//		courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
		
		try {
			exam = exampaperService.getExampaperById(epid);
			if(exam.getQstmiddlecount()==0){
				exam.setEpTotelScore(0);
			}
//		professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
			this.getQueryQstmiddleCondition().setEpId(epid);
			//单选查询
			this.getQueryQstmiddleCondition().setQstType(1);
			danxuan=qstmiddleService.getQstmiddleList(queryQstmiddleCondition);
			//多选查询
			this.getQueryQstmiddleCondition().setQstType(2);
			duoxuan=qstmiddleService.getQstmiddleList(queryQstmiddleCondition);
			//判断查询
			this.getQueryQstmiddleCondition().setQstType(3);
			panduan=qstmiddleService.getQstmiddleList(queryQstmiddleCondition);
			
			//材料
			this.getQueryQstmiddleCondition().setQstType(4);
			cailiao=qstmiddleService.getQstmiddleList(queryQstmiddleCondition);
			
			//图表
			this.getQueryQstmiddleCondition().setQstType(5);
			tubiao=qstmiddleService.getQstmiddleList(queryQstmiddleCondition);
			
			//主关
			this.getQueryQstmiddleCondition().setQstType(6);
			zhuguan=qstmiddleService.getQstmiddleList(queryQstmiddleCondition);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
		return "UpdateQst";
	}
	
	/**
	 * 通过试卷id添加试卷
	 * @author 何海强
	 * @return 
	 */
	public String Qstadd(){
		try{
				String ids[] = qstIds.split(",");//分成 是否上传 :id:主观客观 的形式
				
				Qst question = null;//问题
				Options option = null;//选项
				String tempid = "";//页面传来id
				int temptype=0;//主管客观条件 0客观，1主观
				String[] typeAndId = null;
				
				for(int i = 0; i < ids.length; i ++){
					question = new Qst();
					typeAndId = ids[i].split(":");//获取type 和Id;
					tempid = typeAndId[1];//第几道题
					temptype = Integer.parseInt(typeAndId[2]);
					
					question.setEpId(exam.getEpId());//添加试题--------------------------------------------
					question.setScore(Float.parseFloat(servletRequest.getParameter("score" + tempid)));
					question.setQstContent((String)servletRequest.getParameter("shiti" + tempid).replaceAll("\r\n", "<br>"));
					question.setWrongJude((String)servletRequest.getParameter("pingyu" + tempid));
					question.setQstType(temptype);
					
					if(temptype == 0){//客观题时，答案存储于选项表中
						question.setIsAsr((String)servletRequest.getParameter("right" + tempid));
					}else if(temptype == 2){//多选题，答案存储于选项表中
						String [] isAsr=servletRequest.getParameterValues("right" + tempid);
						String asr="";
						for(int i1=0;i1<isAsr.length;i1++)
						{
							asr+=isAsr[i1];
						}
						question.setIsAsr(asr);
					}
					else if(temptype == 4){//多选题，答案存储于选项表中
						String [] isAsr=servletRequest.getParameterValues("right" + tempid);
						String asr="";
						for(int i1=0;i1<isAsr.length;i1++)
						{
							asr+=isAsr[i1];
						}
						question.setIsAsr(asr);
					}
					else if(temptype == 5){//多选题，答案存储于选项表中
						String [] isAsr=servletRequest.getParameterValues("right" + tempid);
						String asr="";
						for(int i1=0;i1<isAsr.length;i1++)
						{
							asr+=isAsr[i1];
						}
						question.setIsAsr(asr);
					}
					else if(temptype == 3){//判断，答案存储于选项表中
						question.setIsAsr((String)servletRequest.getParameter("right" + tempid));
					}else if(temptype == 6){
						question.setIsAsr("main");
					}
					question.setQstType(temptype);//试题的类型（单选，主观）
					
					question.setCtPerson("A");
					question.setLevel(Integer.parseInt(servletRequest.getParameter("level" + tempid)));
					
					int qstId = qstService.addQst(question);//试题ID
					
					if(temptype == 0){//客观题
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("A");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("B");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("C");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("D");
						optionsService.addOptions(option);
						
						if(!servletRequest.getParameter("E" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("E");
						optionsService.addOptions(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("F");
						optionsService.addOptions(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("G");
						optionsService.addOptions(option);
						}
					}else if(temptype == 2){//多选题
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("A");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("B");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("C");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("D");
						optionsService.addOptions(option);
						
						if(!servletRequest.getParameter("E" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("E");
						optionsService.addOptions(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("F");
						optionsService.addOptions(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("G");
						optionsService.addOptions(option);
						}
					}else if(temptype == 4){//材料分析题
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("A");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("B");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("C");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("D");
						optionsService.addOptions(option);
						
						if(!servletRequest.getParameter("E" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("E");
						optionsService.addOptions(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("F");
						optionsService.addOptions(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("G");
						optionsService.addOptions(option);
						}
					}else if(temptype == 5){//图表题
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("A");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("B");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("C");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("D");
						optionsService.addOptions(option);
						
						if(!servletRequest.getParameter("E" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("E");
						optionsService.addOptions(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("F");
						optionsService.addOptions(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("G");
						optionsService.addOptions(option);
						}
					}
					else if(temptype == 3){//判断题
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("A");
						optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("B");
						optionsService.addOptions(option);
						
						
					}
					else if(temptype == 6){//主观题
						option = new Options();
						option.setOptContent(servletRequest.getParameter("right" + tempid).replaceAll("\r\n", "<br>"));
						option.setQstId(qstId);
						option.setOptOrder("main");
						optionsService.addOptions(option);
					}
					
					String zhishidianid=servletRequest.getParameter("qstzkid"+tempid);
					
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
				}
				
				//更新试卷分值
			
				exampaperService.updateExampaper(exam);
				
				return "addExam";
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 预添加考试
	 * 到达更新考试页面，提供试卷原来的信息
	 * URL中需要的参数为exam.epId
	 * @return
	 */
	public String toUpdateExam(){
		try{
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			examlevel = configurator.getExamlevel();
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
			exam = exampaperService.getExampaperById(exam.getEpId());
			professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
			this.getQueryQstCondition().setEpId(exam.getEpId());//获取试题集合
			qstPaper = qstService.getQstList(queryQstCondition);
			
			QueryReviewsCondition qrc = new QueryReviewsCondition();//获取评论集合
			qrc.setEpId(exam.getEpId());
			reviewsList = reviewsService.getReviewsList(qrc);
			if(reviewsList != null){
				Reviews reviewTemp = null;
				for(int i = 0; i < reviewsList.size(); i ++){
					reviewTemp = reviewsList.get(i);
					
					if(reviewTemp.getEvaType() == 1){
						zongping1 = reviewTemp.getRvInfo();
					}else if(reviewTemp.getEvaType() == 2){
						zongping2 = reviewTemp.getRvInfo();
					}else if(reviewTemp.getEvaType() == 3){
						zongping3 = reviewTemp.getRvInfo();
					}else if(reviewTemp.getEvaType() == 4){
						zongping4 = reviewTemp.getRvInfo();
					}else if(reviewTemp.getEvaType() == 5){
						zongping5 = reviewTemp.getRvInfo();
					}else if(reviewTemp.getEvaType() == 6){
						zongping6 = reviewTemp.getRvInfo();
					}else if(reviewTemp.getEvaType() == 7){
						zongping7 = reviewTemp.getRvInfo();
					}else if(reviewTemp.getEvaType() == 8){
						zongping8 = reviewTemp.getRvInfo();
					}else if(reviewTemp.getEvaType() == 9){
						zongping9 = reviewTemp.getRvInfo();
					}
				}
			}
			return "toUpdateExam";
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 修改试卷
	 * 将添加的试卷进行更新，根据试卷ID
	 * URL中需要的参数为exam.epId
	 * @return
	 */
	public String updateExam(){
		try{
			
			Exampaper exp=exampaperService.getExampaperById(exam.getEpId());
			String a=servletRequest.getParameter("exam.qstmiddlecount");
			exam.setEpKeyword("key");
			exam.setUserId(26);
			exam.setQstmiddlecount(Integer.parseInt(a));
			exam.setJoinNum(exp.getJoinNum());
			exam.setEpTotelScore(exp.getEpTotelScore());
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			exam.setAuthor(users.getLoginName());
			exam.setLastEditTime(new Date());
			exampaperService.updateExampaper(exam);//修改试卷
			
			return "addExam";
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 预添加考试
	 * 进入添加试卷界面
	 * @return
	 */
	public String toAddExam(){
		try{
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
			examlevel = configurator.getExamlevel();
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			return "toAddExam";
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	
	public String toPreviewExampaper(){
		//获得当前登录用户ID
		int userId = getLoginUserId();
		this.getQueryExampaperCondition().setUserId(userId);
		this.getQueryExampaperCondition().setEpId(epid);
		exam=exampaperService.getExampaperPaperResult(this.getQueryExampaperCondition());
		return "toPreviewExampaper";
	}
	
	
	/**
	 * 批量冻结试卷
	 * 传入参数为试卷ID集合 
	 * @return
	 */
	public String freezeExampapers(){
		try{
			if(exampaperIds != null){
				Exampaper temp = null;
				for(int i = 0; i <exampaperIds.length ; i++){
					
					temp = exampaperService.getExampaperById(exampaperIds[i]);
					if(temp != null && temp.getEpState() == Exampaper.EXAMPAPER_DEFAULT_STATUS){
						temp.setEpState(Exampaper.EXAMPAPER_FREEZE_STATUS);
						exampaperService.updateExampaper(temp);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		
		return "freezeExampapers";
	}
	
	/**
	 * 列出试卷列表
	 * 根据查询条件（查询关键字，课程或知识点ID 可分别查询或组合查询）
	 * @return
	 */
	public String listExamPaperByCondition(){
		try{
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
			if(searchKey != null){
				this.getQueryExampaperCondition().setSearchKey(searchKey);
			}
			
			if(exam != null){
				this.getQueryExampaperCondition().setType(exam.getType());
				this.queryExampaperCondition.setKOrCouId(exam.getKOrCouId());
				
				this.queryExampaperCondition.setCid(exam.getCid());
			}else{
				this.getQueryExampaperCondition().setType(0);
				this.queryExampaperCondition.setKOrCouId(0);
			
				this.queryExampaperCondition.setCid(0);
			}
			String seackey=this.getQueryExampaperCondition().getSearchKey();
			if(seackey!=null){
				getQueryExampaperCondition().setSearchKey(URLDecoder.decode(getQueryExampaperCondition().getSearchKey().trim(), "UTF-8"));
			}
			this.getQueryExampaperCondition().setPageSize(20);
			setPage(exampaperService.listExamPaperByConditionexam(this.getQueryExampaperCondition()));
			this.getPage().setPageSize(20);
			setPageUrlParms();
			return "listExamPaperByCondition";
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 添加试卷
	 * 将添加的试卷信息添加到数据库中
	 * @return
	 */
	public String addExam(){
		try{
		
			exam.setEpKeyword("key");
			exam.setUserId(26);
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			exam.setAuthor(users.getLoginName());
			exam.setLastEditTime(new Date());
			int exampaperid = exampaperService.addExampaper(exam);//添加试卷
			exam = exampaperService.getExampaperById(exampaperid);
			
			return "toaddQst";
			
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 试卷阅览效果
	 */

	public String read(){
		try{
			
			testname1=exam.getEpName();//试卷名称
			testeptype1="";//测试类型
			
			course1 = "";//课程名称
			
			if(exam.getCourseId()==0)
			{
				exam=exampaperService.getExampaperById(exam.getEpId());
			}
			if(exam.getCourseId()!=-1)
			{
				
				course1 = courseService.getCourseById(exam.getCourseId()).getTitle();
				
			}
			
			
			List<Qst> aa=new ArrayList<Qst>();	
			
			if(exam.getEptype()==1)
			{
				testeptype1="仿真自测";
			}
			if(exam.getEptype()==2)
			{
				testeptype1="真题测试";
			}
			if(exam.getEptype()==3)
			{
				testeptype1="单元练习";
			}
			if(exam.getEptype()==4)
			{
				testeptype1="专题练习";
			}
			String ids[] = qstIds.split(",");//分成 是否上传 :id:主观客观 的形式
			
			Qst question = null;//问题
			Options option = null;//选项
			String tempid = "";//页面传来id
			int temptype=0;//试题类型
			String[] typeAndId = null;
			
			for(int i = 0; i < ids.length; i ++){
				question = new Qst();
				typeAndId = ids[i].split(":");//获取type 和Id;
				tempid = typeAndId[1];
				temptype = Integer.parseInt(typeAndId[2]);
				
				//添加试题--------------------------------------------
				question.setScore(Float.parseFloat(servletRequest.getParameter("score" + tempid).toString()));
				question.setQstContent((String)servletRequest.getParameter("shiti" + tempid).replaceAll("\r\n", "<br>"));
				question.setWrongJude((String)servletRequest.getParameter("pingyu" + tempid));
				question.setQstType(temptype);
				
				if(temptype == 6){//主观题时，答案存储于选项表中
					question.setIsAsr((String)servletRequest.getParameter("right" + tempid));
				}else if(temptype == 2){//多选题，答案存储于选项表中
					String [] isAsr=servletRequest.getParameterValues("right" + tempid);
					String asr="";
					for(int i1=0;i1<isAsr.length;i1++)
					{
						asr+=isAsr[i1];
					}
					question.setIsAsr(asr);
				}else if(temptype == 4){//材料题，答案存储于选项表中
					String [] isAsr=servletRequest.getParameterValues("right" + tempid);
					String asr="";
					for(int i1=0;i1<isAsr.length;i1++)
					{
						asr+=isAsr[i1];
					}
					question.setIsAsr(asr);
				}else if(temptype == 5){//图表题，答案存储于选项表中
					String [] isAsr=servletRequest.getParameterValues("right" + tempid);
					String asr="";
					for(int i1=0;i1<isAsr.length;i1++)
					{
						asr+=isAsr[i1];
					}
					question.setIsAsr(asr);
				}else if(temptype == 3){//判断，答案存储于选项表中
					question.setIsAsr((String)servletRequest.getParameter("right" + tempid));
				}else if(temptype == 6){
					question.setIsAsr("main");
				}
				question.setQstType(temptype);//试题的类型（单选，主观）
				
				question.setCtPerson("A");
				question.setLevel(Integer.parseInt(servletRequest.getParameter("level" + tempid)));
				
								
				
				List <Options> ss=new ArrayList<Options>();
				if(temptype == 0){//客观题
				
					option = new Options();//添加选项---------------------------------
					option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("A");
					ss.add(option);
					
					option = new Options();
					option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("B");
					ss.add(option);
					
					option = new Options();
					option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("C");
					ss.add(option);
					
					option = new Options();
					option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("D");
					ss.add(option);
					
					if(!servletRequest.getParameter("E" + tempid).trim().equals(""))
					{
					option = new Options();
					option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
					option.setOptOrder("E");
					ss.add(option);
					}
					
					if(!servletRequest.getParameter("F" + tempid).trim().equals("")){
					option = new Options();
					option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
					option.setOptOrder("F");
					ss.add(option);
					}
					
					if(!servletRequest.getParameter("G" + tempid).trim().equals("")){
					option = new Options();
					option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
					option.setOptOrder("G");
					ss.add(option);
					}
				}
					else if(temptype == 2){//多选题
						
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("A");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("B");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("C");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("D");
						ss.add(option);
						
						if(!servletRequest.getParameter("E" + tempid).trim().equals(""))
						{
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("E");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("F");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("G");
						ss.add(option);
						}
					}else if(temptype == 4){//材料题
						
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("A");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("B");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("C");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("D");
						ss.add(option);
						
						if(!servletRequest.getParameter("E" + tempid).trim().equals(""))
						{
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("E");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("F");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("G");
						ss.add(option);
						}
					}else if(temptype == 5){//图表题
						
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("A");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("B");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("C");
						ss.add(option);
						//optionsService.addOptions(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("D");
						ss.add(option);
						
						if(!servletRequest.getParameter("E" + tempid).trim().equals(""))
						{
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("E");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("F");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("G");
						ss.add(option);
						}
					}
						else if(temptype == 3){//判断题
							
							option = new Options();//添加选项---------------------------------
							option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
							
							option.setOptOrder("A");
							ss.add(option);
							//optionsService.addOptions(option);
							
							option = new Options();
							option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
							
							option.setOptOrder("B");
							ss.add(option);
					
				}else if(temptype == 6){//主观题
					option = new Options();
					option.setOptContent(servletRequest.getParameter("right" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("main");
					ss.add(option);
				}
				question.setOptions(ss);
				aa.add(question);
				exam.setQst(aa);
			
			}
			
			
			return "read";
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	

	/**
	 * 试题目列表
	 * @return
	 */
	public  String getExamPaper(){
		try{
			//根据前台获取试卷id查找试卷的试题
			exam=exampaperService.getExampaperById(epid);
			HttpSession session=this.servletRequest.getSession();
			session.setAttribute("exampaper", exam);
			return "showQstPaper";
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		
	}
	/**
	 * 根据学员课程展示题目列表
	 * @return
	 */
	public String getExamPaperList(){
		try{
			//根据学员所购买专业的课程显示试题
			int userid=1;//假设userid			
			subjectList=subjectService.getSubjectListByUserId(userid);
			return "showPaperList";
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
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

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}
	
	public QueryExampaperCondition getQueryExampaperCondition() {
		if(queryExampaperCondition == null){
			queryExampaperCondition = new QueryExampaperCondition();
		}
		return queryExampaperCondition;
	}

	public void setQueryExampaperCondition(
			QueryExampaperCondition queryExampaperCondition) {
		this.queryExampaperCondition = queryExampaperCondition;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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

	public List<File> getShitiPic() {
		return shitiPic;
	}

	public void setShitiPic(List<File> shitiPic) {
		this.shitiPic = shitiPic;
	}

	public List<String> getShitiPicFileName() {
		return shitiPicFileName;
	}

	public void setShitiPicFileName(List<String> shitiPicFileName) {
		this.shitiPicFileName = shitiPicFileName;
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
		
		if(queryQstCondition==null){
			queryQstCondition=new QueryQstCondition();
		}
		return queryQstCondition;
	}

	public void setQueryQstCondition(QueryQstCondition queryQstCondition) {
		this.queryQstCondition = queryQstCondition;
	}

	public int getEpid() {
		return epid;
	}

	public void setEpid(int epid) {
		this.epid = epid;
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

	public List<Qst> getWrongQsts() {
		return wrongQsts;
	}

	public void setWrongQsts(List<Qst> wrongQsts) {
		this.wrongQsts = wrongQsts;
	}

	public List<Professional> getProfessionalList() {
		return professionalList;
	}

	public void setProfessionalList(List<Professional> professionalList) {
		this.professionalList = professionalList;
	}

	public IProfessional getProfessionalService() {
		return professionalService;
	}

	public void setProfessionalService(IProfessional professionalService) {
		this.professionalService = professionalService;
	}

	public IQstKb getQstKbService() {
		return qstKbService;
	}

	public void setQstKbService(IQstKb qstKbService) {
		this.qstKbService = qstKbService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public String getTestname1() {
		return testname1;
	}

	public void setTestname1(String testname1) {
		this.testname1 = testname1;
	}

	public String getTesteptype1() {
		return testeptype1;
	}

	public void setTesteptype1(String testeptype1) {
		this.testeptype1 = testeptype1;
	}

	public String getCourse1() {
		return course1;
	}

	public void setCourse1(String course1) {
		this.course1 = course1;
	}

	public List<Qst> getQstlisttb() {
		return qstlisttb;
	}

	public void setQstlisttb(List<Qst> qstlisttb) {
		this.qstlisttb = qstlisttb;
	}


	public int getQstId() {
		return qstId;
	}


	public void setQstId(int qstId) {
		this.qstId = qstId;
	}


	public Qst getQqst() {
		return qqst;
	}


	public void setQqst(Qst qqst) {
		this.qqst = qqst;
	}

	public IQstmiddle getQstmiddleService() {
		return qstmiddleService;
	}

	public void setQstmiddleService(IQstmiddle qstmiddleService) {
		this.qstmiddleService = qstmiddleService;
	}

	public QueryQstmiddleCondition getQueryQstmiddleCondition() {
		if(queryQstmiddleCondition==null){
			queryQstmiddleCondition =new QueryQstmiddleCondition();
		}
		return queryQstmiddleCondition;
	}

	public void setQueryQstmiddleCondition(
			QueryQstmiddleCondition queryQstmiddleCondition) {
		this.queryQstmiddleCondition = queryQstmiddleCondition;
	}

	public List<Qstmiddle> getDanxuan() {
		return danxuan;
	}

	public void setDanxuan(List<Qstmiddle> danxuan) {
		this.danxuan = danxuan;
	}

	public List<Qstmiddle> getDuoxuan() {
		return duoxuan;
	}

	public void setDuoxuan(List<Qstmiddle> duoxuan) {
		this.duoxuan = duoxuan;
	}

	public List<Qstmiddle> getPanduan() {
		return panduan;
	}

	public void setPanduan(List<Qstmiddle> panduan) {
		this.panduan = panduan;
	}

	public List<Qstmiddle> getCailiao() {
		return cailiao;
	}

	public void setCailiao(List<Qstmiddle> cailiao) {
		this.cailiao = cailiao;
	}

	public List<Qstmiddle> getTubiao() {
		return tubiao;
	}

	public void setTubiao(List<Qstmiddle> tubiao) {
		this.tubiao = tubiao;
	}

	public List<Qstmiddle> getZhuguan() {
		return zhuguan;
	}

	public void setZhuguan(List<Qstmiddle> zhuguan) {
		this.zhuguan = zhuguan;
	}

}
