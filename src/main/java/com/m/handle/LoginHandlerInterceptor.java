package com.m.handle;

import com.m.utils.RequestHolderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    protected Logger logger = LoggerFactory.getLogger(LoginHandlerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestHolderUtil.setThreadLocal(request);
        String user = (String) request.getSession().getAttribute("user");
        logger.info("LoginHandlerInterceptor user: " + user);
        if (StringUtils.isEmpty(user)) {
            logger.info("LoginHandlerInterceptor login");
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
