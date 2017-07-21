
package com.okdeer.mall.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {

//	private static final String DEFAULT_ERROR_VIEW = "common/error";
	private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

		logger.error("URL请求异常:{}",req.getRequestURL(),e);
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;
		// ModelAndView mav = new ModelAndView();
		// mav.addObject("exception", e);
		// mav.addObject("url", req.getRequestURL());
		// mav.setViewName(DEFAULT_ERROR_VIEW);
		// return mav;
		return "error";
	}

}
