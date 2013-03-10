package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.exam.condition.QueryExampaperRecordCondition;
import com.shangde.edu.exam.condition.QueryFavoritesCondition;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.ExampaperRecord;
import com.shangde.edu.exam.domain.FavoritesPaper;
import com.shangde.edu.exam.dto.ExamAnalysisDTO;
import com.shangde.edu.exam.dto.UserExamPaperDTO;

/**
 * IExampaperRecordӿ
 * 试卷记录接口�
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IExampaperRecord extends Serializable {
    /**
     * 添加试卷记录���ExampaperRecord
     * @param exampaperRecord 试卷记录
     * @return id
     */
    public  int addExampaperRecord(ExampaperRecord exampaperRecord);

    /**
     * 删除试卷记录��ExampaperRecord
     */
    public void delExampaperRecordById(int cusId, int epId);
    
    /**
     * 更新试卷记录�޸�ExampaperRecord
     * @param exampaperRecord 要更新的试卷记录
     */
    public void updateExampaperRecord(ExampaperRecord exampaperRecord);

    /**
     * 通过用户ID以及试卷ID获取试卷记录
     * @return �试卷记录
     */
    public ExampaperRecord getExampaperRecordById(int cusId, int epId);

    /**
     * 通过条件获取试卷记录集合
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询集合
     */
    public List<ExampaperRecord> getExampaperRecordList(QueryExampaperRecordCondition queryExampaperRecordCondition);
    /**
     * 通过条件获取试卷记录
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询
     */
    public int getExampaperRecordBycondition(QueryExampaperRecordCondition queryExampaperRecordCondition);
    /**
     *通过用户id查询历史题得分
     *cusId
     */
    
    public ExampaperRecord getExampaperRecordByCusId(int cusId);
    
    /**
     * 获取考试分析
     * @param queryExampaperRecordCondition
     * @return 考试分析集合
     */
    public List<ExamAnalysisDTO> getExampaperAnalysisDTO(QueryExampaperRecordCondition queryExampaperRecordCondition);
    
    /**
     * 按用户Id 及 课程ID获取试卷集合
     * @param queryExampaperRecordCondition
     * @return 查询分页结果集合 
     */
    public PageResult getExampaperRecordListByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition);
    
    /**
     * 按用户Id 及 课程ID获取正确试题数
     * @param queryExampaperRecordCondition
     * @return 正确题数
     */
    public int getRightQstNumByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition);
    
    /**
     * 按用户Id 及 课程ID获取试题数
     * @param 试卷记录查询条件 queryExampaperRecordCondition
     * @return 总题数
     */
    public int getQstNumByCourseId(QueryExampaperRecordCondition queryExampaperRecordCondition);
    
    /**
     * 获取用户考试试卷记录
     * @param 用户试卷ID userEpId
     * @return 用户试卷结果
     */
    public UserExamPaperDTO getUserExamPaperByUserEpId(int userEpId);
    /**
     * 删除试卷记录��ExampaperRecord
     */
    public void delERecordByCusId(int cusId);
    
    /**
     * 获取全部的考试人数
     * 
     */
    public int getExamPaperRecordListall(int epid);
    
    public int getExamPaperRecordqianpan(ExampaperRecord fen);
    
    /**
	  * 通过试卷id查询前10名的分数
	  * @param epId
	  * @return
	  */
    public List<ExampaperRecord> getExamRecordTop10(int epId);
    
    /**
	  * 查询前8名的分数
	  * @param epId
	  * @return
	  */
   public List<ExampaperRecord> getExamRecordTop8(int epId);
   
   /**
	  * 成绩查询
	  */
	 public PageResult GetExampaperRecordAll(QueryExampaperRecordCondition queryExampaperRecordCondition);
	 
	 /**
	  * 前台成绩查询
	  */
	 public PageResult GetExampaperRecordAllHistory(QueryExampaperRecordCondition queryExampaperRecordCondition);
	 
	 /**
	  * 前台我的试卷优化
	  * @author 王超
	  * @param queryExampaperRecordCondition
	  * @return
	  */
	 public PageResult getExamHistory(QueryExampaperRecordCondition queryExampaperRecordCondition);
	 
	 /**
	  * 我的试卷数量
	  * @author 杨海波
	  * @param queryExampaperRecordCondition
	  * @return
	  */
	 public int getMyPapaerCount(QueryExampaperRecordCondition queryExampaperRecordCondition);
	 
	 /**
	  * 我收藏的试卷
	  * @author yanghaibo 2012-07-10 17:24
	  * @param condition
	  * @return
	  */
	 public PageResult getMyFavoritesPaper(QueryFavoritesCondition condition);
	 
	 /**
	  * 我收藏的试卷数量
	  * @author yanghaibo 2012-07-30 17:10
	  * @param condition
	  * @return
	  */
	 public int getMyFavoritesPaperCount(QueryFavoritesCondition condition);
	 /**
	  * 我最近收藏的试卷
	  * @author yanghaibo 2012-07-10 17:24
	  * @param condition
	  * @return
	  */
	 public List<FavoritesPaper> getNewFavoritesPaper(QueryFavoritesCondition condition);
	 /**
	  * 我收藏的试题
	  * @author yanghaibo 2012-07-11 15:54
	  * @param condition
	  * @return
	  */
	 public PageResult getMyFavoritesQuestion(QueryFavoritesCondition condition);
	 /**
	  * 我收藏的试题的数量
	  * @author yanghaibo 2012-07-30 17：25
	  * @param condition
	  * @return
	  */
	 public int getMyFavoritesQuestionCount(QueryFavoritesCondition condition);
	 
}