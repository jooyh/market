package com.study.market.commons.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.market.commons.controller.BaseController;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController{

	@RequestMapping("test1")
	public String testTiles() {
		return "/admin/login.page";
	}

	@RequestMapping("login")
	public String testTiles2() {
		return "/admin/login.part";
	}

	@RequestMapping("test3")
	public String testNonTiles() {
		return "/admin/login";
	}

}
