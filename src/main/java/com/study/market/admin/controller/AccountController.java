package com.study.market.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.market.admin.service.AccountService;
import com.study.market.commons.exceptions.AuthException;

/**
 * FILE NAME   : LoginController.java
 * PACKAGE     : com.study.market.admin.controller
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 17.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 17.     SIWAN       최초작성
 */
@Controller
@RequestMapping("admin")
public class AccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * NAME : login
	 * DESC : 관리자 로그인
	 *        로그인 성공시 쿠키에 토큰값 추가 후 로그인 사용자 정보 반환
	 * DATE : 2020. 4. 17.
	 * <pre>
	 * @auther SIWAN
	 * @param request  : 요청객체
	 * @param response : 응답객체
	 * @return userInfo : 로그인 사용자 정보 리턴
	 * </pre>
	 * @throws AuthException
	 */
	@RequestMapping("logIn")
	@ResponseBody
	public Map login(HttpServletRequest request ,HttpServletResponse response ) throws AuthException {
		Map logInInfo = (Map) request.getAttribute("params");
		Map userInfo = accountService.logIn(request,response);
		if(userInfo == null) userInfo = new HashMap();
		return userInfo;
	}
}
