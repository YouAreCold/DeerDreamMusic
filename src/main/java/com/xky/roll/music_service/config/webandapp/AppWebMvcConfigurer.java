package com.xky.roll.music_service.config.webandapp;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 开放权限   让app与web端可以访问到接口
 * @author wjx  
 *
 */
@Configuration
public class AppWebMvcConfigurer extends  WebMvcConfigurerAdapter{
	@Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
	@Bean
    public Filter corsFilter() {
        return new CorsFilter();
    }
}