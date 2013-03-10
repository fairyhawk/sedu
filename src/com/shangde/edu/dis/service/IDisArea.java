package com.shangde.edu.dis.service;

import java.util.List;
import com.shangde.edu.dis.domain.DisArea;
import com.shangde.edu.dis.condition.QueryDisAreaCondition;

/**
 * DisAreaӿ� User: guoqiang.liu Date: 2011-05-14
 */
public interface IDisArea {
	/**
	 * ���DisArea
	 * 
	 * @param disArea
	 *            �DisArea
	 * @return id
	 */
	public Integer addDisArea(DisArea disArea);

	/**
	 * ���idɾ��һ��DisArea
	 */
	public void delDisAreaById();

	/**
	 * �޸�DisArea
	 * 
	 * @param disArea
	 */
	public void updateDisArea(DisArea disArea);

	/**
	 * ���id��ȡ����DisArea����
	 * 
	 * @return �
	 */
	public DisArea getDisAreaById();

	/**
	 * ��������ȡDisArea�б�
	 * 
	 * @param queryDisAreaCondition
	 * @return ���
	 */
	public List<DisArea> getDisAreaList(
			QueryDisAreaCondition queryDisAreaCondition);
}