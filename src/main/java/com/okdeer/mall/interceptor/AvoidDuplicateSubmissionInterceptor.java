package com.okdeer.mall.interceptor;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * 
 * @pr mall
 * @desc 防止重复提交拦截器
 * @author chenwj
 * @date 2016年1月3日 下午2:49:01
 * @copyright ©2005-2020 yschome.com Inc. All rights reserved
 */
public class AvoidDuplicateSubmissionInterceptor extends
		HandlerInterceptorAdapter {
	/**
	 * log
	 */
	private static final Logger LOG = Logger
			.getLogger(AvoidDuplicateSubmissionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.needSaveToken();
				if (needSaveSession) {
					request.getSession(false).setAttribute("token",generateGUID());
				}

				boolean needRemoveSession = annotation.needRemoveToken();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						LOG.warn("please don't repeat submit,url:"+ request.getServletPath() + "]");
						return false;
					}
					request.getSession(false).removeAttribute("token");
				}
			}
		}
		return true;
	}
	

	private static final Random RANDOM = new Random();

	/**
	 * generateGUID
	 * @return String
	 */
	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

}