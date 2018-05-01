package com.example.myvue.configure;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix = "my.db")
public class MybatisDbConfigure {

	@Value(value = "${druid.datasource.driver-class-name:com.mysql.jdbc.Driver}")
	private String driverClassName;

	@Value(value = "${druid.datasource.username:root}")
	private String userName;

	@Value(value = "${druid.datasource.url:jdbc:mysql://localhost:3306/students?useUnicode=true&amp;characterEncoding=UTF-8&useSSL=true&amp;zeroDateTimeBehavior=convertToNull}")
	private String url;

	@Value(value = "${druid.datasource.password:Awsdrain9@}")
	private String passWord;

	protected static Log log = LogFactory.getLog(MybatisDbConfigure.class);

	@Bean(name = "sqlSessionFactoryBean")
	public SqlSessionFactoryBean myGetSqlSessionFactory(DataSource dataSource) throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

		// mapperLocations
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		try {
			sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*Mapper.xml"));
		} catch (IOException e) {
			log.info("sqlSessionFactoryBean的setMapperLocations有问题");
			e.printStackTrace();
		}

		// dataSource
		sqlSessionFactoryBean.setDataSource(dataSource);

		// SqlSessionFactory sessionFactory = sqlSessionFactoryBean.getObject();
		return sqlSessionFactoryBean;

	}

	@Bean(name = "dataSource")
	public BasicDataSource myGetDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setPassword(passWord);
		dataSource.setUsername(userName);
		dataSource.setMaxIdle(2);
		dataSource.setMaxActive(20);
		dataSource.setMaxWait(1000);
		dataSource.setInitialSize(2);

		// ？
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setRemoveAbandoned(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(30000);
		dataSource.setNumTestsPerEvictionRun(30);
		dataSource.setMinEvictableIdleTimeMillis(1800000);
		return dataSource;
	}
	//
	// private String driverClassName;
	// private String url;
	// private String passWord;
	// private String userName;

}
