package com.shangde.edu.feed.service.impl;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryVideoLogCondition;
import com.shangde.edu.feed.domain.VideoLog;
import com.shangde.edu.feed.service.IVideoLog;

/**
 * 视频统计接口实现类
 * 
 */
@SuppressWarnings("unchecked")
public class VideoLogImpl extends BaseService implements IVideoLog {

	public int addVideoLog(VideoLog videoLog) {
		return simpleDao.createEntity("VideoLog_NS.createVideoLog", videoLog);
	}

	public int delVideoLogById(int id) {
		return simpleDao.delete("VideoLog_NS.deleteVideoLogById", id);
	}

	public int updateVideoLog(VideoLog videoLog) {
		return simpleDao.update("VideoLog_NS.updateVideoLog", videoLog);
	}

	public VideoLog getVideoLogById(int id) {
		return simpleDao.getEntity("VideoLog_NS.getVideoLogById", id);
	}

	public VideoLog getVideoLogByVideoId(int videoId) {
		return simpleDao.getEntity("VideoLog_NS.getVideoLogByVideoId", videoId);
	}

	public PageResult getVideoLogList(
			QueryVideoLogCondition queryVideoLogCondition) {
		return simpleDao.getPageResult("VideoLog_NS.getVideoLogList", "VideoLog_NS.getVideoLogCount", queryVideoLogCondition);
	}

	public int updateActiveNum(int videoId) {
		return simpleDao.update("VideoLog_NS.updateActiveNum", videoId);
	}

	public int updateBuyNum(int videoId) {
		return simpleDao.update("VideoLog_NS.updateBuyNum", videoId);
	}

	public int updateClickBuyNum(int videoId) {
		return simpleDao.update("VideoLog_NS.updateClickBuyNum", videoId);
	}

	public int updateClickNum(int videoId) {
		return simpleDao.update("VideoLog_NS.updateClickNum", videoId);
	}

}
