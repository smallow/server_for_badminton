package com.hhkj.rmo.model;

import com.hhkj.rmo.model.param.OrganParam;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by smallow on 2015/6/10.
 */
@Entity
@Table(name = "rmo_organ")
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "sortColumnsString", "flag"})
public class Organ extends OrganParam {

    @Id
    @Column(name = "i_id")
    @GeneratedValue
    private Integer id;
    @Column(name = "s_org_name")
    private String orgName;
    @Column(name = "s_org_code")
    private String orgCode;
    @Column(name = "s_parent_org_code")
    private String parentCode;

    @Column(name = "d_create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "s_org_short_name")
    private String orgShortName;

    @Column(name = "s_org_desc")
    private String orgDesc;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }
}
