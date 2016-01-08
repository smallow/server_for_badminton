package com.smallow.model;

import com.smallow.model.param.ActivityRecordParameter;
import core.support.DateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by smallow on 15/12/15.
 */
@Entity
@Table(name = "badminton_activity_record")
public class ActivityRecord extends ActivityRecordParameter {

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Column
    private Date date;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column
    private String chargePerson;//负责人
    @Column
    private String contactNumber;//联系电话
    @Column
    private String status;//状态

    @Column
    private Integer playFieldNum;//场地数目
    @Column
    private Integer badmintonNum;//使用的羽毛球数目
    @Column
    private BigDecimal totalMoney;//总费用
    @Column
    private Integer registrationNum;//报名人数
    @Column
    private BigDecimal avgMoney;//人均费用

    @Column
    private Integer groupId;//群id

    @Column
    private String memberIds;//报名人id串

    @Column
    private String venue;//球馆

    @Column
    private Integer createPersonId;//活动发起人ID

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//活动创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPlayFieldNum() {
        return playFieldNum;
    }

    public void setPlayFieldNum(Integer playFieldNum) {
        this.playFieldNum = playFieldNum;
    }

    public Integer getBadmintonNum() {
        return badmintonNum;
    }

    public void setBadmintonNum(Integer badmintonNum) {
        this.badmintonNum = badmintonNum;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(Integer registrationNum) {
        this.registrationNum = registrationNum;
    }

    public BigDecimal getAvgMoney() {
        return avgMoney;
    }

    public void setAvgMoney(BigDecimal avgMoney) {
        this.avgMoney = avgMoney;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Integer getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(Integer createPersonId) {
        this.createPersonId = createPersonId;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
