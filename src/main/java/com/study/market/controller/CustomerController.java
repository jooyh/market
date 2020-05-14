package com.study.market.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.market.commons.controller.BaseController;
import com.study.market.commons.vo.ResultMap;
import com.study.market.service.CustomerService;

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
@RequestMapping("customer")
public class CustomerController extends BaseController{

	@Autowired
	private CustomerService customerService;

	@RequestMapping("getCustomerList")
	@ResponseBody
	public ResultMap getCustomerList(HttpServletRequest request) {
		Map params = getParamMap(request);
		return new ResultMap(customerService.getCustomerInfo(params));
	}

}

