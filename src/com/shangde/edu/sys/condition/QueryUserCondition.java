package com.shangde.edu.sys.condition;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.edu.sys.domain.Group;

public class QueryUserCondition extends PageQuery{
	private int userId;
	private int groupId;
	private int userType;
	private String searchKey;
	private String searchType;
	List<Group> groupList = new ArrayList<Group>();
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public int getUserId(){
		return userId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}
}