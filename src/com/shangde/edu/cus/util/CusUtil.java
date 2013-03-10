package com.shangde.edu.cus.util;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.shangde.common.util.DateUtil;
import com.shangde.common.vo.MemConstans;
import com.shangde.edu.cus.domain.CusRankInfo;
import com.shangde.edu.cus.domain.VideoCusInfo;
import com.shangde.edu.web.domain.CusBuyInfo;
import com.shangde.edu.web.domain.CusNotBuyInfo;
import com.shangde.edu.web.service.IWebService;
import com.shangde.edu.web.service.WebServiceFactory;
/**
 * webService数据解析
 * @author HQL
 *
 */
public class CusUtil {
	
	private static final Logger logger = Logger.getLogger(CusUtil.class);
	
	/**
	 * 获得用户视频KEY
	 * @param cudIs
	 * @return
	 */
	public static String getCusVideoKey(int cudId){
		return MemConstans.CUS_VIDEO_INFO + cudId;
	}
	/**
	 * 获得课程节点KEY
	 * @param courseId 课程ID
	 * @return
	 */
	public static String getKpointKey(int courseId){
		return MemConstans.COURSE_COUNT + courseId;
	}
	
	/**
	 * 获得未购买KEY
	 * @param cudIs
	 * @return
	 */
	public static String getCusVideoKey(String subjectId){
		return MemConstans.CUS_VIDEO_S_INFO + subjectId;
	}
	
	/**
	 * 获取该课程下节点统计信息
	 * @param courseId 课程ID
	 * @return
	 */
	public static Map<Integer,Map<Integer,Integer>> getKpointCount(int courseId){
		IWebService webService = WebServiceFactory.getVideoService();
		String result = webService.getKpointCount(courseId);
		if(StringUtils.isNotEmpty(result)){
			return JsonToKpointRank(courseId,result);
		}
		return null;
	}
	/**
	 * 解析RESULT
	 * @param result
	 * @return
	 */
	private static Map<Integer,Map<Integer,Integer>> JsonToKpointRank(int courseId,String result){
		Map<Integer,Integer> kpointMap = new HashMap<Integer, Integer>();
		Map<Integer,Map<Integer,Integer>> resultMap = new HashMap<Integer, Map<Integer,Integer>>();
		if(StringUtils.isNotEmpty(result)){
			JSONArray jsonArray = JSONArray.fromObject(result);
			try {
				for (int i=0; i < jsonArray.size(); i++){
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					kpointMap.put(jsonObj.getInt("kpointId"), jsonObj.getInt("kcount"));

				}
				resultMap.put(courseId, kpointMap);
				return resultMap;
			} catch (NumberFormatException e) {
				logger.error("CusUtil.JsonToKpointRank",e);
				return resultMap;
			}
		}
		return resultMap;
	}
	
	
	/**
	 * 获取用户已购买信息
	 * @param cusId
	 * @return
	 */
	public static CusBuyInfo getCusBuyInfo(int cusId,List<CusRankInfo> resultList){
		CusBuyInfo entity = new CusBuyInfo();
		resultList.add(getCusVideoRank(cusId));
		return packageCusBuyInfo(entity,resultList);
	}
	
	/**
	 * 封装购买用户统计信息
	 * @param entity
	 * @param resultList
	 * @return
	 */
	private static CusBuyInfo packageCusBuyInfo(CusBuyInfo entity,List<CusRankInfo> resultList){
		for(CusRankInfo var : resultList){
			if(var != null){
				switch (var.getType()) {
				case 1:entity.setLoginCount(var.getCount());
					   entity.setLoginRank(var.getRank());break;
				case 2:entity.setWatchVideoTime(var.getCount());
					   entity.setWatchVideoRank(var.getRank());break;
				case 3:entity.setWorkQuestionCount(var.getCount());
					   entity.setWorkQuestionRank(var.getRank());break;
				}
			}
		}
		return entity;
	}

	
	
	/**
	 * 返回用户未购买统计信息
	 * @param classmate
	 * @param avgVideo
	 * @param avgExampaper
	 * @param avgProblem
	 * @param avgReplyProblem
	 * @return
	 */
	public static CusNotBuyInfo getCusNotBuyInfo(int classmate,int avgVideo,int avgExampaper,int avgProblem,int avgReplyProblem,int subjectId){
		CusNotBuyInfo entity = new CusNotBuyInfo();
		entity.setClassmate(classmate);
		entity.setAvgVideo(avgVideo != 0 ? (avgVideo*2) /classmate  : 0);
		entity.setAvgExercises(avgExampaper !=0 ? (avgExampaper*2) /classmate  : 0);
		entity.setAvgQuestions(avgProblem !=0 ? classmate / (avgProblem*2) : 0);
		entity.setAnswerQuestions(avgReplyProblem !=0 ? classmate / (avgReplyProblem*2) : 0);
		return entity;
	}
	/**
	 * 用户观看视频总和
	 * @param cusId 用ID
	 * @return
	 */
	public static int getCusVideoTotal(int cusId){
		IWebService webService = WebServiceFactory.getVideoService();
		return webService.getCusVideoTotal(cusId);
	}
	
	public static int getCusVideoCount(int subjectId){
		IWebService webService = WebServiceFactory.getVideoService();
		return webService.getCusVideoCountBySubjectId(subjectId);
	}
	
	/**
	 * 根据json串组装统计bean
	 * @return
	 */
	public static CusRankInfo getCusVideoRank(int cusId){
		try {
			IWebService webService = WebServiceFactory.getVideoService();
			String result = webService.getCusVideoRank(cusId);
			if(StringUtils.isNotEmpty(result)){
				return JsonToVideoCusRank(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private static CusRankInfo JsonToVideoCusRank(String result) {
		CusRankInfo dto = null;
		if(StringUtils.isNotEmpty(result)){
			JSONObject object = JSONObject.fromObject(result);
				dto = new CusRankInfo();
				dto.setType(object.getInt("type"));
				dto.setCount(object.getInt("count"));
				dto.setCusid(object.getInt("cusid"));
				dto.setRank(object.getInt("rank"));
		}
		return dto;
	}

	/**
	 * 根据json串组装bean获取用户观看记录
	 * @param cusId
	 * @return
	 */
	public static String getCusInfoById(int cusId){
		try {
			IWebService webService = WebServiceFactory.getVideoService();
			String result = webService.getCusAndVideoInfo(cusId);
			if(StringUtils.isNotEmpty(result)){
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取用户最后一条观看记录
	 * @param cusId
	 * @return
	 */
	public static VideoCusInfo getCusInfoOneById(int cusId){
		try{
			IWebService webService = WebServiceFactory.getVideoService();
			String result = webService.getCusInfoOneById(cusId);
			if(StringUtils.isNotEmpty(result)){
				return JsonToVideoCusInfoOne(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 组装一条记录
	 * @param result
	 * @return
	 */
	private static VideoCusInfo JsonToVideoCusInfoOne(String result){
		VideoCusInfo dto = null;
		if(StringUtils.isNotEmpty(result)){
			try {
				JSONObject jsonObj = JSONObject.fromObject(result);
				dto = new VideoCusInfo();
				dto.setCount(jsonObj.getInt("count"));
				dto.setSellId(jsonObj.getInt("sellId"));
				dto.setSubjectId(jsonObj.getInt("subjectId"));
				dto.setCourseId(jsonObj.getInt("courseId"));
				dto.setVideoName(jsonObj.getString("videoName"));
				dto.setId(Integer.parseInt(jsonObj.getString("id")));
				dto.setSubjectName(jsonObj.getString("subjectName"));
				dto.setEndTime(DateUtil.toDate(jsonObj.getString("endTime"),DateUtil.TIME_FMT));
				dto.setStartTime(DateUtil.toDate(jsonObj.getString("startTime"),DateUtil.TIME_FMT));
			} catch (NumberFormatException e) {
				logger.error("CusUtil.JsonToVideoCusInfoOne",e);
			} catch (ParseException e) {
				logger.error("CusUtil.JsonToVideoCusInfoOne",e);
			}
		}
		return dto;
	}
	/*
	private static LinkedList<VideoCusInfo> JsonToVideoCusInfo(String result){
		LinkedList<VideoCusInfo> list = null;
		if(StringUtils.isNotEmpty(result)){
			JSONArray jsonArray = JSONArray.fromObject(result);
			list = new LinkedList<VideoCusInfo>();
			try {
				for (int i=0; i < jsonArray.size(); i++){
						VideoCusInfo dto = new VideoCusInfo();
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						dto.setCount(jsonObj.getInt("count"));
						dto.setSellId(jsonObj.getInt("sellId"));
						dto.setSubjectId(jsonObj.getInt("subjectId"));
						dto.setCourseId(jsonObj.getInt("courseId"));
						dto.setVideoName(jsonObj.getString("videoName"));
						dto.setId(Integer.parseInt(jsonObj.getString("id")));
						dto.setSubjectName(jsonObj.getString("subjectName"));
						dto.setEndTime(DateUtil.toDate(jsonObj.getString("endTime"),DateUtil.TIME_FMT));
						dto.setStartTime(DateUtil.toDate(jsonObj.getString("startTime"),DateUtil.TIME_FMT));
						list.add(dto);
				}
			} catch (NumberFormatException e) {
				logger.error("CusUtil.JsonToVideoCusInfo",e);
			} catch (ParseException e) {
				logger.error("CusUtil.JsonToVideoCusInfo",e);
			}
		}
		return list;
	}*/
}
