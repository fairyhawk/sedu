package com.shangde.edu.cus.service;

import java.io.Serializable;

import com.shangde.edu.cus.domain.CusCashProtocal;

/**
 * 功能:流水用户保过信息关联表
 * @author Yangning
 *
 */
public interface ICusProtocalCash extends Serializable {
   
	public int addCusProtocalCash (CusCashProtocal cusCashprotocal);
}