package com.study.market.serviceImpl;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.market.commons.exceptions.AuthException;
import com.study.market.commons.service.AuthService;
import com.study.market.service.AccountService;

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
public class AdminAccountServiceImpl implements AccountService{

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private AuthService authService;

	@Autowired
	ShaPasswordEncoder encoder;

	private static final String NAME_SPACE = "AccountMapper.";

	@Override
	public Map login(HttpServletRequest request,HttpServletResponse response) throws AuthException {
		Map params = (Map) request.getAttribute("params");
		String userPw = (String) params.get("userPw");
		String shaPw = encoder.encodePassword(userPw,null);
		params.put("userPw", shaPw);
		Map userInfo = sqlSession.selectOne(NAME_SPACE+"adminLogin",params);

//		Cookie[] cookies = request.getCookies();
		//정상로그인 ( 사용자 정보 존재시 )
//		if(userInfo != null) {
			//쿠키 초기화
//			if(cookies != null) {
//				for(Cookie c : cookies) {
//					if("aythToken".equals(c.getName())) {
//						c.setMaxAge(0);
//						c.setValue(null);
//						response.addCookie(c);
//					}
//				}
//			}

//			Cookie cookie = null;
//			//쿠키에 Token 정보 등록
//			String authToken = authService.insertAuth(userInfo);
//			if(authToken == null) throw new AuthException("토큰 생성 중 오류가 발생했습니다.",2);
//			cookie = new Cookie("authToken", authToken);
//			cookie.setMaxAge(COOKIE_LIFE_TM);
//			cookie.setPath("/");
//			response.addCookie(cookie);
//		}
		return userInfo;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}

