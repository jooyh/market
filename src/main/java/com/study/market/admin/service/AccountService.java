package com.study.market.admin.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.market.commons.exceptions.AuthException;

/**
 * FILE NAME   : AccountService.java
 * PACKAGE     : com.study.market.admin.service
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 17.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 17.     SIWAN       최초작성
 */
public interface AccountService {

	/**
	 * NAME : login
	 * DESC : 로그인 서비스
	 * DATE : 2020. 4. 17.
	 * <pre>
	 * @auther SIWAN
	 * @param loginInfo  : 로그인정보
	 * @return map       : 사용자정보
	 * </pre>
	 */
	public Map logIn(HttpServletRequest request,HttpServletResponse response) throws AuthException;

	/**
	 * NAME : logOut
	 * DESC : 로그 아웃 서비스
	 * DATE : 2020. 4. 17.
	 * <pre>
	 * @auther SIWAN
	 * @param userInfo : 로그인 사용자 정보
	 * </pre>
	 */
	public void logOut(HttpServletRequest request,HttpServletResponse response);

}

