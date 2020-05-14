package com.study.market.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.market.commons.service.BaseService;
import com.study.market.service.OrderService;

/**
 * FILE NAME   : OrderServiceImpl.java
 * PACKAGE     : com.study.market.serviceImpl
 * PROJECT     : market
 * CREATE DATE : 2020. 5. 14.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 5. 14.     SIWAN       최초작성
 */
@Service
public class OrderServiceImpl extends BaseService implements OrderService{

	@Autowired
	private SqlSession sqlSession;

	private static final String NAME_SPACE = "OrderMapper.";

	@Override
	public List getOrderList(Map params) {
		String sqlId = mkSqlId(NAME_SPACE, "selectOrderList");
		return sqlSession.selectList(sqlId,params);
	}

	@Override
	public List getOrderDetail(Map params) {
		String sqlId = mkSqlId(NAME_SPACE, "selectOrderDetail");
		return sqlSession.selectList(sqlId,params);
	}

}
