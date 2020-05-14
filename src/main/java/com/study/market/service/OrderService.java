package com.study.market.service;

import java.util.List;
import java.util.Map;

/**
 * FILE NAME   : OrderService.java
 * PACKAGE     : com.study.market.service
 * PROJECT     : market
 * CREATE DATE : 2020. 5. 14.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 5. 14.     SIWAN       최초작성
 */
public interface OrderService {

	/**
	 * NAME : getOrderList
	 * DESC : 주문 목록 조회
	 * DATE : 2020. 5. 14.
	 * <pre>
	 * @auther SIWAN
	 * @param params
	 * @return 주문목록
	 * </pre>
	 */
	public List getOrderList(Map params);

	/**
	 * NAME : getOrderDetail
	 * DESC : 주문 목록 조회
	 * DATE : 2020. 5. 14.
	 * <pre>
	 * @auther SIWAN
	 * @param params
	 * @return 주문상세
	 * </pre>
	 */
	public List getOrderDetail(Map params);
}
