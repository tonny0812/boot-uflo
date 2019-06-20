package com.m.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserModel implements Serializable {
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户密码
     */
    private String password;
    /**
     *  职位
     */
    private String position;
    /**
     *  状态
     */
    private Short status;
}
