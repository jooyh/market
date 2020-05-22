package com.study.market.commons.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.market.commons.admin.service.AdminAccountService;
import com.study.market.commons.controller.BaseController;
import com.study.market.commons.exceptions.LoginException;

@Controller
@RequestMapping("admin")
public class AdminAccountController extends BaseController{

	@Autowired
	private AdminAccountService adminAccountService;

	private static final String ADM_SESSION_KEY = "ADM_SESSION";

//	@RequestMapping("test1")
//	public String testTiles() {
//		return "/admin/login.page";
//	}
//
//	@RequestMapping("test3")
//	public String testNonTiles() {
//		return "/admin/login";
//	}

	/**
	 * NAME : adminLogin
	 * DESC : [페이지] 관리자 로그인
	 * DATE : 2020. 5. 22.
	 * <pre>
	 * @auther jyh
	 * @return 로그인페이지 경로
	 * </pre>
	 */
	@RequestMapping("login")
	public String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map sessionInfo = (Map) session.getAttribute(ADM_SESSION_KEY);
		if(sessionInfo != null && !sessionInfo.isEmpty()) session.removeAttribute(ADM_SESSION_KEY);
		return "/admin/login.part";
	}

	/**
	 * NAME : accIpList
	 * DESC : [페이지] 접근허용 아이피 목록
	 * DATE : 2020. 5. 22.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("accIpList")
	public String accIpList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		return "/admin/accIpList.page";
	}

	/**
	 * NAME : adminLoginProc
	 * DESC : 관리자 로그인 프로세스
	 * DATE : 2020. 5. 22.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return 관리자 정보
	 * @throws LoginException
	 * </pre>
	 */
	@RequestMapping("loginProc")
	@ResponseBody
	public Map adminLoginProc(HttpServletRequest request, HttpServletResponse response) throws LoginException {
		Map result = adminAccountService.adminLogin(request, response);
		if(result.isEmpty()) throw new LoginException();
		HttpSession session = request.getSession();
		session.setAttribute(ADM_SESSION_KEY, result);
		return result;
//		return "/admin/accIpList.page";
	}

}
