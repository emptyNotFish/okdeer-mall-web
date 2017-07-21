package com.okdeer.mall.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 
 * @pr mall
 * @desc controller异常处理类
 * @author chenwj
 * @date 2015年7月27日 下午3:57:39
 * @copyright ©2005-2020 yschome.com Inc. All rights reserved
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex) {
		 return new ModelAndView("/error/500");
	}

}
