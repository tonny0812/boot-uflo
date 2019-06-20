package com.m.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestHolderUtil {
    private static final ThreadLocal<HttpServletRequest> threadLocal=new ThreadLocal<HttpServletRequest>();
    public static void setThreadLocal(HttpServletRequest req){
        threadLocal.set(req);
    }
    public static HttpServletRequest getThreadLocal(){
        return threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }
}
