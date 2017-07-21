package com.okdeer.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.okdeer.base.web.interceptor.CatInterceptor;


/**
 * 注解配置
 * ClassName: CatInterceptorConfig 
 * @author guocp
 * @date 2016年12月31日
 *
 * =================================================================================================
 *     Task ID			  Date			     Author		      Description
 * ----------------+----------------+-------------------+-------------------------------------------
 *
 */
@Configuration 
public class CatInterceptorConfigure extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 对来自/user/** 这个链接来的请求进行拦截
		registry.addInterceptor(new CatInterceptor());
	}
}
