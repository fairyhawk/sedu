package com.shangde.edu.finance.webService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:
 *
 * @author		Yangning
 * @date		2012-3-5
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class ContractDSImpl extends BaseService implements IContractDS{
	
	//private static String memToken = new String();
	
	private static final String PREIVATE_KEY = "Sunland";
	
	private static final Logger logger = Logger.getLogger(ContractDSImpl.class);
	@Override
	public synchronized int genDsContract(String contractId,int cusId,float cutSumMoney,float sumMoney,String sign,Integer payment,String contractCDkey) {
		try {
			Date _date = new Date();
			logger.info("__invoke genDsContract___");
			if(md5(contractId+cusId+PREIVATE_KEY).equals(sign)){
				logger.info("__invoke genDsContract___compared");
				Contract contract = new Contract();
				contract.setContractFromUrl("http://haixue.com");
				contract.setContractId(contractId);
				contract.setCusId(cusId);
				contract.setContractSumMoney(sumMoney);
				contract.setRemark("SStore商品");
				contract.setContractFrom("Sstore");
				contract.setWebFrom("ssStore");
				contract.setContractCutSumMoney(cutSumMoney);
				contract.setCreateTime(_date);
				contract.setCusIdAddress(0);
				if(payment==null || payment==1 || payment==4){
					contract.setStatus(1);
					contract.setPayType(1);//支付宝
					contract.setPayTime(_date);
					contract.setFreight(0);
				}else if (payment==3){
					contract.setStatus(0);
					contract.setPayType(2);//货到付款
					contract.setContractSumMoney(sumMoney-15);
					contract.setContractCutSumMoney(cutSumMoney-15);
					contract.setFreight(15);
					contract.setContractCDkey(contractCDkey);//生成16位激活码
				}else{
					contract.setPayType(4);
				}
				int getContractId = simpleDao.createEntity("Contract_NS.createDSContract", contract);
				return getContractId;
			}
		} catch (Exception e) {
			logger.info("ContractDSImpl.genDsContract",e);
		}
		return 0;
	}
	

	@Override
	public synchronized int genDsCashRecord(int status,int sellId, float totalMoney, float cutSumMoney,
			int cusId, int ctId, int crSubjectId, int shopPayType,String crInfo,String contractId,String sign) {
		try {
			logger.info("__invoke genDsCashRecord___");
			if(md5(contractId+cusId+PREIVATE_KEY).equals(sign)){
				logger.info("__invoke genDsCashRecord___compared");
				SellWay sellWay = (SellWay)simpleDao.getEntity("SellWay_NS.getSellWayById", sellId);
				
					CashRecord record = new CashRecord();
					record.setCashRecordPrice(cutSumMoney);
					record.setPrice(totalMoney);
					record.setPackId(sellId);
					record.setType(4);
					record.setStatus(status);
					record.setShopPayTime(new Date());
					record.setShopPayType(shopPayType);
					if(sellWay != null){
						record.setCrInfo(sellWay.getSellName());
						record.setCRSubjectId(sellWay.getSellId());
					}else{
						record.setCrInfo("此商品在ss存在，在highso不存在");
						record.setCRSubjectId(0);
					}
					//SS商品默认为视频
					record.setShopType(status);
					record.setCusId(cusId);
					record.setCtId(ctId);
					record.setCreateTime(new Date());
					
					record.setContractId(contractId);
					int genCashRecordId = simpleDao.createEntity("CashRecord_NS.createCashRecord", record);
					return genCashRecordId;
				
			}
		} catch (Exception e) {
			logger.info("ContractDSImpl.genDsContract",e);
		}
			return 0;
	}
	
	 private  String md5(String s) throws Exception{
	        MessageDigest messageDigest = null;
	        try {
	            messageDigest = MessageDigest.getInstance("MD5");
	            messageDigest.reset();
	            messageDigest.update(s.getBytes("UTF-8"));
	        } catch (NoSuchAlgorithmException e) {
	            System.out.println("NoSuchAlgorithmException caught!");
	            System.exit(-1);
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        byte[] byteArray = messageDigest.digest();
	        StringBuffer md5StrBuff = new StringBuffer();
	        for (int i = 0; i < byteArray.length; i++) {
	            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
	                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
	            else
	                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
	        }
	        return md5StrBuff.toString();
	    }
}
