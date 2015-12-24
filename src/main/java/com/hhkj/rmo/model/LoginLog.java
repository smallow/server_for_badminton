package com.hhkj.rmo.model;


import com.hhkj.rmo.model.param.LoginLogParam;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by smallow on 15/7/5.
 */
@Entity
@Table(name = "sys_loginlog")
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "sortColumnsString", "flag"})
public class LoginLog extends LoginLogParam {

    @Id
    @Column(name = "i_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "s_login_code")
    private String loginCode;

    @Column(name = "s_login_name")
    private String loginName;

    @Column(name = "d_login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;

    @Column(name = "d_logout_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
}
