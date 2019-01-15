package com.xky.roll.music_service.config.db;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * sqlserver 配置
 * @author wjx  
 *
 */
@Configuration
public class SqlserverDataSourceConfig {

	static final String PACKAGE = "com.xky.roll.music_api.mapper";
	static final String MAPPER_LOCATION = "classpath:mapper/sqlserver/*.xml";

	@Value("${sqlserver.datasource.url}")
	private String url;

	@Value("${sqlserver.datasource.username}")
	private String user;

	@Value("${sqlserver.datasource.password}")
	private String password;

	@Value("${sqlserver.datasource.driverClassName}")
	private String driverClass;

	@Value("${sqlserver.datasource.max-active}")
	private Integer maxactive;

	@Bean(name = "sqlserverDataSource")
	public DataSource sqlserverrDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setMaxActive(maxactive);
		return dataSource;
	}

	@Bean(name = "sqlserverTransactionManager")
	public DataSourceTransactionManager masterTransactionManager() {
		return new DataSourceTransactionManager(sqlserverrDataSource());
	}

	@Bean(name = "sqlserverSqlSessionFactory")
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("sqlserverDataSource") DataSource sqlserverrDataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(sqlserverrDataSource);
		sessionFactory.setTypeAliasesPackage("com.xky.roll.music_api.pojo");
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(SqlserverDataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}
}
