package com.study.market.commons.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.market.commons.admin.service.AdminAccIpService;
import com.study.market.commons.admin.service.AdminAccountService;
import com.study.market.commons.admin.service.AdminMenuService;
import com.study.market.commons.controller.BaseController;
import com.study.market.commons.exceptions.AuthException;
import com.study.market.commons.exceptions.LoginException;
import com.study.market.commons.service.AuthService;
import com.study.market.commons.vo.ResultMap;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController{

	@Autowired
	private AdminAccountService adminAccountService;
	@Autowired
	private AdminMenuService adminMenuService;
	@Autowired
	private AdminAccIpService adminAccIpService;
	@Autowired
	private AuthService authService;


	private static final String ADM_SESSION_KEY = "admSession";
	private static final String ADM_MENU_KEY = "admMenu";

//	@RequestMapping("test1")
//	public String testTiles() {
//		return "/admin/login.page";
//	}
//
//	@RequestMapping("test")
//	public String testNonTiles() {
//		return "/admin/test.part";
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

		if(sessionInfo != null && !sessionInfo.isEmpty()) return "redirect:/admin/accIpList";
		//session.removeAttribute(ADM_SESSION_KEY);

//		if(sessionInfo.get("adminType").equals("S")) { //일반 사용자의 경우
//
//		}else { //관리자의 경우
//
//		}

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
		return "/admin/accIpList.page";
	}
	/**
	 * NAME : adminList
	 * DESC : [페이지] 관리자 목록
	 * DATE : 2020. 5. 25.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return 관리자 목록 페이지
	 * </pre>
	 */
	@RequestMapping("adminList")
	public String adminList(HttpServletRequest request, HttpServletResponse response) {
		return "/admin/adminList.page";
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
	 * @throws AuthException
	 * @throws IOException
	 */
	@RequestMapping("loginProc")
	@ResponseBody
	public ResultMap adminLoginProc(HttpServletRequest request, HttpServletResponse response) throws LoginException, AuthException {
		Map loginResult = adminAccountService.adminLogin(super.getParamMap(request));
		boolean isLoginSuccess = loginResult != null;
		if(!isLoginSuccess) throw new LoginException();

//		List menuList = adminMenuService.selectAdminMenuList();
//		HttpSession session = request.getSession();
//		session.setAttribute(ADM_SESSION_KEY, result);
		this.setCookie(response,(String) loginResult.get("token"));
//		session.setAttribute(ADM_MENU_KEY, menuList);
		authService.insertAuth(loginResult);
		Map result = new HashMap();
		result.put("isLoginSuccess",isLoginSuccess);

		return new ResultMap(result);
//		return "/admin/accIpList.page";
	}

	private void setCookie(HttpServletResponse response, String token) {
		this.removeCookie(response,"token");
		//토큰을 쿠키에 지정한다
		Cookie c = new Cookie("token", token) ;
	    // 쿠키에 설명을 추가한다
	    c.setComment("token") ;
	    //1일
	    c.setMaxAge(60*60*24) ;
	    response.addCookie(c);
	}

	private void removeCookie(HttpServletResponse response ,String targetName) {
		Cookie kc = new Cookie(targetName, null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
		kc.setMaxAge(0); // 유효시간을 0으로 설정
		response.addCookie(kc); // 응답 헤더에 추가해서 없어지도록
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(ADM_MENU_KEY);
		request.getSession().removeAttribute(ADM_SESSION_KEY);
		return "redirect:/admin/login";
	}

	@RequestMapping("getAccIpList")
	@ResponseBody
	public ResultMap getAccIpList(HttpServletRequest request) {
		return new ResultMap(adminAccIpService.selectAccIpList(getParamMap(request)));
	}

	@RequestMapping("registProc")
	@ResponseBody
	public ResultMap adminRegist(HttpServletRequest request) {
		return new ResultMap(adminAccountService.adminRegist(getParamMap(request)));
	}

	@RequestMapping("logoutTemp")
	@ResponseBody
	public ResultMap logoutTemp(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute(ADM_MENU_KEY);
		request.getSession().removeAttribute(ADM_SESSION_KEY);
		this.removeCookie(response,"token");
		return new ResultMap(null);
	}

	@RequestMapping("auth")
	@ResponseBody
	public ResultMap authCheck(HttpServletRequest request ) throws AuthException {
		Cookie[] cs = request.getCookies();
		for(Cookie c : cs) {
			System.out.println(c.getName() + ":\t" + c.getValue());
			if("token".equals(c.getName())) {
				return new ResultMap(authService.chkAuthToken(c.getValue()));
			}
		}
		return new ResultMap(null);
//		Map paramMap = getParamMap(request);
//		HttpSession session = request.getSession();
//		Map sessionMap = (Map) session.getAttribute(ADM_SESSION_KEY);
//		ResultMap rm = null;
//
//		if(sessionMap == null) {
//			return new ResultMap(null);
//		}
//		String token = (String) sessionMap.get("token");
//		String paramToken = (String) paramMap.get("token");
//
//		if(!paramToken.equals(token)) {
//			throw new AuthException("유효하지 않은 인증정보 입니다.");
//		}

//		return new ResultMap(sessionMap);
//		return null;
	}
}
