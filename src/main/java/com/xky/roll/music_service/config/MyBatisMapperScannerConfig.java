package com.xky.roll.music_service.config;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;


/**
 * mybatis配置
 * @author wjx  
 *
 */
@Configuration
// TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MyBatisMapperScannerConfig {
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlserverSqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.xky.roll.music_api.mapper");
//		mapperScannerConfigurer.setBasePackage("com.xky.roll.music_service.mapper");
		return mapperScannerConfigurer;
	}
}
