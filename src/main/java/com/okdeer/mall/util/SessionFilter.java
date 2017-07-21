package com.okdeer.mall.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author YSCGD @
 */
public class SessionFilter implements Filter {

	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		// 登陆url
		String loginUrl = httpRequest.getContextPath();
		String url = httpRequest.getRequestURI();
		String path = url.substring(url.lastIndexOf("/"));
		
		// 超时处理，ajax请求超时设置超时状态，页面请求超时则返回提示并重定向
		if (path.indexOf(".do") != -1 && session.getAttribute("LOGIN_SUCCESS") == null) {
			// 判断是否为ajax请求
			String header = httpRequest.getHeader("x-requested-with");
			if (header != null && "XMLHttpRequest".equalsIgnoreCase(header)) {
				httpResponse.addHeader("sessionstatus", "timeOut");
				httpResponse.addHeader("loginPath", loginUrl);
				// 不可少，否则请求会出错
				chain.doFilter(request, response);
			} else {
				
				String str = "<script language='javascript'>alert('会话过期,请重新登录');" +
						"window.top.location.href='" + loginUrl + "';</script>";
				// 解决中文乱码
				response.setContentType("text/html;charset=UTF-8");
				try {
					PrintWriter writer = response.getWriter();
					writer.write(str);
					writer.flush();
					writer.close();
				} catch (Exception e) {
					LOGGER.warn("输出会话异常错误", e);
				}
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
