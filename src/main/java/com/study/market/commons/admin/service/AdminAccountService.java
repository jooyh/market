package com.study.market.commons.admin.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.market.commons.exceptions.AuthException;
import com.study.market.commons.service.BaseService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AdminAccountService extends BaseService{

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private ShaPasswordEncoder encoder;

//	@Value("${encode.salt}") FIXME
	private String salt = "TEST_MARKET";

	private static final String NAME_SPACE = "AdminAccountMapper.";

	/**
	 * NAME : adminLogin
	 * DESC : 관리자 로그인
	 * DATE : 2020. 5. 22.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return 관리자 정보
	 * </pre>
	 * @throws AuthException
	 */
	public Map adminLogin(Map paramMap) throws AuthException {
		String admPw = (String) paramMap.get("admPw");
		String shaPw = encoder.encodePassword(admPw,salt);
		paramMap.put("admPw",shaPw);
		Map admInfo = sqlSession.selectOne(super.mkSqlId(NAME_SPACE, "adminLogin"),paramMap);
		String token = makeToken(admInfo);
		admInfo.put("token",token);
		return admInfo;
	}

	public int adminRegist(Map paramMap) {
		String admPw = (String) paramMap.get("admPw");
		String shaPw = encoder.encodePassword(admPw,salt);
		paramMap.put("admPw",shaPw);
		return sqlSession.insert(super.mkSqlId(NAME_SPACE, "adminRegist"),paramMap);
	}

	@Value("${jwt.secret}")
	private String secret;
	private String makeToken(Map adminInfo) throws AuthException {
		String jwt = null;
		try {
			jwt = Jwts.builder()
					  .setSubject("user-info")
					  .setExpiration(new Date())
					  .claim("admId", adminInfo.get("admId"))
					  .claim("admType", adminInfo.get("admType"))
					  .signWith(
					    SignatureAlgorithm.HS256,
					    this.secret.getBytes("UTF-8")
					  )
					  .compact();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new AuthException("토큰 생성 중 오류가 발생했습니다.",300);
		}
		return jwt;
	}
}
