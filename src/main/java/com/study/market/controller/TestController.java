package com.study.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.market.commons.controller.BaseController;

@Controller
@RequestMapping("test")
public class TestController extends BaseController {

	@RequestMapping("login")
	public String test() {
		return "main";
	}
	@RequestMapping("loginSucc")
	public String test2() {
		return "loginSucc";
	}
	@RequestMapping("loginFail")
	public String test3() {
		return "loginFail";
	}



}
