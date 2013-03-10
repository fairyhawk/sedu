/**
 * ClassName  LastLearnBuyInfo
 *
 * History
 * Create User: liuqinggang
 * Create Date: Apr 2, 2011
 * Update User:
 * Update Date:
 */
package com.shangde.common.task;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangde.common.util.FileUtils;
import com.shangde.edu.cusmgr.dto.LearnBuyInfoDTO;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

/**
 * 每间隔1小时取得，学员最新的学习和购买情况 按课程分组存放
 * 
 * @author liuqinggang
 */

public class LastLearnBuyInfo {
	/**
	 * log
	 */
	protected Log log = LogFactory.getLog(LastLearnBuyInfo.class);

	private ICusCouKpoint cusCouKpointService;// 课程知识点记录;
	private ISubject subjectService;// 学科服务

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public void getLastLearnBuyInfo() {
		log.info("每间隔1天取得，学员最新的学习和购买情况 start");
		int maxNum = 20;// 最大的显示数量
		// 获取在课程的信息 ,所有的课程
		List<LearnBuyInfoDTO> learnBuyInfos = cusCouKpointService
				.getLearningInfoInTimeAllSubject();
		List<Subject> subjectList = subjectService
				.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);// 学科集合
		// 根据课程不同分别存在不同的目录下
		// 生成到static\web\learnBuyInfo\1\index_1.shtml下按subjecId建立文件夹
		String staticPath = getClass().getClassLoader().getResource("")
				.getPath();
		//放在static/web/lastLearnBuyInfo目录下
		String learnBuyInfo = URLDecoder.decode(staticPath.substring(0, staticPath.indexOf("WEB-INF")))
				+ "static" + File.separator + "web" + File.separator + "lastLearnBuyInfo"
				+ File.separator;
		// 清空并生成subjectId目录
		FileUtils.clearFile(learnBuyInfo);
		FileUtils.createPath(learnBuyInfo); 
		for (int i = 0; i < subjectList.size(); i++) {
			Subject subject = subjectList.get(i);
			List<LearnBuyInfoDTO> list = new ArrayList<LearnBuyInfoDTO>();
			FileUtils.createPath(learnBuyInfo + subject.getSubjectId());
			String subjectId = subject.getSubjectId() + ""; 
			for (int j = learnBuyInfos.size() - 1; j >= 0; j--) {
				LearnBuyInfoDTO buyInfoDTO = learnBuyInfos.get(j);
				if ((subjectId).equals(buyInfoDTO.getSubjectId())
						&& (list.size() < maxNum)) {
					list.add(learnBuyInfos.get(j));
				}
			}
			// 以json格式写到文件中
			try {
				FileUtils.writeFile(learnBuyInfo
						+ (subject.getSubjectId() + "") + File.separator
						+ "index_1.shtml", JSONArray.fromObject(list)
						.toString(), true);

			} catch (IOException e) {
				log.error("每间隔1天取得，学员最新的学习和购买情况 错误：", e);
			}
		}
		log.info(" 每间隔1天取得，学员最新的学习和购买情况 end==");
	}
}
