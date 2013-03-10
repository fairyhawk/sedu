package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.exam.condition.QueryExampaperCondition;
import com.shangde.edu.exam.condition.QueryFavoritesCondition;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.FavoritesPaper;
import com.shangde.edu.exam.domain.FavoritesQuestion;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.dto.ExampaperCountLastDay;
import com.shangde.edu.exam.dto.ExampaperQstDTO;
import com.shangde.edu.exam.dto.ExampaperSubjectId;
import com.shangde.edu.exam.dto.SubjectExampaperNumDTO;
import com.shangde.edu.sys.domain.Subject;

/**
 * Exampaper 试卷接口ӿ�
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IExampaper extends Serializable {
	
	/**
	 * 获取用户共做了多少试卷
	 * @param cudId
	 * @return
	 */
	public int getExampaperTotal(int cudId);
	
    /**
     * 添加试卷���Exampaper
     * @param exampaper
     * @return id
     */
    public java.lang.Integer addExampaper(Exampaper exampaper);

    /**
     * 通过专业查找8个试卷按照做试卷人数从低到高
     * author 王超
     * @param subjectId
     * @return
     */
    public List<Exampaper> getExampaperTop8(int subjectId);
    
    /**
     * 通过专业查找前10条试卷信息
     * author HQL
     * @param subjectId 专业ID
     * @return
     */
    public List<Exampaper> getExampaperTop10(int subjectId);
    /**
     * 删除试卷��Exampaper
     * @param epId 试卷ID
     */
    public void delExampaperById(int epId);
    /**
     * 更新到期试卷�޸�Exampaper
     * @param exampaper 要更新的试卷
     */
    public void updateExampaperByEndtime(Exampaper exampaper);
    /**
     * 更新试卷�޸�Exampaper
     * @param exampaper 要更新的试卷
     */
    public void updateExampaper(Exampaper exampaper);

    /**
     * 根据ID获取试卷
     * @param epId 试卷ID
     * @return 试卷
     */
    public Exampaper getExampaperById(int epId);
    
    /**
     * 获取前一天更新试卷数量
     * @param queryExampaperCondition
     * @return dto类
     */
    public List<ExampaperCountLastDay> getExampaperCountLastDay(QueryExampaperCondition queryExampaperCondition);
    
    /**
     * 根据Epid获取试卷
     * 前台
     * @author 何海强
     */
    
    public Exampaper getExampaperByIdqian(int epId);
    
    /**
     * 根据Epid获取试卷
     * 前台 NewjudgmentPaper
     * @author 何海强
     */
    
    public Exampaper getNewjudgmentPaper(int epId);
    
    /**
     * 根据kpoint获取试卷
     */
    public Exampaper getExampaperByKpoint(int kpoint);
    
    
    /**
     * 根据kpoint获取试卷
     */
    public List<Exampaper> getExampaperByKpointList(int kpoint);
    
    /**
     * 获取试卷集合��������ȡExampaper�б�
     * @param queryExampaperCondition 试卷查询条件
     * @return 试卷集合���
     */
    public List<Exampaper> getExampaperList(QueryExampaperCondition queryExampaperCondition);
    
    /**
     * 列出试卷列表前台
     * @param queryExampaperCondition
     * @return 分页查询试卷结果
     */
    public PageResult listExamPaperByCondition(QueryExampaperCondition queryExampaperCondition);
    
    /**
     * 列出单元练习试卷列表前台
     * @param queryExampaperCondition
     * @return 分页查询试卷结果
     */
    public PageResult listExamPaperByConditionDYLX(QueryExampaperCondition queryExampaperCondition);
    
    
    
    /**
     * 列出试卷列表后台
     * @param queryExampaperCondition
     * @return 分页查询试卷结果
     */
    public PageResult listExamPaperByConditionexam(QueryExampaperCondition queryExampaperCondition);
    
    
    /**
     * 根据courseid获取试卷列表
     * @param 课程ID courseId
     * @return 试卷集合
     */
    public List<Exampaper> getExampaperListbyCouseID(int courseId);
    
    /**
     * 按知识点查询试卷
     * @param kpointId
     * @return
     */
    public List<Exampaper> getExampaperListByKpointId(int kpointId);
    /**
     * 获取专业对应试卷数量
     * @return
     */
    public List<SubjectExampaperNumDTO> getSubjectExampaperNum(int cusId);
    
    /**
     * 通过课程id，测试类型，测试关键字查询试卷
     */
    public List<Exampaper> getRodeam(QueryExampaperCondition qec);
    
    /**
     * 查询课程
     */
    public List<Coursesort> getcourseotByPid(int pid);
    
    /**
     * 根据用户ID查询用户所购包所属的专业list,评价中心使用
     * zhangjuqiang
     */
    public List<Subject> getSubjectListForUnAss(int cusId);
    /**
     * 根据用户id查询已购买课程id排序
     * @param cusId
     * @return
     */
    public List<ExampaperSubjectId> getSubjectIdListForUnAss(int cusId);
    
    /**
     * 通过用户id和课程id查询试卷
     */
    public int getiskaoshi(QueryExampaperCondition queryExampaperCondition);
    
    /**
     * 通过用户id查询用户注册的专业
     * cusid
     */
    public int getcustomerSubjectId(int cusid);
    
    /*
     * 得到会员注册里的专业。
     */
    public Subject getcustomerSubject(int cusid);    
    
    public List<Subject> getcustomerbuySubject(int cusid);
    /**
	   * 前台getExampaperPaperResult
	   */
    public Exampaper getExampaperPaperResult(QueryExampaperCondition queryExampaperCondition);
    
    /**
     * 根据用户id返回用户积分
     */
    public int getUserjifen(int epId);
    /**
     * 根据ID获取试卷
     * @param epId 试卷ID
     * @return 试卷
     */
    public Exampaper getExampaperByIdUserEp(int epId);
    
    /**
     * 查询试卷专业，并统计个个专业下的试卷数量
     */
    public int getAllSubject(int subjectid);
    
    /**
     * 根据用户专业查询该专业下的所有人做过多少试题
     * @param subjectId 专业ID
     * @return 总和
     */
    public int getCusExampaperCount(int subjectId);
    
    /**
     * 通过用户id查询用户是否购买过课程
     */
    public int getisBuy(QueryExampaperCondition queryExampaperCondition);
    /**
     * 获取当天上传的试卷数
     */
    public int getExampaperNumInDay(int subjectId);
    
    /**考试页面优化开始 王超 */
    
    /**
     * 前台考试页面优化
     * @author 王超
     * @param queryExampaperCondition
     * @return
     */
    public PageResult getExampaperPage(QueryExampaperCondition queryExampaperCondition);
    
    /**
     * 获取考试试题列表
     * @author 王超
     * @param epId
     * @return
     */
    public List<ExampaperQstDTO> getExamQstList(QueryExampaperCondition queryExampaperCondition);
    
    /**
     * 获取考试试题的详细信息
     * @author yanghaibo
     * @param queryExampaperCondition
     * @return
     */
    public List<ExampaperQstDTO> getExamQstDetail(QueryExampaperCondition queryExampaperCondition);
    
    /**
     * 获取试卷详细信息
     * @author 王超
     * @param epId
     * @return
     */
    public Exampaper getExampaperInfoByEpId(int epId);
    /**
     * 获取某专业下面的课程列表
     * @author 王超
     * @param subjectId
     * @return
     */
    public List<Course> getCourseListBySubjectId(QueryExampaperCondition queryExampaperCondition);
    /**考试页面优化结束 王超 */
    

    /*
     * 根据用户id查询最近购买课程专业的一个id
     */
    public Integer getSubjectIdBylatestSell(int cusid);
    /*
     * 根据条件查询所有的试题Id 
     */
    public List<Qst> getQstIdList(QueryExampaperCondition queryExampaperCondition);
    
    /**
     * 获取被收藏试卷的基本信息
     * @author yanghaibo
     * @param epId
     * @return
     */
    public Exampaper getFavoritesExampaperInfo(int epId);
    
    /**
     * 保存被收藏的用户试卷
     * @author yanghaibo
     * @param obj
     * @return
     */
    public java.lang.Integer addFavoritesPaper(FavoritesPaper obj);
    /**
     * 移除被收藏的用户试卷
     * @author yanghaibo
     * @param con
     * @return
     */
    public void delFavoritesPaper(QueryFavoritesCondition con);
    /**
     * 保存被收藏的用户试题
     * @author yanghaibo
     * @param obj
     * @return
     */
    public java.lang.Integer addFavoritesQuestion(FavoritesQuestion obj);
    /**
     * 移除被收藏的用户试题
     * @author yanghaibo
     * @param con
     * @return
     */
    public void delFavoritesQuestion(QueryFavoritesCondition con);
    /**
     * 试题收藏数量+1
     * @author yanghaibo
     * @param qstId
     * @return
     */
    public void addCollectNum(int qstId);
    /**
     * 试题收藏数量-1
     * @author yanghaibo
     * @param qstId
     * @return
     */
    public void subCollectNum(int qstId);

}