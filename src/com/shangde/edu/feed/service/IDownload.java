package com.shangde.edu.feed.service;

import com.shangde.edu.feed.condition.QueryAppStatCondition;
import com.shangde.edu.feed.domain.Download;

/**
 * 下载服务接口
 * 
 * @author Libg
 * 
 */
public interface IDownload {

	/**
	 * 添加下载
	 * 
	 * @param download
	 * @return
	 */
	public int addDownload(Download download);

	/**
	 * 获取下载总次数，根据时间段查询
	 * 
	 * @param queryDownloadCondition
	 * @return
	 */
	public int getDownloadCount(QueryAppStatCondition queryAppStatCondition);

	/**
	 * 获取下载总次数用户分组，根据时间段查询,计算的是人数
	 * 
	 * @param queryDownloadCondition
	 * @return
	 */
	public int getDownloadUserGroupCount(
			QueryAppStatCondition queryAppStatCondition);
}
