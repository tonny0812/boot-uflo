package com.m.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "role")
public class Role {
    /**
     * 自增id主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 角色名
     */
    @Column(name = "name")
    private String name;
    /**
     * 角色描述
     */
    @Column(name = "remarks")
    private String remarks;
    /**
     *  创建时间
     */
    @Column(name = "created_time")
    private LocalDate createdTime;
    /**
     *  状态
     */
    @Column(name = "status")
    private Short status;
}
