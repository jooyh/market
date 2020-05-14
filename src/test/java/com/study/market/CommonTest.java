package com.study.market;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.study.market.configuration.RootConfiguration;
import com.study.market.configuration.ServletConfiguration;
import com.study.market.controller.CustomerController;

/**
 * FILE NAME   : CommonTest.java
 * PACKAGE     : com.study.market
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 10.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 10.     SIWAN       최초작성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfiguration.class , ServletConfiguration.class})
@WebAppConfiguration
public class CommonTest {
	private static final Logger logger = LoggerFactory.getLogger(CommonTest.class);

	@Autowired
	private CustomerController customerController;

	@Autowired
	private WebApplicationContext wac;

	//톰캣을 작동시키지 않아도 컨트롤러 테스트를 진행하게 해줌
	private MockMvc mockmvc;

	@Before
	public void setup() {
		//스프링이 준 WebApplicationContext를 이용해서 mockmvc를 생성
		this.mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.info("setup BoardControllerTest mockMvc...");
	}

	@Test
	public void controllerTest() {
		try {
			logger.info("reflectionTest....S");
			this.mockmvc.perform(post("/admin/customer/getCustomerList").param("params","{\"test\":\"test\"}"))
			.andDo(print())
				//정상 처리 되는지 확인
			.andExpect(status().isOk());
			logger.info("reflectionTest....E");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

