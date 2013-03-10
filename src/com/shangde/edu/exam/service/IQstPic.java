package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.exam.domain.QstPic;
import com.shangde.edu.exam.condition.QueryQstPicCondition;

/**
 * QstPic����ӿ�
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IQstPic extends Serializable {
    /**
     * 添加试题图片
     * @param qstPic 要添加的试题图片�QstPic
     * @return id
     */
    public java.lang.Integer addQstPic(QstPic qstPic);

    /**
     * 删除试图图片通过ID
     * @param picId 试题图片ID
     */
    public void delQstPicById(int picId);

    /**
     * 更新试题图片�޸�QstPic
     * @param qstPic 要更新的试题图片
     */
    public void updateQstPic(QstPic qstPic);

    /**
     * 根据ID获取试题图片
     * @param picId 图片ID
     * @return 试题图片
     */
    public QstPic getQstPicById(int picId);

    /**
     * 根据条件查询试题图片集合��������ȡQstPic�б�
     * @param queryQstPicCondition 查询条件
     * @return 查询结果集合���
     */
    public List<QstPic> getQstPicList(QueryQstPicCondition queryQstPicCondition);
}