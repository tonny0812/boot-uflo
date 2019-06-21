package com.m.utils;

import lombok.Data;

/**
 * 全局返回码定义
 */
@Data
public class CodeMsg {
    private int code;
    private String msg;

    /**
     * 通用异常定义
     */
    public static CodeMsg SUCCESS = new CodeMsg(200,"success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500,"服务端异常");

    /**
     * 登录模块异常,5002XX
     */
    public static CodeMsg COOKIE_ERROR = new CodeMsg(5001,"登录异常,请稍后重试");
    public static CodeMsg USER_CREATE_ERROR = new CodeMsg(5002,"创建用户失败");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
