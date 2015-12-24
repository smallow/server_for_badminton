package com.hhkj.rmo.model;

import com.hhkj.rmo.model.param.PrivilegeParam;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by smallow on 15/6/14.
 */
@Entity
@Table(name = "rmo_privilege")
@JsonIgnoreProperties(value = {"maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "sortColumnsString", "flag"})
public class Privilege  {

    @Id
    @Column(name = "i_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "s_pri_name")
    private String priName;
    @Column(name = "s_pri_code")
    private String priCode;
    @Column(name = "s_pri_desc")
    private String description;

    @Column(name = "s_pri_type")
    private String priType;
    @Column(name = "s_pri_url")
    private String url;

    @Column(name = "s_parent_pri_code")
    private String parentPriCode;

    @Column(name = "s_business_module_code")
    private String businessModuleCode;

    @Column(name = "s_business_module_name")
    private String businessModuleName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPriName() {
        return priName;
    }

    public void setPriName(String priName) {
        this.priName = priName;
    }

    public String getPriCode() {
        return priCode;
    }

    public void setPriCode(String priCode) {
        this.priCode = priCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPriType() {
        return priType;
    }

    public void setPriType(String priType) {
        this.priType = priType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getParentPriCode() {
        return parentPriCode;
    }

    public void setParentPriCode(String parentPriCode) {
        this.parentPriCode = parentPriCode;
    }


    public String getBusinessModuleCode() {
        return businessModuleCode;
    }

    public void setBusinessModuleCode(String businessModuleCode) {
        this.businessModuleCode = businessModuleCode;
    }

    public String getBusinessModuleName() {
        return businessModuleName;
    }

    public void setBusinessModuleName(String businessModuleName) {
        this.businessModuleName = businessModuleName;
    }

    @Override
    public boolean equals(Object o) {
        //如果和自身比较，返回TRUE
        if (this == o) return true;
        //如果不是这个类的实例，返回FALSE
        if (!(o instanceof Privilege)) return false;

        final Privilege u = (Privilege) o;
        if (this.getPriCode().equals(u.getPriCode()))
            return true;
        else
            return false;
    }
}
