package com.shangde.edu.tk.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.dto.KeyValueDTO;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.condition.QueryTaskCondition;

/**
 * Task����ӿ�
 * User: guoqiang.liu
 * Date: 2010-12-20
 */
public interface ITask {
    /**
     * ���Task
     * @param task Ҫ��ӵ�Task
     * @return id
     */
    public java.lang.Integer addTask(Task task);

    /**
     * ���idɾ��һ��Task
     * @param taskId Ҫɾ���id
     */
    public void delTaskById(int taskId);

    /**
     * �޸�Task
     * @param task Ҫ�޸ĵ�Task
     */
    public void updateTask(Task task);

    /**
     * ���id��ȡ����Task����
     * @param taskId Ҫ��ѯ��id
     * @return �꼶
     */
    public Task getTaskById(int taskId);

    /**
     * ��������ȡTask�б�
     * @param queryTaskCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<Task> getTaskList(QueryTaskCondition queryTaskCondition);
    
    public PageResult getTaskListByCondition(QueryTaskCondition queryTaskCondition);
    
    public List<KeyValueDTO> getTaskKeyValueList(QueryTaskCondition queryTaskCondition);
}