package com.study.market.commons.admin.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@Value("${encode.salt}")
	private String salt;

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
	public Map adminLogin(HttpServletRequest request,HttpServletResponse response) {
		Map params = (Map) request.getAttribute("params");
		String userPw = (String) params.get("admPw");
		String shaPw = encoder.encodePassword(userPw,null);
		params.put("admPw", shaPw);
		Map userInfo = sqlSession.selectOne(NAME_SPACE+"adminLogin",params);
		return userInfo;
	}
}
