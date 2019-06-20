package com.m.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",columnDefinition="bigint COMMENT '主键,自动生成'")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",columnDefinition="bigint COMMENT '外键,关联用户数据表'")
    private User user;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id",columnDefinition="bigint COMMENT '外键,关联角色数据表'")
    private Role role;
    @Column(name="approval_state",columnDefinition="int(2) default 0 COMMENT '审批状态,0 停用 1 开启'")
    private Integer approvalState;
}
