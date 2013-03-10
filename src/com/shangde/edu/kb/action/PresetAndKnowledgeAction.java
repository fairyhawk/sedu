package com.shangde.edu.kb.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.kb.condition.QueryChapterCondition;
import com.shangde.edu.kb.domain.Chapter;
import com.shangde.edu.kb.domain.Section;
import com.shangde.edu.kb.domain.StudyCourse;
import com.shangde.edu.kb.service.IChapter;
import com.shangde.edu.kb.service.ISection;
import com.shangde.edu.kb.service.IStudyCourse;
/**
 * 预设和知识体系管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class PresetAndKnowledgeAction extends CommonAction {
	/**
	 *预设项管理
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getPresetManage(){
		try{
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "prestmanage";
	}
	/**
	 *知识体系管理
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getKnowledgeManage(){
		try{
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "knowledgemanage";
	}
}
