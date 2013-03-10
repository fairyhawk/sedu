package com.shangde.edu.crm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.shangde.common.service.BaseService;
import com.shangde.edu.crm.condition.QueryUserCondition;
import com.shangde.edu.crm.domain.Chance;
import com.shangde.edu.crm.domain.ChanceUser;
import com.shangde.edu.cus.domain.Customer;

/**
 * User�������ʵ����? User: guoqiang.liu Date: 2011-11-03
 */
@SuppressWarnings("unchecked")
public class UserImpl extends BaseService implements IUser {
	QueryUserCondition queryUserCondition;

	public java.lang.Integer addUser(ChanceUser user) {
		user.setCreateTime(new Date());
		return simpleDao.createEntity("Chance_User_NS.createUser", user);
	}

	public void delUserById(int id) {
		simpleDao.deleteEntity("Chance_User_NS.deleteUserById", id);
	}

	public void updateUser(ChanceUser user) {
		simpleDao.updateEntity("Chance_User_NS.updateUser", user);
	}

	public ChanceUser getUserById(int id) {
		return simpleDao.getEntity("Chance_User_NS.getUserById", id);
	}

	public List<ChanceUser> getUserByPhone(String phone) {
		return simpleDao.getForList("Chance_User_NS.getUserByPhone", phone);
	}

	public List<ChanceUser> getUserList(QueryUserCondition queryUserCondition) {
		return simpleDao.getForList("Chance_User_NS.getUserList",
				queryUserCondition);
	}

	public int[] addUser(Customer customer) {
		int[] vals = new int[3];
		int changceUserId = 0;
		vals[2] = 0;
		queryUserCondition = new QueryUserCondition();
		queryUserCondition.setPhone(customer.getMobile());
		List<ChanceUser> chanceUsers = simpleDao.getForList(
				"Chance_User_NS.getUserList", queryUserCondition);// 根据手机号获取记录
		if (chanceUsers.size() == 0) {
			changceUserId = this.addChanceUser(customer, 1);// 新用户直接添加
			vals[1] = 1;

		} else {
			int bigNum = 0;
			ChanceUser oneUser = null;
			for (ChanceUser chanceUser : chanceUsers) {
				if (chanceUser.getUserType() > bigNum) {
					bigNum = chanceUser.getUserType();
					oneUser = chanceUser;
				}
			}
			if (bigNum == 1 || bigNum == 3 || bigNum ==5 || bigNum==7) {// 如果是注册用户或者是转注册用户直接添加
				changceUserId = this.addChanceUser(customer, bigNum);
			}
			if (bigNum == 2  ) {
				vals[2] = 1;// 如果是乐语用户
				oneUser.setUserType(3);
				changceUserId = this.updateChanceUser(customer, oneUser);// 如果是乐语更新状态补充资料
			}
			if ( bigNum == 4) {
				vals[2] = 1;// 如果是留言帐户	
				oneUser.setUserType(5);
				changceUserId = this.updateChanceUser(customer, oneUser);// 如果是留言用户更新状态补充资料
			}
			if ( bigNum == 6) {
				vals[2] = 1;// 如果是CallIn帐户	
				oneUser.setUserType(7);
				changceUserId = this.updateChanceUser(customer, oneUser);// 如果是CallIn用户更新状态补充资料
			}
			vals[1] = 2;
		}
		vals[0] = changceUserId;
		return vals;
	}

	/**
	 * 添加用户库实体
	 * 
	 * @param customer
	 * @return
	 */
	public int addChanceUser(Customer customer, int state) {
		if(customer!=null&&customer.getEmail()!=null)
		{
		int isCun=simpleDao.getEntity("Chance_User_NS.searchCusInCrm", customer.getEmail());//判断email是否存在于crm_user_tbl中
		if(isCun!=0)
		{
			return 0;//如果email在crm_user_tbl 中存在则不添加
		}
		}
		com.shangde.edu.crm.domain.ChanceUser user = null;
		user = this.getUserByPhoneForLY(customer.getMobile());
		if (user == null) {
			user = new com.shangde.edu.crm.domain.ChanceUser();
		}
		user.setId(0);
		user.setMobile(customer.getMobile());
		user.setEmail(customer.getEmail());
		user.setUserType(state); // 1 注册用户2 乐语用户 3 乐语转注册4自然留言5留言转注册 6 CallIn 7 CallIn 转注册
		user.setSubjectId(customer.getSubjectId());
		user.setRegTime(customer.getRegTime());
		user.setCusId(customer.getCusId());
		user.setCreateTime(new Date());
		int changceUserId = simpleDao.createEntity("Chance_User_NS.createUser",
				user);
		return changceUserId;
	}
	/**
	 * 更新用户库实体
	 * 
	 * @param customer
	 * @return
	 */
	public int updateChanceUser(Customer customer, ChanceUser user) {
		user.setEmail(customer.getEmail());
		user.setSubjectId(customer.getSubjectId());
		user.setRegTime(new Date());
		user.setCusId(customer.getCusId());
		simpleDao.updateEntity("Chance_User_NS.updateUser", user);
		Chance chance=new Chance();
		chance.setCrmUserId(user.getId());
		chance.setSubjectId(user.getSubjectId());
		simpleDao.update("Chance_NS.updateChanceForSubjectId", chance);
		int changceUserId = user.getId();
		return changceUserId;
	}
	/**
	 * 根据规则获取符合条件 销售人员
	 * 
	 * @return
	 */
	public int getUserByGuiZe(int subjectId) {
		Integer zeroChanceUserId = simpleDao.getEntity(
				"Chance_User_NS.getZeroChance", subjectId);// 获取没获得过分配的人
		if (zeroChanceUserId != null && zeroChanceUserId > 0) {
			return zeroChanceUserId;
		} else {
			Integer fiveChanceUserId = simpleDao.getEntity(
					"Chance_User_NS.getFiveChance", subjectId);// 获取5个机会以下的要分配的人
			if (fiveChanceUserId != null && fiveChanceUserId > 0) {
				return fiveChanceUserId;
			} else {
				Integer userId = simpleDao.getEntity(
						"Chance_User_NS.getUserByGuiZe", subjectId);// 根据分配规则（转换率进行计算）
				return userId != null ? userId : 0;
			}
		}
	}

	/**
	 * //根据学员id获取crmUser
	 * 
	 * @param cusId
	 * @return
	 */
	public ChanceUser getUserByCusId(int cusId) {
		ChanceUser chanceUser = simpleDao.getEntity(
				"Chance_User_NS.getUserByCusId", cusId);
		return chanceUser;
	}

	/**
	 * 根据手机号查询乐语用户
	 * 
	 * @param mobile
	 * @return
	 */
	public ChanceUser getUserByPhoneForLY(String mobile) {
		ChanceUser chanceUser = simpleDao.getEntity(
				"Chance_User_NS.getUserByPhoneForLY", mobile);
		return chanceUser;
	}

	/**
	 * 获取意外 未分配的学员
	 * 
	 * @return
	 */
	public List<Integer> getYiWaiCus() {
		List<Integer> list = simpleDao.getForList("Chance_User_NS.getYiWaiCus",
				null);
		return list;
	}

	/**
	 * 最终找不到坐席的补齐
	 * 
	 * @param subjectId
	 * @return
	 */
	public int getBuQi(int subjectId) {
		try {
			List<Integer> list = simpleDao.getForList("Chance_User_NS.getBuQi",
					subjectId);
			if (list != null && list.size() != 0) {			
				return list.get(0);
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("指派随机数异常" + e.toString());
			return 0;
		}
	}

	/**
	 * 获取一段时间内注册的学员
	 * 
	 * @return
	 */
	public List<Integer> getCusTime(String all, String begin, String end) {
		Map map = new HashMap();
		map.put("begin", begin);
		map.put("end", end);
		List<Integer> list = null;
		if (all == null || all.equals("")) {
			list = simpleDao.getForList("Chance_User_NS.getCusTime", map);
		}
		if (all != null && all.equals("all")) {
			list = simpleDao.getForList("Chance_User_NS.getCusTimeAll", map);
		}
		return list;
	}

	/**
	 * 获取延时时间
	 * 
	 * @return
	 */
	public java.util.HashMap getTime() {
		java.util.HashMap time = simpleDao.getEntity(
				"Chance_User_NS.getTimingNum", null);
		return time;
	}

	/**
	 * 更新用户状态为已指派机会
	 * 
	 * @param cus_id
	 */
	public void updateCusState(int cus_id) {
		simpleDao.update("Chance_User_NS.updateCusState", cus_id);
	}
	
	/**
	 * 更新最新取的定时时间
	 */
	public void updateCrmTime() {
		simpleDao.update("Chance_User_NS.updateCrmTime", null);
	}

	/**
	 * 更新 message
	 */
	public void updateMes(Map<String,String> mes) {
		simpleDao.update("Chance_User_NS.updateMes", mes);
	}
}
