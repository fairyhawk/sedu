package com.shangde.edu.exam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.exam.condition.QueryExampaperRecordCondition;
import com.shangde.edu.exam.condition.QueryFavoritesCondition;
import com.shangde.edu.exam.domain.ExampaperRecord;
import com.shangde.edu.exam.domain.FavoritesPaper;
import com.shangde.edu.exam.dto.ExamAnalysisDTO;
import com.shangde.edu.exam.dto.UserExamPaperDTO;

/**
 * ExampaperRecord试卷记录
 * IExampaperRecord实现类
 * 主要为试卷记录的操作类
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
@SuppressWarnings("unchecked")
public class ExampaperRecordImpl extends BaseService implements IExampaperRecord{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 添加试卷记录���ExampaperRecord
     * @param exampaperRecord 试卷记录
     * @return id
     */
    public  int addExampaperRecord(ExampaperRecord exampaperRecord) {
    	return simpleDao.createEntity("ExampaperRecord_NS.createExampaperRecord",exampaperRecord);
    }
    
    /**
     * 删除试卷记录��ExampaperRecord
     */
    public void delExampaperRecordById(int cusId, int epId){
    	Map keyMap = new HashMap();
    	keyMap.put("cusId", cusId);
		keyMap.put("epId", epId);
    	simpleDao.deleteEntity("ExampaperRecord_NS.deleteExampaperRecordById", keyMap);
    }
    
    /**
     * 查找试卷最大分数
     * @param epId
     * @return
     */
    public float getExampaperMaxFen(int epId){
    	if(simpleDao.getEntity("ExampaperRecord_NS.getExampaperMaxFen", epId)==null){
    	return 0;
    	}else{
    		return simpleDao.getEntity("ExampaperRecord_NS.getExampaperMaxFen", epId);
    	}
    }
    
    /**
     * 查找试卷最小分数
     * @param epId
     * @return
     */
    public float getExampaperMinFen(int epId){
    	if(simpleDao.getEntity("ExampaperRecord_NS.getExampaperMinFen", epId)==null){
    	return 0;
    	}else{
    		return simpleDao.getEntity("ExampaperRecord_NS.getExampaperMinFen", epId);
    	}
    }
    
    /**
     * 查找试卷平均分
     * @param epId
     * @return
     */
    public float getExampaperAvgScoreByEpId(int epId){
    	if(simpleDao.getEntity("ExampaperRecord_NS.getExampaperAvgScoreByEpId", epId)==null){
    	return 0;
    	}else{
    		return simpleDao.getEntity("ExampaperRecord_NS.getExampaperAvgScoreByEpId", epId);
    	}
    }
    
    /**
     * 更新试卷记录�޸�ExampaperRecord
     * @param exampaperRecord 要更新的试卷记录
     */
    public void updateExampaperRecord(ExampaperRecord exampaperRecord) {
        simpleDao.updateEntity("ExampaperRecord_NS.updateExampaperRecord",exampaperRecord);
    }
    
    /**
     * 通过用户ID以及试卷ID获取试卷记录
     * @return �试卷记录
     */
    public ExampaperRecord getExampaperRecordById(int cusId, int epId) {
    	Map keyMap = new HashMap();
    	keyMap.put("cusId", cusId);
		keyMap.put("epId", epId);
		
    	return simpleDao.getEntity("ExampaperRecord_NS.getExampaperRecordById", keyMap);
    }
    
    
    /**
     *通过用户id查询历史题得分
     *cusId
     */
    
    public ExampaperRecord getExampaperRecordByCusId(int cusId) {
    	
    	return simpleDao.getEntity("ExampaperRecord_NS.getExampaperRecordByCusId", cusId);
    }
    /**
     * 通过条件获取试卷记录集合
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询集合
     */
    public List<ExampaperRecord> getExampaperRecordList(QueryExampaperRecordCondition queryExampaperRecordCondition) {
        return simpleDao.getForList("ExampaperRecord_NS.getExampaperRecordList",queryExampaperRecordCondition);
    }
    
    /**
     * 通过条件获取试卷记录
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询
     */
    public int getExampaperRecordBycondition(QueryExampaperRecordCondition queryExampaperRecordCondition){
    	return simpleDao.getEntity("ExampaperRecord_NS.getExampaperRecordBycondition", queryExampaperRecordCondition);
    }
    
    /**
     * 获取考试分析
     * @param queryExampaperRecordCondition
     * @return 考试分析集合
     */
    public List<ExamAnalysisDTO> getExampaperAnalysisDTO(QueryExampaperRecordCondition queryExampaperRecordCondition){
    	return simpleDao.getForList("ExampaperRecord_NS.getExampaperAnalysisDTO", queryExampaperRecordCondition);
    }
    
    /**
     * 按用户Id 及 课程ID获取试卷集合
     * @param queryExampaperRecordCondition
     * @return 查询分页结果集合 
     */
    public PageResult getExampaperRecordListByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition){
    	return  simpleDao.getPageResult("ExampaperRecord_NS.getExampaperRecordListByCourseId", "ExampaperRecord_NS.getExampaperRecordListCountByCourseId", queryExampaperRecordCondition);

    }
    
    /**
     * 按用户Id 及 课程ID获取正确试题数
     * @param queryExampaperRecordCondition
     * @return 正确题数
     */
    public int getRightQstNumByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition){
    	return simpleDao.getEntity("ExampaperRecord_NS.getRightQstNumByCourseId", queryExampaperRecordCondition);
    }
    
    /**
     * 按用户Id 及 课程ID获取试题数
     * @param 试卷记录查询条件 queryExampaperRecordCondition
     * @return 总题数
     */
    public int getQstNumByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition){
    	return simpleDao.getEntity("ExampaperRecord_NS.getQstNumByCourseId", queryExampaperRecordCondition);
    }

    /**
     * 获取用户考试试卷记录
     * @param 用户试卷ID userEpId
     * @return 用户试卷结果
     */
	public UserExamPaperDTO getUserExamPaperByUserEpId(int userEpId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("ExampaperRecord_NS.getUserExamPaperByUserEpId", userEpId);
	}
	 public void delERecordByCusId(int cusId){
		 simpleDao.deleteEntity("ExampaperRecord_NS.deleteERecordById", cusId);
	 }
	 
	 /**
	  * 
	  */
	 public int getExamPaperRecordListall(int epid)
	 {
		 return simpleDao.getEntity("ExampaperRecord_NS.ExamRecordall",epid);
	 }
    
	 /**
	  * 前台得分排序
	  */
	 public int getExamPaperRecordqianpan(ExampaperRecord fen)
	 {
		 return simpleDao.getEntity("ExampaperRecord_NS.fen",fen);
	 }
	 
	 /**
	  * 通过试卷id查询前10名的分数
	  * @param epId
	  * @return
	  */
	 public List<ExampaperRecord> getExamRecordTop10(int epId){
		 
		 return simpleDao.getForList("ExampaperRecord_NS.getExamRecordtop10", epId);
	 }
	 
	 /**
	  * 查询前8名的分数
	  * @param epId
	  * @return
	  */
	 public List<ExampaperRecord> getExamRecordTop8(int epId){
		 
		 return simpleDao.getForList("ExampaperRecord_NS.getExamRecordtop8", epId);
	 }
	 
	 /**
	  * 成绩查询
	  */
	 
	 public PageResult GetExampaperRecordAll(QueryExampaperRecordCondition queryExampaperRecordCondition){
		 
		 return simpleDao.getPageResult("ExampaperRecord_NS.getExampaperRecordAll", "ExampaperRecord_NS.getExampaperRecordAllCount", queryExampaperRecordCondition);
	 }
	 
	 /**
	  * 前台成绩查询
	  */
	 
	 public PageResult GetExampaperRecordAllHistory(QueryExampaperRecordCondition queryExampaperRecordCondition){
		 
		 return simpleDao.getPageResult("ExampaperRecord_NS.getExampaperRecordAllHistory", "ExampaperRecord_NS.getExampaperRecordAllCount", queryExampaperRecordCondition);
	 }
	 
	 /**
	  * 前台我的试卷优化
	  * @author 王超
	  * @param queryExampaperRecordCondition
	  * @return
	  */
	 public PageResult getExamHistory(QueryExampaperRecordCondition queryExampaperRecordCondition){
		 return simpleDao.getPageResult("ExampaperRecord_NS.getExamHistoryList", "ExampaperRecord_NS.getExamHistoryCount", queryExampaperRecordCondition);
	 }

	@Override
	public PageResult getMyFavoritesPaper(QueryFavoritesCondition condition) {
		return simpleDao.getPageResult("FavoritesPaper_NS.getMyFavoritesList", "FavoritesPaper_NS.getMyFavoritesCount", condition);
	}

	@Override
	public PageResult getMyFavoritesQuestion(QueryFavoritesCondition condition) {
		return simpleDao.getPageResult("FavoritesQuestion_NS.getMyFavoritesList", "FavoritesQuestion_NS.getMyFavoritesCount", condition);
	}

	@Override
	public List<FavoritesPaper> getNewFavoritesPaper(
			QueryFavoritesCondition condition) {
		return simpleDao.getForList("FavoritesPaper_NS.getNewFavoritesList", condition);
	}

	@Override
	public int getMyFavoritesPaperCount(QueryFavoritesCondition condition) {
		return simpleDao.getEntity("FavoritesPaper_NS.getMyFavoritesCount", condition);
	}

	@Override
	public int getMyFavoritesQuestionCount(QueryFavoritesCondition condition) {
		return simpleDao.getEntity("FavoritesQuestion_NS.getMyFavoritesCount", condition);
	}

	@Override
	public int getMyPapaerCount(QueryExampaperRecordCondition queryExampaperRecordCondition) {
		return simpleDao.getEntity("ExampaperRecord_NS.getMyPaperCount", queryExampaperRecordCondition);
	}
}
