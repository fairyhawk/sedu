package com.shangde.edu.cus.action;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.IAddress;
import com.shangde.edu.cus.service.IArea;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.domain.Contract;

public class AddressAction extends CommonAction {
	private IAddress addressService;
	private Contract contract;
	private Address address;
	private Customer customer;
	private ICustomer customerService;
	private String addressArea;
	private IArea areaService;



	public String toContractCustomerAddress()
	{		
		try{
			if(address!=null){
				if(address.getId()!=0)
				{
					address=addressService.getAddressById(address.getId());
					if(address!=null){
						
						customer=customerService.getCustomerById(address.getCusId());
						addressArea	=	areaService.getAreaById(address.getProvinceId()).getAreaName()
										+areaService.getAreaById(address.getCityId()).getAreaName()
										+areaService.getAreaById(address.getTownId()).getAreaName()
										+address.getAddress();
					
					}
					
				}else
				{
					return "list";
				}
			}else
			{
				return "list";
			}		
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "toAddress";
	}

	public IAddress getAddressService() {
		return addressService;
	}

	public void setAddressService(IAddress addressService) {
		this.addressService = addressService;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}
	public String getAddressArea() {
		return addressArea;
	}

	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}

	public IArea getAreaService() {
		return areaService;
	}

	public void setAreaService(IArea areaService) {
		this.areaService = areaService;
	}
}
