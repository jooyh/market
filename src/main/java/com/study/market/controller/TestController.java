package com.study.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.market.commons.controller.BaseController;
import com.study.market.commons.vo.ResultMap;
import com.study.market.service.TestService;

@Controller
@RequestMapping("test")
public class TestController extends BaseController {

	@Autowired
	private TestService ts;

	@RequestMapping("/connection")
	@ResponseBody
	public ResultMap test1() {
		return new ResultMap(ts.testService());
	}

	@RequestMapping("login")
	public String test() {
		return "main";
	}
}
