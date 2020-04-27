package com.study.market.commons.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.market.commons.service.AuthService;

public class Interceptor extends HandlerInterceptorAdapter{

	protected static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

	@Autowired
	private AuthService authService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		this.convertRequestToMap(request);
		String uri = request.getRequestURI();
		logger.debug("===================       START       ===================");
		logger.debug(" Request URI \t:  " + uri);

		Map params = (Map) request.getAttribute("params");
		String authToken = (String) params.get("authToken");
		authService.chkAuthToken(authToken);

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
		logger.debug("===================       END       ===================");
	}

	/**
	 * NAME : convertRequestToMap
	 * DESC : 서버 요청 파라미터를 Map 형태로 변환
	 * DATE : 2020. 4. 3.
	 * <pre>
	 * @auther SIWAN
	 * @param request 서버로 전달된 요청
	 * @return paramMap 맵형식으로 변환된 요청 파라미터
	 * </pre>
	 */
	private static final String JSON_STRING_KEY = "params";
	private void convertRequestToMap(HttpServletRequest request){
		String jsonString = request.getParameter(JSON_STRING_KEY);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtils.hasText(jsonString)) {
			try {
				ObjectMapper om = new ObjectMapper();
				paramMap = om.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
				for( String key : paramMap.keySet() ){
					if(paramMap.get(key) != null) {
						String clsNm = paramMap.get(key).getClass().getName();
						if("LIST".contains(clsNm.toUpperCase())) {
							List<Map<String,Object>> cnvtList =
									om.readValue(
											paramMap.get(key).toString(), new TypeReference<List<Map<String,Object>>>(){}
									);
							paramMap.put(key,cnvtList);
						}
					}
				}
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Map sessionMap = (Map) request.getSession().getAttribute(SESSION_USER_INFO_KEY);
//		paramMap.put(SESSION_USER_INFO_KEY, sessionMap);
		request.setAttribute("params", paramMap);
	}
}
