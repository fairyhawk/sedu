package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.ClazzCou;
import com.shangde.edu.cou.condition.QueryClazzCouCondition;

/**
 * ClazzCou����ӿ�
 * User: guoqiang.liu
 * Date: 2010-10-14
 */
public interface IClazzCou {
    /**
     * ���ClazzCou
     * @param clazzCou Ҫ��ӵ�ClazzCou
     * @return id
     */
    public java.lang.Integer addClazzCou(ClazzCou clazzCou);

    /**
     * ���idɾ��һ��ClazzCou
     * @param id Ҫɾ���id
     */
    public void delClazzCouById(int id);

    /**
     * �޸�ClazzCou
     * @param clazzCou Ҫ�޸ĵ�ClazzCou
     */
    public void updateClazzCou(ClazzCou clazzCou);

    /**
     * ���id��ȡ����ClazzCou����
     * @param id Ҫ��ѯ��id
     * @return �꼶
     */
    public ClazzCou getClazzCouById(int id);

    /**
     * ��������ȡClazzCou�б�
     * @param queryClazzCouCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<ClazzCou> getClazzCouList(QueryClazzCouCondition queryClazzCouCondition);
    
    /**
     * 按班级名查询课程列表
     * @param queryClazzCouCondition
     * @return
     */
    public PageResult getCourseListByClazzId(QueryClazzCouCondition queryClazzCouCondition);
    
    /**
     * 判断在班级中课程是否存在
     * @param queryClazzCouCondition
     * @return
     */
    public int isExistInClazz(QueryClazzCouCondition queryClazzCouCondition);
    
    /**
     * 从班级中删除课程
     * @param queryClazzCouCondition
     */
    public void deleteCourseInClazz(QueryClazzCouCondition queryClazzCouCondition);
    
    /**
     * 
     * @param courseId
     * @return
     * @authorcxs
     * 功能：按课程ID获取班级集合
     * @param args
     */
    public List getClazzCouByCourseId(int courseId);
}