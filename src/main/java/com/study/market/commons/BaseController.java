package com.study.market.commons;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * FILE NAME   : BaseController.java
 * PACKAGE     : com.study.market.commons
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 3.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 3.     SIWAN       최초작성
 */
public class BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	private static final String JSON_STRING_KEY = "params";
	private static final String USER_SESSION_KEY = "userInfo";

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
	public Map convertRequestToMap(HttpServletRequest request){
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

		Map sessionMap = (Map) request.getSession().getAttribute(USER_SESSION_KEY);
		paramMap.put(USER_SESSION_KEY, sessionMap);

		return paramMap;
	}


}

