package com.smallow.bean;



import core.support.JqueryBaseParameter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by smallow on 2015/5/6.
 */

public class OrderBean extends JqueryBaseParameter{

    private Long id;
    private String title;//订单中产品标题
    private String orderNum;//订单编号
    private Integer contentNum;//份数
    private String createTime;//订单生成时间
    private String status;//订单状态
    private String paymentType;//支付方式
    private BigDecimal totalMoney;//商品总额
    private BigDecimal realPaymentMoney;//实付款
    private String telephone;//手机号
    private String userVoucher;//用户凭证
    private Long merchantId;//商户ID
    private Integer contentId;//产品ID
    private Long userId;//用户ID
    private BigDecimal originalPrice;//产品原单价
    private BigDecimal concessionalPrice;//产品优惠后单价
    private String name;// 商家名称
    private String firstTitleImg;//标题图第一张


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getContentNum() {
        return contentNum;
    }

    public void setContentNum(Integer contentNum) {
        this.contentNum = contentNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getRealPaymentMoney() {
        return realPaymentMoney;
    }

    public void setRealPaymentMoney(BigDecimal realPaymentMoney) {
        this.realPaymentMoney = realPaymentMoney;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserVoucher() {
        return userVoucher;
    }

    public void setUserVoucher(String userVoucher) {
        this.userVoucher = userVoucher;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstTitleImg() {
        return firstTitleImg;
    }

    public void setFirstTitleImg(String firstTitleImg) {
        this.firstTitleImg = firstTitleImg;
    }
}

