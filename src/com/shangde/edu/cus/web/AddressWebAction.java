package com.shangde.edu.cus.web;

import java.util.List;

import org.apache.log4j.Logger;


import net.sf.json.JSONArray;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cus.condition.QueryAddressCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.dto.AddressDTO;
import com.shangde.edu.cus.service.IAddress;

/**
 * 
 * @author zhouZhiQiang
 * @version 1.0
 */
public class AddressWebAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(AddressWebAction.class);
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 地址服务类
	 */
	private IAddress addressService;
	
	/**
	 * 地址列表
	 */
	private List<Address> addressList;
	
	/**
	 * 地址DTO列表
	 */
	private List<AddressDTO> addressDTOList;
	
	/**
	 * 查询地址条件
	 */
	private QueryAddressCondition queryAddressCondition;
	
	private int location;
	
	/**
	 * 地址实体
	 */
	private Address address;
	
	public String getFirstAddressByCusId() {
		try{
			address = addressService.getFirstAddressByCusId(getLoginUserId());
			setResult(new Result<Address>(false, "success", "", address));
		}catch(Exception e){
			setResult(new Result<Address>(false, "error", "", address));
			logger.error("AddressWebAction.getFirstAddressByCusId", e);
		}
		return "json";
	}
	
	public String showAddressList() {
		try{
			getQueryAddressCondition().setCusId(getLoginUserId());
			addressDTOList = addressService.getAddressDTOList(getQueryAddressCondition());
		}catch(Exception e){
			logger.error("AddressWebAction.showAddressList", e);
			return ERROR;
		}
		return "showAddressList";
	}
	
	public String updateAddrFirst() {
		try{
			addressService.setAddrsCommonByCusId(getLoginUserId());
			address = addressService.getAddressById(address.getId());
			address.setIsFirst(Address.ADDRESS_IS_FIRST_YES);
			addressService.updateAddress(address);
		}catch(Exception e){
			logger.error("AddressWebAction.updateAddrFirst", e);
			return ERROR;
		}
		return "reshowAddressList";
	}
	
	public String deleteAddress() {
		try{
			addressService.delAddressById(address.getId());
		}catch(Exception e){
			logger.error("AddressWebAction.deleteAddress", e);
			return ERROR;
		}
		return "reshowAddressList";
	}
	//删除地址
	public String deleteAddresssf() {
		try{
			addressService.delAddressById(address.getId());
		}catch(Exception e){
			logger.error("AddressWebAction.deleteAddresssf", e);
		}
        return "json";
    }
	//设置为常用地址
	   public String updateAddrFirstsf() {
		   try{
	        addressService.setAddrsCommonByCusId(getLoginUserId());
	        address = addressService.getAddressById(address.getId());
	        address.setIsFirst(Address.ADDRESS_IS_FIRST_YES);
	        addressService.updateAddress(address);
		   }catch(Exception e){
			   logger.error("AddressWebAction.updateAddrFirstsf", e);
		   }
	        return "json";
	   }
	
	public String updateOrAddAddr() {
		try{
			address.setCusId(getLoginUserId());
			if(address.getIsFirst() == 1) {
				addressService.setAddrsCommonByCusId(getLoginUserId());
			}
			if(address.getId() == 0) {
				addressService.addAddress(address);
			} else {
				addressService.updateAddress(address);
			}
		}catch(Exception e){
			 logger.error("AddressWebAction.updateOrAddAddr", e);
			 return ERROR;
		}
		return "reshowAddressList";
	}

	public IAddress getAddressService() {
		return addressService;
	}

	public void setAddressService(IAddress addressService) {
		this.addressService = addressService;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public QueryAddressCondition getQueryAddressCondition() {
		if(queryAddressCondition == null) {
			queryAddressCondition = new QueryAddressCondition();
		}
		return queryAddressCondition;
	}

	public void setQueryAddressCondition(QueryAddressCondition queryAddressCondition) {
		this.queryAddressCondition = queryAddressCondition;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public List<AddressDTO> getAddressDTOList() {
		return addressDTOList;
	}

	public void setAddressDTOList(List<AddressDTO> addressDTOList) {
		this.addressDTOList = addressDTOList;
	}
	
	//购买页面显示收货地址
	public String showAddressListInbuyPage() {
	    try {
	        getQueryAddressCondition().setCusId(getLoginUserId());
	        addressDTOList = addressService.getAddressDTOList(getQueryAddressCondition());
	        if(addressDTOList != null&& addressDTOList.size()>0){
	            JSONArray jsl = JSONArray.fromObject(addressDTOList);
	            this.setResult(new Result<JSONArray>(false,"success",null,jsl));
	        }else{
	            this.setResult(new Result<String>(false,"noaddress",null,""));
	        }
        } catch (Exception e) {
            this.setResult(new Result<String>(false,"error","",""));
        }
	    
	    return "json";
	}
}
