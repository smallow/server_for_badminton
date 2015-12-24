package com.hhkj.rmo.model;

import com.hhkj.rmo.model.param.EmployeeParam;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by smallow on 2015/6/11.
 */
@Entity
@Table(name = "rmo_employee")
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "sortColumnsString", "flag"})
public class Employee extends EmployeeParam{
    @Id
    @Column(name = "i_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "s_name")
    private String name;
    @Column(name = "s_sex")
    private String sex;
    @Column(name = "i_age")
    private Integer age;
    @Column(name = "s_telephone")
    private String telephone;
    @Column(name = "s_mobile")
    private String mobile;
    @Column(name = "i_orgId")
    private Integer orgId;
    @Column(name = "d_create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "b_sf_user")
    private Boolean sfUser;

    @Column(name = "i_user_id")
    private Integer userId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getSfUser() {
        return sfUser;
    }

    public void setSfUser(Boolean sfUser) {
        this.sfUser = sfUser;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
