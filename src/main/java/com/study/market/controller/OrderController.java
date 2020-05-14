package com.study.market.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.market.commons.controller.BaseController;
import com.study.market.commons.vo.ResultMap;
import com.study.market.service.OrderService;

/**
 * FILE NAME   : OrderController.java
 * PACKAGE     : com.study.market.controller
 * PROJECT     : market
 * CREATE DATE : 2020. 5. 14.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 5. 14.     SIWAN       최초작성
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;

	/**
	 * NAME : getOrderList
	 * DESC : 주문목록 조회하기
	 * DATE : 2020. 5. 14.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @return
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping("getOrderList")
	public ResultMap getOrderList(HttpServletRequest request) {
		return new ResultMap(orderService.getOrderList(getParamMap(request)));
	}

	/**
	 * NAME : getOrderDetail
	 * DESC : 주문 상세 조회
	 * DATE : 2020. 5. 14.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @return
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping("getOrderDetail")
	public ResultMap getOrderDetail(HttpServletRequest request) {
		return new ResultMap(orderService.getOrderDetail(getParamMap(request)));
	}
}
