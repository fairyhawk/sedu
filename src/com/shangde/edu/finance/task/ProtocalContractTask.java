package com.shangde.edu.finance.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.domain.CusCashProtocal;
import com.shangde.edu.cus.service.ICusProtocalCash;

/**
 * 存储保过协议内容
 * @author Yangning
 *
 */
public class ProtocalContractTask implements Runnable{
	
	private static final Logger logger = Logger.getLogger(ProtocalContractTask.class);

	private List sellCashIdMapList;
	
	private String sellids;
	
	
	private int cusDetialId;
	
	private ISellWay sellWayService;
	
	/**用户保过协议流水关联**/
	private ICusProtocalCash  cusProtocalCashService;
	
	public ProtocalContractTask(List sellCashIdMapList,String sellids,int cusDetialId,ISellWay sellWayService,ICusProtocalCash cusProtocalCashService){
		this.sellCashIdMapList = sellCashIdMapList;
		this.sellids = sellids;
		this.cusDetialId = cusDetialId;
		this.sellWayService = sellWayService;
		this.cusProtocalCashService = cusProtocalCashService;
	}
	
	@Override
	public void run() {
		try{
			List sellWayList = null;
			//先查库，找到所有售卖方式(只查一次)
			if(this.sellids!=null && sellids.trim().length() > 0){
				String[] sellIdArray = this.sellids.split(",");
				if(sellIdArray.length>0){
					List<Integer> sellIdListTemp = new ArrayList<Integer>();
					for(int i=0;i<sellIdArray.length;i++){
						sellIdListTemp.add(Integer.parseInt(sellIdArray[i]));
					}
						sellWayList = sellWayService.getSellWayByIdList(sellIdListTemp);
					}
			}
			
			if(sellWayList != null && sellWayList.size() > 0 && this.sellCashIdMapList != null && this.sellCashIdMapList.size() > 0){
				for(int i = 0;i<sellWayList.size();i++){
					SellWay sell =(SellWay)sellWayList.get(i);
					if(sell != null){
						for (int j = 0;j<this.sellCashIdMapList.size();j++) {
							Map sellCashMap = (HashMap)this.sellCashIdMapList.get(j);
							//如果流水Id与售卖方式中有此保过内容写入库中
							if(sellCashMap.get(sell.getSellId()) != null){
								CusCashProtocal cashProtocal = new CusCashProtocal();
		            			cashProtocal.setCusProtocalId(cusDetialId);
		            			cashProtocal.setCashId(Integer.parseInt(sellCashMap.get(sell.getSellId()).toString()));
		            			cashProtocal.setProtocalContent(sell.getProtocalContent());
		            			cusProtocalCashService.addCusProtocalCash(cashProtocal);
		            			break;
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error("ProtocalContractTask.saveCashProtocal",e);
		}
	}
}
