package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.Difficulty;
import com.shangde.edu.kb.condition.QueryDifficultyCondition;

/**
 * Difficulty管理接口ӿ�
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
public interface IDifficulty {
    /**
     * 添加difficulty
     * @param difficulty 要添加的difficulty
     * @return id 
     */
    public java.lang.Integer addDifficulty(Difficulty difficulty);

    /**
     * ���idɾ��删除difficulty
     * @param dId 删除的Id
     */
    public void delDifficultyById(int dId);

    /**
     * 修改difficulty޸�Difficulty
     * @param difficulty 要修改的difficulty
     */
    public void updateDifficulty(Difficulty difficulty);

    /**
     * 根据id获取单个difficulty对象���id��ȡ����Difficulty����
     * @param dId 要查询的id
     * @return 学习难度�
     */
    public Difficulty getDifficultyById(int dId);

    /**
     * 分页查询��������ȡDifficulty�б�
     * @param queryDifficultyCondition 查询条件���
     * @return 分页结果���
     */
    public List<Difficulty> getDifficultyList(QueryDifficultyCondition queryDifficultyCondition);
    
    /**
     * 根据条件获得单个difficulty对象
     * @param  difficulty查询条件
     * @return difficulty对象
     */
    
    public Difficulty getDifficulty(Difficulty difficulty);
}