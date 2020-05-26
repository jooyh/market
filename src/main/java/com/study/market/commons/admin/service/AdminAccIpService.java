package com.study.market.commons.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.market.commons.service.BaseService;

/**
 * @author jyh
 *
 */
@Service
public class AdminAccIpService extends BaseService{

	@Autowired
	private SqlSession sqlSession;

	private static final String NAME_SPACE = "AdminAccIpMapper.";

	public List selectAccIpList(Map params) {
		return sqlSession.selectList(mkSqlId(NAME_SPACE, "selectAccIpList"),params);
	}

}
