package com.shangde.edu.card.service;

import com.shangde.edu.card.domain.CardCourse;

public interface CardMainService {
	/**
	 * 激活课程卡
	 * @param cardCourse
	 * @throws Exception
	 */
	String updateActivateCardCourse(CardCourse cardCourse,Integer cusId) throws Exception;
}
