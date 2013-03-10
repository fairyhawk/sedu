package com.shangde.edu.iphone.web;

import java.util.List;

import org.testng.log4testng.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.iphone.condition.QueryCourseIPhoneCondition;
import com.shangde.edu.iphone.dto.IphoneModel;
import com.shangde.edu.iphone.service.ICourseIPhone;
import com.shangde.edu.iphone.service.IKpointIPhone;
import com.shangde.edu.iphone.service.ISellWayIPhone;
import com.shangde.edu.iphone.service.ISubjectIPhone;
import com.shangde.edu.res.service.IVedio;

/**
 * 当前类  为张栋iphone端提供课程
 * @author wangzheng
 *
 */

public class IPhoneCourseWebAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(IPhoneCourseWebAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1882320843971351964L;

	/**
	 * 课程节点服务接口
	 */
	private IKpointIPhone kpointIPhoneService;
	
	/**
	 * 课程服务接口
	 */
	private ICourseIPhone courseIPhoneService;
	
	/**
	 * 售卖方式服务接口 
	 */
	private ISellWayIPhone sellWayIPhoneService;
	
	/**
	 * 视频服务接口
	 */
	private IVedio vedioService;
	
	/**
	 * 专业接口
	 */
	private ISubjectIPhone subjectIPhoneService;
	
	/**
	 * iphone端 课程查询附加条件
	 */
	private QueryCourseIPhoneCondition queryCondition;
	

	public QueryCourseIPhoneCondition getQueryCondition() {
		if(this.queryCondition == null)
			this.queryCondition = new QueryCourseIPhoneCondition();
		return queryCondition;
	}

	public void setQueryCondition(QueryCourseIPhoneCondition queryCondition) {
		this.queryCondition = queryCondition;
	}

	private int pageId;
	
	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	/**
	 * 售卖方式ID
	 */	
	public String getCourseBySellWayId(){
		try{
			String returnMessage = "";
			PageResult pageResult = null;
			if(pageId != 0){
				this.getQueryCondition().setSellWayId(pageId);  //添加查询条件  当前售卖方式ID
	//			this.getQueryCondition().setPageSize(10);
				pageResult = this.courseIPhoneService.getCourseBySellWayId(this.getQueryCondition());
				List<IphoneModel> couList = pageResult.getPageResult();  //遍历课程  添加当前课程下  课程节点数量
				if (couList != null && !couList.isEmpty()) {
					for (IphoneModel iphoneModel : couList) {
						iphoneModel.setVideoURL(this.kpointIPhoneService.getKpointVoUrlById(iphoneModel.getVideoId()));
						iphoneModel.setCourseNum(getKpointSumByCourseId(iphoneModel.getVideoId()));
					}
				}
				pageResult.setPageResult(couList);
				setPage(pageResult);
				setPageUrlParms();
				if(getPage()!=null){
					getPage().setPageSize(this.getQueryCondition().getPageSize());
				}
				returnMessage = "success";
				
			}
			else{
				returnMessage = "error";
			}
			setResult(new Result<PageResult>(false, returnMessage, null, pageResult));
		}catch(Exception e){
			logger.error("IPhoneCourseWebAction.getCourseBySellWayId", e);
		}
		return "json";
	}

	/**
	 * 得到当前课程  包含讲数 为张栋提供iphone端数据
	 * @param courseId
	 * @return
	 */
	public int getKpointSumByCourseId(int courseId){
		 return this.kpointIPhoneService.getKpointSumByCourseId(courseId); //得到当前课程课程节点数
	}
	
	
	/**
	 * 得到最新推荐课程
	 * @return
	 */
	
	public String newCourse(){
		try{
			this.getQueryCondition().setNewCourse(1);
	//		this.getQueryCondition().setPageSize(10);
			PageResult newCourse = this.courseIPhoneService.getNewCourse(this.getQueryCondition());
			List<IphoneModel> iphoneList = newCourse.getPageResult();
			for (IphoneModel iphoneModel :iphoneList) {
				if(iphoneModel!=null){
					iphoneModel.setTeachers(getCourseTeacherBySellId(iphoneModel.getVideoId()));
					iphoneModel.setLeaf(1);
				}
			}
			setPageUrlParms();
			if(getPage()!=null){
				getPage().setPageSize(this.getQueryCondition().getPageSize());
			}
			setResult(new Result<PageResult>(false, "success", null, newCourse));
		}catch(Exception e){
			logger.error("IPhoneCourseWebAction.newCourse", e);
		}
		return "json";
	}
	
	/**
	 * 得到当前售卖方式下的课程老师！
	 * 
	 * @param sellWayId
	 *            售卖方式ID
	 * @return
	 */
	private String getCourseTeacherBySellId(int sellWayId) {
		StringBuilder sb = new StringBuilder();
		List<Teacher> teacherList = this.sellWayIPhoneService
				.getTeacherBySellWayId(sellWayId); // 根据售卖方式ID 得到售卖方式下所有课程的老师
		if (teacherList.size() != 0) { // 遍历老师 做处理
			for (int i = 0; i < teacherList.size(); i++) {
				if (teacherList.get(i) != null) {
					sb.append(teacherList.get(i).getName());
					if (i != teacherList.size() - 1) {
						sb.append("、");
					}
				}
			}
		}
		return sb.toString();
	}
	
	public String getSubject(){
		try{
			this.getQueryCondition().setSubjectId(6);
	//		this.getQueryCondition().setPageSize(10);
			PageResult pageResult = this.subjectIPhoneService.getAllSubject(this.getQueryCondition());
			setPageUrlParms();
			if(getPage()!=null){
				getPage().setPageSize(this.getQueryCondition().getPageSize());
			}
			setResult(new Result<PageResult>(false, "success", null, pageResult));
		}catch(Exception e){
			logger.error("IPhoneCourseWebAction.getSubject", e);
			setResult(new Result<PageResult>(false, "error", null, null));
		}
		return "json";
	}


	public IKpointIPhone getKpointIPhoneService() {
		return kpointIPhoneService;
	}
 
	public void setKpointIPhoneService(IKpointIPhone kpointIPhoneService) {
		this.kpointIPhoneService = kpointIPhoneService;
	}

	public ICourseIPhone getCourseIPhoneService() {
		return courseIPhoneService;
	}

	public void setCourseIPhoneService(ICourseIPhone courseIPhoneService) {
		this.courseIPhoneService = courseIPhoneService;
	}

	public ISellWayIPhone getSellWayIPhoneService() {
		return sellWayIPhoneService;
	}

	public void setSellWayIPhoneService(ISellWayIPhone sellWayIPhoneService) {
		this.sellWayIPhoneService = sellWayIPhoneService;
	}

	public IVedio getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVedio vedioService) {
		this.vedioService = vedioService;
	}


	public ISubjectIPhone getSubjectIPhoneService() {
		return subjectIPhoneService;
	}

	public void setSubjectIPhoneService(ISubjectIPhone subjectIPhoneService) {
		this.subjectIPhoneService = subjectIPhoneService;
	}

}
