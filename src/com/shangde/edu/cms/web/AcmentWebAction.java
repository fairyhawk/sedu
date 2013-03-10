package com.shangde.edu.cms.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


import com.shangde.common.action.CommonAction;
import com.shangde.edu.cms.domain.Announcement;
import com.shangde.edu.cms.service.IAnnouncement;

public class AcmentWebAction extends CommonAction {

	
	private static final Logger logger  = Logger.getLogger(AcmentWebAction.class);
	
	/**
	 * 个人中心公告service
	 */
	private IAnnouncement acementService;
	
	/**
	 * 公告ID
	 */
	private int id;
	
	/**
	 * 专业ID拼接的字符串
	 */
	private String subjectIds;
	
	/**
	 * 当前公告内容实体
	 */
	private Announcement announcement;			//展示当前文章
	
	/**
	 * 上一篇公告实体
	 */
	private Announcement announcementUp;		//上一篇
	
	/**
	 * 下一篇公告实体
	 */
	private Announcement announcementDown;		//下一篇
	
	public void setAcementService(IAnnouncement acementService) {
		this.acementService = acementService;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubjectIds() {
		return subjectIds;
	}

	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public Announcement getAnnouncementUp() {
		return announcementUp;
	}

	public void setAnnouncementUp(Announcement announcementUp) {
		this.announcementUp = announcementUp;
	}

	public Announcement getAnnouncementDown() {
		return announcementDown;
	}

	public void setAnnouncementDown(Announcement announcementDown) {
		this.announcementDown = announcementDown;
	}

	/**
	 * 课程公告展示
	 * @return
	 */
	public String view(){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("subjectIds", subjectIds);
			announcement = acementService.getById(id);		//展示当前公告
			announcementUp = acementService.getUp(map);		//上一篇
			announcementDown = acementService.getDown(map);	//下一篇
			acementService.updateClickNum(id);				//修改点击次数
		}catch(Exception e){
			return ERROR;
		}
		return "view";
	}
}
