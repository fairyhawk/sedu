package com.shangde.edu.stu.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.stu.domain.VideoStatistics;
import com.shangde.edu.stu.condition.QueryVideoStatisticsCondition;

public interface IVideoStatistics {

    public Integer addVideoStatistics(VideoStatistics videoStatistics);

    public void delVideoStatisticsById();

    public void updateVideoStatistics(VideoStatistics videoStatistics);

    public VideoStatistics getVideoStatisticsById();

    public List<String> getUserVideoKiponitListBySubjectId(Map<String, String> map);

    public List<String> getUserVideoKiponitListByCourseId(Map<String, String> map);

    public List<VideoStatistics> getVideoStatisticsList(
            QueryVideoStatisticsCondition queryVideoStatisticsCondition);
    
    /**
     * 根据课程ID和用户ID查找是否存在本课程的观看记录
     * 
     */
    public List<VideoStatistics> getVideoStatisticsListByCouseId(
            QueryVideoStatisticsCondition queryVideoStatisticsCondition);
    
    public void updateUserWatchKpoint(String courseInfo) ;
    
    public void updateUserWatchKpoint(int cusId,int courseId,int kpointId) ;
    /**
     * 根据subjectId和用户id获得已经看过的节点list
     * @param subjectId 项目id,cusId用户id
     * @return List<String>
     */

    public List<String> getUserLearnKpointBySubjectId(int subjectId, int cusId);
    /**
     * 根据subjectId和用户id获得已经看过的节点list
     * @param subjectId 项目id,cusId用户id
     * @return List<String>
     */
    
    public List<String> getUserLearnKpointByCourseId(int courseId,int cusId);
    
    /**
     * 根据日期和用户id获得今天看过的节点list
     * @param today 今天的日期格式20110908 yyyyMMdd ,cusId用户id
     * @return List<String>
     */
    
    public List<String> getUserLearnKpointByDate(String today,int cusId);
    

    /**
     * 根据subjectIds和用户id获得已经看过的节点list
     * @param courseIds
     * @param cusId
     * @return
     * Author:Yangning
     * CreateDate:2012-2-14
     */
    public List<String> getUserLearnKpointByCourseIds(String courseIds,int cusId);
    
    /**
     * 业务方法 -->查询是否存在该课程结点已观看....如未已观看写入库,如该课程未观看且库中无记录则更新
     * @param vst
     * @return
     */
    public int upOrAddWatch(VideoStatistics vst);
    
    /**
     * 查询用户所有观看记录  --> 只包含已购买课程
     * @param map
     * @return
     */
    public List getCusWathcedKpointList(Map map);
}