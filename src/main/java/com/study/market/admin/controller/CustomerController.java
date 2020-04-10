package com.study.market.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.market.admin.service.CustomerService;

/**
 * FILE NAME   : CustomerController.java
 * PACKAGE     : com.study.market.admin.controller
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 10.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 10.     SIWAN       최초작성
 */
@Controller
@RequestMapping("/admin/customer")
public class CustomerController{

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/getCustomerList")
	@ResponseBody
	public Map getCustomerList(HttpServletRequest request) {
		Map resultMap = new HashMap();
		Map params = (Map) request.getAttribute("params");
		List customerList = customerService.getCustomerInfo(params);
		resultMap.put("RESULT",customerList );
		return resultMap;
	}

//	@RequestMapping("/api")
//	@ResponseBody
//	public Map api(HttpServletRequest request){
//		logger.info("api ..... [s]");
//		HttpServletModel servletModel;
//		Object result = null;
//		Map map = new HashMap();
//		try {
//			servletModel = new HttpServletModel(request);
//			logger.info(servletModel.toString());
//			result = ReflectionUtils.invokeMethod(servletModel.method, servletModel.params);
//			logger.info(result.toString());
//		} catch (ClassNotFoundException e) {
//			logger.error("/admin/api", e);
//
//		}
//		map.put("reuslt",result);
//		logger.info("api ..... [e]");
//		return map;
//	}
//
//
//
//	class HttpServletModel {
//        public final String uri;
//        public final String service;
//        public final Class<?> serviceClass;
//        public final String beanName;
//        public final String methodName;
//        public final Method method;
//        public final Map params;
//        public HttpServletModel(HttpServletRequest request) throws ClassNotFoundException {
//            //화면에서 전달받은 request를  Parameters 형식으로 변환
//            Map parameters = convertRequestToMap(request);
//            //서비스 호츨 uri
//            this.uri = (String) parameters.get("uri");
//            //서비스명
//            this.service = uri.substring(uri.lastIndexOf('/') + 1, uri.lastIndexOf('.'));
//            //서비스 클래스
//            this.serviceClass = Class.forName(service);
//            //서비스 bean명
//            String bName = service.substring(service.lastIndexOf('.') + 1);
//            this.beanName = StringUtils.uncapitalize(bName);
//            //호출 메소드 명
//            this.methodName = uri.substring(uri.lastIndexOf('.') + 1);
//            this.method = ReflectionUtils.findMethod(serviceClass, methodName);
//            this.params = parameters;
//
////            if (logger.isinfoEnabled()) {
//                logger.info("===========================================================");
//                logger.info(request.getRequestURI());
//                logger.info("URI : " + this.uri);
//                logger.info("Service class : " + this.service);
//                logger.info("service bean name : " + this.beanName);
//                logger.info("method name : " + this.method);
//                logger.info("params : " + params);
//                logger.info("===========================================================");
////            }
//        }
//		@Override
//		public String toString() {
//			return "HttpServletModel [uri=" + uri + ", service=" + service + ", serviceClass=" + serviceClass
//					+ ", beanName=" + beanName + ", methodName=" + methodName + ", method=" + method + ", params="
//					+ params + "]";
//		}
//    }
}

