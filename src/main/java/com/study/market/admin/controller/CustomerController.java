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
@RequestMapping("admin/customer")
public class CustomerController{

	@Autowired
	private CustomerService customerService;

	@RequestMapping("getCustomerList")
	@ResponseBody
	public List getCustomerList(HttpServletRequest request) {
		Map resultMap = new HashMap();
		Map params = (Map) request.getAttribute("params");
		return customerService.getCustomerInfo(params);
	}

}

