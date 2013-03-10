package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.edu.sys.condition.QueryGroupCondition;
import com.shangde.edu.sys.domain.Group;

/**
 * 组管理接口
 * @author guoqiang.liu
 */
public interface IGroup {
    /**
     * 添加组
     * @param group 要添加的组
     * @return id
     */
    public java.lang.Integer addGroup(Group group);

    /**
     * 根据id删除一个组
     * @param groupId 要删除的id
     */
    public void delGroupById(int groupId);

    /**
     * 修改组
     * @param group 要修改的组
     */
    public void updateGroup(Group group);

    /**
     * 根据id获取单个组对象
     * @param groupId 要查询的id
     * @return 年级
     */
    public Group getGroupById(int groupId);

    /**
     * 根据条件获取组列表
     * @param queryGroupCondition 查询条件
     * @return 查询结果
     */
    public List<Group> getGroupList(QueryGroupCondition queryGroupCondition);
    
    /**
     * 根据条件获取组列表
     * @param queryGroupCondition 查询条件
     * @return 查询结果
     */
    public List<Group> getGroupListForTechTree(QueryGroupCondition queryGroupCondition);
    
    /**
     * 根据条件获取组列表
     * @param queryGroupCondition 查询条件
     * @return 查询结果
     */
    public List<Group> getFirGroup(QueryGroupCondition queryGroupCondition);
    
    public List<Group> getFirGroupForDwr();
    /**
     * 得到该组下的下一级组元素
     * @param groupId
     * @return
     */
    public List<Group> getChildGroupById(String groupId);
    /**
     * 删除组
     * @param groupId
     * @return
     */
    public void deleteGroups(String groupIds);
    
}