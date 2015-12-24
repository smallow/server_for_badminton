package com.smallow.model.sys;

import com.smallow.model.sys.param.ContentParameter;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by smallow on 2015/4/29.
 */
@Entity
@Table(name="tb_content")
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties","sortColumnsString", "flag" })
public class Content extends ContentParameter{

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @Column(length = 500)
    private String titleImg;

    @Column
    private BigDecimal originalPrice;

    @Column
    private BigDecimal concessionalPrice;

    @Column
    private Boolean needAppointment;//是否需要预约

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectDate;//有效期

    @Column
    private Integer leftNum;//剩余件数

    @Column(length = 5000)
    private String content;

    @Column
    private Long merchantId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getConcessionalPrice() {
        return concessionalPrice;
    }

    public void setConcessionalPrice(BigDecimal concessionalPrice) {
        this.concessionalPrice = concessionalPrice;
    }

    public Boolean getNeedAppointment() {
        return needAppointment;
    }

    public void setNeedAppointment(Boolean needAppointment) {
        this.needAppointment = needAppointment;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
