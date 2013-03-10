package com.shangde.edu.exam.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.exam.condition.QueryExampaperCondition;
import com.shangde.edu.exam.condition.QueryFavoritesCondition;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.FavoritesPaper;
import com.shangde.edu.exam.domain.FavoritesQuestion;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.dto.ExampaperCountLastDay;
import com.shangde.edu.exam.dto.ExampaperQstDTO;
import com.shangde.edu.exam.dto.ExampaperSubjectId;
import com.shangde.edu.exam.dto.SubjectExampaperNumDTO;
import com.shangde.edu.sys.domain.Subject;

/**
 * IExampaper的实现类
 * 对试卷的相关操作
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public class ExampaperImpl extends BaseService implements IExampaper{
	


	@Override
	public int getCusExampaperCount(int subjectId) {
		return simpleDao.getEntity("Exampaper_NS.getExampaperCount",subjectId);
	}
	
	@Override
	public int getExampaperTotal(int cudId) {
		return simpleDao.getEntity("Exampaper_NS.getExampaperTotal",cudId);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 添加试卷���Exampaper
     * @param exampaper
     * @return id
     */
    public java.lang.Integer addExampaper(Exampaper exampaper) {
    	return simpleDao.createEntity("Exampaper_NS.createExampaper",exampaper);
    }
    
    
    public List<ExampaperCountLastDay> getExampaperCountLastDay(QueryExampaperCondition queryExampaperCondition){
    	return simpleDao.getForList("Exampaper_NS.getExampaperCountLastDay",queryExampaperCondition);
    }
    /**
     * 删除试卷��Exampaper
     * @param epId 试卷ID
     */
    public void delExampaperById(int epId){
        simpleDao.deleteEntity("Exampaper_NS.deleteExampaperById",epId);
    }
    
    /**
     * 通过专业查找8个试卷按照做试卷人数从低到高
     * author 王超
     * @param subjectId
     * @return
     */
    public List<Exampaper> getExampaperTop8(int subjectId){
    	return simpleDao.getForList("Exampaper_NS.getExampaperTop8",subjectId);
    }
    
    /**
     * 通过专业查找前10条试卷信息
     * author HQL
     * @param subjectId 专业ID
     * @return
     */
    public List<Exampaper> getExampaperTop10(int subjectId){
    	Calendar calendar = Calendar.getInstance();
    	Map paramMap = new HashMap();
    	paramMap.put("subjectId", subjectId);
    	calendar.add(Calendar.DATE, -3);
    	paramMap.put("startTime", calendar.getTime());
    	paramMap.put("endTime", new Date());
    	return simpleDao.getForList("Exampaper_NS.getExampaperTop10",paramMap);
    }
    
    /**
     * 获取当天上传的试卷数
     */
    public int getExampaperNumInDay(int subjectId){
    	String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	Map<Object,Object> map = new HashMap<Object,Object>();
    	map.put("subjectId", subjectId);
    	map.put("createTime", now);
    	return simpleDao.getEntity("Exampaper_NS.getExampaperNumInDay",map);
    }
    
    /**
     * 更新试卷�޸�Exampaper
     * @param exampaper 要更新的试卷
     */
    public void updateExampaper(Exampaper exampaper) {
        simpleDao.updateEntity("Exampaper_NS.updateExampaper",exampaper);
    }
    /**
     * 更新到期试卷�޸�Exampaper
     * @param exampaper 要更新的试卷
     */
    public void updateExampaperByEndtime(Exampaper exampaper){
    	simpleDao.updateEntity("Exampaper_NS.updateExampaperByEndtime",exampaper);
    }
    
    /**
     * 根据ID获取试卷
     * @param epId 试卷ID
     * @return 试卷
     */
    public Exampaper getExampaperById(int epId) {
        return simpleDao.getEntity("Exampaper_NS.getExampaperById",epId);
    }
    
    /**
     * 根据ID获取试卷
     * @param epId 试卷ID
     * @return 试卷
     */
    public Exampaper getExampaperByIdUserEp(int epId) {
        return simpleDao.getEntity("Exampaper_NS.getExampaperByIdUserEp",epId);
    }
    
    /**
     * 根据Epid获取试卷
     * 前台
     * @author 何海强
     */
    
    public Exampaper getExampaperByIdqian(int epId) {
        return simpleDao.getEntity("Exampaper_NS.getExampaperByIdhhq",epId);
    }
    
    
    /**
     * 根据Epid获取试卷
     * 前台 NewjudgmentPaper
     * @author 何海强
     */
    
    public Exampaper getNewjudgmentPaper(int epId) {
        return simpleDao.getEntity("Exampaper_NS.getNewjudgmentPaper",epId);
    }
    /**
     * 根据kpointid获取试卷
     * 
     */
    
    public Exampaper getExampaperByKpoint(int kpoint)
    {
    	
    	return simpleDao.getEntity("Exampaper_NS.getExampaperKpoint", kpoint);
    }
    
    
    /**
     * 通过课程id，测试类型，测试关键字查询试卷
     */
    public List<Exampaper> getRodeam(QueryExampaperCondition qec)
    {
    	return simpleDao.getForList("Exampaper_NS.getRodeam", qec);
    }
    /**
     * 获取试卷集合��������ȡExampaper�б�
     * @param queryExampaperCondition 试卷查询条件
     * @return 试卷集合���
     */
    public List<Exampaper> getExampaperList(QueryExampaperCondition queryExampaperCondition) {
        return simpleDao.getForList("Exampaper_NS.getExampaperList",queryExampaperCondition);
    }
    
    /**
     * 列出试卷列表前台
     * @param queryExampaperCondition
     * @return 分页查询试卷结果
     */
    public PageResult listExamPaperByCondition(QueryExampaperCondition queryExampaperCondition){
    	return simpleDao.getPageResult("Exampaper_NS.listExamPaper", "Exampaper_NS.listExamPaperCount", queryExampaperCondition);
    }
    
    /**
     * 列出单元练习试卷列表前台
     * @param queryExampaperCondition
     * @return 分页查询试卷结果
     */
    public PageResult listExamPaperByConditionDYLX(QueryExampaperCondition queryExampaperCondition){
    	return simpleDao.getPageResult("Exampaper_NS.dylistExamPaper", "Exampaper_NS.dylistExamPaperCount", queryExampaperCondition);
    }
    /**
     * 列出试卷列表后台
     * @param queryExampaperCondition
     * @return 分页查询试卷结果
     */
    public PageResult listExamPaperByConditionexam(QueryExampaperCondition queryExampaperCondition){
    	return simpleDao.getPageResult("Exampaper_NS.listExamPaperByConditionexam", "Exampaper_NS.listExamPaperCountByConditionexam", queryExampaperCondition);
    }
    
    /**
     * 根据courseid获取试卷列表
     * @param 课程ID courseId
     * @return 试卷集合
     */
	public List<Exampaper> getExampaperListbyCouseID(int courseId) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Exampaper_NS.getExampaperListByCourseId",courseId);
	}

	public List<Exampaper> getExampaperListByKpointId(int kpointId) {
		return simpleDao.getForList("Exampaper_NS.getExampaperListByKpointId",kpointId);
	}
	
	 public List<Exampaper> getExampaperByKpointList(int kpoint)
	    {
	    	
	    	return simpleDao.getForList("Exampaper_NS.getExampaperKpoint", kpoint);
	    }
	 
	    /**
	     * 获取专业对应试卷数量
	     * @return
	     */
	    public List<SubjectExampaperNumDTO> getSubjectExampaperNum(int cusId){
	    	return simpleDao.getForList("Exampaper_NS.getSubjectExampaperNum",cusId);
	    }
	 
	 /**
	  *查询课程
	  * @param pid
	  * @return
	  */
	 public List<Coursesort> getcourseotByPid(int pid){
		 
		 return simpleDao.getForList("Exampaper_NS.getcourseotByPi", pid);
	 }
	 
	 public List<Subject> getSubjectListForUnAss(int cusId) {
			// TODO Auto-generated method stub
			return simpleDao.getForList("Exampaper_NS.getSubjectListForUnAss", cusId);
		}
	 
	 /**
	     * 根据用户id查询已购买课程id排序
	     * @param cusId
	     * @return
	     */
	    public List<ExampaperSubjectId> getSubjectIdListForUnAss(int cusId){
	    	return simpleDao.getForList("Exampaper_NS.getSubjectIdListForUnAss",cusId); 
	    }
	 
	 /**
	  * 通过用户id和课程id查询试卷是否存在
	  */
	 public int getiskaoshi(QueryExampaperCondition queryExampaperCondition){
		 
		 return simpleDao.getEntity("Exampaper_NS.iskaoshi",queryExampaperCondition); 
	 }
	 /**
	     * 通过用户id查询用户注册的专业
	     * cusid
	     */
	    public int getcustomerSubjectId(int cusid){
	    	return simpleDao.getEntity("Exampaper_NS.customrsubjectid", cusid);
	    }
	    
	    public Subject getcustomerSubject(int cusid){
	    	
	    	return simpleDao.getEntity("Exampaper_NS.customersubject",cusid);
	    }
	    
	  /**
	   * 前台getExampaperPaperResult
	   */
	    
	    public Exampaper getExampaperPaperResult(QueryExampaperCondition queryExampaperCondition) {
	        return simpleDao.getEntity("Exampaper_NS.getExampaperPaperResult",queryExampaperCondition);
	    }
	    
	    /**
	     * 根据用户id返回用户积分
	     */
	    public int getUserjifen(int epId){
	    	return simpleDao.getEntity("Exampaper_NS.getUserjifen",epId);
	    }
	    
	    /**
	     * 查询试卷专业，并统计个个专业下的试卷数量
	     */
	    public int getAllSubject(int subjectid){
	    	return simpleDao.getEntity("Exampaper_NS.getAllSubject", subjectid);
	    }
	    
	    /**
	     * 通过用户id查询用户是否购买过课程
	     */
	    public int getisBuy(QueryExampaperCondition queryExampaperCondition){
	    	return  simpleDao.getEntity("Exampaper_NS.isbuy", queryExampaperCondition);
	    }
	    /**考试页面优化开始 王超 */
	    /**
	     * 前台考试页面优化
	     * @author 王超
	     * @param queryExampaperCondition
	     * @return
	     */
	    public PageResult getExampaperPage(QueryExampaperCondition queryExampaperCondition){
	    	return simpleDao.getPageResult("Exampaper_NS.getExampaperListQian","Exampaper_NS.getExampaperCountQian",queryExampaperCondition);
	    }
	    /**
	     * 获取考试试题列表
	     * @author 王超
	     * @param epId
	     * @return
	     */
	    public List<ExampaperQstDTO> getExamQstList(QueryExampaperCondition queryExampaperCondition){
	    	return simpleDao.getForList("Exampaper_NS.getExamQstList", queryExampaperCondition);
	    }
	    
	    /**
	     * 获取试卷详细信息
	     * @author 王超
	     * @param epId
	     * @return
	     */
	    public Exampaper getExampaperInfoByEpId(int epId){
	    	return simpleDao.getEntity("Exampaper_NS.getExampaperInfoByEpId", epId);
	    }

		@Override
		public List<Qst> getQstIdList(
				QueryExampaperCondition queryExampaperCondition) {
			// TODO Auto-generated method stub
			return simpleDao.getForList("Qst_NS.getQstIdBycondForList", queryExampaperCondition);
		}

		@Override
		public Integer getSubjectIdBylatestSell(int cusid) {
			// TODO Auto-generated method stub
			return simpleDao.getEntity("Exampaper_NS.getSubjectIdBylatestSell", cusid);
		}
	    
	    /**
	     * 获取某专业下面的课程列表
	     * @author 王超
	     * @param subjectId
	     * @return
	     */
	    public List<Course> getCourseListBySubjectId(QueryExampaperCondition queryExampaperCondition){
	    	return simpleDao.getForList("Exampaper_NS.getCourseListBySubjectId", queryExampaperCondition);
	    }
	    /**考试页面优化结束 王超 */

	    /**
	     * 获取被收藏试卷的基本信息
	     * @author yanghaibo
	     * @param epId
	     * @return
	     */
		@Override
		public Exampaper getFavoritesExampaperInfo(int epId) {
			return simpleDao.getEntity("Exampaper_NS.getExampaperInfo", epId);
		}

		@Override
		public Integer addFavoritesPaper(FavoritesPaper obj) {
			return simpleDao.createEntity("FavoritesPaper_NS.createFavorites",obj);
		}

		@Override
		public void delFavoritesPaper(QueryFavoritesCondition con) {
			this.simpleDao.deleteEntity("FavoritesPaper_NS.deleteFavorites", con);
			
		}

		@Override
		public Integer addFavoritesQuestion(FavoritesQuestion obj) {
			return simpleDao.createEntity("FavoritesQuestion_NS.createFavorites",obj);
		}

		@Override
		public void delFavoritesQuestion(QueryFavoritesCondition con) {
			this.simpleDao.deleteEntity("FavoritesQuestion_NS.deleteFavorites", con);
			
		}

		@Override
		public void addCollectNum(int qstId) {
			this.simpleDao.updateEntity("FavoritesQuestion_NS.addCollectNum", qstId);
			
		}

		@Override
		public void subCollectNum(int qstId) {
			this.simpleDao.updateEntity("FavoritesQuestion_NS.subCollectNum", qstId);
			
		}

		@Override
		public List<ExampaperQstDTO> getExamQstDetail(
				QueryExampaperCondition queryExampaperCondition) {
			return simpleDao.getForList("Exampaper_NS.getExamQstDetail", queryExampaperCondition);
		}

		@Override
		public List<Subject> getcustomerbuySubject(int cusid) {
			// TODO Auto-generated method stub
			return simpleDao.getForList("Exampaper_NS.getcustomerbuySubject", cusid);
		}
}			

