package com.study.market.commons.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.study.market.commons.exceptions.AuthException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * FILE NAME   : AuthService.java
 * PACKAGE     : com.study.market.commons
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 17.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 17.     SIWAN       최초작성
 */
@Service
public class AuthService {

	@Autowired
	private SqlSession sqlSession;

	@Value("${jwt.secret}")
	private String secret;

	private static final String NAME_SPACE = "AuthMapper.";

	/**
	 * NAME : checkToken
	 * DESC : token 값 체크
	 * DATE : 2020. 4. 17.
	 * <pre>
	 * @auther SIWAN
	 * @param token
	 * @return authMap (인증 토큰,인증 사용자 아이디..)
	 * </pre>
	 * @throws AuthException
	 */
	public Map chkAuthToken(String authToken) throws AuthException {
		Map authMap = sqlSession.selectOne(NAME_SPACE+"chkAuthToken",authToken);
		if(authMap == null || authMap.isEmpty()) {
			throw new AuthException("인증정보가 없습니다.");
		}
		return authMap;
	}

	/**
	 * NAME : insertAuth
	 * DESC :
	 * DATE : 2020. 4. 17.
	 * <pre>
	 * @auther SIWAN
	 * @param userInfo (아이디,비밀번호 ..)
	 * @return result
	 * @throws AuthException
	 * </pre>
	 */
	public String insertAuth(Map userInfo) throws AuthException {
		String userId = (String) userInfo.get("userId");
		String token = this.makeToken(userInfo);
		userInfo.put("authToken",token);
		int result = sqlSession.insert(NAME_SPACE+"insertAuth",userInfo);
		if(result != 1) {
			throw new AuthException("인증정보 등록 중 오류가 발생했습니다.",1);
		}
		return token;
	}

	/**
	 * NAME : makeToken
	 * DESC : 토큰생성
	 * DATE : 2020. 4. 17.
	 * <pre>
	 * @auther SIWAN
	 * @param userId
	 * @return
	 * @throws AuthException
	 * </pre>
	 */
	private String makeToken(Map userInfo) throws AuthException {
		String jwt = null;
		try {
			jwt = Jwts.builder()
					  .setSubject("user-info")
					  .setExpiration(new Date())
					  .claim("userId", userInfo.get("userId"))
					  .claim("userGroup", userInfo.get("userGroup"))
					  .signWith(
					    SignatureAlgorithm.HS256,
					    this.secret.getBytes("UTF-8")
					  )
					  .compact();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new AuthException("토큰 생성 중 오류가 발생했습니다.",2);
		}
		return jwt;
	}
}

