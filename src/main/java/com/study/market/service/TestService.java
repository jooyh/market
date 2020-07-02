package com.study.market.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.market.commons.service.BaseService;

@Service
public class TestService extends BaseService{
	@Autowired
	private SqlSession sqlSession;

	public Map testService() {
		String st = super.mkSqlId(this.getClass().getSimpleName(),"testService");
		Map rslt = new HashMap();
		rslt.put("TEST",sqlSession.selectList(st));
		return rslt;
	}

}
