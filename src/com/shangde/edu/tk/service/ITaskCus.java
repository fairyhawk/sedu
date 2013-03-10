package com.shangde.edu.tk.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;

/**
 * TaskCus����ӿ�
 * User: guoqiang.liu
 * Date: 2010-12-20
 */
public interface ITaskCus extends Serializable {
    /**
     * ���TaskCus
     * @param taskCus Ҫ��ӵ�TaskCus
     * @return id
     */
    public java.lang.Integer addTaskCus(TaskCus taskCus);

    /**
     * ���idɾ��һ��TaskCus
     * @param id Ҫɾ���id
     */
    public void delTaskCusById(int id);
    
    /**
     * @author cxs
     * 功能：删除用户人任务 by 任务ID
     * @param args
     * @param taskId
     */
    public void delTaskCusByTaskId(int taskId);
    /**
     * �޸�TaskCus
     * @param taskCus Ҫ�޸ĵ�TaskCus
     */
    public void updateTaskCus(TaskCus taskCus);
    /**
     * @author yjd
     * 通过用户ID删除任务
     * @param cusId
     */
    public void deleteTaskCusByCusId(int cusId);

    /**
     * ���id��ȡ����TaskCus����
     * @param id Ҫ��ѯ��id
     * @return �꼶
     */
    public TaskCus getTaskCusById(int id);
    
    /**
     * @author cxs
     * 功能：通过关键字获取任务
     * @param args
     * @param key
     * @return
     */
    public TaskCus getTaskCusByKey(QueryTaskCusCondition queryTaskCusCondition);

    /**
     * ��������ȡTaskCus�б�
     * @param queryTaskCusCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<TaskCus> getTaskCusList(QueryTaskCusCondition queryTaskCusCondition);
    
    /**
     * 
     * @author chenshuai
     * 功能：获取用户前台任务
     * @param args
     * @param queryTaskCusCondition
     * @return
     */
    public List<TaskCus> getWebTaskCusList(QueryTaskCusCondition queryTaskCusCondition);
    
    /**
     * @author chenshuai
     * 功能：根据用户的Id和任务Id查询记录条数
     * @param args
     * @param queryTaskCusCondition
     * @return
     */
    public Integer getCountByCusIdAndTaskId(QueryTaskCusCondition queryTaskCusCondition);
    
    /**
     * @author chenshuai
     * 功能：根据任务ID及学员ID获取用户任务
     * @param args
     * @param queryTaskCusCondition
     * @return
     */
    public TaskCus getTaskCusByTaskIdAndCusId(QueryTaskCusCondition queryTaskCusCondition);
    
    /**
     * @author chenshuai
     * 功能：获取用户第一个任务
     * @param args
     * @param queryTaskCusCondition
     * @return
     */
    public TaskCus getFirstWebTaskCus(QueryTaskCusCondition queryTaskCusCondition);
}