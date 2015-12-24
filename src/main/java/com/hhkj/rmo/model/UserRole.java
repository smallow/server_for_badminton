package com.hhkj.rmo.model;

import javax.persistence.*;

/**
 * Created by smallow on 2015/6/16.
 */
@Entity
@Table(name = "rmo_user_role")
public class UserRole  {
    @Id
    @Column(name = "i_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "i_user_id")
    private Integer userId;
    @Column(name = "s_role_code")
    private String roleCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
