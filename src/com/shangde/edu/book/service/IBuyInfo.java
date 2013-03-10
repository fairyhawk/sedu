package com.shangde.edu.book.service;

import java.util.List;
import com.shangde.edu.book.domain.BuyInfo;
import com.shangde.edu.book.condition.QueryBuyInfoCondition;

/**
 * Book����ӿ�
 * User: guoqiang.liu
 * Date: 2011-09-07
 */
public interface IBuyInfo {
    /**
     * ���Book
     * @param book Ҫ��ӵ�Book
     * @return id
     */
    public void addBook(BuyInfo book);

    /**
     * ���idɾ��һ��Book
     */
    public void delBookById();

    /**
     * �޸�Book
     * @param book Ҫ�޸ĵ�Book
     */
    public void updateBook(BuyInfo book);

    /**
     * ���id��ȡ����Book����
     * @return �꼶
     */
    public BuyInfo getBookById();

    /**
     * ���������ȡBook�б�
     * @param queryBookCondition ��ѯ����
     * @return ��ѯ���
     */
    public List<BuyInfo> getBookList(QueryBuyInfoCondition queryBookCondition);
}