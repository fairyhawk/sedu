package com.shangde.edu.book.domain;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

public class BuyInfo implements Serializable{
    private int id;
    private String cusName;
    private String address;
    private String tel;
    private String mobilephone;
    private String postalcode;
    private String remark;
    private String bookname;
    private int buySum;
    private int cusId;
    private Date createtime;
        
    public int getBuySum() {
		return buySum;
	}

	public void setBuySum(int buySum) {
		this.buySum = buySum;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public String getCusName(){
        return cusName;
    }

    public void setCusName(String cusName) throws UnsupportedEncodingException{
        this.cusName =  URLDecoder.decode(cusName,"utf-8").replace(" ", "");
    }
        
    public String getAddress(){
        return address;
    }

    public void setAddress(String address) throws UnsupportedEncodingException{
        this.address =URLDecoder.decode(address,"utf-8").replace(" ", "");
    }
        
    public String getTel(){
        return tel;
    }

    public void setTel(String tel){
        this.tel = tel; 
    }
        
    public String getMobilephone(){
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) throws UnsupportedEncodingException{
        this.mobilephone =  URLDecoder.decode(mobilephone,"utf-8").replace(" ", "");
    }
        
    public String getPostalcode(){
        return postalcode;
    }

    public void setPostalcode(String postalcode) throws UnsupportedEncodingException{
        this.postalcode =  URLDecoder.decode(postalcode,"utf-8").replace(" ", ""); 
    }
        
    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark) throws UnsupportedEncodingException{
        this.remark =  URLDecoder.decode(remark,"utf-8").replace(" ", ""); 
    }
        
    public String getBookname(){
        return bookname;
    }

    public void setBookname(String bookname) throws UnsupportedEncodingException{
        this.bookname = URLDecoder.decode(bookname,"utf-8").replace(" ", "");  
    }

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
    
    
}
