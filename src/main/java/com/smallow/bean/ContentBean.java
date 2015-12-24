package com.smallow.bean;

import com.smallow.model.merchant.Merchant;
import core.support.JqueryBaseParameter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by smallow on 2015/5/21.
 */
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties","sortColumnsString", "flag" })
public class ContentBean extends JqueryBaseParameter implements Serializable{

    private Integer id;
    private String title;
    private String titleImg;
    private BigDecimal originalPrice;
    private BigDecimal concessionalPrice;

    private String effectDate;//有效期
    private String telphone;
    private String mobilephone;
    private String address;
    private Merchant merchant;
    private boolean needAppointMent;

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

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public boolean isNeedAppointMent() {
        return needAppointMent;
    }

    public void setNeedAppointMent(boolean needAppointMent) {
        this.needAppointMent = needAppointMent;
    }
}
