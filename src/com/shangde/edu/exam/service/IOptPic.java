package com.shangde.edu.exam.service;

import java.util.List;
import com.shangde.edu.exam.domain.OptPic;
import com.shangde.edu.exam.condition.QueryOptPicCondition;

/**
 * OptPic 选项图片ӿ�
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IOptPic {
    /**
     * 添加选项图片���OptPic
     * @param optPic 选项图片
     * @return id
     */
    public java.lang.Integer addOptPic(OptPic optPic);

    /**
     * ���idɾ��删除选项图片
     * @param picId 选项图片ID
     */
    public void delOptPicById(int picId);

    /**
     * 更新选项图片�޸�OptPic
     * @param optPic 
     */
    public void updateOptPic(OptPic optPic);

    /**
     * 通过ID获取选项ID
     * @param picId 图片Id
     * @return 选项图片
     */
    public OptPic getOptPicById(int picId);

    /**
     * 获取选项图片集合��������ȡOptPic�б�
     * @param queryOptPicCondition 查询条件
     * @return 图片集合���
     */
    public List<OptPic> getOptPicList(QueryOptPicCondition queryOptPicCondition);
}