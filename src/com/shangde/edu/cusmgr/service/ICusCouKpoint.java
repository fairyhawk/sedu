package com.shangde.edu.cusmgr.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.dto.UserKpointDTO;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;
import com.shangde.edu.cou.webdto.UserCenterKpointDTO;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;
import com.shangde.edu.cusmgr.condition.QueryCusMgrCondition;
import com.shangde.edu.cusmgr.domain.CusCouKpoint;
import com.shangde.edu.cusmgr.dto.LearnBuyInfoDTO;
import com.shangde.edu.res.domain.Books;
import com.shangde.edu.sys.domain.Subject;

/**
 * CusCouKpoint管理接口
 * User: guoqiang.liu
 * Date: 2010-07-26
 */
public interface ICusCouKpoint extends Serializable {
    /**
     * 添加CusCouKpoint
     * @param cusCouKpoint 要添加的CusCouKpoint
     * @return id
     */
    public java.lang.Integer addCusCouKpoint(CusCouKpoint cusCouKpoint);

    /**
     * 根据id删除一个CusCouKpoint
     * @param id 要删除的id
     */
    public void delCusCouKpointById(int id);

    /**
     * 修改CusCouKpoint
     * @param cusCouKpoint 要修改的CusCouKpoint
     */
    public void updateCusCouKpoint(CusCouKpoint cusCouKpoint);

    /**
     * 根据id获取单个CusCouKpoint对象
     * @param id 要查询的id
     * @return 年级
     */
    public CusCouKpoint getCusCouKpointById(int id);

    /**
     * 根据条件获取CusCouKpoint列表
     * @param queryCusCouKpointCondition 查询条件
     * @return 查询结果
     */
    public List<CusCouKpoint> getCusCouKpointList(QueryCusCouKpointCondition queryCusCouKpointCondition);


	/**
	 * 根据已选课程id删除CusCouKpoint
	 * @param courseId 课程id
	 */
	public void delCusCouKpointByCus(QueryCusMgrCondition queryCusMgrCondition);
	
	/**
	 * 根据用户ID获取课程
	 * @param cusId
	 * @return
	 */
	public List<Course> getCusCouKpointListByCusId(int cusId);
	
	
	
	public List<Integer> getCusCouIdKpointListByCusId(int cusId);
	
	/**
	 * 获取知识树
	 * @return
	 */
	public List<UserKpointDTO> getKpointListByCusIdAndCourseId(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * 按课程ID删除数据
	 * @param courseId
	 */
	public void deleteCusCouKpointByCourseId(int courseId);
	
	/**
     * 根据id删除一个CusCouKpoint
     * @param id 要删除的id
     */
    public void delCusCouKpointByCusId(int cusId);
    
    /**
	 * @author chenshuai
	 * 功能：根据用户ID及课程ID获取该课程表中已经学完的知识点数
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public int getReadedCusCouKpointCountByCondition(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author chenshuai
	 * 功能：判断用户课程的知识点是否都已经看完 等于0时则看完
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public int isAllReadByCondition(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author chenshuai
	 * 功能：通过课程ID及用户ID查找所有叶子节点为1的所有知识点
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public List<UserCenterKpointDTO> getUserCenterKpointDTOByCusIdAndCourseId(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author chenshuai
	 * 功能：获取用户最后学习的视频ID
	 * @param args
	 * @param cusId
	 * @return
	 */
	public Integer getUserLastPointIdByCusId(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author chenshuai
	 * 功能：根据课程ID及用户ID获取用户该课程最后学习的视频ID
	 * 若全部未观看，则返回该课程的第一个知识点ID
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getUserCourseLastIdByCusId(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author cxs
	 * 功能：根据课程ID获取该学员今天听视频的数量
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public int getCountUserStudyTodayByCourseId(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author cxs
	 * 功能：查询比该用户学习数量多指定数量视频的用户数
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public int getCountLargerThanUserStudyToday(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author cxs
	 * 功能：查询比该用户学习数量小于指定数量视频的用户数
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public int getCountlessThanUserStudyToday(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author cxs
	 * 功能：查询比该用户学习数量等于指定数量视频的用户数
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public int getCountEqualNumThanUserStudyToday(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author cxs
	 * 功能：获取学员数量，通过课程ID
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public int getCusSizeByCourseId(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author chenshuai
	 * 功能：根据用户ID查询所购买的专业集合
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public List<Subject> getUserCenterSubjectListByCusId(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author chenshuai
	 * 功能：用户是否看过课程，0表示没有
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public int isReadCourse(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author chenshuai
	 * 功能：获取用户购买最早的课程的专业ID
	 * @param args
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getEarliestSubjectIdByCusId(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author chenshuai
	 * 功能：获取课程已经上传视频的数量
	 * 视频数，并且视频路径不为NULL或空字符串
	 * @param args
	 * @param courseId
	 * @return
	 */
	public int getCourseVedioSizeByCourseId(int courseId);
	
	/**
	 * @author zhouzhiqiang
	 * 功能：获取在几分钟内学习了课程的信息
	 * @param minutes
	 * @return
	 */
	public List getLearningInfoInTime(QueryCusCouKpointCondition queryCusCouKpointCondition);
	
	/**
	 * @author liuqinggang
	 * 功能：获取在几分钟内学习了课程的信息 ,所有的课程
	 * @return
	 */
	public List<LearnBuyInfoDTO> getLearningInfoInTimeAllSubject();
	/**
	 * @author liuqinggang
	 * 功能：通过包ID查找所有课程
	 * @param args
	 * @param sellId 包ID
	 * @return
	 */
	public List<UserCenterCourseDTO> getCouserListBySellId(String sellId);
	
	/**
	 * 根据cus_id和subjectId获得用户购买成功的单个专业课程
	 */
	public List<Course> getCusCouKpointListByCusIdAndSubjectId(QueryCourseCondition queryCourseCondition);
	/**
	 * @author fanxin
	 * 功能：查看books表的mp3的音频地址URL
	 * @return
	 */
    public List<Books> getBooksListByCusIdAndCourseId(QueryCusCouKpointCondition queryCusCouKpointCondition);
    
    
    /**
     * 查找当前节点下所有子节点
     * @param CourseList
     * @return
     * Author:Yangning
     * CreateDate:2012-1-5
     */
	public List<UserCenterKpointDTO> getUserCenterKpointDTOByCusIdsAndCourseId(Map CourseList);
	/**
	 * 查找所有sellIds下的视频数量
	 * @param courseId
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-1-6
	 */
	public List<Map<String,Integer>> getCourseVedioSizeByCourseIds(Map courseList);

}