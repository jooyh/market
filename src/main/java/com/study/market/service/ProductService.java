package com.study.market.service;

import java.util.List;
import java.util.Map;

/**
 * FILE NAME   : ProductService.java
 * PACKAGE     : com.study.market.service
 * PROJECT     : market
 * CREATE DATE : 2020. 5. 14.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 5. 14.     SIWAN       최초작성
 */
public interface ProductService {

	/**
	 * NAME : getProductInfo
	 * DESC : 상품정보 조회
	 * DATE : 2020. 5. 14.
	 * <pre>
	 * @auther SIWAN
	 * @param params
	 * @return 상품정보
	 * </pre>
	 */
	public List getProductInfo(Map params);

	/**
	 * NAME : getProductLineList
	 * DESC : 상품라인 목록 조회
	 * DATE : 2020. 5. 14.
	 * <pre>
	 * @auther SIWAN
	 * @param params
	 * @return 상품라인 목록
	 * </pre>
	 */
	public List getProductLineList();

}
