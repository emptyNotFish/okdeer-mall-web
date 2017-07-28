package com.okdeer.mall.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okdeer.base.common.utils.StringUtils;
import com.okdeer.base.web.interceptor.AnnotationBasedIgnoreableInterceptor;
import com.okdeer.mall.session.UserSession;
import com.okdeer.mall.session.UserSessionWrapper;
import com.okdeer.mall.shiro.Constant;

/**
 * DESC: 安全校验 拦截器
 * @author LIU.W
 * @DATE 2016年3月11日上午10:19:47
 * @version 0.1.0
 * @copyright ©2005-2020 yschome.com Inc. All rights reserved
 */
@Configuration
public class SecurityCheckInterceptor extends AnnotationBasedIgnoreableInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(SecurityCheckInterceptor.class);

	@Resource
	private UserSessionWrapper userSessionWrapper;

	protected boolean preHandleInternal(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 包含便利店、管家的登入验证
		return appValidate(request, response, handler);
	}

	/**
	 * app登入验证
	 * @param request
	 * @param response
	 * @param handler
	 * @return   
	 * @author guocp
	 * @throws Exception 
	 * @date 2016年12月24日
	 */
	private boolean appValidate(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. 检查是否有TokenCheckInterceptors(用户登录状态)注解
		TokenCheckInterceptor tokenCheckInterceptor = getTokenCheck(handler);
		if (null != tokenCheckInterceptor) {
			// 获取安全验证参数值 token
			String token = (String) request.getParameter(Constant.DEFAULT_TOKEN_KEY);
			response.setContentType("application/json;charset=UTF-8");
			if (StringUtils.isEmpty(token)) {
				redirectLogin(request, response, ExceptionConstant.SECURITY_UNLOGIN, tokenCheckInterceptor.type());
				return false;
			}

			// 用户登录 token有效性校验
			try {
				UserSession session = userSessionWrapper.getUserSession(token);
				if (session == null) {
					redirectLogin(request, response, ExceptionConstant.SECURITY_LOGIN_TIMEOUT,
							tokenCheckInterceptor.type());
					return false;
				} else if (session.isExpire()) {
					userSessionWrapper.delSession(token);
					redirectLogin(request, response, ExceptionConstant.SECURITY_LOGIN_ELSEWHERE,
							tokenCheckInterceptor.type());
					return false;
				}
				// 更新最后时间
				userSessionWrapper.refreshSession(session);
			} catch (Exception e) {
				redirectLogin(request, response, ExceptionConstant.SECURITY_LOGIN_TIMEOUT,
						tokenCheckInterceptor.type());
				return false;
			}
		}
		return true;
	}

	/**
	 * 跳转到登入页面
	 * @param request
	 * @param response
	 * @param message
	 * @param type
	 * @throws Exception   
	 * @author guocp
	 * @date 2017年6月20日
	 */
	private void redirectLogin(HttpServletRequest request, HttpServletResponse response, String message,
			TokenCheckTypeEnum type) throws Exception {
		if (type == TokenCheckTypeEnum.HT5) {
			responseTimeOutHtml5(request, response, message);
		} else {
			responseTimeOutJson(response, message);
		}
	}

	protected void postHandleInternal(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	protected void afterCompletionInternal(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
	}

	/**
	 * DESC: 用户登录状态检查注解
	 */
	private TokenCheckInterceptor getTokenCheck(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		TokenCheckInterceptor tokenCheckInterceptor = (TokenCheckInterceptor) handlerMethod
				.getMethodAnnotation(TokenCheckInterceptor.class);
		if (tokenCheckInterceptor == null) {
			Method method = handlerMethod.getMethod();
			tokenCheckInterceptor = method.getDeclaringClass().getAnnotation(TokenCheckInterceptor.class);
		}

		return tokenCheckInterceptor;
	}

	/**
	 * @desc 返回Map
	 * @param obj 返回数据对象
	 * @param code 返回值()
	 * @param message 返回数据消息
	 * @return map
	 */
	private Map<String, Object> resultDataMap(Object obj, String message, PublicResultCodeEnum result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", obj);
		map.put("message", message);
		// 兼容其他地方接收msg参数 避免报错
		map.put("msg", message);
		map.put("code", result.getStatus());
		return map;
	}

	/**
	 * DESC: token HTML 5 超时调用
	 * @author LIU.W
	 * @param response
	 * @param message
	 * @throws Exception
	 */
	private void responseTimeOutHtml5(HttpServletRequest request, HttpServletResponse response, String message)
			throws Exception {
		String rootURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();

		String ajaxFlag = request.getHeader("x-requested-with");
		if ("XMLHttpRequest".equalsIgnoreCase(ajaxFlag)) {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> resultMap = resultDataMap(null, message, PublicResultCodeEnum.NOT_LOGIN);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.getOutputStream().write(objectMapper.writeValueAsString(resultMap).getBytes());
			response.flushBuffer();

		} else {
			response.getWriter().println("<!DOCTYPE html>");
			response.getWriter().println("<html>");
			response.getWriter().println("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">");
			response.getWriter().println("<head>");
			response.getWriter().println("<script src=\"" + rootURL + "/static/js/phoneCommon.js\"></script>");
			response.getWriter().println("<script>");
			response.getWriter().println("js_tokenTimeoutToLogin(\"" + message + "\")");
			response.getWriter().println("</script>");
			response.getWriter().println("</head>");
			response.getWriter().println("</html>");
			response.flushBuffer();
		}

	}

	/**
	 * DESC: token JSON 超时调用
	 * @author LIU.W
	 * @param response
	 * @param message
	 * @throws Exception
	 */
	private void responseTimeOutJson(HttpServletResponse response, String message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> resultMap = resultDataMap(null, message, PublicResultCodeEnum.NOT_LOGIN);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getOutputStream().write(objectMapper.writeValueAsString(resultMap).getBytes());
		response.flushBuffer();
	}
}
