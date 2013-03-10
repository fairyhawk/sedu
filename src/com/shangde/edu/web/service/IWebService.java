package com.shangde.edu.web.service;


/**
 * 主体功能:WEBSERVICE工厂
 *
 * @author		HQL
 * @date		2012-6-26
 */
public interface IWebService {
	
	/**
	 * 根据用户ID获取用户观看视频信息(2,11)
	 * @param cusId 用户ID
	 * @return json串
	 */
	public String getCusAndVideoInfo(int cusId);
	
	/**
	 * 根据用户ID获取最后一条观看记录(1)
	 * @param cusId 用户ID
	 * @return
	 */
	public String getCusInfoOneById(int cusId);
	
	/**
	 * 根据用户专业ID获取观看总和
	 * @return 总和
	 */
	public int getCusVideoCountBySubjectId(int subjectId);

	/**
	 * 用户视频统计数据
	 * @return
	 */
	public String getCusVideoRank(int cusId);
	
	/**
	 * 用户观看视频总和
	 * @param cusId
	 * @return
	 */
	public int getCusVideoTotal(int cusId);
	
	/**
	 * 根据专业统计top
	 * @param subjectId
	 * @return
	 */
	public String getCusVideoRankTop(int subjectId);
	/**
	 * 获取该课程下节点统计信息
	 * @param courseId 课程ID
	 * @return
	 */
	public String getKpointCount(int courseId);
}
