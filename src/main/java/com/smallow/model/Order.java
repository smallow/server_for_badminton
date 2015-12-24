package com.smallow.model;

import com.smallow.model.param.OrderParameter;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by smallow on 2015/5/5.
 */
@Entity
@Table(name="tb_order")
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties","sortColumnsString", "flag" })
public class Order extends OrderParameter {
    @Id
    @Column
    @GeneratedValue
    private Long id;
    @Column
    private String orderNum;//订单编号
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//订单生成时间
    @Column
    private String status;//订单状态
    @Column
    private Integer contentId;
    @Column
    private BigDecimal totalMoney;//商品总额
    @Column
    private BigDecimal realPaymentMoney;//实付款
    @Column
    private String paymentType;//支付方式
    @Column
    private String deliveryType;//配送方式

    @Column
    private Long receivePersonInfoId;//收货人信息ID
    @Column
    private Long userId;//会员ID

    @Column
    private String telephone;//手机号

    @Column
    private Integer contentNum;//商品份儿数
    @Column
    private String userVoucher;//用户凭证

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Long getReceivePersonInfoId() {
        return receivePersonInfoId;
    }

    public void setReceivePersonInfoId(Long receivePersonInfoId) {
        this.receivePersonInfoId = receivePersonInfoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getContentNum() {
        return contentNum;
    }

    public void setContentNum(Integer contentNum) {
        this.contentNum = contentNum;
    }

    public String getUserVoucher() {
        return userVoucher;
    }

    public void setUserVoucher(String userVoucher) {
        this.userVoucher = userVoucher;
    }
}
