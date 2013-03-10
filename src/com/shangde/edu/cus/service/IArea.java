package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.Area;
import com.shangde.edu.cus.condition.QueryAreaCondition;

/**
 * Area管理接口
 * User: guoqiang.liu
 * Date: 2011-03-07
 */
public interface IArea {
    /**
     * 添加Area
     * @param area 要添加的Area
     * @return id
     */
    public java.lang.Integer addArea(Area area);

    /**
     * 根据id删除一个Area
     * @param id 要删除的id
     */
    public void delAreaById(int id);

    /**
     * 修改Area
     * @param area 要修改的Area
     */
    public void updateArea(Area area);

    /**
     * 根据id获取单个Area对象
     * @param id 要查询的id
     * @return 年级
     */
    public Area getAreaById(int id);

    /**
     * 根据条件获取Area列表
     * @param queryAreaCondition 查询条件
     * @return 查询结果
     */
    public List<Area> getAreaList(QueryAreaCondition queryAreaCondition);
}