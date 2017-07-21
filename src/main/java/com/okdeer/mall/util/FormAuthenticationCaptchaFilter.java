package com.okdeer.mall.util;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扩展认证默认过滤
 * @author ty
 * @date 2014年12月2日 下午10:47:09
 */
public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {
	
	private static final Logger log = LoggerFactory.getLogger(FormAuthenticationCaptchaFilter.class);

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return new UsernamePasswordCaptchaToken(username,password.toCharArray(), rememberMe, host, captcha);
	}
	
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        
    	if (super.isAccessAllowed(request, response, mappedValue)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            // If principal is not null, then the user is known and should be allowed access.
            return subject.getPrincipal() != null;
        }
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String loginUrl = getLoginUrl();
        timeout(httpRequest, httpResponse, loginUrl);
        return false;
    }

    private void timeout(HttpServletRequest request,
                         HttpServletResponse response, String logoutUrl) throws IOException {

        String ajaxFlag = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(ajaxFlag)) {
            response.setHeader("sessionTimeOut", "true");
            response.setHeader("logoutUrl", logoutUrl);
            response.setStatus(408); //请求超时
        } else {
            //saveRequest(request);
            response.getOutputStream().println("<html>");
            response.getOutputStream().println("<script>");
            response.getOutputStream().println("window.open ('" + logoutUrl + "','_top')");
            response.getOutputStream().println("</script>");
            response.getOutputStream().println("</html>");
            response.flushBuffer();
        }
    }

}