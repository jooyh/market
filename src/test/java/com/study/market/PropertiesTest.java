package com.study.market;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.market.commons.util.FileUtil;
import com.study.market.configuration.RootConfiguration;
import com.study.market.configuration.ServletConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfiguration.class , ServletConfiguration.class})
@WebAppConfiguration
public class PropertiesTest {

	@Value("${jwt.secret}")
	private String secret;
	@Value("${file.uploadBaseDir}")
	private String uploadBaseDir;

	@Autowired
	private FileUtil fu;

	@Test
	public void propTest() throws IOException {
		String resource = "props/market.properties";
		Properties properties = new Properties();
		Reader reader = Resources.getResourceAsReader(resource);
        properties.load(reader);

		String aa = (String) properties.get("file.uploadBaseDir");
//		fu.getPath("/TEST");
		System.out.println(aa);
	}



}
