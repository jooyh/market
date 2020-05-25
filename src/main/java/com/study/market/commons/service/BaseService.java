package com.study.market.commons.service;

import java.util.Map;

/**
 * FILE NAME   : BaseService.java
 * PACKAGE     : com.study.market.commons
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 10.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 10.     SIWAN       최초작성
 */
public class BaseService {

	protected String mkSqlId (String nameSpace,String methodNm) {
		StringBuffer sb = new StringBuffer().append(nameSpace).append(methodNm);
		return sb.toString();
	}


	protected boolean validParams(Map params,String ... validKeys) {

		return true;
	}
}

