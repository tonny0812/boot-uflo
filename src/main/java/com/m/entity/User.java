package com.m.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "user")
@DynamicInsert
public class User implements Serializable {
    /**
     * 自增id主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 用户名
     */
    @Column(name = "name")
    private String name;
    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;
    /**
     *  创建时间
     */
    @Column(name = "created_time")
    private LocalDate createdTime;
    /**
     *  职位
     */
    @Column(name = "position")
    private String position;
    /**
     *  状态
     */
    @Column(name = "status")
    private Short status;
}
