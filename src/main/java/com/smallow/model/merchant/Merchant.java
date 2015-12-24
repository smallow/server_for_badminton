package com.smallow.model.merchant;

import com.smallow.model.merchant.param.MerchantParameter;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by smallow on 2015/4/22.
 */
@Entity
@Table(name="tb_merchant")
@Cache(region = "all",usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties","sortColumnsString", "flag" })
public class Merchant extends MerchantParameter{
    private static final long serialVersionUID = 822330369002149147L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @Column(name = "address",length = 255)
    private String address;

    @Column(name = "telphone",length =50)
    private String telphone;

    @Column(name = "mobilephone",length = 50)
    private String mobilephone;

    @Column(name = "contactpersonname",length = 50)
    private String contactpersonname;

    @Column(name = "regno",length = 50,nullable = false)
    private String regno;//登记注册证号

    @Column(name = "type",length =50)
    private String type;//企业类型 个体、公司、农村合作社

    @Column(name = "bigtype",length = 50,nullable = false)
    private String bigtype;

    @Column(name = "midtype",length = 50,nullable = false)
    private String midtype;

    @Column(name = "pwd",length = 50,nullable = false)
    private String pwd;

    @Column(name = "last_logintime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;


    @Column
    private Boolean sfTop10;//是否推荐首页前10
    @Column
    private Integer top10Order;//前10的排名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getContactpersonname() {
        return contactpersonname;
    }

    public void setContactpersonname(String contactpersonname) {
        this.contactpersonname = contactpersonname;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBigtype() {
        return bigtype;
    }

    public void setBigtype(String bigtype) {
        this.bigtype = bigtype;
    }

    public String getMidtype() {
        return midtype;
    }

    public void setMidtype(String midtype) {
        this.midtype = midtype;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    public Boolean getSfTop10() {
        return sfTop10;
    }

    public void setSfTop10(Boolean sfTop10) {
        this.sfTop10 = sfTop10;
    }

    public Integer getTop10Order() {
        return top10Order;
    }

    public void setTop10Order(Integer top10Order) {
        this.top10Order = top10Order;
    }
}
