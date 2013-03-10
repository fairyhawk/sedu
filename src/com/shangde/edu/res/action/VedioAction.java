package com.shangde.edu.res.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ITeacher;
import com.shangde.edu.res.condition.QueryVedioCondition;
import com.shangde.edu.res.domain.Picture;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.service.IPicture;
import com.shangde.edu.res.service.IVedio;


/**
 * 视频管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class VedioAction extends CommonAction {

	/**
	 * 声名视频的PO对象
	 */
	private Vedio vedio;
	/**
	 * 声名视频的服务
	 */
	private IVedio vedioService;
	/**
	 * 查询用到的condition
	 */

	private QueryVedioCondition queryVedioCondition;

	/**
	 * 定义一个整型的数组
	 */
	private int[] voId;
	/**
	 * 查询条件
	 */
	private String searchKey;
	/**
	 * 课程分类查询条件
	 */
	private QueryCoursesortCondition queryCourseSortCondition;

	/**
	 * 课程分类集
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 课程Id
	 */
	private int courseId;
	
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;;
	
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	/**
	 * 知识点集合
	 */
	private List<Kpoint> kpointList;
	/**
	 * 知识点对象
	 */
	private Kpoint kpoint;
	/**
	 * 声明图片的服务
	 */
	private IPicture pictureService;
	
	/**
	 * 图片用到的List
	 */
	private List<File> fileList=new ArrayList<File>(); 
	
	private List<String> fileListFileName=new ArrayList<String>();
	
	private List<Picture> pictureList;

	private int mainpicid;
	
	
	/**
	 * 视频附属图片
	 */
	private List<File> otherPics = new ArrayList<File>();
	
	private List<String> otherPicsFileName = new ArrayList<String>();
	
	/**
	 * 视频首图片
	 */
	private List<File> firstPic = new ArrayList<File>();
	
	private List<String> firstPicFileName = new ArrayList<String>();
	
	/**
	 * 视频已添加的视频附属图片
	 */
	private List<File> otherPicEd = new ArrayList<File>();
	
	private List<String> otherPicEdFileName = new ArrayList<String>();
	
	private int[] pic;//图片ids
    
	/**
	 * 课程
	 */
	private Course course;
	
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	
	private String oldFileName;
	/**
	 * 教师服务对象
	 */
	private ITeacher teacherService;
	/**
	 * 教师查询条件
	 */
	private QueryTeacherCondition queryTeacherCondition;
	/**
	 * 教师list
	 */
	private List<Teacher> teacherList=new ArrayList<Teacher>();
	private int listSize;
	
	private int pointid;
	
	private int fuSortId = -2;
	private int suSortId;
	private int tuSortId;
	private int cuSortId;
	
	private String fuSortIdString;
	private String suSortIdString;
	private String tuSortIdString;
	private String cuSortIdString;
	/**
	 * 获得视频列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getVedioList() {
		try {
			if(searchKey!=null&&!"".equals(searchKey.trim())){
				this.getQueryVedioCondition().setSearchKey(searchKey.trim());
			}
			getQueryVedioCondition().setPageSize(25);
			setPage(this.vedioService.getVedioList(getQueryVedioCondition()));
			this.getPage().setPageSize(25);
			setPageUrlParms();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "listVedio";

	}

	/**
	 * 打开添加视频的页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toAddVedio() {
		try{
			//查询所有教师
			teacherList=this.teacherService.getTeacherList(getQueryTeacherCondition());
			this.getQueryCourseSortCondition().setPId(-1);
			courseSortList = coursesortService.getChildCoursesortList(queryCourseSortCondition);
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "toAddVedio";

	}

	/**
	 * 添加视频
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addVedio() {
		try {
			
			String path="/upload"+this.getSavePath();
			Date date = new Date();
			vedio.setCreateTime(date);
			if(vedio.getTcId()!=0){
				Teacher teacher=this.teacherService.getTeacherById(vedio.getTcId());
				vedio.setLecture(teacher.getName());
				//this.vedioService.updateVedio(vedio);
			}
			if(vedio.getPointid()!=0){
				 Vedio newVedio=this.vedioService.getVedioByPointid(vedio.getPointid());
				if(newVedio!=null){
					this.vedioService.deleteVedioByPointid(vedio.getPointid());
				}
			}
			this.vedioService.addVedio(vedio);
			//更新视频表，把老师名称更新上
			if(this.getFileList()!=null){
				Picture picture=null;
				Picture pictureUp=null;
				List<String> nameList=new ArrayList<String>();
				for(int i=0;i<this.getFileList().size();i++){
					picture=new Picture();
					picture.setPicName("暂时不需要");
					if(i==0){
					picture.setIsIndex(Picture.PIC_MAIN_PIC);
					}else{
					picture.setIsIndex(Picture.PIC_OTHER_PIC);	
					}
					picture.setPicContent("aaa");
					picture.setVoId(vedio.getVoId());
					picture.setCreatTime(date);
					picture.setPicSize(String.valueOf(this.getFileList().get(i).length()));
					String name=this.getFileListFileName().get(i);
					name=name.substring(name.indexOf("."),name.length());
					picture.setPicType(name);
					int pId=this.pictureService.addPicture(picture);
					pictureUp=this.pictureService.getPictureById(pId);
					String fileName=sdf.format(date)+"-"+pId+name;
					pictureUp.setPicUrl(path+"/"+fileName);
					//把首选视频ＩＤ，图片地址存到课程表中。
					if(i==0){
						 if(vedio.getVoRadOne()!=null&&!"".equals(vedio.getVoRadOne())){
				            	if(vedio.getVoRadOne().equals("1")){
				            		course=this.courseService.getCourseById(courseId);
				            		course.setTjVedioId(vedio.getVoId());
				            		course.setVedioPicUrl(fileName);
				            		this.courseService.updateCourse(course);
				            	}
				            }
					}
					pictureUp.setPicName(fileName);
					this.pictureService.updatePicture(pictureUp);
					nameList.add(fileName);
				}
				
				this.upload(nameList, fileList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "listAllVedio";
	}

	/**
	 * 打开修改视频的页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toEditVedio() {
		try {
			//查询所有教师
			teacherList=this.teacherService.getTeacherList(getQueryTeacherCondition());
			vedio = this.vedioService.getVedioById(vedio.getVoId());
			kpoint=this.kpointService.getKpointById(vedio.getPointid());
			this.getQueryCourseSortCondition().setPId(-1);
			courseSortList = coursesortService.getChildCoursesortList(queryCourseSortCondition);
			pictureList =this.pictureService.getPictureByVoId(vedio.getVoId());
			listSize=pictureList.size();
			
			if(kpoint!=null){

				course = courseService.getCourseById(kpoint.getCourseId());
				
				Coursesort temp1 = coursesortService.getCoursesortById(course.getSortId());
				Coursesort temp2 = coursesortService.getCoursesortById(temp1.getPId());
				Coursesort temp3 = coursesortService.getCoursesortById(temp2.getPId());
				
				this.fuSortIdString = temp3.getCoursesortName();
				this.suSortIdString = temp2.getCoursesortName();
				this.tuSortIdString = temp1.getCoursesortName();
				this.cuSortIdString=course.getTitle();
				
				this.fuSortId = temp3.getCoursesortId();
				this.suSortId = temp2.getCoursesortId();
				this.tuSortId = temp1.getCoursesortId();
				this.cuSortId=course.getCourseId();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "toEditVedio";

	}

	/**
	 * 修改视频
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editVedio() {
		try {
			
			Date date = new Date();
			vedio.setCreateTime(date);
			if (vedio.getTcId() != 0) {
				Teacher teacher = this.teacherService.getTeacherById(vedio
						.getTcId());
				vedio.setLecture(teacher.getName());
			//	this.vedioService.updateVedio(vedio);
			}
			if(vedio.getPointid()!=0&&pointid!=0){
				if(vedio.getPointid()!=pointid){
					 Vedio newVedio=this.vedioService.getVedioByPointid(vedio.getPointid());
						if(newVedio!=null){
							this.vedioService.deleteVedioByPointid(vedio.getPointid());
						}
				}
			}
			this.vedioService.updateVedio(vedio);
			String isExistFile = servletRequest.getParameter("mainpic"
					+ mainpicid);// 主图片是否被修改
			List<String> filenames = new ArrayList<String>();

			Picture vpic = null;
			String fileName = null;
			String filePath = null;
			String newFileName=null;
			if (isExistFile != null && !isExistFile.equals("")) {// 主图片改动

				fileName = sdf.format(new Date()) + "-" + this.mainpicid
						+ this.getFileType(this.firstPicFileName.get(0));
				filePath = "/upload/res/vedio/" + fileName;
				vpic = pictureService.getPictureById(this.mainpicid);

				vpic.setPicType(this.getFileType(this.firstPicFileName.get(0)));
				vpic.setPicName(fileName);
				vpic.setPicUrl(filePath);
			
				this.pictureService.updatePicture(vpic);
				filenames.add(fileName);
				this.upload(filenames, this.firstPic);
				filenames = new ArrayList<String>();
			}else{

				if(this.getFileList()!=null){
					Picture picture=null;
					Picture pictureUp=null;
					List<String> nameList=new ArrayList<String>();
					for(int i=0;i<this.getFileList().size();i++){
						picture=new Picture();
						picture.setPicName("暂时不需要");
						if(i==0){
						picture.setIsIndex(Picture.PIC_MAIN_PIC);
						picture.setPicContent("aaa");
						picture.setVoId(vedio.getVoId());
						picture.setCreatTime(date);
						picture.setPicSize(String.valueOf(this.getFileList().get(i).length()));
						String name=this.getFileListFileName().get(i);
						name=name.substring(name.indexOf("."),name.length());
						picture.setPicType(name);
						int pId=this.pictureService.addPicture(picture);
						pictureUp=this.pictureService.getPictureById(pId);
						newFileName=sdf.format(date)+"-"+pId+name;
						pictureUp.setPicUrl("/upload/res/vedio/"+newFileName);
						
						pictureUp.setPicName(newFileName);
						this.pictureService.updatePicture(pictureUp);
						nameList.add(newFileName);
						}
					}
					
					this.upload(nameList, fileList);
				}
				
				
				
			}
			// 首选视频是否为空
			if (vedio.getVoRadOne() != null && !"".equals(vedio.getVoRadOne())) {
				// 根据课程id查到这个课程
				course = this.courseService.getCourseById(courseId);
				// 判断是否为首选视频 0为否，1为是
				if (vedio.getVoRadOne().equals("0")) {
					// 判断修改的视频ＩＤ是否与查出课程的视频ＩＤ相等，如果相等，则置空。
					if (vedio.getVoId() == course.getTjVedioId()) {
						course.setTjVedioId(0);
						course.setVedioPicUrl("");
						this.courseService.updateCourse(course);
					}
				} else {
					// 是首选视频
					if (vedio.getVoId() == course.getTjVedioId()) {
						if (fileName != null && !"".equals(fileName)) {
							course.setVedioPicUrl(fileName);
						} else if (newFileName != null
								&& !"".equals(newFileName)) {
							course.setVedioPicUrl(newFileName);
						} else {
							course.setVedioPicUrl(oldFileName);
						}
						this.courseService.updateCourse(course);

					} else {
						course.setTjVedioId(vedio.getVoId());
						if (fileName != null && !"".equals(fileName)) {
							course.setVedioPicUrl(fileName);
						} else if (newFileName != null
								&& !"".equals(newFileName)) {
							course.setVedioPicUrl(newFileName);
						} else {
							course.setVedioPicUrl(oldFileName);
						}
						this.courseService.updateCourse(course);

					}

				}

			}
			
//			
//			//更新课程表中的字段
//			if (vedio.getVoRadOne() != null && !"".equals(vedio.getVoRadOne())) {
//				course = this.courseService.getCourseById(courseId);
//				//如果修改的视频ＩＤ与课程中的首选视频ＩＤ相同，则去修改
//				if (vedio.getVoId() == course.getTjVedioId()) {
//						if (vedio.getVoRadOne().equals("0")) {
//						course.setTjVedioId(0);
//						course.setVedioPicUrl("");
//						this.courseService.updateCourse(course);
//					}else{
//						if(fileName!=null&&!"".equals(fileName)){
//						course.setVedioPicUrl(fileName);
//						}else if(newFileName!=null&&!"".equals(newFileName))
//						{
//						course.setVedioPicUrl(newFileName);
//						}else{
//						 course.setVedioPicUrl(oldFileName);
//						}
//						this.courseService.updateCourse(course);
//					}
//				}else{
//					//如果不同，直接覆盖首选视频ＩＤ和图片路径
//					if(vedio.getVoRadOne().equals("1")){
//					course.setTjVedioId(vedio.getVoId());
//					if(fileName!=null&&!"".equals(fileName)){
//						course.setVedioPicUrl(fileName);
//					} else if (newFileName != null && !"".equals(newFileName)) {
//						course.setVedioPicUrl(newFileName);
//					} else {
//						course.setVedioPicUrl(oldFileName);
//					}
//					this.courseService.updateCourse(course);
//					}
//				}
//	
//
//			}
			if (pic != null) {
				for (int i = 0; i < this.pic.length; i++) {
					isExistFile = servletRequest.getParameter("otherPicEd"
							+ pic[i]);
					if (isExistFile != null && !isExistFile.equals("")) {// 附属图片改动

						vpic = pictureService.getPictureById(this.pic[i]);
						fileName = sdf.format(new Date())
								+ "-"
								+ pic[i]
								+ this.getFileType(this.otherPicEdFileName
										.get(0));
						filePath = "/upload/res/vedio/" + fileName;
						vpic.setPicType(this
								.getFileType(this.otherPicEdFileName.get(0)));
						vpic.setPicName(fileName);
						vpic.setPicUrl(filePath);
						filenames.add(fileName);
						pictureService.updatePicture(vpic);

					}
				}
				this.upload(filenames, otherPicEd);
			}

			
			filenames = new ArrayList<String>();

			File fileTemp = null;
			String filetype = null;

			int picid = 0;
			long filesize = 0;

			for (int i = 0; i < otherPics.size(); i++) {// 添加新的附属图片
				vpic = new Picture();

				fileTemp = otherPics.get(i);
				fileName = sdf.format(new Date());
				filePath = "/upload/res/vedio/" + fileName;
				filesize = fileTemp.length();
				filetype = this.getFileType(otherPicsFileName.get(i));
				vpic.setVoId(vedio.getVoId());
				vpic.setIsIndex(Picture.PIC_OTHER_PIC);
				vpic.setPicName(fileName);
				vpic.setPicUrl(filePath);
				vpic.setPicSize(String.valueOf(filesize));
				vpic.setPicType(filetype);
				vpic.setCreatTime(date);
				picid = pictureService.addPicture(vpic);
				vpic = pictureService.getPictureById(picid);

				fileName = fileName + "-" + picid + this.getFileType(filetype);
				filePath = filePath + "-" + picid + this.getFileType(filetype);
				vpic.setPicName(fileName);
				vpic.setPicUrl(filePath);
				filenames.add(fileName);
				pictureService.updatePicture(vpic);

				this.upload(filenames, otherPics);

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		//return "listAllVedio";
		return "listAllVedio";

	}

	/**
	 * 删除单个视频
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteVedio() {
		try {
			this.vedioService.delVedioById(vedio.getVoId());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "listAllVedio";
	}

	/**
	 * 删除多个视频
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteAllVedio() {
		try {
			if (voId != null) {
				for (int i = 0; i < voId.length; i++) {
					this.vedioService.delVedioById(new Integer(voId[i]));

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "listAllVedio";
	}
	/**
	 * 把视频添加知识点上
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addVedioPoint(){
		try{
		this.getQueryKpointCondition().setCourseId(courseId);
		queryKpointCondition.setLeaf(-1);
		//kpointList=kpointService.getKpointListByCourseId(queryKpointCondition);
		List<KeyValueDTO> myList = kpointService.getKpointDTOListByCourseId(queryKpointCondition);
		//List<KeyValueDTO> myList = new ArrayList<KeyValueDTO>();
//		
//		KeyValueDTO keyvalue = null;
//		
//		for(int i=0; i < kpointList.size(); i++) {
//			keyvalue = new KeyValueDTO();
//			Kpoint grouptemp = kpointList.get(i); 
//			keyvalue.setKe(grouptemp.getPId());
//			keyvalue.setKey(grouptemp.getPointId());
//			keyvalue.setValue(grouptemp.getName());
//			myList.add(keyvalue);
//		}
		this.setResult(new Result<List<KeyValueDTO>>(true,"",null,myList));
	}catch(Exception ex){
		ex.printStackTrace();
		return ERROR;
	}
	return "getKpointById";

}
public String deletePic(){
	 
	try {
		this.pictureService.delPictureById(this.mainpicid);
		
	} catch (Exception e) {
		e.printStackTrace();
		return ERROR;
	}
	return "deletePicSuccess";
	
}
//修改视频分数
public Double updateVedioScores(Map<String, Integer> flexParam){
	Double voScores = 0.0;
	try{
	if(flexParam != null){
		int vId = flexParam.get("videoId");
		int scores = flexParam.get("scores");
		if(vId != 0){
			vedio = this.vedioService.getVedioById(vId);
			if(vedio != null){
				//voScores = (vedio.getVoBScores() + scores) / 2.0 ;
				voScores = Double.valueOf(scores);
				vedio.setVoScores(voScores);
				this.vedioService.updateVedio(vedio);
			}
		}
	}
	}catch(Exception e){
		System.out.println(e.toString());
	}
	return voScores;
}
//查询视频打的分数
public Number queryVedioScores(int vId){
	Number voScores = 0;
	if(vId!=0){
		vedio = this.vedioService.getVedioById(vId);
		if(vedio != null){
			voScores = vedio.getVoScores();
			if(voScores.intValue() == 0){
				voScores = 10;
			}
		}
	}
	return voScores;
}

public int getMainpicid() {
	return mainpicid;
}

public void setMainpicid(int mainpicid) {
	this.mainpicid = mainpicid;
}

public List<Picture> getPictureList() {
	return pictureList;
}

public void setPictureList(List<Picture> pictureList) {
	this.pictureList = pictureList;
}

public List<String> getFileListFileName() {
	return fileListFileName;
}

public void setFileListFileName(List<String> fileListFileName) {
	this.fileListFileName = fileListFileName;
}

public IPicture getPictureService() {
	return pictureService;
}

public void setPictureService(IPicture pictureService) {
	this.pictureService = pictureService;
}

public List<File> getFileList() {
	return fileList;
}

public void setFileList(List<File> fileList) {
	this.fileList = fileList;
}

public Kpoint getKpoint() {
	return kpoint;
}

public void setKpoint(Kpoint kpoint) {
	this.kpoint = kpoint;
}

public QueryCoursesortCondition getQueryCourseSortCondition() {
	if(queryCourseSortCondition == null){
		queryCourseSortCondition = new QueryCoursesortCondition();
	}
	return queryCourseSortCondition;
}

public void setQueryCourseSortCondition(
		QueryCoursesortCondition queryCourseSortCondition) {
	this.queryCourseSortCondition = queryCourseSortCondition;
}

public List<Coursesort> getCourseSortList() {
	return courseSortList;
}

public void setCourseSortList(List<Coursesort> courseSortList) {
	this.courseSortList = courseSortList;
}

public ICoursesort getCoursesortService() {
	return coursesortService;
}

public void setCoursesortService(ICoursesort coursesortService) {
	this.coursesortService = coursesortService;
}

public int getCourseId() {
	return courseId;
}

public void setCourseId(int courseId) {
	this.courseId = courseId;
}

public IKpoint getKpointService() {
	return kpointService;
}

public void setKpointService(IKpoint kpointService) {
	this.kpointService = kpointService;
}

public QueryKpointCondition getQueryKpointCondition() {
	if(queryKpointCondition == null){
		queryKpointCondition = new QueryKpointCondition();
	}
	return queryKpointCondition;
}

public void setQueryKpointCondition(QueryKpointCondition queryKpointCondition) {
	this.queryKpointCondition = queryKpointCondition;
}

public List<Kpoint> getKpointList() {
	return kpointList;
}

public void setKpointList(List<Kpoint> kpointList) {
	this.kpointList = kpointList;
}

public String getSearchKey() {
	return searchKey;
}

public void setSearchKey(String searchKey) {
	this.searchKey = searchKey;
}

public int[] getVoId() {
	return voId;
}

public void setVoId(int[] voId) {
	this.voId = voId;
}

public QueryVedioCondition getQueryVedioCondition() {

	if (queryVedioCondition == null) {
		queryVedioCondition = new QueryVedioCondition();

	}

	return queryVedioCondition;
}

public void setQueryVedioCondition(QueryVedioCondition queryVedioCondition) {
	this.queryVedioCondition = queryVedioCondition;
}

public Vedio getVedio() {
	return vedio;
}

public void setVedio(Vedio vedio) {
	this.vedio = vedio;
}

public IVedio getVedioService() {
	return vedioService;
}

public void setVedioService(IVedio vedioService) {
	this.vedioService = vedioService;
}

public List<File> getOtherPics() {
	return otherPics;
}

public void setOtherPics(List<File> otherPics) {
	this.otherPics = otherPics;
}

public List<String> getOtherPicsFileName() {
	return otherPicsFileName;
}

public void setOtherPicsFileName(List<String> otherPicsFileName) {
	this.otherPicsFileName = otherPicsFileName;
}

public List<File> getFirstPic() {
	return firstPic;
}

public void setFirstPic(List<File> firstPic) {
	this.firstPic = firstPic;
}

public List<String> getFirstPicFileName() {
	return firstPicFileName;
}

public void setFirstPicFileName(List<String> firstPicFileName) {
	this.firstPicFileName = firstPicFileName;
}

public List<File> getOtherPicEd() {
	return otherPicEd;
}

public void setOtherPicEd(List<File> otherPicEd) {
	this.otherPicEd = otherPicEd;
}

public List<String> getOtherPicEdFileName() {
	return otherPicEdFileName;
}

public void setOtherPicEdFileName(List<String> otherPicEdFileName) {
	this.otherPicEdFileName = otherPicEdFileName;
}

public int[] getPic() {
	return pic;
}

public void setPic(int[] pic) {
	this.pic = pic;
}


public Course getCourse() {
	return course;
}

public void setCourse(Course course) {
	this.course = course;
}

public ICourse getCourseService() {
	return courseService;
}

public void setCourseService(ICourse courseService) {
	this.courseService = courseService;
}

public String getOldFileName() {
	return oldFileName;
}

public void setOldFileName(String oldFileName) {
	this.oldFileName = oldFileName;
}

public ITeacher getTeacherService() {
	return teacherService;
}

public void setTeacherService(ITeacher teacherService) {
	this.teacherService = teacherService;
}

public QueryTeacherCondition getQueryTeacherCondition() {
	if (queryTeacherCondition == null) {
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

public int getListSize() {
	return listSize;
}

public void setListSize(int listSize) {
	this.listSize = listSize;
}

public int getPointid() {
	return pointid;
}

public void setPointid(int pointid) {
	this.pointid = pointid;
}

public int getFuSortId() {
	return fuSortId;
}

public void setFuSortId(int fuSortId) {
	this.fuSortId = fuSortId;
}

public int getSuSortId() {
	return suSortId;
}

public void setSuSortId(int suSortId) {
	this.suSortId = suSortId;
}

public int getTuSortId() {
	return tuSortId;
}

public void setTuSortId(int tuSortId) {
	this.tuSortId = tuSortId;
}

public String getFuSortIdString() {
	return fuSortIdString;
}

public void setFuSortIdString(String fuSortIdString) {
	this.fuSortIdString = fuSortIdString;
}

public String getSuSortIdString() {
	return suSortIdString;
}

public void setSuSortIdString(String suSortIdString) {
	this.suSortIdString = suSortIdString;
}

public String getTuSortIdString() {
	return tuSortIdString;
}

public void setTuSortIdString(String tuSortIdString) {
	this.tuSortIdString = tuSortIdString;
}

public int getCuSortId() {
	return cuSortId;
}

public void setCuSortId(int cuSortId) {
	this.cuSortId = cuSortId;
}

public String getCuSortIdString() {
	return cuSortIdString;
}

public void setCuSortIdString(String cuSortIdString) {
	this.cuSortIdString = cuSortIdString;
}

}
