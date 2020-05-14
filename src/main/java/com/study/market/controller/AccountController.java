package com.study.market.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.study.market.commons.exceptions.AuthException;
import com.study.market.commons.exceptions.FileException;
import com.study.market.commons.util.FileUtil;
import com.study.market.commons.vo.ResultMap;
import com.study.market.service.AccountService;

/**
 * FILE NAME   : LoginController.java
 * PACKAGE     : com.study.market.controller
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 17.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 17.     SIWAN       최초작성
 */
@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private FileUtil fileUtil;

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
	 * @throws FileException
	 * @throws AuthException
	 */
	@RequestMapping("login")
	@ResponseBody
//	public Map login(HttpServletRequest request ,HttpServletResponse response ) throws AuthException {
//		Map logInInfo = (Map) request.getAttribute("params");
//		Map userInfo = accountService.login(request,response);
//		if(userInfo == null) userInfo = new HashMap();
//		return userInfo;
//	}
	public ResultMap login(MultipartHttpServletRequest request ) throws FileException {
		return new ResultMap(fileUtil.uploadFiles(request));
	}
}

