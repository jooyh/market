package com.study.market.admin.serviceImpl;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.market.admin.service.LoginService;
import com.study.market.commons.exceptions.AuthException;
import com.study.market.commons.service.AuthService;

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

	@Autowired
	private AuthService authService;

	private static final String NAME_SPACE = "LoginMapper.";

	private static final int COOKIE_LIFE_TM = 24*60*60;

	@Override
	public Map logIn(HttpServletRequest request,HttpServletResponse response) {
		Map params = (Map) request.getAttribute("params");
		Map userInfo = sqlSession.selectOne(NAME_SPACE+"adminLogin",params);
		Cookie[] cookies = request.getCookies();

		//쿠키 초기화
		for(Cookie c : cookies) {
			if("aythToken".equals(c.getName())) {
				c.setMaxAge(0);
				c.setValue(null);
				response.addCookie(c);
			}
		}

		if(userInfo != null) {
			Cookie cookie = null;
			try {
				//쿠키에 Token 정보 등록
				cookie = new Cookie("authToken", authService.insertAuth(userInfo));
				cookie.setMaxAge(COOKIE_LIFE_TM);
			} catch (AuthException e) {
				response.setStatus(e.getErrCode(),e.getMessage());
			}
			response.addCookie(cookie);
		}
		return userInfo;
	}

	@Override
	public void logOut(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
	}

}

