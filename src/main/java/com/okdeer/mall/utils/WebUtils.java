package com.okdeer.mall.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
/**
 * web请求地址
 * @author Administrator
 *
 */
public final class WebUtils {
    /**
     * Constant for HTTP POST method.
     */
    private static final String POST_METHOD = "POST";
    /**
     * Part of HTTP content type header.
     */
    private static final String MULTIPART = "multipart/";
    private WebUtils() {
    }
    public static final boolean isMultipart(HttpServletRequest request) {
        if (!POST_METHOD.equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String contentType = request.getContentType();
        if (contentType == null) {
            return false;
        }
        if (contentType.toLowerCase(Locale.ENGLISH).startsWith(MULTIPART)) {
            return true;
        }
        return false;
    }
    public static String getWebAppUrl(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        sb.append(request.getScheme()).append("://");
        sb.append(request.getServerName());
        if (request.getServerPort() != 80) {
            sb.append(":").append(request.getServerPort());
        }
        sb.append(request.getContextPath()).append("/");
        return sb.toString();
    }
}

