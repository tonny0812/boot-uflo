package com.m.filter;


import com.m.utils.RequestHolderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "requestFilter", urlPatterns = "/*")
public class RequestFilter implements Filter {
    protected Logger logger = LoggerFactory.getLogger(RequestFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RequestHolderUtil.setThreadLocal(request);
        logger.info("ThreadLocal: " + request.getServletPath());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
