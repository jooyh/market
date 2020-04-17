package com.study.market;
/**
 * FILE NAME   : AuthTest.java
 * PACKAGE     : com.study.market
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 17.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 17.     SIWAN       최초작성
 */

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.market.commons.exceptions.AuthException;
import com.study.market.commons.service.AuthService;
import com.study.market.configuration.RootConfiguration;
import com.study.market.configuration.ServletConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfiguration.class , ServletConfiguration.class})
@WebAppConfiguration
public class AuthTest {


	private static final Logger logger = LoggerFactory.getLogger(AuthTest.class);

	@Autowired
	private AuthService authService;

	private Map userInfo;

	@Before
	public void setting() {
		this.userInfo = new HashMap();
		userInfo.put("userId", "TEST_ADMIN");
		userInfo.put("userGroup", "ADMIN");
		userInfo.put("authToken","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2Vycy9Uek1Vb2NNRjRwIiwiZXhwIjoxNTg3MDk5MDgzLCJ1c2VySWQiOiJURVNUX0FETUlOIiwic2NvcGUiOiJBRE1JTiJ9.YKerGN7G9uP5B7X55eXpc9nnXvNdQdTZtCGtZ_DbeVc");
	}

	@Test
	public void chkAuthToken() {
		Map test = null;
		try {
			test = authService.chkAuthToken((String) this.userInfo.get("authToken"));
		} catch (AuthException e) {
			logger.info(e.getMessage());
		}
	}

//	@Test
	public void insertAuth() {
		try {
			int result = authService.insertAuth(this.userInfo);
		} catch (AuthException e) {
			// TODO Auto-generated catch block
			logger.debug(e.getMessage());
		}
	}
}

