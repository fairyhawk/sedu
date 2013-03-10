package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Tjcourse;
import com.shangde.edu.cou.condition.QueryTjcourseCondition;

/**
 * Tjcourse推荐服务接口ӿ�
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface ITjcourse {
    /**
     * 添加推荐课程���Tjcourse
     * @param tjcourse 
     * @return id
     */
    public java.lang.Integer addTjcourse(Tjcourse tjcourse);

    /**
     * 删除推荐课程��Tjcourse
     * @param id
     */
    public void delTjcourseById(int id);

    /**
     * 更新推荐课程�޸�Tjcourse
     * @param tjcourse 
     */
    public void updateTjcourse(Tjcourse tjcourse);

    /**
     * 获取推荐课程���id��ȡ����Tjcourse����
     * @param id 推荐课程ID
     * @return 推荐课程
     */
    public Tjcourse getTjcourseById(int id);

    /**
     * 获取推荐课程集合��������ȡTjcourse�б�
     * @param queryTjcourseCondition
     * @return ��结果集合���
     */
    public List<Tjcourse> getTjcourseList(QueryTjcourseCondition queryTjcourseCondition);
    
    /**
     * 删除推荐课程
     * @param tjcourse
     */
    public void delTjcourseByCourseId(Tjcourse tjcourse);
}