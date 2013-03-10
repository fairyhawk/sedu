package com.shangde.edu.res.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.res.domain.VedioCount;
import com.shangde.edu.res.service.IVedioCount;
import com.shangde.edu.stu.domain.VideoStatistics;
import com.shangde.edu.stu.service.IVideoStatistics;
/**
 * qita
 * @author xiaguangyang
 *
 */
public class VedioCountAction extends CommonAction{
	

	
	private IVedioCount vediocountService;
	
	private IVideoStatistics videoStatisticsService;
	
	private VideoStatistics videoStatistics;
	
     

	public VideoStatistics getVideoStatistics() {
		return videoStatistics;
	}

	public void setVideoStatistics(VideoStatistics videoStatistics) {
		this.videoStatistics = videoStatistics;
	}

	private int videoCountStatus;
 	/**
 	 * 声名logger
 	 */
 	private Logger logger = LoggerFactory.getLogger(VedioCountAction.class);

	public IVedioCount getVediocountService() {
		return vediocountService;
	}

	public void setVediocountService(IVedioCount vediocountService) {
		this.vediocountService = vediocountService;
	}

	public int getVideoCountStatus() {
		return videoCountStatus;
	}

	public void setVideoCountStatus(int videoCountStatus) {
		this.videoCountStatus = videoCountStatus;
	}
	
	public IVideoStatistics getVideoStatisticsService() {
		return videoStatisticsService;
	}

	public void setVideoStatisticsService(IVideoStatistics videoStatisticsService) {
		this.videoStatisticsService = videoStatisticsService;
	}

	/**
	 * 播放器功能统计
	 */
	public void vedioCount(){
		try{
		Integer userId= this.getLoginUserId();//获取学员登陆id
	    VedioCount vedioCount=new VedioCount();
		vedioCount.setCountTime(new Date());
		vedioCount.setCusId(userId!=null?userId:0);
		vedioCount.setStatus(videoCountStatus);
		vediocountService.addCount(vedioCount);//添加统计记录
		}catch(Exception e){
			logger.error(e.toString());
		}
	}
	
	public String addVst(){
		try{
			if(videoStatistics != null && getLoginUserId() > 0 
					&& videoStatistics.getCourseId() > 0 
					&& videoStatistics.getViewCode() != null 
					&& StringUtils.isNumeric(videoStatistics.getViewCode())){
				videoStatistics.setCusId(getLoginUserId());
				logger.info("cusId" + getLoginUserId());
				logger.info("courseId" + videoStatistics.getCourseId());
				logger.info("viewCode" + videoStatistics.getViewCode());
				videoStatisticsService.upOrAddWatch(videoStatistics);
				
				this.setResult(new Result(true,"success",null,null));
				return "json";
			}else{
				this.setResult(new Result(true,"fail",null,null));
				return "json";
			}
		}catch(Exception e){
			this.setResult(new Result(true,"error",null,null));
			return "json";
		}
	}
}
