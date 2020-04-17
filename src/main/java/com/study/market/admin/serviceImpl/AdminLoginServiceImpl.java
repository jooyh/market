package com.study.market.admin.serviceImpl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.market.admin.service.LoginService;

/**
 * FILE NAME   : AdminLoginServiceImpl.java
 * PACKAGE     : com.study.market.admin.serviceImpl
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 17.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 17.     SIWAN       최초작성
 */
@Service
public class AdminLoginServiceImpl implements LoginService{

	@Autowired
	private SqlSession sqlSession;

	private static final String NAME_SPACE = "LoginMapper.";

	@Override
	public Map login(Map loginInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logOut(Map userInfo) {
		// TODO Auto-generated method stub
	}

}

