package com.hhkj.rmo.model;

import javax.persistence.*;

/**
 * Created by smallow on 15/6/14.
 */
@Entity
@Table(name = "rmo_role_pri")
public class RolePri {

    @Id
    @Column(name = "i_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "s_role_code")
    private String roleCode;
    @Column(name = "s_pri_code")
    private String priCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getPriCode() {
        return priCode;
    }

    public void setPriCode(String priCode) {
        this.priCode = priCode;
    }
}
