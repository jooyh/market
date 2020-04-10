package com.study.market.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.mysql.jdbc.Driver;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;

@Configuration
public class RootConfiguration {

	private static final String APP_CONFIG_FILE_PATH = "props/market.properties";

	@Value("${db.driverClass}")
	private Class jdbcDriverClassName;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String userName;
	@Value("${db.password}")
	private String password;

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocations(new Resource[] { new ClassPathResource(APP_CONFIG_FILE_PATH) });
		return ppc;
	}

	@Bean
	public DataSource dataSourceSpied() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(this.jdbcDriverClassName);
		dataSource.setUrl(this.url);
		dataSource.setUsername(this.userName);
		dataSource.setPassword(this.password);
		return dataSource;
	}
	@Bean
	public Log4jdbcProxyDataSource dataSource() {
		DataSource ds = this.dataSourceSpied();
		Log4jdbcProxyDataSource dataSource = new Log4jdbcProxyDataSource(ds);
		Log4JdbcCustomFormatter logFormatter = new Log4JdbcCustomFormatter();
		logFormatter.setLoggingType(LoggingType.MULTI_LINE);
		logFormatter.setSqlPrefix("SQL         :  ");
		dataSource.setLogFormatter(logFormatter);
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		 sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//				 .getResources("classpath*:mappers/*.xml"));
				 .getResources("classpath*:com/study/market/*/mappers/*.xml"));
		return sqlSessionFactoryBean;
	}

	 @Bean
	 public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		 SqlSessionTemplate session = new SqlSessionTemplate(sqlSessionFactory);
		 return session;
	 }

	 @Bean
	 public ShaPasswordEncoder shaPasswordEncoder() {
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(512);
		shaPasswordEncoder.setEncodeHashAsBase64(true);
		return shaPasswordEncoder;
	 }

//	 @Bean
//	 public MapperScannerConfigurer mapperScannerConfigurer() {
//		 MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		 mapperScannerConfigurer.setBasePackage("com.study.market.*.dao");
//		 return mapperScannerConfigurer;
//	 }
}