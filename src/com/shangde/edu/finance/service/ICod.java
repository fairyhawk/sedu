package com.shangde.edu.finance.service;

import java.util.List;
import com.shangde.edu.finance.domain.Cod;
import com.shangde.edu.finance.condition.QueryCodCondition;

/**
 * Cod管理接口
 * User: guoqiang.liu
 * Date: 2011-03-09
 */
public interface ICod {
    /**
     * 添加Cod
     * @param cod 要添加的Cod
     * @return id
     */
    public java.lang.Integer addCod(Cod cod);

    /**
     * 根据id删除一个Cod
     * @param codId 要删除的id
     */
    public void delCodById(int codId);

    /**
     * 修改Cod
     * @param cod 要修改的Cod
     */
    public void updateCod(Cod cod);

    /**
     * 根据id获取单个Cod对象
     * @param codId 要查询的id
     * @return 年级
     */
    public Cod getCodById(int codId);

    /**
     * 根据条件获取Cod列表
     * @param queryCodCondition 查询条件
     * @return 查询结果
     */
    public List<Cod> getCodList(QueryCodCondition queryCodCondition);
}