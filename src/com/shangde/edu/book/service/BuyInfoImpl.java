package com.shangde.edu.book.service;

import java.util.List;
import com.shangde.edu.book.domain.BuyInfo;
import com.shangde.edu.book.condition.QueryBuyInfoCondition;
import com.shangde.common.service.BaseService;

/**
 * Book�������ʵ����?
 * User: guoqiang.liu
 * Date: 2011-09-07
 */
@SuppressWarnings("unchecked")
public class BuyInfoImpl extends BaseService implements IBuyInfo{

	public void  addBook(BuyInfo buyInfo) {
     simpleDao.createEntity("BookBuyInfo_NS.createBook",buyInfo);
    }

    public void delBookById(){
    }

    public void updateBook(BuyInfo book) {
        simpleDao.updateEntity("BookBuyInfo_NS.updateBook",book);
    }

    public BuyInfo getBookById() {
    	return null;
    }

    public List<BuyInfo> getBookList(QueryBuyInfoCondition queryBookCondition) {
        return simpleDao.getForList("BookBuyInfo_NS.getBookList",queryBookCondition);
    }
}
