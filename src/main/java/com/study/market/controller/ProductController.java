package com.study.market.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.market.commons.controller.BaseController;
import com.study.market.commons.vo.ResultMap;
import com.study.market.service.ProductService;

/**
 * FILE NAME   : ProductController.java
 * PACKAGE     : com.study.market.controller
 * PROJECT     : market
 * CREATE DATE : 2020. 5. 14.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 5. 14.     SIWAN       최초작성
 */
@Controller
@RequestMapping("product")
public class ProductController extends BaseController{

	@Autowired
	private ProductService productService;

	/**
	 * NAME : getProductInfo
	 * DESC : 제품 정보 조회
	 * DATE : 2020. 5. 14.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @return ResultMap
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping("getProductInfo")
	public ResultMap getProductInfo(HttpServletRequest request) {
		return new ResultMap(productService.getProductInfo(getParamMap(request)));
	}


	/**
	 * NAME : getProductLineList
	 * DESC : 제품라인 목록조회
	 * DATE : 2020. 5. 14.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @return ResultMap
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping("getProductLineList")
	public ResultMap getProductLineList(HttpServletRequest request) {
		return new ResultMap(productService.getProductLineList());
	}

}
