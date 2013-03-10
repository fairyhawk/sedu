package com.shangde.edu.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.edu.crm.condition.QueryChanceCondition;
import com.shangde.edu.crm.domain.Chance;
import com.shangde.edu.crm.domain.ChanceUser;
import com.shangde.edu.crm.domain.Record;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.sys.domain.Subject;

/**
 * Chance�������ʵ����?
 * User: guoqiang.liu
 * Date: 2011-11-03
 */
@SuppressWarnings("unchecked")
public class ChanceImpl extends BaseService implements IChance{
 
    /**
     * 添加机会
     * @param customer
     * @param userId
     * @return
     */
    public void addChance(Customer customer,int crmUserId,int xiaoshouId,String type) {
    	ChanceUser crmUser=simpleDao.getEntity("Chance_User_NS.getUserById", crmUserId);
    	Chance chance=new Chance();
    	chance.setCrmUserId(crmUserId);//用户库id
    	chance.setOrigin(1);
    	chance.setWebName(customer.getCusFromUrl());
    	chance.setSubjectId(customer.getSubjectId());
    	chance.setFollowStatus(2);
    	chance.setCreateTime(new Date());
    	if(type!=null && type.equals("all"))
    	{
    		chance.setUserId(xiaoshouId);
    		chance.setChanceUtime(new Date());
    	}else{
    	chance.setUserId(0);//坐席id 
    	}
       	chance.setBackCusId(xiaoshouId);
    	if(crmUser.getUserType()==4)//如果用户是来自自然来源就不指派给客服用0给覆盖掉
    	{
    		chance.setBackCusId(0);
    	}
    	if(chance.getWebName()!=null&&chance.getWebName().equals("highso.org.cn") && chance.getSubjectId()==5)//highso.org.cn并且项目为5的不自动分配
    	{
    		chance.setBackCusId(0);
    	}
    	chance.setChanceStime(customer.getRegTime());
    	int chanceId= simpleDao.createEntity("Chance_NS.createChance",chance);
    	if(type!=null && type.equals("all"))
    	{
    		 this.addRecord(xiaoshouId,chanceId);
    	}
    	
    }
    /**
     * 根据学员手机获取座席id
     * @param phone
     * @return
     */
    public int getXSIdByChancePhone(String phone){
    	Integer XSId=simpleDao.getEntity("Chance_NS.getXSIdByChancePhone", phone);
    	return XSId!=null?XSId:0;
    }

    /**
     * 添加记录
     * @param userId
     * @param chanceId
     */
    public void addRecord(int xiaoshouId,int chanceId)
    {
    	Record rec=new Record();
		rec.setCommStatus(0);
		rec.setUserId(xiaoshouId);
		rec.setCreateTime(new Date());
		rec.setCrmChanceId(chanceId);
		simpleDao.createEntity("Record_NS.createRecord", rec);
    }
    
    /**
     * 获取指派机会
     * @param id
     * @return
     */
    public Chance getChanceById(int id) {
        return simpleDao.getEntity("Chance_NS.getChanceById",id);
    }
    
    /**
	 * 获取未分配的机会 在过去的两小时中
	 * @return
	 */
	public List<Chance> getChanceNoAssign(String type,String begin,String end){
		Map map = new HashMap();
		map.put("alltype", type);
		map.put("begin", begin);
		map.put("end", end);
		List<Chance> list=simpleDao.getForList("Chance_NS.getCrmUserNoAssign", map);
		return list;
	}
	
	/**
	 * 更新机会表
	 * @param chance
	 */
	public void updateChance(Chance chance){
		simpleDao.update("Chance_NS.updateChance", chance);
	}

	//王迪开始
	/**
	 * 添加机会表 
	 * param Chance
	 * wd
	 */
	 public void addChc(Chance chance){
		 chance.setCreateTime(new Date());
		 simpleDao.createEntity("Chance_NS.createChance", chance);
	 }
	//王迪结束
	
	//王超开始
	/**
     * @author 王超
     * 定时将过期机会转入机会库
     */
	  public void updateCheckChance(QueryChanceCondition queryChanceCondition){
	    	simpleDao.update("Chance_NS.checkChance", queryChanceCondition);
	    }
	  
	  /**
	     * 检查已购买用户
	     */
	    public void updateCheckIsBuy(){
	    	simpleDao.update("Chance_NS.checkIsBuy", null);
	    }
   //王超结束

}
