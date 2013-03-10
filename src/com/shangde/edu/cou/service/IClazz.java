package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryClazzCondition;
import com.shangde.edu.cou.domain.Clazz;

/**
 * Clazzӿ�
 * User: guoqiang.liu
 * Date: 2010-10-14
 */
public interface IClazz {
    /**
     * ���Clazz
     * @param clazz Ҫ��ӵ�Clazz
     * @return id
     */
    public java.lang.Integer addClazz(Clazz clazz);

    /**
     * ���idɾ��һ��Clazz
     * @param clazzId Ҫɾ���id
     */
    public void delClazzById(int clazzId);

    /**
     * �޸�Clazz
     * @param clazz Ҫ�޸ĵ�Clazz
     */
    public void updateClazz(Clazz clazz);

    /**
     * ���id��ȡ����Clazz����
     * @param clazzId Ҫ��ѯ��id
     * @return �꼶
     */
    public Clazz getClazzById(int clazzId);

    /**
     * ��������ȡClazz�б�
     * @param queryClazzCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<Clazz> getClazzList(QueryClazzCondition queryClazzCondition);
    
    public PageResult getClazzsListByCondition(QueryClazzCondition queryClazzCondition);
    
    /**
	 * 获取班级for TAG
	 * @param queryClazzCondition
	 * @return
	 */
	public List getClazzListForTag(QueryClazzCondition queryClazzCondition);
}