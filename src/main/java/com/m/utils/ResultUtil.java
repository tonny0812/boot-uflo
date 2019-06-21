package com.m.utils;

import lombok.Data;

/**
 * 返回结果封装工具类
 */
@Data
public class ResultUtil<T> {

    private int code;
    private String msg;
    private T data;

    private ResultUtil(T data) {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = "success";
        this.data = data;
    }

    private ResultUtil(CodeMsg codeMsg) {
        if (codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    /**
     * 成功返回调用
     *
     * @param <T>
     * @return
     */
    public static <T> ResultUtil<T> success(T data) {
        return new ResultUtil<>(data);
    }

    /**
     * 失败返回调用
     *
     * @param <T>
     * @return
     */
    public static <T> ResultUtil<T> error(CodeMsg codeMsg) {
        return new ResultUtil<>(codeMsg);
    }
}
