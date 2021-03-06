package com.study.market.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.market.commons.service.BaseService;
import com.study.market.service.CustomerService;

/**
 * FILE NAME   : CustomerServiceImpl.java
 * PACKAGE     : com.study.market.serviceImpl
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 3.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 3.     SIWAN       최초작성
 */
@Service
public class CustomerServiceImpl extends BaseService implements CustomerService{

	@Autowired
	private SqlSession sqlSession;

	private static final String NAME_SPACE = "CustomerMapper.";

	@Override
	public List getCustomerInfo(Map params) {
		String sqlId = mkSqlId(NAME_SPACE, "selectCustomerInfo");
		return sqlSession.selectList(sqlId,params);
	}




}

