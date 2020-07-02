package com.study.market;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.market.configuration.RootConfiguration;
import com.study.market.configuration.ServletConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfiguration.class , ServletConfiguration.class})
@WebAppConfiguration
public class EncodTest {

	@Value("${jwt.secret}")
	private String secret;
	@Autowired
	private ShaPasswordEncoder encoder;


	@Test
	public void encode() {
		String pw = "1234";

		System.out.println("TEST:"+secret);
		System.out.println("org:"+pw);
		System.out.println("after:"+encoder.encodePassword(pw,secret));
	}


}
