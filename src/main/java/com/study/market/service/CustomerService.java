package com.study.market.service;

import java.util.List;
import java.util.Map;

/**
 * FILE NAME   : CustomerService.java
 * PACKAGE     : com.study.market.service
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 3.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 3.     SIWAN       최초작성
 */
public interface CustomerService {

	/**
	 * NAME : getCustomerInfo
	 * DESC : 고객정보 조회
	 * DATE : 2020. 4. 3.
	 * <pre>
	 * @auther SIWAN
	 * @param params 고객
	 * @return 고객정보 목록
	 * </pre>
	 */
	public List getCustomerInfo(Map params);

}

