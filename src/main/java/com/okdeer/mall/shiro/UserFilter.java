/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.okdeer.mall.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DESC: 重写Shiro userFilter 过滤器
 * @author LIU.W
 * @DATE 2016年2月29日下午5:45:55
 * @version 0.1.0
 * @copyright ©2005-2020 yschome.com Inc. All rights reserved
 */
public class UserFilter extends AccessControlFilter {

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
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