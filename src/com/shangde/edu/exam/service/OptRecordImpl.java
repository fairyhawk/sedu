package com.shangde.edu.exam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.exam.condition.QueryOptRecordCondition;
import com.shangde.edu.exam.domain.OptRecord;
import com.shangde.edu.exam.dto.UserQst;

/**
 * OptRecord�������ʵ����?
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
@SuppressWarnings("unchecked")
public class OptRecordImpl extends BaseService implements IOptRecord{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  void addOptRecord(OptRecord optRecord) {
    	simpleDao.createEntity("OptRecord_NS.createOptRecord",optRecord);
    }

    public void delOptRecordById(int qstId, int asrId, int cusId){
    	Map keyMap = new HashMap();
    	keyMap.put("qstId", qstId);
    	keyMap.put("asrId", asrId);
    	keyMap.put("cusId", cusId);
    	
    	simpleDao.deleteEntity("OptRecord_NS.deleteOptRecordById", keyMap);
    	
    }

    public void updateOptRecord(OptRecord optRecord) {
        simpleDao.updateEntity("OptRecord_NS.updateOptRecord",optRecord);
    }

    public OptRecord getOptRecordById(int qstId, int asrId, int cusId) {
    	Map keyMap = new HashMap();
    	keyMap.put("qstId", qstId);
    	keyMap.put("asrId", asrId);
    	keyMap.put("cusId", cusId);
    	
    	return simpleDao.getEntity("OptRecord_NS.getOptRecordById", keyMap);
    }

    public List<OptRecord> getOptRecordList(QueryOptRecordCondition queryOptRecordCondition) {
        return simpleDao.getForList("OptRecord_NS.getOptRecordList",queryOptRecordCondition);
    }

	public void delOptRecordByCusId(int cusId) {
		simpleDao.deleteEntity("OptRecord_NS.deleteOptRecordByCusId", cusId);
	}
	/**
	 * 通过试卷Id获取用户做题历史记录
	 * @author 王超
	 * @param epId
	 * @return
	 */
	public List<UserQst> getUserQstOptionList(int epId){
		return simpleDao.getForList("OptRecord_NS.getUserQstOptionList", epId);
	}
	
	/**
     * 一次添加所有试题记录
     * @author 王超
     * @param optRecord 选项记录
     * @return id
     */
    public  void addManyOptRecord(List<OptRecord> list){
    	simpleDao.createEntity("OptRecord_NS.addManyOptRecord", list);
    }

	@Override
	public PageResult getErrorQstList(QueryOptRecordCondition queryOptRecordCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("OptRecord_NS.getErrorQstList", "OptRecord_NS.getErrorQstCount",queryOptRecordCondition);
	}

	@Override
	public List<OptRecord> getOptRecordList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getErrorQstCountBycusId(Integer userId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("OptRecord_NS.getErrorQstCountBycusId", userId);
	}

}
