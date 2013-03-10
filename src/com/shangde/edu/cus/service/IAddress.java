package com.shangde.edu.cus.service;

import java.util.List;

import com.shangde.edu.cus.condition.QueryAddressCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.dto.AddressDTO;

/**
 * Address����ӿ�
 * User: guoqiang.liu
 * Date: 2011-03-07
 */
public interface IAddress {
    /**
     * ���Address
     * @param address Ҫ��ӵ�Address
     * @return id
     */
    public java.lang.Integer addAddress(Address address);

    /**
     * ���idɾ��һ��Address
     * @param id Ҫɾ���id
     */
    public void delAddressById(int id);

    /**
     * �޸�Address
     * @param address Ҫ�޸ĵ�Address
     */
    public void updateAddress(Address address);

    /**
     * ���id��ȡ����Address����
     * @param id Ҫ��ѯ��id
     * @return �꼶
     */
    public Address getAddressById(int id);
    
    /**
     * 根据学员ID得到地址
     */
    public List<Address> getAddressByCusId(int customerId);

    /**
     * ��������ȡAddress�б�
     * @param queryAddressCondition ��ѯ���
     * @return ��ѯ���
     */
    public List<Address> getAddressList(QueryAddressCondition queryAddressCondition);

    /**
     * 根据用户id获取首选地址
     * @param loginUserId
     * @return
     */
	public Address getFirstAddressByCusId(int loginUserId);

	/**
	 * 根据条件获取地址DTO列表
	 * @param queryAddressCondition
	 * @return
	 */
	public List<AddressDTO> getAddressDTOList(
			QueryAddressCondition queryAddressCondition);

	/**
	 * 将用户的地址都改为非常用地址
	 * @param loginUserId
	 */
	public void setAddrsCommonByCusId(int loginUserId);
}