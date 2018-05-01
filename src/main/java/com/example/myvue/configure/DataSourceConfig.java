package com.example.myvue.configure;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/2/9.
 */

//@Configuration
public class DataSourceConfig {
    @Resource
    private Environment env;

    @Bean(/*destroyMethod = "close",*/ name = "dataSource")
    public DruidDataSource dataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("druid.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("druid.datasource.url"));
//        System.out.println("url--"+env.getProperty("druid.datasource.url"));
        dataSource.setUsername(env.getProperty("druid.datasource.username"));
        dataSource.setPassword(env.getProperty("druid.datasource.password"));
        dataSource.setMaxActive(Integer.valueOf(env.getProperty("druid.datasource.maxActive")));
        dataSource.setMaxWait(Integer.valueOf(env.getProperty("druid.datasource.maxWait")));
        dataSource.setInitialSize(Integer.valueOf(env.getProperty("druid.datasource.initialSize")));
        dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("druid.datasource.initialSize")));
        dataSource.setValidationQuery(env.getProperty("druid.datasource.validationQuery"));

        // druid 加密
//        try {
//            dataSource.setFilters("config");
//            dataSource.setConnectionProperties(env.getProperty("druid.datasource.connectionProperties"));
//        } catch (SQLException e) {
//
//        }
        dataSource.setRemoveAbandoned(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(30000);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }
}
