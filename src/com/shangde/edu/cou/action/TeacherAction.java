package com.shangde.edu.cou.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.HTMLDecoder;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.condition.QueryTcCouRecordCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.ITeacher;
import com.shangde.edu.cusmgr.dto.CourseDTO;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TeacherAction extends CommonAction{
	
	/**
	 * 教师服务对象
	 */
	private ITeacher teacherService;
	
	/**
	 * 教师实体
	 */
	private Teacher teacher = new Teacher();
	
	/**
	 * 教师查询条件
	 */
	private QueryTeacherCondition queryTeacherCondition;
	
	/**
	 * 课程分类服务对象
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 教师list
	 */
	private List<Teacher> teacherList=new ArrayList<Teacher>();
	
	/**
	 * 课程分类list
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();

	/**
	 * 课程分类list
	 */
	private List<Coursesort> courseSortList2 = new ArrayList<Coursesort>();
	
	/**
	 * 课程DTOlist
	 */
	private List<CourseDTO> courseDTOList = new ArrayList<CourseDTO>();

	/**
	 * id数组
	 */
	private int[] ids;
	
	/**
	 * 添加教师
	 * @return String
	 * @thorows Exception
	 */
	public String addTeacher(){
		try {
			teacher.setPicPath("");
			if(getFiles() != null && getFiles().length != 0) {
				Date date = new Date();
				//设定上传图片名称
				String picPath = sdf.format(date) + teacherService.addTeacher(teacher) + getFileType(getFilesFileName()[0]);
				teacher.setPicPath(picPath);
				teacherService.updateTeacher(teacher);
				
				//为上传准备参数，文件名list和文件list
				List<String> fileNameList = new ArrayList<String>();
				fileNameList.add(picPath);
				
				//文件list
				List<File> fileList = new ArrayList<File>();
				fileList.add(getFiles()[0]);
				upload(fileNameList, fileList);
				
			} else {
				teacherService.addTeacher(teacher);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}
	

	/**
	 * 修改教师
	 * @return String
	 * @thorows Exception
	 */
	public String updateTeacher(){
		try {
			if(getFiles() != null && getFiles().length != 0) {
				
				//如果此教师本没有头像，那么就设定文件名字，否则就使用之前的头像文件名，将之覆盖
				if(teacher.getPicPath() == null  ||  "".equals(teacher.getPicPath().trim())) {
					Date date = new Date();
					String picPath = sdf.format(date) + teacher.getTcId() + getFileType(getFilesFileName()[0]);
					teacher.setPicPath(picPath);
				}
				
				//准备上传参数
				List<String> fileNameList = new ArrayList<String>();
				fileNameList.add(teacher.getPicPath());
				List<File> fileList = new ArrayList<File>();
				fileList.add(getFiles()[0]);
				upload(fileNameList, fileList);
			}
			
			//如果教师没有头像，那么赋值空串
			if(teacher.getPicPath() == null) {
				teacher.setPicPath("");
			}
			teacherService.updateTeacher(teacher);
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 根据ids删除教师
	 * @return String
	 * @thorows Exception
	 */
	public String deleteTeacher(){
		try {
			if(ids != null) {
				for(int i=0; i<ids.length; i++) {
					//删除教师改为置状态位，所以不删头像文件
//					teacher = teacherService.getTeacherById(ids[i]);
//					File file = new File(getRealSavePath() + "/" + teacher.getPicPath());
//					if(file.exists()) {
//						file.delete();
//					}
					teacherService.delTeacherById(ids[i]);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "relist";
	}

	/**
	 * 分页查询
	 * @return String
	 * @thorows Exception
	 */
	public String showTeacherList() {
		try {
			//根据名称搜索
			String name = getQueryTeacherCondition().getName();
			if(name != null) {
				getQueryTeacherCondition().setName(name.trim());
			}
			
			setPage(teacherService.getTeacherListByCondition(getQueryTeacherCondition()));
			setPageUrlParms();
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "list";
	}
	
	/**
	 * 初始化添加页面
	 * @return String
	 * @thorows Exception
	 */
	public String initAddTeacher() {
		return "addPage";
	}

	/**
	 * 初始化修改页面
	 * @return String
	 * @thorows Exception
	 */
	public String initUpdateTeacher() {
		try {
			teacher = teacherService.getTeacherById(teacher.getTcId());
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "updatePage";
	}
	
	/**
	 * 初始化教师选课功能，暂时不用
	 * @return
	 */
	public String initSelectCourse() {
		try {
			if(getQueryTeacherCondition().getCourseName() != null && !getQueryTeacherCondition().getCourseName().trim().equals("")){
				if(getQueryTeacherCondition().getCourseName().indexOf("&#") > -1){
					getQueryTeacherCondition().setCourseName(HTMLDecoder.decode(getQueryTeacherCondition().getCourseName()));//将这类代码转换成中文
				}
			}
			
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			//获取课程所属分类
			Coursesort fSort = null;
			Coursesort sSort = null;
			Coursesort tSort = null;
			
			if(getQueryTeacherCondition().getSortId() != null){
				tSort = coursesortService.getCoursesortById(getQueryTeacherCondition().getSortId());
			}
			
			if(tSort != null && tSort.getPId() != 0){
				sSort = coursesortService.getCoursesortById(tSort.getPId());
			}
			
			if(sSort!= null && sSort.getPId() != 0){
				fSort = coursesortService.getCoursesortById(sSort.getPId());
			}
			
			if(fSort != null){
				courseSortList2.add(fSort);
			}
			
			if(sSort != null){
				courseSortList2.add(sSort);
			}
			
			if(tSort != null){
				courseSortList2.add(tSort);
			}
			
			setPage(teacherService.getNomalCourseListBySortId(getQueryTeacherCondition()));
			setPageUrlParms();
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "selectCoursePage";
	}
	
	/**
	 * 选课
	 * @return
	 */
	public String chooseCourse() {
		try {
			teacherService.addchooseCourse(teacher.getTcId(), ids);
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}
	
	/**
	 * 取消选课
	 * @return
	 */
	public String unchoose() {
		try {
			QueryTcCouRecordCondition queryTcCouRecordCondition = new QueryTcCouRecordCondition();
			queryTcCouRecordCondition.setTcId(teacher.getTcId());
			queryTcCouRecordCondition.setCourseId(ids[0]);
			
			teacherService.delTcCouRecord(queryTcCouRecordCondition);
			sendMessage("success");
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}
	

	
	/**
	 * 查看教师信息
	 * @return
	 */
	public String viewTeacher() {
		try {
			teacher = teacherService.getTeacherById(teacher.getTcId());
			courseDTOList = teacherService.getCourseListByTcId(teacher.getTcId());
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "viewTeacher";
	}
	
	public void sendMessage(String message) throws IOException {
		this.getServletResponse().setCharacterEncoding("utf-8");
		this.getServletResponse().getWriter().write(message);
	}

	public Teacher getTeacher() {
		if(teacher == null) {
			teacher = new Teacher();
		}
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public QueryTeacherCondition getQueryTeacherCondition() {
		if(queryTeacherCondition == null) {
			queryTeacherCondition = new QueryTeacherCondition();
		}
		return queryTeacherCondition;
	}

	public void setQueryTeacherCondition(QueryTeacherCondition queryTeacherCondition) {
		this.queryTeacherCondition = queryTeacherCondition;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public void setTeacherService(ITeacher teacherService) {
		this.teacherService = teacherService;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public List<Coursesort> getCourseSortList2() {
		return courseSortList2;
	}

	public void setCourseSortList2(List<Coursesort> courseSortList2) {
		this.courseSortList2 = courseSortList2;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<CourseDTO> getCourseDTOList() {
		return courseDTOList;
	}

	public void setCourseDTOList(List<CourseDTO> courseDTOList) {
		this.courseDTOList = courseDTOList;
	}
}
