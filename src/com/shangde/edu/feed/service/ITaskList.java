package com.shangde.edu.feed.service;

import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryTaskListCondition;
import com.shangde.edu.feed.domain.TaskList;

/**
 * TaskList管理接口
 * 
 * 
 * @author Libg
 * 
 */
public interface ITaskList {

	/**
	 * 添加TaskList
	 * 
	 * @param taskList
	 *            要添加的TaskList
	 * @return id
	 */
	public int addTaskList(TaskList taskList);

	/**
	 * 根据id删除一个TaskList
	 * 
	 * @param id
	 * @return
	 */
	public int delTaskListById(int id);

	/**
	 * 修改TaskList
	 * 
	 * @param taskList
	 *            要修改的TaskList
	 */
	public int updateTaskList(TaskList taskList);

	/**
	 * 根据id获取单个TaskList对象
	 * 
	 * @param id
	 * @return
	 */
	public TaskList getTaskListById(int id);

	/**
	 * 根据id，email查询记录
	 * 
	 * @param map
	 *            key=id,email
	 * @return
	 */
	public int getTaskListByIdEmail(Map<String, String> map);

	/**
	 * 根据条件获取TaskList列表
	 * 
	 * @param queryTaskListCondition
	 * @return
	 */
	public PageResult getTaskListList(
			QueryTaskListCondition queryTaskListCondition);

	/**
	 * 修改状态
	 * 
	 * @param map
	 *            key=id,status
	 * @return
	 */
	public int updateTaskListStatus(Map map);

	/**
	 * 根据条件获取TaskList详细统计列表
	 * 
	 * 泛型=TaskListDTO
	 * 
	 * @return
	 */
	public PageResult getTaskListVList(
			QueryTaskListCondition queryTaskListCondition);

	/**
	 * 查询TaskId/RegularlySentTime两个字段，返回一个结果集
	 * 
	 * @param map
	 *            key=idsList,value=List对象
	 * @return map key=taskId,value=RegularlySentTime
	 */
	public Map<Integer, String> getTaskIdRegularlySentTime(
			Map<String, Object> map);
}