package com.okdeer.mall.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * DESC: Token用户登录状态注解
 * @author LIU.W
 * @DATE 2016年3月11日上午10:51:08
 * @version 0.1.0
 * @copyright ©2005-2020 yschome.com Inc. All rights reserved
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenCheckInterceptor {
	
	String value() default "";
	
	TokenCheckTypeEnum type() default TokenCheckTypeEnum.ORG;
	
}