package com.study.market.commons.admin.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.market.commons.service.BaseService;

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
	 */
	public Map adminLogin(Map paramMap) {
		String admPw = (String) paramMap.get("admPw");
		String shaPw = encoder.encodePassword(admPw,salt);
		paramMap.put("admPw",shaPw);
		Map userInfo = sqlSession.selectOne(super.mkSqlId(NAME_SPACE, "adminLogin"),paramMap);
		return userInfo;
	}
}
