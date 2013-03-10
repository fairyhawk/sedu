package com.shangde.edu.velocity.web;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cms.action.TmpHistoryAction;
import com.shangde.edu.cus.condition.QueryAreaCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.domain.Area;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.IAddress;
import com.shangde.edu.cus.service.IArea;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.velocity.domain.Velocity;
import com.shangde.edu.velocity.service.IVelocity;
import java.util.List;
import org.apache.log4j.Logger;

public class velocityWebAction extends CommonAction
{
  private static Logger logger = Logger.getLogger(TmpHistoryAction.class);
  private IAddress addressService;
  private ICustomer customerService;
  private IArea areaService;
  private Area area;
  private Address address;
  private List<Address> addressList;
  private QueryAreaCondition queryAreaCondition;
  private IVelocity velocityService;
  private Velocity velocity;

  public String gotoSpeedPage()
  {
    return "gotoSpeed";
  }

  public void addTestVelocity() {
    
    try {
      Customer customer = null;
      int userId = getLoginUserId();
      if (userId != 0) {
        customer = this.customerService.getCustomerById(userId);
        addressList = this.addressService.getAddressByCusId(userId);
        if (customer != null) {
          getVelocity().setMail(customer.getEmail());
          getVelocity().setMobile(customer.getMobile());
          getVelocity().setSubject(customer.getSubjectId());
        }
      }
      if ((this.addressList != null) && (this.addressList.size() != 0)) {
        this.address = ((Address)this.addressList.get(this.addressList.size() - 1));
        getVelocity().setProvince(this.address.getProvinceId());
        getVelocity().setDistrict(this.address.getCityId());
      }

      Integer addVelocity = this.velocityService.addVelocity(getVelocity());
    }
    catch (RuntimeException e) {
      logger.equals(getVelocity().getBrowser());
      logger.equals(getVelocity().getProblem());
      logger.equals(getVelocity().getBroadBand());
      e.printStackTrace();
    }
  }

  public IAddress getAddressService()
  {
    return this.addressService;
  }

  public void setAddressService(IAddress addressService)
  {
    this.addressService = addressService;
  }

  public Address getAddress()
  {
    if (this.address == null) {
      this.address = new Address();
    }
    return this.address;
  }

  public void setAddress(Address address)
  {
    this.address = address;
  }

  public List<Address> getAddressList()
  {
    return this.addressList;
  }

  public void setAddressList(List<Address> addressList)
  {
    this.addressList = addressList; }

  public IArea getAreaService() {
    return this.areaService;
  }

  public void setAreaService(IArea areaService)
  {
    this.areaService = areaService;
  }

  public Area getArea()
  {
    if (this.area == null) {
      this.area = new Area();
    }
    return this.area;
  }

  public void setArea(Area area)
  {
    this.area = area;
  }

  public QueryAreaCondition getQueryAreaCondition()
  {
    if (this.queryAreaCondition == null) {
      this.queryAreaCondition = new QueryAreaCondition();
    }
    return this.queryAreaCondition;
  }

  public void setQueryAreaCondition(QueryAreaCondition queryAreaCondition)
  {
    this.queryAreaCondition = queryAreaCondition;
  }

  public ICustomer getCustomerService()
  {
    return this.customerService;
  }

  public void setCustomerService(ICustomer customerService)
  {
    this.customerService = customerService;
  }

  public Velocity getVelocity()
  {
    if (this.velocity == null) {
      this.velocity = new Velocity();
    }
    return this.velocity;
  }

  public void setVelocity(Velocity velocity)
  {
    this.velocity = velocity;
  }

  public IVelocity getVelocityService()
  {
    return this.velocityService;
  }

  public void setVelocityService(IVelocity velocityService)
  {
    this.velocityService = velocityService;
  }
}