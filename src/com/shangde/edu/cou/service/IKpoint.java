package com.shangde.edu.cou.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourKpJSONCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.dto.JilianKpointDTO;
import com.shangde.edu.cou.dto.KPointDTO;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.dto.LeafKpointUrlDTO;
import com.shangde.edu.cou.dto.UserKpointDTO;

/**
 * Kpoint知识点服务接口ӿ�
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface IKpoint extends Serializable {
    /**
     * 添加知识点���Kpoint
     * @param kpoint 要添加的知识点
     * @return id
     */
    public java.lang.Integer addKpoint(Kpoint kpoint);

    /**
     * 通过知识点ID删除知识点
     * @param pointId 删除知识点
     */
    public void delKpointById(int pointId);

    /**
     * 更新知识点�޸�Kpoint
     * @param kpoint 要更新的知识点
     */
    public void updateKpoint(Kpoint kpoint);

    /**
     * 通过知识点ID获取知识点
     * @param pointId 知识点ID
     * @return �知识点
     */
    public Kpoint getKpointById(int pointId);

    /**
     * 查询知识点��������ȡKpoint�б�
     * @param queryKpointCondition 查询条件���
     * @return ��查询结果���
     */
    public List<Kpoint> getKpointList(QueryKpointCondition queryKpointCondition);
    
    /**
     * 通过课程ID获取知识点集合
     * @param queryKpointCondition
     * @return 查询结果
     */
    public List<Kpoint> getKpointListByCourseId(QueryKpointCondition queryKpointCondition);
  
    /**
     * 条件查询
     * @param queryKpointCondition
     * @return 查询结果�  
     */
    public PageResult getKpointListByCondition(QueryKpointCondition queryKpointCondition) ;
    
    /**
     * 通过PID获取子集合
     * @param queryKpointCondition
     * @return
     */
    public List<Kpoint> getKpointListByPid(QueryKpointCondition queryKpointCondition);
    
    /**
     * 后台获取知识点DTO
     * @param queryKpointCondition
     * @return
     */
    public List<KeyValueDTO> getKpointDTOListByCourseId(QueryKpointCondition queryKpointCondition);
    
    /**
     * 前台视频获取知识点DTO ，包含vedioUrl
     * @param courseId
     * @return
     */
    public List<KeyValueDTO> getKeyDTOWebResultByCourseId(int courseId);
    
    /**
     * 获取试听课程
     * @param courseId
     * @return
     */
    public List<UserKpointDTO> getTryKpointTreeByCourseId(int courseId);
    
    /**
     * 根据课程ID 获取该课程的的所有叶子节点
     * @param courseId
     * @return 
     */
	public List<LeafKpointUrlDTO> getLeafKpointDTOByCourseId(int courseId);
	
	/**
     * 根据知识点ID查看该知识点是否存在子节点
     * @param pId
     * @return 
     */
	public int getChildKpointCount(int pId);
	
	/**
	 * 按课程名删除课程
	 * @param courseId
	 */
	public void deleteKpointByCourseId(int courseId);
	
	/**
	 * 通过课程id查询知识点
	 */
	 public List<JilianKpointDTO> getJilianKpoint(QueryCourKpJSONCondition qk);

	public int getFirstLevelPointId(Kpoint kp);
	/**
	 * 根据客户ID和专业ID查知识点，供评价中心用
	 * by张聚强
	 */
	public List<Kpoint> getKpointListForUnAss(QueryKpointCondition qkCondition);
	/**
	 * 查找知识点所属包IDS，有可能包括在不同的售卖方式中
	 */
	public List<Integer> getSellWayIdsByKpointId(QueryKpointCondition qkCondition);
	
	public PageResult getMoreKpointListForUnAss(QueryKpointCondition condition);
	
	/**
	 * 为张栋提供iphone端数据
	 * 获取当前课程  讲数（一共有多少节课）
	 */
	public int getKpointSumByCourseId(int courseId);
	
	/**
	 * 根据kpointID  得到当前节点对应的视频地址
	 * 为张栋提供数据 课程首播视频
	 * @return
	 */
	public String getKpointVoUrlById(int courseId);
	
	/**
	 * 为张栋提供数据
	 */
    public PageResult getIphoneKpointListByPid(QueryKpointCondition queryKpointCondition);
	
}